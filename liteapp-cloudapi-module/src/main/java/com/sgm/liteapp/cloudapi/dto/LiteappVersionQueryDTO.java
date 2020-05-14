package com.sgm.liteapp.cloudapi.dto;

public class LiteappVersionQueryDTO {

	private String oem;

	private String hardwareCode;

	private String hardwareVersion;

	private String softwareCode;

	private String softwareVersion;

	private String appGuid;

	private String appVersion;

	public String getOem() {
		return oem;
	}

	public void setOem(String oem) {
		this.oem = oem;
	}

	public String getHardwareCode() {
		return hardwareCode;
	}

	public void setHardwareCode(String hardwareCode) {
		this.hardwareCode = hardwareCode;
	}

	public String getHardwareVersion() {
		return hardwareVersion;
	}

	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}

	public String getSoftwareCode() {
		return softwareCode;
	}

	public void setSoftwareCode(String softwareCode) {
		this.softwareCode = softwareCode;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getAppGuid() {
		return appGuid;
	}

	public void setAppGuid(String appGuid) {
		this.appGuid = appGuid;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

}
