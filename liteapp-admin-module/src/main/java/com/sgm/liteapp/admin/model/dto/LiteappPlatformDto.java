package com.sgm.liteapp.admin.model.dto;

public class LiteappPlatformDto {

	private String liteappPlatformId;
    private String oemCode;
    private String softwareCode;
    private String hardwareCode;
    private String isSubscribe;
	
    
	public String getLiteappPlatformId() {
		return liteappPlatformId;
	}
	public void setLiteappPlatformId(String liteappPlatformId) {
		this.liteappPlatformId = liteappPlatformId;
	}
	public String getOemCode() {
		return oemCode;
	}
	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
	public String getSoftwareCode() {
		return softwareCode;
	}
	public void setSoftwareCode(String softwareCode) {
		this.softwareCode = softwareCode;
	}
	public String getHardwareCode() {
		return hardwareCode;
	}
	public void setHardwareCode(String hardwareCode) {
		this.hardwareCode = hardwareCode;
	}
	public String getIsSubscribe() {
		return isSubscribe;
	}
	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
    
}
