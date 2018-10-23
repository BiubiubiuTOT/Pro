package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.content.Context;

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.EnclosureDelegate;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapterdelegates.LightBlueTvDelegate;

import java.util.List;

/**
 * 类描述：质量监督注册
 * 创建人：mhl
 * 创建时间：2018/2/3 14:49
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class FilesAdapter extends ListDelegationAdapter<List<BaseEntity>> {

    public FilesAdapter(Context mContext, List<BaseEntity> items) {
        this.delegatesManager.addDelegate(new LightBlueTvDelegate(mContext));
        this.delegatesManager.addDelegate(new EnclosureDelegate(mContext));
        this.delegatesManager.setFallbackDelegate(new EnclosureDelegate(mContext));
        setItems(items);
    }
}
