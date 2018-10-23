package com.ljkj.qxn.wisdomsitepro.ui.quality.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.TvListItemEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：整改流程
 * 创建人：liufei
 * 创建时间：2017/11/25 16:37
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class RectificationProcessAdapter extends BaseRecyclerAdapter<TvListItemEntity, RectificationProcessAdapter.ViewHolder> {

    @Override
    public int getItemCount() {
        return 2;
    }

    public RectificationProcessAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rectification_process, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if (position == getItemCount() - 1) {
            holder.vD.setVisibility(View.GONE);
        } else {
            holder.vD.setVisibility(View.VISIBLE);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_clock)
        ImageView imgClock;

        @BindView(R.id.v_d)
        View vD;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_descri)
        TextView tvDescri;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
