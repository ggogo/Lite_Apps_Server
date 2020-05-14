package com.sgm.liteapp.cloudapi.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("TT_STORE_LITEAPP")
public class StoreLiteappEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String storeLiteappId;

	private String liteappName;

	private String createBy;

	private Date createDate;

	private String lastUpdateBy;

	private Date lastUpdateDate;

	private int isValid;

	private String liteappGuid;

	private String oemCode;

	private String softwareCode;

	private String hardwareCode;

	private String liteappReleaseId;

	private String hardwareVersion;

	private String softwareVersion;

	private String liteappVersion;

	public String getStoreLiteappId() {
		return storeLiteappId;
	}

	public void setStoreLiteappId(String storeLiteappId) {
		this.storeLiteappId = storeLiteappId;
	}

	public String getLiteappName() {
		return liteappName;
	}

	public void setLiteappName(String liteappName) {
		this.liteappName = liteappName;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public String getOemCode() {
		return oemCode;
	}

	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}

	public String getSoftwareCode() {
		return softwareCode;
	}

	public void setSoftwareCode(String softwareCode) {
		this.softwareCode = softwareCode;
	}

	public String getHardwareCode() {
		return hardwareCode;
	}

	public void setHardwareCode(String hardwareCode) {
		this.hardwareCode = hardwareCode;
	}

	public String getLiteappReleaseId() {
		return liteappReleaseId;
	}

	public void setLiteappReleaseId(String liteappReleaseId) {
		this.liteappReleaseId = liteappReleaseId;
	}

	public String getHardwareVersion() {
		return hardwareVersion;
	}

	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getLiteappVersion() {
		return liteappVersion;
	}

	public void setLiteappVersion(String liteappVersion) {
		this.liteappVersion = liteappVersion;
	}

	public String getLiteappGuid() {
		return liteappGuid;
	}

	public void setLiteappGuid(String liteappGuid) {
		this.liteappGuid = liteappGuid;
	}

}
