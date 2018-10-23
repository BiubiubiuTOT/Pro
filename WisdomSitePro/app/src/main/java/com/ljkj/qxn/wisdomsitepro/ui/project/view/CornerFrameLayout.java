package com.ljkj.qxn.wisdomsitepro.ui.project.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import cdsp.android.util.DisplayUtils;

/**
 * 类描述：边角ViewPager
 * 创建人：lxx
 * 创建时间：2018/8/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CornerFrameLayout extends FrameLayout {
    private Path leftTopPath, leftBottomPath, rightTopPath, rightBottomPath;
    private Paint paint;

    public CornerFrameLayout(Context context) {
        this(context, null);
    }

    public CornerFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DisplayUtils.dip2px(getContext(), 3));

        leftTopPath = new Path();
        leftBottomPath = new Path();
        rightTopPath = new Path();
        rightBottomPath = new Path();

        setWillNotDraw(false);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        leftTopPath.reset();
        leftBottomPath.reset();
        rightTopPath.reset();
        rightBottomPath.reset();

        int lineLength = DisplayUtils.dip2px(getContext(), 16);

        leftTopPath.moveTo(0, lineLength);
        leftTopPath.lineTo(0, 0);
        leftTopPath.lineTo(lineLength, 0);

        leftBottomPath.moveTo(0, h - lineLength);
        leftBottomPath.lineTo(0, h);
        leftBottomPath.lineTo(lineLength, h);

        rightTopPath.moveTo(w - lineLength, 0);
        rightTopPath.lineTo(w, 0);
        rightTopPath.lineTo(w, lineLength);

        rightBottomPath.moveTo(w, h - lineLength);
        rightBottomPath.lineTo(w, h);
        rightBottomPath.lineTo(w - lineLength, h);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawPath(leftTopPath, paint);
        canvas.drawPath(leftBottomPath, paint);
        canvas.drawPath(rightTopPath, paint);
        canvas.drawPath(rightBottomPath, paint);
    }

}
