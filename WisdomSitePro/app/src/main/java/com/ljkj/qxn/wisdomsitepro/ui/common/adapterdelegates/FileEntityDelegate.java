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
import com.ljkj.qxn.wisdomsitepro.Utils.JumpUtil2;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;

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

public class FileEntityDelegate extends AbsListItemAdapterDelegate<FileEntity, BaseEntity, FileEntityDelegate.LightTvBlueViewHolder> {

    private Context mContext;

    public FileEntityDelegate(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected boolean isForViewType(@NonNull BaseEntity item, @NonNull List<BaseEntity> items, int position) {
        return item instanceof FileEntity;
    }

    @NonNull
    @Override
    protected LightTvBlueViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file, parent, false);
        return new LightTvBlueViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final FileEntity item, @NonNull LightTvBlueViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.tvTitle.setText(item.getFileName());

        viewHolder.tvLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil2.toBrowserForEnclosure(mContext, item);
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
