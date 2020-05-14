package com.sgm.liteapp.admin.model.dto;

import java.io.Serializable;

public class UploadInfoflowviewsJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9026795128789453150L;
	
	private String name;
	
	private String friendlyName;
	
	private String guid;
	
	private String version;
	
	private String showtype;
	
	private String path;
	
	private String thumbnail;
	
	private UploadClosestrategyJson closestrategy;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFriendlyName() {
		return friendlyName;
	}
	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getShowtype() {
		return showtype;
	}
	public void setShowtype(String showtype) {
		this.showtype = showtype;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public UploadClosestrategyJson getClosestrategy() {
		return closestrategy;
	}
	public void setClosestrategy(UploadClosestrategyJson closestrategy) {
		this.closestrategy = closestrategy;
	}
	
}