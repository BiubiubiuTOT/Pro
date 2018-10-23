package com.ljkj.qxn.wisdomsitepro.ui.common.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.glide.GlideHelper;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：图片选择
 * 创建人：mhl
 * 创建时间：2017/12/1 15:20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ShowImageAdapter extends BaseRecyclerAdapter<FileEntity, ShowImageAdapter.ViewHolder> {

    public ShowImageAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        FileEntity item = getItem(position);
        final String url = HostConfig.getFileDownUrl(item.fileId);
        GlideHelper.loadImage(mContext, holder.ivImg, url);

        holder.ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPickerHelper.startPreview((Activity) mContext, url);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img)
        ImageView ivImg;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
