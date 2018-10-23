package com.ljkj.qxn.wisdomsitepro.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeptTeamInfo {

    /**
     * id : 7723800167329587200
     * name : 木工劳务公司
     * lmTeamList : [{"pageNum":0,"pageSize":0,"id":"7725492607586951168","projId":"7720883873953882112","projCode":null,"parentId":"7723800167329587200","name":"木工班","orgType":195,"sort":null,"createUserId":"7720898944388063232","createUserName":"刘松","createTime":"2018-09-05 09:33:17","updateUserId":"7720898944388063232","updateUserName":"刘松","updateTime":"2018-09-05 09:33:17","status":1}]
     */

    private String id;
    private String name;
    private List<LmTeamListBean> lmTeamList;

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

    public List<LmTeamListBean> getLmTeamList() {
        return lmTeamList;
    }

    public void setLmTeamList(List<LmTeamListBean> lmTeamList) {
        this.lmTeamList = lmTeamList;
    }

    public static class LmTeamListBean {
        /**
         * pageNum : 0
         * pageSize : 0
         * id : 7725492607586951168
         * projId : 7720883873953882112
         * projCode : null
         * parentId : 7723800167329587200
         * name : 木工班
         * orgType : 195
         * sort : null
         * createUserId : 7720898944388063232
         * createUserName : 刘松
         * createTime : 2018-09-05 09:33:17
         * updateUserId : 7720898944388063232
         * updateUserName : 刘松
         * updateTime : 2018-09-05 09:33:17
         * status : 1
         */

        private int pageNum;
        private int pageSize;
        private String id;
        private String projId;
        private String projCode;
        private String parentId;
        private String name;
        private int orgType;
        private String sort;
        private String createUserId;
        private String createUserName;
        private String createTime;
        private String updateUserId;
        private String updateUserName;
        private String updateTime;
        private int status;
        @SerializedName("teamLeader")
        private String teamLeaderName;
        private String teamLeaderId;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProjId() {
            return projId;
        }

        public void setProjId(String projId) {
            this.projId = projId;
        }

        public String getProjCode() {
            return projCode;
        }

        public void setProjCode(String projCode) {
            this.projCode = projCode;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrgType() {
            return orgType;
        }

        public void setOrgType(int orgType) {
            this.orgType = orgType;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public String getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(String createUserName) {
            this.createUserName = createUserName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(String updateUserId) {
            this.updateUserId = updateUserId;
        }

        public String getUpdateUserName() {
            return updateUserName;
        }

        public void setUpdateUserName(String updateUserName) {
            this.updateUserName = updateUserName;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }


        public String getTeamLeader() {
            return teamLeaderName == null ? "" : teamLeaderName;
        }

        public void setTeamLeader(String teamLeader) {
            this.teamLeaderName = teamLeader;
        }

        public String getTeamLeaderId() {
            return teamLeaderId;
        }

        public void setTeamLeaderId(String teamLeaderId) {
            this.teamLeaderId = teamLeaderId;
        }
    }
}
