package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.List;

public class QualityPatrolDetailInfo {

    /**
     * check : {"id":"7727687396116463616","createTime":"2018-09-11 10:54:22","updateTime":null,"status":1,"projId":"7725905018358943744","checkCode":"code123","checkPosition":"头","checkDate":"2018-09-11","reformDate":"2018-09-20","whyType":"3","lossType":"一般事故","spokesman":"发文人","checkInfo":"隐患内容","teamId":null,"team":"木工班","teamManagerId":null,"teamManager":"负责人","building":"1","floor":"2","reformId":"7727742756217487360","reformStatus":1,"reformNumber":2,"createUserId":"0","createUserName":null,"overTime":null}
     * reform : [{"id":"7727737925309366272","createTime":"2018-09-11 14:15:09","updateTime":null,"status":1,"checkId":"7727687396116463616","reformContent":"整改内容","reformFile":null,"checkStatus":1,"checkStatusContent":"整改状态内容","createUserId":"0","createUserName":"string"},{"id":"7727742756217487360","createTime":"2018-09-11 14:34:21","updateTime":null,"status":1,"checkId":"7727687396116463616","reformContent":"整改内容","reformFile":null,"checkStatus":1,"checkStatusContent":null,"createUserId":"0","createUserName":"string"}]
     */

    private CheckBean check;
    private List<ReformBean> reform;

    public CheckBean getCheck() {
        return check;
    }

    public void setCheck(CheckBean check) {
        this.check = check;
    }

    public List<ReformBean> getReform() {
        return reform;
    }

    public void setReform(List<ReformBean> reform) {
        this.reform = reform;
    }

    public static class CheckBean {
        /**
         * id : 7727687396116463616
         * createTime : 2018-09-11 10:54:22
         * updateTime : null
         * status : 1
         * projId : 7725905018358943744
         * checkCode : code123
         * checkPosition : 头
         * checkDate : 2018-09-11
         * reformDate : 2018-09-20
         * whyType : 3
         * lossType : 一般事故
         * spokesman : 发文人
         * checkInfo : 隐患内容
         * teamId : null
         * team : 木工班
         * teamManagerId : null
         * teamManager : 负责人
         * building : 1
         * floor : 2
         * reformId : 7727742756217487360
         * reformStatus : 1
         * reformNumber : 2
         * createUserId : 0
         * createUserName : null
         * overTime : null
         */

        private String id;
        private String createTime;
        private String updateTime;
        private int status;
        private String projId;
        private String checkCode;
        private String checkPosition;
        private String checkDate;
        private String reformDate;
        private String whyType;
        private String lossType;
        private String spokesman;
        private String checkInfo;
        private String teamId;
        private String team;
        private String teamManagerId;
        private String teamManager;
        private String building;
        private String floor;
        private String reformId;
        private int reformStatus;
        private String reformNumber;
        private String createUserId;
        private String createUserName;
        private String overTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public String getProjId() {
            return projId;
        }

        public void setProjId(String projId) {
            this.projId = projId;
        }

        public String getCheckCode() {
            return checkCode;
        }

        public void setCheckCode(String checkCode) {
            this.checkCode = checkCode;
        }

        public String getCheckPosition() {
            return checkPosition;
        }

        public void setCheckPosition(String checkPosition) {
            this.checkPosition = checkPosition;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public String getReformDate() {
            return reformDate;
        }

        public void setReformDate(String reformDate) {
            this.reformDate = reformDate;
        }

        public String getWhyType() {
            return whyType;
        }

        public void setWhyType(String whyType) {
            this.whyType = whyType;
        }

        public String getLossType() {
            return lossType;
        }

        public void setLossType(String lossType) {
            this.lossType = lossType;
        }

        public String getSpokesman() {
            return spokesman;
        }

        public void setSpokesman(String spokesman) {
            this.spokesman = spokesman;
        }

        public String getCheckInfo() {
            return checkInfo;
        }

        public void setCheckInfo(String checkInfo) {
            this.checkInfo = checkInfo;
        }

        public String getTeamId() {
            return teamId;
        }

        public void setTeamId(String teamId) {
            this.teamId = teamId;
        }

        public String getTeam() {
            return team;
        }

        public void setTeam(String team) {
            this.team = team;
        }

        public String getTeamManagerId() {
            return teamManagerId;
        }

        public void setTeamManagerId(String teamManagerId) {
            this.teamManagerId = teamManagerId;
        }

        public String getTeamManager() {
            return teamManager;
        }

        public void setTeamManager(String teamManager) {
            this.teamManager = teamManager;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getReformId() {
            return reformId;
        }

        public void setReformId(String reformId) {
            this.reformId = reformId;
        }

        public int getReformStatus() {
            return reformStatus;
        }

        public void setReformStatus(int reformStatus) {
            this.reformStatus = reformStatus;
        }

        public String getReformNumber() {
            return reformNumber;
        }

        public void setReformNumber(String reformNumber) {
            this.reformNumber = reformNumber;
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

        public String getOverTime() {
            return overTime;
        }

        public void setOverTime(String overTime) {
            this.overTime = overTime;
        }
    }

    public static class ReformBean {
        /**
         * id : 7727737925309366272
         * createTime : 2018-09-11 14:15:09
         * updateTime : null
         * status : 1
         * checkId : 7727687396116463616
         * reformContent : 整改内容
         * reformFile : null
         * checkStatus : 1
         * checkStatusContent : 整改状态内容
         * createUserId : 0
         * createUserName : string
         */

        private String id;
        private String createTime;
        private String updateTime;
        private int status;
        private String checkId;
        private String reformContent;
        private String reformFile;
        private int checkStatus;
        private String checkStatusContent;
        private String createUserId;
        private String createUserName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public String getCheckId() {
            return checkId;
        }

        public void setCheckId(String checkId) {
            this.checkId = checkId;
        }

        public String getReformContent() {
            return reformContent;
        }

        public void setReformContent(String reformContent) {
            this.reformContent = reformContent;
        }

        public String getReformFile() {
            return reformFile;
        }

        public void setReformFile(String reformFile) {
            this.reformFile = reformFile;
        }

        public int getCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(int checkStatus) {
            this.checkStatus = checkStatus;
        }

        public String getCheckStatusContent() {
            return checkStatusContent;
        }

        public void setCheckStatusContent(String checkStatusContent) {
            this.checkStatusContent = checkStatusContent;
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
    }
}