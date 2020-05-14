package com.sgm.liteapp.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgm.liteapp.admin.common.JsonResult;
import com.sgm.liteapp.admin.model.dto.TmCodeDto;
import com.sgm.liteapp.admin.model.entity.TmCode;
import com.sgm.liteapp.admin.service.TmCodeService;

@RestController
@RequestMapping("/master")
public class VehicleMasterInfo {

	private static final Logger logger=LoggerFactory.getLogger(VehicleMasterInfo.class);
	
	@Autowired
	private TmCodeService tmCodeService;
	
	@PostMapping(value = "/query")
	public JsonResult queryCodeInfo(@RequestBody TmCodeDto dto) throws Exception {
		logger.info("车机信息主数据查询");
		Page<TmCode> page = tmCodeService.masterCodeInfoQuery(dto);
		return JsonResult.RESULT(page);
	}
	
	@PostMapping("/insert")
	public JsonResult insertCodeInfo(@RequestBody TmCodeDto dto) {
		logger.info("车机信息主数据新增");
		String json=tmCodeService.masterCodeInfoInsert(dto);
		return JsonResult.RESULT(json);
	}
	
}
