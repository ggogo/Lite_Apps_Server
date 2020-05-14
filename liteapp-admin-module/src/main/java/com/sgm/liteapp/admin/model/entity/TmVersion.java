package com.sgm.liteapp.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
@TableName("TM_VERSION")
public class TmVersion implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId("VERSION_ID")
    private String versionId;

    /**
     * 版本号
     */
    @TableField("VERSION_NUMBER")
    private String versionNumber;

    /**
     * 发布说明
     */
    @TableField("RELEASE_NOTE")
    private String releaseNote;

    /**
     * 版本类型, S 软件版本 H硬件版本
     */
    @TableField("VERSION_TYPE")
    private String versionType;

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


    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getReleaseNote() {
        return releaseNote;
    }

    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote;
    }

    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType;
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

    @Override
    public String toString() {
        return "TmVersion{" +
        "versionId=" + versionId +
        ", versionNumber=" + versionNumber +
        ", releaseNote=" + releaseNote +
        ", versionType=" + versionType +
        ", isValid=" + isValid +
        ", createBy=" + createBy +
        ", createDate=" + createDate +
        ", lastUpdateBy=" + lastUpdateBy +
        ", lastUpdateDate=" + lastUpdateDate +
        "}";
    }
}
