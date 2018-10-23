package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.List;

/**
 * 类描述：管理人员名单
 * 创建人：liufei
 * 创建时间：2018/3/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ManagerPeopleInfo {

    private List<ManagerPersonBean> managerDuptyList;//1项目经理  2项目副职
    private List<ManageGroupBean> manageDeptList;//部门  成员
    private List<ManageGroupBean> managerTeamList;//班组 成员

    public List<ManagerPersonBean> getManagerDuptyList() {
        return managerDuptyList;
    }

    public void setManagerDuptyList(List<ManagerPersonBean> managerDuptyList) {
        this.managerDuptyList = managerDuptyList;
    }

    public List<ManageGroupBean> getManageDeptList() {
        return manageDeptList;
    }

    public void setManageDeptList(List<ManageGroupBean> manageDeptList) {
        this.manageDeptList = manageDeptList;
    }

    public List<ManageGroupBean> getManagerTeamList() {
        return managerTeamList;
    }

    public void setManagerTeamList(List<ManageGroupBean> managerTeamList) {
        this.managerTeamList = managerTeamList;
    }

    public static class ManagerPersonBean {
        /**
         * id : 7730253891199238144
         * projId : 7730256564149485568
         * projCode : dfdfd
         * managerName : 董卿
         * managerPhone : 18302619791
         * managerIdCard : 123
         * projPosition : 1
         * professionalTitle : 174
         * profession : 12312
         * type : 1
         * deptId : 7730253891165683712
         * status : 1
         * createTime : 1537246362000
         * createUserId : 7722303027708190720
         * createUserName : admin
         * updateTime : 1537282415000
         * updateUserId : 123
         * updateUserName : 笑笑
         * deptType : 1
         * deptName : 项目经理
         * certificateName : 123
         * certificateNo : 123
         * file : []
         */

        private String id;
        private String projId;
        private String projCode;
        private String managerName;
        private String managerPhone;
        private String managerIdCard;
        private int projPosition;
        private int professionalTitle;
        private String profession;
        private int type;  //1  项目经理  2 项目副职
        private String deptId;
        private int status;
        private long createTime;
        private String createUserId;
        private String createUserName;
        private long updateTime;
        private String updateUserId;
        private String updateUserName;
        private int deptType;
        private String deptName;
        private String certificateName;
        private String certificateNo;
        private String photoId;
        private List<?> file;

        public String getPhotoId() {
            return photoId;
        }

        public void setPhotoId(String photoId) {
            this.photoId = photoId;
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

        public String getManagerName() {
            return managerName;
        }

        public void setManagerName(String managerName) {
            this.managerName = managerName;
        }

        public String getManagerPhone() {
            return managerPhone;
        }

        public void setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
        }

        public String getManagerIdCard() {
            return managerIdCard;
        }

        public void setManagerIdCard(String managerIdCard) {
            this.managerIdCard = managerIdCard;
        }

        public int getProjPosition() {
            return projPosition;
        }

        public void setProjPosition(int projPosition) {
            this.projPosition = projPosition;
        }

        public int getProfessionalTitle() {
            return professionalTitle;
        }

        public void setProfessionalTitle(int professionalTitle) {
            this.professionalTitle = professionalTitle;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
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

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
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

        public int getDeptType() {
            return deptType;
        }

        public void setDeptType(int deptType) {
            this.deptType = deptType;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getCertificateName() {
            return certificateName;
        }

        public void setCertificateName(String certificateName) {
            this.certificateName = certificateName;
        }

        public String getCertificateNo() {
            return certificateNo;
        }

        public void setCertificateNo(String certificateNo) {
            this.certificateNo = certificateNo;
        }

        public List<?> getFile() {
            return file;
        }

        public void setFile(List<?> file) {
            this.file = file;
        }
    }

    public static class ManageGroupBean {
        /**
         * managerDept : {"managerDeptId":"7730303889158193152","projId":"7730256564149485568","projCode":"dfdfd","deptName":"安全部","type":3}
         * managerList : [{"id":"7730306327646846976","projId":"7730256564149485568","projCode":"dfdfd","managerName":"广告","managerPhone":"17698761234","managerIdCard":"522121199811188765","projPosition":158,"professionalTitle":177,"profession":"无","type":3,"deptId":"7730303889158193152","status":1,"createTime":1537258864000,"createUserId":"123","createUserName":"笑笑","updateTime":null,"updateUserId":null,"updateUserName":null,"deptType":3,"deptName":"安全部","certificateName":"无","certificateNo":"无","file":[]}]
         */

        private ManagerDeptBean managerDept;
        private List<ManagerDeptPersonBean> managerList;

        public ManagerDeptBean getManagerDept() {
            return managerDept;
        }

        public void setManagerDept(ManagerDeptBean managerDept) {
            this.managerDept = managerDept;
        }

        public List<ManagerDeptPersonBean> getManagerList() {
            return managerList;
        }

        public void setManagerList(List<ManagerDeptPersonBean> managerList) {
            this.managerList = managerList;
        }


    }


    public static class ManagerDeptPersonBean {
        /**
         * id : 7730306327646846976
         * projId : 7730256564149485568
         * projCode : dfdfd
         * managerName : 广告
         * managerPhone : 17698761234
         * managerIdCard : 522121199811188765
         * projPosition : 158
         * professionalTitle : 177
         * profession : 无
         * type : 3
         * deptId : 7730303889158193152
         * status : 1
         * createTime : 1537258864000
         * createUserId : 123
         * createUserName : 笑笑
         * updateTime : null
         * updateUserId : null
         * updateUserName : null
         * deptType : 3
         * deptName : 安全部
         * certificateName : 无
         * certificateNo : 无
         * file : []
         */

        private String id;
        private String projId;
        private String projCode;
        private String managerName;
        private String managerPhone;
        private String managerIdCard;
        private int projPosition;
        private int professionalTitle;
        private String profession;
        private int type;
        private String deptId;
        private int status;
        private long createTime;
        private String createUserId;
        private String createUserName;
        private Object updateTime;
        private Object updateUserId;
        private Object updateUserName;
        private int deptType;
        private String deptName;
        private String certificateName;
        private String certificateNo;
        private List<?> file;
        private String photoId;

        public String getPhotoId() {
            return photoId;
        }

        public void setPhotoId(String photoId) {
            this.photoId = photoId;
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

        public String getManagerName() {
            return managerName;
        }

        public void setManagerName(String managerName) {
            this.managerName = managerName;
        }

        public String getManagerPhone() {
            return managerPhone;
        }

        public void setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
        }

        public String getManagerIdCard() {
            return managerIdCard;
        }

        public void setManagerIdCard(String managerIdCard) {
            this.managerIdCard = managerIdCard;
        }

        public int getProjPosition() {
            return projPosition;
        }

        public void setProjPosition(int projPosition) {
            this.projPosition = projPosition;
        }

        public int getProfessionalTitle() {
            return professionalTitle;
        }

        public void setProfessionalTitle(int professionalTitle) {
            this.professionalTitle = professionalTitle;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
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

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(Object updateUserId) {
            this.updateUserId = updateUserId;
        }

        public Object getUpdateUserName() {
            return updateUserName;
        }

        public void setUpdateUserName(Object updateUserName) {
            this.updateUserName = updateUserName;
        }

        public int getDeptType() {
            return deptType;
        }

        public void setDeptType(int deptType) {
            this.deptType = deptType;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getCertificateName() {
            return certificateName;
        }

        public void setCertificateName(String certificateName) {
            this.certificateName = certificateName;
        }

        public String getCertificateNo() {
            return certificateNo;
        }

        public void setCertificateNo(String certificateNo) {
            this.certificateNo = certificateNo;
        }

        public List<?> getFile() {
            return file;
        }

        public void setFile(List<?> file) {
            this.file = file;
        }
    }

    public static class ManagerDeptBean {
        /**
         * managerDeptId : 7730303889158193152
         * projId : 7730256564149485568
         * projCode : dfdfd
         * deptName : 安全部
         * type : 3
         */

        private String managerDeptId;
        private String projId;
        private String projCode;
        private String deptName;
        private int type;

        public String getManagerDeptId() {
            return managerDeptId;
        }

        public void setManagerDeptId(String managerDeptId) {
            this.managerDeptId = managerDeptId;
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

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

}
