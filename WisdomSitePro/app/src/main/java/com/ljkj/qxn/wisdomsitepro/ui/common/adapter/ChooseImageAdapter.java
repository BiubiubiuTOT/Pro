package com.ljkj.qxn.wisdomsitepro.ui.common.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;

import java.io.File;
import java.util.List;

import cdsp.android.glide.GlideHelper;

public class ChooseImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private int maxImageCount;
    private Callback callback;

    public ChooseImageAdapter(@Nullable List<String> data) {
        this(data, 9);
    }

    public ChooseImageAdapter(@Nullable List<String> data, int maxImageCount) {
        super(R.layout.adapter_choose_image, data);
        this.maxImageCount = maxImageCount;
        setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_delete:
                        if (callback != null) {
                            callback.deleteImage(position);
                        }
                        break;
                    case R.id.iv_image:
                        if (position == ChooseImageAdapter.super.getItemCount()) { //添加图片
                            if (callback != null) {
                                callback.addImage();
                            }
                        } else { //点击图片预览
                            if (callback != null) {
                                callback.previewImage(position);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.iv_delete);
        helper.addOnClickListener(R.id.iv_image);
        helper.setVisible(R.id.iv_image, true);
        helper.setVisible(R.id.iv_delete, true);

        int position = helper.getAdapterPosition();
        if (position == super.getItemCount()) {
            helper.setGone(R.id.iv_delete, false);
            if (super.getItemCount() < maxImageCount) {
                helper.setImageResource(R.id.iv_image, R.mipmap.ic_add_file);
            } else {
                helper.setGone(R.id.iv_image, false);
            }

        } else {
            if (item.startsWith("http")) {
                GlideHelper.loadImage(mContext, (ImageView) helper.getView(R.id.iv_image), item);
            } else {
                GlideHelper.loadImage(mContext, (ImageView) helper.getView(R.id.iv_image), new File(item));
            }

        }

    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void addImage();

        void deleteImage(int position);

        void previewImage(int position);
    }

}
