package com.sgm.liteapp.commons.csapi;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.sgm.liteapp.commons.utils.IOUtils;
import com.sgm.liteapp.commons.web.AdaptiveRestTemplate;

public class CSAPIServiceImpl extends AdaptiveRestTemplate implements CSAPIService, InitializingBean {

	@Value("${com.sgm.csapi.appid}")
	private String sgmAppId;

	@Value("${com.sgm.csapi.server.endpoint}")
	private String sgmServerUrl;

	@Override
	public String uploadFile(String fileName, byte[] fileContent) throws CSAPIException {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("multipart/form-data");
		headers.setContentType(type);
		// 设置请求体
		ByteArrayResource fileSystemResource = new ByteArrayResource(fileContent) {
			@Override
			public String getFilename() {
				return fileName;
			}
		};
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
		form.add("file", fileSystemResource);
		form.add("appId", sgmAppId);

		// 上传文件
		HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
		try {
			ResponseEntity<String> uploadResponse = super.postForEntity(sgmServerUrl + "/file/backend/upload", files,
					String.class);
			if (uploadResponse.getStatusCodeValue() != 200) {
				throw new Exception(uploadResponse.getBody());
			}
			return uploadResponse.getBody();
		} catch (Exception e) {
			throw new CSAPIException("文件上传失败:" + e.getMessage());
		}
	}

	@Override
	public String getAccessToken() throws CSAPIException {
		try {
			ResponseEntity<String> tokenEntity = super.getForEntity(sgmServerUrl + "/file/token?appId=" + sgmAppId,
					String.class);
			if (tokenEntity.getStatusCodeValue() != 200) {
				throw new CSAPIException(tokenEntity.getBody());
			}
			return tokenEntity.getBody();
		} catch (Exception e) {
			throw new CSAPIException("获取文件服务器访问凭据失败:" + e.getMessage());
		}

	}

	@Override
	public FileEntity getFile(String fileId) throws CSAPIException {
		FileEntity file = new FileEntity();
		try {
			ResponseEntity<Resource> resp = super.getForEntity(sgmServerUrl + "/file/backend/{1}?appId=" + sgmAppId,
					Resource.class, fileId);
			if (resp.getStatusCodeValue() != 200) {
				throw new CSAPIException("HTTP Status " + resp.getStatusCodeValue());
			}
			byte[] fileContent = IOUtils.read(resp.getBody().getInputStream());
			file.setContent(fileContent);
			return file;
		} catch (Exception e) {
			throw new CSAPIException("获取文件失败:" + e.getMessage());
		}
	}

	@Override
	public void deleteFile(String fileId) throws CSAPIException {
		try {
			ResponseEntity<String> resp = super.exchange(sgmServerUrl + "/file/backend/{1}?appId=" + sgmAppId,
					HttpMethod.DELETE, null, String.class, fileId);
			if (resp.getStatusCodeValue() != 200) {
				throw new CSAPIException("HTTP Status " + resp.getStatusCodeValue());
			}
		} catch (Exception e) {
			throw new CSAPIException("删除文件失败:" + e.getMessage());
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.initAdaption();
	}

}
