package com.sgm.liteapp.admin.model.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadMainInfoJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6308343273871866089L;
	
	private String name;
	
	private String friendlyname;
	
	private UploadIconJson appicon;
	
	private String infoicon;
	
	private String version;
	
	private String intro;
	
	private UploadPlatformJson platformInfo;
	
	private String applevel;
	
	private String apptype;
	
	private String guid;
	
	private String shortcut;
	
	private List<UploadViewsJson> views;
	
	private List<UploadInfoflowviewsJson> infoflowviews;
	
	private List<UploadPagesJson> pages;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFriendlyname() {
		return friendlyname;
	}

	public void setFriendlyname(String friendlyname) {
		this.friendlyname = friendlyname;
	}

	public UploadIconJson getAppicon() {
		return appicon;
	}

	public void setAppicon(UploadIconJson appicon) {
		this.appicon = appicon;
	}

	public String getInfoicon() {
		return infoicon;
	}

	public void setInfoicon(String infoicon) {
		this.infoicon = infoicon;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public UploadPlatformJson getPlatformInfo() {
		return platformInfo;
	}

	public void setPlatformInfo(UploadPlatformJson platformInfo) {
		this.platformInfo = platformInfo;
	}

	public String getApplevel() {
		return applevel;
	}

	public void setApplevel(String applevel) {
		this.applevel = applevel;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	public List<UploadViewsJson> getViews() {
		return views;
	}

	public void setViews(List<UploadViewsJson> views) {
		this.views = views;
	}

	public List<UploadInfoflowviewsJson> getInfoflowviews() {
		return infoflowviews;
	}

	public void setInfoflowviews(List<UploadInfoflowviewsJson> infoflowviews) {
		this.infoflowviews = infoflowviews;
	}

	public List<UploadPagesJson> getPages() {
		return pages;
	}

	public void setPages(List<UploadPagesJson> pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "UploadMainInfoJson [name=" + name + ", friendlyname=" + friendlyname + ", appicon=" + appicon
				+ ", infoicon=" + infoicon + ", version=" + version + ", intro=" + intro + ", platformInfo="
				+ platformInfo + ", applevel=" + applevel + ", apptype=" + apptype + ", guid=" + guid + ", shortcut="
				+ shortcut + ", views=" + views + ", infoflowviews=" + infoflowviews + ", pages=" + pages + "]";
	}

	
}
