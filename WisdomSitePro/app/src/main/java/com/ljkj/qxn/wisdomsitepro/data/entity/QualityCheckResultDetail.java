package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：检查结果详情
 * 创建人：lxx
 * 创建时间：2018/3/15 11:12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QualityCheckResultDetail {

    public Base base;
    public ProInspect proInspect;
    /**
     * 工程地址
     */
    public String proAddress;

    public static class ProInspect {


        private String id;

        /**
         * 安全质量检查编号
         */
        private String checkNo;

        /**
         * 处理状态
         */
        private String clzt;

        /**
         * 反馈日期
         */
        private String fkrqTime;

        /**
         * 检查工程部位
         */
        private String jcgcbw;

        /**
         * 检查记录表
         */
        private String jcjlb;

        /**
         * 检查日期
         */
        private String jcrqTime;

        /**
         * 联系人
         */
        private String linker;

        /**
         * 检查人员
         */
        private String jcry;

        /**
         * 项目id
         */
        private String proId;

        /**
         * 联系人电话
         */
        private String linkerPhone;

        /**
         * 项目名称
         */
        private String projectName;

        /**
         * 申请解除督办日期
         */
        private String sqjcdbrq;

        /**
         * 停留时间
         */
        private String tlsj;

        /**
         * 类型
         */
        private String type;

        /**
         * 项目Id
         */
        private String xmid;

        /**
         * 项目名称
         */
        private String xmmc;

        /**
         * 隐患详情
         */
        private String yhxq;

        /**
         * 隐患等级
         */
        private String yhdj;

        /**
         * 整改负责人
         */
        private String zgfzr;

        /**
         * 整改类型
         */
        private String zglx;

        /**
         * 整改期限
         */
        private String zgqx;

        /**
         * 下发整改通知书的标题
         */
        private String zgtzsmz;

        public String getCheckNo() {
            return checkNo;
        }

        public void setCheckNo(String checkNo) {
            this.checkNo = checkNo;
        }

        public String getClzt() {
            return clzt;
        }

        public void setClzt(String clzt) {
            this.clzt = clzt;
        }

        public String getFkrqTime() {
            return fkrqTime;
        }

        public void setFkrqTime(String fkrqTime) {
            this.fkrqTime = fkrqTime;
        }

        public String getJcgcbw() {
            return jcgcbw;
        }

        public void setJcgcbw(String jcgcbw) {
            this.jcgcbw = jcgcbw;
        }

        public String getJcjlb() {
            return jcjlb;
        }

        public void setJcjlb(String jcjlb) {
            this.jcjlb = jcjlb;
        }

        public String getJcrqTime() {
            return jcrqTime;
        }

        public void setJcrqTime(String jcrqTime) {
            this.jcrqTime = jcrqTime;
        }

        public String getLinker() {
            return linker;
        }

        public void setLinker(String linker) {
            this.linker = linker;
        }

        public String getJcry() {
            return jcry;
        }

        public void setJcry(String jcry) {
            this.jcry = jcry;
        }

        public String getProId() {
            return proId;
        }

        public void setProId(String proId) {
            this.proId = proId;
        }

        public String getLinkerPhone() {
            return linkerPhone;
        }

        public void setLinkerPhone(String linkerPhone) {
            this.linkerPhone = linkerPhone;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getSqjcdbrq() {
            return sqjcdbrq;
        }

        public void setSqjcdbrq(String sqjcdbrq) {
            this.sqjcdbrq = sqjcdbrq;
        }

        public String getTlsj() {
            return tlsj;
        }

        public void setTlsj(String tlsj) {
            this.tlsj = tlsj;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getXmid() {
            return xmid;
        }

        public void setXmid(String xmid) {
            this.xmid = xmid;
        }

        public String getXmmc() {
            return xmmc;
        }

        public void setXmmc(String xmmc) {
            this.xmmc = xmmc;
        }

        public String getYhxq() {
            return yhxq;
        }

        public void setYhxq(String yhxq) {
            this.yhxq = yhxq;
        }

        public String getYhdj() {
            return yhdj;
        }

        public void setYhdj(String yhdj) {
            this.yhdj = yhdj;
        }

        public String getZgfzr() {
            return zgfzr;
        }

        public void setZgfzr(String zgfzr) {
            this.zgfzr = zgfzr;
        }

        public String getZglx() {
            return zglx;
        }

        public void setZglx(String zglx) {
            this.zglx = zglx;
        }

        public String getZgqx() {
            return zgqx;
        }

        public void setZgqx(String zgqx) {
            this.zgqx = zgqx;
        }

        public String getZgtzsmz() {
            return zgtzsmz;
        }

        public void setZgtzsmz(String zgtzsmz) {
            this.zgtzsmz = zgtzsmz;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "ProInspect{" +
                    "checkNo='" + checkNo + '\'' +
                    ", clzt='" + clzt + '\'' +
                    ", fkrqTime='" + fkrqTime + '\'' +
                    ", jcgcbw='" + jcgcbw + '\'' +
                    ", jcjlb='" + jcjlb + '\'' +
                    ", jcrqTime='" + jcrqTime + '\'' +
                    ", linker='" + linker + '\'' +
                    ", jcry='" + jcry + '\'' +
                    ", proId='" + proId + '\'' +
                    ", linkerPhone='" + linkerPhone + '\'' +
                    ", projectName='" + projectName + '\'' +
                    ", sqjcdbrq='" + sqjcdbrq + '\'' +
                    ", tlsj='" + tlsj + '\'' +
                    ", type='" + type + '\'' +
                    ", xmid='" + xmid + '\'' +
                    ", xmmc='" + xmmc + '\'' +
                    ", yhxq='" + yhxq + '\'' +
                    ", yhdj='" + yhdj + '\'' +
                    ", zgfzr='" + zgfzr + '\'' +
                    ", zglx='" + zglx + '\'' +
                    ", zgqx='" + zgqx + '\'' +
                    ", zgtzsmz='" + zgtzsmz + '\'' +
                    '}';
        }
    }

    public static class Base {
        private String id;

        /**
         * 监理单位意见
         */
        private String jldwyj;

        /**
         * 工程地址
         */
        private String proAddress;

        /**
         * 工程名称
         */
        private String proName;


        /**
         * 是否制定措施
         */
        private String sfzdcs;


        /**
         * 是否制定预案
         */
        private String sfzdya;

        /**
         * 整改情况
         */
        private String zgqk;

        /**
         * 工程质量整改通知书编号
         */
        private String zgtzsbh;

        /**
         * 整改资金数额
         */
        private String zgzjse;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJldwyj() {
            return jldwyj;
        }

        public void setJldwyj(String jldwyj) {
            this.jldwyj = jldwyj;
        }

        public String getProAddress() {
            return proAddress;
        }

        public void setProAddress(String proAddress) {
            this.proAddress = proAddress;
        }

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public String getSfzdcs() {
            return sfzdcs;
        }

        public void setSfzdcs(String sfzdcs) {
            this.sfzdcs = sfzdcs;
        }

        public String getSfzdya() {
            return sfzdya;
        }

        public void setSfzdya(String sfzdya) {
            this.sfzdya = sfzdya;
        }

        public String getZgqk() {
            return zgqk;
        }

        public void setZgqk(String zgqk) {
            this.zgqk = zgqk;
        }

        public String getZgtzsbh() {
            return zgtzsbh;
        }

        public void setZgtzsbh(String zgtzsbh) {
            this.zgtzsbh = zgtzsbh;
        }

        public String getZgzjse() {
            return zgzjse;
        }

        public void setZgzjse(String zgzjse) {
            this.zgzjse = zgzjse;
        }

        @Override
        public String toString() {
            return "Base{" +
                    "id='" + id + '\'' +
                    ", jldwyj='" + jldwyj + '\'' +
                    ", proAddress='" + proAddress + '\'' +
                    ", proName='" + proName + '\'' +
                    ", sfzdcs='" + sfzdcs + '\'' +
                    ", sfzdya='" + sfzdya + '\'' +
                    ", zgqk='" + zgqk + '\'' +
                    ", zgtzsbh='" + zgtzsbh + '\'' +
                    ", zgzjse='" + zgzjse + '\'' +
                    '}';
        }
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public ProInspect getProInspect() {
        return proInspect;
    }

    public void setProInspect(ProInspect proInspect) {
        this.proInspect = proInspect;
    }

    public String getProAddress() {
        return proAddress;
    }

    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
    }

    @Override
    public String toString() {
        return "QualityCheckResultDetail{" +
                "base=" + base +
                ", proInspect=" + proInspect +
                ", proAddress='" + proAddress + '\'' +
                '}';
    }
}
