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

public class SafeProtectionModel extends BaseModel {
    private static SafeProtectionModel model;

    /**
     * 获取楼栋列表
     */
    private static final String BUILD_LIST = "/security/securityDefend/buildList";

    /**
     * 删除楼栋
     */
    private static final String BUILD_DELETE = "/security/securityDefend/delete";


    /** 更新楼栋 */
    private static final String UPDATE_BUILD = "/securityDefend/updateBuild";

    /** 新增楼栋 */
    private static final String ADD_BUILD = "/security/securityDefend/addBuild";

    /** 第一次新增楼栋 */
    private static final String FIRST_ADD_BUILD = "/security/securityDefend/firstAddBuild";

    /** 获取楼层列表 */
    private static final String FLOOR_LIST = "/security/securityDefend/floorList";

    /** 第一次添加楼层信息,支持批量添加 */
    private static final String FIRST_ADD_FLOOR = "/security/securityDefend/firstAddFloor";

    /** 删除楼层 */
    private static final String DELETE_FLOOR = "/security/securityDefend/delFloor";

    /** 单独添加楼层 */
    private static final String ADD_FLOOR = "/security/securityDefend/addFloor";

    /** 添加楼层防护信息 */
    private static final String ADD_FLOOR_DETAIL = "/security/securityDefend/addFloorImg";

    /** 查询楼层防护信息 */
    private static final String SELECT_FLOOR_DETAIL = "/security/securityDefend/selectById";

    private SafeProtectionModel() {
    }

    public static SafeProtectionModel newInstance() {
        if (model == null) {
            model = new SafeProtectionModel();
        }
        return model;
    }

    /**
     * 获取楼栋列表
     *
     * @param proId        项目Id
     * @param jsonCallback jsonCallback
     */
    public void getBuildList(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + BUILD_LIST, params, BUILD_LIST, jsonCallback);
    }

    /**
     * 删除楼栋
     *
     * @param id           id
     * @param jsonCallback jsonCallback
     */
    public void deleteBuild(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + BUILD_DELETE, params, BUILD_DELETE, jsonCallback);
    }

    /**
     * 更新楼栋
     *
     * @param id           楼栋id
     * @param buildName    楼栋名称
     * @param proId        项目id
     * @param jsonCallback jsonCallback
     */
    public void updateBuild(String id, String buildName, String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("buildName", buildName);
        params.put("projId", proId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + UPDATE_BUILD, params, UPDATE_BUILD, jsonCallback);
    }

    /**
     * 单独新增楼栋
     *
     * @param buildName 楼栋名称
     * @param proId     项目id
     * @param proCode   proCode
     * @param proName   工程名称
     */
    public void addBuild(String buildName, String proId, String proCode, String proName, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projCode", proCode);
        params.put("projName", proName);
        params.put("buildName", buildName);
        params.put("projId", proId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + ADD_BUILD, params, ADD_BUILD, jsonCallback);
    }

    /**
     * 第一次新增楼栋（当没楼栋时，调用这个接口，否则调用addBuild接口）
     *
     * @param buildName 楼栋名称, 第一次添加时不传, 单独添加时传
     * @param num       楼栋数
     * @param proCode   proCode
     * @param proId     项目id
     * @param proName   项目名称
     */
    public void firstAddBuild(String buildName, int num, String proCode, String proId, String proName, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("num", num);
        params.put("projCode", proCode);
        params.put("projName", proName);
        params.put("buildName", buildName);
        params.put("projId", proId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + FIRST_ADD_BUILD, params, FIRST_ADD_BUILD, jsonCallback);
    }

    /**
     * 获取楼栋列表
     *
     * @param buildId      楼栋id
     * @param projCode     projCode
     * @param projId       项目id
     * @param jsonCallback jsonCallback
     */
    public void getFloorList(String buildId, String projCode, String projId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("buildId", buildId);
        params.put("projCode", projCode);
        params.put("projId", projId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + FLOOR_LIST, params, FLOOR_LIST, jsonCallback);
    }

    /**
     * 第一次添加楼层信息
     *
     * @param buildId      楼栋id
     * @param num          新增楼层数量
     * @param projCode     projCode
     * @param projId       项目id
     * @param jsonCallback jsonCallback
     */
    public void firstAddFloors(String buildId, int num, String projCode, String projId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("buildId", buildId);
        params.put("num", num);
        params.put("projCode", projCode);
        params.put("projId", projId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + FIRST_ADD_FLOOR, params, FIRST_ADD_FLOOR, jsonCallback);
    }

    /**
     * 单独添加楼层
     *
     * @param buildId  楼栋id
     * @param projCode projCode
     * @param projId   项目id
     */
    public void addFloor(String buildId, String projCode, String projId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("buildId", buildId);
        params.put("projCode", projCode);
        params.put("projId", projId);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + ADD_FLOOR, params, ADD_FLOOR, jsonCallback);
    }


    /**
     * 删除楼层
     *
     * @param floorId      楼层id
     * @param jsonCallback jsonCallback
     */
    public void deleteFloor(String floorId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", floorId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + DELETE_FLOOR, params, DELETE_FLOOR, jsonCallback);
    }

    /**
     * 获取楼层防护信息
     *
     * @param floorId      楼层id
     * @param jsonCallback jsonCallback
     */
    public void getFloorDetail(String floorId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", floorId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SELECT_FLOOR_DETAIL, params, SELECT_FLOOR_DETAIL, jsonCallback);
    }

    /**
     * 添加楼层防护信息
     *
     * @param floorId      楼层id
     * @param projCode     projCode
     * @param projId       项目id
     * @param content      防护说明
     * @param file         防护图片信息
     * @param jsonCallback jsonCallback
     */
    public void addFloorDetail(String floorId, String projCode, String projId, String content, List<FileEntity> file, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", floorId);
        params.put("projCode", projCode);
        params.put("projId", projId);
        params.put("content", content);
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
                jsonObject.put("relId", fileEntity.relId);
                jsonArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("file", jsonArray);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + ADD_FLOOR_DETAIL, params, ADD_FLOOR_DETAIL, jsonCallback);
    }


}
