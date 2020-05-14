package com.sgm.liteapp.admin.model.dto;

import java.io.Serializable;

public class UploadSysactJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1829871695439311210L;
	
	private String touchstart;
	
	private String touchwidth;
	
	private String pagename;
	
	public String getTouchstart() {
		return touchstart;
	}
	public void setTouchstart(String touchstart) {
		this.touchstart = touchstart;
	}
	public String getTouchwidth() {
		return touchwidth;
	}
	public void setTouchwidth(String touchwidth) {
		this.touchwidth = touchwidth;
	}
	public String getPagename() {
		return pagename;
	}
	public void setPagename(String pagename) {
		this.pagename = pagename;
	}
	
	@Override
	public String toString() {
		return "UploadSysactInfo [touchstart=" + touchstart + ", touchwidth=" + touchwidth + ", pagename=" + pagename
				+ "]";
	}
	
	
}
