package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.List;

public class LabourDetail {

    /**
     * pageNum : 0
     * pageSize : 0
     * projId : 7730596820333821952
     * projName : null
     * file : [{"relId":"7734668646949257216","fileName":"1 (1).pdf","fileSize":"213633","id":"7734668647196721152","fileExt":"pdf","fileId":"7734670470309675009"}]
     * id : 7734668646949257216
     * name : liusong
     * sex : 1
     * personPhotoId :
     * idcardFront :
     * idcardOpposite :
     * nation : 汉族
     * barthday : 2018-09-29
     * address : 213123
     * certificatesType : null
     * certificatesNum : 123456789123456789
     * signOrganization : 123123
     * startDate : 1538236800000
     * endDate : 1538668800000
     * longPeriod : 1
     * laborCompany : 劳务公司
     * teamsId : null
     * teamsType : 钢筋班组
     * isForeman : null
     * phoneNum : 13087826581
     * settleWageMethod : 1
     * isWorkhead : null
     * workCode : 213123
     * isPoverty : 1
     * povertyAttach : 7734670470309675009
     * isReported : null
     * reportDatetime : null
     * status : null
     * leaveStatus : null
     * accessDate : null
     * createTime : null
     */

    private int pageNum;
    private int pageSize;
    private String projId;
    private String projName;
    private String id;
    private String name;
    private int sex;
    private String personPhotoId;
    private String idcardFront;
    private String idcardOpposite;
    private String nation;
    private String barthday;
    private String address;
    private String certificatesType;
    private String certificatesNum;
    private String signOrganization;
    private String startDate;
    private String endDate;
    private String longPeriod;
    private String laborCompany;
    private String teamsId;
    private String teamsType;
    private String isForeman;
    private String phoneNum;
    private int settleWageMethod;
    private String isWorkhead;
    private String workCode;
    private int isPoverty;
    private String povertyAttach;
    private int isReported;
    private String reportDatetime;
    private String status;
    private String leaveStatus;
    private String accessDate;
    private String createTime;
    private List<FileBean> file;

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

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPersonPhotoId() {
        return personPhotoId;
    }

    public void setPersonPhotoId(String personPhotoId) {
        this.personPhotoId = personPhotoId;
    }

    public String getIdcardFront() {
        return idcardFront;
    }

    public void setIdcardFront(String idcardFront) {
        this.idcardFront = idcardFront;
    }

    public String getIdcardOpposite() {
        return idcardOpposite;
    }

    public void setIdcardOpposite(String idcardOpposite) {
        this.idcardOpposite = idcardOpposite;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBarthday() {
        return barthday;
    }

    public void setBarthday(String barthday) {
        this.barthday = barthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCertificatesType() {
        return certificatesType;
    }

    public void setCertificatesType(String certificatesType) {
        this.certificatesType = certificatesType;
    }

    public String getCertificatesNum() {
        return certificatesNum;
    }

    public void setCertificatesNum(String certificatesNum) {
        this.certificatesNum = certificatesNum;
    }

    public String getSignOrganization() {
        return signOrganization;
    }

    public void setSignOrganization(String signOrganization) {
        this.signOrganization = signOrganization;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLongPeriod() {
        return longPeriod;
    }

    public void setLongPeriod(String longPeriod) {
        this.longPeriod = longPeriod;
    }

    public String getLaborCompany() {
        return laborCompany;
    }

    public void setLaborCompany(String laborCompany) {
        this.laborCompany = laborCompany;
    }

    public String getTeamsId() {
        return teamsId;
    }

    public void setTeamsId(String teamsId) {
        this.teamsId = teamsId;
    }

    public String getTeamsType() {
        return teamsType;
    }

    public void setTeamsType(String teamsType) {
        this.teamsType = teamsType;
    }

    public String getIsForeman() {
        return isForeman;
    }

    public void setIsForeman(String isForeman) {
        this.isForeman = isForeman;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getSettleWageMethod() {
        return settleWageMethod;
    }

    public void setSettleWageMethod(int settleWageMethod) {
        this.settleWageMethod = settleWageMethod;
    }

    public String getIsWorkhead() {
        return isWorkhead;
    }

    public void setIsWorkhead(String isWorkhead) {
        this.isWorkhead = isWorkhead;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public int getIsPoverty() {
        return isPoverty;
    }

    public void setIsPoverty(int isPoverty) {
        this.isPoverty = isPoverty;
    }

    public String getPovertyAttach() {
        return povertyAttach;
    }

    public void setPovertyAttach(String povertyAttach) {
        this.povertyAttach = povertyAttach;
    }

    public int getIsReported() {
        return isReported;
    }

    public void setIsReported(int isReported) {
        this.isReported = isReported;
    }

    public String getReportDatetime() {
        return reportDatetime;
    }

    public void setReportDatetime(String reportDatetime) {
        this.reportDatetime = reportDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(String accessDate) {
        this.accessDate = accessDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<FileBean> getFile() {
        return file;
    }

    public void setFile(List<FileBean> file) {
        this.file = file;
    }

    public static class FileBean {
        /**
         * relId : 7734668646949257216
         * fileName : 1 (1).pdf
         * fileSize : 213633
         * id : 7734668647196721152
         * fileExt : pdf
         * fileId : 7734670470309675009
         */

        private String relId;
        private String fileName;
        private String fileSize;
        private String id;
        private String fileExt;
        private String fileId;

        public String getRelId() {
            return relId;
        }

        public void setRelId(String relId) {
            this.relId = relId;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileSize() {
            return fileSize;
        }

        public void setFileSize(String fileSize) {
            this.fileSize = fileSize;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFileExt() {
            return fileExt;
        }

        public void setFileExt(String fileExt) {
            this.fileExt = fileExt;
        }

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }
    }
}
