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
 * 
 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
@TableName("TM_CODE")
public class TmCode implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("CODE_ID")
    private String codeId;

    /**
     * 代码值
     */
    @TableField("CODE_VALUE")
    private String codeValue;

    /**
     * 代码类型，H 硬件编码 S 软件编码 O OEM编码
     */
    @TableField("CODE_TYPE")
    private String codeType;

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


    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
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
        return "TmCode{" +
        "codeId=" + codeId +
        ", codeValue=" + codeValue +
        ", codeType=" + codeType +
        ", isValid=" + isValid +
        ", createBy=" + createBy +
        ", createDate=" + createDate +
        ", lastUpdateBy=" + lastUpdateBy +
        ", lastUpdateDate=" + lastUpdateDate +
        "}";
    }
}
