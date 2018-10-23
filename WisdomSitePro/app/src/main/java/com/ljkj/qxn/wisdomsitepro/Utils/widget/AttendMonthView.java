package com.ljkj.qxn.wisdomsitepro.Utils.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * 考勤月份
 * 创建人：lxx
 * 创建时间：2018/5/2 09:43
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AttendMonthView extends MonthView {

    private int mRadius;
    private int mDotRadius;

    public AttendMonthView(Context context) {
        super(context);
    }

    @Override
    protected void onPreviewHook() {
        super.onPreviewHook();
        mDotRadius = DensityUtil.dp2px(2);
        mRadius = Math.min(mItemWidth, mItemHeight) / 5 * 2 - mDotRadius;
    }

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
        canvas.drawCircle(cx, cy, mRadius, mSelectedPaint);

        return true;
    }

    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
        int dotX = x + mItemWidth / 2;
        int dotY = y + mItemHeight / 2 + mRadius + mDotRadius * 2;
        canvas.drawCircle(dotX, dotY, mDotRadius, mSchemePaint);
    }

    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        float baselineY = mTextBaseLine + y;
        int cx = x + mItemWidth / 2;


        if (hasScheme) {
            int dotX = x + mItemWidth / 2;
            int dotY = y + mItemHeight / 2 + mRadius + mDotRadius * 2;
            canvas.drawCircle(dotX, dotY, mDotRadius, mSchemePaint);
        }

        if (isSelected) {
            mCurDayTextPaint.setColor(Color.WHITE);
            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY, calendar.isCurrentDay() ? mCurDayTextPaint :
                    calendar.isCurrentMonth() ? mSelectTextPaint : mOtherMonthTextPaint);
        } else {
            mCurDayTextPaint.setColor(Color.parseColor("#333333"));
            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY, calendar.isCurrentDay() ? mCurDayTextPaint :
                    calendar.isCurrentMonth() ? mCurMonthTextPaint : mOtherMonthTextPaint);
        }


    }


}
