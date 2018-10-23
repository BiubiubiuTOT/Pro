package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：施工现场
 * 创建人：liufei
 * 创建时间：2018/3/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ConstructionSiteInfo {

    /**
     * id : 7730703724630310912
     * projId : 7730596820333821952
     * projCode : ZYJ-P001
     * status : 1
     * createTime : 1537353611000
     * createUserId : 7730596821558558720
     * createUserName : 张毓军
     * updateTime : 1537925151000
     * updateUserId : 7731299291440021504
     * updateUserName : 项目经理勿删
     * file : null
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
    private Object file;

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

    public Object getFile() {
        return file;
    }

    public void setFile(Object file) {
        this.file = file;
    }
}
