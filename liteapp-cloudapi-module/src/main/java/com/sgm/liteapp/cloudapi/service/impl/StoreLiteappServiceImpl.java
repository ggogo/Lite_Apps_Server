package com.sgm.liteapp.cloudapi.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgm.liteapp.cloudapi.dao.entity.BuildinLiteappEntity;
import com.sgm.liteapp.cloudapi.dao.entity.LiteappConfigEntity;
import com.sgm.liteapp.cloudapi.dao.entity.LiteappSubscribeEntity;
import com.sgm.liteapp.cloudapi.dao.entity.StoreLiteappEntity;
import com.sgm.liteapp.cloudapi.dao.mapper.BuildinLiteappMapper;
import com.sgm.liteapp.cloudapi.dao.mapper.LiteappSubscribeMapper;
import com.sgm.liteapp.cloudapi.dao.mapper.StoreLiteappMapper;
import com.sgm.liteapp.cloudapi.dto.StoreLiteappDTO;
import com.sgm.liteapp.cloudapi.manager.LiteappConfigLoaderManager;
import com.sgm.liteapp.cloudapi.dto.LiteappQueryDTO;
import com.sgm.liteapp.cloudapi.dto.StoreLiteappConfigDTO;
import com.sgm.liteapp.cloudapi.service.StoreLiteappService;
import com.sgm.liteapp.commons.utils.CollectionUtils;

@Service
public class StoreLiteappServiceImpl implements StoreLiteappService {

	@Autowired
	private StoreLiteappMapper mapper;

	@Autowired
	private BuildinLiteappMapper buildinMapper;

	@Autowired
	private LiteappSubscribeMapper subscribeMapper;

	@Autowired
	private LiteappConfigLoaderManager configLoaderManager;

	@Override
	public List<StoreLiteappDTO> getStoreLiteappList(LiteappQueryDTO query) {
		List<StoreLiteappDTO> list = new ArrayList<StoreLiteappDTO>();
		// 当前软硬件环境所有可用APP
		List<StoreLiteappEntity> entities = mapper.getStoreLiteappList(query);
		// 当前软硬件环境预装APP
		List<BuildinLiteappEntity> buildinList = buildinMapper.getBuildinLiteappList(query);
		Set<String> buildinGuidSet = new HashSet<String>();
		if (buildinList != null) {
			for (BuildinLiteappEntity buildin : buildinList) {
				buildinGuidSet.add(buildin.getLiteappGuid());
			}
		}
		// 当前用户已经订阅的
		List<LiteappSubscribeEntity> subscribeList = subscribeMapper.getUserSubscribedLiteappList(query);
		Set<String> subscribeGuidSet = new HashSet<String>();
		if (subscribeList != null) {
			for (LiteappSubscribeEntity subscribe : subscribeList) {
				subscribeGuidSet.add(subscribe.getLiteappGuid());
			}
		}
		if (entities != null) {
			for (StoreLiteappEntity entity : entities) {
				StoreLiteappDTO dto = new StoreLiteappDTO();
				BeanUtils.copyProperties(entity, dto);
				if (buildinGuidSet.contains(entity.getLiteappGuid())) {
					dto.setBuildin(1);// 是否预装
				}
				if (subscribeGuidSet.contains(entity.getLiteappGuid())) {
					dto.setIsAssociate(1);// 是否已经订阅
				}
				// 装载配置信息
				dto.setConfiguration(configLoaderManager.getLiteappConfigByReleaseId(entity.getLiteappReleaseId()));
				dto.setShowPackageURL(dto.getConfiguration().getPackageUrl());
				list.add(dto);
			}
		}
		return list;
	}

	@Override
	public StoreLiteappConfigDTO getLiteappConfiguration(String guid, String version) {
		// 先获取ReleaseID
		LiteappConfigEntity config = CollectionUtils.getFirst(mapper.getLiteappConfig(guid, version));
		if (config != null) {
			String releaseId = config.getLiteappReleaseId();
			return configLoaderManager.getLiteappConfigByReleaseId(releaseId);
		}
		return null;
	}

	@Override
	public List<StoreLiteappDTO> getMySubscribedAppList(LiteappQueryDTO query) {
		List<StoreLiteappDTO> all = this.getStoreLiteappList(query);
		List<StoreLiteappDTO> myList = new ArrayList<StoreLiteappDTO>();
		if (all != null) {
			for (StoreLiteappDTO app : all) {
				if (app.getIsAssociate() == 1) {
					myList.add(app);
				}
			}
		}
		return myList;
	}

}
