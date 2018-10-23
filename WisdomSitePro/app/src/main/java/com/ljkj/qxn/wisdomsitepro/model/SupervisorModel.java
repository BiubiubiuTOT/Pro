package com.ljkj.qxn.wisdomsitepro.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljkj.qxn.wisdomsitepro.Utils.OkGoHelper;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.InspectorRecorderManageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SiteStationRecorderManageDetailInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SupervisorRecordManageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;

import java.util.HashMap;
import java.util.Map;

import cdsp.android.http.JsonCallback;
import cdsp.android.model.BaseModel;

/**
 * 类描述：监理管理
 * 创建人：lxx
 * 创建时间：2018/9/3
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SupervisorModel extends BaseModel {
    private static SupervisorModel model;

    /**
     * 查询旁站记录列表
     */
    private static final String SIDE_STATION_RECORD_MANAGE_LIST = "/supervision/sideRecord/list";

    /**
     * 刪除旁站记录列表
     */
    private static final String SIDE_STATION_RECORD_DELETE = "/supervision/sideRecord/list";

    private static final String SIDE_STATION_RECORD_UNIT = "/proj/unit/queryUnitsByProjId";

    /**
     * 旁站记录新增
     */
    private static final String SIDE_STATION_RECORD_ADD = "/supervision/sideRecord/add";

    /**
     * 旁站记录详情
     */
    private static final String SIDE_STATION_RECORD_DETAIL = "/supervision/sideRecord/querySideRecordById";

    /**
     * 巡视记录列表
     */
    private static final String PATROL_RECORD_LIST = "/supervision/patrolRecord/list";
    /**
     * 巡视记录详情
     */
    private static final String PATROL_RECORD_DETAIL = "/supervision/patrolRecord/queryPatrolRecordById";
    /**
     * 巡视记录新增
     */
    private static final String PATROL_RECORD_ADD = "/supervision/patrolRecord/add";
    /**
     * 巡视记录删除
     */
    private static final String PATROL_RECORD_DELETE = "/supervision/patrolRecord/delete";

    /**
     * 监理记录列表
     */
    private static final String SUPERVISOR_RECORD_LIST = "/supervision/supervisionRecord/list";
    /**
     * 监理记录新增
     */
    private static final String SUPERVISOR_RECORD_ADD = "/supervision/supervisionRecord/add";
    /**
     * 监理记录详情
     */
    private static final String SUPERVISOR_RECORD_DETAIL = "/supervision/supervisionRecord/querySupervisionRecordById";
    /**
     * 监理记录删除
     */
    private static final String SUPERVISOR_RECORD_DELETE = "/supervision/supervisionRecord/delete";

    /**
     * 查询监理标准规范新增
     */
    private static final String STANDARD_RECORD_ADD = "/supervision/standardSpecification/delete";
    /**
     * 查询监理标准规范删除
     */
    private static final String STANDARD_RECORD_DELETE = "/supervision/standardSpecification/delete";
    /**
     * 查询监理标准规范详情
     */
    private static final String STANDARD_RECORD_DETAIL = "/supervision/standardSpecification/detail";

    /**
     * 查询监理标准规范列表
     */
    private static final String STANDARD_RECORD_LIST = "/supervision/standardSpecification/list";

    public static SupervisorModel newInstance() {
        if (model == null) {
            model = new SupervisorModel();
        }
        return model;
    }

    /**
     * 查询旁站记录列表
     *
     * @param proId        项目id
     * @param recordName   记录人
     * @param beginTime    旁站开始时间
     * @param pageNum      第几页
     * @param pageSize     每页展示几条
     * @param jsonCallback jsonCallback
     */
    public void getSideStationRecordList(String proId, String recordName, String beginTime, int pageNum, int pageSize, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("projId", proId);
        requestParams.put("createUserName", recordName);
        requestParams.put("createUserId", UserManager.getInstance().getUserId());
        requestParams.put("beginTime", beginTime);
        requestParams.put("pageNum", pageNum);
        requestParams.put("pageSize", pageSize);
//        OkGoHelper.getInstance().get(HostConfig.getHost() + SIDE_STATION_RECORD_MANAGE_LIST, requestParams, SIDE_STATION_RECORD_MANAGE_LIST, jsonCallback);

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SIDE_STATION_RECORD_MANAGE_LIST, requestParams, SIDE_STATION_RECORD_MANAGE_LIST, jsonCallback);
    }

    /**
     * 删除旁站记录
     *
     * @param jsonCallback jsonCallback
     */
    public void deleteSideRecord(String id, String name, String userId, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        requestParams.put("createUserName", name);
        requestParams.put("createUserId", userId);

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SIDE_STATION_RECORD_DELETE, requestParams, SIDE_STATION_RECORD_DELETE, jsonCallback);
    }


    /**
     * 新增旁站记录
     *
     * @param jsonCallback jsonCallback
     */
    public void addSideRecord(SiteStationRecorderManageDetailInfo info, JsonCallback jsonCallback) {
        String json = JSON.toJSONString(info);
        Map<String, Object> requestParams = JSONObject.toJavaObject(JSON.parseObject(json), Map.class);
        requestParams.put("file", info.getFile());

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SIDE_STATION_RECORD_ADD, requestParams, SIDE_STATION_RECORD_ADD, jsonCallback);
    }

    /**
     * 旁站记录详情
     *
     * @param jsonCallback jsonCallback
     */
    public void getSideRecordDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);

        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SIDE_STATION_RECORD_DETAIL, requestParams, SIDE_STATION_RECORD_DETAIL, jsonCallback);
    }


    /**
     * 旁站记录详情
     *
     * @param jsonCallback jsonCallback
     */
    public void getSideRecordUnits(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("projId", id);

        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SIDE_STATION_RECORD_UNIT,
                requestParams, SIDE_STATION_RECORD_UNIT, jsonCallback);
    }


    /**
     * 查询巡视记录列表
     *
     * @param proId        项目id
     * @param recordName   记录人
     * @param beginTime    旁站开始时间
     * @param pageNum      第几页
     * @param pageSize     每页展示几条
     * @param jsonCallback jsonCallback
     */
    public void getInspectorRecordList(String proId, String recordName, String beginTime, int pageNum, int pageSize, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("projId", proId);
        requestParams.put("createUserName", recordName);
        requestParams.put("createUserId", UserManager.getInstance().getUserId());
        requestParams.put("patrolTime", beginTime);
        requestParams.put("pageNum", pageNum);
        requestParams.put("pageSize", pageSize);
//        OkGoHelper.getInstance().get(HostConfig.getHost() + SIDE_STATION_RECORD_MANAGE_LIST, requestParams, SIDE_STATION_RECORD_MANAGE_LIST, jsonCallback);

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + PATROL_RECORD_LIST, requestParams, PATROL_RECORD_LIST, jsonCallback);
    }

    /**
     * 删除巡视记录
     *
     * @param jsonCallback jsonCallback
     */
    public void deleteInspectorRecord(String id, String name, String userId, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        requestParams.put("createUserName", name);
        requestParams.put("createUserId", userId);

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + PATROL_RECORD_DELETE, requestParams, PATROL_RECORD_DELETE, jsonCallback);
    }


    /**
     * 新增巡视记录
     *
     * @param jsonCallback jsonCallback
     */
    public void addInspectorRecord(InspectorRecorderManageInfo info, JsonCallback jsonCallback) {
        String json = JSON.toJSONString(info);
        Map<String, Object> requestParams = JSONObject.toJavaObject(JSON.parseObject(json), Map.class);
        requestParams.put("file", info.getFile());

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + PATROL_RECORD_ADD, requestParams, PATROL_RECORD_ADD, jsonCallback);
    }

    /**
     * 巡视记录详情
     *
     * @param jsonCallback jsonCallback
     */
    public void getInspectorRecordDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);

        OkGoHelper.getInstance().get2(HostConfig.getHost2() + PATROL_RECORD_DETAIL, requestParams, PATROL_RECORD_DETAIL, jsonCallback);
    }

    /**
     * 查询监理记录列表
     *
     * @param proId        项目id
     * @param recordName   记录人
     * @param beginTime    旁站开始时间
     * @param pageNum      第几页
     * @param pageSize     每页展示几条
     * @param jsonCallback jsonCallback
     */
    public void getSupervisorRecordList(String proId, String recordName, String beginTime, int pageNum, int pageSize, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("projId", proId);
        requestParams.put("createUserId", UserManager.getInstance().getUserId());
        requestParams.put("createUserName", recordName);
        requestParams.put("recordTime", beginTime);
        requestParams.put("pageNum", pageNum);
        requestParams.put("pageSize", pageSize);
//        OkGoHelper.getInstance().get(HostConfig.getHost() + SIDE_STATION_RECORD_MANAGE_LIST, requestParams, SIDE_STATION_RECORD_MANAGE_LIST, jsonCallback);

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SUPERVISOR_RECORD_LIST, requestParams, SUPERVISOR_RECORD_LIST, jsonCallback);
    }

    /**
     * 删除监理记录
     *
     * @param jsonCallback jsonCallback
     */
    public void deleteSupervisorRecord(String id, String name, String userId, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        requestParams.put("createUserName", name);
        requestParams.put("createUserId", userId);

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SUPERVISOR_RECORD_DELETE, requestParams, SUPERVISOR_RECORD_DELETE, jsonCallback);
    }


    /**
     * 新增监理记录
     *
     * @param jsonCallback jsonCallback
     */
    public void addSupervisorRecord(SupervisorRecordManageInfo info, JsonCallback jsonCallback) {
        String json = JSON.toJSONString(info);
        Map<String, Object> requestParams = JSONObject.toJavaObject(JSON.parseObject(json), Map.class);
        requestParams.put("file", info.getFile());

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SUPERVISOR_RECORD_ADD, requestParams, SUPERVISOR_RECORD_ADD, jsonCallback);
    }

    /**
     * 监理记录详情
     *
     * @param jsonCallback jsonCallback
     */
    public void getSupervisorRecordDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);

        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SUPERVISOR_RECORD_DETAIL, requestParams, SUPERVISOR_RECORD_DETAIL, jsonCallback);
    }

    /**
     * 查询监理记录列表
     *
     * @param proId        项目id
     * @param recordName   记录人
     * @param pageNum      第几页
     * @param pageSize     每页展示几条
     * @param jsonCallback jsonCallback
     */
    public void getStandardRecordList(String proId, String recordName, int pageNum, int pageSize, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("projId", proId);
        requestParams.put("name", recordName);
        requestParams.put("pageNum", pageNum);
        requestParams.put("pageSize", pageSize);
//        OkGoHelper.getInstance().get(HostConfig.getHost() + SIDE_STATION_RECORD_MANAGE_LIST, requestParams, SIDE_STATION_RECORD_MANAGE_LIST, jsonCallback);

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + STANDARD_RECORD_LIST, requestParams, STANDARD_RECORD_LIST, jsonCallback);
    }

    /**
     * 删除监理记录
     *
     * @param jsonCallback jsonCallback
     */
    public void deleteStandardRecord(String id, String name, String userId, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        requestParams.put("createUserName", name);
        requestParams.put("createUserId", userId);

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + STANDARD_RECORD_DELETE, requestParams, STANDARD_RECORD_DELETE, jsonCallback);
    }


    /**
     * 新增监理记录
     *
     * @param jsonCallback jsonCallback
     */
    public void addStandardRecord(SupervisorRecordManageInfo info, JsonCallback jsonCallback) {
        String json = JSON.toJSONString(info);
        Map<String, Object> requestParams = JSONObject.toJavaObject(JSON.parseObject(json), Map.class);
        requestParams.put("file", info.getFile());

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + STANDARD_RECORD_ADD, requestParams, STANDARD_RECORD_ADD, jsonCallback);
    }

    /**
     * 监理记录详情
     *
     * @param jsonCallback jsonCallback
     */
    public void getStandardRecordDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);

        OkGoHelper.getInstance().get2(HostConfig.getHost2() + STANDARD_RECORD_DETAIL, requestParams, STANDARD_RECORD_DETAIL, jsonCallback);
    }

}
