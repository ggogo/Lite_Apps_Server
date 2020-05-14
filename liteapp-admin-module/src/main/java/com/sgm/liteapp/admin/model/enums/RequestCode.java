package com.sgm.liteapp.admin.model.enums;

public enum RequestCode {

	CODE200(200,"请求成功"),
	CODE500(500,"系统错误"),
	CODE501(501,"服务器内部错误"),
	CODE99(99,"类型转换错误"),
	CODE88(88,"登录失败"),
	CODE999(999,"登录超时"),
	CODE77(77,"更新错误"),
	CODE66(66,"用户类型错误"),
	CODE55(55,"未知方法异常"),
	CODE111(111,"导出错误"),
	CODE112(112,"导入错误"),
	CODE113(113,"导入参数为空");
	
	private int code;
	private String desc;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	private RequestCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static RequestCode codeToEnum(Integer code){
		for (RequestCode s : RequestCode.values())  {
			if(code == s.getCode()){
				return s;
			}
		}
		return null;
	}

}