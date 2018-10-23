package com.ljkj.qxn.wisdomsitepro.ui.application.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;

import java.util.List;

/**
 * 类描述：塔吊详情页数据Adapter
 * 创建人：lxx
 * 创建时间：2018/7/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TowerCraneDataAdapter extends BaseQuickAdapter<TowerCraneDataAdapter.Data, BaseViewHolder> {

    public TowerCraneDataAdapter(@Nullable List<TowerCraneDataAdapter.Data> data) {
        super(R.layout.view_tower_crane_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TowerCraneDataAdapter.Data item) {
        helper.setText(R.id.tv_title, item.title);
        helper.setText(R.id.tv_content, item.value);
    }

    public static class Data {
        public Data(String title, String value) {
            this.title = title;
            this.value = value;
        }

        public String title;
        public String value;
    }

}
