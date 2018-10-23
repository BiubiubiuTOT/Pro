package com.ljkj.qxn.wisdomsitepro.ui.quality.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;
import cdsp.android.util.OpenFileUtil;

/**
 * 查看本地附件
 * Created by lxx on 2018/3/14.
 */

public class ShowAttachmentAdapter extends BaseRecyclerAdapter<FileEntity, ShowAttachmentAdapter.VH> {

    public ShowAttachmentAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attachment_show, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        super.onBindViewHolder(holder, position);
        final FileEntity info = getItem(position);
        holder.attachmentNameText.setText(info.getFileName());
        holder.showText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = OpenFileUtil.openFile(mContext, info.getFilePath());
                mContext.startActivity(intent);
            }
        });
    }

    static class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_attachment_name)
        TextView attachmentNameText;

        @BindView(R.id.tv_show_attachment)
        TextView showText;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
