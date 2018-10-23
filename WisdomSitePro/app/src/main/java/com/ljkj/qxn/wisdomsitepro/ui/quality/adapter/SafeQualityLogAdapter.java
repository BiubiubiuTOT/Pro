package com.ljkj.qxn.wisdomsitepro.ui.quality.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeQualityLogInfo;

import java.util.List;

/**
 * 类描述：安全/质量日志Adapter
 * 创建人：lxx
 * 创建时间：2018/7/6
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeQualityLogAdapter extends BaseQuickAdapter<SafeQualityLogInfo, BaseViewHolder> {

    public SafeQualityLogAdapter(@Nullable List<SafeQualityLogInfo> data) {
        super(R.layout.adapter_safe_quality_log, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SafeQualityLogInfo item) {
        String name = item.getRecordPerson();
        helper.setText(R.id.tv_avatar, TextUtils.isEmpty(name) ? "" : name.substring(name.length() - 1, name.length()));
        helper.setText(R.id.tv_record_person, "记录人：" + name);
        helper.setText(R.id.tv_date, "日 期：" + item.getRecorderDate());
        helper.setText(R.id.tv_construction_site, "施工部位：" + item.getConstructionSite());
        helper.setText(R.id.tv_construction_dynamic, "施工工序动态：" + item.getConstructionDynamic());
    }

}
