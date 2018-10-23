package com.ljkj.qxn.wisdomsitepro.data.entity;


import com.google.gson.annotations.SerializedName;
import com.ljkj.qxn.wisdomsitepro.data.CheckableEntity;

/**
 * 类描述：视频源信息
 * 创建人：lxx
 * 创建时间：2018/4/20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class VideoSourceInfo implements CheckableEntity {
    private String id;

    @SerializedName("typeName")
    private String areaName;

    /**
     * 视频对应名称
     */
    private String name;

    /**
     * 视频源路径
     */
    private String sourceUrl;

    private boolean checked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "VideoSourceInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                '}';
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
