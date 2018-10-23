package com.ljkj.qxn.wisdomsitepro.ui.application.equipment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.WApplication;
import com.ljkj.qxn.wisdomsitepro.contract.application.TowerCraneEquipmentContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.TowerCraneEquipmentDetail;
import com.ljkj.qxn.wisdomsitepro.data.entity.TowerCraneEquipmentInfo;
import com.ljkj.qxn.wisdomsitepro.model.EquipmentModel;
import com.ljkj.qxn.wisdomsitepro.presenter.application.TowerCraneEquipmentPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.TowerCraneDataAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：塔吊详情
 * 创建人：lxx
 * 创建时间：2018/7/26
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TowerCraneDetailActivity extends BaseActivity implements TowerCraneEquipmentContract.View {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.item_name)
    ItemView nameItem;

    @BindView(R.id.item_card_no)
    ItemView cardNoItem;

    @BindView(R.id.item_time)
    ItemView timeItem;

    @BindView(R.id.item_operate_time)
    ItemView operateTimeItem;

    @BindView(R.id.overlook_view)
    TowerCraneOverlookView overlookView;

    private TowerCraneDataAdapter towerCraneDataAdapter;
    private TowerCraneEquipmentPresenter presenter;
    private String deviceCode;
    private boolean loop;
    private boolean showProgress = true;

    public static void startActivity(Context context, String title, String code) {
        Intent intent = new Intent(context, TowerCraneDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("code", code);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tower_crane_detail);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (loop) {
                presenter.getTowerCraneEquipmentDetail(deviceCode);
                WApplication.getMainHandler().postDelayed(runnable, 60 * 1000);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (!loop) {
            loop = true;
            WApplication.getMainHandler().post(runnable);
        }
    }

    @Override
    protected void initUI() {
        String title = getIntent().getStringExtra("title");
        deviceCode = getIntent().getStringExtra("code");
        titleText.setText(title);
    }

    @Override
    protected void initData() {
        presenter = new TowerCraneEquipmentPresenter(this, EquipmentModel.newInstance());
        addPresenter(presenter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(towerCraneDataAdapter = new TowerCraneDataAdapter(null));
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
    public void showTowerCraneDetail(TowerCraneEquipmentDetail detail) {
        List<TowerCraneDataAdapter.Data> list = new ArrayList<>();
        list.add(new TowerCraneDataAdapter.Data("载重", detail.loads == null ? "" : detail.loads + "t"));
        list.add(new TowerCraneDataAdapter.Data("高度", detail.heights == null ? "" : detail.heights + "m"));
        list.add(new TowerCraneDataAdapter.Data("倾角", detail.dipAngle));
        list.add(new TowerCraneDataAdapter.Data("幅度", detail.ranges + "m"));
        list.add(new TowerCraneDataAdapter.Data("风速", detail.windSpeed + "m/s"));
        list.add(new TowerCraneDataAdapter.Data("力矩", detail.moments + "%"));
        list.add(new TowerCraneDataAdapter.Data("角度", detail.rotation + "°"));
        towerCraneDataAdapter.setNewData(list);

        nameItem.setContent(detail.staffName);
        cardNoItem.setContent(detail.staffCardNo);

        try {
            int angle = (int) Float.parseFloat(detail.rotation);
            float moment = Float.parseFloat(detail.moments) / 100;
            overlookView.setData(angle, moment);
        } catch (Exception e) {
            e.printStackTrace();
        }

        presenter.getTowerCraneEquipmentRecord(deviceCode, "离线".equals(detail.isOnline) ? 2 : 1);
    }

    @Override
    public void showTowerCraneRecord() {
        //TODO
        timeItem.setContent("");
        operateTimeItem.setContent("");
    }

    @Override
    public void showProgress(String message) {
        if (showProgress) {
            showProgress = false;
            super.showProgress(message);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        loop = false;
    }

    @Override
    protected void onDestroy() {
        loop = false;
        WApplication.getMainHandler().removeCallbacks(runnable);
        super.onDestroy();
    }

    @Override
    public void showTowerCraneEquipmentList(List<TowerCraneEquipmentInfo> datas) {
    }


}
