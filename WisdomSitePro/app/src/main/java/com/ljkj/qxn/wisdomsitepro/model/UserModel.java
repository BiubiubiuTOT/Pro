package com.ljkj.qxn.wisdomsitepro.model;

import com.ljkj.qxn.wisdomsitepro.Utils.OkGoHelper;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;

import java.util.HashMap;

import cdsp.android.http.JsonCallback;
import cdsp.android.model.BaseModel;

/**
 * 类描述：用户
 * 创建人：mhl
 * 创建时间：2018/2/27 10:31
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class UserModel extends BaseModel {

    private static UserModel model;

    /**
     * 登录URL
     */
    private static final String LOGIN_URL = "/loginController.do?login";

    /**
     * 登录URL
     */
    private static final String LOGIN_URL2 = "/authority/authority/login";

    /**
     * 注销登陆
     */
    private static final String LOGIN_OUT_URL = "/authority/authority/logout";

    /**
     * 更改密码URL
     */
    private static final String UPDATE_PWD_URL = "/authority/user/updatePasswordById";

    /**
     * 查询当前登录人信息
     */
    private static final String USER_INFO_URL = "/commController.do?getCurrentUserInfo";

    /**
     * 查询项目列表
     */
    private static final String PROJECT_LIST_URL = "/loginController.do?projectList";


    /**
     * 查询项目列表V2
     */
    private static final String PROJECT_LIST_URL2 = "/projectBase/queryProjectByProjId";


    /**
     * 消息列表
     */
    private static final String MSG_LIST_URL = "/commController.do?inspectMsgList";


    /**
     * 查询职务权限菜单
     */
    private static final String AUTHORITY_CONFIG = "/authority/authority/mainMenusApp";

    private UserModel() {

    }

    public static UserModel newInstance() {

        if (model == null) {
            model = new UserModel();
        }
        return model;
    }

    /**
     * 用户登录
     *
     * @param name
     * @param password
     * @param jsonCallback
     */
    public void login(String name, String password, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);
        OkGoHelper.getInstance().get(HostConfig.getHost() + LOGIN_URL, params, LOGIN_URL, jsonCallback);

    }

    /**
     * 登录
     *
     * @param name         手机号
     * @param password     密码
     * @param jsonCallback
     */
    public void loginV2(String name, String password, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("phone", name);
        params.put("password", password);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + LOGIN_URL2, params, LOGIN_URL2, jsonCallback);
    }

    /**
     * 修改密码
     *
     * @param newPassword  新密码
     * @param projId       项目id
     * @param password     原密码
     * @param userId       用户id
     * @param jsonCallback jsonCallback
     */
    public void updatePwd(String userId, String projId, String newPassword, String password, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("newPassword", newPassword);
        params.put("password", password);
        params.put("id", userId);
        params.put("projId", projId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + UPDATE_PWD_URL, params, UPDATE_PWD_URL, jsonCallback);

    }

    /**
     * 查询当前登录人信息
     *
     * @param jsonCallback
     */
    public void getUserInfo(JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        OkGoHelper.getInstance().post(HostConfig.getHost() + USER_INFO_URL, params, USER_INFO_URL, jsonCallback);

    }

    /**
     * 获取项目列表
     */
    public void getProjectList(JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();

        OkGoHelper.getInstance().get(HostConfig.getHost() + PROJECT_LIST_URL, params, PROJECT_LIST_URL, jsonCallback);
    }

    public void getProject(String projId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + PROJECT_LIST_URL2, params, PROJECT_LIST_URL2, jsonCallback);
    }

    /**
     * 消息列表
     *
     * @param page         当前页	默认为1，表示第一页
     * @param proId        项目id
     * @param rows         每页展现几条
     * @param status       消息状态		状态，1：新消息（待处理）；2：已读消息（历史消息）
     * @param jsonCallback jsonCallback
     */
    public void getMessageList(int page, String proId, int rows, int status, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("page", page);
        requestParams.put("proId", proId);
        requestParams.put("rows", rows);
        requestParams.put("status", status + "");
        OkGoHelper.getInstance().get(HostConfig.getHost() + MSG_LIST_URL, requestParams, MSG_LIST_URL, jsonCallback);
    }


    /**
     * 注销登陆
     *
     * @param userToken    用户Token
     * @param jsonCallback jsonCallback
     */
    public void logout(String userToken, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("token", userToken);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + LOGIN_OUT_URL, params, LOGIN_OUT_URL, jsonCallback);
    }

    /**
     * 查询职务权限菜单
     *
     * @param roleId       角色id
     * @param userId       用户id
     * @param jsonCallback jsonCallback
     */
    public void getAuthority(String roleId, String userId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", roleId);
        params.put("userId", userId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + AUTHORITY_CONFIG, params, AUTHORITY_CONFIG, jsonCallback);
    }
}
