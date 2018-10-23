package com.ljkj.qxn.wisdomsitepro.ui.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.MyPdfDownloadFile;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.MyRemotePDFViewPager;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;

/**
 * 类描述：PDF查看
 * 创建人：lxx
 * 创建时间：2018/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RemotePDFActivity extends BaseActivity implements DownloadFile.Listener {
    private static final String KEY_PDF_URL = "key_pdf_url";

    @BindView(R.id.ll_container)
    LinearLayout container;

    @BindView(R.id.tv_title)
    TextView titleText;

    private MyRemotePDFViewPager remotePDFViewPager;
    private PDFPagerAdapter pdfPagerAdapter;
    private MyPdfDownloadFile myPdfDownloadFile;

    @RequiresApi(21)
    public static void startActivity(Context context, @NonNull String pdfUrl) {
        Intent intent = new Intent(context, RemotePDFActivity.class);
        intent.putExtra(KEY_PDF_URL, pdfUrl);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_pdf);
    }

    @Override
    protected void initUI() {
        titleText.setText("pdf预览");
    }

    @Override
    protected void initData() {
        final String pdfUrl = getIntent().getStringExtra(KEY_PDF_URL);
        myPdfDownloadFile = new MyPdfDownloadFile(this);
        AndPermission.with(this)
                .runtime()
                .permission(PermissionConstant.PERMISSIONS_OF_FILE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        addPdfViewPager(pdfUrl);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
//                        showError("用户已禁止文件读写权限");
                        addPdfViewPager(pdfUrl);
                    }
                })
                .start();
    }

    private void addPdfViewPager(String pdfUrl) {
        showProgress("pdf加载中...");
        remotePDFViewPager = new MyRemotePDFViewPager(this, myPdfDownloadFile, pdfUrl, this);
        container.addView(remotePDFViewPager, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    @OnClick({R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (pdfPagerAdapter != null) {
            pdfPagerAdapter.close();
        }
        myPdfDownloadFile.release();
        super.onDestroy();
    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        pdfPagerAdapter = new PDFPagerAdapter(this, destinationPath);
        remotePDFViewPager.setAdapter(pdfPagerAdapter);
        hideProgress();
    }

    @Override
    public void onFailure(Exception e) {
        showError("pdf预览失败");
        onBackPressed();
    }

    @Override
    public void onProgressUpdate(int progress, int total) {
        Log.e("lxx", "total = " + total + "   progress = " + progress);
    }


}
