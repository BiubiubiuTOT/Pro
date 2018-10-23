package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeFragment;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：安全管理
 * 创建人：lxx
 * 创建时间：2018/6/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeManagerActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_manager);
    }

    @Override
    protected void initUI() {
        titleText.setText("安全管理");
    }

    @Override
    protected void initData() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, Fragment.instantiate(this, SafeFragment.TAG))
                .commit();
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
