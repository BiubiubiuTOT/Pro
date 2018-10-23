package com.ljkj.qxn.wisdomsitepro.ui.application.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConstructLogInfo;
import com.ljkj.qxn.wisdomsitepro.ui.application.ConstructLogDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：
 * 创建人：mhl
 * 创建时间：2018/2/2 10:25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class ConstructLogListAdapter extends BaseRecyclerAdapter<ConstructLogInfo, ConstructLogListAdapter.ConstructLogListViewHolder> {

    public ConstructLogListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ConstructLogListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_construct_log, parent, false);
        return new ConstructLogListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ConstructLogListViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        String recorderName = getItem(position).getRecorder();
        String proHead = String.format(mContext.getString(R.string.construct_log_project_head), getItem(position).getPro_head());
        holder.tvProjectHead.setText(proHead);
        if (recorderName != null && recorderName.length() > 0) {
            String str = String.valueOf(recorderName.charAt(recorderName.length() - 1));
            holder.avatarText.setText(str);
        } else {
            holder.avatarText.setText("");
        }

        String recorder = String.format(mContext.getString(R.string.construct_log_project_recorder), recorderName);
        holder.recorderPersonText.setText(recorder);

        holder.codeText.setText("编号：" + getItem(position).getCode());
        holder.tvDate.setText("日   期：" + getItem(position).getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstructLogDetailActivity.startActivity(mContext, getItem(position).getId());
            }
        });
    }

    static class ConstructLogListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_avatar)
        TextView avatarText;

        @BindView(R.id.tv_project_head)
        TextView tvProjectHead;

        @BindView(R.id.tv_recorder_person)
        TextView recorderPersonText;

        @BindView(R.id.tv_code)
        TextView codeText;

        @BindView(R.id.tv_date)
        TextView tvDate;

        public ConstructLogListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
