package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.JumpUtil;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

public class MobileLawActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_law);
    }

    @Override
    protected void initUI() {
        titleText.setText("移动执法");
    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.tv_back, R.id.tv_one, R.id.tv_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_one:
                JumpUtil.toBrowser(this, "http://211.162.125.99:8080/808gps/open/hls/index.html?lang=zh&devIdno=11866&&account=lmrh&password=000000");
                break;
            case R.id.tv_two:
                JumpUtil.toBrowser(this, "http://211.162.125.99:8080/808gps/open/hls/index.html?lang=zh&devIdno=80006527&&account=lmrh&password=000000");
                break;
            default:
                break;
        }
    }
}
