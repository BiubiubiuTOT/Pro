package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardFloorInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.banner.holder.Holder;
import cdsp.android.http.RequestParams;

/**
 * 类描述：项目bunner
 * 创建人：liufei
 * 创建时间：2018/3/13 16:22
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SafeGuardFloorBannerView implements Holder<SafeGuardFloorInfo> {

    @BindView(R.id.iv_img)
    ImageView ivImg;

    private Context context;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_safe_guard_floor_banner, null, false);
        ButterKnife.bind(this, view);
        this.context = context;
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, SafeGuardFloorInfo data) {
        RequestParams params = new RequestParams();
        params.put("fileid", data.getId());
        params.put("isDownload", true);
//        StringBuilder url = new StringBuilder(HostConfig.getHost() + HostConfig.IMAGR_URL + UrlUtil.encodeParams(params));
//        GlideHelper.loadImage(context,ivImg,url.append(UrlUtil.buildProtocolParams(url.toString())).toString());
    }
}