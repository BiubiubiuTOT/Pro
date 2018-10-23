package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeEduInfo;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeEducationDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：安全教育
 * 创建人：liufei
 * 创建时间：2018/2/2 10:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SafeEducationAdapter extends BaseRecyclerAdapter<SafeEduInfo, SafeEducationAdapter.ViewHolder> {

    public SafeEducationAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_safe_education, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        String status = getItem(position).getIsSb();
        holder.tvTitle.setText(getItem(position).getName());
        holder.tvDate.setText("交底日期：" + getItem(position).getDate());
        holder.tvTeacher.setText("培训人：" + getItem(position).getPxPerson());

        if (TextUtils.equals(status, "1")) {
            holder.tvStatus.setImageResource(R.mipmap.ic_safe_edu_report);
        } else {
            holder.tvStatus.setImageResource(R.mipmap.ic_safe_edu_no_report);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SafeEducationDetailActivity.startActivity(mContext, getItem(position).getId());
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.iv_status)
        ImageView tvStatus;

        @BindView(R.id.tv_date)
        TextView tvDate;

        @BindView(R.id.tv_teacher)
        TextView tvTeacher;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
