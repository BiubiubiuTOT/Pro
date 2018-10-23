package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ljkj.qxn.wisdomsitepro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：安全评价
 * 创建人：liufei
 * 创建时间：2018/2/2 10:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SafeCommentAdapter extends BaseRecyclerAdapter<String, SafeCommentAdapter.ViewHolder> {

    @Override
    public int getItemCount() {
        return 20;
    }

    public SafeCommentAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_safe_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        //已通过
        if (position % 4 == 0) {
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_pass);
        }

        //未通过
        else if (position % 2 == 0) {
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_not_pass);
        }

        //待审核
        else if (position % 3 == 0) {
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_wait_audit);
        }

        //
        else {
            holder.ivStatus.setVisibility(View.GONE);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_status)
        ImageView ivStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
