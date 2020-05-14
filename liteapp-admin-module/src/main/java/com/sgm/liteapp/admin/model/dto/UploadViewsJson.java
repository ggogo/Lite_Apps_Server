package com.sgm.liteapp.admin.model.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadViewsJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1695514221844800819L;
	
	private String guid;
	
	private String name;
	
	private String version;
	
	private String friendlyname;
	
	private String type;
	
	private String width;
	
	private String height;
	
	private UploadIconJson icon;
	
	private String thumbnail;
	
	private String path;
	
	private String display;
	
	private UploadSysactJson sysact;
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getFriendlyname() {
		return friendlyname;
	}
	public void setFriendlyname(String friendlyname) {
		this.friendlyname = friendlyname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public UploadIconJson getIcon() {
		return icon;
	}
	public void setIcon(UploadIconJson icon) {
		this.icon = icon;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public UploadSysactJson getSysact() {
		return sysact;
	}
	public void setSysact(UploadSysactJson sysact) {
		this.sysact = sysact;
	}
	@Override
	public String toString() {
		return "UploadViewsInfo [guid=" + guid + ", name=" + name + ", version=" + version + ", friendlyname="
				+ friendlyname + ", type=" + type + ", width=" + width + ", height=" + height + ", icon=" + icon
				+ ", thumbnail=" + thumbnail + ", path=" + path + ", display=" + display + ", sysact=" + sysact + "]";
	}
}
