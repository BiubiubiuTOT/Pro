package com.ljkj.qxn.wisdomsitepro.ui.application.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.LabelView;
import com.ljkj.qxn.wisdomsitepro.data.entity.TowerCraneEquipmentInfo;

import java.util.HashMap;
import java.util.List;

/**
 * 类描述：设备列表Adapter
 * 创建人：lxx
 * 创建时间：2018/7/26
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TowerCraneEquipmentAdapter extends BaseQuickAdapter<TowerCraneEquipmentInfo, BaseViewHolder> {
    private HashMap<String, String> alarmMap = new HashMap<>();
    private HashMap<String, String> statusMap = new HashMap<>();

    public TowerCraneEquipmentAdapter(@Nullable List<TowerCraneEquipmentInfo> data) {
        super(R.layout.adapter_tower_crane_equipment, data);

        alarmMap.put("00000000", "正常工作");
        alarmMap.put("00000001", "回转限位预警");
        alarmMap.put("00000010", "幅度限位预警");
        alarmMap.put("00000100", "高度限位预警");
        alarmMap.put("00001000", "超载预警");
        alarmMap.put("00010000", "禁入区预警");
        alarmMap.put("00100000", "多机碰撞预警");
        alarmMap.put("01000000", "超风速预警");
        alarmMap.put("10000000", "倾斜预警");


        statusMap.put("000000", "正常工作");
        statusMap.put("000001", "风速传感器故障");
        statusMap.put("000010", "称重传感器故障");
        statusMap.put("000100", "回转传感器故障");
        statusMap.put("001000", "幅度传感器故障");
        statusMap.put("010000", "高度传感器故障");
        statusMap.put("100000", "倾角传感器故障");
    }

    @Override
    protected void convert(BaseViewHolder helper, TowerCraneEquipmentInfo item) {
        helper.setText(R.id.tv_title, item.getDeviceName());
        ItemView codeItem = helper.getView(R.id.item_code);
        ItemView alarmItem = helper.getView(R.id.item_alarm);
        ItemView statusItem = helper.getView(R.id.item_status);
        ItemView accessTimeItem = helper.getView(R.id.item_access_time);
        ItemView runningItem = helper.getView(R.id.item_running);

        codeItem.setContent(item.getDeviceCode());
        alarmItem.setContent(alarmMap.get(item.getAlarmState()));
        statusItem.setContent(statusMap.get(item.getDeviceState()));
        accessTimeItem.setContent(item.getTime());
        runningItem.setContent("");

        LabelView labelView = helper.getView(R.id.lv_status);
        if ("在线".equals(item.getOnline())) {
            labelView.setData("在线", Color.WHITE, Color.parseColor("#8DCA51"));
        } else {
            labelView.setData("离线", Color.WHITE, Color.parseColor("#B8B8B8"));
        }

    }

}
