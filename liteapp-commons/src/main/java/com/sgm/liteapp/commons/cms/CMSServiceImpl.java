package com.sgm.liteapp.commons.cms;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgm.liteapp.commons.utils.IOUtils;
import com.sgm.liteapp.commons.web.AdaptiveRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class CMSServiceImpl extends AdaptiveRestTemplate implements CMSService, InitializingBean {

	@Value("${com.sgm.cms.host}")
	private String apiAccessUrl;

	@Value("${com.sgm.cms.token}")
	private String apiAccessToken;

	@Value("${com.sgm.cms.sid}")
	private String spaceId;

	private ObjectMapper JSON = new ObjectMapper();

	@Override
	public Material uploadMaterial(String fileName, byte[] file) throws CMSServiceException {
		return uploadMaterial(fileName, file, false);
	}

	@Override
	public Material uploadMaterial(String fileName, byte[] file, boolean secured) throws CMSServiceException {
		/* 设置必要表单参数 */
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
		paramMap.add("uniqfile", true);// 重命名文件名
		paramMap.add("save", true);// 存入素材库
		paramMap.add("ACL", secured);// 文件为私有，需要授权

		ByteArrayResource contentsAsResource = new ByteArrayResource(file) {
			@Override
			public String getFilename() {
				return fileName;
			}
		};
		paramMap.add("file", contentsAsResource);
		/* 设置请求头 */
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.add("token", apiAccessToken);// 访问令牌
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(paramMap, headers);
		ResponseEntity<Material> responseEntity = this.postForEntity(
				apiAccessUrl + "/api/v2/spaces/" + spaceId + "/file/upload", requestEntity, Material.class);
		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			throw new CMSServiceException("服务器返回异常状态" + responseEntity.getStatusCode().value());
		}
		Material body = responseEntity.getBody();
		if (body == null) {
			throw new CMSServiceException("无法解析服务器端返回数据");
		}
		if (StringUtils.isNotEmpty(body.getErrcode())) {
			throw new CMSServiceException("服务器返回错误代码" + body.getErrcode() + "，错误原因：" + body.getErrmsg());
		}
		return body;
	}

	@Override
	public SecuredFileURLs getSecuredFileURLs(String... fileObjKey) throws CMSServiceException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("objKey", fileObjKey);
		param.put("options", Collections.singletonMap("expires", 60));
		String requestBody = null;
		try {
			requestBody = JSON.writeValueAsString(param);
		} catch (JsonProcessingException e) {
			throw new CMSServiceException(e.getMessage(), e);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> formEntity = new HttpEntity<String>(requestBody, headers);
		ResponseEntity<SecuredFileURLs> responseEntity = this.postForEntity(
				apiAccessUrl + "/api/v2/spaces/" + spaceId + "/materials/signatureurl", formEntity,
				SecuredFileURLs.class);
		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			throw new CMSServiceException("服务器返回异常状态" + responseEntity.getStatusCode().value());
		}
		SecuredFileURLs body = responseEntity.getBody();
		if (body == null) {
			throw new CMSServiceException("无法解析服务器端返回数据");
		}
		if (StringUtils.isNotEmpty(body.getErrcode())) {
			throw new CMSServiceException("服务器返回错误代码" + body.getErrcode() + "，错误原因：" + body.getErrmsg());
		}
		return body;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.initAdaption();
	}

	public static void main(String[] args) throws Exception {
		CMSServiceImpl serivce = new CMSServiceImpl();
		serivce.apiAccessUrl = "https://api-cmsqa.cw.sgmlink.com";
		serivce.apiAccessToken = "C1A4F2A9207C4A3691808420E2A752F7";
		serivce.spaceId = "5e9d7217f6dc8";
		serivce.afterPropertiesSet();
		Material returnval = serivce.uploadMaterial("weather_0.0.1.zip",
				IOUtils.readFile(new java.io.File("D:\\SGM\\轻应用平台\\weather_0.0.1.zip")));
		System.out.println(returnval.getObjKey());
	}

}
