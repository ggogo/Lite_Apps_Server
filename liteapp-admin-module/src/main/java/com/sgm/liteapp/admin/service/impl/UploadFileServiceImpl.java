package com.sgm.liteapp.admin.service.impl;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgm.liteapp.admin.exception.ResultException;
import com.sgm.liteapp.admin.model.dto.UploadInfoflowviewsJson;
import com.sgm.liteapp.admin.model.dto.UploadMainInfoJson;
import com.sgm.liteapp.admin.model.dto.UploadPagesJson;
import com.sgm.liteapp.admin.model.dto.UploadViewsJson;
import com.sgm.liteapp.admin.model.entity.TcLiteappConfig;
import com.sgm.liteapp.admin.model.entity.TcLiteappViewConfig;
import com.sgm.liteapp.admin.model.entity.TlLiteappRelease;
import com.sgm.liteapp.admin.model.mapper.TcLiteappConfigMapper;
import com.sgm.liteapp.admin.model.mapper.TcLiteappViewConfigMapper;
import com.sgm.liteapp.admin.model.mapper.TlLiteappReleaseMapper;
import com.sgm.liteapp.admin.service.UploadFileService;
import com.sgm.liteapp.admin.utils.CommonConstants;
import com.sgm.liteapp.admin.utils.UUIDUtils;
import com.sgm.liteapp.commons.utils.ZipUtils;

@Service
public class UploadFileServiceImpl implements UploadFileService {

	@Autowired
	private TcLiteappConfigMapper tcLiteappConfigMapper;

	@Autowired
	private TlLiteappReleaseMapper tlLiteappReleaseMapper;

	@Autowired
	private TcLiteappViewConfigMapper tcLiteappViewConfigMapper;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String processHandleUploadInfo(String tempFileId, byte[] zipFile) throws Exception {
		String jsonStr = ZipUtils.searchFileText(new ByteArrayInputStream(zipFile), "config.json");
		if (StringUtils.isEmpty(jsonStr)) {
			throw new ResultException(500, "无法从上传包里找到config.json文件");
		}
		ObjectMapper mapper = new ObjectMapper();
		UploadMainInfoJson mainInfo = mapper.readValue(jsonStr, UploadMainInfoJson.class);

		QueryWrapper<TcLiteappConfig> queryWrapper = new QueryWrapper<TcLiteappConfig>();
		queryWrapper.eq("GUID", mainInfo.getGuid()).eq("VERSION", mainInfo.getVersion());
		TcLiteappConfig checkLiteappConfig = tcLiteappConfigMapper.selectOne(queryWrapper);
		if (checkLiteappConfig != null) {
			throw new ResultException(500,
					"轻应用Id：" + mainInfo.getGuid() + " 版本号：" + mainInfo.getVersion() + " 在系统中已经存在");
		}

		String liteappReleaseId = UUIDUtils.getUUID();
		TlLiteappRelease tlLiteappRelease = new TlLiteappRelease();
		tlLiteappRelease.setLiteappReleaseId(liteappReleaseId);
		tlLiteappRelease.setLiteappName(mainInfo.getName());
		tlLiteappRelease.setAppVersionNumber(mainInfo.getVersion());
		tlLiteappRelease.setPackageUrl(tempFileId);// 这是临时文件的位置，上架后需要替换成正式地址
		tlLiteappRelease.setCreateDate(new Date());
		tlLiteappRelease.setCreateBy("");
		tlLiteappRelease.setStatus(CommonConstants.LITEAPP_RELEASE_STATUS_PENDING_PUBLISHED);
		tlLiteappRelease.setSubscribeStatus(CommonConstants.LITEAPP_SUBSCRIBE_STATUS_AUTONOMOUS_SUBSCRIBE);
		tlLiteappReleaseMapper.insert(tlLiteappRelease);

		String liteappConfigId = UUIDUtils.getUUID();
		TcLiteappConfig tcLiteappConfig = new TcLiteappConfig();
		tcLiteappConfig.setLiteappConfigId(liteappConfigId);
		tcLiteappConfig.setLiteappReleaseId(liteappReleaseId);
		tcLiteappConfig.setFriendlyName(mainInfo.getFriendlyname());
		tcLiteappConfig.setSmallIcon(mainInfo.getAppicon().getSmall());
		tcLiteappConfig.setBigIcon(mainInfo.getAppicon().getBig());
		tcLiteappConfig.setInfoIcon(mainInfo.getInfoicon());
		tcLiteappConfig.setIntor(mainInfo.getIntro());
		tcLiteappConfig.setVersion(mainInfo.getVersion());
		tcLiteappConfig.setHardwareVersion(mainInfo.getPlatformInfo().getHardwareversion());
		tcLiteappConfig.setSoftwareVersion(mainInfo.getPlatformInfo().getSoftwareversion());
		tcLiteappConfig.setAppLevel(mainInfo.getApplevel());
		tcLiteappConfig.setApiLevel("");
		tcLiteappConfig.setAppType(mainInfo.getApptype());
		tcLiteappConfig.setGuid(mainInfo.getGuid());
		tcLiteappConfig.setShortcut(mainInfo.getShortcut());
		tcLiteappConfig.setCreateDate(new Date());
		tcLiteappConfig.setCreateBy("");
		tcLiteappConfigMapper.insert(tcLiteappConfig);

		List<UploadViewsJson> views = mainInfo.getViews();
		for (UploadViewsJson view : views) {
			TcLiteappViewConfig tcLiteappViewConfig = new TcLiteappViewConfig();
			tcLiteappViewConfig.setLiteappViewConfigId(UUIDUtils.getUUID());
			tcLiteappViewConfig.setLiteappConfigId(liteappConfigId);
			tcLiteappViewConfig.setViewType(view.getType());
			tcLiteappViewConfig.setGuid(view.getGuid());
			tcLiteappViewConfig.setName(view.getName());
			tcLiteappViewConfig.setFriendlyName(view.getFriendlyname());
			tcLiteappViewConfig.setVersion(view.getVersion());
			tcLiteappViewConfig.setSmallIcon(view.getIcon().getSmall());
			tcLiteappViewConfig.setBigIcon(view.getIcon().getBig());
			tcLiteappViewConfig.setThumbnail(view.getThumbnail());
			tcLiteappViewConfig.setPath(view.getPath());
			tcLiteappViewConfig.setDisplay(view.getDisplay());
			tcLiteappViewConfig.setCreateBy("");
			tcLiteappViewConfig.setCreateDate(new Date());
			if (CommonConstants.VIEWTYPE_WIDGET.equals(view.getType())) {
				tcLiteappViewConfig.setWidth(view.getWidth());
				tcLiteappViewConfig.setHeight(view.getHeight());
			} else if (CommonConstants.VIEWTYPE_DETAILINFO.equals(view.getType())) {

			}
			tcLiteappViewConfigMapper.insert(tcLiteappViewConfig);
		}

		List<UploadInfoflowviewsJson> infoflowviews = mainInfo.getInfoflowviews();
		for (UploadInfoflowviewsJson infoFlowView : infoflowviews) {
			TcLiteappViewConfig tcLiteappViewConfig = new TcLiteappViewConfig();
			tcLiteappViewConfig.setLiteappViewConfigId(UUIDUtils.getUUID());
			tcLiteappViewConfig.setLiteappConfigId(liteappConfigId);
			tcLiteappViewConfig.setName(infoFlowView.getName());
			tcLiteappViewConfig.setFriendlyName(infoFlowView.getFriendlyName());
			tcLiteappViewConfig.setGuid(infoFlowView.getGuid());
			tcLiteappViewConfig.setVersion(infoFlowView.getVersion());
			tcLiteappViewConfig.setThumbnail(infoFlowView.getThumbnail());
			tcLiteappViewConfig.setPath(infoFlowView.getPath());
			tcLiteappViewConfig.setCreateBy("");
			tcLiteappViewConfig.setCreateDate(new Date());
			tcLiteappViewConfig.setViewType(CommonConstants.VIEWTYPE_INFOFLOW);
			tcLiteappViewConfigMapper.insert(tcLiteappViewConfig);
		}

		List<UploadPagesJson> pages = mainInfo.getPages();
		for (UploadPagesJson page : pages) {
			TcLiteappViewConfig tcLiteappViewConfig = new TcLiteappViewConfig();
			tcLiteappViewConfig.setLiteappViewConfigId(UUIDUtils.getUUID());
			tcLiteappViewConfig.setLiteappConfigId(liteappConfigId);
			tcLiteappViewConfig.setGuid(page.getGuid());
			tcLiteappViewConfig.setName(page.getName());
			tcLiteappViewConfig.setVersion(page.getVersion());
			tcLiteappViewConfig.setFriendlyName(page.getFriendlyname());
			tcLiteappViewConfig.setPath(page.getPath());
			tcLiteappViewConfig.setCreateBy("");
			tcLiteappViewConfig.setCreateDate(new Date());
			tcLiteappViewConfig.setViewType(CommonConstants.VIEWTYPE_PAGE);
			tcLiteappViewConfigMapper.insert(tcLiteappViewConfig);
		}
		return liteappReleaseId;
	}

}
