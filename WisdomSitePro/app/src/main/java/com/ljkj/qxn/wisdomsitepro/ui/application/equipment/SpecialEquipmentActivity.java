package com.ljkj.qxn.wisdomsitepro.ui.application.equipment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.AuthorityId;
import com.ljkj.qxn.wisdomsitepro.manager.AuthorityManager;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：特种设备
 * 创建人：lxx
 * 创建时间：2018/7/12
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SpecialEquipmentActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
    }

    @Override
    protected void initUI() {
        titleText.setText("特种设备");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_back, R.id.tv_one, R.id.tv_two, R.id.tv_three, R.id.tv_four, R.id.tv_five, R.id.tv_six, R.id.tv_seven})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_one:
                if (AuthorityManager.getInstance().findAuthorityById(AuthorityId.EquipmentManage.TOWER_CRANE).isHide()) {
                    showError("无权限查看！");
                } else {
                    startActivity(new Intent(SpecialEquipmentActivity.this, EquipmentListActivity.class));
                }

                break;
            case R.id.tv_two:
//                startActivity(new Intent(SpecialEquipmentActivity.this, ElevatorActivity.class));
                showError("请接入升降机！");
                break;
            default:
                TextView textView = (TextView) view;
                showError("请接入" + textView.getText().toString() + "!");
                break;
        }
    }

}
