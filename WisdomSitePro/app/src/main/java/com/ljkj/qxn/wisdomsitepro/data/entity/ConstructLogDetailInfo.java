package com.ljkj.qxn.wisdomsitepro.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 类描述：施工日志详情
 * 创建人：rjf
 * 创建时间：2018/3/15 09:20.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class ConstructLogDetailInfo {

    /**
     * 施工单位
     */
    private String constructionUnit;
    private String createBy;


    private long createDate;

    private String createName;

    /**
     * 日期
     */
    @SerializedName("date")
    private String date;

    /**
     * 突发事件
     */
    private String emergency;

    private String fileId;

    private String id;

    /**
     * 最高/最低温度
     */
    private String maxMinTemp;

    /**
     * 项目负责人
     */
    @SerializedName("projHead")
    private String proHead;


    /**
     * 项目ID
     */
    private String proId;

    /**
     * 项目名称
     */
    @SerializedName("projName")
    private String proName;

    /**
     * 项目编号
     */
    @SerializedName("projNo")
    private String proNo;

    /**
     * 生产情况记录
     */
    private String production;

    /**
     * 技术质量安全工作记录
     */
    private String qualitySafety;

    /**
     * 记录人
     */
    private String recorder;

    private String remark;

    private String updateBy;


    private String updateName;

    /**
     * 天气情况
     */
    private String weather;

    /**
     * 风力
     */
    private String wind;

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
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

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaxMinTemp() {
        return maxMinTemp;
    }

    public void setMaxMinTemp(String maxMinTemp) {
        this.maxMinTemp = maxMinTemp;
    }

    public String getProHead() {
        return proHead;
    }

    public void setProHead(String proHead) {
        this.proHead = proHead;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProNo() {
        return proNo;
    }

    public void setProNo(String proNo) {
        this.proNo = proNo;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getQualitySafety() {
        return qualitySafety;
    }

    public void setQualitySafety(String qualitySafety) {
        this.qualitySafety = qualitySafety;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
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
}
