package com.ljkj.qxn.wisdomsitepro.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：权限信息
 * 创建人：lxx
 * 创建时间：2018/9/29
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AuthorityInfo {
    /** 权限：编辑 */
    public static final int AUTHORITY_EDIT = 1;
    /** 权限：查看 */
    public static final int AUTHORITY_SEE = 2;

    /** 权限：隐藏（本地自己定义的） */
    public static final int AUTHORITY_HIDE = -1;

    /** 模块id */
    public String id;

    /** 模块名称 */
    public String name;

    /** 1:编辑 2：查看 */
    public int isEditAuthority;

    /** 该模块下的功能权限信息 */
    public List<AuthorityInfo> children = new ArrayList<>();

    public AuthorityInfo(String id, String name, int isEditAuthority) {
        this.id = id;
        this.name = name;
        this.isEditAuthority = isEditAuthority;
    }

    public boolean canEdit() {
        return isEditAuthority == AUTHORITY_EDIT;
    }

    public boolean canSee() {
        return isEditAuthority == AUTHORITY_SEE;
    }

    public boolean isHide() {
        return isEditAuthority == AUTHORITY_HIDE;
    }
}
