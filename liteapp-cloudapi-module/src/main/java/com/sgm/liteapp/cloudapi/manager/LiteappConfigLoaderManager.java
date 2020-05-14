package com.sgm.liteapp.cloudapi.manager;

import com.sgm.liteapp.cloudapi.dto.StoreLiteappConfigDTO;

public interface LiteappConfigLoaderManager {

	public StoreLiteappConfigDTO getLiteappConfigByReleaseId(String releaseId);

	public void removeCache(String key);

}
