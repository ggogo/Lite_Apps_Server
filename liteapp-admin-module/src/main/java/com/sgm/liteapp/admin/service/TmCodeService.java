package com.sgm.liteapp.admin.service;

import com.sgm.liteapp.admin.model.dto.TmCodeDto;
import com.sgm.liteapp.admin.model.entity.TmCode;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface TmCodeService extends IService<TmCode> {

	public Page<TmCode> masterCodeInfoQuery(TmCodeDto dto)  throws Exception;
	
	public String masterCodeInfoInsert(TmCodeDto dto);
}
