package com.sgm.liteapp.admin.model.dto;

import java.io.Serializable;

public class UploadPagesJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2852193124910752435L;

	private String guid;
	
	private String name;
	
	private String version;
	
	private String friendlyname;
	
	private String path;
	
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "UploadPagesJson [guid=" + guid + ", name=" + name + ", version=" + version + ", friendlyname="
				+ friendlyname + ", path=" + path + "]";
	}
}
