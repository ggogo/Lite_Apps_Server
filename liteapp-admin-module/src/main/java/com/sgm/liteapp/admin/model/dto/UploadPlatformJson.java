package com.sgm.liteapp.admin.model.dto;

import java.io.Serializable;

public class UploadPlatformJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4251241836657489599L;
	
	private String hardwareversion;
	
	private String softwareversion;
	
	public String getHardwareversion() {
		return hardwareversion;
	}
	public void setHardwareversion(String hardwareversion) {
		this.hardwareversion = hardwareversion;
	}
	public String getSoftwareversion() {
		return softwareversion;
	}
	public void setSoftwareversion(String softwareversion) {
		this.softwareversion = softwareversion;
	}
	@Override
	public String toString() {
		return "UploadPlatformInfo [hardwareversion=" + hardwareversion + ", softwareversion=" + softwareversion + "]";
	}
	
}
