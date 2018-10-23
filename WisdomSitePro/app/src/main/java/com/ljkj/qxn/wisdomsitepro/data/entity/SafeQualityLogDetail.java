package com.ljkj.qxn.wisdomsitepro.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 类描述：安全/质量日志 详情
 * 创建人：lxx
 * 创建时间：2018/7/9
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeQualityLogDetail {
    public String id;

    /**
     * 记录日期
     */
    @SerializedName("recordDate")
    public String date; //记录日期

    /**
     * 施工部位
     */
    @SerializedName(value = "conSite", alternate = "constructionPosition")
    public String constructionSite; //施工部位

    /**
     * 天气情况 :1晴天，2阴天，3小雨，4大雨
     */
    public String weather; //天气情况 :1晴天，2阴天，3小雨，4大雨

    /**
     * 记录人
     */
    @SerializedName(value = "createUsername", alternate = "createUserName")
    public String recorder; //记录人

    /**
     * 施工工序动态
     */
    @SerializedName(value = "conProcess", alternate = "constructionDynamic")
    public String constructionProcess; //施工工序动态

    /**
     * 安全问题处理情况
     */
    @SerializedName(value = "securityProblem", alternate = "qualityProblemResult")
    public String problem; //质量、安全问题处理情况

    /**
     * 安全状况
     */
    @SerializedName(value = "securityStatus", alternate = "qualitySituation")
    public String situation; //质量、安全状况

}
