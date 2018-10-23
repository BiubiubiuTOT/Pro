package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;

import cdsp.android.banner.holder.Holder;
import cdsp.android.glide.GlideHelper;

/**
 * 类描述：工程形象进度
 * 创建人：liufei
 * 创建时间：2018/3/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ProgressImageHolderView implements Holder<FileEntity> {
    private ImageView imageView;
    private Context context;

    @Override
    public View createView(Context context) {
        this.context = context;
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(final Context context, int position, FileEntity data) {
        final String url = HostConfig.getFileDownUrl(data.fileId);
        GlideHelper.loadImage(context, imageView, url);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPickerHelper.startPreview((Activity) context, url);
            }
        });
    }
}
