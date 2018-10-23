package com.ljkj.qxn.wisdomsitepro.ui.project.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.ManagerPeopleInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.glide.GlideHelper;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：管理人员 子类
 * 创建人：liufei
 * 创建时间：2017/11/25 16:37
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ManagerPeopleChildAdapter extends BaseRecyclerAdapter<ManagerPeopleInfo.ManagerDeptPersonBean, ManagerPeopleChildAdapter.ViewHolder> {

    public ManagerPeopleChildAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manager_people_child, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        String photoId = getItem(position).getPhotoId();
        if (photoId != null) {
            final String url = HostConfig.getFileDownUrl(photoId);
            GlideHelper.loadImage(mContext, holder.ivImg, url, R.mipmap.ic_head_default, R.mipmap.ic_head_default);
        }

        holder.tvName.setText(getItem(position).getManagerName());
        holder.tvJob.setText(getItem(position).getDeptName());
        holder.tvPhone.setText(getItem(position).getManagerPhone());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_job)
        TextView tvJob;
        @BindView(R.id.tv_phone)
        TextView tvPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
