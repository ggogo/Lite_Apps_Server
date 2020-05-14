package com.sgm.liteapp.cloudapi.service;

import com.sgm.liteapp.cloudapi.dto.LiteappPackageUrlDTO;
import com.sgm.liteapp.cloudapi.dto.LiteappVersionQueryDTO;

public interface LiteappPackageDownloadService {

	public LiteappPackageUrlDTO getLiteappPackageURLs(LiteappVersionQueryDTO query);
}
