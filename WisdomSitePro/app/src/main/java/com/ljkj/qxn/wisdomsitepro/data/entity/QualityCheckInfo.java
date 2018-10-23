package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：质量检查实体
 * 创建人：lxx
 * 创建时间：2018/3/9.
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class QualityCheckInfo {

    private String id;

    /**
     * 处理日期
     */
    private String fkrqTime;

    /**
     * 检查日期
     */
    private String jcrqTime;

    /**
     * 检查人
     */
    private String jcry;

    /**
     * 是否审核
     */
    private String sfsh;

    /**
     * 隐患等级
     */
    private String yhdj;

    /**
     * 整改类型
     */
    private String zglx;

    /**
     * 处理状态
     */
    private String clzt;


    /**
     * 检查编号
     */
    private String checkNo;

    /** 整改期限 */
    private String zgqx;

    public String getFkrqTime() {
        return fkrqTime;
    }

    public void setFkrqTime(String fkrqTime) {
        this.fkrqTime = fkrqTime;
    }

    public String getJcrqTime() {
        return jcrqTime;
    }

    public void setJcrqTime(String jcrqTime) {
        this.jcrqTime = jcrqTime;
    }

    public String getJcry() {
        return jcry;
    }

    public void setJcry(String jcry) {
        this.jcry = jcry;
    }

    public String getSfsh() {
        return sfsh;
    }

    public void setSfsh(String sfsh) {
        this.sfsh = sfsh;
    }

    public String getYhdj() {
        return yhdj;
    }

    public void setYhdj(String yhdj) {
        this.yhdj = yhdj;
    }

    public String getZglx() {
        return zglx;
    }

    public void setZglx(String zglx) {
        this.zglx = zglx;
    }


    public String getClzt() {
        return clzt;
    }

    public void setClzt(String clzt) {
        this.clzt = clzt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
    }

    public String getZgqx() {
        return zgqx;
    }

    public void setZgqx(String zgqx) {
        this.zgqx = zgqx;
    }

    @Override
    public String toString() {
        return "QualityCheckInfo{" +
                "fkrqTime='" + fkrqTime + '\'' +
                ", jcrqTime='" + jcrqTime + '\'' +
                ", jcry='" + jcry + '\'' +
                ", sfsh='" + sfsh + '\'' +
                ", yhdj='" + yhdj + '\'' +
                ", zglx='" + zglx + '\'' +
                ", clzt='" + clzt + '\'' +
                '}';
    }
}
