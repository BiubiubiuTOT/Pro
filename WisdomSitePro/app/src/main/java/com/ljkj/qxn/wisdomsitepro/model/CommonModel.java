package com.ljkj.qxn.wisdomsitepro.model;

import com.ljkj.qxn.wisdomsitepro.BuildConfig;
import com.ljkj.qxn.wisdomsitepro.Utils.OkGoHelper;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.model.BaseModel;

/**
 * 类描述：通用模块
 * 创建人：mhl
 * 创建时间：2018/2/28 9:41
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class CommonModel extends BaseModel {

    private static CommonModel model;

    /**
     * 文件列表查询
     */
    private static final String LIST_FILES_URL = "/commController.do?getFiles";

    /**
     * 数据字典列表查询
     */
    private static final String LIST_DICT_DATA_URL = "/sys/dict/getAllByList";

    /**
     * 文件上传
     */
    private static final String UPLOAD_FILES_URL = "/commController.do?uploadFiles";


    private static final String CHECK_UPDATE = "/appStartController.do?version";


    /**
     * 文件上传v2.0
     */
    private static final String UPLOAD = "/file/upload/";

    /**
     * 查询文件关联信息
     */
    private static final String QUERY_FILE = "/system/fileRelation/queryFileRelation";

    /**
     * 批量删除文件关联信息
     */
    private static final String DELETE_FILE_RELATION = "/system/fileRelation/batchDeleteByIds";

    /**
     * 批量删除文件
     */
    private static final String DELETE_FILES = "http://10.2.100.17:8094/batchDelete";

    /**
     * 获取项目专职安全员列表
     */
    private static final String SECURITY_OFFICER = "/authority/user/querySecurityOfficerList";


    private CommonModel() {

    }

    public static CommonModel newInstance() {
        if (model == null) {
            model = new CommonModel();
        }
        return model;
    }

    /**
     * @param fieldName    文件对象的字段名
     * @param id           文件对应的业务对象id
     * @param jsonCallback
     */
    public void listFiles(String fieldName, String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("fieldName", fieldName);
        params.put("id", id);
        OkGoHelper.getInstance().get(HostConfig.getHost() + LIST_FILES_URL, params, LIST_FILES_URL, jsonCallback);
    }

    /**
     * @param type         key值
     * @param jsonCallback
     */
    public void listDictData(String type, JsonCallback jsonCallback) {
        HttpParams params = new HttpParams();
        params.put("teamCode", type);
        OkGoHelper.getInstance().postV2(HostConfig.HOST_DEBUG2 + LIST_DICT_DATA_URL, params, LIST_DICT_DATA_URL, jsonCallback);
    }

    /**
     * 文件上传
     *
     * @param cgFormField 文件对象的字段名
     * @param cgFormId    对应的业务数据iid
     * @param cgFormName  对应的业务表
     * @param fileKey     文件id	string	这个是编辑文件时的文件id，可以忽略
     */
    public void uploadFiles(String cgFormField, String cgFormId, String cgFormName, String fileKey, List<String> filePaths, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("cgFormField", cgFormField);
        params.put("cgFormId", cgFormId);
        params.put("cgFormName", cgFormName);
        params.put("fileKey", fileKey);

        List<File> files = new ArrayList<>();
        for (String path : filePaths) {
            files.add(new File(path));
        }
        OkGoHelper.getInstance().uploadFiles(HostConfig.getHost() + UPLOAD_FILES_URL, params, files, UPLOAD_FILES_URL, jsonCallback);
    }

    /**
     * 获取最新APP版本信息
     *
     * @param jsonCallback jsonCallback
     */
    public void updateVersion(JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("appVersionCode", BuildConfig.VERSION_CODE);
        OkGoHelper.getInstance().get(HostConfig.getHost() + CHECK_UPDATE, params, CHECK_UPDATE, jsonCallback);
    }

    /**
     * 上传文件
     *
     * @param proId        项目id
     * @param fileList     文件列表
     * @param jsonCallback jsonCallback
     */
    public void upload(String proId, List<File> fileList, JsonCallback jsonCallback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("projId", proId);
        HashMap<String, List<File>> files = new HashMap<>();
        files.put("file", fileList);
        OkGoHelper.getInstance().uploadFiles2(HostConfig.getHost2()+UPLOAD, params, files, HostConfig.getHost2()+UPLOAD, jsonCallback);
    }

    /**
     * 查询文件关联信息
     *
     * @param id           id
     * @param jsonCallback jsonCallback
     */
    public void queryFile(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("relId", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + QUERY_FILE, params, QUERY_FILE, jsonCallback);
    }

    /**
     * 批量删除文件
     *
     * @param fileIds      文件id
     * @param jsonCallback jsonCallback
     */
    public void deleteFiles(List<String> fileIds, JsonCallback jsonCallback) {
        JSONArray jsonArray = new JSONArray();
        for (String fileId : fileIds) {
            jsonArray.put(fileId);
        }
        OkGoHelper.getInstance().postJsonArray(DELETE_FILES, jsonArray, DELETE_FILES, jsonCallback);
    }

    /**
     * 根据文件id批量删除
     *
     * @param deleteFileIds 删除的文件id
     * @param jsonCallback  jsonCallback
     */
    public void deleteFilesRelation(List<String> deleteFileIds, JsonCallback jsonCallback) {
        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < deleteFileIds.size(); i++) {
            ids.append(deleteFileIds.get(i));
            if (i < deleteFileIds.size() - 1) {
                ids.append(",");
            }
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("ids", ids.toString());
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + DELETE_FILE_RELATION, params, DELETE_FILE_RELATION, jsonCallback);
    }

    /**
     * 获取项目专职安全员列表
     *
     * @param projId       项目id
     * @param jsonCallback jsonCallback
     */
    public void getSecurityOfficer(String projId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", projId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SECURITY_OFFICER, params, SECURITY_OFFICER, jsonCallback);
    }
}
