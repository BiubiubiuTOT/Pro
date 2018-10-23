package com.ljkj.qxn.wisdomsitepro.ui.project.adapter;

import android.content.Context;

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;
import com.ljkj.qxn.wisdomsitepro.data.BaseEntity;

import java.util.List;

/**
 * 类描述：管理人员
 * 创建人：liufei
 * 创建时间：2018/1/18 15:16
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ManagerPeopleAdapter extends ListDelegationAdapter<List<BaseEntity>> {

    private Context mContext;

    public ManagerPeopleAdapter(Context mContext, List<BaseEntity> items) {
        this.mContext = mContext;
        this.delegatesManager.addDelegate(new ManagerPeopleRecycleDelegate(mContext));
        this.delegatesManager.addDelegate(new ManagerPeopleDeptDelegate(mContext));
        setItems(items);
    }
}
