package com.ljkj.qxn.wisdomsitepro.data.entity.contract;

import java.util.List;

/**
 * 类描述：部门成员信息
 * 创建人：rjf
 * 创建时间：2018/6/26 14:38.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class DepartmentMemberInfo {

    //头像路径
    private String avatarUrl;
    //姓名
    private String name;
    //是否管理员
    private boolean isManager;
    //职位
    private List<String> position;


    public String getAvatarUrl() {
        return avatarUrl;
    }

    public DepartmentMemberInfo setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public DepartmentMemberInfo setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isManager() {
        return isManager;
    }

    public DepartmentMemberInfo setManager(boolean manager) {
        isManager = manager;
        return this;
    }

    public List<String> getPosition() {
        return position;
    }

    public DepartmentMemberInfo setPosition(List<String> position) {
        this.position = position;
        return this;
    }
}
