package com.sgm.liteapp.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 轻应用发布历史
 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
@TableName("TL_LITEAPP_RELEASE")
public class TlLiteappRelease implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId("LITEAPP_RELEASE_ID")
    private String liteappReleaseId;

    @TableField("LITEAPP_NAME")
    private String liteappName;

    /**
     * 版本ID
     */
    @TableField("APP_VERSION_ID")
    private String appVersionId;

    /**
     * 号
     */
    @TableField("APP_VERSION_NUMBER")
    private String appVersionNumber;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("CREATE_DATE")
    private Date createDate;

    /**
     * 更新人
     */
    @TableField("LAST_UPDATE_BY")
    private String lastUpdateBy;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    /**
     * 是否生效
     */
    @TableField("IS_VALID")
    private Integer isValid;

    /**
     * 创建人
     */
    @TableField("CREATE_BY")
    private String createBy;

    /**
     * 说明
     */
    @TableField("RELEASE_NODE")
    private String releaseNode;

    /**
     * 发布(上线)日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("RELEASE_DATE")
    private Date releaseDate;

    @TableField("APP_CONFIG_LOCATION")
    private String appConfigLocation;

    @TableField("APP_CARD_URL")
    private String appCardUrl;

    @TableField("PACKAGE_URL")
    private String packageUrl;

    @TableField("INFOFLOW_CARD_URL")
    private String infoflowCardUrl;
    
    @TableField("STATUS")
    private String status;
    
    @TableField("SUBSCRIBE_STATUS")
    private String subscribeStatus;
    
    @TableField(value = "GUID", exist = false)
    private String guid;
    
    @TableField(value = "FRIENDLY_NAME", exist = false)
    private String friendlyName;
    
    @TableField(value = "APP_TYPE", exist = false)
    private String appType;
    
    @TableField(value = "VERSION", exist = false)
    private String version;
   
    @TableField(value = "SOFTWARE_VERSION", exist = false)
    private String softwareVersion;
    
    @TableField(value = "HARDWARE_VERSION", exist = false)
    private String hardwareVersion;

    
	public String getSubscribeStatus() {
		return subscribeStatus;
	}

	public void setSubscribeStatus(String subscribeStatus) {
		this.subscribeStatus = subscribeStatus;
	}

	public String getLiteappReleaseId() {
		return liteappReleaseId;
	}

	public void setLiteappReleaseId(String liteappReleaseId) {
		this.liteappReleaseId = liteappReleaseId;
	}

	public String getLiteappName() {
		return liteappName;
	}

	public void setLiteappName(String liteappName) {
		this.liteappName = liteappName;
	}

	public String getAppVersionId() {
		return appVersionId;
	}

	public void setAppVersionId(String appVersionId) {
		this.appVersionId = appVersionId;
	}

	public String getAppVersionNumber() {
		return appVersionNumber;
	}

	public void setAppVersionNumber(String appVersionNumber) {
		this.appVersionNumber = appVersionNumber;
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

	public String getReleaseNode() {
		return releaseNode;
	}

	public void setReleaseNode(String releaseNode) {
		this.releaseNode = releaseNode;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getAppConfigLocation() {
		return appConfigLocation;
	}

	public void setAppConfigLocation(String appConfigLocation) {
		this.appConfigLocation = appConfigLocation;
	}

	public String getAppCardUrl() {
		return appCardUrl;
	}

	public void setAppCardUrl(String appCardUrl) {
		this.appCardUrl = appCardUrl;
	}

	public String getPackageUrl() {
		return packageUrl;
	}

	public void setPackageUrl(String packageUrl) {
		this.packageUrl = packageUrl;
	}

	public String getInfoflowCardUrl() {
		return infoflowCardUrl;
	}

	public void setInfoflowCardUrl(String infoflowCardUrl) {
		this.infoflowCardUrl = infoflowCardUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	@Override
	public String toString() {
		return "TlLiteappRelease [liteappReleaseId=" + liteappReleaseId + ", liteappName=" + liteappName
				+ ", appVersionId=" + appVersionId + ", appVersionNumber=" + appVersionNumber + ", createDate="
				+ createDate + ", lastUpdateBy=" + lastUpdateBy + ", lastUpdateDate=" + lastUpdateDate + ", isValid="
				+ isValid + ", createBy=" + createBy + ", releaseNode=" + releaseNode + ", releaseDate=" + releaseDate
				+ ", appConfigLocation=" + appConfigLocation + ", appCardUrl=" + appCardUrl + ", packageUrl="
				+ packageUrl + ", infoflowCardUrl=" + infoflowCardUrl + ", status=" + status + ", subscribeStatus="
				+ subscribeStatus + ", guid=" + guid + ", friendlyName=" + friendlyName + ", appType=" + appType
				+ ", version=" + version + ", softwareVersion=" + softwareVersion + ", hardwareVersion="
				+ hardwareVersion + "]";
	}
}
