package com.sgm.liteapp.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 车机预装APP
 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
@TableName("TT_BUILDIN_LITEAPP")
public class TtBuildinLiteapp implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("BUILDIT_LITEAPP_ID")
    private String builditLiteappId;

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

    /**
     * 创建时间
     */
    @TableField("CREATE_DATE")
    private Date createDate;

    @TableField("LITEAPP_GUID")
    private String liteappGuidId;

    /**
     * 预装名称，例如xx车型车系预装
     */
    @TableField("BUILDIN_NAME")
    private String buildinName;


    public String getBuilditLiteappId() {
        return builditLiteappId;
    }

    public void setBuilditLiteappId(String builditLiteappId) {
        this.builditLiteappId = builditLiteappId;
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

    public String getBuildinName() {
        return buildinName;
    }

    public void setBuildinName(String buildinName) {
        this.buildinName = buildinName;
    }

    @Override
    public String toString() {
        return "TtBuildinLiteapp{" +
        "builditLiteappId=" + builditLiteappId +
        ", oemCode=" + oemCode +
        ", softwareCode=" + softwareCode +
        ", hardwareCode=" + hardwareCode +
        ", softwareVersion=" + softwareVersion +
        ", hardwareVersion=" + hardwareVersion +
        ", lastUpdateBy=" + lastUpdateBy +
        ", lastUpdateDate=" + lastUpdateDate +
        ", isValid=" + isValid +
        ", createBy=" + createBy +
        ", createDate=" + createDate +
        ", liteappGuidId=" + liteappGuidId +
        ", buildinName=" + buildinName +
        "}";
    }
}
