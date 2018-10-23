package com.ljkj.qxn.wisdomsitepro.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljkj.qxn.wisdomsitepro.Utils.OkGoHelper;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectProgressAddInfo;

import java.util.HashMap;
import java.util.Map;

import cdsp.android.http.JsonCallback;
import cdsp.android.model.BaseModel;

/**
 * 类描述：项目模块
 * 创建人：liufei
 * 创建时间：2018/3/10 11:29
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectModel extends BaseModel {

    private static ProjectModel model;

    /**
     * 管理人员名单
     */
    private static final String MANAGER_PEOPLE_URL = "/proj/manager/queryManagerAndDeptByProjId";

    /**
     * 施工现场图
     */
    private static final String CONSTRUCTION_SITE_URL = "/proj/constructionPlan/queryConstructionPlanByProjId";

    /**
     * 工程效果图
     */
    private static final String PROJECT_EFFECT_URL = "/proj/effect/queryEffectByProjId";

    /**
     * 工程形象进度
     */
    private static final String PROJECT_PROGRESS_URL = "/proj/progress/queryProgressByProjIdAndProgressDate";

    /**
     * 最新工程形象进度
     */
    private static final String PROJECT_RECENT_PROGRESS_URL = "/proj/progress/queryProgressByProjId";


    private static final String PROJECT_PROGRESS_UPDATE_URL = "/proj/progress/addProgress";

    /**
     * 获取当前天气
     */
    private static final String GET_WEATHER = "/weaTherController.do?getWeather";

    /**
     * 项目统计
     */
    private static final String GET_PROJECT_STATISTICS = "/laborController.do?firstPageStatistics";

    /**
     * 项目编号，项目完整度
     */
    private static final String GET_PROJECT_INFO = "/proj/projectBase/queryProjectBaseByprojId";


    /**
     * 首页项目信息
     */
    private static final String PROJECT_HOME_INFO = "/proj/projectBase/queryProjectHomeByProjId";

    /**
     * 首页劳务数量统计
     */
    private static final String LABOR_COUNT = "/labor/laborStatistics/aboutLaborCount";


    /** 项目实时在场人数变化趋势 */
    private static final String REAL_TIME_PERSON_STATISTICS = "/labor/laborStatistics/projRealAttend";

    /** 项目实时在场班组统计 */
    private static final String PRESENCE_GROUP_STATISTICS = "/labor/laborStatistics/projRealTeamType";

    /** 安全、质量统计 */
    private static final String SAFE_QUALITY_STATISTICS = "/security/count/countAllCheckAndPatrol";

    /** 最近24小时环境数据统计 */
    private static final String ENVIRONMENT_STATISTICS = "/environment/environment/get24HourWeaTher";

    /** 工程概况牌 */
    private static final String PROJECT_OVERVIEW = "/proj/projectBase/queryProjectProfielByProjIdForMobile";


    /** 查询公示牌信息 */
    private static final String GET_BRAND_INFO = "/proj/publicity/queryPublicityByParam";


    /** 获取农名工维权告示牌 */
    private static final String FARMER_INFO = "/proj/farmersProtect/queryFarmersProtectByProjId";

    /**
     * 五方责任主体
     */
    private static final String FIVE_PARTY = "/proj/unit/queryUnitListByProjId";

    /**
     * 分包单位
     */
    private static final String SUB_UNIT = "/proj/unit/querySubUnitListProjId";

    private ProjectModel() {

    }

    public static ProjectModel newInstance() {
        if (model == null) {
            model = new ProjectModel();
        }
        return model;
    }

    /**
     * 管理人员名单
     *
     * @param proId        项目id
     * @param jsonCallback
     */
    public void getManagerPeople(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + MANAGER_PEOPLE_URL, params, MANAGER_PEOPLE_URL, jsonCallback);
    }

    /**
     * 施工现场图
     *
     * @param proId        项目id
     * @param jsonCallback
     */
    public void getConstructionSite(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + CONSTRUCTION_SITE_URL, params, CONSTRUCTION_SITE_URL, jsonCallback);
    }

    /**
     * 工程效果图
     *
     * @param proId        项目id
     * @param jsonCallback
     */
    public void getProjectEffect(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + PROJECT_EFFECT_URL, params, PROJECT_EFFECT_URL, jsonCallback);
    }

    /**
     * 工程形象进度
     *
     * @param proId        项目id
     * @param time         查询日期日期
     * @param jsonCallback
     */
    public void getProjectProgress(String proId, String time, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        params.put("progressDate", time);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + PROJECT_PROGRESS_URL, params, PROJECT_PROGRESS_URL, jsonCallback);
    }

    /**
     * 最新工程形象进度
     *
     * @param proId        项目id
     * @param jsonCallback
     */
    public void getRecentProjectProgress(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + PROJECT_RECENT_PROGRESS_URL, params, PROJECT_RECENT_PROGRESS_URL, jsonCallback);
    }


    /**
     * 工程形象进度-新增
     */
    public void addProjectProgress(ProjectProgressAddInfo info, JsonCallback jsonCallback) {
        String json = JSON.toJSONString(info);
        Map<String, Object> requestParams = JSONObject.toJavaObject(JSON.parseObject(json), Map.class);
        requestParams.put("file", info.getFile());

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + PROJECT_PROGRESS_UPDATE_URL, requestParams, PROJECT_PROGRESS_UPDATE_URL, jsonCallback);
    }

    /**
     * 获取当前天气
     *
     * @param projectId    项目id
     * @param jsonCallback jsonCallback
     */
    public void getWeather(String projectId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);
        OkGoHelper.getInstance().get(HostConfig.getHost() + GET_WEATHER, params, GET_WEATHER, jsonCallback);
    }

    /**
     * 获取项目统计信息
     *
     * @param projectId    项目id
     * @param jsonCallback jsonCallback
     */
    public void getProjectStatistics(String projectId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", projectId);
        OkGoHelper.getInstance().get(HostConfig.getHost() + GET_PROJECT_STATISTICS, params, GET_PROJECT_STATISTICS, jsonCallback);
    }

    /**
     * 获取项目编号，项目进度
     *
     * @param projectId    项目id
     * @param jsonCallback
     */
    public void getProjectInfo(String projectId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projectId);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + GET_PROJECT_INFO, params, GET_PROJECT_INFO, jsonCallback);
    }

    /**
     * 通过projId获取首页项目需要信息
     *
     * @param proId        项目id
     * @param jsonCallback jsonCallback
     */
    public void getProjectHomeInfo(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + PROJECT_HOME_INFO, params, PROJECT_HOME_INFO, jsonCallback);
    }

    /**
     * 首页劳务数量统计
     *
     * @param projId       项目id
     * @param jsonCallback jsonCallback
     */
    public void getLaborCount(String projId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + LABOR_COUNT, params, LABOR_COUNT, jsonCallback);
    }

    /**
     * 项目实时在场人数变化趋势
     *
     * @param projId       项目id
     * @param jsonCallback jsonCallback
     */
    public void getRealTimePersonStatistics(String projId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + REAL_TIME_PERSON_STATISTICS, params, REAL_TIME_PERSON_STATISTICS, jsonCallback);
    }

    /**
     * 项目实时在场班组统计
     *
     * @param projId       项目id
     * @param jsonCallback jsonCallback
     */
    public void getRealTimeTeamStatistics(String projId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + PRESENCE_GROUP_STATISTICS, params, PRESENCE_GROUP_STATISTICS, jsonCallback);
    }

    /**
     * 安全、质量统计
     *
     * @param projId       项目id
     * @param jsonCallback jsonCallback
     */
    public void getSafeQualityStatistics(String projId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SAFE_QUALITY_STATISTICS, params, SAFE_QUALITY_STATISTICS, jsonCallback);
    }

    /**
     * 最近24小时环境数据统计
     *
     * @param projId       项目id
     * @param jsonCallback jsonCallback
     */
    public void getEnvironmentStatistics(String projId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + ENVIRONMENT_STATISTICS, params, ENVIRONMENT_STATISTICS, jsonCallback);
    }

    /**
     * 工程概况牌
     *
     * @param projId       项目id
     * @param jsonCallback jsonCallback
     */
    public void getProjectOverView(String projId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + PROJECT_OVERVIEW, params, PROJECT_OVERVIEW, jsonCallback);
    }

    /**
     * 查询公示牌信息
     *
     * @param projId       项目id
     * @param publicType   公示类型：177,消防保卫牌；176,安全生产牌；178,文明施工牌
     * @param jsonCallback jsonCallback
     */
    public void getBrandInfo(String projId, String publicType, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        params.put("publicType", publicType);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + GET_BRAND_INFO, params, GET_BRAND_INFO, jsonCallback);
    }

    /**
     * 获取农名工维权告示牌
     *
     * @param projId       项目id
     * @param jsonCallback jsonCallback
     */
    public void getFarmerInfo(String projId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + FARMER_INFO, params, FARMER_INFO, jsonCallback);
    }

    /**
     * @param projId       项目id
     * @param jsonCallback jsonCallback
     */
    public void getFivePartyInfo(String projId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + FIVE_PARTY, params, FIVE_PARTY, jsonCallback);
    }

    /**
     * @param projId       项目id
     * @param jsonCallback jsonCallback
     */
    public void getSubInfo(String projId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SUB_UNIT, params, SUB_UNIT, jsonCallback);
    }
}
