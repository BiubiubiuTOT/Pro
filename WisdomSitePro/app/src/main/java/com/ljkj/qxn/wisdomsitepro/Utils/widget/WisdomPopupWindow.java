package com.ljkj.qxn.wisdomsitepro.Utils.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.FloatRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * 弹出框
 * 创建人：lxx
 * 创建时间：2018/3/30 16:13
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class WisdomPopupWindow extends PopupWindow {

    private Context context;

    @Override
    public int getWidth() {
        return getContentView().getMeasuredWidth();
    }

    @Override
    public int getHeight() {
        return getContentView().getMeasuredHeight();
    }

    public interface OnViewClickListener {
        void onViewClick(PopupWindow popupWindow, View view);
    }

    private WisdomPopupWindow(Builder builder) {
        this.context = builder.context;
        setContentView(builder.popupView);
        setWidth(builder.width);
        setHeight(builder.height);

        setOutsideTouchable(builder.touchable);
        if (builder.bgLevel != -1) {
            setBackGroundLevel(builder.bgLevel);
        }
        if (builder.animationStyle != -1) {
            setAnimationStyle(builder.animationStyle);
        }
    }

    @Override
    public void setOutsideTouchable(boolean touchable) {
        setBackgroundDrawable(new ColorDrawable(0x00000000));//设置透明背景
        super.setOutsideTouchable(touchable);
        setFocusable(touchable);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        setBackGroundLevel(1.0f);
    }

    //设置背景灰色程度
    private void setBackGroundLevel(@FloatRange(from = 0.0f, to = 1.0f) float level) {
        Window window = ((Activity) context).getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = level;
        window.setAttributes(params);
    }

    public static class Builder {
        Context context;
        int width = ViewGroup.LayoutParams.WRAP_CONTENT, height = ViewGroup.LayoutParams.WRAP_CONTENT; //弹窗的宽和高
        float bgLevel = -1; //屏幕背景灰色程度
        int animationStyle = -1; //动画Id
        View popupView;
        boolean touchable = true;

        OnViewClickListener listener;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * @param layoutResId 设置PopupWindow 布局id
         */
        public Builder setView(@LayoutRes int layoutResId) {
            this.popupView = LayoutInflater.from(context).inflate(layoutResId, null);
            return this;
        }

        /**
         * @param view 设置PopupWindow view
         */
        public Builder setView(@NonNull View view) {
            this.popupView = view;
            return this;
        }

        /**
         * 设置子View
         *
         * @param listener ViewInterface
         */
        public Builder setViewOnclickListener(OnViewClickListener listener) {
            this.listener = listener;
            return this;
        }

        /**
         * 设置宽度和高度 如果不设置 默认是wrap_content
         *
         * @param width  宽
         * @param height 高
         */
        public Builder setWidthAndHeight(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        /**
         * 设置背景灰色程度
         *
         * @param level 0.0f-1.0f
         * @return Builder
         */
        public Builder setBackGroundLevel(@FloatRange(from = 0.0f, to = 1.0f) float level) {
            this.bgLevel = level;
            return this;
        }

        /**
         * 是否点击Outside消失
         *
         * @param touchable 是否可点击
         */
        public Builder setOutsideTouchable(boolean touchable) {
            this.touchable = touchable;
            return this;
        }

        /**
         * 设置动画
         */
        public Builder setAnimationStyle(int animationStyle) {
            this.animationStyle = animationStyle;
            return this;
        }

        public WisdomPopupWindow build() {
            WisdomPopupWindow popupWindow = new WisdomPopupWindow(this);
            if (listener != null) {
                listener.onViewClick(popupWindow, popupView);
            }
            return popupWindow;
        }

    }

}
