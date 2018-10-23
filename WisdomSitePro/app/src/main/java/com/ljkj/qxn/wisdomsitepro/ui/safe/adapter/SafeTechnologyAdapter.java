package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeTechnologyInfo;

import java.util.List;

/**
 * 类描述：安全技术交底Adapter
 * 创建人：lxx
 * 创建时间：2018/9/20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeTechnologyAdapter extends BaseQuickAdapter<SafeTechnologyInfo, BaseViewHolder> {

    public SafeTechnologyAdapter(@Nullable List<SafeTechnologyInfo> data) {
        super(R.layout.adapter_safe_technology, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, SafeTechnologyInfo item) {
        helper.setText(R.id.tv_title, item.name);
        helper.setText(R.id.tv_date, "交底日期：" + item.date);
        helper.setText(R.id.tv_position, "交底部位：" + item.position);
        helper.setText(R.id.tv_person, "交底人员：" + item.person);
        if (item.isReport == 0) {
            helper.setImageResource(R.id.iv_status, R.mipmap.ic_safe_edu_no_report);
        } else {
            helper.setImageResource(R.id.iv_status, R.mipmap.ic_safe_edu_report);
        }
    }

}
