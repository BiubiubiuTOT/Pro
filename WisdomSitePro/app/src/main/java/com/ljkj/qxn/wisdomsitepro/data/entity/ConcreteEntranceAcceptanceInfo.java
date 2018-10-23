package com.ljkj.qxn.wisdomsitepro.data.entity;

import org.json.JSONArray;

/**
 * 商品混凝土进场验收实体
 * Created by lxx on 2018/3/10.
 */

public class ConcreteEntranceAcceptanceInfo extends BaseConcreteInfo {

    /**
     * id : 7729179135698862080
     * createTime : 2018-09-15 13:42:00
     * updateTime : null
     * status : 1
     * projId : 123
     * pouringPart : 2
     * concreteSupplier : 2
     * sceneConstructionPersonnel :
     * supervisorUnitPersonnel : null
     * sceneOperationPersonnel :
     * concreteType : shanghai
     * strengthGrade : beijing
     * pouringStartDate : 2018-09-10
     * pouringEndDate : null
     * coordinationCode : 2
     * weatherCondition :
     */

    private String id;
    private String createTime;
    private String updateTime;
    private int status;
    private String projId;
    private String pouringPart;//浇筑部位
    private String concreteSupplier;//混凝土供应商
    private String sceneConstructionPersonnel;//现场施工人员
    private String supervisorUnitPersonnel;//监理单位现场旁站人员
    private String sceneOperationPersonnel;//现场劳务操作人员
    private String concreteType;//混凝土类别
    private String strengthGrade;//强度等级
    private String pouringStartDate;//浇筑开始日期(yyyy-MM-dd)
    private String pouringEndDate;//浇筑结束日期(yyyy-MM-dd)
    private String coordinationCode;// 配合比编号
    private String weatherCondition;//天气情况

    private JSONArray file;

    public JSONArray getFile() {
        return file;
    }

    public void setFile(JSONArray file) {
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getPouringPart() {
        return pouringPart;
    }

    public void setPouringPart(String pouringPart) {
        this.pouringPart = pouringPart;
    }

    public String getConcreteSupplier() {
        return concreteSupplier;
    }

    public void setConcreteSupplier(String concreteSupplier) {
        this.concreteSupplier = concreteSupplier;
    }

    public String getSceneConstructionPersonnel() {
        return sceneConstructionPersonnel;
    }

    public void setSceneConstructionPersonnel(String sceneConstructionPersonnel) {
        this.sceneConstructionPersonnel = sceneConstructionPersonnel;
    }

    public String getSupervisorUnitPersonnel() {
        return supervisorUnitPersonnel;
    }

    public void setSupervisorUnitPersonnel(String supervisorUnitPersonnel) {
        this.supervisorUnitPersonnel = supervisorUnitPersonnel;
    }

    public String getSceneOperationPersonnel() {
        return sceneOperationPersonnel;
    }

    public void setSceneOperationPersonnel(String sceneOperationPersonnel) {
        this.sceneOperationPersonnel = sceneOperationPersonnel;
    }

    public String getConcreteType() {
        return concreteType;
    }

    public void setConcreteType(String concreteType) {
        this.concreteType = concreteType;
    }

    public String getStrengthGrade() {
        return strengthGrade;
    }

    public void setStrengthGrade(String strengthGrade) {
        this.strengthGrade = strengthGrade;
    }

    public String getPouringStartDate() {
        return pouringStartDate;
    }

    public void setPouringStartDate(String pouringStartDate) {
        this.pouringStartDate = pouringStartDate;
    }

    public String getPouringEndDate() {
        return pouringEndDate;
    }

    public void setPouringEndDate(String pouringEndDate) {
        this.pouringEndDate = pouringEndDate;
    }

    public String getCoordinationCode() {
        return coordinationCode;
    }

    public void setCoordinationCode(String coordinationCode) {
        this.coordinationCode = coordinationCode;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }
}
