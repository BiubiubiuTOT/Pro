package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：巡检信息
 * 创建人：liufei
 * 创建时间：2018/3/12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class PatrolInfo {

    private String id;

    /**
     * 班组负责人
     */
    private String bzfzr;

    /**
     * 质检编号
     */
    private String code;

    /**
     * 按事故造成的人员伤亡或者直接经济损失分类
     */
    private String lossShow;

    /**
     * 状态:1创建，2评审，3重新整改，4，审批，5通过（状态历史：1、2状态是初始状态;3、4是未通过状态;5通过(以1234循环到5状态一个流程)）
     */
    private String status;

    /**
     * 检查日期
     */
    private String upTime;

    /**
     * 事故原因
     */
    private String whyShow;

    /**
     * 整改班组
     */
    private String zgbzShow;

    private String createBy;
    private String createDate;
    private String createName;
    private String file;
    private String fwr;
    private String gcmc;
    private String lossType;
    private String relId;
    private String swr;
    private String type;
    private String updateBy;
    private String updateDate;
    private String updateName;
    private String whyType;
    private String zgFile;
    private String zgNoThrough;
    private String zgbz;
    private String zgnr;

    public String getBzfzr() {
        return bzfzr;
    }

    public void setBzfzr(String bzfzr) {
        this.bzfzr = bzfzr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFwr() {
        return fwr;
    }

    public void setFwr(String fwr) {
        this.fwr = fwr;
    }

    public String getGcmc() {
        return gcmc;
    }

    public void setGcmc(String gcmc) {
        this.gcmc = gcmc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLossShow() {
        return lossShow;
    }

    public void setLossShow(String lossShow) {
        this.lossShow = lossShow;
    }

    public String getLossType() {
        return lossType;
    }

    public void setLossType(String lossType) {
        this.lossType = lossType;
    }

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSwr() {
        return swr;
    }

    public void setSwr(String swr) {
        this.swr = swr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getWhyShow() {
        return whyShow;
    }

    public void setWhyShow(String whyShow) {
        this.whyShow = whyShow;
    }

    public String getWhyType() {
        return whyType;
    }

    public void setWhyType(String whyType) {
        this.whyType = whyType;
    }

    public String getZgFile() {
        return zgFile;
    }

    public void setZgFile(String zgFile) {
        this.zgFile = zgFile;
    }

    public String getZgNoThrough() {
        return zgNoThrough;
    }

    public void setZgNoThrough(String zgNoThrough) {
        this.zgNoThrough = zgNoThrough;
    }

    public String getZgbz() {
        return zgbz;
    }

    public void setZgbz(String zgbz) {
        this.zgbz = zgbz;
    }

    public String getZgbzShow() {
        return zgbzShow;
    }

    public void setZgbzShow(String zgbzShow) {
        this.zgbzShow = zgbzShow;
    }

    public String getZgnr() {
        return zgnr;
    }

    public void setZgnr(String zgnr) {
        this.zgnr = zgnr;
    }

    @Override
    public String toString() {
        return "PatrolInfo{" +
                "id='" + id + '\'' +
                ", bzfzr='" + bzfzr + '\'' +
                ", code='" + code + '\'' +
                ", lossShow='" + lossShow + '\'' +
                ", status='" + status + '\'' +
                ", upTime='" + upTime + '\'' +
                ", whyShow='" + whyShow + '\'' +
                ", zgbzShow='" + zgbzShow + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate='" + createDate + '\'' +
                ", createName='" + createName + '\'' +
                ", file='" + file + '\'' +
                ", fwr='" + fwr + '\'' +
                ", gcmc='" + gcmc + '\'' +
                ", lossType='" + lossType + '\'' +
                ", relId='" + relId + '\'' +
                ", swr='" + swr + '\'' +
                ", type='" + type + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", updateName='" + updateName + '\'' +
                ", whyType='" + whyType + '\'' +
                ", zgFile='" + zgFile + '\'' +
                ", zgNoThrough='" + zgNoThrough + '\'' +
                ", zgbz='" + zgbz + '\'' +
                ", zgnr='" + zgnr + '\'' +
                '}';
    }
}
