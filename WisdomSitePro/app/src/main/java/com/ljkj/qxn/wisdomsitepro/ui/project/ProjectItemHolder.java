package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.ProItemEntity;
import com.ljkj.qxn.wisdomsitepro.ui.project.adapter.ProjectItemAdapter;

import java.util.List;

import cdsp.android.banner.holder.Holder;

/**
 * 类描述：项目
 * 创建人：mhl
 * 创建时间：2018/2/1 11:32
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ProjectItemHolder implements Holder<List<ProItemEntity>> {

    private RecyclerView recyclerView;

    private ProjectItemAdapter adapter;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_simple_recycleview_list, null, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, List<ProItemEntity> data) {
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        recyclerView.setAdapter(adapter = new ProjectItemAdapter(context));
        adapter.loadData(data);
    }
}
