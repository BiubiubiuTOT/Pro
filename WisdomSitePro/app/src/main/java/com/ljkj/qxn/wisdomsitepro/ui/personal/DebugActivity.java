package com.ljkj.qxn.wisdomsitepro.ui.personal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.BuildConfig;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.SharedPreferencesUtil;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.logging.Logger;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.ToastUtils;

public class DebugActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_ip)
    TextView showIpText;

    @BindView(R.id.et_ip)
    EditText ipText;

    public static void startActivity(Context context) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        Intent intent = new Intent(context, DebugActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
    }

    @Override
    protected void initUI() {
        titleText.setText("Debug");
        updateIpUi();
    }

    @Override
    protected void initData() {
        ipText.setText(HostConfig.getHost2());
    }

    @OnClick({R.id.tv_back, R.id.btn_address, R.id.btn_ip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_address:
                showAddressDialog();
                break;
            case R.id.btn_ip:
                saveIp(ipText.getText().toString().trim());
                break;
            default:
                break;
        }
    }

    private void showAddressDialog() {
        final List<String> list = HostConfig.getDebugAddressList();
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("切换服务器地址(" + HostConfig.getHost2() + ")")
                .setItems(list.toArray(new String[list.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String address = list.get(which);
                        Logger.d("切换服务器地址：" + address);
                        SharedPreferencesUtil.putString(DebugActivity.this, HostConfig.KEY_DEBUG_ADDRESS, address);
                        HostConfig.changeDebugAddress(address);
                        updateIpUi();
                    }
                });
        builder.show();
    }

    private void saveIp(String ip) {
        if (TextUtils.isEmpty(ip)) {
            ToastUtils.showShort("请输入ip");
            return;
        }
        SharedPreferencesUtil.putString(DebugActivity.this, HostConfig.KEY_DEBUG_ADDRESS, ip);
        HostConfig.changeDebugAddress(ip);
        ToastUtils.showLong("ip地址保存成功");
        updateIpUi();
    }


    private void updateIpUi() {
        showIpText.setText("当前IP：" + HostConfig.getHost2());
    }

}
