package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafePatrolInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：巡检
 * 创建人：liufei
 * 创建时间：2018/2/2 10:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class SafeInspectionListV2Adapter extends BaseRecyclerAdapter<SafePatrolInfo, SafeInspectionListV2Adapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;

    public SafeInspectionListV2Adapter(Context mContext) {
        super(mContext);
    }


    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common_patrol, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        SafePatrolInfo patrolInfo = getItem(position);
        holder.tvName.setText("整改班组：" + patrolInfo.getTeam());
        holder.tvNumber.setText("安全巡检编号：" + patrolInfo.getCheckCode());

        holder.tvReason.setText("事故原因：" + patrolInfo.getAccidentCause());
        holder.tvLose.setText("伤亡及损失：" + patrolInfo.getPersonEconomicLoss());

        holder.tvDirector.setText("班组负责人：" + patrolInfo.getTeamManager());
        holder.tvDate.setText("检查日期：" + patrolInfo.getCheckDate());

        final int status = patrolInfo.getStatus();//状态

        if (status == 2) { //待整改
            holder.tvRectify.setVisibility(View.GONE);
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_wait_rectify);

        } else if (status == 3) { //3待核查
            holder.tvRectify.setVisibility(View.GONE);
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_wait_audit);

        } else if (status == 4) { //4不合格
            holder.tvRectify.setVisibility(View.GONE);
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_unqualified);

        } else if (status == 5) { //合格
            //查看详情
            holder.tvRectify.setVisibility(View.GONE);
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_qualified);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position, status);
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, int flag);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_number)
        TextView tvNumber;

        @BindView(R.id.tv_rectify)
        TextView tvRectify;

        @BindView(R.id.iv_status)
        ImageView ivStatus;

        @BindView(R.id.tv_reason)
        TextView tvReason;

        @BindView(R.id.tv_lose)
        TextView tvLose;

        @BindView(R.id.tv_director)
        TextView tvDirector;

        @BindView(R.id.tv_date)
        TextView tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
