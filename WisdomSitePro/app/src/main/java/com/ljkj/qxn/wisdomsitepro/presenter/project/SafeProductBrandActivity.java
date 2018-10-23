package com.ljkj.qxn.wisdomsitepro.presenter.project;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

public class SafeProductBrandActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_product_brand);
    }

    @Override
    protected void initUI() {
        titleText.setText("安全生产牌");
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
