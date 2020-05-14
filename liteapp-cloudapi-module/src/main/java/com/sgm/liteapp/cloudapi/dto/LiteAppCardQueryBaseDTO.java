package com.sgm.liteapp.cloudapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LiteAppCardQueryBaseDTO extends LiteAppCardBaseDTO {

	@JsonProperty(value= "switch")
	private String switchStr;

	public String getSwitchStr() {
		return switchStr;
	}

	public void setSwitchStr(String switchStr) {
		this.switchStr = switchStr;
	}
}
