package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：安全技术交底详情
 * 创建人：lxx
 * 创建时间：2018/7/18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeTechnologyDiscloseDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView titleText;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SafeTechnologyDiscloseDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_technology_disclose_add);
    }

    @Override
    protected void initUI() {
        titleText.setText("安全技术交底详情");
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
