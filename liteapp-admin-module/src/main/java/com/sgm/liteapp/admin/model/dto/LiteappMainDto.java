package com.sgm.liteapp.admin.model.dto;

import java.util.List;


public class LiteappMainDto extends PageDto{

	 private String liteappReleaseId;
	 private String guid;
	 private String liteappName;
	 private String status;
	 private String version;
	 private String appType;
	 private String subscribeStatus;
	 
	 private String actionType;
	 private String oemCode;
	 private List<LiteappPlatformDto> liteappPlatformList;
	 private List<LiteappInfoflowViewDto> liteappInfoflowViewList;
	 
	public List<LiteappInfoflowViewDto> getLiteappInfoflowViewList() {
		return liteappInfoflowViewList;
	}
	public void setLiteappInfoflowViewList(List<LiteappInfoflowViewDto> liteappInfoflowViewList) {
		this.liteappInfoflowViewList = liteappInfoflowViewList;
	}
	public String getLiteappReleaseId() {
		return liteappReleaseId;
	}
	public void setLiteappReleaseId(String liteappReleaseId) {
		this.liteappReleaseId = liteappReleaseId;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getLiteappName() {
		return liteappName;
	}
	public void setLiteappName(String liteappName) {
		this.liteappName = liteappName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getSubscribeStatus() {
		return subscribeStatus;
	}
	public void setSubscribeStatus(String subscribeStatus) {
		this.subscribeStatus = subscribeStatus;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getOemCode() {
		return oemCode;
	}
	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
	public List<LiteappPlatformDto> getLiteappPlatformList() {
		return liteappPlatformList;
	}
	public void setLiteappPlatformList(List<LiteappPlatformDto> liteappPlatformList) {
		this.liteappPlatformList = liteappPlatformList;
	}
	 
	 
}
