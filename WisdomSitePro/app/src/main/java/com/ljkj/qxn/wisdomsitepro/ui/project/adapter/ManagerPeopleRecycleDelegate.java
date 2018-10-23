package com.ljkj.qxn.wisdomsitepro.ui.project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.ManagerPeopleRecycleEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：管理人员
 * 创建人：liufei
 * 创建时间：2017/12/7
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ManagerPeopleRecycleDelegate extends AbsListItemAdapterDelegate<ManagerPeopleRecycleEntity, BaseEntity, ManagerPeopleRecycleDelegate.ViewHolder> {

    private Context mContext;

    public ManagerPeopleRecycleDelegate(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected boolean isForViewType(@NonNull BaseEntity item, @NonNull List<BaseEntity> items, int position) {
        return item instanceof ManagerPeopleRecycleEntity;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manager_people, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ManagerPeopleRecycleEntity item, @NonNull ViewHolder holder, @NonNull List<Object> payloads) {
        holder.tvTitle.setText(item.getTitle());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        ManagerAdapter adapter = new ManagerAdapter(mContext);
        holder.recyclerView.setAdapter(adapter);
        adapter.loadData(item.getChildList());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
