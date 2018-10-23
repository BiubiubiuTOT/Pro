package com.ljkj.qxn.wisdomsitepro.Utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 输入ItemView
 * Created by lxx on 2018/3/13.
 */

public class InputItemView extends FrameLayout {

    @BindView(R.id.tv_title)
    TextView mTitleText;

    @BindView(R.id.et_content)
    EditText mContentText;

    @BindView(R.id.line)
    View line;

    public InputItemView(@NonNull Context context) {
        this(context, null);
    }

    public InputItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mContentText.isEnabled()) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return true;
        }
    }

    public InputItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, R.layout.input_item_view_layout, this);
        ButterKnife.bind(this, view);
        setClickable(true);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.InputItemView);
        String title = ta.getString(R.styleable.InputItemView_inputItemTitle);
        String hint = ta.getString(R.styleable.InputItemView_inputItemHint);
        int inputType = ta.getInt(R.styleable.InputItemView_inputItemType, 1);
        boolean showArrow = ta.getBoolean(R.styleable.InputItemView_inputItemRightArrow, false);
        boolean showBottomLine = ta.getBoolean(R.styleable.InputItemView_showBottomLine, true);

        int contentLength = ta.getInt(R.styleable.InputItemView_inputItemMaxLength, 0);
        if (contentLength > 0) {
            mContentText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(contentLength)});
        }

        ta.recycle();

        mTitleText.setText(title);
        mContentText.setHint(TextUtils.isEmpty(hint) ? "请输入" : hint);
        if (inputType == 0) {
            mContentText.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (inputType == 1) {
            mContentText.setInputType(InputType.TYPE_CLASS_TEXT);
        } else if (inputType == 2) {
            mContentText.setInputType(InputType.TYPE_CLASS_PHONE);
        } else if (inputType == 3) {
            mContentText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }

        if (showArrow) {
            //mContentText.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(getContext(), R.mipmap.ic_more), null);
            mContentText.setEnabled(false);
        } else {
            mContentText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        line.setVisibility(showBottomLine ? View.VISIBLE : View.GONE);
    }

    public void setEnable(boolean enable) {
        mContentText.setEnabled(enable);
    }

    public void showArrow(boolean show) {
        if (show) {
            mContentText.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(getContext(), R.mipmap.ic_more), null);
        } else {
            mContentText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    public void addTextChangeListener(TextWatcher textWatcher) {
        mContentText.addTextChangedListener(textWatcher);
    }

    public String getContent() {
        return mContentText.getText().toString();
    }

    public void setContent(@NonNull String content) {
        mContentText.setText(content);
    }

    public void setTitle(@NonNull SpannableStringBuilder title) {
        mTitleText.setText(title);
    }

    public String getTitle() {
        return mTitleText.getText().toString();
    }

    public String getHint() {
        return mContentText.getHint().toString();
    }

    public TextView getTitleText() {
        return mTitleText;
    }
}
