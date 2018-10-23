package com.ljkj.qxn.wisdomsitepro.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckDetail {

    /** 检查内容 */
    @SerializedName(value = "check", alternate = "qualityInspect")
    public Check check;

    @SerializedName(value = "reform", alternate = "reformList")
    public List<Reform> reforms;

    /** 检查内容 */
    public static class Check {

        public String id;

        /** 监理角色时使用的字段，查询文件 */
        public String checkId;

        /** 检查编号 */
        public String checkCode;

        /** 检查人员 */
        public String checkerName;

        /** 检查日期 */
        public String checkDate;

        /** 隐患等级 */
        public String reformGrade;

        /** 检查部位 */
        public String checkPosition;

        /** 整改类型 */
        public String reformType;

        /** 整改单位 */
        public String reformUnit;

        /** 隐患情况 */
        public String checkInfo;
    }

    /** 整改回复内容 */
    public static class Reform {
        /** 等待监理审核 */
        public static final int REFORM_STATUS1 = 1;
        /** 监理审核不合格 */
        public static final int REFORM_STATUS2 = 2;
        /** 监理审核通过,等待政府部门审核 */
        public static final int REFORM_STATUS3 = 3;
        /** 政府部门 审核不合格 */
        public static final int REFORM_STATUS4 = 4;
        /** 政府部门 审核合格 */
        public static final int REFORM_STATUS5 = 5;

        public String id;

        /** 监理角色时使用的字段，查询文件 */
        public String reformId;

        public String createTime;

        /** 是否制定措施 */
        public String isEncatFunc;

        /** 是否制定预案 */
        public String isEncatPlan;

        /** 整改情况 */
        public String reformInfo;

        /** 整改资金金额 */
        public String reformMoney;

        /** 施工单位整改人员 */
        @SerializedName(value = "reformer", alternate = "createUserName")
        public String reformer;

        /** 整改状态：1等待监理审核  2监理审核不合格  3、监理审核通过，等待政府部门审核 4、整改不合格 5合格 */
        public int reformStatus;

        /** 监理人 */
        public String supervisorHandler;

        /** 监理部门意见 */
        public String supervisorHandleInfo;

        /** 监理审查回复日期 */
        public String supervisorHandleDate;

        /** 政府监管机构审核人 */
        public String govAuditor;

        /** 监督机构意见 */
        public String govAuditInfo;

        /** 监督机构审查回复日期 */
        public String govAuditDate;
    }

}
