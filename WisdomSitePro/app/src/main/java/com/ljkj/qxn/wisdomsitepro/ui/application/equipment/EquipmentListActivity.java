package com.ljkj.qxn.wisdomsitepro.ui.application.equipment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.application.TowerCraneEquipmentContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.TowerCraneEquipmentDetail;
import com.ljkj.qxn.wisdomsitepro.data.entity.TowerCraneEquipmentInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.EquipmentModel;
import com.ljkj.qxn.wisdomsitepro.presenter.application.TowerCraneEquipmentPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.TowerCraneEquipmentAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：设备列表
 * 创建人：lxx
 * 创建时间：2018/7/26
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class EquipmentListActivity extends BaseActivity implements TowerCraneEquipmentContract.View {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.ll_no_data)
    ViewGroup noDataLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private TowerCraneEquipmentPresenter presenter;
    private TowerCraneEquipmentAdapter towerCraneEquipmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_list);
    }

    @Override
    protected void initUI() {
        titleText.setText("设备列表");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected void initData() {
        presenter = new TowerCraneEquipmentPresenter(this, EquipmentModel.newInstance());
        addPresenter(presenter);
        recyclerView.setAdapter(towerCraneEquipmentAdapter = new TowerCraneEquipmentAdapter(null));

        towerCraneEquipmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TowerCraneEquipmentInfo info = (TowerCraneEquipmentInfo) adapter.getItem(position);
                if (info != null) {
                    TowerCraneDetailActivity.startActivity(EquipmentListActivity.this, info.getDeviceName(), info.getDeviceCode());
                }
            }
        });
        presenter.getTowerCraneEquipmentList(UserManager.getInstance().getProjectId());
    }

    @OnClick({R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        noDataLayout.setVisibility(recyclerView.getAdapter().getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showTowerCraneEquipmentList(List<TowerCraneEquipmentInfo> datas) {
        towerCraneEquipmentAdapter.setNewData(datas);
    }

    @Override
    public void showTowerCraneDetail(TowerCraneEquipmentDetail detail) {
    }

    @Override
    public void showTowerCraneRecord() {
    }
}
