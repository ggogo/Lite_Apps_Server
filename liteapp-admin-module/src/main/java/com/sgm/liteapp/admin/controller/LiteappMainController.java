package com.sgm.liteapp.admin.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sgm.liteapp.admin.common.JsonResult;
import com.sgm.liteapp.admin.model.dto.LiteappMainDetailDto;
import com.sgm.liteapp.admin.model.dto.LiteappMainDto;
import com.sgm.liteapp.admin.model.entity.TlLiteappRelease;
import com.sgm.liteapp.admin.service.LiteappMainService;

@RestController
@RequestMapping("/liteappMain")
public class LiteappMainController {

	public static final Logger logger = LoggerFactory.getLogger(LiteappMainController.class);

	@Autowired
	private LiteappMainService liteappMainService;

	@PostMapping("/query")
	public JsonResult liteappMainQuery(@RequestBody LiteappMainDto dto) {
		logger.info("轻应用主页查询");
		Page<TlLiteappRelease> page = liteappMainService.liteappMainQuery(dto);
		return JsonResult.SUCCESS(page);
	}

	@PostMapping("/detail")
	public JsonResult liteappMainDetail(@RequestBody LiteappMainDto dto) {
		logger.info("轻应用详情查询");
		LiteappMainDetailDto list = liteappMainService.liteappMainDetail(dto);
		return JsonResult.SUCCESS(list);
	}

	@PostMapping("/update")
	public JsonResult liteappMainDetailUplate(@RequestBody LiteappMainDto dto) {
		logger.info("轻应用详情修改");
		String result = liteappMainService.proccesLiteappMainEditData(dto);
		return JsonResult.SUCCESS(result);
	}

	@PostMapping("/publish")
	public JsonResult liteappMainPublish(@RequestBody LiteappMainDto dto) {
		logger.info("轻应上架");
		try {
			if (StringUtils.isEmpty(dto.getLiteappReleaseId())) {
				throw new Exception("ID为空");
			}
			String result = liteappMainService.proccesLiteappMainPublish(dto);
			return JsonResult.SUCCESS(result);
		} catch (Exception e) {
			logger.error("轻应上架失败", e);
			return JsonResult.ERROR("轻应上架失败:" + e.getMessage());
		}
	}

}
