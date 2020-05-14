package com.sgm.liteapp.cloudapi.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("Tc_User_Card_Config")
public class LiteAppUserCardEntity {
    private String userCardConfigId;
    private String idpUserId;
    private String cardGuid;
    private String cardVersion;
    private int sortNumber;
    private int display;

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

    public String getCardVersion() {
        return cardVersion;
    }

    public void setCardVersion(String cardVersion) {
        this.cardVersion = cardVersion;
    }

    public int getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(int sortNumber) {
        this.sortNumber = sortNumber;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }
}
