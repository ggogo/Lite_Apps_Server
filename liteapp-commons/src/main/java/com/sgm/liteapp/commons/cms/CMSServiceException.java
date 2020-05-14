package com.sgm.liteapp.commons.cms;

public class CMSServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public CMSServiceException(String message) {
		super(message);
	}

	public CMSServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
