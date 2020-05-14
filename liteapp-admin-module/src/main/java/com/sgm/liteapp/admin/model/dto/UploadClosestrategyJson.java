package com.sgm.liteapp.admin.model.dto;

import java.io.Serializable;
import java.util.List;

public class UploadClosestrategyJson implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9022655887329338964L;
	
	private String overtime;
	
	private List<String> strategy;

	public String getOvertime() {
		return overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

	public List<String> getStrategy() {
		return strategy;
	}

	public void setStrategy(List<String> strategy) {
		this.strategy = strategy;
	}
	
}