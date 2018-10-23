package com.ljkj.qxn.wisdomsitepro.ui.application.equipment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：升降机
 * 创建人：lxx
 * 创建时间：2018/7/26
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ElevatorActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView titleText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elevator);

    }

    @Override
    protected void initUI() {
        titleText.setText("升降机");
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
