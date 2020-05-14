package com.sgm.liteapp.admin.model.enums;

public enum RequestStatus {
	SUCCESS(0,true),
	FAIL(1,false);

	private int code;
	private boolean desc;

	private RequestStatus(int code, boolean desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean getDesc() {
		return desc;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	public static RequestStatus codeToEnum(Integer code){
		for (RequestStatus s : RequestStatus.values())  {
			if(code == s.getCode()){
				return s;
			}
		}
		return null;
	}


}
