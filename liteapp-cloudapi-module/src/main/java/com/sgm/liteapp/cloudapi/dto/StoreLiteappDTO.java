package com.sgm.liteapp.cloudapi.dto;

/**
 * 轻应用基础信息
 * 
 * @author liup
 *
 */
public class StoreLiteappDTO {

	private int buildin;

	private int isAssociate;

	private String appconfig;

	private String appcardURL;

	private String showPackageURL;

	private String showinfoflowcardsURL;

	private StoreLiteappConfigDTO configuration;

	public int getBuildin() {
		return buildin;
	}

	public void setBuildin(int buildin) {
		this.buildin = buildin;
	}

	public String getAppconfig() {
		return appconfig;
	}

	public void setAppconfig(String appconfig) {
		this.appconfig = appconfig;
	}

	public String getAppcardURL() {
		return appcardURL;
	}

	public void setAppcardURL(String appcardURL) {
		this.appcardURL = appcardURL;
	}

	public String getShowPackageURL() {
		return showPackageURL;
	}

	public void setShowPackageURL(String showPackageURL) {
		this.showPackageURL = showPackageURL;
	}

	public String getShowinfoflowcardsURL() {
		return showinfoflowcardsURL;
	}

	public void setShowinfoflowcardsURL(String showinfoflowcardsURL) {
		this.showinfoflowcardsURL = showinfoflowcardsURL;
	}

	public StoreLiteappConfigDTO getConfiguration() {
		return configuration;
	}

	public void setConfiguration(StoreLiteappConfigDTO configuration) {
		this.configuration = configuration;
	}

	public int getIsAssociate() {
		return isAssociate;
	}

	public void setIsAssociate(int isAssociate) {
		this.isAssociate = isAssociate;
	}

}
