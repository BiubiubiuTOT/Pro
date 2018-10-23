package com.ljkj.qxn.wisdomsitepro.data.entity;

import org.json.JSONArray;

/**
 * 类描述：监理记录管理Info
 * 创建人：lxx
 * 创建时间：2018/8/30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SupervisorRecordManageInfo {

    /**
     * id : 7731325061453512704
     * projId : 7730596820333821952
     * name : erew
     * recordTime : 2018-09-21 00:00:00
     * supervisionUnit : 世界监理单位
     * supervisionType : null
     * recordInfo : null
     * createUserId : 7730596821558558720
     * createUserName : 张毓军
     * createTime : 2018-09-21 11:49:09
     * updateUserId : null
     * updateUserName : null
     * updateTime : null
     * status : 1
     * file : null
     * flag : 2
     */

    private String id;
    private String projId;
    private String name;
    private String recordTime;
    private String supervisionUnit;
    private int supervisionType;
    private String recordInfo;
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

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getSupervisionUnit() {
        return supervisionUnit;
    }

    public void setSupervisionUnit(String supervisionUnit) {
        this.supervisionUnit = supervisionUnit;
    }

    public int getSupervisionType() {
        return supervisionType;
    }

    public void setSupervisionType(int supervisionType) {
        this.supervisionType = supervisionType;
    }

    public String getRecordInfo() {
        return recordInfo;
    }

    public void setRecordInfo(String recordInfo) {
        this.recordInfo = recordInfo;
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
