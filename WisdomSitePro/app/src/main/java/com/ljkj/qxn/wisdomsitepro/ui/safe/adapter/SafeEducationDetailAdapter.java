package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.content.Context;

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.BlackBlockTvDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.DividerTvDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.EnclosureDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.ImageListDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.LightBlueTvDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.ReTvDelegate;

import java.util.List;

/**
 * 类描述：安全教育详情
 * 创建人：mhl
 * 创建时间：2018/3/14 14:12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SafeEducationDetailAdapter extends ListDelegationAdapter<List<BaseEntity>> {

    public SafeEducationDetailAdapter(Context mContext, List<BaseEntity> items) {

        this.delegatesManager.addDelegate(new LightBlueTvDelegate(mContext));
        this.delegatesManager.addDelegate(new ReTvDelegate(mContext));
        this.delegatesManager.addDelegate(new BlackBlockTvDelegate(mContext));
        this.delegatesManager.addDelegate(new ImageListDelegate(mContext));
        this.delegatesManager.addDelegate(new DividerTvDelegate(mContext));
        this.delegatesManager.addDelegate(new EnclosureDelegate(mContext));
        this.delegatesManager.setFallbackDelegate(new EnclosureDelegate(mContext));
        setItems(items);
    }
}

