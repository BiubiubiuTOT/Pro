package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.List;

public class ProjectEffectInfo {

    /**
     * id : 7731431718187630592
     * projId : 7730596820333821952
     * projCode : ZYJ-P001
     * status : 1
     * createTime : 1537527178000
     * createUserId : 7730596821558558720
     * createUserName : 张毓军
     * updateTime : 1538114576000
     * updateUserId : 7731299291440021504
     * updateUserName : 项目经理勿删
     * file : []
     */

    private String id;
    private String projId;
    private String projCode;
    private int status;
    private long createTime;
    private String createUserId;
    private String createUserName;
    private long updateTime;
    private String updateUserId;
    private String updateUserName;
    private List<?> file;

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

    public String getProjCode() {
        return projCode;
    }

    public void setProjCode(String projCode) {
        this.projCode = projCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
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

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
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

    public List<?> getFile() {
        return file;
    }

    public void setFile(List<?> file) {
        this.file = file;
    }
}
