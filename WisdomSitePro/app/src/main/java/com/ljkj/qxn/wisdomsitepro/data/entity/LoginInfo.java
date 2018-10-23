package com.ljkj.qxn.wisdomsitepro.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/27 10:48
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LoginInfo {
    /** 用户信息 */
    public User user;

    /** 项目信息 */
    public Project project;

    public String roleId;

    public String roleCode;

    /** 用户token */
    public String token = "";

    public static class Project {
        /** 项目id */
        public String projId;

        /** 项目名称 */
        public String projName;

        /** 项目编码 */
        public String projCode;

        /** 项目地址 */
        public String projAddress;


        public String startDate;
        public String endDate;
    }

    public static class User {
        @SerializedName("id")
        public String userId;

        /** 电话号码 */
        public String phone;

        /** 用户名 */
        public String name;

        /** 部门id */
        public String deptId;

        /** 身份证号 */
        @SerializedName("idcard")
        public String idCard;

        public String roleId;
    }
}
