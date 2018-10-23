package com.ljkj.qxn.wisdomsitepro.data.entity;

import com.google.gson.annotations.SerializedName;

public class TowerCraneEquipmentDetail {

    /** 载重 */
    public String loads;

    /** 幅度 */
    public String ranges;

    /** 力矩 */
    public String moments;

    /** 高度 */
    public String heights;

    /** 倾角 */
    public String dipAngle;

    /** 风速 */
    public String windSpeed;

    /** 角度 */
    public String rotation;

    /** 员工姓名 */
    public String staffName;

    /** 员工卡号 */
    @SerializedName("staffWorkcardNo")
    public String staffCardNo;


    public String isOnline;

}
