package com.ljkj.qxn.wisdomsitepro.ui.common;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 检查-整改操作基础UI
 * 创建人：lxx
 * 创建时间：2018/3/15 15:00
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class BaseCheckRectificationActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    protected TextView titleText;

    @BindView(R.id.rl_images)
    protected RecyclerView imageRecyclerView;

    @BindView(R.id.tv_project_name)
    protected TextView projectNameText;

    @BindView(R.id.tv_project_address)
    protected TextView projectAddressText;

    @BindView(R.id.et_situation)
    protected EditText situationEdit;

    @BindView(R.id.et_advice)
    protected EditText adviceEdit;

    @BindView(R.id.item_plan)
    protected InputItemView planItem;

    @BindView(R.id.item_measure)
    protected InputItemView measureItem;

    @BindView(R.id.item_money)
    protected InputItemView moneyItem;

    @BindView(R.id.report_recycler_view)
    protected RecyclerView reportRecyclerView;

    @BindView(R.id.money_recycler_view)
    protected RecyclerView moneyRecyclerView;

    @BindView(R.id.plan_recycler_view)
    protected RecyclerView planRecyclerView;

    @BindView(R.id.measure_recycler_view)
    protected RecyclerView measureRecyclerView;

    @BindView(R.id.tv_upload_plan)
    protected TextView uploadPlanText;

    @BindView(R.id.tv_upload_measure)
    protected TextView uploadMeasureText;

    @BindView(R.id.ll_plan)
    protected LinearLayout planLayout;

    @BindView(R.id.ll_measure)
    protected LinearLayout measureLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immediate_rectification);
    }

    @Override
    protected void initUI() {
        titleText.setText("立即整改");
        imageRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        imageRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initData() {

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
}
