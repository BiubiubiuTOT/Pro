package com.ljkj.qxn.wisdomsitepro.ui.contacts.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.QueryMemberInfo;

import java.util.List;

import cdsp.android.glide.GlideHelper;

/**
 * Created by niujingtong on 2018/7/5.
 * 模块：
 */
public class DepartmentMemberAdapter extends BaseQuickAdapter<QueryMemberInfo, BaseViewHolder> {

    public DepartmentMemberAdapter(int layoutResId, @Nullable List<QueryMemberInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryMemberInfo item) {
        String photoId = item.getPhotoId();
        if (photoId != null) {
            final String url = HostConfig.getFileDownUrl(photoId);
            GlideHelper.loadCircleImage(mContext, (ImageView) helper.getView(R.id.iv_contract_avatar),
                    url, R.mipmap.ic_head_default);
        }

        helper.setText(R.id.tv_name, item.getName())
//                .setGone(R.id.tv_manager_tag, item.getRole().equals("isPmRole"))
                .setText(R.id.tv_position, item.getUserType());
    }
}
