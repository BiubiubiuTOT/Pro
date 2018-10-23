package com.ljkj.qxn.wisdomsitepro.data.entity;


import com.google.gson.annotations.SerializedName;

/**
 * 类描述：安全技术交底实体
 * 创建人：lxx
 * 创建时间：2018/9/20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeTechnologyInfo {

    public String id;

    /** 交底名称 */
    @SerializedName("presentationName")
    public String name;

    /** 交底日期 */
    @SerializedName("presentationDate")
    public String date;

    /** 交底部位 */
    @SerializedName("presentationPosition")
    public String position;

    /** 交底人员 */
    @SerializedName("presentationPerson")
    public String person;

    /** 是否上报：0未上报    1上报 */
    public int isReport;
}
