package com.ljkj.qxn.wisdomsitepro.manager;


import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ljkj.qxn.wisdomsitepro.Utils.OkGoHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.SharedPreferencesUtil;
import com.ljkj.qxn.wisdomsitepro.WApplication;
import com.ljkj.qxn.wisdomsitepro.data.entity.LoginInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectInfo;

import java.util.ArrayList;
import java.util.List;

import cdsp.android.util.GsonUtils;

/**
 * 用户管理
 * 创建人：lxx
 * 创建时间：2018/3/21 16:06
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class UserManager {
    private static final String KEY_FIRST = "_key_first2";
    private static final String KEY_LOGIN_INFO = "key_login_info2";

    /** 项目经理 */
    public static final int ROLE_PROJECT_MANGER = 1;
    /** 管理员 */
    public static final int ROLE_ADMIN = 2;
    /** 监理 */
    public static final int ROLE_SUPERVISOR = 3;

    @Deprecated
    private static final String KEY_PROJECT_INFO = "key_project_info2";

    private static UserManager instance;
    private LoginInfo loginInfo;

    private List<OnProjectChangeListener> listeners;

    private UserManager() {
        this.listeners = new ArrayList<>();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            synchronized (UserManager.class) {
                if (instance == null) {
                    instance = new UserManager();
                }
            }
        }
        return instance;
    }

    /**
     * 保存登录信息
     *
     * @param loginInfo loginInfo
     */
    public void saveLoginInfo(@NonNull LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
        String loginJson = GsonUtils.toJSON(loginInfo);
        SharedPreferencesUtil.putString(WApplication.getApplication(), KEY_LOGIN_INFO, loginJson);
        OkGoHelper.getInstance().updateUserTokenHeader(loginInfo.token);
    }

    public void saveFirst(boolean first) {
        SharedPreferencesUtil.putBoolean(WApplication.getApplication(), KEY_FIRST, first);
    }

    public boolean isFirst() {
        return SharedPreferencesUtil.getBoolean(WApplication.getApplication(), KEY_FIRST, true);
    }

    @NonNull
    public LoginInfo getLoginInfo() {
        if (loginInfo == null) {
            String loginJson = SharedPreferencesUtil.getString(WApplication.getApplication(), KEY_LOGIN_INFO, "");
            if (TextUtils.isEmpty(loginJson)) {
                LoginInfo temp = new LoginInfo();
                temp.user = new LoginInfo.User();
                temp.project = new LoginInfo.Project();
                return temp;
            } else {
                loginInfo = GsonUtils.fromJson(loginJson, LoginInfo.class);
            }
        }
        return loginInfo;
    }

    public void clearLoginInfo() {
        loginInfo = null;
        SharedPreferencesUtil.remove(WApplication.getApplication(), KEY_LOGIN_INFO);
    }

    public boolean needLogin() {
        String loginJson = SharedPreferencesUtil.getString(WApplication.getApplication(), KEY_LOGIN_INFO, "");
//        return TextUtils.isEmpty(loginJson) || TextUtils.isEmpty(getLoginInfo().token)
//                || AuthorityManager.getInstance().getAuthorityInfoList().size() == 0;
        return TextUtils.isEmpty(loginJson) || AuthorityManager.getInstance().getAuthorityInfoList().size() == 0;
    }

    /** 用户名 */
    public String getUserName() {
        return getLoginInfo().user.name;
    }

    /** 用户电话号码 */
    public String getUserPhone() {
        return getLoginInfo().user.phone;
    }

    /** 用户id */
    public String getUserId() {
        return getLoginInfo().user.userId;
    }

    /** 用户token */
    public String getUserToken() {
        return getLoginInfo().token;
    }

    @Role
    public int getRoleCode() {
        String codeStr = getLoginInfo().roleCode;
        if ("is_pm".equals(codeStr)) {
            return ROLE_PROJECT_MANGER;
        }
        if ("is_admin".equals(codeStr)) {
            return ROLE_ADMIN;
        }
        if ("is_supervisor".equals(codeStr)) {
            return ROLE_SUPERVISOR;
        }
        return ROLE_PROJECT_MANGER;
    }

    @Deprecated
    public ProjectInfo getProjectInfo() {
        String projectJson = SharedPreferencesUtil.getString(WApplication.getApplication(), KEY_PROJECT_INFO, "");
        if (TextUtils.isEmpty(projectJson)) {
            return null;
        } else {
            return GsonUtils.fromJson(projectJson, ProjectInfo.class);
        }
    }

    /** 项目名称 */
    public String getProjectName() {
        return getLoginInfo().project.projName;
    }

    /** 项目id */
    public String getProjectId() {
        return getLoginInfo().project.projId;
    }

    /** 项目编码 */
    public String getProjectCode() {
        return getLoginInfo().project.projCode;
    }

    /**
     * 是否为项目经理
     */
    public boolean isProjectManager() {
        return getRoleCode() == ROLE_PROJECT_MANGER;
    }

    /** 是否为监理 */
    public boolean isSupervisor() {
        return getRoleCode() == ROLE_SUPERVISOR;
    }

    @Deprecated
    public void addOnProjectChangeListener(OnProjectChangeListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeOnProjectChangeListener(OnProjectChangeListener listener) {
        listeners.remove(listener);
    }

    public interface OnProjectChangeListener {
        void onProjectChange(String projectId, String projectName);
    }

    @IntDef({ROLE_ADMIN, ROLE_PROJECT_MANGER, ROLE_SUPERVISOR})
    @interface Role {
    }
}
