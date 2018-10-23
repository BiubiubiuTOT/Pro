package com.ljkj.qxn.wisdomsitepro.data.entity;

import com.google.gson.annotations.SerializedName;

public class LaborCountInfo {
    /** 项目实时在场人数 */
    public String actualCount;

    /** 项目历史累计人数 */
    @SerializedName("histroyCouont")
    public String historyCount;

    /** 劳务人员今日出勤率 */
    public String todayRate;

    /** 月平均出勤人数 */
    public String laborEverDayNum;

    /** 今日进场劳务人数 */
    public String inCount;

    /** 今日出场劳务人数 */
    public String outCount;
}
