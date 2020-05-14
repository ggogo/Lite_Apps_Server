package com.sgm.liteapp.cloudapi.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sgm.liteapp.cloudapi.constants.LiteappViewType;
import com.sgm.liteapp.cloudapi.dao.mapper.LiteappConfigMapper;
import com.sgm.liteapp.cloudapi.dto.LiteappConfigAndViewDTO;
import com.sgm.liteapp.cloudapi.dto.StoreLiteappConfigDTO;
import com.sgm.liteapp.cloudapi.dto.StoreLiteappConfigDTO.Icon;
import com.sgm.liteapp.cloudapi.dto.StoreLiteappConfigDTO.Infoflowview;
import com.sgm.liteapp.cloudapi.dto.StoreLiteappConfigDTO.Page;
import com.sgm.liteapp.cloudapi.dto.StoreLiteappConfigDTO.Platforminfo;
import com.sgm.liteapp.cloudapi.dto.StoreLiteappConfigDTO.View;
import com.sgm.liteapp.cloudapi.manager.LiteappConfigLoaderManager;
import com.sgm.liteapp.commons.utils.CacheUtils;
import com.sgm.liteapp.commons.utils.CollectionUtils;

@Component
public class LiteappConfigLoaderManagerImpl implements LiteappConfigLoaderManager {

	private static final String CONFIG_CACHE_KEY_PREFIX = "APPCFG_REL_";

	@Autowired
	private LiteappConfigMapper configMapper;

	@Override
	public StoreLiteappConfigDTO getLiteappConfigByReleaseId(String releaseId) {		
		StoreLiteappConfigDTO cachedConfig = (StoreLiteappConfigDTO) CacheUtils
				.get(CONFIG_CACHE_KEY_PREFIX + releaseId);
		if (cachedConfig == null) {
			// 从数据库里取
			List<LiteappConfigAndViewDTO> configAndView = configMapper
					.getLiteappConfigAndViewJoinListByReleaseId(releaseId);
			cachedConfig = this.loadConfigDataFromDatabase(configAndView);
			if (cachedConfig != null) {
				// 之后加载到缓存
				CacheUtils.set(CONFIG_CACHE_KEY_PREFIX + releaseId, cachedConfig);
			}
		}
		return cachedConfig;
	}

	@Override
	public void removeCache(String key) {
		CacheUtils.remove(CONFIG_CACHE_KEY_PREFIX + key);
	}

	/**
	 * 根据数据库里的数据装载APP配置信息
	 * 
	 * @param configAndView
	 * @return
	 */
	private StoreLiteappConfigDTO loadConfigDataFromDatabase(List<LiteappConfigAndViewDTO> configAndView) {
		if (CollectionUtils.isNotEmpty(configAndView)) {
			StoreLiteappConfigDTO dto = new StoreLiteappConfigDTO();
			dto.setViews(new ArrayList<View>());
			dto.setInfoflowviews(new ArrayList<Infoflowview>());
			dto.setPages(new ArrayList<Page>());
			for (int i = 0; i < configAndView.size(); i++) {
				LiteappConfigAndViewDTO data = configAndView.get(i);
				if (i == 0) {
					dto.setName(data.getLiteappName());
					dto.setFriendlyname(data.getFriendlyName());
					Icon appicon = new Icon();
					appicon.setBig(data.getBigIcon());
					appicon.setSmall(data.getSmallIcon());
					dto.setAppicon(appicon);
					dto.setInfoicon(data.getInfoIcon());
					dto.setIntor(data.getIntor());
					dto.setVersion(data.getVersion());
					Platforminfo platforminfo = new Platforminfo();
					platforminfo.setHardwareversion(data.getHardwareVersion());
					platforminfo.setSoftwareversion(data.getSoftwareVersion());
					dto.setPlatforminfo(platforminfo);
					dto.setApplevel(data.getAppLevel());
					dto.setApilevel(data.getApiLevel());
					dto.setApptype(data.getAppType());
					dto.setGuid(data.getGuid());
					dto.setShortcut(data.getShortcut());
					dto.setPackageUrl(data.getPackageUrl());
				}
				// 小卡片或大卡片
				if (LiteappViewType.WIDGET_VIEW.equals(data.getViewType())
						|| LiteappViewType.DETAILINFO_VIEW.equals(data.getViewType())) {
					View view = new View();
					view.setGuid(data.getViewGuid());
					view.setName(data.getViewName());
					view.setFriendlyname(data.getViewFriendlyName());
					view.setVersion(data.getViewVersion());
					view.setType(data.getViewType());
					view.setWidth(data.getWidth());
					view.setHeight(data.getHeight());
					Icon icon = new Icon();
					icon.setSmall(data.getViewSmallIcon());
					icon.setBig(data.getViewBigIcon());
					view.setIcon(icon);
					view.setThumbnail(data.getThumbnail());
					view.setPath(data.getPath());
					view.setDisplay(data.getDisplay());
					dto.getViews().add(view);
				} else if (LiteappViewType.INFOFLOW_VIEW.equals(data.getViewType())) {// 信息流卡片
					Infoflowview infoflowview = new Infoflowview();
					infoflowview.setGuid(data.getViewGuid());
					infoflowview.setName(data.getViewName());
					infoflowview.setFriendlyname(data.getViewFriendlyName());
					infoflowview.setVersion(data.getViewVersion());
					infoflowview.setShowtype(data.getShowType());
					infoflowview.setPath(data.getPath());
					infoflowview.setClosestrategy(data.getCloseStrategy());
					dto.getInfoflowviews().add(infoflowview);
				} else if (LiteappViewType.PAGE.equals(data.getViewType())) {// Page
					Page page = new Page();
					page.setGuid(data.getViewGuid());
					page.setName(data.getViewName());
					page.setFriendlyname(data.getViewFriendlyName());
					page.setVersion(data.getViewVersion());
					page.setPath(data.getPath());
					dto.getPages().add(page);
				}

			}
			return dto;
		}
		return null;
	}

}
