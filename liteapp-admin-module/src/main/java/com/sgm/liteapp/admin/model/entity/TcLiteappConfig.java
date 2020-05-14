package com.sgm.liteapp.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 轻应用配置
 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
@TableName("TC_LITEAPP_CONFIG")
public class TcLiteappConfig implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("LITEAPP_CONFIG_ID")
    private String liteappConfigId;

    /**
     * 主键
     */
    @TableField("LITEAPP_RELEASE_ID")
    private String liteappReleaseId;

    @TableField("FRIENDLY_NAME")
    private String friendlyName;

    /**
     * 小图标
     */
    @TableField("SMALL_ICON")
    private String smallIcon;

    /**
     * 大图标
     */
    @TableField("BIG_ICON")
    private String bigIcon;

    /**
     * 轻应用信息图标
     */
    @TableField("INFO_ICON")
    private String infoIcon;

    /**
     * 描述
     */
    @TableField("INTOR")
    private String intor;

    /**
     * 版本信息
     */
    @TableField("VERSION")
    private String version;

    /**
     * 试用硬件平台
     */
    @TableField("HARDWARE_VERSION")
    private String hardwareVersion;

    @TableField("SOFTWARE_VERSION")
    private String softwareVersion;

    @TableField("APP_LEVEL")
    private String appLevel;

    @TableField("API_LEVEL")
    private String apiLevel;

    @TableField("APP_TYPE")
    private String appType;

    @TableField("GUID")
    private String guid;

    @TableField("SHORTCUT")
    private String shortcut;

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
    
    @TableField(value = "STATUS", exist = false)
    private String status;
    @TableField(value = "LITEAPP_NAME", exist =  false)
    private String liteappName;
    @TableField(value = "OEM_CODE", exist =  false)
    private String oemCode;
	
	@TableField(value = "SUBSCRIBE_STATUS", exist =  false)
    private String subscribeStatus;
    
    
	public String getSubscribeStatus() {
		return subscribeStatus;
	}
	public void setSubscribeStatus(String subscribeStatus) {
		this.subscribeStatus = subscribeStatus;
	}
	public String getLiteappConfigId() {
		return liteappConfigId;
	}
	public void setLiteappConfigId(String liteappConfigId) {
		this.liteappConfigId = liteappConfigId;
	}
	public String getLiteappReleaseId() {
		return liteappReleaseId;
	}
	public void setLiteappReleaseId(String liteappReleaseId) {
		this.liteappReleaseId = liteappReleaseId;
	}
	public String getFriendlyName() {
		return friendlyName;
	}
	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}
	public String getSmallIcon() {
		return smallIcon;
	}
	public void setSmallIcon(String smallIcon) {
		this.smallIcon = smallIcon;
	}
	public String getBigIcon() {
		return bigIcon;
	}
	public void setBigIcon(String bigIcon) {
		this.bigIcon = bigIcon;
	}
	public String getInfoIcon() {
		return infoIcon;
	}
	public void setInfoIcon(String infoIcon) {
		this.infoIcon = infoIcon;
	}
	public String getIntor() {
		return intor;
	}
	public void setIntor(String intor) {
		this.intor = intor;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
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
	public String getAppLevel() {
		return appLevel;
	}
	public void setAppLevel(String appLevel) {
		this.appLevel = appLevel;
	}
	public String getApiLevel() {
		return apiLevel;
	}
	public void setApiLevel(String apiLevel) {
		this.apiLevel = apiLevel;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLiteappName() {
		return liteappName;
	}
	public void setLiteappName(String liteappName) {
		this.liteappName = liteappName;
	}
	public String getOemCode() {
		return oemCode;
	}
	public void setOemCode(String oemCode) {
		this.oemCode = oemCode;
	}
	@Override
	public String toString() {
		return "TcLiteappConfig [liteappConfigId=" + liteappConfigId + ", liteappReleaseId=" + liteappReleaseId
				+ ", friendlyName=" + friendlyName + ", smallIcon=" + smallIcon + ", bigIcon=" + bigIcon + ", infoIcon="
				+ infoIcon + ", intor=" + intor + ", version=" + version + ", hardwareVersion=" + hardwareVersion
				+ ", softwareVersion=" + softwareVersion + ", appLevel=" + appLevel + ", apiLevel=" + apiLevel
				+ ", appType=" + appType + ", guid=" + guid + ", shortcut=" + shortcut + ", createDate=" + createDate
				+ ", lastUpdateBy=" + lastUpdateBy + ", lastUpdateDate=" + lastUpdateDate + ", isValid=" + isValid
				+ ", createBy=" + createBy + ", status=" + status + ", liteappName=" + liteappName + ", oemCode="
				+ oemCode + ", subscribeStatus=" + subscribeStatus + "]";
	}

	
}
