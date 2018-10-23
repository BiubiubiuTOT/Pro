package com.ljkj.qxn.wisdomsitepro.ui.application.supervisor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：监理管理
 * 创建人：lxx
 * 创建时间：2018/8/30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SupervisorManageActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_manager);
    }

    @Override
    protected void initUI() {
        titleText.setText("监理管理");
    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.tv_back, R.id.item_side_station_record, R.id.item_inspector_record, R.id.item_supervisor_record, R.id.item_supervisor_standard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_side_station_record:
                startActivity(new Intent(this, SideStationRecordManageActivity.class));
                break;
            case R.id.item_inspector_record:
                startActivity(new Intent(this, InspectorRecordManageActivity.class));
                break;
            case R.id.item_supervisor_record:
                startActivity(new Intent(this, SupervisorRecordManageActivity.class));
                break;
            case R.id.item_supervisor_standard:
                startActivity(new Intent(this, SupervisorStandardActivity.class));
                break;
            default:
                break;
        }
    }

}
