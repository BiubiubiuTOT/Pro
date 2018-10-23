package com.ljkj.qxn.wisdomsitepro.ui.quality.adapter;

import android.content.Context;

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.BlackBlockTvDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.LightBlueTvDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.TvListDelegate;

import java.util.List;

/**
 * 类描述：质量监督注册
 * 创建人：mhl
 * 创建时间：2018/2/3 14:49
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class RegiAndSuperviOfQualityAdapter extends ListDelegationAdapter<List<BaseEntity>> {

    public RegiAndSuperviOfQualityAdapter(Context mContext, List<BaseEntity> items) {
        this.delegatesManager.addDelegate(new LightBlueTvDelegate(mContext));
        this.delegatesManager.addDelegate(new TvListDelegate(mContext));
        this.delegatesManager.addDelegate(new BlackBlockTvDelegate(mContext));
        this.delegatesManager.setFallbackDelegate(new LightBlueTvDelegate(mContext));
        setItems(items);
    }
}
