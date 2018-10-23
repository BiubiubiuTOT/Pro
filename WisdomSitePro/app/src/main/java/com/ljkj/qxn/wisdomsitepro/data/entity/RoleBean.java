package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.List;

public class RoleBean {

    private List<TListBean> tList;
    private List<DListBean> dList;

    public List<TListBean> getTList() {
        return tList;
    }

    public void setTList(List<TListBean> tList) {
        this.tList = tList;
    }

    public List<DListBean> getDList() {
        return dList;
    }

    public void setDList(List<DListBean> dList) {
        this.dList = dList;
    }

    public static class TListBean {
        /**
         * id : 7722668830948352000
         * name : 木工班组长
         * projId : 7727391783479427072
         * status : 0
         * num : 0
         */

        private String id;
        private String name;
        private String projId;
        private int status;
        private int num;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProjId() {
            return projId;
        }

        public void setProjId(String projId) {
            this.projId = projId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    public static class DListBean {
        /**
         * id : 7728858588079218688
         * name : 项目副经理
         * projId : 7727391783479427072
         * status : 1
         * num : 0
         */

        private String id;
        private String name;
        private String projId;
        private int status;
        private int num;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProjId() {
            return projId;
        }

        public void setProjId(String projId) {
            this.projId = projId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
