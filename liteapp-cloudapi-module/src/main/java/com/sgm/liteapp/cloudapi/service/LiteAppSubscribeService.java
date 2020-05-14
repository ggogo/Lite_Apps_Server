package com.sgm.liteapp.cloudapi.service;


import com.sgm.liteapp.cloudapi.dto.LiteAppBaseDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppSubscribeDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppUnsubscribeDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppUpdateListDTO;
import com.sgm.liteapp.cloudapi.dto.LiteappQueryDTO;

import java.util.List;

public interface LiteAppSubscribeService {

    public String subscribeApp(LiteAppSubscribeDTO query);
    public String unsubscribeApp(LiteAppUnsubscribeDTO query);
    public List<LiteAppUpdateListDTO> updateList(LiteappQueryDTO query);
}
