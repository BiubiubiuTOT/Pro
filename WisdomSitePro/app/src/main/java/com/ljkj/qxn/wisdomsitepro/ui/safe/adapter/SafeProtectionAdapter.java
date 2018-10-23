package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.BuildingInfo;

import java.util.List;

/**
 * 类描述：安全防护 Adapter
 * 创建人：lxx
 * 创建时间：2018/9/4
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeProtectionAdapter extends BaseQuickAdapter<BuildingInfo, BaseViewHolder> {
    public boolean showDeleteIcon = false;

    public SafeProtectionAdapter(@Nullable List<BuildingInfo> data) {
        super(R.layout.adapter_safe_protection, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BuildingInfo item) {
        helper.setText(R.id.tv_title, item.buildName);
        helper.setText(R.id.tv_floors, "楼层数：" + item.floors + "层");

        if (helper.getAdapterPosition() % 2 == 0) {
            helper.setImageResource(R.id.iv_right, R.mipmap.ic_safe_protection_orange);
        } else {
            helper.setImageResource(R.id.iv_right, R.mipmap.ic_safe_protection_blue);
        }
    }

}
