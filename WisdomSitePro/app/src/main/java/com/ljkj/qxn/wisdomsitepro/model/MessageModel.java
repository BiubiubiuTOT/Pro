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
 * 类描述：消息
 * 创建人：lxx
 * 创建时间：2018/6/23
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MessageModel extends BaseModel {
    private static MessageModel model;

    /**
     * 发布公告
     */
    private static final String ADD_NOTICE = "/quality/notice/add";

    /**
     * 获取公告列表
     */
    private static final String GET_NOTICE_LIST = "/quality/notice/list";

    /**
     * 获取公告详情
     */
    private static final String GET_NOTICE_DETAIL = "/quality/notice/detail";

    /**
     * 获取公告联系人
     */
    private static final String GET_NOTICE_CONTACT = "/contactsController.do?getContacts";

    public static MessageModel newInstance() {

        if (model == null) {
            model = new MessageModel();
        }
        return model;
    }

    /**
     * 发布公告
     *
     * @param projectId       项目id
     * @param noticeTitle     公告标题
     * @param noticeContent   公告内容
     * @param createUserName  创建用户名称
     * @param createUserId    创建用户id
     * @param acceptUsersId   接收用户Id(用户id,好隔开)
     * @param acceptUsersName 接收用户名称(用户名称, 好隔开)
     * @param file            附件
     */
    public void addNotice(String projectId, String noticeTitle, String noticeContent, String createUserId, String createUserName,
                          String acceptUsersId, String acceptUsersName, List<FileEntity> file, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("projId", projectId);
        requestParams.put("title", noticeTitle);
        requestParams.put("content", noticeContent);
        requestParams.put("createUserId", createUserId);
        requestParams.put("createUserName", createUserName);
        requestParams.put("acceptUsersId", acceptUsersId);
        requestParams.put("acceptUsersName", acceptUsersName);
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
        requestParams.put("file", jsonArray);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + ADD_NOTICE, requestParams, ADD_NOTICE, jsonCallback);
    }

    /**
     * 获取公告列表
     *
     * @param projectId    项目id
     * @param createUserId     用户id
     * @param page         第几页
     * @param size         每页展示几条
     * @param jsonCallback jsonCallback
     */
    public void getNoticeList(String projectId, String createUserId, int page, int size, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("projId", projectId);
        requestParams.put("createUserId", createUserId);
        requestParams.put("pageNum", page);
        requestParams.put("releaseUserName", "");
        requestParams.put("pageSize", size);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + GET_NOTICE_LIST, requestParams, GET_NOTICE_LIST, jsonCallback);
    }

    /**
     * 获取公告详情
     *
     * @param noticeId     公告id
     * @param jsonCallback jsonCallback
     */
    public void getNoticeDetail(String noticeId, String createUserId, String createUserName, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", noticeId);
        requestParams.put("createUserId", createUserId);
        requestParams.put("createUserName", createUserName);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + GET_NOTICE_DETAIL, requestParams, GET_NOTICE_DETAIL, jsonCallback);
    }

    /**
     * 获取公告联系人
     *
     * @param projectId    projectId
     * @param jsonCallback jsonCallback
     */
    public void getNoticeContact(String projectId, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("proId", projectId);
        OkGoHelper.getInstance().get(HostConfig.getHost() + GET_NOTICE_CONTACT, requestParams, GET_NOTICE_CONTACT, jsonCallback);
    }

}
