package com.sgm.liteapp.servicegateway.authority.service;

public interface B2CIDMFederationService {

	public Credentials exchangeTokenByTicket(String idpUserID, String ticket, String deviceId)
			throws AuthenticationException;

	public void logout(String idpUserID, String accessToken);

	public Credentials refresh(String idpUserID, String refreshToken, String deviceId) throws AuthenticationException;

	public boolean validateToken(String idpUserID, String accessToken);
}
