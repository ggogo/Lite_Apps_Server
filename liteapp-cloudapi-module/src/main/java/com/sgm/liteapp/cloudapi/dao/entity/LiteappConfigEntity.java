package com.sgm.liteapp.cloudapi.dao.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("TC_LITEAPP_CONFIG")
public class LiteappConfigEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String liteappConfigId;

	private String liteappReleaseId;

	public String getLiteappConfigId() {
		return liteappConfigId;
	}

	public void setLiteappConfigId(String liteappConfigId) {
		this.liteappConfigId = liteappConfigId;
	}

	public String getLiteappReleaseId() {
		return liteappReleaseId;
	}

	public void setLiteappReleaseId(String liteappReleaseId) {
		this.liteappReleaseId = liteappReleaseId;
	}

}
