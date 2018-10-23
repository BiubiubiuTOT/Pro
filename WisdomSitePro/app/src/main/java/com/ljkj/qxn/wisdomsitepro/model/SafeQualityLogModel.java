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
 * 类描述：安全/质量日志
 * 创建人：lxx
 * 创建时间：2018/9/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeQualityLogModel extends BaseModel {
    private static SafeQualityLogModel model;

    private SafeQualityLogModel() {
    }

    public static SafeQualityLogModel newInstance() {
        if (model == null) {
            model = new SafeQualityLogModel();
        }
        return model;
    }

    /** 安全日志列表 */
    private static final String SAFE_LOG_LIST = "/security/securityLog/list";

    /** 质量日志列表 */
    private static final String QUALITY_LOG_LIST = "/quality/qualityLogs/list";

    /** 安全日志详情 */
    private static final String SAFE_LOG_DETAIL = "/security/securityLog/selectById";

    /** 质量日志详情 */
    private static final String QUALITY_LOG_DETAIL = "/quality/qualityLogs/detail";

    /** 安全日志新增 */
    private static final String SAFE_LOG_ADD = "/security/securityLog/insert";

    /** 质量日志新增 */
    private static final String QUALITY_LOG_ADD = "/quality/qualityLogs/add";


    /**
     * 获取安全日志列表
     *
     * @param proId        项目id
     * @param page         第几页
     * @param size         每页展示几条
     * @param recorder     记录人
     * @param jsonCallback jsonCallback
     */
    public void getSafeLogList(String proId, int page, int size, String recorder, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        params.put("pageNum", page);
        params.put("pageSize", size);
        params.put("createUsername", recorder);
        params.put("recordDate", "");
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SAFE_LOG_LIST, params, SAFE_LOG_LIST, jsonCallback);
    }

    /**
     * 获取质量日志列表
     *
     * @param proId        项目id
     * @param page         第几页
     * @param size         每页展示几条
     * @param recorder     记录人
     * @param jsonCallback jsonCallback
     */
    public void getQualityLogList(String proId, int page, int size, String recorder, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        params.put("pageNum", page);
        params.put("pageSize", size);
        params.put("createUserName", recorder);
        params.put("recordDate", "");
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + QUALITY_LOG_LIST, params, QUALITY_LOG_LIST, jsonCallback);
    }

    /**
     * 获取安全日志详情
     *
     * @param id           日志id
     * @param jsonCallback jsonCallback
     */
    public void getSafeLogDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SAFE_LOG_DETAIL, params, SAFE_LOG_DETAIL, jsonCallback);
    }

    /**
     * 获取质量日志详情
     *
     * @param id           日志id
     * @param jsonCallback jsonCallback
     */
    public void getQualityLogDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + QUALITY_LOG_DETAIL, params, QUALITY_LOG_DETAIL, jsonCallback);
    }

    /**
     * 新增安全日志
     *
     * @param conProcess      施工工序动态
     * @param conSite         施工部位
     * @param recordDate      记录日期 格式为yyyy-MM-dd ,
     * @param createUserId    创建人id
     * @param createUsername  创建人名称 ,
     * @param projCode        项目编码
     * @param projId          项目id
     * @param securityProblem 安全问题处理情况 ,
     * @param securityStatus  安全状况
     * @param weather         天气情况 1晴天，2阴天，3小雨，4大雨
     */
    public void addSafeLog(String conProcess, String conSite, String recordDate, String createUserId, String createUsername, String projCode,
                           String projId, String securityProblem, String securityStatus, String weather, List<FileEntity> file, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("conProcess", conProcess);
        params.put("conSite", conSite);
        params.put("recordDate", recordDate);
        params.put("createUserid", createUserId);
        params.put("createUsername", createUsername);
        params.put("projCode", projCode);
        params.put("projId", projId);
        params.put("securityProblem", securityProblem);
        params.put("securityStatus", securityStatus);
        params.put("weather", weather);
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
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SAFE_LOG_ADD, params, SAFE_LOG_ADD, jsonCallback);
    }

    /**
     * 新增质量日志
     *
     * @param constructionDynamic  施工工序动态
     * @param constructionPosition 施工部位
     * @param recordDate           记录日期 格式为yyyy-MM-dd ,
     * @param projId               项目id
     * @param createUserName       创建人名称
     * @param qualityProblemResult 质量问题处理情况 ,
     * @param qualitySituation     质量状况
     * @param weather              天气情况 1晴天，2阴天，3小雨，4大雨
     */
    public void addQualityLog(String constructionDynamic, String constructionPosition, String recordDate, String projId,
                              String qualityProblemResult, String qualitySituation, String weather, List<FileEntity> file, String createUserName, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("constructionDynamic", constructionDynamic);
        params.put("constructionPosition", constructionPosition);
        params.put("recordDate", recordDate);
        params.put("projId", projId);
        params.put("qualityProblemResult", qualityProblemResult);
        params.put("qualitySituation", qualitySituation);
        params.put("createUserName", createUserName);
        params.put("weather", weather);
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

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + QUALITY_LOG_ADD, params, QUALITY_LOG_ADD, jsonCallback);
    }


}
