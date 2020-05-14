package com.sgm.liteapp.cloudapi.dto;

public class LiteAppUpdateListDTO {
    private String appGuid;
    private String latestVersion;

    public String getAppGuid() {
        return appGuid;
    }

    public void setAppGuid(String appGuid) {
        this.appGuid = appGuid;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }
}
