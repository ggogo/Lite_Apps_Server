package com.sgm.liteapp.cloudapi.dto;

public class LiteAppCardDisplayDTO extends LiteAppCardBaseDTO{


    private String appGuid;
    private String appVersion;
    private Integer display;


    public String getAppGuid() {
        return appGuid;
    }

    public void setAppGuid(String appGuid) {
        this.appGuid = appGuid;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

}
