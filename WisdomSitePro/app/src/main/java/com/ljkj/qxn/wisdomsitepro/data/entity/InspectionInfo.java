package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.List;

/**
 * 类描述：巡检详情
 * 创建人：mhl
 * 创建时间：2018/3/19 9:41
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class InspectionInfo {


    /**
     * 质检编号
     */
    private String code;

    /**
     * 发文人
     */
    private String fwr;

    /**
     * 工程名称
     */
    private String gcmc;

    /**
     * id
     */
    private String id;

    /**
     * 事故造成的人员伤亡或者直接经济损失分类
     */
    private String lossType;

    /**
     * 检查日期
     */
    private String upTime;

    /**
     * 事故发生的原因分类
     */
    private String whyType;

    /**
     * 整改详情
     */
    private String zgDetail;

    /**
     * 不通过原因
     */
    private String zgNoThrough;

    /**
     * 整改班组
     */
    private String zgbz;

    /**
     * 整改内容
     */
    private String zgnr;


    /////////////////////////////分割线/////////////////////////
    public Check check;
    public List<Reform> reforms;

    public static final class Check {

        public String id;

        /**
         *
         */
        public String bzfzrShow;

        /**
         * 安、质检编号
         */
        public String code;

        /**
         * 发文人
         */
        public String fwr;

        /**
         * 工程名称
         */
        public String gcmc;

        /**
         * 事故造成的人员伤亡或者直接经济损失分类
         */
        public String lossShow;

        /**
         * 状态
         */
        public String status;

        /**
         * 类型 2安全；3质量
         */
        public String type;


        /**
         * 检查日期
         */
        public String upTime;

        /**
         * 事故发生的原因分类
         */
        public String whyShow;

        /**
         * 整改班组
         */
        public String zgbz;

        /**
         * 整改内容
         */
        public String zgnr;


        public String zgbzShow;

        public String bzfzr;
    }

    public static final class Reform {
        /**
         * check的id
         */
        public String bizId;


        public String createDate;

        public String id;

        /**
         * 不通过原因
         */
        public String noThroughReason;

        /**
         * 发文人
         */
        public String postMan;

        /**
         * 整改详情
         */
        public String reformDetail;

        /**
         * 整改结果	整改结果: 0合格、不合格
         */
        public String reformResult;


        /**
         * 状态 1创建，2评审，3重新整改，4，审批，5通过（状态历史：1、2状态是初始状态;3、4是未通过状态;5通过(以1234循环到5状态一个流程)）
         */
        public String statusHistory;

        /**
         * 班组负责人
         */
        public String teamLeader;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getLossType() {
        return lossType;
    }

    public void setLossType(String lossType) {
        this.lossType = lossType;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getWhyType() {
        return whyType;
    }

    public void setWhyType(String whyType) {
        this.whyType = whyType;
    }

    public String getZgDetail() {
        return zgDetail;
    }

    public void setZgDetail(String zgDetail) {
        this.zgDetail = zgDetail;
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

    public String getZgnr() {
        return zgnr;
    }

    public void setZgnr(String zgnr) {
        this.zgnr = zgnr;
    }
}
