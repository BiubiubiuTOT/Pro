package com.ljkj.qxn.wisdomsitepro.model;

import com.ljkj.qxn.wisdomsitepro.Utils.OkGoHelper;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.model.BaseModel;

/**
 * 类描述：应用
 * 创建人：mhl
 * 创建时间：2018/2/26 10:49
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ApplicationModel extends BaseModel {

    private static ApplicationModel model;

    /**
     * 工资列表
     */
    private static String WAGELIST_URL = "/labor/laborWage/queryMemberList";

    /**
     * 月工资总额
     */
    private static String PROSFGZSTATISTICS_URL = "/laborController.do?proSfgzStatistics";

    /**
     * 考勤列表
     */
    private static String ATTENDANCELIST_URL = "/labor/laborAttendance/queryMemberList";

    /**
     * 考勤历史
     */
    private static String ATTENDANCEHISTORY_URL = "/laborController.do?attendanceHistory";

    /**
     * 施工日志列表
     */
    private static String CONSTRUCT_LOG_LIST_URL = "/management/managementConstructionLogs/selectByProjId";

    /**
     * 新增施工日志
     */
    private static String CONSTRUCT_ADD_LOG = "/management/managementConstructionLogs/insert";

    /**
     * 通过projId获取施工单位信息---提供给施工日志
     */
    private static String CONSTRUCT_ADD_LOG_INFO = "/proj/unit/queryConstructionUnitByProjId";

    /**
     * 施工日志详情
     */
    private static String CONSTRUCT_LOG_DETAIL = "/management/managementConstructionLogs/selectById";

    /**
     * 实时监控
     */
    private static String VIDEO_LIST_URL = "/videoMonitor.do?video";


    /**
     * 视频监控
     */
    private static String VIDEO_MONITOR_SOURCE = "/video/videoMonitor/queryAllToApp";

    /**
     * 人员班组列表
     */
    private static String TEAM_PERSON_LIST = "/labor/laborPerson/queryTeamList";

    /**
     * 考勤班组列表
     */
    private static String TEAM_ATTENDANCE_LIST = "/labor/laborAttendance/queryTeamList";

    /**
     * 人员列表
     */
    private static String LABOR_MEMBER_LIST_URL = "/labor/laborPerson/queryMemberList";

    /**
     * 人员详情
     */
    private static String LABOR_MEMBER_DETAIL = "/labor/laborPerson/queryPersonInfo";

    /**
     * 考勤人员列表
     */
    private static String LABOR_ATTENDANCE_LIST_URL = "/labor/laborAttendance/queryMemberList";

    /**
     * 合同列表
     */
    private static String CONTRACT_LIST_URL = "/labor/laborContract/queryContractList";

    /**
     * 信用记录
     */
    private static String CREDIT_LIST_URL = "/labor/laborCreditRecord/queryByPersonId";

    /**
     * 工伤记录
     */
    private static String INJURY_LIST_URL = "/labor/laborInjuryRecord/queryInjuryList";

    /**
     * 工作经验
     */
    private static String EXPERIENCE_LIST_URL = "/labor/laborExperienceRecord/queryExperienceRecordList";

    /**
     * 技能
     */
    private static String SKILL_LIST_URL = "/labor/laborPersonSkills/queryPersonSkillsList";


    private ApplicationModel() {
    }

    public static ApplicationModel newInstance() {

        if (model == null) {
            model = new ApplicationModel();
        }
        return model;
    }

    /**
     * 劳务人员列表查询
     *
     * @param searchKey    搜索关键字：	姓名、分包单位、身份证号、电话
     * @param page         当前页,默认为1，表示第一页
     * @param proId        当前项目id
     * @param rows         每页展现几条
     * @param jsonCallback
     */
    public void laborList(String searchKey, int page, String proId, int rows, String id
            , JsonCallback jsonCallback) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("name", searchKey);
        params.put("projId", proId);
        params.put("pageNum", page);
        params.put("pageSize", rows);
        params.put("teamsType", id);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + LABOR_MEMBER_LIST_URL, params, LABOR_MEMBER_LIST_URL, jsonCallback);
    }

    /**
     * 劳务人员详情
     *
     * @param jsonCallback
     */
    public void getLaborDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + LABOR_MEMBER_DETAIL, params, LABOR_MEMBER_DETAIL, jsonCallback);
    }

    /**
     * 工资列表查询
     *
     * @param month        发放月份
     * @param searchKey    姓名、身份证、分包单位
     * @param page         默认为1，表示第一页
     * @param proId        当前项目id
     * @param rows         每页展现几条
     * @param teams        班组
     * @param jsonCallback
     */
    public void wageList(String month, String searchKey, int page, String proId, int rows, String workType, String teams, JsonCallback jsonCallback) {

        HashMap<String, Object> params = new HashMap<>();
//        params.put("payWageMonth", month);
        params.put("name", searchKey);
        params.put("projId", proId);
//        params.put("workType", workType);
//        params.put("teams", teams);
        params.put("pageNum", page);
        params.put("pageSize", rows);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + WAGELIST_URL, params, WAGELIST_URL, jsonCallback);
    }

    /**
     * 月份工资总额
     *
     * @param month
     * @param proId
     * @param jsonCallback
     */
    public void proSfgzStatistics(String month, String proId, JsonCallback jsonCallback) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("month", month);
        params.put("proId", proId);
        OkGoHelper.getInstance().get(HostConfig.getHost() + PROSFGZSTATISTICS_URL, params, PROSFGZSTATISTICS_URL, jsonCallback);
    }


    /**
     * 考勤列表
     *
     * @param searchKey    姓名、地址
     * @param page
     * @param proId
     * @param rows
     * @param jsonCallback
     */
    public void attendanceList(String searchKey, int page, String proId, int rows, String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", searchKey);
        params.put("projId", proId);
        params.put("pageNum", page);
        params.put("pageSize", rows);
        params.put("teamsType", id);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + ATTENDANCELIST_URL, params, ATTENDANCELIST_URL, jsonCallback);
    }

    /**
     * 考勤历史
     *
     * @param laborPersonId 某个劳务人员id
     * @param month         当前考勤月份
     * @param page          当前页
     * @param proId         当前项目id
     * @param rows          每页展现几条
     * @param jsonCallback
     */
    public void attendanceHistory(String laborPersonId, String month, int page, String proId, int rows, JsonCallback jsonCallback) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("laborPersonId", laborPersonId);
        params.put("month", month);
        params.put("page", page);
        params.put("proId", proId);
        params.put("rows", rows);
        OkGoHelper.getInstance().post(HostConfig.getHost() + ATTENDANCEHISTORY_URL, params, ATTENDANCEHISTORY_URL, jsonCallback);
    }

    /**
     * 施工日志列表
     *
     * @param projId       项目id
     * @param page         第几页
     * @param size         每页展示几条
     * @param date         日期
     * @param recorder     记录人
     * @param jsonCallback jsonCallback
     */
    public void constructLogList(String projId, int page, int size, String date, String recorder, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        params.put("pageNum", page);
        params.put("pageSize", size);
        params.put("recorder", recorder);
        params.put("date", date);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + CONSTRUCT_LOG_LIST_URL, params, CONSTRUCT_LOG_LIST_URL, jsonCallback);
    }

    /**
     * 新增施工日志
     *
     * @param constructionUnit 施工单位
     * @param date             日期
     * @param emergency        突发情况
     * @param maxMinTemp       最高/最低温度
     * @param proHead          项目负责人
     * @param proId            项目ID
     * @param proName          项目名称
     * @param proNo            项目编号
     * @param production       生产情况记录
     * @param qualitySafety    技术质量安全工作记录
     * @param recorder         记录人
     * @param weather          天气
     * @param wind             风力等级
     * @param file             图片
     */
    public void saveConstructLog(String constructionUnit, String date, String emergency, String maxMinTemp, String proHead,
                                 String proId, String proName, String proNo, String production, String qualitySafety, String recorder,
                                 String weather, String wind, List<FileEntity> file, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        JSONArray jsonArray = new JSONArray();
        try {
            for (FileEntity fileEntity : file) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("fileExt", fileEntity.fileExt);
                jsonObject.put("fileId", fileEntity.fileId);
                jsonObject.put("fileName", fileEntity.fileName);
                jsonObject.put("fileSize", fileEntity.fileSize);
                jsonObject.put("type", fileEntity.type);
                jsonObject.put("projCode", fileEntity.projCode);
                jsonObject.put("projId", fileEntity.projId);
                jsonArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("file", jsonArray);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("constructionUnit", constructionUnit);
            jsonObject.put("date", date);
            jsonObject.put("emergency", emergency);
            jsonObject.put("temp", maxMinTemp);
            jsonObject.put("projHead", proHead);
            jsonObject.put("projId", proId);
            jsonObject.put("projName", proName);
            jsonObject.put("projNo", proNo);
            jsonObject.put("production", production);
            jsonObject.put("qualitySafety", qualitySafety);
            jsonObject.put("recorder", recorder);
            jsonObject.put("weather", weather);
            jsonObject.put("wind", wind);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("logs", jsonObject);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + CONSTRUCT_ADD_LOG, params, CONSTRUCT_ADD_LOG, jsonCallback);
    }

    /**
     * 获取施工日志详情
     *
     * @param id 施工日志对应ID
     */
    public void getLogDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + CONSTRUCT_LOG_DETAIL, params, CONSTRUCT_LOG_DETAIL, jsonCallback);
    }

    /**
     * 获取施工日志新增页面保存所需信息
     *
     * @param proId 当前项目ID
     */
    public void getConstructLogAddInfo(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + CONSTRUCT_ADD_LOG_INFO, params, CONSTRUCT_ADD_LOG_INFO, jsonCallback);
    }


    /**
     * 视频监控资源
     *
     * @param proId        当前项目ID
     * @param jsonCallback jsonCallback
     */
    public void getVideoSource(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        OkGoHelper.getInstance().get(HostConfig.getHost() + VIDEO_LIST_URL, params, VIDEO_LIST_URL, jsonCallback);
    }

    /**
     * 视频监控资源
     *
     * @param proId        当前项目ID
     * @param jsonCallback jsonCallback
     */
    public void getVideoMonitorSource(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + VIDEO_MONITOR_SOURCE, params, VIDEO_MONITOR_SOURCE, jsonCallback);
    }

    /**
     * 获取人员班组列表
     *
     * @param proId        当前项目ID
     * @param jsonCallback jsonCallback
     */
    public void getTeamPersonList(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + TEAM_PERSON_LIST, params, TEAM_PERSON_LIST, jsonCallback);
    }

    /**
     * 获取考勤班组列表
     *
     * @param proId        当前项目ID
     * @param jsonCallback jsonCallback
     */
    public void getTeamAttendanceList(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + TEAM_ATTENDANCE_LIST, params, TEAM_ATTENDANCE_LIST, jsonCallback);
    }


    /**
     * 合同列表
     *
     * @param jsonCallback jsonCallback
     */
    public void getContractList(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("personId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + CONTRACT_LIST_URL, params, CONTRACT_LIST_URL, jsonCallback);
    }


    /**
     * 信用记录列表
     *
     * @param jsonCallback jsonCallback
     */
    public void getCreditList(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("personId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + CREDIT_LIST_URL, params, CREDIT_LIST_URL, jsonCallback);
    }

    /**
     * 信用记录列表
     *
     * @param jsonCallback jsonCallback
     */
    public void getExperienceList(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("personId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + EXPERIENCE_LIST_URL, params, EXPERIENCE_LIST_URL, jsonCallback);
    }

    /**
     * 信用记录列表
     *
     * @param jsonCallback jsonCallback
     */
    public void getInjuryList(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("personId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + INJURY_LIST_URL, params, INJURY_LIST_URL, jsonCallback);
    }

    /**
     * 信用记录列表
     *
     * @param jsonCallback jsonCallback
     */
    public void getSkillList(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("personId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SKILL_LIST_URL, params, SKILL_LIST_URL, jsonCallback);
    }
}
