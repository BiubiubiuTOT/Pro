package com.ljkj.qxn.wisdomsitepro.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 类描述：项目列表
 * 创建人：rjf
 * 创建时间：2018/3/19 10:28.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class ProjectInfo {

    /**
     * 项目ID
     */
    private String id;

    /**
     * 项目名称
     */
    private String name;

    private String projectCode; //项目编码

    private String address; //项目地点

    @SerializedName("start_time")
    private String startTime; //项目开工日期

    @SerializedName("time")
    private String timeLimit;//工期

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Override
    public String toString() {
        return "ProjectListInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", startTime='" + startTime + '\'' +
                ", timeLimit='" + timeLimit + '\'' +
                '}';
    }
}
