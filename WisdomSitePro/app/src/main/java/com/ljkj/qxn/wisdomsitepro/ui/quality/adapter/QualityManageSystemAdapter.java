package com.ljkj.qxn.wisdomsitepro.ui.quality.adapter;

import android.content.Context;

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.BlackBlockTvDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.EnclosureDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.LightBlueTvDelegate;

import java.util.List;

/**
 * 类描述：质量管理体系
 * 创建人：mhl
 * 创建时间：2018/2/3 10:05
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class QualityManageSystemAdapter extends ListDelegationAdapter<List<BaseEntity>> {

    public QualityManageSystemAdapter(Context mContext, List<BaseEntity> items) {
        this.delegatesManager.addDelegate(new LightBlueTvDelegate(mContext));
        this.delegatesManager.addDelegate(new BlackBlockTvDelegate(mContext));
        this.delegatesManager.addDelegate(new EnclosureDelegate(mContext));
        this.delegatesManager.setFallbackDelegate(new LightBlueTvDelegate(mContext));
        setItems(items);
    }
}
