package com.ljkj.qxn.wisdomsitepro.data.entity;

import org.json.JSONArray;

public class SiteStationRecorderManageDetailInfo {

    /**
     * id : 7730693611549032448
     * projId : 7730596820333821952
     * name : dfg
     * constructionUnit : 梵蒂冈地方
     * supervisionUnit : 儿童未付
     * beginTime : 2018-09-04 00:00:00
     * endTime : 2018-09-21 00:00:00
     * process : rewr
     * position : f
     * qualityInspector : df
     * securityInspector : ere
     * specialOperators : 2
     * specialCertifiedNum : 2
     * machineEquipment : dfdsf
     * machineEquipmentNum : 1
     * materialAcceptanceInfo : fds
     * securityMeasuresInfo : rfsd
     * supervisionSamplingData : fds
     * constructionDesc : df
     * projRisk : fsdf
     * createUserId : 7730596821558558720
     * createUserName : 张毓军
     * createTime : 2018-09-19 18:00:00
     * updateUserId : null
     * updateUserName : null
     * updateTime : null
     * status : 1
     * file : null
     * flag : 1
     */

    private String id;
    private String projId;
    private String name;
    private String constructionUnit;//施工单位
    private String supervisionUnit;//监理单位
    private String beginTime;
    private String endTime;
    private String process;//工序
    private String position;//部位
    private String qualityInspector;//安检员
    private String securityInspector;//质检员
    private String specialOperators;//特种作业人员人数
    private String specialCertifiedNum;//特种作业人员持证数
    private String machineEquipment;//主要机械设备
    private String machineEquipmentNum;//机械设备数量
    private String materialAcceptanceInfo;//主要材料验收情况
    private String securityMeasuresInfo;// 安全技术措施落实情况
    private String supervisionSamplingData;//监理抽检数据情况
    private String constructionDesc;//施工过程简述
    private String projRisk;//工程质量安全问题或隐患
    private String createUserId;
    private String createUserName;
    private String createTime;
    private String updateUserId;
    private String updateUserName;
    private String updateTime;
    private int status;
    private JSONArray file;
    private int flag;//是否可撤回删除状态 1：可撤回/可删除；2：不可撤回/不可删除

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getSupervisionUnit() {
        return supervisionUnit;
    }

    public void setSupervisionUnit(String supervisionUnit) {
        this.supervisionUnit = supervisionUnit;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getQualityInspector() {
        return qualityInspector;
    }

    public void setQualityInspector(String qualityInspector) {
        this.qualityInspector = qualityInspector;
    }

    public String getSecurityInspector() {
        return securityInspector;
    }

    public void setSecurityInspector(String securityInspector) {
        this.securityInspector = securityInspector;
    }

    public String getSpecialOperators() {
        return specialOperators;
    }

    public void setSpecialOperators(String specialOperators) {
        this.specialOperators = specialOperators;
    }

    public String getSpecialCertifiedNum() {
        return specialCertifiedNum;
    }

    public void setSpecialCertifiedNum(String specialCertifiedNum) {
        this.specialCertifiedNum = specialCertifiedNum;
    }

    public String getMachineEquipment() {
        return machineEquipment;
    }

    public void setMachineEquipment(String machineEquipment) {
        this.machineEquipment = machineEquipment;
    }

    public String getMachineEquipmentNum() {
        return machineEquipmentNum;
    }

    public void setMachineEquipmentNum(String machineEquipmentNum) {
        this.machineEquipmentNum = machineEquipmentNum;
    }

    public String getMaterialAcceptanceInfo() {
        return materialAcceptanceInfo;
    }

    public void setMaterialAcceptanceInfo(String materialAcceptanceInfo) {
        this.materialAcceptanceInfo = materialAcceptanceInfo;
    }

    public String getSecurityMeasuresInfo() {
        return securityMeasuresInfo;
    }

    public void setSecurityMeasuresInfo(String securityMeasuresInfo) {
        this.securityMeasuresInfo = securityMeasuresInfo;
    }

    public String getSupervisionSamplingData() {
        return supervisionSamplingData;
    }

    public void setSupervisionSamplingData(String supervisionSamplingData) {
        this.supervisionSamplingData = supervisionSamplingData;
    }

    public String getConstructionDesc() {
        return constructionDesc;
    }

    public void setConstructionDesc(String constructionDesc) {
        this.constructionDesc = constructionDesc;
    }

    public String getProjRisk() {
        return projRisk;
    }

    public void setProjRisk(String projRisk) {
        this.projRisk = projRisk;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
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

    public JSONArray getFile() {
        return file;
    }

    public void setFile(JSONArray file) {
        this.file = file;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
