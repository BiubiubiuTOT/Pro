package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.List;

public class ContractInfo {


    /**
     * file : null
     * id : 7737817761375780864
     * name : 去玩儿群
     * contractSalary : 123.00
     * projName : 张毓军项目勿删
     * signDate : 2018-10-10
     * contractEndDate : 2018-10-10
     * laborCompany : 劳务公司
     * fileId : null
     * fileIds : ["7737818622848073729","7737825450986242049"]
     */

    private String file;
    private String id;
    private String name;
    private String contractSalary;
    private String projName;
    private String signDate;
    private String contractEndDate;
    private String laborCompany;
    private String fileId;
    private List<String> fileIds;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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

    public String getContractSalary() {
        return contractSalary;
    }

    public void setContractSalary(String contractSalary) {
        this.contractSalary = contractSalary;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getLaborCompany() {
        return laborCompany;
    }

    public void setLaborCompany(String laborCompany) {
        this.laborCompany = laborCompany;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public List<String> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<String> fileIds) {
        this.fileIds = fileIds;
    }
}
