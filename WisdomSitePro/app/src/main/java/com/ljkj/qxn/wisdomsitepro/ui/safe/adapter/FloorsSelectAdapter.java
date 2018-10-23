package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.FloorInfo;

import java.util.List;

/**
 * 类描述：楼层选择Adapter
 * 创建人：lxx
 * 创建时间：2018/9/5
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class FloorsSelectAdapter extends BaseQuickAdapter<FloorInfo, BaseViewHolder> {

    public FloorsSelectAdapter(@Nullable List<FloorInfo> data) {
        super(R.layout.adapter_floors_select, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FloorInfo item) {
        helper.setText(R.id.tv_floor, item.floorName);
    }

}
