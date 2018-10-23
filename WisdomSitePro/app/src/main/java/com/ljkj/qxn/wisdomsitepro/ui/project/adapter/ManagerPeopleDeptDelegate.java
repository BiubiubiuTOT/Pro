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
import com.ljkj.qxn.wisdomsitepro.data.ManagerGroupEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：管理人员 组
 * 创建人：liufei
 * 创建时间：2017/12/7
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ManagerPeopleDeptDelegate extends AbsListItemAdapterDelegate<ManagerGroupEntity, BaseEntity, ManagerPeopleDeptDelegate.ViewHolder> {

    private Context mContext;

    public ManagerPeopleDeptDelegate(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected boolean isForViewType(@NonNull BaseEntity item, @NonNull List<BaseEntity> items, int position) {
        return item instanceof ManagerGroupEntity;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manager_people, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ManagerGroupEntity item, @NonNull ViewHolder holder, @NonNull List<Object> payloads) {
        holder.tvTitle.setText(item.getTitle());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        ManagerPeopleDeptAdapter adapter = new ManagerPeopleDeptAdapter(mContext);
        holder.recyclerView.setAdapter(adapter);
        adapter.loadData(item.getGroupList());
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
