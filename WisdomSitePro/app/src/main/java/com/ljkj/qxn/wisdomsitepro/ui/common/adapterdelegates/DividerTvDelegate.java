package com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.DividerEntity;

import java.util.List;

import butterknife.ButterKnife;
import cdsp.android.util.DisplayUtils;

/**
 * 类描述：分割线TextView
 * 创建人：mhl
 * 创建时间：2018/2/3 9:49
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class DividerTvDelegate extends AbsListItemAdapterDelegate<DividerEntity, BaseEntity, DividerTvDelegate.LightTvBlueViewHolder> {

    private Context mContext;

    public DividerTvDelegate(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected boolean isForViewType(@NonNull BaseEntity item, @NonNull List<BaseEntity> items, int position) {
        return item instanceof DividerEntity;
    }

    @NonNull
    @Override
    protected LightTvBlueViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {

        TextView textView = new TextView(mContext);
        textView.setBackgroundColor(Color.parseColor("#f5f5f5"));
        return new LightTvBlueViewHolder(textView);
        
    }

    @Override
    protected void onBindViewHolder(@NonNull DividerEntity item, @NonNull LightTvBlueViewHolder viewHolder, @NonNull List<Object> payloads) {

        viewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dip2px(mContext, item.height)));
    }

    static class LightTvBlueViewHolder extends RecyclerView.ViewHolder {

        public LightTvBlueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
