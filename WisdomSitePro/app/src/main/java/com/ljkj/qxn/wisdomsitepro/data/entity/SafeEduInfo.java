package com.ljkj.qxn.wisdomsitepro.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 类描述：安全教育
 * 创建人：liufei
 * 创建时间：2018/3/12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SafeEduInfo {

    /**
     * 培训时间
     */
    @SerializedName("presentationDate")
    private String date;

    /**
     * 是否上报监督机构
     */
    @SerializedName("isReport")
    private String isSb;

    /**
     * 项目名称
     */
    @SerializedName("presentationName")
    private String name;

    /**
     * 培训老师
     */
    @SerializedName("trainer")
    private String pxPerson;

    /**
     * 培训人员
     */
    @SerializedName("trainee")
    private String students;

    /**
     * ID
     */
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsSb() {
        return isSb;
    }

    public void setIsSb(String isSb) {
        this.isSb = isSb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPxPerson() {
        return pxPerson;
    }

    public void setPxPerson(String pxPerson) {
        this.pxPerson = pxPerson;
    }

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }
}
