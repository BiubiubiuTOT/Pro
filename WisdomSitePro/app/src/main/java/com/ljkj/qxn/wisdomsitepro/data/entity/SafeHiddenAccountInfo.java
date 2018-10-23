package com.ljkj.qxn.wisdomsitepro.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 类描述：安全隐患台账实体
 * 创建人：lxx
 * 创建时间：2018/7/11
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeHiddenAccountInfo {

    private String id;

    @SerializedName(value = "govAuditDate", alternate = "createTime")
    private String endDate; //销号时间

    @SerializedName("projName")
    private String projectName; //工程名称

    @SerializedName("accidentCause")
    private String accidentReason; //事故原因

    @SerializedName("personEconomicLoss")
    private String accidentType; //事故类型

    @SerializedName(value = "reformer", alternate = "teamManager")
    private String teamLeader; //整改负责人

    @SerializedName("checkInfo")
    private String hiddenDetail; //隐患详情


    @SerializedName("checkDate")
    private String checkDate; //检查日期

    @SerializedName(value = "reformDate", alternate = "endDate")
    private String upTime; //整改期限

    @SerializedName("reformMoney")
    private String money; //整改资金投入情况

    @SerializedName("isEncatFunc")
    private String measure; //是否制定措施

    @SerializedName("isEncatPlan")
    private String plan; //是否制定预案

    @SerializedName("reformGrade")
    private String hiddenLevel; //隐患等级

    public String getEndDate() {
        return endDate == null ? "" : endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAccidentReason() {
        return accidentReason;
    }

    public void setAccidentReason(String accidentReason) {
        this.accidentReason = accidentReason;
    }

    public String getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public String getHiddenDetail() {
        return hiddenDetail;
    }

    public void setHiddenDetail(String hiddenDetail) {
        this.hiddenDetail = hiddenDetail;
    }


    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getHiddenLevel() {
        return hiddenLevel;
    }

    public void setHiddenLevel(String hiddenLevel) {
        this.hiddenLevel = hiddenLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SafeHiddenAccountInfo{" +
                "endDate='" + endDate + '\'' +
                ", projectName='" + projectName + '\'' +
                ", accidentReason='" + accidentReason + '\'' +
                ", accidentType='" + accidentType + '\'' +
                ", teamLeader='" + teamLeader + '\'' +
                ", hiddenDetail='" + hiddenDetail + '\'' +
                ", checkDate='" + checkDate + '\'' +
                ", upTime='" + upTime + '\'' +
                ", money='" + money + '\'' +
                ", measure='" + measure + '\'' +
                ", plan='" + plan + '\'' +
                ", hiddenLevel='" + hiddenLevel + '\'' +
                '}';
    }
}
