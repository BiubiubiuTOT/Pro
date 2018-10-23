package com.ljkj.qxn.wisdomsitepro.data.entity;

import org.json.JSONArray;

public class InspectorRecorderManageInfo {

    /**
     * id : 7730703981311361024
     * projId : 7730596820333821952
     * name : cfef
     * supervisionUnit : ewrew
     * patrolTime : 2018-09-13
     * patrolScope : rdf
     * projWorkerTech : dfghth
     * recordData : treta
     * createUserId : 123
     * createUserName : 笑笑
     * createTime : 2018-09-19 18:41:12
     * updateUserId : null
     * updateUserName : null
     * updateTime : 2018-09-21 09:46:44
     * status : 1
     * file : null
     * flag : 2
     */

    private String id;
    private String projId;
    private String name;
    private String supervisionUnit;//监理单位 
    private String patrolTime;//巡视时间
    private String patrolScope;//巡视范围/部位/工序 ,
    private String projWorkerTech;//施工项目/人员到位/工艺合规性 ,
    private String recordData;//巡视记录数据
    private String createUserId;
    private String createUserName;
    private String createTime;
    private String updateUserId;
    private String updateUserName;
    private String updateTime;
    private int status;
    private JSONArray file;
    private int flag;

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

    public String getSupervisionUnit() {
        return supervisionUnit;
    }

    public void setSupervisionUnit(String supervisionUnit) {
        this.supervisionUnit = supervisionUnit;
    }

    public String getPatrolTime() {
        return patrolTime;
    }

    public void setPatrolTime(String patrolTime) {
        this.patrolTime = patrolTime;
    }

    public String getPatrolScope() {
        return patrolScope;
    }

    public void setPatrolScope(String patrolScope) {
        this.patrolScope = patrolScope;
    }

    public String getProjWorkerTech() {
        return projWorkerTech;
    }

    public void setProjWorkerTech(String projWorkerTech) {
        this.projWorkerTech = projWorkerTech;
    }

    public String getRecordData() {
        return recordData;
    }

    public void setRecordData(String recordData) {
        this.recordData = recordData;
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
