package com.ljkj.qxn.wisdomsitepro.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 类描述：安全/质量日志 列表实体
 * 创建人：lxx
 * 创建时间：2018/7/6
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeQualityLogInfo {

    private String id; //日志id

    @SerializedName(value = "createUserName",alternate = "createUsername")
    private String recordPerson; //记录人

    @SerializedName(value = "constructionPosition", alternate = "conSite")
    private String constructionSite; //施工部位

    @SerializedName(value = "constructionDynamic", alternate = "conProcess")
    private String constructionDynamic; //施工工序动态

    @SerializedName("recordDate")
    private String recorderDate; //记录日期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecordPerson() {
        return recordPerson;
    }

    public void setRecordPerson(String recordPerson) {
        this.recordPerson = recordPerson;
    }

    public String getConstructionSite() {
        return constructionSite;
    }

    public void setConstructionSite(String constructionSite) {
        this.constructionSite = constructionSite;
    }

    public String getConstructionDynamic() {
        return constructionDynamic;
    }

    public void setConstructionDynamic(String constructionDynamic) {
        this.constructionDynamic = constructionDynamic;
    }

    public String getRecorderDate() {
        return recorderDate;
    }

    public void setRecorderDate(String recorderDate) {
        this.recorderDate = recorderDate;
    }

}
