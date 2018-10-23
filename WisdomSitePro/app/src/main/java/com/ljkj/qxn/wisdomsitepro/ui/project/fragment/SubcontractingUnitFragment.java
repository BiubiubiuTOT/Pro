package com.ljkj.qxn.wisdomsitepro.ui.project.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.FivePartyInfo;
import com.ljkj.qxn.wisdomsitepro.ui.project.adapter.SubUnitAdapter;

import butterknife.BindView;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：分包单位
 * 创建人：lxx
 * 创建时间：2018/6/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SubcontractingUnitFragment extends BaseFragment implements DataInterface {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static SubcontractingUnitFragment newInstance() {
        SubcontractingUnitFragment fragment = new SubcontractingUnitFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subcontracting_unit, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void updateUIForDetail(FivePartyInfo info) {

    }

    @Override
    public void showSubData(FivePartyInfo info) {
        SubUnitAdapter adapter = new SubUnitAdapter(mContext);
        recyclerView.setAdapter(adapter);
        adapter.loadData(info.getUnitPersonQualificationsVOList());
    }
}