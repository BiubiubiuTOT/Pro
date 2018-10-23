package com.ljkj.qxn.wisdomsitepro.data.entity;

/**
 * 类描述：用户信息
 * 创建人：liufei
 * 创建时间：2018/3/12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class UserInfo {

    private String departId;
    private String mobilePhone;
    private String realName;
    private String userId;
    private String userName;
    private String portrait;

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
