package com.sgm.liteapp.servicegateway.authority.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConfigurationProperties(prefix = "b2cidm.service")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class B2CIDMFederationServiceImpl implements B2CIDMFederationService {

	protected final static Logger _log = LoggerFactory.getLogger(B2CIDMFederationService.class);

	private String url;

	private String clientId;

	private String clientSecret;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Credentials exchangeTokenByTicket(String idpUserID, String ticket, String deviceId)
			throws AuthenticationException {
		Map params = new HashMap();
		// 请求参数
		params.put("idpUserID", idpUserID);
		params.put("ticket", ticket);
		params.put("device_id", deviceId);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			// 接口认证头
			headers.add("client_id", clientId);
			headers.add("client_secret", clientSecret);
			HttpEntity<Map> request = new HttpEntity<Map>(params, headers);
			ResponseEntity<B2CIDMResponse> responseEntity = restTemplate.postForEntity(url + "/exchangeTokenByTicket",
					request, B2CIDMResponse.class);
			B2CIDMResponse responseData = responseEntity.getBody();
			if (responseData != null && responseData.getStatusCode() != 200) {
				// 服务端主动返回的错误信息
				throw new Exception("服务端返错误信息(" + responseData.getMessage() + ")");
			}
			if (responseEntity.getStatusCodeValue() != 200) {
				// 访问链路异常
				throw new Exception("服务端返回状态码(" + responseEntity.getStatusCodeValue() + ")");
			}
			if (responseData == null || responseData.getData() == null) {
				throw new Exception("无法解析服务端返回数据");
			}
			Map tokenInfo = (Map) responseData.getData();
			Credentials cred = new Credentials();
			cred.setAccessToken((String) tokenInfo.get("access_token"));
			cred.setRefreshToken((String) tokenInfo.get("perm_token"));
			return cred;
		} catch (Exception e) {
			_log.error("交换访问令牌失败", e);
			throw new AuthenticationException("交换访问令牌失败:" + e.getMessage(), e);
		}
	}

	@Override
	public void logout(String idpUserID, String accessToken) {
		Map params = new HashMap();
		// 请求参数
		params.put("idpUserID", idpUserID);
		params.put("timestamp", System.currentTimeMillis());
		// 64位的无符号的随机数，对于每个请求独特的生成
		params.put("nonce", RandomStringUtils.random(64, false, true));
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			// 接口认证头
			headers.add("client_id", clientId);
			headers.add("access_token", accessToken);
			HttpEntity<Map> request = new HttpEntity<Map>(params, headers);
			ResponseEntity<B2CIDMResponse> responseEntity = restTemplate.postForEntity(url + "/oauth2/token/logout",
					request, B2CIDMResponse.class);
			B2CIDMResponse responseData = responseEntity.getBody();
			if (responseData != null && responseData.getStatusCode() != 200) {
				// 服务端主动返回的错误信息
				throw new Exception("服务端返错误信息(" + responseData.getMessage() + ")");
			}
			if (responseEntity.getStatusCodeValue() != 200) {
				// 访问链路异常
				throw new Exception("服务端返回状态码(" + responseEntity.getStatusCodeValue() + ")");
			}
			if (responseData == null) {
				throw new Exception("无法解析服务端返回数据");
			}
		} catch (Exception e) {
			_log.error("注销令牌失败", e);
		}

	}

	@Override
	public Credentials refresh(String idpUserID, String refreshToken, String deviceId) throws AuthenticationException {
		Map params = new HashMap();
		// 请求参数
		params.put("username", idpUserID);
		params.put("perm_token", refreshToken);
		params.put("device_id", deviceId);
		params.put("INVOKE_API", "SGMOWNER");
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			// 接口认证头
			headers.add("client_id", clientId);
			headers.add("client_secret", clientSecret);
			HttpEntity<Map> request = new HttpEntity<Map>(params, headers);
			ResponseEntity<B2CIDMResponse> responseEntity = restTemplate.postForEntity(url + "/oauth2/token/refresh",
					request, B2CIDMResponse.class);
			B2CIDMResponse responseData = responseEntity.getBody();
			if (responseData != null && responseData.getStatusCode() != 200) {
				// 服务端主动返回的错误信息
				throw new Exception("服务端返错误信息(" + responseData.getMessage() + ")");
			}
			if (responseEntity.getStatusCodeValue() != 200) {
				// 访问链路异常
				throw new Exception("服务端返回状态码(" + responseEntity.getStatusCodeValue() + ")");
			}
			if (responseData == null || responseData.getData() == null) {
				throw new Exception("无法解析服务端返回数据");
			}
			Map tokenInfo = (Map) responseData.getData();
			Credentials cred = new Credentials();
			cred.setAccessToken((String) tokenInfo.get("access_token"));
			long expiresIn = NumberUtils.toLong(String.valueOf(tokenInfo.get("expires_in")));
			if (expiresIn > 0) {
				cred.setExpiresIn(expiresIn);
			}
			return cred;
		} catch (Exception e) {
			_log.error("交换访问令牌失败", e);
			throw new AuthenticationException("刷新访问令牌失败:" + e.getMessage(), e);
		}
	}

	@Override
	public boolean validateToken(String idpUserID, String accessToken) {
		if (StringUtils.isEmpty(idpUserID)) {
			return false;
		}
		Map params = new HashMap();
		// 请求参数
		params.put("idpUserID", idpUserID);
		params.put("timestamp", System.currentTimeMillis());
		// 64位的无符号的随机数，对于每个请求独特的生成
		params.put("nonce", RandomStringUtils.random(64, false, true));
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
			// 接口认证头
			headers.add("client_id", clientId);
			headers.add("access_token", accessToken);
			HttpEntity<Map> request = new HttpEntity<Map>(params, headers);
			ResponseEntity<B2CIDMResponse> responseEntity = restTemplate.postForEntity(url + "/oauth2/token/info",
					request, B2CIDMResponse.class);
			B2CIDMResponse responseData = responseEntity.getBody();
			if (responseData != null && responseData.getStatusCode() != 200) {
				// 服务端主动返回的错误信息
				throw new Exception("服务端返错误信息(" + responseData.getMessage() + ")");
			}
			if (responseEntity.getStatusCodeValue() != 200) {
				// 访问链路异常
				throw new Exception("服务端返回状态码(" + responseEntity.getStatusCodeValue() + ")");
			}
			if (responseData == null || responseData.getData() == null) {
				throw new Exception("无法解析服务端返回数据");
			}
			Map userinfo = (Map) responseData.getData();
			String returnName = (String) userinfo.get("userName");
			return StringUtils.equalsIgnoreCase(returnName, idpUserID);
		} catch (Exception e) {
			_log.error("验证令牌失败", e);
			return false;
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public static class B2CIDMResponse<T> {

		private int statusCode;

		private String subStatusCode;

		private String message;

		private T data;

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public String getSubStatusCode() {
			return subStatusCode;
		}

		public void setSubStatusCode(String subStatusCode) {
			this.subStatusCode = subStatusCode;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

	}

}
