package com.sgm.liteapp.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgm.liteapp.admin.model.dto.LiteappMainDetailDto;
import com.sgm.liteapp.admin.model.dto.LiteappMainDto;
import com.sgm.liteapp.admin.model.entity.TlLiteappRelease;

public interface LiteappMainService {

	public Page<TlLiteappRelease> liteappMainQuery(LiteappMainDto dto);

	public LiteappMainDetailDto liteappMainDetail(LiteappMainDto dto);

	public String proccesLiteappMainEditData(LiteappMainDto dto);

	public String proccesLiteappMainPublish(LiteappMainDto dto) throws Exception;
}
