package com.sgm.liteapp.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订阅历史
 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
@TableName("TL_LITEAPP_SUBSCRIBE_LOG")
public class TlLiteappSubscribeLog implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("LITEAPP_SUBSCRIBE_LOG_ID")
    private String liteappSubscribeLogId;

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

    /**
     * 订阅或者取消订阅
     */
    @TableField("ACTION_TYPE")
    private String actionType;

    /**
     * 发生时间
     */
    @TableField("ACTION_DATE")
    private Date actionDate;

    /**
     * 分区标识，按照yyyymmdd分区
     */
    @TableField("PARTITION_FLAG")
    private Integer partitionFlag;

    @TableField("LITEAPP_GUID")
    private String liteappGuidId;

    @TableField("LITEAPP_VERSION")
    private String liteappVersion;


    public String getLiteappSubscribeLogId() {
        return liteappSubscribeLogId;
    }

    public void setLiteappSubscribeLogId(String liteappSubscribeLogId) {
        this.liteappSubscribeLogId = liteappSubscribeLogId;
    }

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

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public Integer getPartitionFlag() {
        return partitionFlag;
    }

    public void setPartitionFlag(Integer partitionFlag) {
        this.partitionFlag = partitionFlag;
    }

    public String getLiteappGuidId() {
        return liteappGuidId;
    }

    public void setLiteappGuidId(String liteappGuidId) {
        this.liteappGuidId = liteappGuidId;
    }

    public String getLiteappVersion() {
        return liteappVersion;
    }

    public void setLiteappVersion(String liteappVersion) {
        this.liteappVersion = liteappVersion;
    }

    @Override
    public String toString() {
        return "TlLiteappSubscribeLog{" +
        "liteappSubscribeLogId=" + liteappSubscribeLogId +
        ", idpUserId=" + idpUserId +
        ", oemCode=" + oemCode +
        ", softwareCode=" + softwareCode +
        ", hardwareCode=" + hardwareCode +
        ", softwareVersion=" + softwareVersion +
        ", hardwareVersion=" + hardwareVersion +
        ", actionType=" + actionType +
        ", actionDate=" + actionDate +
        ", partitionFlag=" + partitionFlag +
        ", liteappGuidId=" + liteappGuidId +
        ", liteappVersion=" + liteappVersion +
        "}";
    }
}
