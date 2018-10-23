package com.ljkj.qxn.wisdomsitepro.ui.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.PatrolInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.ui.common.BaseInspectionListActivity;

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

public class InspectionListAdapter extends BaseRecyclerAdapter<PatrolInfo, InspectionListAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private String type = BaseInspectionListActivity.QUALITY_TYPE;

    public InspectionListAdapter(Context mContext) {
        super(mContext);
    }

    public InspectionListAdapter(Context mContext, String type) {
        this(mContext);
        this.type = type;
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

        PatrolInfo patrolInfo = getItem(position);
        holder.tvName.setText("整改班组：" + patrolInfo.getZgbz());
        if (type.equals(BaseInspectionListActivity.QUALITY_TYPE)) {
            holder.tvNumber.setText("质量巡检编号：" + patrolInfo.getCode());
        } else if (type.equals(BaseInspectionListActivity.SAFE_TYPE)) {
            holder.tvNumber.setText("安全巡检编号：" + patrolInfo.getCode());
        }


        holder.tvReason.setText("事故原因：" + patrolInfo.getWhyShow());
        holder.tvLose.setText("伤亡及损失：" + patrolInfo.getLossShow());

        holder.tvDirector.setText("班组负责人：" + patrolInfo.getBzfzr());
        holder.tvDate.setText("检查日期：" + patrolInfo.getUpTime());

        String status = patrolInfo.getStatus();//状态
        String userName = UserManager.getInstance().getUserId();//用户名
        String createBy = patrolInfo.getCreateBy();//创建人

        int flag = 0;
        if (TextUtils.equals(status, "1")) { //创建
            if (TextUtils.equals(userName, createBy)) {// 创建人
                //查看详情
                holder.tvRectify.setVisibility(View.GONE);

                holder.ivStatus.setVisibility(View.VISIBLE); //原来是不显示的，现在改成显示成待整改
                holder.ivStatus.setImageResource(R.mipmap.ic_status_wait_rectify);

                flag = 0;
            } else { //班组负责人
                //立即整改
//                holder.tvRectify.setVisibility(View.VISIBLE);
//                holder.tvRectify.setText("立即整改");
//                holder.tvRectify.setBackgroundResource(R.drawable.bg_rectangle_color_main_20dp);

                holder.tvRectify.setVisibility(View.GONE);
                holder.ivStatus.setVisibility(View.VISIBLE);
                holder.ivStatus.setImageResource(R.mipmap.ic_status_wait_rectify);
                flag = 2;

            }

        } else if (TextUtils.equals(status, "2")) { //评审
            if (TextUtils.equals(userName, createBy)) { // 创建人
                //立即审批
//                holder.tvRectify.setVisibility(View.VISIBLE);
//                holder.tvRectify.setText("立即审批");
//                holder.tvRectify.setBackgroundResource(R.drawable.bg_rectangle_color_main_20dp);

                holder.tvRectify.setVisibility(View.GONE);
                holder.ivStatus.setVisibility(View.VISIBLE);
                holder.ivStatus.setImageResource(R.mipmap.ic_status_wait_audit);
                flag = 1;
            } else { //班组负责人
                //查看详情
                holder.tvRectify.setVisibility(View.GONE);
                holder.ivStatus.setVisibility(View.VISIBLE);
                holder.ivStatus.setImageResource(R.mipmap.ic_status_wait_audit);
                flag = 5;
            }

        } else if (TextUtils.equals(status, "3")) { //重新整改
            if (TextUtils.equals(userName, createBy)) { // 创建人
                //查看详情
                holder.tvRectify.setVisibility(View.GONE);

                holder.ivStatus.setVisibility(View.VISIBLE); //原来是不显示的，现在改成显示成待整改
                holder.ivStatus.setImageResource(R.mipmap.ic_status_unqualified);
                flag = 0;

            } else { //班组负责人
                //重新整改
//                holder.tvRectify.setVisibility(View.VISIBLE);
//                holder.tvRectify.setText("重新整改");
//                holder.tvRectify.setBackgroundResource(R.drawable.bg_rectangle_orange_20dp);

                holder.tvRectify.setVisibility(View.GONE);
                holder.ivStatus.setVisibility(View.VISIBLE);
                holder.ivStatus.setImageResource(R.mipmap.ic_status_unqualified);
                flag = 3;
            }

        } else if (TextUtils.equals(status, "4")) { //审批
            if (TextUtils.equals(userName, createBy)) { // 创建人
                //立即审批
//                holder.tvRectify.setVisibility(View.VISIBLE);
//                holder.tvRectify.setText("立即审批");
//                holder.tvRectify.setBackgroundResource(R.drawable.bg_rectangle_color_main_20dp);

                holder.tvRectify.setVisibility(View.GONE);
                holder.ivStatus.setVisibility(View.VISIBLE);
                holder.ivStatus.setImageResource(R.mipmap.ic_status_wait_audit);
                flag = 1;

            } else { //班组负责人
                //查看详情
                holder.tvRectify.setVisibility(View.GONE);
                holder.ivStatus.setVisibility(View.VISIBLE);
                holder.ivStatus.setImageResource(R.mipmap.ic_status_wait_audit);
                flag = 5;
            }

        } else if (TextUtils.equals(status, "5")) { //通过
            //查看详情
            holder.tvRectify.setVisibility(View.GONE);
            holder.ivStatus.setVisibility(View.VISIBLE);
            holder.ivStatus.setImageResource(R.mipmap.ic_status_qualified);
            flag = 4;
        }

        final int finalFlag = flag;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position, finalFlag);
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
