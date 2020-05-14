package com.sgm.liteapp.cloudapi.service;

import java.util.List;

import com.sgm.liteapp.cloudapi.dto.StoreLiteappDTO;
import com.sgm.liteapp.cloudapi.dto.LiteappQueryDTO;
import com.sgm.liteapp.cloudapi.dto.StoreLiteappConfigDTO;

public interface StoreLiteappService {

	public List<StoreLiteappDTO> getStoreLiteappList(LiteappQueryDTO query);

	public StoreLiteappConfigDTO getLiteappConfiguration(String guid, String version);

	public List<StoreLiteappDTO> getMySubscribedAppList(LiteappQueryDTO query);

}
