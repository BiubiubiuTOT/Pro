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
import com.ljkj.qxn.wisdomsitepro.Utils.JumpUtil;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：附件
 * 创建人：mhl
 * 创建时间：2018/2/3 9:49
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class EnclosureDelegate extends AbsListItemAdapterDelegate<EnclosureInfo, BaseEntity, EnclosureDelegate.LightTvBlueViewHolder> {

    private Context mContext;

    public EnclosureDelegate(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected boolean isForViewType(@NonNull BaseEntity item, @NonNull List<BaseEntity> items, int position) {
        return item instanceof EnclosureInfo;
    }

    @NonNull
    @Override
    protected LightTvBlueViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file, parent, false);
        return new LightTvBlueViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final EnclosureInfo item, @NonNull LightTvBlueViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.tvTitle.setText(item.getTitle());

        viewHolder.tvLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                JumpUtil.toBrowserForEnclosure(mContext,item);
                JumpUtil.toBrowser(mContext,item.getValue());
            }
        });
    }

    static class LightTvBlueViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_load)
        TextView tvLoad;

        public LightTvBlueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
