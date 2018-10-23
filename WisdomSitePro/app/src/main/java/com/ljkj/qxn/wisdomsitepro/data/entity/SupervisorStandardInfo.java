package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.io.Serializable;

/**
 * 类描述：监理标准规范实体
 * 创建人：lxx
 * 创建时间：2018/9/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SupervisorStandardInfo implements Serializable {

    /**
     * id : 7731323797936209920
     * projId : 7730596820333821952
     * name : Koala.jpg
     * type : 1
     * createUserId : 7730596821558558720
     * createUserName : 张毓军
     * createTime : 2018-09-21 11:44:08
     * updateUserId : null
     * updateUserName : null
     * updateTime : null
     * status : 1
     * file : null
     */

    private String id;
    private String projId;
    private String name;
    private int type;
    private String createUserId;
    private String createUserName;
    private String createTime;
    private String updateUserId;
    private String updateUserName;
    private String updateTime;
    private int status;
    private String file;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
