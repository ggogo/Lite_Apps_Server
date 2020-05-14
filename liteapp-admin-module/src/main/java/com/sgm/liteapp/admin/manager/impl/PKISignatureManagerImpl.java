package com.sgm.liteapp.admin.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.sgm.liteapp.admin.manager.PKISignatureManager;
import com.sgm.liteapp.commons.crypto.digest.DigestUtils;
import com.sgm.liteapp.commons.crypto.digest.FileSignSequence;
import com.sgm.liteapp.commons.utils.IOUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.asn1.DEROutputStream;

@Component
@ConfigurationProperties(prefix = "pki.sign")
public class PKISignatureManagerImpl implements PKISignatureManager {

	private String algorithm;

	private String url;

	private String appid;

	private String appkey;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public byte[] signFile(byte[] srcData) throws Exception {
		return signFile(srcData, algorithm);
	}

	@Override
	public byte[] signFile(byte[] srcData, String algorithm) throws Exception {
		ByteArrayOutputStream bOut = null;
		DEROutputStream dOut = null;
		try {
			byte[] signature = Base64.decodeBase64(this.getDigestSignature(srcData, algorithm));
			FileSignSequence sequence = new FileSignSequence(Integer.valueOf(1), srcData, getOid(algorithm), signature);
			bOut = new ByteArrayOutputStream();
			dOut = new DEROutputStream(bOut);
			dOut.writeObject(sequence.getDERSeqObject());
			return bOut.toByteArray();
		} catch (Exception e) {
			throw new Exception("生成文件签名错误", e);
		} finally {
			if (dOut != null) {
				dOut.close();
			}
			if (bOut != null) {
				bOut.close();
			}
		}

	}

	@Override
	public String getDigestSignature(byte[] srcData, String algorithm) throws Exception {
		if (ArrayUtils.isEmpty(srcData)) {
			throw new Exception("无法对空文件生成摘要");
		}
		try {
			byte[] fileDigest = DigestUtils.digest(srcData, algorithm);
			return getSignature(fileDigest);
		} catch (Exception e) {
			throw new Exception("生成摘要签名错误", e);
		}
	}

	@Override
	public String getDigestSignature(byte[] srcData) throws Exception {
		return getDigestSignature(srcData, algorithm);
	}

	@Override
	public String getSignature(byte[] srcData) throws Exception {
		if (ArrayUtils.isEmpty(srcData)) {
			throw new Exception("无法对空文件进行签名");
		}
		ByteArrayOutputStream bOut = null;
		DEROutputStream dOut = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
		try {
			String plainBase64 = Base64.encodeBase64String(srcData);
			param.add("plainBase64", plainBase64);
			param.add("appid", appid);
			param.add("appkey", appkey);
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(param,
					headers);
			ResponseEntity<ApiResponse> response = restTemplate.postForEntity(url, request, ApiResponse.class);
			ApiResponse json = response.getBody();
			if (response.getStatusCode() != HttpStatus.OK) {
				if (json != null && json.getMeta() != null) {
					throw new Exception("PKI接口访问异常(" + json.getMeta().getMessage() + ")");
				} else {
					throw new Exception("PKI接口访问异常(" + response.getStatusCode().value() + ")");
				}
			}
			if (json == null || json.getMeta() == null) {
				throw new Exception("PKI接口返回数据不完整");
			}
			if (!json.getMeta().isSuccess()) {
				throw new Exception("PKI接口返回错误信息:" + json.getMeta().getMessage());
			}
			return json.getData().toString();
		} catch (Exception e) {
			throw new Exception("生成文件签名错误", e);
		} finally {
			if (dOut != null) {
				dOut.close();
			}
			if (bOut != null) {
				bOut.close();
			}
		}
	}

	private String getOid(String algorithm) throws NoSuchAlgorithmException {
		if ((null == algorithm) || ("".equals(algorithm))) {
			algorithm = "sm3";
		} else {
			algorithm = StringUtils.lowerCase(algorithm);
		}
		if ("sha256".equals(algorithm)) {
			return "2.16.840.1.101.3.4.2.1";
		}
		if ("sha384".equals(algorithm)) {
			return "2.16.840.1.101.3.4.2.2";
		}
		if (("sm3".equals(algorithm)) || ("ecdsa-sm2".equals(algorithm))) {
			return "1.2.156.10197.1.401";
		}
		throw new NoSuchAlgorithmException("不支持的算法");
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	protected static class ApiResponse implements Serializable {

		private static final long serialVersionUID = 1L;

		private static final String OK = "ok";

		private static final String ERROR = "error";

		private Meta meta;

		private Object data;

		protected ApiResponse() {
			this.meta = new Meta(true);
			this.data = null;
		}

		protected ApiResponse success() {
			this.meta = new Meta(true, OK);
			return this;
		}

		protected ApiResponse success(Object data) {
			this.meta = new Meta(true, OK);
			this.data = data;
			return this;
		}

		protected ApiResponse failure() {
			this.meta = new Meta(false, ERROR);
			return this;
		}

		protected ApiResponse failure(String message) {
			this.meta = new Meta(false, message);
			return this;
		}

		public Meta getMeta() {
			return meta;
		}

		public void setMeta(Meta meta) {
			this.meta = meta;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

	}

	protected static class Meta implements Serializable {
		private static final long serialVersionUID = 1L;

		private boolean success;

		private String message;

		protected Meta() {
			this.success = true;
			this.message = "ok";
		}

		protected Meta(boolean success) {
			this.success = success;
		}

		protected Meta(boolean success, String message) {
			this.success = success;
			this.message = message;
		}

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

	public static void main(String[] args) throws Exception {
		PKISignatureManagerImpl sign = new PKISignatureManagerImpl();
		sign.appid = "ABCDE";
		sign.appkey = "ZmMsKgSqnEKZvK7g8dROvQ==";
		sign.url = "http://10.203.96.153:9000/api/sign-front/v1/signs/pkcs7/ecdsa-sm2";
		sign.algorithm = "sm3";
		sign.restTemplate = new RestTemplate();
		byte[] srcData = IOUtils.readFile(new File("D:\\SGM\\轻应用平台\\liteapp_test.zip"));
		byte[] dest = sign.signFile(srcData);
		java.io.FileOutputStream file = new java.io.FileOutputStream("D:\\liteapp_test.signed");
		file.write(dest);
		file.flush();
		file.close();
	}

}
