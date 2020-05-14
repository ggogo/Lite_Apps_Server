package com.sgm.liteapp.admin.common;

import com.sgm.liteapp.admin.model.enums.RequestStatus;

public class JsonResult {

	private String msg;
	private Integer code;
	private boolean status = false;
	private Object data;

	public JsonResult() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public JsonResult(String msg, int code, boolean status, Object data) {
		this.msg = msg;
		this.code = code;
		this.status = status;
		this.data = data;
	}

	/**
	 * 返回值
	 *
	 * @param str
	 * @return
	 */
	public static JsonResult RESULT(Object json) {
		return SUCCESS(json);
	}

	/**
	 * 失败返回值
	 *
	 * @param str
	 * @return
	 */
	public static JsonResult ERROR(String date) {
		return new JsonResult(date, 500, RequestStatus.FAIL.getDesc(), null);
	}

	/**
	 * 成功返回值
	 *
	 * @param str
	 * @return
	 */
	public static JsonResult SUCCESS(String date) {
		return new JsonResult(null, 200, RequestStatus.SUCCESS.getDesc(), date);
	}

	/**
	 * 成功返回值
	 *
	 * @return
	 */
	public static JsonResult SUCCESS() {
		return SUCCESS((Object) null);
	}

	/**
	 * 成功返回值
	 *
	 * @param data
	 * @return
	 */
	public static JsonResult SUCCESS(Object data) {
		return new JsonResult(null, 200, RequestStatus.SUCCESS.getDesc(), data);
	}

}
