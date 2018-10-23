package com.ljkj.qxn.wisdomsitepro.ui.application.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.AttendanceInfo;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：考勤管理 adapter
 * 创建人：lxx
 * 创建时间：2018/4/26 15:07
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class AttendManageListAdapter extends BaseRecyclerAdapter<AttendanceInfo, AttendManageListAdapter.AttendManageListViewHolder> {

    public AttendManageListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public AttendManageListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attend_manage, parent, false);
        return new AttendManageListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final AttendManageListViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        final AttendanceInfo attendanceInfo = getItem(position);
        String onWrok = attendanceInfo.getOnWrok();
        String[] t = new String[0];
        if (!TextUtils.isEmpty(onWrok)) {
            t = onWrok.split(" ");
        }

        holder.tvDate.setText("考勤日期：" + (t.length > 0 ? t[0] : ""));
        holder.tvPlace.setText("考勤地点：" + attendanceInfo.getAddress());

        String name = attendanceInfo.getName();
        holder.tvName.setText(name);
        if (name != null && name.length() > 0) {
            String str = String.valueOf(name.charAt(name.length() - 1));
            holder.imgAvatar.setText(str);
        }

        holder.tvWorkInTime.setText("上班打卡：" + onWrok);
        holder.tvWorkOutTime.setText("下班打卡：" + attendanceInfo.getOffWork());
//        holder.tvWorkType.setText("班组：" + attendanceInfo.getWorkType());
        holder.tvTotalHours.setText("当日累计工时：" + attendanceInfo.getWorkHour() + "小时" + attendanceInfo.getWorkMinute() + "分钟");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AttendDetailActivity.startActivity(mContext, attendanceInfo);
            }
        });
    }

    private String format(String millisStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        try {
            long millis = Long.parseLong(millisStr);
            return dateFormat.format(millis);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    static class AttendManageListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_avatar)
        TextView imgAvatar;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_detail)
        TextView tvDetail;

        @BindView(R.id.tv_work_type)
        TextView tvWorkType;

        @BindView(R.id.tv_place)
        TextView tvPlace;

        @BindView(R.id.tv_date)
        TextView tvDate;

        @BindView(R.id.tv_total_hours)
        TextView tvTotalHours;

        @BindView(R.id.tv_work_in_time)
        TextView tvWorkInTime;

        @BindView(R.id.tv_work_out_time)
        TextView tvWorkOutTime;

        public AttendManageListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
