package com.ljkj.qxn.wisdomsitepro.Utils.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;

/**
 * 类描述：MyRemotePDFViewPager
 * 创建人：lxx
 * 创建时间：2018/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MyRemotePDFViewPager extends RemotePDFViewPager {
    public MyRemotePDFViewPager(Context context, String pdfUrl, DownloadFile.Listener listener) {
        super(context, pdfUrl, listener);
    }

    public MyRemotePDFViewPager(Context context, DownloadFile downloadFile, String pdfUrl, DownloadFile.Listener listener) {
        super(context, downloadFile, pdfUrl, listener);
    }

    public MyRemotePDFViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
