package com.ljkj.qxn.wisdomsitepro.model;

import com.ljkj.qxn.wisdomsitepro.Utils.OkGoHelper;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;

import java.util.HashMap;

import cdsp.android.http.JsonCallback;
import cdsp.android.model.BaseModel;

public class EquipmentModel extends BaseModel {

    private EquipmentModel() {
    }

    public static EquipmentModel newInstance() {
        return EquipmentModel.ModelHolder.holder;
    }

    private static class ModelHolder {
        private static EquipmentModel holder = new EquipmentModel();
    }

    /** 获取吊塔设备列表 */
    private static final String TOWER_CRANE_EQUIPMENT_LIST_URL = "/equipment/towerCrane/selectListToApp";

    /** 获取吊塔设备司机信息 */
    private static final String TOWER_CRANE_EQUIPMENT_RECORD_URL = "/equipment/OperationRecord/selectByTypeToApp";

    /** 获取吊塔设备详情 */
    private static final String TOWER_CRANE_EQUIPMENT_DETAIL_URL = "/equipment/towerCrane/selectLastData";


    /**
     * 获取吊塔设备列表
     *
     * @param proId            项目id
     * @param abstractCallback abstractCallback
     */
    public void getTowerCraneEquipmentList(String proId, JsonCallback abstractCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("projId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + TOWER_CRANE_EQUIPMENT_LIST_URL, requestParams, TOWER_CRANE_EQUIPMENT_LIST_URL, abstractCallback);
    }

    /**
     * 获取吊塔设备司机信息
     *
     * @param deviceCode       deviceCode
     * @param type             1.正常，2.离线
     * @param abstractCallback abstractCallback
     */
    public void getTowerCraneEquipmentRecord(String deviceCode, int type, JsonCallback abstractCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("deviceCode", deviceCode);
        requestParams.put("type", type);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + TOWER_CRANE_EQUIPMENT_RECORD_URL, requestParams, TOWER_CRANE_EQUIPMENT_RECORD_URL, abstractCallback);
    }

    /**
     * 获取吊塔设备详情
     *
     * @param deviceCode       设备码
     * @param abstractCallback abstractCallback
     */
    public void getTowerCraneEquipmentDetail(String deviceCode, JsonCallback abstractCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("deviceCode", deviceCode);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + TOWER_CRANE_EQUIPMENT_DETAIL_URL, requestParams, TOWER_CRANE_EQUIPMENT_DETAIL_URL, abstractCallback);
    }

}
