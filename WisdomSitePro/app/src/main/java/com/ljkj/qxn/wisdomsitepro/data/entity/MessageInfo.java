package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 消息实体
 * 创建人：lxx
 * 创建时间：2018/3/19 14:52
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class MessageInfo {

    private String id;

    /**
     * 下发时间
     */
    private String issuedDate;

    /**
     * 检查日期
     */
    private String jcrq_time;

    /**
     * 检查人
     */
    private String jcry;

    /**
     * 类型  1安全；2质量
     */
    private String type;

    /**
     * 处理状态
     */
    private String clzt;

    /**
     * 是否审核
     */
    private String sfsh;

    /**
     * 隐患类型
     */
    private String yhdj;

    /**
     * 整改类型
     */
    private String zglx;

    private String project_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getJcrq_time() {
        return jcrq_time;
    }

    public void setJcrq_time(String jcrq_time) {
        this.jcrq_time = jcrq_time;
    }

    public String getJcry() {
        return jcry;
    }

    public void setJcry(String jcry) {
        this.jcry = jcry;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getClzt() {
        return clzt;
    }

    public void setClzt(String clzt) {
        this.clzt = clzt;
    }

    public String getSfsh() {
        return sfsh;
    }

    public void setSfsh(String sfsh) {
        this.sfsh = sfsh;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "id='" + id + '\'' +
                ", issuedDate='" + issuedDate + '\'' +
                ", jcrq_time='" + jcrq_time + '\'' +
                ", jcry='" + jcry + '\'' +
                ", type='" + type + '\'' +
                ", clzt='" + clzt + '\'' +
                ", sfsh='" + sfsh + '\'' +
                ", yhdj='" + yhdj + '\'' +
                ", zglx='" + zglx + '\'' +
                ", project_name='" + project_name + '\'' +
                '}';
    }
}
