package com.ljkj.qxn.wisdomsitepro.ui.application.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.WageInfo;

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

public class PayRollListAdapter extends BaseRecyclerAdapter<WageInfo, PayRollListAdapter.PayRollListViewHolder> {

    public PayRollListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public PayRollListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payroll, parent, false);
        return new PayRollListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PayRollListViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        String name = getItem(position).getName();
        WageInfo wageInfo = getItem(position);
        if (name != null && name.length() > 0) {
            String str = String.valueOf(name.charAt(name.length() - 1));
            holder.ivImg.setText(str);
        }
        holder.tvTitle.setText(name);
        holder.groupText.setText("班组：" + wageInfo.getTeamsType());
        String shouldWage = wageInfo.getShouldWage();
        holder.tvShouldPaid.setText("应发工资：" + shouldWage + "元");
        String realWage = wageInfo.getRealWage();
        holder.tvPaided.setText("实发工资：" + realWage + "元");
        holder.statusImage.setImageResource(!realWage.equals(shouldWage) ? R.mipmap.ic_uncleared : R.mipmap.ic_already_clear);
        holder.tvDate.setText("发放月份：" + wageInfo.getPayWageMonth());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }

    static class PayRollListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        TextView ivImg;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_group)
        TextView groupText;
        @BindView(R.id.tv_should_paid)
        TextView tvShouldPaid;
        @BindView(R.id.tv_paided)
        TextView tvPaided;

        @BindView(R.id.iv_status)
        ImageView statusImage;
        @BindView(R.id.tv_date)
        TextView tvDate;

        public PayRollListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
