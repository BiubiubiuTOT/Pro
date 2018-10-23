package com.ljkj.qxn.wisdomsitepro.ui.project.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectProgressInfo;

import java.util.List;


public class ProjectTimeAdapter extends BaseQuickAdapter<ProjectProgressInfo, BaseViewHolder> {

    public ProjectTimeAdapter(@Nullable List<ProjectProgressInfo> data) {
        super(R.layout.adapter_project_time, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectProgressInfo item) {
        helper.setText(R.id.tv_time, item.getProgressDate());
    }


}
