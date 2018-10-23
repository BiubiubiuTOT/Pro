package com.ljkj.qxn.wisdomsitepro.Utils.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.ljkj.qxn.wisdomsitepro.R;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * 类描述：去除边框，添加下划线
 * 创建人：rjf
 * 创建时间：2018/6/26 10:28.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class LineEditText extends android.support.v7.widget.AppCompatEditText {

    // 画笔 用来画下划线
    private Paint paint;

    public LineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(ContextCompat.getColor(context, R.color.color_grey_999999));
        // 开启抗锯齿 较耗内存
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 得到总行数
        int lineCount = getLineCount();
        // 得到每行的高度
        int lineHeight = getLineHeight();
        // 根据行数循环画线
        for (int i = 0; i < lineCount; i++) {
            int lineY = (i + 1) * lineHeight ;
            lineY += DensityUtil.dp2px(10);
            canvas.drawLine(0, lineY, this.getWidth(), lineY, paint);
        }

    }

}
