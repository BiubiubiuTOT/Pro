package cdsp.android.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

import cdsp.android.R;

/**
 * 类描述：可选择TextView
 * 创建人：lxx
 * 创建时间：2018/6/14
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CheckableTextView extends android.support.v7.widget.AppCompatTextView implements Checkable {
    private boolean mChecked = false;
    private OnCheckedChangeListener onCheckedChangeListener;

    private int checkColor;
    private int unCheckColor;

    public CheckableTextView(Context context) {
        this(context, null);
    }

    public CheckableTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CheckableTextView);
        checkColor = ta.getColor(R.styleable.CheckableTextView_checkColor, Color.WHITE);
        unCheckColor = ta.getColor(R.styleable.CheckableTextView_unCheckColor, Color.BLACK);
        ta.recycle();

        setTextColor(unCheckColor);
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(!mChecked);
                if (onCheckedChangeListener != null) {
                    onCheckedChangeListener.onCheckedChanged(CheckableTextView.this, mChecked);
                }
            }
        });
    }

    @Override
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            refreshDrawableState();
        }
        setTextColor(checked ? checkColor : unCheckColor);
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, new int[]{android.R.attr.state_checked});
        }
        return drawableState;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(View view, boolean isChecked);
    }
}
