package com.sgm.liteapp.admin.model.dto;

import org.springframework.util.StringUtils;

public class PageDto {

	private Integer pageNo;
	private Integer size;
	private String sortName;
	private String sort;

	public Integer getPageNo() {
		return pageNo == null ? 1 : pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getSize() {
		return size == null ? 10 : size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSort() {
		if(StringUtils.isEmpty(sort)) {
			sort = "DESC";
		}
		return sort.toUpperCase();
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
