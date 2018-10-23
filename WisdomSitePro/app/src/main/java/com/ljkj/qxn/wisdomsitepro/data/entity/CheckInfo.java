package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：检查实体
 * 创建人：lxx
 * 创建时间：2018/9/5.
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CheckInfo {
    /** 待整改(立即整改) */
    public static final int WAIT_RECTIFY = 1;
    /** 待审核 */
    public static final int WAIT_AUDITED = 2;
    /** 待整改(重新整改) */
    public static final int RE_RECTIFY = 3;
    /** 合格 */
    public static final int QUALIFIED = 4;

    /** 检查id */
    public String id;

    /** 检查工程部位 */
    public String checkPosition;

    /** 检查机构 */
    public String checkUnit;

    /** 检查人员职务 */
    public String checkerDuty;

    /** 整改期限 */
    public String reformDate;

    /** 检查编号 */
    public String checkCode;

    /** 隐患等级 */
    public String reformGrade;

    /** 整改类型 */
    public String reformType;

    /** 检查人员姓名 */
    public String checkerName;

    /** 检查日期 */
    public String checkDate;

    /** 整改状态: 1.待整改 2.待审核 3.重新整改 4.合格 */
    public int reformStatus;


    /** 监理角色字段： 0：待处理、1：待核查、2：合格  3：待处理（待项目方重新整改） */
    public int handleType;

}
