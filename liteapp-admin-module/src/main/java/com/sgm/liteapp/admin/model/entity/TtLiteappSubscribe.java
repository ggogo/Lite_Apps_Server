package com.sgm.liteapp.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户订阅轻应用
 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
@TableName("TT_LITEAPP_SUBSCRIBE")
public class TtLiteappSubscribe implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("IDP_USER_ID")
    private String idpUserId;

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

    @TableId("LITEAPP_SUBSCRIBE_ID")
    private String liteappSubscribeId;

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

    /**
     * 创建人
     */
    @TableField("CREATE_BY")
    private String createBy;

    @TableField("LITEAPP_GUID")
    private String liteappGuid;


    public String getIdpUserId() {
        return idpUserId;
    }

    public void setIdpUserId(String idpUserId) {
        this.idpUserId = idpUserId;
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

    public String getLiteappSubscribeId() {
        return liteappSubscribeId;
    }

    public void setLiteappSubscribeId(String liteappSubscribeId) {
        this.liteappSubscribeId = liteappSubscribeId;
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

    public String getLiteappGuid() {
        return liteappGuid;
    }

    public void setLiteappGuid(String liteappGuid) {
        this.liteappGuid = liteappGuid;
    }

    @Override
    public String toString() {
        return "TtLiteappSubscribe{" +
        "idpUserId=" + idpUserId +
        ", oemCode=" + oemCode +
        ", softwareCode=" + softwareCode +
        ", hardwareCode=" + hardwareCode +
        ", softwareVersion=" + softwareVersion +
        ", hardwareVersion=" + hardwareVersion +
        ", liteappSubscribeId=" + liteappSubscribeId +
        ", createDate=" + createDate +
        ", lastUpdateBy=" + lastUpdateBy +
        ", lastUpdateDate=" + lastUpdateDate +
        ", isValid=" + isValid +
        ", createBy=" + createBy +
        ", liteappGuid=" + liteappGuid +
        "}";
    }
}
