package com.sgm.liteapp.cloudapi.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("Tl_Liteapp_Subscribe_Log")
public class LiteappSubscribeLogEntity extends LiteappSubscribeEntity{
    private static final long serialVersionUID = 1L;
    private String liteAppSubscribeLogId;
    private String actionType;
    private Date actionDate;
    private Integer partitionFlag;

    public String getLiteAppSubscribeLogId() {
        return liteAppSubscribeLogId;
    }

    public void setLiteAppSubscribeLogId(String liteAppSubscribeLogId) {
        this.liteAppSubscribeLogId = liteAppSubscribeLogId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public Integer getPartitionFlag() {
        return partitionFlag;
    }

    public void setPartitionFlag(Integer partitionFlag) {
        this.partitionFlag = partitionFlag;
    }
}
