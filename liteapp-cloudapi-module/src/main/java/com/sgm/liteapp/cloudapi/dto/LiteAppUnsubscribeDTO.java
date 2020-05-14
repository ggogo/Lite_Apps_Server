package com.sgm.liteapp.cloudapi.dto;

public class LiteAppUnsubscribeDTO extends LiteAppBaseDTO{

    private StoreLiteappConfigDTO[] appList;

    public StoreLiteappConfigDTO[] getAppList() {
        return appList;
    }

    public void setAppList(StoreLiteappConfigDTO[] appList) {
        this.appList = appList;
    }
}
