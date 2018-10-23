package com.ljkj.qxn.wisdomsitepro.data.entity;

import org.json.JSONArray;

/**
 * 混凝土抗压强度检验实体
 * Created by lxx on 2018/3/10.
 */

public class ConcreteCompressiveInfo extends BaseConcreteInfo {

    private String id;
    /**
     * 见证人
     */
    private String witness;

    /**
     * 养护方式
     */
    private String maintenanceMode;

    /**
     * 龄期
     */
    private String nearTerm;

    /**
     * 检验编号
     */
    private String checkCode;


    /**
     * 委托单位
     */
    private String entrustUnit;

    /**
     * 使用部位
     */
    private String usePart;

    /**
     * 检验单位
     */
    private String checkUnit;

    /**
     * 样品名称
     */
    private String sampleName;

    /**
     * 外观质量
     */
    private String appearanceQuality;

    /**
     * 设计强度等级
     */
    private String designStrengthGrade;

    /**
     * 成型日期
     */
    private String formingDate;

    /**
     * 验收附件
     */
    private JSONArray file;

    /**
     * 检验日期
     */
    private String checkDate;

    /**
     * 检验结果
     */
    private String checkResult;

    /**
     * 见证单位
     */
    private String witnessUnit;

    /**
     * 设计抗渗等级
     */
    private String designImperviousGrade;
    private String projId;

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWitnessUnit() {
        return witnessUnit;
    }

    public void setWitnessUnit(String witnessUnit) {
        this.witnessUnit = witnessUnit;
    }

    public String getDesignImperviousGrade() {
        return designImperviousGrade;
    }

    public void setDesignImperviousGrade(String designImperviousGrade) {
        this.designImperviousGrade = designImperviousGrade;
    }

    public String getWitness() {
        return witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
    }

    public String getMaintenanceMode() {
        return maintenanceMode;
    }

    public void setMaintenanceMode(String maintenanceMode) {
        this.maintenanceMode = maintenanceMode;
    }

    public String getNearTerm() {
        return nearTerm;
    }

    public void setNearTerm(String nearTerm) {
        this.nearTerm = nearTerm;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getEntrustUnit() {
        return entrustUnit;
    }

    public void setEntrustUnit(String entrustUnit) {
        this.entrustUnit = entrustUnit;
    }

    public String getUsePart() {
        return usePart;
    }

    public void setUsePart(String usePart) {
        this.usePart = usePart;
    }

    public String getCheckUnit() {
        return checkUnit;
    }

    public void setCheckUnit(String checkUnit) {
        this.checkUnit = checkUnit;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getAppearanceQuality() {
        return appearanceQuality;
    }

    public void setAppearanceQuality(String appearanceQuality) {
        this.appearanceQuality = appearanceQuality;
    }

    public String getDesignStrengthGrade() {
        return designStrengthGrade;
    }

    public void setDesignStrengthGrade(String designStrengthGrade) {
        this.designStrengthGrade = designStrengthGrade;
    }

    public String getFormingDate() {
        return formingDate;
    }

    public void setFormingDate(String formingDate) {
        this.formingDate = formingDate;
    }

    public JSONArray getFile() {
        return file;
    }

    public void setFile(JSONArray file) {
        this.file = file;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    @Override
    public String toString() {
        return "ConcreteCompressiveInfo{" +
                "id='" + id + '\'' +
                ", witness='" + witness + '\'' +
                ", maintenanceMode='" + maintenanceMode + '\'' +
                ", nearTerm=" + nearTerm +
                ", checkCode='" + checkCode + '\'' +
                ", entrustUnit='" + entrustUnit + '\'' +
                ", usePart='" + usePart + '\'' +
                ", checkUnit='" + checkUnit + '\'' +
                ", sampleName='" + sampleName + '\'' +
                ", appearanceQuality='" + appearanceQuality + '\'' +
                ", designStrengthGrade='" + designStrengthGrade + '\'' +
                ", formingDate='" + formingDate + '\'' +
                ", file='" + file + '\'' +
                ", checkDate='" + checkDate + '\'' +
                ", checkResult='" + checkResult + '\'' +
                '}';
    }
}
