package com.sgm.liteapp.cloudapi.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 轻应用配置详细信息
 * 
 * @author liup
 *
 */
public class StoreLiteappConfigDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String friendlyname;

	private String infoicon;

	private String intor;

	private String version;

	private String applevel;

	private String apilevel;

	private String apptype;

	private String guid;

	private String shortcut;

	private Icon appicon;

	private Platforminfo platforminfo;

	private List<View> views;

	private List<Infoflowview> infoflowviews;

	private List<Page> pages;

	@JsonIgnore
	private String packageUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFriendlyname() {
		return friendlyname;
	}

	public void setFriendlyname(String friendlyname) {
		this.friendlyname = friendlyname;
	}

	public String getInfoicon() {
		return infoicon;
	}

	public void setInfoicon(String infoicon) {
		this.infoicon = infoicon;
	}

	public String getIntor() {
		return intor;
	}

	public void setIntor(String intor) {
		this.intor = intor;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getApplevel() {
		return applevel;
	}

	public void setApplevel(String applevel) {
		this.applevel = applevel;
	}

	public String getApilevel() {
		return apilevel;
	}

	public void setApilevel(String apilevel) {
		this.apilevel = apilevel;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	public String getPackageUrl() {
		return packageUrl;
	}

	public void setPackageUrl(String packageUrl) {
		this.packageUrl = packageUrl;
	}

	public Icon getAppicon() {
		return appicon;
	}

	public void setAppicon(Icon appicon) {
		this.appicon = appicon;
	}

	public Platforminfo getPlatforminfo() {
		return platforminfo;
	}

	public void setPlatforminfo(Platforminfo platforminfo) {
		this.platforminfo = platforminfo;
	}

	public List<View> getViews() {
		return views;
	}

	public void setViews(List<View> views) {
		this.views = views;
	}

	public List<Infoflowview> getInfoflowviews() {
		return infoflowviews;
	}

	public void setInfoflowviews(List<Infoflowview> infoflowviews) {
		this.infoflowviews = infoflowviews;
	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public static final class Icon implements Serializable {

		private static final long serialVersionUID = 1L;

		private String big;

		private String small;

		public String getBig() {
			return big;
		}

		public void setBig(String big) {
			this.big = big;
		}

		public String getSmall() {
			return small;
		}

		public void setSmall(String small) {
			this.small = small;
		}

	}

	public static final class Platforminfo implements Serializable {

		private static final long serialVersionUID = 1L;

		private String hardwareversion;

		private String softwareversion;

		public String getHardwareversion() {
			return hardwareversion;
		}

		public void setHardwareversion(String hardwareversion) {
			this.hardwareversion = hardwareversion;
		}

		public String getSoftwareversion() {
			return softwareversion;
		}

		public void setSoftwareversion(String softwareversion) {
			this.softwareversion = softwareversion;
		}

	}

	public static final class View implements Serializable {

		private static final long serialVersionUID = 1L;

		private String guid;

		private String name;

		private String friendlyname;

		private String version;

		private String type;

		private String width;

		private String height;

		private Icon icon;

		private String thumbnail;

		private String path;

		private String display;

		public String getGuid() {
			return guid;
		}

		public void setGuid(String guid) {
			this.guid = guid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFriendlyname() {
			return friendlyname;
		}

		public void setFriendlyname(String friendlyname) {
			this.friendlyname = friendlyname;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getWidth() {
			return width;
		}

		public void setWidth(String width) {
			this.width = width;
		}

		public String getHeight() {
			return height;
		}

		public void setHeight(String height) {
			this.height = height;
		}

		public Icon getIcon() {
			return icon;
		}

		public void setIcon(Icon icon) {
			this.icon = icon;
		}

		public String getThumbnail() {
			return thumbnail;
		}

		public void setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getDisplay() {
			return display;
		}

		public void setDisplay(String display) {
			this.display = display;
		}

	}

	public static final class Infoflowview implements Serializable {

		private static final long serialVersionUID = 1L;

		private String guid;

		private String name;

		private String friendlyname;

		private String version;

		private String showtype;

		private String path;

		private String closestrategy;

		public String getGuid() {
			return guid;
		}

		public void setGuid(String guid) {
			this.guid = guid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFriendlyname() {
			return friendlyname;
		}

		public void setFriendlyname(String friendlyname) {
			this.friendlyname = friendlyname;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getShowtype() {
			return showtype;
		}

		public void setShowtype(String showtype) {
			this.showtype = showtype;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getClosestrategy() {
			return closestrategy;
		}

		public void setClosestrategy(String closestrategy) {
			this.closestrategy = closestrategy;
		}

	}

	public static final class Page implements Serializable {

		private static final long serialVersionUID = 1L;

		private String guid;

		private String name;

		private String friendlyname;

		private String version;

		private String path;

		public String getGuid() {
			return guid;
		}

		public void setGuid(String guid) {
			this.guid = guid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFriendlyname() {
			return friendlyname;
		}

		public void setFriendlyname(String friendlyname) {
			this.friendlyname = friendlyname;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

	}
}
