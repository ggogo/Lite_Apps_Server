package com.sgm.liteapp.cloudapi.dto;

public class LiteAppCardQueryDTO extends LiteAppCardQueryBaseDTO {

    private String idpUserId;

    private LiteAppCardQueryBaseDTO[] display;

    public String getIdpUserId() {
        return idpUserId;
    }

    public void setIdpUserId(String idpUserId) {
        this.idpUserId = idpUserId;
    }

    public LiteAppCardQueryBaseDTO[] getDisplay() {
        return display;
    }

    public void setDisplay(LiteAppCardQueryBaseDTO[] display) {
        this.display = display;
    }
}
