package com.sgm.liteapp.commons.crypto.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sgm.liteapp.commons.crypto.digest.sm3.SM3Digest;

public class DigestUtils {

	protected final static Logger log = LoggerFactory.getLogger(DigestUtils.class);

	public static byte[] digest(byte[] srcData, String algorithm) throws NoSuchAlgorithmException {
		if ((null == algorithm) || ("".equals(algorithm))) {
			algorithm = "sm3";
		} else {
			algorithm = StringUtils.lowerCase(algorithm);
		}
		if ("md5".equals(algorithm)) {
			return md5Digest(srcData);
		}
		if ("sha1".equals(algorithm)) {
			return sha1Digest(srcData);
		}
		if ("sha256".equals(algorithm)) {
			return sha256Digest(srcData);
		}
		if ("sha384".equals(algorithm)) {
			return sha384Digest(srcData);
		}
		if (("sm3".equals(algorithm)) || ("ecdsa-sm2".equals(algorithm))) {
			return sm3Digest(srcData);
		}
		throw new NoSuchAlgorithmException("不支持的摘要算法");
	}

	public static byte[] md5Digest(byte[] data) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(data);
		} catch (NoSuchAlgorithmException e) {
			log.error("md5EncodeString.NoSuchAlgorithmException", e);
		}
		return messageDigest.digest();
	}

	public static byte[] sha1Digest(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			return md.digest(data);
		} catch (NoSuchAlgorithmException e) {
			log.error("sha1EncodeString.NoSuchAlgorithmException", e);
		}
		return null;
	}

	public static byte[] sha256Digest(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			return md.digest(data);
		} catch (NoSuchAlgorithmException e) {
			log.error("sha256EncodeString.NoSuchAlgorithmException", e);
		}
		return null;
	}

	public static byte[] sha384Digest(byte[] data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-384");
			return md.digest(data);
		} catch (NoSuchAlgorithmException e) {
			log.error("sha384EncodeString.NoSuchAlgorithmException", e);
		}
		return null;
	}

	public static byte[] sm3Digest(byte[] data) {
		SM3Digest sm3 = new SM3Digest();
		sm3.update(data, 0, data.length);
		byte[] out = new byte[32];
		sm3.doFinal(out, 0);
		return out;
	}
}
