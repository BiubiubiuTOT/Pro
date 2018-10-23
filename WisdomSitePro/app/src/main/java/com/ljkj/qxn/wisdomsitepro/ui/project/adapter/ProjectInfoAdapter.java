package com.ljkj.qxn.wisdomsitepro.ui.project.adapter;

import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.TypedValue;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;

import java.util.List;


public class ProjectInfoAdapter extends BaseQuickAdapter<ProjectInfoAdapter.Data, BaseViewHolder> {

    public ProjectInfoAdapter(@Nullable List<Data> data) {
        super(R.layout.adapter_project_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Data item) {
        helper.setText(R.id.tv_title, item.title);
        helper.setTextColor(R.id.tv_title, item.color);
        helper.setTextColor(R.id.tv_value, item.color);

        SpannableString spannableString = new SpannableString(item.value);
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, helper.itemView.getContext().getResources().getDisplayMetrics());
        spannableString.setSpan(new AbsoluteSizeSpan(size), 0, item.value.length() - 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        helper.setText(R.id.tv_value, spannableString);

        int position = helper.getAdapterPosition();
        if (position > 0 && (position + 1) % 3 == 0) {
            helper.setVisible(R.id.line, false);
        } else {
            helper.setVisible(R.id.line, true);
        }

    }

    public static class Data {
        public Data(String title, String value,int color) {
            this.title = title;
            this.value = value;
            this.color = color;
        }

        public String title;
        public String value;
        public int color;
    }

}
