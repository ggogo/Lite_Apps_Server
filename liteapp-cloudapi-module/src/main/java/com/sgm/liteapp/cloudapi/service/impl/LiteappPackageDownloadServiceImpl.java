package com.sgm.liteapp.cloudapi.service.impl;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgm.liteapp.cloudapi.dao.entity.LiteappPackageUrlEntity;
import com.sgm.liteapp.cloudapi.dao.entity.StoreLiteappEntity;
import com.sgm.liteapp.cloudapi.dao.mapper.LiteappPackageUrlMapper;
import com.sgm.liteapp.cloudapi.dao.mapper.StoreLiteappMapper;
import com.sgm.liteapp.cloudapi.dto.LiteappPackageUrlDTO;
import com.sgm.liteapp.cloudapi.dto.LiteappPackageUrlDTO.UpdateChain;
import com.sgm.liteapp.cloudapi.dto.LiteappVersionQueryDTO;
import com.sgm.liteapp.cloudapi.service.LiteappPackageDownloadService;
import com.sgm.liteapp.commons.utils.CollectionUtils;
import com.sgm.liteapp.commons.web.WebAccessException;

@Service
public class LiteappPackageDownloadServiceImpl implements LiteappPackageDownloadService {

	@Autowired
	private StoreLiteappMapper storeLiteappMapper;

	@Autowired
	private LiteappPackageUrlMapper liteappPackageUrlMapper;

	@Override
	public LiteappPackageUrlDTO getLiteappPackageURLs(LiteappVersionQueryDTO query) {
		// 最新版本
		StoreLiteappEntity latestRelease = CollectionUtils.getFirst(storeLiteappMapper.getLiteappLatestRelease(query));
		if (latestRelease == null) {
			throw new WebAccessException("无法找到APP最新版本，请确认查询参数是否准确");
		}
		String installedVersion = query.getAppVersion();// 当前车机安装APP的版本
		query.setAppVersion(latestRelease.getLiteappVersion());
		// 当前最新版本安装包
		LiteappPackageUrlEntity latestPackageUrl = CollectionUtils
				.getFirst(liteappPackageUrlMapper.getLiteappPackageUrls(query));
		if (latestPackageUrl == null) {
			throw new WebAccessException("无法找到APP最新版本对应的安装包，请确认查询参数是否准确");
		}
		LiteappPackageUrlDTO dto = new LiteappPackageUrlDTO();
		dto.setCurrentVersion(latestRelease.getLiteappVersion());// 当前最新版本
		dto.setFullPackageUrl(latestPackageUrl.getFullPackageUrl());// 当前最新版本全包路径
		if (StringUtils.isEmpty(latestPackageUrl.getFromVersion())) {
			// 没有差分包，一般是首发版
			return dto;
		} else {
			// 到上一个版本的差分版本
			dto.setUpdateChain(new ArrayList<UpdateChain>());
			UpdateChain diff = new UpdateChain();
			diff.setFromVersion(latestPackageUrl.getFromVersion());
			diff.setToVersion(latestPackageUrl.getToVersion());
			diff.setDiffPackageUrl(latestPackageUrl.getDiffPackageUrl());
			dto.getUpdateChain().add(diff);
			if (!StringUtils.equals(latestPackageUrl.getFromVersion(), installedVersion)) {
				// 存在一个以上的差分版本
				query.setAppVersion(latestPackageUrl.getFromVersion());
				latestPackageUrl = CollectionUtils.getFirst(liteappPackageUrlMapper.getLiteappPackageUrls(query));
				if (latestPackageUrl == null
						|| !StringUtils.equals(latestPackageUrl.getFromVersion(), installedVersion)) {
					// 再上一个差分版本不是车机当前安装的版本，意味着车机当前安装的版本与最新版本跨度超过2个版本，这样直接下载全包，并将差分包清除
					dto.setUpdateChain(null);
				} else {
					diff = new UpdateChain();
					diff.setFromVersion(latestPackageUrl.getFromVersion());
					diff.setToVersion(latestPackageUrl.getToVersion());
					diff.setDiffPackageUrl(latestPackageUrl.getDiffPackageUrl());
					dto.getUpdateChain().add(0, diff);
				}
			}
		}
		return dto;
	}

}
