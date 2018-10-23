package com.ljkj.qxn.wisdomsitepro.ui.application.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：日历列表适配器
 * 创建人：liufei
 * 创建时间：2018/1/5 13:58
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CalendarDayAdapter extends BaseRecyclerAdapter<Date, CalendarDayAdapter.ViewHolder> {

    //选中的日期
    private Date selectDay = Calendar.getInstance().getTime();
    //有事项的日期
    private List<Integer> signDay = new ArrayList<>();

    public CalendarDayAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    public Date getSelectDay() {
        return selectDay;
    }

    public void setSelectDay(Date selectDay) {
        this.selectDay = selectDay;
        notifyDataSetChanged();
    }

    public void setSignDay(List<Integer> signDay) {
        this.signDay = signDay;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        holder.tvCalendarDay.setText(getItem(position).getDate() + "");

        if (getItem(position).getMonth() == selectDay.getMonth()) {
            if (signDay.contains(getItem(position).getDate())) {
                holder.ivDot.setBackgroundResource(R.drawable.dot_orange);
            } else {
                holder.ivDot.setBackgroundResource(R.drawable.dot_blue);
            }
            if (getItem(position).getDate() == selectDay.getDate()) {
                holder.tvCalendarDay.setBackgroundResource(R.drawable.dot_blue);
                holder.tvCalendarDay.setTextColor(ContextCompat.getColor(mContext, R.color.color_white));
            } else {
                holder.tvCalendarDay.setBackgroundResource(R.color.color_calendar_bg);
                holder.tvCalendarDay.setTextColor(ContextCompat.getColor(mContext, R.color.color_grey_666666));
            }
            holder.tvCalendarDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemViewClickListener.onViewClick(holder.tvCalendarDay, position);
                }
            });
        } else {
            holder.ivDot.setBackgroundResource(R.color.color_calendar_bg);
            holder.tvCalendarDay.setBackgroundResource(R.color.color_calendar_bg);
            holder.tvCalendarDay.setTextColor(ContextCompat.getColor(mContext, R.color.color_grey_cccccc));
            holder.tvCalendarDay.setOnClickListener(null);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_calendar_day)
        TextView tvCalendarDay;
        @BindView(R.id.iv_dot)
        ImageView ivDot;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
