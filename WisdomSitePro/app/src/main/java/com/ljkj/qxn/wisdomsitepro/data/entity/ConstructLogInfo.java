package com.ljkj.qxn.wisdomsitepro.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：JiaJia
 * 创建时间：2018/3/9 15:14.
 * 功能描述：
 */

public class ConstructLogInfo {


    /**
     * 施工单位
     */
    private String construction_unit;

    /**
     * 日期
     */
    private String date;


    private String emergency;

    /**
     * 项目负责人
     */
    @SerializedName("projHead")
    private String pro_head;

    /**
     * 项目名称
     */
    private String pro_name;

    /**
     * 记录人
     */
    private String recorder;

    /**
     * 日志ID
     */
    private String id;


    /**
     * 施工日志编号
     */
    @SerializedName("projNo")
    private String pro_no;

    private String weather;
    private String wind;

    public String getConstruction_unit() {
        return construction_unit;
    }

    public void setConstruction_unit(String construction_unit) {
        this.construction_unit = construction_unit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getPro_head() {
        return pro_head;
    }

    public void setPro_head(String pro_head) {
        this.pro_head = pro_head;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }


    public String getCode() {
        return pro_no;
    }

    public void setCode(String code) {
        this.pro_no = code;
    }
}
