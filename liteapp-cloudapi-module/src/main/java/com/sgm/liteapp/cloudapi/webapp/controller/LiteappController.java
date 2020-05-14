package com.sgm.liteapp.cloudapi.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgm.liteapp.cloudapi.dto.LiteAppBaseInfoDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppSubscribeDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppUnsubscribeDTO;
import com.sgm.liteapp.cloudapi.dto.LiteAppUpdateListDTO;
import com.sgm.liteapp.cloudapi.dto.LiteappPackageUrlDTO;
import com.sgm.liteapp.cloudapi.dto.StoreLiteappDTO;
import com.sgm.liteapp.cloudapi.dto.LiteappQueryDTO;
import com.sgm.liteapp.cloudapi.dto.LiteappVersionQueryDTO;
import com.sgm.liteapp.cloudapi.dto.StoreLiteappConfigDTO;
import com.sgm.liteapp.cloudapi.service.LiteAppSubscribeService;
import com.sgm.liteapp.cloudapi.service.LiteappPackageDownloadService;
import com.sgm.liteapp.cloudapi.service.StoreLiteappService;
import com.sgm.liteapp.commons.web.ResponseBody;
import com.sgm.liteapp.commons.web.SessionContext;
import com.sgm.liteapp.commons.web.WebAccessException;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/app")
public class LiteappController {

	@Autowired
	private StoreLiteappService service;

	@Autowired
	private LiteAppSubscribeService subscribeService;

	@Autowired
	private LiteappPackageDownloadService packageDownloadService;

	@PostMapping("/list")
	@ApiOperation(value = "获取当前系统版本下全部可用应用列表", notes = "获取当前系统版本下全部可用应用列表")
	public ResponseBody<List<StoreLiteappDTO>> listAll(@RequestBody LiteappQueryDTO query) {
		query.setIdpUserId(SessionContext.getUser().getUid());
		return ResponseBody.data(service.getStoreLiteappList(query));
	}

	@PostMapping("/config")
	@ApiOperation(value = "查看APP配置", notes = "查看APP配置")
	public ResponseBody<StoreLiteappConfigDTO> getAppConfigInfo(@RequestBody LiteAppBaseInfoDTO query) {
		if (query == null || query.getAppGuid() == null || query.getAppVersion() == null) {
			throw new WebAccessException(400, "缺失必要查询条件");
		}
		return ResponseBody.data(service.getLiteappConfiguration(query.getAppGuid(), query.getAppVersion()));
	}

	@PostMapping("/personal")
	@ApiOperation(value = "获取个人订阅的应用列表", notes = "获取个人订阅的应用列表")
	public ResponseBody<List<StoreLiteappDTO>> getMySubscribedApp(@RequestBody LiteappQueryDTO query) {
		query.setIdpUserId(SessionContext.getUser().getUid());
		return ResponseBody.data(service.getMySubscribedAppList(query));
	}

	@PostMapping("/packageurl")
	@ApiOperation(value = "获取轻应用APP下载链接", notes = "获取轻应用APP下载链接")
	public ResponseBody<LiteappPackageUrlDTO> getLiteappPackageURLs(@RequestBody LiteappVersionQueryDTO query) {
		return ResponseBody.data(packageDownloadService.getLiteappPackageURLs(query));
	}

	@PostMapping("/subscribe")
	@ApiOperation(value = "车机端用户在订阅某个APP", notes = "车机端用户在订阅某个APP")
	public ResponseBody<String> subscribeApp(@RequestBody LiteAppSubscribeDTO dto) {
		dto.setIdpUserId(SessionContext.getUser().getUid());
		return ResponseBody.data(responseMessageSuccess, subscribeService.subscribeApp(dto));
	}

	@PostMapping("/unsubscribe")
	@ApiOperation(value = "当用户需要移除轻应用APP", notes = "当用户需要移除轻应用APP")
	public ResponseBody<String> unsubscribeApp(@RequestBody LiteAppUnsubscribeDTO dto) {
		dto.setIdpUserId(SessionContext.getUser().getUid());
		return ResponseBody.data(responseMessageSuccess, subscribeService.unsubscribeApp(dto));
	}

	@PostMapping("/update/list")
	@ApiOperation(value = "并返回需要升级的app标识", notes = "并返回需要升级的app标识")
	public ResponseBody<List<LiteAppUpdateListDTO>> updateList(@RequestBody LiteappQueryDTO dto) {
		dto.setIdpUserId(SessionContext.getUser().getUid());
		return ResponseBody.data(responseMessageSuccess, subscribeService.updateList(dto));
	}

	private static final String responseMessageSuccess = "success";

}
