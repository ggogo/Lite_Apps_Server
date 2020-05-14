package com.sgm.liteapp.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgm.liteapp.admin.manager.LiteappReleaseManager;
import com.sgm.liteapp.admin.manager.PKISignatureManager;
import com.sgm.liteapp.admin.model.dto.LiteappMainDetailDto;
import com.sgm.liteapp.admin.model.dto.LiteappMainDto;
import com.sgm.liteapp.admin.model.dto.LiteappPlatformDto;
import com.sgm.liteapp.admin.model.entity.LiteappPackageUrl;
import com.sgm.liteapp.admin.model.entity.TcLiteappConfig;
import com.sgm.liteapp.admin.model.entity.TcLiteappViewConfig;
import com.sgm.liteapp.admin.model.entity.TlLiteappRelease;
import com.sgm.liteapp.admin.model.entity.TmCode;
import com.sgm.liteapp.admin.model.entity.TtBuildinLiteapp;
import com.sgm.liteapp.admin.model.entity.TtLiteappPlatform;
import com.sgm.liteapp.admin.model.entity.TtStoreLiteapp;
import com.sgm.liteapp.admin.model.mapper.TcLiteappConfigMapper;
import com.sgm.liteapp.admin.model.mapper.TcLiteappViewConfigMapper;
import com.sgm.liteapp.admin.model.mapper.TlLiteappReleaseMapper;
import com.sgm.liteapp.admin.model.mapper.TmCodeMapper;
import com.sgm.liteapp.admin.model.mapper.TtBuildinLiteappMapper;
import com.sgm.liteapp.admin.model.mapper.TtLiteappPackageUrlMapper;
import com.sgm.liteapp.admin.model.mapper.TtLiteappPlatformMapper;
import com.sgm.liteapp.admin.model.mapper.TtStoreLiteappMapper;
import com.sgm.liteapp.admin.service.LiteappMainService;
import com.sgm.liteapp.admin.utils.CommonConstants;
import com.sgm.liteapp.admin.utils.UUIDUtils;
import com.sgm.liteapp.commons.cms.CMSService;
import com.sgm.liteapp.commons.cms.Material;
import com.sgm.liteapp.commons.csapi.CSAPIService;
import com.sgm.liteapp.commons.csapi.FileEntity;
import com.sgm.liteapp.commons.utils.CollectionUtils;
import com.sgm.liteapp.commons.utils.VersionUtils;

@Service
public class LiteappMainServiceImpl implements LiteappMainService {

	@Autowired
	private TmCodeMapper tmCodeMapper;

	@Autowired
	private TtStoreLiteappMapper ttStoreLiteappMapper;

	@Autowired
	private TcLiteappConfigMapper tcLiteappConfigMapper;

	@Autowired
	private TtBuildinLiteappMapper ttBuildinLiteappMapper;

	@Autowired
	private TlLiteappReleaseMapper tlLiteappReleaseMapper;

	@Autowired
	private TtLiteappPlatformMapper ttLiteappPlatformMapper;

	@Autowired
	private TcLiteappViewConfigMapper tcLiteappViewConfigMapper;

	@Autowired
	private TtLiteappPackageUrlMapper liteappPackageUrlMapper;

	@Autowired
	private LiteappReleaseManager liteappReleaseManager;

	@Autowired
	private CSAPIService csapiService;

	@Autowired
	private PKISignatureManager signatureManager;

	@Autowired
	private CMSService cmsService;

	@Override
	public Page<TlLiteappRelease> liteappMainQuery(LiteappMainDto dto) {
		Page<TlLiteappRelease> page = new Page<TlLiteappRelease>(dto.getPageNo(), dto.getSize());
		List<TlLiteappRelease> list = tlLiteappReleaseMapper.liteappMainQuery(page, dto);
		page.setRecords(list);
		return page;
	}

	@Override
	public LiteappMainDetailDto liteappMainDetail(LiteappMainDto dto) {
		LiteappMainDetailDto detail = new LiteappMainDetailDto();
		// 轻应用基础信息
		TcLiteappConfig tcLiteappConfig = tcLiteappConfigMapper.queryTcLiteappConfigById(dto.getLiteappReleaseId());
		detail.setTcLiteappConfig(tcLiteappConfig);
		// oem code
		List<TmCode> oemCodeList = tmCodeMapper.queryTmCodeByCodeType(CommonConstants.VEHICLE_MASTER_TYPE_OMEFLAG);
		detail.setOemCodeList(oemCodeList);
		// software code
		List<TmCode> softwareList = tmCodeMapper.queryTmCodeByCodeType(CommonConstants.VEHICLE_MASTER_TYPE_SOFTWARE);
		detail.setSoftwareList(softwareList);
		// hardware code
		List<TmCode> hardwareList = tmCodeMapper.queryTmCodeByCodeType(CommonConstants.VEHICLE_MASTER_TYPE_HARDWARE);
		detail.setHardwareList(hardwareList);

		QueryWrapper<TtLiteappPlatform> queryWrapperPlatform = new QueryWrapper<TtLiteappPlatform>();
		queryWrapperPlatform.eq("LITEAPP_RELEASE_ID", dto.getLiteappReleaseId()).orderByAsc("HARDWARE_CODE",
				"SOFTWARE_CODE");
		List<TtLiteappPlatform> platformList = ttLiteappPlatformMapper.selectList(queryWrapperPlatform);
		detail.setPlatformList(platformList);
		// 小卡片信息
		List<TcLiteappViewConfig> smallViewList = tcLiteappViewConfigMapper
				.queryTcLiteappViewConfigByReleaseIdAndViewType(dto.getLiteappReleaseId(),
						new String[] { CommonConstants.VIEWTYPE_WIDGET, CommonConstants.VIEWTYPE_PAGE,
								CommonConstants.VIEWTYPE_DETAILINFO });
		detail.setSmallViewList(smallViewList);
		// 消息卡片信息
		List<TcLiteappViewConfig> infoflowViewList = tcLiteappViewConfigMapper
				.queryTcLiteappViewConfigByReleaseIdAndViewType(dto.getLiteappReleaseId(),
						new String[] { CommonConstants.VIEWTYPE_INFOFLOW });
		detail.setInfoflowViewList(infoflowViewList);

		return detail;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String proccesLiteappMainEditData(LiteappMainDto dto) {
		TlLiteappRelease tlLiteappRelease = tlLiteappReleaseMapper.selectById(dto.getLiteappReleaseId());
		if (tlLiteappRelease == null) {
			throw new RuntimeException("未找到轻应用");
		}
		if (dto.getOemCode() == null) {
			throw new RuntimeException("OEM代码为空");
		}
		QueryWrapper<TtLiteappPlatform> queryWrapper = new QueryWrapper<TtLiteappPlatform>();
		queryWrapper.eq("LITEAPP_RELEASE_ID", dto.getLiteappReleaseId());
		ttLiteappPlatformMapper.delete(queryWrapper);

		for (LiteappPlatformDto liteappPlatform : dto.getLiteappPlatformList()) {
			TtLiteappPlatform ttLiteappPlatform = new TtLiteappPlatform();
			ttLiteappPlatform.setLiteappPlatformId(UUIDUtils.getUUID());
			ttLiteappPlatform.setLiteappReleaseId(dto.getLiteappReleaseId());
			ttLiteappPlatform.setOemCode(dto.getOemCode());
			ttLiteappPlatform.setSoftwareCode(liteappPlatform.getSoftwareCode());
			ttLiteappPlatform.setHardwareCode(liteappPlatform.getHardwareCode());
			ttLiteappPlatform.setIsSubscribe(liteappPlatform.getIsSubscribe());
			ttLiteappPlatform.setCreateBy("");
			ttLiteappPlatform.setCreateDate(new Date());
			ttLiteappPlatformMapper.insert(ttLiteappPlatform);
		}

		tlLiteappRelease.setLastUpdateDate(new Date());
		tlLiteappRelease.setLiteappReleaseId(dto.getLiteappReleaseId());
		tlLiteappRelease.setSubscribeStatus(dto.getSubscribeStatus());
		tlLiteappReleaseMapper.updateById(tlLiteappRelease);

		return "Success";
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String proccesLiteappMainPublish(LiteappMainDto dto) throws Exception {
		TlLiteappRelease tlLiteappRelease = tlLiteappReleaseMapper.selectById(dto.getLiteappReleaseId());
		if (tlLiteappRelease == null) {
			throw new RuntimeException("未找到轻应用");
		}

		QueryWrapper<TtLiteappPlatform> queryWrapper = new QueryWrapper<TtLiteappPlatform>();
		queryWrapper.eq("LITEAPP_RELEASE_ID", dto.getLiteappReleaseId());
		List<TtLiteappPlatform> liteappPlatformList = ttLiteappPlatformMapper.selectList(queryWrapper);

		TcLiteappConfig tcLiteappConfig = tcLiteappConfigMapper.queryTcLiteappConfigById(dto.getLiteappReleaseId());

		QueryWrapper<TtBuildinLiteapp> deleteWrapperBuildin = new QueryWrapper<TtBuildinLiteapp>();
		deleteWrapperBuildin.eq("LITEAPP_GUID", tcLiteappConfig.getGuid());
		ttBuildinLiteappMapper.delete(deleteWrapperBuildin);

		// 应用包下载链接信息，分为全包和差分包两种
		List<LiteappPackageUrl> packageUrls = new ArrayList<LiteappPackageUrl>();
		Map<String, String> diffPackageMapping = new HashMap<String, String>();

		for (TtLiteappPlatform ttLiteappPlatform : liteappPlatformList) {
			/* 应用包信息 */
			LiteappPackageUrl packageUrl = new LiteappPackageUrl();
			packageUrl.setLiteappPackageUrlId(UUIDUtils.getUUID());
			packageUrl.setOemCode(ttLiteappPlatform.getOemCode());
			packageUrl.setSoftwareCode(ttLiteappPlatform.getSoftwareCode());
			packageUrl.setHardwareCode(ttLiteappPlatform.getHardwareCode());
			packageUrl.setSoftwareVersion(tcLiteappConfig.getSoftwareVersion());
			packageUrl.setHardwareVersion(tcLiteappConfig.getHardwareVersion());
			packageUrl.setLiteappGuidId(tcLiteappConfig.getGuid());
			packageUrl.setCreateDate(new Date());
			packageUrl.setLastUpdateDate(null);
			packageUrl.setToVersion(tcLiteappConfig.getVersion());
			packageUrl.setOriginalPackageUrl(tlLiteappRelease.getPackageUrl());
			packageUrls.add(packageUrl);
			/* 应用商店信息 */
			QueryWrapper<TtStoreLiteapp> wrapperStore = new QueryWrapper<TtStoreLiteapp>();
			wrapperStore.eq("OEM_CODE", ttLiteappPlatform.getOemCode())
					.eq("SOFTWARE_CODE", ttLiteappPlatform.getSoftwareCode())
					.eq("HARDWARE_CODE", ttLiteappPlatform.getHardwareCode())
					.eq("HARDWARE_VERSION", tcLiteappConfig.getHardwareVersion())
					.eq("SOFTWARE_VERSION", tcLiteappConfig.getSoftwareVersion())
					.eq("LITEAPP_GUID", tcLiteappConfig.getGuid());
			List<TtStoreLiteapp> exists = ttStoreLiteappMapper.selectList(wrapperStore);
			/* 更新应用商店里的APP版本 */
			if (exists == null || exists.size() < 1) {
				// 首次发行
				TtStoreLiteapp ttStoreLiteapp = new TtStoreLiteapp();
				ttStoreLiteapp.setStoreLiteappId(UUIDUtils.getUUID());
				ttStoreLiteapp.setLiteappName(tcLiteappConfig.getLiteappName());
				ttStoreLiteapp.setLiteappGuidId(tcLiteappConfig.getGuid());
				ttStoreLiteapp.setOemCode(ttLiteappPlatform.getOemCode());
				ttStoreLiteapp.setSoftwareCode(ttLiteappPlatform.getSoftwareCode());
				ttStoreLiteapp.setHardwareCode(ttLiteappPlatform.getHardwareCode());
				ttStoreLiteapp.setLiteappReleaseId(tcLiteappConfig.getLiteappReleaseId());
				ttStoreLiteapp.setLiteappVersion(tcLiteappConfig.getVersion());
				ttStoreLiteapp.setSoftwareVersion(tcLiteappConfig.getSoftwareVersion());
				ttStoreLiteapp.setHardwareVersion(tcLiteappConfig.getHardwareVersion());
				ttStoreLiteapp.setCreateBy("");
				ttStoreLiteapp.setCreateDate(new Date());
				ttStoreLiteappMapper.insert(ttStoreLiteapp);
				// 首次版没有差分包，必须是全包下载
			} else {
				if (exists.size() > 1) {
					throw new RuntimeException("同一应用存在两个不同版本");
				}
				TtStoreLiteapp previousVersion = CollectionUtils.getFirst(exists);
				String prevVerNum = previousVersion.getLiteappVersion();
				if (VersionUtils.compareVersion(prevVerNum, tcLiteappConfig.getVersion()) >= 0) {
					throw new RuntimeException("当前上架应用版本高于待发布版本");
				}
				previousVersion.setLastUpdateDate(new Date());
				previousVersion.setLiteappVersion(tcLiteappConfig.getVersion());
				previousVersion.setLiteappReleaseId(dto.getLiteappReleaseId());
				previousVersion.setLiteappName(tlLiteappRelease.getLiteappName());// 名称可能变化
				ttStoreLiteappMapper.updateById(previousVersion);
				// 差分包计算
				QueryWrapper<LiteappPackageUrl> wrapperPackageUrl = new QueryWrapper<LiteappPackageUrl>();
				wrapperPackageUrl.eq("OEM_CODE", ttLiteappPlatform.getOemCode())
						.eq("SOFTWARE_CODE", ttLiteappPlatform.getSoftwareCode())
						.eq("HARDWARE_CODE", ttLiteappPlatform.getHardwareCode())
						.eq("HARDWARE_VERSION", tcLiteappConfig.getHardwareVersion())
						.eq("SOFTWARE_VERSION", tcLiteappConfig.getSoftwareVersion())
						.eq("LITEAPP_GUID", tcLiteappConfig.getGuid()).eq("TO_VERSION", prevVerNum);
				LiteappPackageUrl lastVerPackageUrl = CollectionUtils
						.getFirst(liteappPackageUrlMapper.selectList(wrapperPackageUrl));

				if (lastVerPackageUrl != null && StringUtils.isNotEmpty(lastVerPackageUrl.getOriginalPackageUrl())) {
					packageUrl.setFromVersion(prevVerNum);
					diffPackageMapping.put(packageUrl.getLiteappPackageUrlId(),
							lastVerPackageUrl.getOriginalPackageUrl());
				}
			}
			if (CommonConstants.FLAG_Y.equals(ttLiteappPlatform.getIsSubscribe())) {
				TtBuildinLiteapp ttBuildinLiteapp = new TtBuildinLiteapp();
				ttBuildinLiteapp.setBuilditLiteappId(UUIDUtils.getUUID());
				ttBuildinLiteapp.setOemCode(ttLiteappPlatform.getOemCode());
				ttBuildinLiteapp.setSoftwareCode(ttLiteappPlatform.getSoftwareCode());
				ttBuildinLiteapp.setHardwareCode(ttLiteappPlatform.getHardwareCode());
				ttBuildinLiteapp.setSoftwareVersion(tcLiteappConfig.getSoftwareVersion());
				ttBuildinLiteapp.setHardwareVersion(tcLiteappConfig.getHardwareVersion());
				ttBuildinLiteapp.setLiteappGuidId(tcLiteappConfig.getGuid());
				ttBuildinLiteapp.setBuildinName(tcLiteappConfig.getLiteappName());
				ttBuildinLiteappMapper.insert(ttBuildinLiteapp);
			}
		}

		String unsignedPackageUrl = tlLiteappRelease.getPackageUrl();
		FileEntity unsignedFile = csapiService.getFile(unsignedPackageUrl);
		if (unsignedFile == null || unsignedFile.getContent() == null) {
			throw new RuntimeException("无法找到应用包文件");
		}
		byte[] signedFile = signatureManager.signFile(ArrayUtils.clone(unsignedFile.getContent()));
		Material cmsMaterial = cmsService.uploadMaterial(
				tlLiteappRelease.getLiteappName() + "_" + tcLiteappConfig.getVersion() + "_signed.zip", signedFile);

		// 更改为上架状态
		tlLiteappRelease.setStatus(CommonConstants.LITEAPP_RELEASE_STATUS_UPPER_SHELF);
		// 全包(签名后)路径
		tlLiteappRelease.setPackageUrl(cmsMaterial.getUrl());
		tlLiteappReleaseMapper.updateById(tlLiteappRelease);

		Map<String, String> cachedDiffPackage = new HashMap<String, String>();
		for (LiteappPackageUrl url : packageUrls) {
			url.setFullPackageUrl(cmsMaterial.getUrl());
			if (diffPackageMapping.containsKey(url.getLiteappPackageUrlId())) {
				// 计算差分包
				if (cachedDiffPackage.containsKey(url.getFromVersion())) {
					url.setDiffPackageUrl(cachedDiffPackage.get(url.getFromVersion()));
				} else {
					String preVerOriginalPackageUrl = diffPackageMapping.get(url.getLiteappPackageUrlId());
					// 上一个版本未签名原始文件
					FileEntity preVerOriginalPackageFile = csapiService.getFile(preVerOriginalPackageUrl);
					byte[] diffPackage = liteappReleaseManager.createDiffPackageZipFile(
							preVerOriginalPackageFile.getContent(), ArrayUtils.clone(unsignedFile.getContent()));
					Material diffMaterial = cmsService.uploadMaterial(
							tlLiteappRelease.getLiteappName() + "_" + url.getFromVersion() + "_"
									+ tcLiteappConfig.getVersion() + "_diff_signed.zip",
							signatureManager.signFile(diffPackage));
					url.setDiffPackageUrl(diffMaterial.getUrl());
					cachedDiffPackage.put(url.getFromVersion(), diffMaterial.getUrl());
				}
			}
			liteappPackageUrlMapper.insert(url);
		}
		diffPackageMapping.clear();
		cachedDiffPackage.clear();
		return "Success";
	}

}
