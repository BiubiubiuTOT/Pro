package com.ljkj.qxn.wisdomsitepro.ui.application.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.SupervisorStandardInfo;

import java.util.List;

/**
 * 类描述：监理标准规范Adapter
 * 创建人：lxx
 * 创建时间：2018/9/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SupervisorStandardAdapter extends BaseQuickAdapter<SupervisorStandardInfo, BaseViewHolder> {
    public String[] types = {"质量", "安全", "其他"};

    public SupervisorStandardAdapter(@Nullable List<SupervisorStandardInfo> data) {
        super(R.layout.adapter_supervisor_standard, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SupervisorStandardInfo item) {
        helper.setText(R.id.tv_title, item.getName());
        helper.setText(R.id.tv_name, item.getCreateUserName());
        int type = item.getType();
        helper.setText(R.id.tv_type, type != 0 ? "表格分类：" + types[type - 1] : "无");
        helper.setText(R.id.tv_date, "上传日期：" + item.getCreateTime());
    }

}
