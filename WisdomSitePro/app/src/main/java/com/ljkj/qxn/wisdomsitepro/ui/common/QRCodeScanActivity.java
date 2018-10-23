package com.ljkj.qxn.wisdomsitepro.ui.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.ToastUtils;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * 类描述：二维码扫描
 * 创建人：lxx
 * 创建时间：2018/6/28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class QRCodeScanActivity extends BaseActivity {
    public static final String KEY_SCAN_RESULT = "key_scan_result";

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.zxing_view)
    ZXingView zXingView;

    public static void startActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, QRCodeScanActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startActivityWithPermission(final Activity activity, final int requestCode) {
        AndPermission.with(activity)
                .runtime()
                .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        QRCodeScanActivity.startActivity(activity, requestCode);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        ToastUtils.showShort("扫描二维码需要打开相机和散光灯的权限");
                    }
                })
                .start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_scan);
    }

    @Override
    protected void initUI() {
        titleText.setText("扫一扫");
    }

    @Override
    protected void initData() {
        //设置扫描二维码的代理
        zXingView.setDelegate(new QRCodeView.Delegate() {
            @Override
            public void onScanQRCodeSuccess(String result) {
//                handleResult(result);
                Intent intent = new Intent();
                intent.putExtra(KEY_SCAN_RESULT, result);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void onScanQRCodeOpenCameraError() {
                showError("打开相机出错");
                finish();
            }
        });

    }

    //二维码扫描结果处理
    private void handleResult(@NonNull String result) {
        if (isUrl(result)) {
            WebViewActivity.startActivity(this, "", result);
            finish();
        } else {
            ToastUtils.showShort(result);
            zXingView.startSpot(); // 延迟0.5秒后开始识别
        }
    }

    private boolean isUrl(String result) {
        try {
            new URL(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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

    @Override
    protected void onStart() {
        super.onStart();
        zXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
        zXingView.startSpotAndShowRect(); //显示扫描框，并且延迟0.5秒后开始识别
    }

    @Override
    protected void onStop() {
        super.onStop();
        zXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
    }

    @Override
    protected void onDestroy() {
        zXingView.onDestroy();
        super.onDestroy();
    }

}
