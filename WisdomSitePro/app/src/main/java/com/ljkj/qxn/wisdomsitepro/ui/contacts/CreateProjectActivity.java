package com.ljkj.qxn.wisdomsitepro.ui.contacts;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.SpannableStringUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：联系人 -- 新建项目
 * 创建人：rjf
 * 创建时间：2018/6/26 10:52.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class CreateProjectActivity extends BaseActivity {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_tel_phone)
    TextView tvTelPhone;
    private static final String PHONE_NUMBER = "0851-86869595";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("新建项目");

        tvTelPhone.setText(SpannableStringUtils.getBuilder("电话：")
                .append(PHONE_NUMBER)
                .setForegroundColor(ContextCompat.getColor(this, R.color.color_orange))
                .create());
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.tv_tel_phone)
    public void clickPhone(View view) {
        callPhone();
    }

    private void callPhone() {
        AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.CALL_PHONE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + PHONE_NUMBER));
                        startActivity(intent);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showError("用户已禁止访问电话权限");
                    }
                })
                .start();
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }
}
