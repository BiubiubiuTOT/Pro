package com.ljkj.qxn.wisdomsitepro.ui.application.equipment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * 类描述：塔吊俯视View
 * 创建人：lxx
 * 创建时间：2018/8/2
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class TowerCraneOverlookView extends View {
    private Paint paint;
    private int color = Color.parseColor("#36CA3A");
    private int viewWidth;
    private int viewHeight;
    private int biggestRadius, centerRadius, smallRadius;

    private int angle = 0;
    private float moment = 0.0f; //力矩

    public TowerCraneOverlookView(Context context) {
        this(context, null);
    }

    public TowerCraneOverlookView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TowerCraneOverlookView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;

        w = w - getPaddingLeft() - getPaddingRight();
        h = w - getPaddingTop() - getPaddingBottom();
        biggestRadius = (w > h ? h : w) / 2;

        centerRadius = DensityUtil.dp2px(4);
        smallRadius = DensityUtil.dp2px(3);
    }

    /**
     * @param angle  角度
     * @param moment 力矩
     */
    public void setData(int angle, float moment) {
        this.angle = angle;
        this.moment = moment;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        paint.setStrokeWidth(DensityUtil.dp2px(1));
        canvas.translate(viewWidth / 2, viewHeight / 2);
        canvas.rotate(-angle);
        canvas.drawCircle(0, 0, biggestRadius, paint); //外圈大圆
        canvas.drawLine(-DensityUtil.dp2px(12), 0, -centerRadius, 0, paint); //中间的小圆左边线段

        int momentLength = (int) (biggestRadius * moment); //中间的小圆与右边小圆之间的线段长度(力矩)
        momentLength = momentLength < centerRadius ? centerRadius : (momentLength + DensityUtil.dp2px(2));
        canvas.drawLine(centerRadius, 0, momentLength, 0, paint);
        canvas.drawCircle(momentLength + smallRadius, 0, smallRadius, paint); //右边小圆
        canvas.drawLine(momentLength + smallRadius * 2, 0, biggestRadius, 0, paint); //右边小圆与外边大圆之间的线段

        paint.setStrokeWidth(DensityUtil.dp2px(2));
        canvas.drawCircle(0, 0, centerRadius, paint);//中间的小圆

        canvas.restore();
    }

}
