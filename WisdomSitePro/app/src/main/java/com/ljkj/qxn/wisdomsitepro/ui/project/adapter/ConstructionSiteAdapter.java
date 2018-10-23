package com.ljkj.qxn.wisdomsitepro.ui.project.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.glide.GlideHelper;
import cdsp.android.ui.BaseRecyclerAdapter;
import cdsp.android.util.DisplayUtils;

/**
 * 类描述：施工现场
 * 创建人：liufei
 * 创建时间：2017/11/25 16:37
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ConstructionSiteAdapter extends BaseRecyclerAdapter<FileEntity, ConstructionSiteAdapter.ViewHolder> {

    private int height;

    public ConstructionSiteAdapter(Context mContext) {
        super(mContext);
        height = (getWindowWidth() - DisplayUtils.dip2px(mContext, 12.0f)) / 2;
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_construction_site, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.ivImg.getLayoutParams();
        layoutParams.height = height;
        holder.ivImg.setLayoutParams(layoutParams);

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

    private int getWindowWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        ImageView ivImg;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
