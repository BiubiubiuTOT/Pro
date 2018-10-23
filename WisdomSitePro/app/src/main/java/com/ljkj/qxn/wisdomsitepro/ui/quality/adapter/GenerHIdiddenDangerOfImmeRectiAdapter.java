package com.ljkj.qxn.wisdomsitepro.ui.quality.adapter;

import android.content.Context;

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.BlackBlockTvDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.EnclosureDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.ImageListDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.LightBlueTvDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.TvListDelegate;

import java.util.List;

/**
 * 类描述：一般隐患（立即整改）
 * 创建人：mhl
 * 创建时间：2018/2/3 14:49
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class GenerHIdiddenDangerOfImmeRectiAdapter extends ListDelegationAdapter<List<BaseEntity>> {

    public GenerHIdiddenDangerOfImmeRectiAdapter(Context mContext, List<BaseEntity> items) {
        this.delegatesManager.addDelegate(new LightBlueTvDelegate(mContext));
        this.delegatesManager.addDelegate(new TvListDelegate(mContext));
        this.delegatesManager.addDelegate(new BlackBlockTvDelegate(mContext));
        this.delegatesManager.addDelegate(new EnclosureDelegate(mContext));
        this.delegatesManager.addDelegate(new ImageListDelegate(mContext));
        this.delegatesManager.setFallbackDelegate(new LightBlueTvDelegate(mContext));
        setItems(items);
    }
}
