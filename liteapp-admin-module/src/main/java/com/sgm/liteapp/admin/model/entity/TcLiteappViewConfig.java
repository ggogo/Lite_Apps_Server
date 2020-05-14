package com.sgm.liteapp.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 轻应用卡片配置
 * </p>
 *
 * @author 
 * @since 2020-04-21
 */
@TableName("TC_LITEAPP_VIEW_CONFIG")
public class TcLiteappViewConfig implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId("LITEAPP_VIEW_CONFIG_ID")
    private String liteappViewConfigId;

    @TableField("LITEAPP_CONFIG_ID")
    private String liteappConfigId;

    /**
     * 卡片类型 Widget | InfoFlow | DetailInfo | page
     */
    @TableField("VIEW_TYPE")
    private String viewType;

    @TableField("GUID")
    private String guid;

    @TableField("NAME")
    private String name;

    @TableField("FRIENDLY_NAME")
    private String friendlyName;

    @TableField("VERSION")
    private String version;

    @TableField("WIDTH")
    private String width;

    @TableField("HEIGHT")
    private String height;

    @TableField("SMALL_ICON")
    private String smallIcon;

    @TableField("BIG_ICON")
    private String bigIcon;

    @TableField("THUMBNAIL")
    private String thumbnail;

    @TableField("PATH")
    private String path;

    @TableField("DISPLAY")
    private String display;

    @TableField("SHOW_TYPE")
    private String showType;

    @TableField("CLOSE_STRATEGY")
    private String closeStrategy;

    /**
     * 更新人
     */
    @TableField("LAST_UPDATE_BY")
    private String lastUpdateBy;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    /**
     * 是否生效
     */
    @TableField("IS_VALID")
    private Integer isValid;

    /**
     * 创建人
     */
    @TableField("CREATE_BY")
    private String createBy;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("CREATE_DATE")
    private Date createDate;

    @TableField("LABEL")
    private String label;

    public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

    public String getLiteappViewConfigId() {
        return liteappViewConfigId;
    }

    public void setLiteappViewConfigId(String liteappViewConfigId) {
        this.liteappViewConfigId = liteappViewConfigId;
    }

    public String getLiteappConfigId() {
        return liteappConfigId;
    }

    public void setLiteappConfigId(String liteappConfigId) {
        this.liteappConfigId = liteappConfigId;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

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

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public String getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
    }

    public String getBigIcon() {
        return bigIcon;
    }

    public void setBigIcon(String bigIcon) {
        this.bigIcon = bigIcon;
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

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getCloseStrategy() {
        return closeStrategy;
    }

    public void setCloseStrategy(String closeStrategy) {
        this.closeStrategy = closeStrategy;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "TcLiteappViewConfig{" +
        "liteappViewConfigId=" + liteappViewConfigId +
        ", liteappConfigId=" + liteappConfigId +
        ", viewType=" + viewType +
        ", guid=" + guid +
        ", name=" + name +
        ", friendlyName=" + friendlyName +
        ", version=" + version +
        ", width=" + width +
        ", height=" + height +
        ", smallIcon=" + smallIcon +
        ", bigIcon=" + bigIcon +
        ", thumbnail=" + thumbnail +
        ", path=" + path +
        ", display=" + display +
        ", showType=" + showType +
        ", closeStrategy=" + closeStrategy +
        ", lastUpdateBy=" + lastUpdateBy +
        ", lastUpdateDate=" + lastUpdateDate +
        ", isValid=" + isValid +
        ", createBy=" + createBy +
        ", createDate=" + createDate +
        "}";
    }
}
