package com.ljkj.qxn.wisdomsitepro.ui.personal.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectInfo;

import java.util.List;

public class ProjectListAdapter extends BaseQuickAdapter<ProjectInfo, BaseViewHolder> {

    public ProjectListAdapter(@Nullable List<ProjectInfo> data) {
        super(R.layout.item_project_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectInfo item) {
        int position = helper.getLayoutPosition();
        switch (position % 3) {
            case 0:
                helper.setBackgroundRes(R.id.ll_item, R.mipmap.project_list_iv1);
                break;
            case 1:
                helper.setBackgroundRes(R.id.ll_item, R.mipmap.project_list_iv2);
                break;
            case 2:
                helper.setBackgroundRes(R.id.ll_item, R.mipmap.project_list_iv3);
                break;
            default:
                break;
        }
        helper.setText(R.id.tv_project_name,item.getName());
    }

}
