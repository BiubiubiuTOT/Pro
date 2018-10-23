package com.ljkj.qxn.wisdomsitepro.Utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.ljkj.qxn.wisdomsitepro.R;

/**
 * 类描述：圆角LinearLayout
 * 创建人：lxx
 * 创建时间：2018/7/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RoundRectLinearLayout extends LinearLayout {

    private Paint paint;
    private Path path;
    private int strokeWidth; //边框宽度
    private int strokeColor; //边框颜色
    private float[] radiusArray = new float[8];

    private RectF saveLayer = new RectF();

    public RoundRectLinearLayout(Context context) {
        this(context, null);
    }

    public RoundRectLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundRectLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundRectLinearLayout);

        int allRadius = ta.getDimensionPixelSize(R.styleable.RoundRectLinearLayout_all_radius, 0);

        int topLeftRadius = ta.getDimensionPixelSize(R.styleable.RoundRectLinearLayout_top_left_radius, 0);
        int topRightRadius = ta.getDimensionPixelSize(R.styleable.RoundRectLinearLayout_top_right_radius, 0);
        int bottomLeftRadius = ta.getDimensionPixelSize(R.styleable.RoundRectLinearLayout_bottom_left_radius, 0);
        int bottomRightRadius = ta.getDimensionPixelSize(R.styleable.RoundRectLinearLayout_bottom_right_radius, 0);
        this.strokeWidth = ta.getDimensionPixelSize(R.styleable.RoundRectLinearLayout_stroke_width, 0);
        this.strokeColor = ta.getColor(R.styleable.RoundRectLinearLayout_stroke_color, Color.WHITE);
        ta.recycle();

        //左上
        radiusArray[0] = allRadius == 0 ? topLeftRadius : allRadius;
        radiusArray[1] = allRadius == 0 ? topLeftRadius : allRadius;

        //左右
        radiusArray[2] = allRadius == 0 ? topRightRadius : allRadius;
        radiusArray[3] = allRadius == 0 ? topRightRadius : allRadius;

        //下左
        radiusArray[4] = allRadius == 0 ? bottomLeftRadius : allRadius;
        radiusArray[5] = allRadius == 0 ? bottomLeftRadius : allRadius;

        //下右
        radiusArray[6] = allRadius == 0 ? bottomRightRadius : allRadius;
        radiusArray[7] = allRadius == 0 ? bottomRightRadius : allRadius;

        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        this.path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        RectF rect = new RectF();
        rect.set(getPaddingLeft(), getPaddingTop(), w - getPaddingRight(), h - getPaddingBottom());
        path.addRoundRect(rect, radiusArray, Path.Direction.CW);
        path.moveTo(-10, -10);
        path.moveTo(w + 10, h + 10);

        saveLayer.set(0, 0, w, h);
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.clipPath(path); //剪裁掉RoundRectLinearLayout自身的背景
        super.draw(canvas);
        canvas.restore();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(saveLayer, null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        if (strokeWidth > 0) {
            paint.setXfermode(null);
            paint.setStrokeWidth(strokeWidth * 2);
            paint.setColor(strokeColor);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, paint);
        }

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setStrokeWidth(0);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);
        canvas.restore();
    }

}
