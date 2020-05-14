package com.sgm.liteapp.cloudapi.service.impl;

import com.sgm.liteapp.cloudapi.dao.entity.LiteappSubscribeEntity;
import com.sgm.liteapp.cloudapi.dao.entity.LiteappSubscribeLogEntity;
import com.sgm.liteapp.cloudapi.dao.mapper.LiteAppUserCardMapper;
import com.sgm.liteapp.cloudapi.dao.mapper.LiteappSubscribeMapper;
import com.sgm.liteapp.cloudapi.dto.LiteAppSubscribeDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppUnsubscribeDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppUpdateListDTO;
import com.sgm.liteapp.cloudapi.dto.LiteappQueryDTO;
import com.sgm.liteapp.cloudapi.dto.StoreLiteappConfigDTO;
import com.sgm.liteapp.cloudapi.manager.LiteappConfigLoaderManager;
import com.sgm.liteapp.cloudapi.service.LiteAppSubscribeService;
import com.sgm.liteapp.cloudapi.utils.DateUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LiteAppSubscribeServiceImpl implements LiteAppSubscribeService {

    @Autowired
    private LiteappSubscribeMapper subscribeMapper;

    @Autowired
    private LiteappConfigLoaderManager configLoaderManager;

    @Autowired
    private LiteAppUserCardMapper liteAppUserCardMapper;


    @Override
    public String subscribeApp(LiteAppSubscribeDTO dto) {
        LiteappSubscribeEntity entity = new LiteappSubscribeEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.setIsValid(1);
        entity.setLiteappSubscribeId(UUID.randomUUID().toString());
        entity.setLiteappGuid(dto.getAppGuid());
        entity.setCreateDate(DateUtils.getCurrentDate());
//        entity.setCreateBy("");
//        entity.setLastUpdateBy("");
//        entity.setLastUpdateDate(DateUtils.getCurrentDate());

        subscribeMapper.subscribeApp(entity);

        LiteappSubscribeLogEntity logEntity = new LiteappSubscribeLogEntity();
        BeanUtils.copyProperties(entity, logEntity);
        logEntity.setActionType("subscribe");
        logEntity.setActionDate(DateUtils.getCurrentDate());
        logEntity.setLiteAppSubscribeLogId(UUID.randomUUID().toString());
        logEntity.setPartitionFlag(DateUtils.getPartitionFlagBasedOnCurrentDate());
        logEntity.setLiteAppVersion(dto.getAppVersion());
        subscribeMapper.subscribeAppLog(logEntity);


        return null;
    }


    @Override
    public String unsubscribeApp(LiteAppUnsubscribeDTO dto) {
        if (dto != null && dto.getAppList() != null && dto.getAppList().length > 0) {
            LiteappSubscribeEntity entity = new LiteappSubscribeEntity();
            BeanUtils.copyProperties(dto, entity);
            for (StoreLiteappConfigDTO app : dto.getAppList()) {
                entity.setLiteappGuid(app.getGuid());
                subscribeMapper.unsubscribeApp(entity);

                LiteappSubscribeLogEntity logEntity = new LiteappSubscribeLogEntity();
                BeanUtils.copyProperties(entity, logEntity);
                logEntity.setActionType("unsubscribe");
                logEntity.setActionDate(DateUtils.getCurrentDate());
                logEntity.setLiteAppSubscribeLogId(UUID.randomUUID().toString());
                logEntity.setPartitionFlag(DateUtils.getPartitionFlagBasedOnCurrentDate());
                logEntity.setLiteAppVersion(app.getVersion());
                subscribeMapper.subscribeAppLog(logEntity);

            }

        }

        return null;
    }

    @Override
    public List<LiteAppUpdateListDTO> updateList(LiteappQueryDTO query) {
        List<LiteAppUpdateListDTO> result = subscribeMapper.getUserSubscribedAndBuildInLatestAppList(query);
        return result;
    }
}
