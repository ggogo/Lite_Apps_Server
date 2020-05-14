package com.sgm.liteapp.admin.model.dto;

import java.io.Serializable;

public class UploadIconJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6792177452914384211L;
	
	private String small;
	
	private String big;
	
	public String getSmall() {
		return small;
	}
	public void setSmall(String small) {
		this.small = small;
	}
	public String getBig() {
		return big;
	}
	public void setBig(String big) {
		this.big = big;
	}
	@Override
	public String toString() {
		return "UploadIconInfo [small=" + small + ", big=" + big + "]";
	}
	
}
