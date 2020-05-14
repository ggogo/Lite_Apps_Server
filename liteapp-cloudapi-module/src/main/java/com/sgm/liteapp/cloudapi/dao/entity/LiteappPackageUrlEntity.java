package com.sgm.liteapp.cloudapi.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("TT_LITEAPP_PACKAGE_URL")
public class LiteappPackageUrlEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String oemCode;

	private String softwareCode;

	private String hardwareCode;

	private String liteappGuid;

	private String hardwareVersion;

	private String softwareVersion;

	private String liteAppVersion;

	private String createBy;

	private Date createDate;

	private String lastUpdateBy;

	private Date lastUpdateDate;

	private int isValid;

	private String fullPackageUrl;

	private String diffPackageUrl;

	private String fromVersion;

	private String toVersion;

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

	public String getLiteappGuid() {
		return liteappGuid;
	}

	public void setLiteappGuid(String liteappGuid) {
		this.liteappGuid = liteappGuid;
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

	public String getLiteAppVersion() {
		return liteAppVersion;
	}

	public void setLiteAppVersion(String liteAppVersion) {
		this.liteAppVersion = liteAppVersion;
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

	public String getFullPackageUrl() {
		return fullPackageUrl;
	}

	public void setFullPackageUrl(String fullPackageUrl) {
		this.fullPackageUrl = fullPackageUrl;
	}

	public String getDiffPackageUrl() {
		return diffPackageUrl;
	}

	public void setDiffPackageUrl(String diffPackageUrl) {
		this.diffPackageUrl = diffPackageUrl;
	}

	public String getFromVersion() {
		return fromVersion;
	}

	public void setFromVersion(String fromVersion) {
		this.fromVersion = fromVersion;
	}

	public String getToVersion() {
		return toVersion;
	}

	public void setToVersion(String toVersion) {
		this.toVersion = toVersion;
	}

}
