package com.sgm.liteapp.admin.exception;
/**
 * @author RSD
 *自定义请求异常
 * 2018年11月7日 -- 下午5:52:27
 */
public class ResultException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private Integer code;
	private String message;
	
	
	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	public ResultException(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}


	public ResultException(String message) {
		super();
		this.message = message;
	}

}
