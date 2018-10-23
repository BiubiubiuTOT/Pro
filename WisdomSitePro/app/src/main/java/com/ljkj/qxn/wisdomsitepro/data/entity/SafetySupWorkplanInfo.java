package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：安全监督方案
 * 创建人：mhl
 * 创建时间：2018/3/13 13:52
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SafetySupWorkplanInfo {

    /**
     * 基础次数
     */
    private int jcnum;

    /**
     * 进度计划实施记录
     */
    private String plan;

    /**
     * 其他要求
     */
    private String qtyq;

    /**
     * 施工次数
     */
    private int sgnum;

    /**
     * 装饰次数
     */
    private int zsnum;

    public int getJcnum() {
        return jcnum;
    }

    public void setJcnum(int jcnum) {
        this.jcnum = jcnum;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getQtyq() {
        return qtyq;
    }

    public void setQtyq(String qtyq) {
        this.qtyq = qtyq;
    }

    public int getSgnum() {
        return sgnum;
    }

    public void setSgnum(int sgnum) {
        this.sgnum = sgnum;
    }

    public int getZsnum() {
        return zsnum;
    }

    public void setZsnum(int zsnum) {
        this.zsnum = zsnum;
    }
}
