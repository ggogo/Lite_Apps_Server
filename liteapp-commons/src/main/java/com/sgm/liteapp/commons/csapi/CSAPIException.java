package com.sgm.liteapp.commons.csapi;

public class CSAPIException extends Exception {

	private static final long serialVersionUID = 1L;

	public CSAPIException(String message) {
		super(message);
	}

	public CSAPIException(String message, Throwable cause) {
		super(message, cause);
	}
}
