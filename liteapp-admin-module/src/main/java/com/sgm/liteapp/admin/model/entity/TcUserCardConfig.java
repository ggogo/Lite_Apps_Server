package com.sgm.liteapp.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 卡片用户客制化设置

 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
@TableName("TC_USER_CARD_CONFIG")
public class TcUserCardConfig implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("USER_CARD_CONFIG_ID")
    private String userCardConfigId;

    @TableField("IDP_USER_ID")
    private String idpUserId;

    @TableField("CARD_GUID")
    private String cardGuid;

    @TableField("SORT_NUMBER")
    private Long sortNumber;

    @TableField("DISPLAY")
    private Integer display;

    @TableField("CARD_VERSION")
    private String cardVersion;


    public String getUserCardConfigId() {
        return userCardConfigId;
    }

    public void setUserCardConfigId(String userCardConfigId) {
        this.userCardConfigId = userCardConfigId;
    }

    public String getIdpUserId() {
        return idpUserId;
    }

    public void setIdpUserId(String idpUserId) {
        this.idpUserId = idpUserId;
    }

    public String getCardGuid() {
        return cardGuid;
    }

    public void setCardGuid(String cardGuid) {
        this.cardGuid = cardGuid;
    }

    public Long getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Long sortNumber) {
        this.sortNumber = sortNumber;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public String getCardVersion() {
        return cardVersion;
    }

    public void setCardVersion(String cardVersion) {
        this.cardVersion = cardVersion;
    }

    @Override
    public String toString() {
        return "TcUserCardConfig{" +
        "userCardConfigId=" + userCardConfigId +
        ", idpUserId=" + idpUserId +
        ", cardGuid=" + cardGuid +
        ", sortNumber=" + sortNumber +
        ", display=" + display +
        ", cardVersion=" + cardVersion +
        "}";
    }
}
