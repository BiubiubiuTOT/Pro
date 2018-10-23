package com.ljkj.qxn.wisdomsitepro.model;

import com.ljkj.qxn.wisdomsitepro.Utils.OkGoHelper;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;

import java.util.HashMap;

import cdsp.android.http.JsonCallback;
import cdsp.android.model.BaseModel;

public class AuthorityModel extends BaseModel {
    //根据项目id查询劳务公司班组列表
    private static final String DEPARTMENT_TEAM_URL = "/authority/dept/queryTeamListByProjId";
    //根据项目id和班组id获取班组长
    private static final String TEAM_LEADER_URL = "/authority/user/queryTeamLeaderById";

    private static AuthorityModel model;

    private AuthorityModel() {

    }

    public static AuthorityModel newInstance() {
        if (model == null) {
            model = new AuthorityModel();
        }
        return model;
    }

    /**
     * 根据项目id查询劳务公司班组列表
     *
     * @param proId 项目ID
     */
    public void getDeptTeamList(String proId, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("pageNum", 1);
        params.put("pageSize", 100);
        params.put("projId", proId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + DEPARTMENT_TEAM_URL, params, DEPARTMENT_TEAM_URL, callback);
    }

    /**
     * 根据项目id和班组id获取班组长
     *
     * @param proId 项目ID
     */
    public void getTeamLeader(String deptId, String proId, JsonCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("pageNum", 1);
        params.put("pageSize", 100);
        params.put("projId", proId);
        params.put("deptId", deptId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + TEAM_LEADER_URL, params, TEAM_LEADER_URL, callback);
    }
}
