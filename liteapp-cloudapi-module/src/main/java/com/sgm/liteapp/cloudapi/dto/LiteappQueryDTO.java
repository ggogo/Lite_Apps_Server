package com.sgm.liteapp.cloudapi.dto;

public class LiteappQueryDTO {

	@SuppressWarnings("unused")
	private String oemCode;

	private String oem;

	private String hardwareCode;

	private String hardwareVersion;

	private String softwareCode;

	private String softwareVersion;

	private String appName;

	private String idpUserId;

	public String getOem() {
		return oem;
	}

	public void setOem(String oem) {
		this.oem = oem;
	}

	public String getOemCode() {
		return oem;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
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

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getIdpUserId() {
		return idpUserId;
	}

	public void setIdpUserId(String idpUserId) {
		this.idpUserId = idpUserId;
	}

}
