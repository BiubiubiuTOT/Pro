package com.ljkj.qxn.wisdomsitepro.ui.quality.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.TvListItemEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：安全管理人员体系子类
 * 创建人：liufei
 * 创建时间：2017/11/25 16:37
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class TvListAdapter extends BaseRecyclerAdapter<TvListItemEntity, TvListAdapter.ViewHolder> {

    public TvListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_relist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        holder.tvTitle.setText(getItem(position).title);
        holder.tvContent.setText(getItem(position).content);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_content)
        TextView tvContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
