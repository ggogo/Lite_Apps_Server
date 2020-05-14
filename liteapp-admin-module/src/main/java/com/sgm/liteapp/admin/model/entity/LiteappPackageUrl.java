package com.sgm.liteapp.admin.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("TT_LITEAPP_PACKAGE_URL")
public class LiteappPackageUrl implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("LITEAPP_PACKAGE_URL_ID")
	private String LiteappPackageUrlId;

	@TableField("OEM_CODE")
	private String oemCode;

	@TableField("SOFTWARE_CODE")
	private String softwareCode;

	@TableField("HARDWARE_CODE")
	private String hardwareCode;

	@TableField("SOFTWARE_VERSION")
	private String softwareVersion;

	@TableField("HARDWARE_VERSION")
	private String hardwareVersion;

	@TableField("LAST_UPDATE_BY")
	private String lastUpdateBy;

	@TableField("LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@TableField("IS_VALID")
	private Integer isValid;

	@TableField("CREATE_BY")
	private String createBy;

	@TableField("CREATE_DATE")
	private Date createDate;

	@TableField("LITEAPP_GUID")
	private String liteappGuidId;

	@TableField("FULL_PACKAGE_URL")
	private String fullPackageUrl;

	@TableField("FROM_VERSION")
	private String fromVersion;

	@TableField("TO_VERSION")
	private String toVersion;

	@TableField("DIFF_PACKAGE_URL")
	private String diffPackageUrl;

	@TableField("ORIGINAL_PACKAGE_URL")
	private String originalPackageUrl;

	public String getLiteappPackageUrlId() {
		return LiteappPackageUrlId;
	}

	public void setLiteappPackageUrlId(String liteappPackageUrlId) {
		LiteappPackageUrlId = liteappPackageUrlId;
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

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getHardwareVersion() {
		return hardwareVersion;
	}

	public void setHardwareVersion(String hardwareVersion) {
		this.hardwareVersion = hardwareVersion;
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

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
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

	public String getLiteappGuidId() {
		return liteappGuidId;
	}

	public void setLiteappGuidId(String liteappGuidId) {
		this.liteappGuidId = liteappGuidId;
	}

	public String getFullPackageUrl() {
		return fullPackageUrl;
	}

	public void setFullPackageUrl(String fullPackageUrl) {
		this.fullPackageUrl = fullPackageUrl;
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

	public String getDiffPackageUrl() {
		return diffPackageUrl;
	}

	public void setDiffPackageUrl(String diffPackageUrl) {
		this.diffPackageUrl = diffPackageUrl;
	}

	public String getOriginalPackageUrl() {
		return originalPackageUrl;
	}

	public void setOriginalPackageUrl(String originalPackageUrl) {
		this.originalPackageUrl = originalPackageUrl;
	}

}
