package com.ljkj.qxn.wisdomsitepro.Utils.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * 类描述：WisdomWebView
 * 创建人：lxx
 * 创建时间：2018/5/22 14:55
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class WisdomWebView extends WebView {
    private OnScrollChangedCallback mOnScrollChangedCallback;

    public WisdomWebView(Context context) {
        super(context);
    }

    public WisdomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WisdomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedCallback != null) {
            mOnScrollChangedCallback.onScroll(l, t, oldl, oldt);
        }
    }

    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return mOnScrollChangedCallback;
    }

    public void setOnScrollChangedCallback(OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }

    public static interface OnScrollChangedCallback {
        public void onScroll(int left, int top, int oldLeft, int oldTop);
    }

}
