package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.List;

/**
 * 类描述：安全防护
 * 创建人：rjf
 * 创建时间：2018/3/13 15:38.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardInfo {

    /**
     * 楼盘名称
     */
    private String name;

    /**
     * 楼盘层数列表
     */
    private List<DongsBean> dongs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DongsBean> getDongs() {
        return dongs;
    }

    public void setDongs(List<DongsBean> dongs) {
        this.dongs = dongs;
    }

    public static class DongsBean {

        /**
         * 楼栋名称
         */
        private String lou;

        /**
         * 楼层数
         */
        private int cengs;

        public String getLou() {
            return lou;
        }

        public void setLou(String lou) {
            this.lou = lou;
        }

        public int getCengs() {
            return cengs;
        }

        public void setCengs(int cengs) {
            this.cengs = cengs;
        }

        @Override
        public String toString() {
            return "DongsBean{" +
                    "lou='" + lou + '\'' +
                    ", cengs=" + cengs +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SafeGuardInfo{" +
                "name='" + name + '\'' +
                ", dongs=" + dongs +
                '}';
    }
}
