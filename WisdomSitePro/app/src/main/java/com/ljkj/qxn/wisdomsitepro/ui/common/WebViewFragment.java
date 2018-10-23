package com.ljkj.qxn.wisdomsitepro.ui.common;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.WisdomWebView;

import butterknife.BindView;
import cdsp.android.logging.Logger;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：WebViewFragment
 * 创建人：lxx
 * 创建时间：2018/5/23 16:10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class WebViewFragment extends BaseFragment {
    private static final String EXTRA_KEY_URL = "extra_key_url";

    @BindView(R.id.web_view)
    WisdomWebView webView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private String url;
    private boolean isAnimStart = false;

    private Callback callback;
    private boolean hasError;

    public static WebViewFragment newInstance(String url) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_KEY_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        return view;
    }

    @Override
    protected void initUI() {
    }

    /**
     * 重加载
     *
     * @param url 地址
     */
    public void reload(@Nullable String url) {
        if (!TextUtils.isEmpty(url)) {
            this.url = url;
        }
        webView.loadUrl(url);
    }

    @Override
    protected void initData() {
        this.url = getArguments().getString(EXTRA_KEY_URL);
        configWebView();
        Logger.i(TAG, "WebView加载的url=" + url);
        webView.loadUrl(url);
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface", "AddJavascriptInterface"})
    private void configWebView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        WebSettings webSettings = webView.getSettings();
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        webSettings.setJavaScriptEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);  //是否支持viewport属性，默认值 false
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小,当页面宽度大于WebView宽度时，缩小使页面宽度等于WebView宽度

        webSettings.setDomStorageEnabled(true);
        webSettings.setSavePassword(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setAppCacheEnabled(false);
        webSettings.setAllowFileAccess(true);  //设置可以访问文件
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setTextZoom(100); //关键属性，避免系统字体改变时，导致html页面显示铺不满手机屏幕
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setUseWideViewPort(true);

        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }

        webView.setWebViewClient(new SimpleWebViewClient());
        webView.setWebChromeClient(new SimpleWebChromeClient());
    }

    private class SimpleWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            hasError = false;
            if (progressBar != null) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setAlpha(1.0f);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (!hasError && callback != null) {
                callback.loadSuccess();
            }
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Logger.e("WebView", "onReceivedError--->" + errorCode + "  " + description);
            super.onReceivedError(view, errorCode, description, failingUrl);
            hasError = true;
            if (callback != null) {
                callback.loadError(errorCode, description);
                if (errorCode == WebViewClient.ERROR_FAILED_SSL_HANDSHAKE || errorCode == WebViewClient.ERROR_FILE ||
                        errorCode == WebViewClient.ERROR_FILE_NOT_FOUND || errorCode == WebViewClient.ERROR_TOO_MANY_REQUESTS) {
                    hasError = false;
                }
            }
        }

    }

    private class SimpleWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (progressBar == null) {
                return;
            }
            int currentProgress = progressBar.getProgress();
            if (newProgress >= 100 && !isAnimStart) {
                isAnimStart = true; // 防止调用多次动画
                startDismissAnimation(currentProgress);// 开启属性动画让进度条平滑消失

            } else {
                startProgressAnimation(currentProgress, newProgress);// 开启属性动画让进度条平滑递增
            }

        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

    }

    //递增动画
    private void startProgressAnimation(int from, int to) {
        ObjectAnimator animator = ObjectAnimator.ofInt(progressBar, "progress", from, to);
        animator.setDuration(300);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }

    //消失动画
    private void startDismissAnimation(final int progress) {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(progressBar, "alpha", 1.0f, 0.0f);
        ObjectAnimator anim2 = ObjectAnimator.ofInt(progressBar, "progress", progress, 100);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(anim1, anim2);
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (progressBar == null) {
                    return;
                }
                progressBar.setVisibility(View.GONE);
                progressBar.setProgress(0);
                isAnimStart = false;
            }
        });
        animatorSet.start();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void loadError(int errorCode, String msg);

        void loadSuccess();
    }

}
