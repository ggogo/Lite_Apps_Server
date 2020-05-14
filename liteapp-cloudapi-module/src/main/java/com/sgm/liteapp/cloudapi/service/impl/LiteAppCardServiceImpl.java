package com.sgm.liteapp.cloudapi.service.impl;

import com.sgm.liteapp.cloudapi.dao.entity.LiteAppUserCardEntity;
import com.sgm.liteapp.cloudapi.dao.mapper.BuildinLiteappMapper;
import com.sgm.liteapp.cloudapi.dao.mapper.LiteAppUserCardMapper;
import com.sgm.liteapp.cloudapi.dao.mapper.LiteappSubscribeMapper;
import com.sgm.liteapp.cloudapi.dto.LiteAppBaseInfoDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppCardDisplayDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppBaseDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppCardQueryBaseDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppCardQueryDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppUpdateListDTO;
import com.sgm.liteapp.cloudapi.dto.LiteappQueryDTO;
import com.sgm.liteapp.cloudapi.service.LiteAppCardService;
import com.sgm.liteapp.commons.utils.CollectionUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author stephenz
 */
@Service
public class LiteAppCardServiceImpl implements LiteAppCardService {

    @Autowired
    private LiteAppUserCardMapper cardMapper;

    @Autowired
    private BuildinLiteappMapper buildinMapper;

    @Autowired
    private LiteappSubscribeMapper liteappSubscribeMapper;

    @Override
    public List<LiteAppCardDisplayDTO> queryCardList(LiteAppBaseDTO dto) {
        LiteappQueryDTO query = new LiteappQueryDTO();
        BeanUtils.copyProperties(dto, query);
        List<LiteAppCardDisplayDTO> result = cardMapper.queryUserCardsIncludingBuildInByAppInfo(dto);

//        List<LiteAppUserCardEntity> userCardEntityList = cardMapper.queryUserCardByIdpUserId(dto.getIdpUserId());
//        if (CollectionUtils.isNotEmpty(result) && CollectionUtils.isNotEmpty(userCardEntityList)) {
//            Map<String, Integer> sortNumberMap = new HashMap<>();
//            Map<String, Integer> displayMap = new HashMap<>();
//            for (LiteAppUserCardEntity entity : userCardEntityList) {
//                String cardKey = entity.getCardGuid() + entity.getCardVersion();
//                sortNumberMap.put(cardKey, entity.getSortNumber());
//                displayMap.put(cardKey, entity.getDisplay());
//            }
//            Set<String> cardUnqIDSet = new HashSet<>();
//            List<LiteAppCardDisplayDTO> duplicatedCardToRemove = new ArrayList<>();
//            for (LiteAppCardDisplayDTO dto1 : result) {
//                String cardKey = dto1.getCardGuid() + dto1.getCardVersion();
//                if (cardUnqIDSet.contains(cardKey)) {
//                    duplicatedCardToRemove.add(dto1);
//                } else {
//                    cardUnqIDSet.add(cardKey);
//                }
//                if (sortNumberMap.containsKey(cardKey)) {
//                    dto1.setSortNo(sortNumberMap.get(cardKey));
//                }
//                if (displayMap.containsKey(cardKey)) {
//                    dto1.setDisplay(displayMap.get(cardKey));
//                }
//            }
//        }

        return result;
    }

    @Override
    public LiteAppBaseInfoDTO queryCardAssociatedApp(LiteAppCardQueryDTO dto) {
        List<LiteAppBaseInfoDTO> result = cardMapper.queryCardAssociatedApp(dto);
        if (CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public String sortCardDisplay(LiteAppCardQueryDTO dto) {
        if (dto != null && dto.getDisplay() != null && dto.getDisplay().length > 0) {
            for (LiteAppCardQueryBaseDTO dto1 : dto.getDisplay()) {
                List<LiteAppUserCardEntity> userCardEntityList = cardMapper.queryUserCardByIdpUserIdAndCardInfo(dto.getIdpUserId(), dto1.getCardGuid(), dto1.getCardVersion());
                if (CollectionUtils.isNotEmpty(userCardEntityList)) {
                    cardMapper.updateSortNumberOfUserCardByIdpUserIdAndCardInfo(dto.getIdpUserId(), dto1.getCardGuid(), dto1.getCardVersion(), dto1.getSortNo());
                } else {
                    cardMapper.insertSortNumberOfUserCardByIdpUserIdAndCardInfo(UUID.randomUUID().toString(), dto.getIdpUserId(), dto1.getCardGuid(), dto1.getCardVersion(), dto1.getSortNo());
                }
            }
        }
        return null;
    }

    @Override
    public String setCardDisplay(LiteAppCardQueryDTO dto) {
        if (dto != null && dto.getDisplay() != null && dto.getDisplay().length > 0) {
            for (LiteAppCardQueryBaseDTO dto1 : dto.getDisplay()) {
                List<LiteAppUserCardEntity> userCardEntityList = cardMapper.queryUserCardByIdpUserIdAndCardInfo(dto.getIdpUserId(), dto1.getCardGuid(), dto1.getCardVersion());
                int displayValue = 1;
                if (CLOSE_STR.equalsIgnoreCase(dto1.getSwitchStr())) {
                    displayValue = 0;
                }
                if (CollectionUtils.isNotEmpty(userCardEntityList)) {
                    cardMapper.updateDisplayOfUserCardByIdpUserIdAndCardInfo(dto.getIdpUserId(), dto1.getCardGuid(), dto1.getCardVersion(), displayValue);
                } else {
                    cardMapper.insertDisplayOfUserCardByIdpUserIdAndCardInfo(UUID.randomUUID().toString(), dto.getIdpUserId(), dto1.getCardGuid(), dto1.getCardVersion(), displayValue);
                }
            }
        }
        return null;
    }

    private static final String CLOSE_STR = "CLOSE";
}
