package com.sgm.liteapp.commons.cms;

import java.util.List;

public class SecuredFileURLs {

	private String errcode;

	private String errmsg;

	private List<FileURL> data;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public List<FileURL> getData() {
		return data;
	}

	public void setData(List<FileURL> data) {
		this.data = data;
	}

	public static final class FileURL {

		private String key;

		private String url;

		private String cnameUrl;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getCnameUrl() {
			return cnameUrl;
		}

		public void setCnameUrl(String cnameUrl) {
			this.cnameUrl = cnameUrl;
		}

	}
}
