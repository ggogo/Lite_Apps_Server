package com.sgm.liteapp.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 轻应用商店当前最新版本
 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
@TableName("TT_STORE_LITEAPP")
public class TtStoreLiteapp implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId("STORE_LITEAPP_ID")
    private String storeLiteappId;

    @TableField("LITEAPP_NAME")
    private String liteappName;

    /**
     * 创建人
     */
    @TableField("CREATE_BY")
    private String createBy;

    /**
     * 创建时间
     */
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
    @TableField("LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    /**
     * 是否生效
     */
    @TableField("IS_VALID")
    private Integer isValid;

    @TableField("LITEAPP_GUID")
    private String liteappGuidId;

    @TableField("OEM_CODE")
    private String oemCode;

    @TableField("SOFTWARE_CODE")
    private String softwareCode;

    @TableField("HARDWARE_CODE")
    private String hardwareCode;

    @TableField("LITEAPP_RELEASE_ID")
    private String liteappReleaseId;

    @TableField("LITEAPP_VERSION")
    private String liteappVersion;

    /**
     * 试用硬件平台
     */
    @TableField("HARDWARE_VERSION")
    private String hardwareVersion;

    @TableField("SOFTWARE_VERSION")
    private String softwareVersion;


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

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getLiteappGuidId() {
        return liteappGuidId;
    }

    public void setLiteappGuidId(String liteappGuidId) {
        this.liteappGuidId = liteappGuidId;
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

    public String getLiteappVersion() {
        return liteappVersion;
    }

    public void setLiteappVersion(String liteappVersion) {
        this.liteappVersion = liteappVersion;
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

    @Override
    public String toString() {
        return "TtStoreLiteapp{" +
        "storeLiteappId=" + storeLiteappId +
        ", liteappName=" + liteappName +
        ", createBy=" + createBy +
        ", createDate=" + createDate +
        ", lastUpdateBy=" + lastUpdateBy +
        ", lastUpdateDate=" + lastUpdateDate +
        ", isValid=" + isValid +
        ", liteappGuidId=" + liteappGuidId +
        ", oemCode=" + oemCode +
        ", softwareCode=" + softwareCode +
        ", hardwareCode=" + hardwareCode +
        ", liteappReleaseId=" + liteappReleaseId +
        ", liteappVersion=" + liteappVersion +
        ", hardwareVersion=" + hardwareVersion +
        ", softwareVersion=" + softwareVersion +
        "}";
    }
}