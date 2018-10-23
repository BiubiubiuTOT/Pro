package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.List;

/**
 * 类描述：安全防护楼栋详情
 * 创建人：rjf
 * 创建时间：2018/3/13 16:23.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardBuildInfo {

    /**
     * 楼栋名称
     */
    private String lou;

    /**
     * 楼栋排序
     */
    private String louOrder;

    /**
     * 架空层数量
     */
    private int jkCount;

    /**
     * 地下层数量
     */
    private int dxCount;

    /**
     * 普通层数量
     */
    private int lcCount;

    /**
     * 楼层数列表
     */
    private List<CengsBean> cengs;

    public String getLou() {
        return lou;
    }

    public void setLou(String lou) {
        this.lou = lou;
    }

    public String getLouOrder() {
        return louOrder;
    }

    public void setLouOrder(String louOrder) {
        this.louOrder = louOrder;
    }

    public int getJkCount() {
        return jkCount;
    }

    public void setJkCount(int jkCount) {
        this.jkCount = jkCount;
    }

    public int getDxCount() {
        return dxCount;
    }

    public void setDxCount(int dxCount) {
        this.dxCount = dxCount;
    }

    public int getLcCount() {
        return lcCount;
    }

    public void setLcCount(int lcCount) {
        this.lcCount = lcCount;
    }

    public List<CengsBean> getCengs() {
        return cengs;
    }

    public void setCengs(List<CengsBean> cengs) {
        this.cengs = cengs;
    }

    public static class CengsBean {

        /**
         * 楼层名称
         */
        private String ceng;

        /**
         * 楼层ID
         */
        private String id;

        public String getCeng() {
            return ceng;
        }

        public void setCeng(String ceng) {
            this.ceng = ceng;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "CengsBean{" +
                    "ceng='" + ceng + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SafeGuardBuildInfo{" +
                "lou='" + lou + '\'' +
                ", cengs=" + cengs +
                '}';
    }
}
