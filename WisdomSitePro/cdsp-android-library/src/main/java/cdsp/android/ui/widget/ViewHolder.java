package cdsp.android.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ================================================
 * 作    者：loujingying@aliyun.com
 * 版    本：1.0.0
 * 创建日期：2017/9/5
 * 描    述：通用的ViewHolder
 * 修订历史：
 * ================================================
 */

public class ViewHolder {

    private View mView;
    private SparseArray<View> sparseArray = new SparseArray<View>();

    private ViewHolder(){}

    public static ViewHolder instance(Context context, int layoutId, ViewGroup parent) {
        View v = View.inflate(context, layoutId, parent);

        ViewHolder holder = new ViewHolder();
        v.setTag(holder);
        holder.setView(v);

        return holder;
    }

    private void setView(View view) {
        this.mView = view;
    }

    public View getView() {
        return mView;
    }

    private <T extends View> T findViewById(final int viewId) {
        View v = sparseArray.get(viewId);
        if (v == null) {
            v = mView.findViewById(viewId);
            sparseArray.put(viewId, v);
        }
        return (T) v;
    }

    public ViewHolder setText(final int viewId, final String value) {
        TextView v = findViewById(viewId);
        v.setText(value);
        return this;
    }

    public ViewHolder setImageBitmap(final int viewId, final Bitmap bm) {
        ImageView v = findViewById(viewId);
        v.setImageBitmap(bm);
        return this;
    }

    public ViewHolder setImageResource(final int viewId, final int resId) {
        ImageView v = findViewById(viewId);
        v.setImageResource(resId);
        return this;
    }

    public ViewHolder setImageResource(View.OnClickListener l) {
        mView.setOnClickListener(l);
        return this;
    }

}