package com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.BlockEntryEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：黑色块状TextView
 * 创建人：mhl
 * 创建时间：2018/2/3 9:49
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class BlackBlockTvDelegate extends AbsListItemAdapterDelegate<BlockEntryEntity, BaseEntity, BlackBlockTvDelegate.LightTvBlueViewHolder> {

    private Context mContext;

    public BlackBlockTvDelegate(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected boolean isForViewType(@NonNull BaseEntity item, @NonNull List<BaseEntity> items, int position) {
        return item instanceof BlockEntryEntity;
    }

    @NonNull
    @Override
    protected LightTvBlueViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_block_tv_black, parent, false);
        return new LightTvBlueViewHolder(view);

    }

    @Override
    protected void onBindViewHolder(@NonNull BlockEntryEntity item, @NonNull LightTvBlueViewHolder viewHolder, @NonNull List<Object> payloads) {

        viewHolder.tvContent.setText(item.entryName);
    }

    static class LightTvBlueViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tv_content)
        TextView tvContent;

        public LightTvBlueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
