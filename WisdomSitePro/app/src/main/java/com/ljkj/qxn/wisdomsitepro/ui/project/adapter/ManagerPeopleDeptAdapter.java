package com.ljkj.qxn.wisdomsitepro.ui.project.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.ManagerPeopleInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：管理人员 分组
 * 创建人：liufei
 * 创建时间：2017/11/25 16:37
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ManagerPeopleDeptAdapter extends BaseRecyclerAdapter<ManagerPeopleInfo.ManageGroupBean, ManagerPeopleDeptAdapter.ViewHolder> {

    public SparseArray<Boolean> sparseArray = new SparseArray<>();

    public ManagerPeopleDeptAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manager_people_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.tvTitle.setText(getItem(position).getManagerDept().getDeptName());
        List<ManagerPeopleInfo.ManagerDeptPersonBean> managerList = getItem(position).getManagerList();
        setLayout(holder.recyclerView, holder.tvCount, position);

        if (managerList != null) {
            ManagerPeopleChildAdapter adapter = new ManagerPeopleChildAdapter(mContext);
            holder.recyclerView.setAdapter(adapter);
            holder.tvCount.setText(managerList.size() + "人");
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            adapter.loadData(managerList);
        }

        holder.llGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sparseArray.put(position, holder.recyclerView.getVisibility() == View.VISIBLE ? false : true);
                setLayout(holder.recyclerView, holder.tvCount, position);
            }
        });
    }

    private void setLayout(RecyclerView recyclerView, TextView tvMore, int position) {
        recyclerView.setVisibility(sparseArray.get(position) != null && sparseArray.get(position) ? View.VISIBLE : View.GONE);
        if (sparseArray.get(position) != null && sparseArray.get(position)) {
            recyclerView.setVisibility(View.VISIBLE);
            tvMore.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(mContext, R.mipmap.ic_group_up_grey), null);
        } else {
            recyclerView.setVisibility(View.GONE);
            tvMore.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(mContext, R.mipmap.ic_group_down_grey), null);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.ll_group)
        LinearLayout llGroup;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
