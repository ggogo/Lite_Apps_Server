package com.sgm.liteapp.admin.model.dto;

import java.util.List;

import com.sgm.liteapp.admin.model.entity.TcLiteappConfig;
import com.sgm.liteapp.admin.model.entity.TcLiteappViewConfig;
import com.sgm.liteapp.admin.model.entity.TmCode;
import com.sgm.liteapp.admin.model.entity.TtLiteappPlatform;

public class LiteappMainDetailDto {

	private TcLiteappConfig tcLiteappConfig;
	
	private List<TmCode> oemCodeList;
	
	private List<TmCode> softwareList;
	
	private List<TmCode> hardwareList;
	
	private List<TtLiteappPlatform> platformList;
	
	private List<TcLiteappViewConfig> smallViewList;
	
	private List<TcLiteappViewConfig> infoflowViewList;

	public List<TtLiteappPlatform> getPlatformList() {
		return platformList;
	}

	public void setPlatformList(List<TtLiteappPlatform> platformList) {
		this.platformList = platformList;
	}

	public List<TcLiteappViewConfig> getSmallViewList() {
		return smallViewList;
	}

	public void setSmallViewList(List<TcLiteappViewConfig> smallViewList) {
		this.smallViewList = smallViewList;
	}

	public List<TcLiteappViewConfig> getInfoflowViewList() {
		return infoflowViewList;
	}

	public void setInfoflowViewList(List<TcLiteappViewConfig> infoflowViewList) {
		this.infoflowViewList = infoflowViewList;
	}

	public TcLiteappConfig getTcLiteappConfig() {
		return tcLiteappConfig;
	}

	public void setTcLiteappConfig(TcLiteappConfig tcLiteappConfig) {
		this.tcLiteappConfig = tcLiteappConfig;
	}

	public List<TmCode> getOemCodeList() {
		return oemCodeList;
	}

	public void setOemCodeList(List<TmCode> oemCodeList) {
		this.oemCodeList = oemCodeList;
	}

	public List<TmCode> getSoftwareList() {
		return softwareList;
	}

	public void setSoftwareList(List<TmCode> softwareList) {
		this.softwareList = softwareList;
	}

	public List<TmCode> getHardwareList() {
		return hardwareList;
	}

	public void setHardwareList(List<TmCode> hardwareList) {
		this.hardwareList = hardwareList;
	}
	
}
