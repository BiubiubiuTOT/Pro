package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectBannerInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.banner.holder.Holder;
import cdsp.android.glide.GlideHelper;

/**
 * 类描述：项目bunner
 * 创建人：liufei
 * 创建时间：2018/3/13 16:22
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ProjectBannerView implements Holder<ProjectBannerInfo> {

    @BindView(R.id.iv_img)
    ImageView ivImg;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_project_banner, null, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void UpdateUI(final Context context, int position, final ProjectBannerInfo data) {
        tvTitle.setText(data.getTitle());
        tvDate.setText(data.getDate());
        GlideHelper.loadImage(context,ivImg,data.getFileId());

//        ivImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ProjectProgressActivity.startActivity(context, data.getDate());
//            }
//        });
    }
}