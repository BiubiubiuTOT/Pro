package com.ljkj.qxn.wisdomsitepro.ui.web;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Stack;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.base.BaseApplication;
import cdsp.android.constant.Consts;
import cdsp.android.logging.Logger;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DeviceUtils;
import cdsp.android.util.FileUtils;

/**
 * 类描述：基本WebViewActvity
 * 创建人：mhl
 * 创建时间：2017/9/29 10:37
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@Deprecated
public class BaseWebViewActivity extends BaseActivity {

    private static final String TAG = "BaseWebViewActivity";

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.wb_content)
    WebView wbContent;

    @BindView(R.id.pb_webview)
    ProgressBar pbWebview;

    private String url;
    private String title;
    /**
     * 记录Url 加载记录，点击返回键，按顺序返回
     */
    private Stack<String> stackOfUrls = new Stack<String>();

    private Handler handler = new Handler();

    /**
     * 图片选择回调
     */
    private ValueCallback<Uri> mUploadMessage;

    private ValueCallback<Uri[]> mUploadCallbackAboveL;

    private Uri fileUri;
    public static final int TYPE_GALLERY = 2;
    public static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_webview);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initUI() {

        initview();
        tvTitle.setText(title);
    }

    @Override
    protected void initData() {

        Logger.i(TAG, "加入协议参数之前 url :" + url);
        wbContent.loadUrl(url = url + buildProtocolParams());
        stackOfUrls.push(url);
        Logger.i(TAG, "stack push url:" + url);
        Logger.i(TAG, "加入协议参数之后 url :" + url);
    }


    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    void initview() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        wbContent.setWebChromeClient(new MChromeClient());
        wbContent.setWebViewClient(new MClient());

        wbContent.getSettings().setJavaScriptEnabled(true);

        /*JS接口对象*/
        wbContent.addJavascriptInterface(new MActivityJS(), "native");
        wbContent.getSettings().setSupportZoom(true);

        /*关键属性，避免系统字体改变时，导致html页面显示铺不满手机屏幕*/
        wbContent.getSettings().setTextZoom(100);

        /*编码格式*/
        wbContent.getSettings().setDefaultTextEncodingName("utf-8");

        /*webView自适应屏幕*/
        wbContent.getSettings().supportMultipleWindows();  //多窗口
        wbContent.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);  //关闭webview中缓存
        wbContent.getSettings().setAllowFileAccess(true);  //设置可以访问文件
        wbContent.getSettings().setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        wbContent.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        wbContent.getSettings().setLoadsImagesAutomatically(true);  //支持自动加载图片
        wbContent.getSettings().setUseWideViewPort(true);
        wbContent.getSettings().setLoadWithOverviewMode(true);
        wbContent.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
    }

    private class MChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (pbWebview != null) {
                pbWebview.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);

            if (newProgress == 100) {
                Log.d("web load", "finish");
            } else {
                Log.d("web load", "Progress = " + newProgress);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            //截取标题“-”分割第一个作为标题显示
            if (TextUtils.isEmpty(title) && title.contains("-")) {
                tvTitle.setText(title.split("-")[0]);
            }
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

            return super.onJsAlert(view, url, message, result);
        }

        // For Android < 3.0
        public void onShowFileChooser(ValueCallback<Uri> uploadMsg) {
            Logger.d(TAG, "into onShowFileChooser <3.0");
            mUploadMessage = uploadMsg;
            showOptions();
        }

        // For Android > 4.1.1
        public void onShowFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            Logger.d(TAG, "into onShowFileChooser >4.1.1");
            mUploadMessage = uploadMsg;
            showOptions();
        }

        // For Android > 5.0支持多张上传
        @Override
        public boolean onShowFileChooser(WebView webView,
                                         ValueCallback<Uri[]> uploadMsg,
                                         FileChooserParams fileChooserParams) {
            Logger.d(TAG, "into onShowFileChooser >5.0");
            mUploadCallbackAboveL = uploadMsg;
            showOptions();
            return true;
        }
    }

    private class MClient extends WebViewClient {
        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            super.doUpdateVisitedHistory(view, url, isReload);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Logger.e("onPageStarted", url);
            if (pbWebview != null)
                pbWebview.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }


        @Override
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            Logger.e("onReceivedError", url);
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            if (pbWebview != null) {
                pbWebview.setVisibility(View.GONE);
                tvTitle.setText("页面加载出错");
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Logger.e("onPageFinished", url);
            super.onPageFinished(view, url);
            if (pbWebview != null) {
                pbWebview.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            super.onPageCommitVisible(view, url);
            if (pbWebview != null) {
                pbWebview.setVisibility(View.GONE);
            }
        }

        @TargetApi(21)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Logger.e("OverrideUrlLoading", request.getUrl());

            //拨打电话
//            if (request.getUrl().toString().startsWith("tel:")) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
//                startActivity(intent);
//            }
//
//            //网页地址访问
//            else if (request.getUrl().toString().startsWith("http") && !request.getUrl().toString().contains("standard/preview.do")) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
//                startActivity(intent);
//            } else {
//                view.loadUrl(request.getUrl().toString());
//            }
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Logger.e("OldOverrideUrlLoading", url);

            //拨打电话
//            if (url.startsWith("tel:")) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                startActivity(intent);
//            }
//
//            //网页地址访问
//            else if (url.toString().startsWith("http") && !url.contains("standard/preview.do")) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                startActivity(intent);
//            } else {
//                view.loadUrl(url);
//            }

            view.loadUrl(url);
            return true;
        }
    }


    /**
     * JS对象：4.2以后，任何为JS暴露的接口,都必须加上 @JavascriptInterface
     */
    class MActivityJS {


        /**
         * 转到新的url
         *
         * @param title
         * @param path
         * @param params
         */
        @JavascriptInterface
        public void showWebForJS(final String title, final String path, final String params) {

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Logger.d(TAG, "path:" + path);
                    Logger.d(TAG, "params:" + params);
                    tvTitle.setText(title);
                    try {
                        String url = HostConfig.getHost().substring(0, HostConfig.getHost().length() - 2) + path + "?params=" + URLEncoder.encode(params.toString(), "UTF-8") + buildProtocolParams();
                        Logger.d(TAG, "URL :" + url);
                        stackOfUrls.push(url);
                        wbContent.loadUrl(url);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        /**
         * 页面提交完成返回
         */
        @JavascriptInterface
        public void popviewcontroller() {
            pbWebview.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);
        }
    }


    /**
     * 添加协议参数
     */
    private String buildProtocolParams() {

        StringBuilder sbParams = new StringBuilder();
        if (!url.contains("?")) {
            sbParams.append("?" + Consts.REQUEST_PARAM_APP_ID + "=" + BaseApplication.getApplication().getAppId());
        } else {
            sbParams.append("&" + Consts.REQUEST_PARAM_APP_ID + "=" + BaseApplication.getApplication().getAppId());
        }
        sbParams.append("&" + Consts.REQUEST_PARAM_DEVICE_ID + "=" + DeviceUtils.getDeviceId(BaseApplication.getAppContext()));
        sbParams.append("&" + Consts.REQUEST_PARAM_USER_TOKEN + "=" + UserManager.getInstance().getUserToken());
        return sbParams.toString();
    }

    /**
     * 包含拍照和相册选择
     */
    public void showOptions() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setOnCancelListener(new ReOnCancelListener());
        alertDialog.setTitle("选择");
        alertDialog.setItems(new String[]{"相机", "相册"},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, final int which) {
                        AndPermission.with(BaseWebViewActivity.this)
                                .runtime()
                                .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                                .onGranted(new Action<List<String>>() {
                                    @Override
                                    public void onAction(List<String> permissions) {
                                        if (which == 0) {
                                            toCamera();
                                        } else {
                                            showPhoto();
                                        }
                                    }
                                })
                                .onDenied(new Action<List<String>>() {
                                    @Override
                                    public void onAction(List<String> permissions) {
                                        showError("请打开相机、相册权限");
                                    }
                                })
                                .start();
                    }
                }

        );
        alertDialog.show();
    }

    // 请求拍照
    public void toCamera() {
        try {
            startActivityForResult(dispatchTakePictureIntent(), REQUEST_TAKE_PHOTO);
        } catch (IOException e) {
            showError("无法启用系统相机");
            e.printStackTrace();
        }
    }

    public void showPhoto() {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
        BaseWebViewActivity.this.startActivityForResult(i,
                TYPE_GALLERY);
    }

    private class ReOnCancelListener implements
            DialogInterface.OnCancelListener {

        @Override
        public void onCancel(DialogInterface dialogInterface) {
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
                mUploadMessage = null;
            }
            if (mUploadCallbackAboveL != null) {
                mUploadCallbackAboveL.onReceiveValue(null);
                mUploadCallbackAboveL = null;
            }
        }
    }

    public Intent dispatchTakePictureIntent() throws IOException {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = FileUtils.makeFile(com.ljkj.qxn.wisdomsitepro.data.Consts.Cache.SDCardRoot + File.separator + com.ljkj.qxn.wisdomsitepro.data.Consts.Cache.COMPRESS_CACHE_DIR + File.separator + FileUtils.getRandomFileName("jpg"));
            // Continue only if the File was successfully created
            if (photoFile != null) {
                fileUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", photoFile);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ClipData clip = ClipData.newUri(this.getContentResolver(), "A photo", fileUri);
                    takePictureIntent.setClipData(clip);
                    takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else {
                    List<ResolveInfo> resInfoList =
                            this.getPackageManager()
                                    .queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
                    for (ResolveInfo resolveInfo : resInfoList) {
                        String packageName = resolveInfo.activityInfo.packageName;
                        this.grantUriPermission(packageName, fileUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    }
                }
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            }
        }
        return takePictureIntent;
    }


    /**
     * 回调到网页
     *
     * @param isCamera
     * @param uri
     */
    public void onActivityCallBack(boolean isCamera, Uri uri) {
        if (mUploadCallbackAboveL != null) {
            Uri[] uris = new Uri[]{uri};
            mUploadCallbackAboveL.onReceiveValue(uris);
            mUploadCallbackAboveL = null;
        } else if (mUploadMessage != null) {
            mUploadMessage.onReceiveValue(uri);
            mUploadMessage = null;
        } else {
            showError("无法获取数据");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) { // 相机拍照
                if (fileUri != null) {
                    onActivityCallBack(true, fileUri);
                } else {
                    showError("拍照文件获取失败...");
                    if (mUploadMessage != null) {
                        mUploadMessage.onReceiveValue(null);
                        mUploadMessage = null;
                    }
                    if (mUploadCallbackAboveL != null) {
                        mUploadCallbackAboveL.onReceiveValue(null);
                        mUploadCallbackAboveL = null;
                    }
                }
            } else if (requestCode == TYPE_GALLERY) {// 相册选择
                if (data != null) {
                    Uri uri = data.getData();
                    if (uri != null) {
                        onActivityCallBack(false, uri);
                    } else {
                        showError("获取数据为空...");
                    }
                }
            }
        } else {
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
                mUploadMessage = null;
            }
            if (mUploadCallbackAboveL != null) {
                mUploadCallbackAboveL.onReceiveValue(null);
                mUploadCallbackAboveL = null;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (!stackOfUrls.isEmpty()) {
            if (stackOfUrls.size() >= 2) {
                wbContent.loadUrl(stackOfUrls.remove(stackOfUrls.size() - 2));// 返回上一页面
                return;
            }
        }
        this.finish();
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }
}
