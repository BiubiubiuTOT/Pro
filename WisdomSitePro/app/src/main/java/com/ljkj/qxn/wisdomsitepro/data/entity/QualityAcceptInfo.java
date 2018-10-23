package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述: 质量验收网络请求返回实体类
 * 创建人：rjf
 * 创建时间：2018/3/10 11:48.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class QualityAcceptInfo {
    /**
     * 基础类型
     */
    private String jclx;

    /**
     * 结构类型
     */
    private String jglx;

    /**
     * 结构类型层数
     */
    private String jglxcs;

    /**
     * 建筑面积
     */
    private String jzmj;

    /**
     * 地下室层数
     */
    private String dxscs;

    /**
     * 施工周期
     */
    private String sgzq;

    /**
     * 验收部位
     */
    private String ysbw;

    /**
     * 验收日期
     */
    private long ysrq;

    /**
     * 分部工程名称
     */
    private String fbgcmc;

    /**
     * 附件列表
     */
    private List<EnclosureInfo> files = new ArrayList<>();

    public String getFbgcmc() {
        return fbgcmc;
    }

    public void setFbgcmc(String fbgcmc) {
        this.fbgcmc = fbgcmc;
    }

    public String getJclx() {
        return jclx;
    }

    public void setJclx(String jclx) {
        this.jclx = jclx;
    }

    public String getJglx() {
        return jglx;
    }

    public void setJglx(String jglx) {
        this.jglx = jglx;
    }

    public String getJglxcs() {
        return jglxcs;
    }

    public void setJglxcs(String jglxcs) {
        this.jglxcs = jglxcs;
    }

    public String getJzmj() {
        return jzmj;
    }

    public void setJzmj(String jzmj) {
        this.jzmj = jzmj;
    }

    public String getDxscs() {
        return dxscs;
    }

    public void setDxscs(String dxscs) {
        this.dxscs = dxscs;
    }

    public String getSgzq() {
        return sgzq;
    }

    public void setSgzq(String sgzq) {
        this.sgzq = sgzq;
    }

    public String getYsbw() {
        return ysbw;
    }

    public void setYsbw(String ysbw) {
        this.ysbw = ysbw;
    }

    public long getYsrq() {
        return ysrq;
    }

    public void setYsrq(long ysrq) {
        this.ysrq = ysrq;
    }


    public List<EnclosureInfo> getFiles() {
        return files;
    }

    public void setFiles(List<EnclosureInfo> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "QualityAcceptInfo{" +
                "jclx='" + jclx + '\'' +
                ", jglx='" + jglx + '\'' +
                ", jglxcs='" + jglxcs + '\'' +
                ", jzmj='" + jzmj + '\'' +
                ", dxscs='" + dxscs + '\'' +
                ", sgzq='" + sgzq + '\'' +
                ", ysbw='" + ysbw + '\'' +
                ", ysrq='" + ysrq + '\'' +
                ", files=" + files +
                '}';
    }
}
