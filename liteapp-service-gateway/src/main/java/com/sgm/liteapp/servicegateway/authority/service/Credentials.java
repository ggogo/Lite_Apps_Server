package com.sgm.liteapp.servicegateway.authority.service;

public class Credentials implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String accessToken;

	private String refreshToken;

	private long expiresIn = 1800L;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

}
