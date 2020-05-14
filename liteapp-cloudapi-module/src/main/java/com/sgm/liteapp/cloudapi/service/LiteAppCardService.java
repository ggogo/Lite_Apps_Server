package com.sgm.liteapp.cloudapi.service;

import com.sgm.liteapp.cloudapi.dto.LiteAppBaseInfoDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppCardDisplayDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppBaseDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppCardQueryDTO;

import java.util.List;

public interface LiteAppCardService {

    public List<LiteAppCardDisplayDTO> queryCardList(LiteAppBaseDTO dto);
    public LiteAppBaseInfoDTO queryCardAssociatedApp(LiteAppCardQueryDTO dto);
    public String sortCardDisplay(LiteAppCardQueryDTO dto);
    public String setCardDisplay(LiteAppCardQueryDTO dto);


}
