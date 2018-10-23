package com.ljkj.qxn.wisdomsitepro.manager;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.Utils.SharedPreferencesUtil;
import com.ljkj.qxn.wisdomsitepro.WApplication;
import com.ljkj.qxn.wisdomsitepro.data.AuthorityId;
import com.ljkj.qxn.wisdomsitepro.data.entity.AuthorityInfo;

import java.util.ArrayList;
import java.util.List;

import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.GsonUtils;

public class AuthorityManager {
    private static final String KEY_AUTHORITY = "key_authority";
    private static AuthorityManager instance;

    private static List<AuthorityInfo> authorityInfoList = new ArrayList<>();

    public static AuthorityManager getInstance() {
        if (instance == null) {
            synchronized (AuthorityManager.class) {
                if (instance == null) {
                    instance = new AuthorityManager();
                    String jsonStr = SharedPreferencesUtil.getString(WApplication.getApplication(), KEY_AUTHORITY, "");
                    if (!TextUtils.isEmpty(jsonStr)) {
                        try {
                            authorityInfoList = GsonUtils.jsonToList(jsonStr, new TypeToken<List<AuthorityInfo>>() {
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
        return instance;
    }

    public void saveAuthority(List<AuthorityInfo> list) {
        clear();
        for (AuthorityInfo info : list) {
            authorityInfoList.add(new AuthorityInfo(info.id, info.name, info.isEditAuthority));
            for (AuthorityInfo child : info.children) {
                authorityInfoList.add(new AuthorityInfo(child.id, child.name, child.isEditAuthority));
            }
        }
        String authorityJson = GsonUtils.toJson(authorityInfoList);
        SharedPreferencesUtil.putString(WApplication.getApplication(), KEY_AUTHORITY, authorityJson);
    }

    public void clear() {
        authorityInfoList.clear();
        SharedPreferencesUtil.remove(WApplication.getApplication(), KEY_AUTHORITY);
    }

    public List<AuthorityInfo> getAuthorityInfoList() {
        return authorityInfoList;
    }

    public boolean hasAuthorityInfo() {
        return authorityInfoList.size() > 0;
    }

    /**
     * 获取权限
     *
     * @param authorityId 权限项id  {@link com.ljkj.qxn.wisdomsitepro.data.AuthorityId}
     * @return AuthorityInfo
     */
    @NonNull
    public AuthorityInfo findAuthorityById(@AuthorityId.Id String authorityId) {
        if (!BaseActivity.isHandleAuthority) {
            return new AuthorityInfo(authorityId, "", AuthorityInfo.AUTHORITY_EDIT);
        }

        for (AuthorityInfo info : authorityInfoList) {
            if (authorityId.equals(info.id)) {
                return info;
            }
        }
        return new AuthorityInfo(authorityId, "", AuthorityInfo.AUTHORITY_HIDE);
    }

    /**
     * 根据返回的权限、设置ui展示
     *
     * @param view 所需控制的View
     * @param id   权限项id  {@link com.ljkj.qxn.wisdomsitepro.data.AuthorityId}
     */
    public void handleViewByAuthority(@NonNull View view, @AuthorityId.Id String id) {
        handleViewByAuthority(view, id, false);
    }

    /**
     * 根据返回的权限、设置ui展示
     *
     * @param view      所需控制的View
     * @param id        权限项id  {@link com.ljkj.qxn.wisdomsitepro.data.AuthorityId}
     * @param isAddView 是否为新增按钮ui
     */
    public void handleViewByAuthority(@NonNull View view, @AuthorityId.Id String id, boolean isAddView) {
        AuthorityInfo authorityInfo = findAuthorityById(id);
        if (isAddView) {
            view.setVisibility((authorityInfo.isHide() || authorityInfo.canSee()) ? View.GONE : View.VISIBLE);
        } else {
            view.setVisibility(authorityInfo.isHide() ? View.GONE : View.VISIBLE);
        }
    }
}
