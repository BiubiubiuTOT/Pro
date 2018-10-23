package com.ljkj.qxn.wisdomsitepro.ui.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.glide.GlideHelper;
import cdsp.android.ui.BaseRecyclerAdapter;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.ui.common.SelectImageCallback;

import java.io.File;

/**
 * 类描述：图片选择
 * 创建人：mhl
 * 创建时间：2017/12/1 15:20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SelectImagSmallAdapter extends BaseRecyclerAdapter<String, SelectImagSmallAdapter.ViewHolder> {

    private int max = Integer.MAX_VALUE;

    private SelectImageCallback selectImageCallback;

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    public SelectImagSmallAdapter(Context mContext) {
        super(mContext);
    }


    public SelectImagSmallAdapter(Context mContext, int max) {
        super(mContext);
        this.max = max;
    }

    public void setSelectImageCallback(SelectImageCallback selectImageCallback) {
        this.selectImageCallback = selectImageCallback;
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_small_add_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        if (position == super.getItemCount()) {

            //未超出最大图片数量
            if (super.getItemCount() < max) {

                holder.ivImg.setImageResource(R.mipmap.ic_add_file);
                holder.ivImg.setVisibility(View.VISIBLE);

                holder.ivImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selectImageCallback != null) {
                            selectImageCallback.addImage();
                        }
                    }
                });

            } else {
                holder.ivImg.setVisibility(View.GONE);
            }
        } else {
            GlideHelper.loadImage(mContext, holder.ivImg, new File(getItem(position)));
            holder.ivImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectImageCallback != null) {
                        selectImageCallback.viewImage(position);
                    }
                }
            });
        }
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
