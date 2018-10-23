package com.ljkj.qxn.wisdomsitepro.ui.quality.adapter;

import android.content.Context;

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.EnclosureDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.FileEntityDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.LightBlueTvDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.ReTvDelegate;

import java.util.List;

/**
 * 类描述：混泥土检验详情
 * 创建人：liufei
 * 创建时间：2018/2/2 10:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class QualityInforOfMixedSoilAdapter extends ListDelegationAdapter<List<BaseEntity>> {

    public QualityInforOfMixedSoilAdapter(Context mContext, List<BaseEntity> items) {

        this.delegatesManager.addDelegate(new LightBlueTvDelegate(mContext));
        this.delegatesManager.addDelegate(new FileEntityDelegate(mContext));
        this.delegatesManager.addDelegate(new ReTvDelegate(mContext));
        this.delegatesManager.setFallbackDelegate(new LightBlueTvDelegate(mContext));
        setItems(items);
    }
}
