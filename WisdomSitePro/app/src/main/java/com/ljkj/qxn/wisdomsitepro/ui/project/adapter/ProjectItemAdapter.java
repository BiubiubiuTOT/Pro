package com.ljkj.qxn.wisdomsitepro.ui.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.H5Helper;
import com.ljkj.qxn.wisdomsitepro.data.ProItemEntity;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：项目
 * 创建人：mhl
 * 创建时间：2018/2/1 11:14
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ProjectItemAdapter extends BaseRecyclerAdapter<ProItemEntity, ProjectItemAdapter.ProjectItemViewHolder> {

    public ProjectItemAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ProjectItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_item, parent, false);
        return new ProjectItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ProjectItemViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.ivIcon.setImageResource(getItem(position).resId);
        holder.tvTitle.setText(getItem(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItem(position).clazz != null) {
                    mContext.startActivity(new Intent(mContext, getItem(position).clazz));
                } else if (getItem(position).type != -1) {
                    String name = getItem(position).name;
                    if (name.equals("维权告示牌")) {
                        name = "农民工维权告示牌";
                    }
                    H5Helper.toGSP(mContext, name, UserManager.getInstance().getProjectId(), getItem(position).type);
                } else if (TextUtils.equals("节能公示牌", getItem(position).name)) {
                    H5Helper.toEnergyConservation(mContext, "节能公示牌", UserManager.getInstance().getProjectId());
                } else if (TextUtils.equals("工程概况牌", getItem(position).name)) {
                    H5Helper.toProfile(mContext, "工程概况牌", UserManager.getInstance().getProjectId());
                }
            }
        });
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    static class ProjectItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_icon)
        ImageView ivIcon;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        public ProjectItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
