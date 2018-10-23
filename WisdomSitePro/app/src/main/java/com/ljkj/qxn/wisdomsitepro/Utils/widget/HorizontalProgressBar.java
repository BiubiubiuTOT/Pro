package com.ljkj.qxn.wisdomsitepro.Utils.widget;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.ljkj.qxn.wisdomsitepro.R;

/**
 * 类描述：水平进度条
 * 创建人：lxx
 * 创建时间：2018/8/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class HorizontalProgressBar extends View {
    private static final int TOTAL_PROGRESS = 100; //总进度

    private Paint progressPaint;
    private Paint bgPaint;
    private Paint textPaint;
    private ValueAnimator valueAnimator;
    private int startColor, endColor, bgColor;
    private int progress;
    private int currentProgress = 0;
    private boolean animate = true;
    private int textSize;
    private int duration = 1000;

    private int viewWidth, viewHeight;

    public HorizontalProgressBar(Context context) {
        this(context, null);
    }

    public HorizontalProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HorizontalProgressBar);
        startColor = ta.getColor(R.styleable.HorizontalProgressBar_hpb_start_color, Color.parseColor("#579AF9"));
        endColor = ta.getColor(R.styleable.HorizontalProgressBar_hpb_end_color, Color.parseColor("#B5F877"));
        bgColor = ta.getColor(R.styleable.HorizontalProgressBar_hpb_bg_color, Color.parseColor("#32354B"));
        progress = ta.getInt(R.styleable.HorizontalProgressBar_hpb_progress, 30);
        animate = ta.getBoolean(R.styleable.HorizontalProgressBar_hpb_animate, true);
        textSize = ta.getDimensionPixelSize(R.styleable.HorizontalProgressBar_hpb_text_size, sp2px(13));
        duration = ta.getInt(R.styleable.HorizontalProgressBar_hpb_duration, 1000);
        ta.recycle();

        if (progress < 0 || progress > 100) {
            throw new IllegalArgumentException("必须满足条件: 0 < progress <= 100");
        }
        initPaint();
        setValueAnimator();
        if (progress > 0) {
            valueAnimator.start();
        }
    }

    private void initPaint() {
        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStyle(Paint.Style.FILL);

        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        bgPaint.setColor(bgColor);
        progressPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(endColor);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTextSize(textSize);
    }

    private void setValueAnimator() {
        valueAnimator = ValueAnimator.ofInt(0, progress);
        valueAnimator.setDuration(duration);
        valueAnimator.setStartDelay(100);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentProgress = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewHeight = h - getPaddingTop() - getPaddingBottom();
        viewWidth = w - getPaddingRight() - getPaddingLeft() - viewHeight;
        progressPaint.setStrokeWidth(viewHeight);
        bgPaint.setStrokeWidth(viewHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int requireHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED) {
            requireHeight = getPaddingBottom() + getPaddingTop() + textSize + 6;
        }

        setMeasuredDimension(width, requireHeight);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int offset = 0;
        int textMarginLeft = 6;
        String text = currentProgress + "%";
        if (progress > 92) {
            offset = (int) textPaint.measureText(text) + textMarginLeft;
        }
        int width = viewWidth - offset;

        int startX = getPaddingLeft() + viewHeight / 2;
        int startY = getPaddingTop() + viewHeight / 2;

        int bgStopX = getWidth() - getPaddingRight() - viewHeight / 2 - offset;

        int progressStopX = startX + width * currentProgress / TOTAL_PROGRESS;

        canvas.drawLine(startX, startY, bgStopX, startY, bgPaint);

        progressPaint.setShader(new LinearGradient(startX, startY, progressStopX, startY, new int[]{startColor, endColor}, null, Shader.TileMode.CLAMP));

        canvas.drawLine(startX, startY, progressStopX, startY, progressPaint);

        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int center = getHeight() / 2;
        int baseline = center + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        canvas.drawText(text, progressStopX + viewHeight / 2 + textMarginLeft, baseline, textPaint);
    }

    /**
     * 设置进度
     *
     * @param progress progress
     */
    public void setProgress(@IntRange(from = 0, to = 100) int progress) {
        this.progress = progress;
        if (animate) {
            if (valueAnimator != null) {
                valueAnimator.end();
            } else {
                setValueAnimator();
            }
            valueAnimator.setIntValues(0, progress);
            valueAnimator.start();
        } else {
            currentProgress = progress;
            invalidate();
        }
    }

    private int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, getResources().getDisplayMetrics());
    }

    @Override
    protected void onDetachedFromWindow() {
        if (valueAnimator != null) {
            valueAnimator.end();
        }
        super.onDetachedFromWindow();
    }
}
