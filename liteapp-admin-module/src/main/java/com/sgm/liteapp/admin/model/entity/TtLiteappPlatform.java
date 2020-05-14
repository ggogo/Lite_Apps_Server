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
 * 轻应用支持的软硬件平台
 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
@TableName("TT_LITEAPP_PLATFORM")
public class TtLiteappPlatform implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("LITEAPP_PLATFORM_ID")
    private String liteappPlatformId;

    /**
     * 主键
     */
    @TableField("LITEAPP_RELEASE_ID")
    private String liteappReleaseId;

	/*
	 * @TableField("CODE_ID") private String codeId;
	 */

    /**
     * 创建人
     */
    @TableField("CREATE_BY")
    private String createBy;

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
    
    @TableField("OEM_CODE")
    private String oemCode;

    @TableField("SOFTWARE_CODE")
    private String softwareCode;

    @TableField("HARDWARE_CODE")
    private String hardwareCode;
    
    @TableField("IS_SUBSCRIBE")
    private String isSubscribe;
   

	public String getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
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

	public String getLiteappPlatformId() {
        return liteappPlatformId;
    }

    public void setLiteappPlatformId(String liteappPlatformId) {
        this.liteappPlatformId = liteappPlatformId;
    }

    public String getLiteappReleaseId() {
        return liteappReleaseId;
    }

    public void setLiteappReleaseId(String liteappReleaseId) {
        this.liteappReleaseId = liteappReleaseId;
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

	@Override
	public String toString() {
		return "TtLiteappPlatform [liteappPlatformId=" + liteappPlatformId + ", liteappReleaseId=" + liteappReleaseId
				+ ", createBy=" + createBy + ", createDate=" + createDate + ", lastUpdateBy=" + lastUpdateBy
				+ ", lastUpdateDate=" + lastUpdateDate + ", isValid=" + isValid + ", oemCode=" + oemCode
				+ ", softwareCode=" + softwareCode + ", hardwareCode=" + hardwareCode + ", isSubscribe="
				+ isSubscribe + "]";
	}
}
