package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardInfo;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeGuardBuildingDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：安全防护列表适配器
 * 创建人：rjf
 * 创建时间：2018/3/13 10:29.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardAdapter extends BaseRecyclerAdapter<SafeGuardInfo.DongsBean, SafeGuardAdapter.SafeGuardViewHolder> {

    private String projectName;

    public SafeGuardAdapter(Context mContext) {
        super(mContext);
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public SafeGuardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_safe_guard, parent, false);
        return new SafeGuardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SafeGuardViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        holder.tvBuildingName.setText(getItem(position).getLou());
        final String floorNum = getItem(position).getCengs() + "层";
        holder.tvBuildingNum.setText(floorNum);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SafeGuardBuildingDetailActivity.startActivity(mContext, getItem(position).getLou(), getItem(position).getCengs(), projectName);
            }
        });
    }

    public class SafeGuardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_building_name)
        TextView tvBuildingName;
        @BindView(R.id.tv_building_num)
        TextView tvBuildingNum;

        public SafeGuardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
