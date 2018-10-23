package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：旁站记录管理实体
 * 创建人：lxx
 * 创建时间：2018/8/29
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SiteStationRecorderManageInfo {
    /**
     * id : 7730693611549032448
     * projId : 7730596820333821952
     * name : dfg
     * constructionUnit : 梵蒂冈地方
     * supervisionUnit : 儿童未付
     * beginTime : 2018-09-04 00:00
     * endTime : 2018-09-21 00:00
     * createUserName : 张毓军
     * createTime : 2018-09-19 18:00:00
     * flag : 1
     */

    private String id;
    private String projId;
    private String name;
    private String constructionUnit;
    private String supervisionUnit;
    private String beginTime;
    private String endTime;
    private String createUserName;
    private String createTime;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
