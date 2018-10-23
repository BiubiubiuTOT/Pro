package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafeGuardBuildInfo;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeGuardFloorDetailActivity;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：安全防护楼栋详情列表适配器
 * 创建人：rjf
 * 创建时间：2018/3/13 10:29.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardBuildingDetailAdapter extends BaseRecyclerAdapter<SafeGuardBuildInfo.CengsBean, SafeGuardBuildingDetailAdapter.SafeGuardViewHolder> {


    private String buildingName;

    OnItemDeleteClickListener onItemDeleteClickListener;

    public SafeGuardBuildingDetailAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    public void setOnItemDeleteClickListener(OnItemDeleteClickListener onItemDeleteClickListener) {
        this.onItemDeleteClickListener = onItemDeleteClickListener;
    }

    @Override
    public SafeGuardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_safe_guard_floor, parent, false);
        return new SafeGuardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SafeGuardViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        holder.tvBuildingName.setText(getItem(position).getCeng());

        holder.contentContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SafeGuardFloorDetailActivity.startActivity(mContext,getItem(position).getCeng(), buildingName, getItem(position).getId());
            }
        });

        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onItemDeleteClickListener){
                    onItemDeleteClickListener.onItemDeleteClick(position,(SwipeMenuLayout)holder.itemView);
                }
            }
        });
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public class SafeGuardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_building_name)
        TextView tvBuildingName;
        @BindView(R.id.tv_building_num)
        TextView tvBuildingNum;
        @BindView(R.id.content_container)
        RelativeLayout contentContainer;
        @BindView(R.id.tv_delete)
        TextView tvDelete;

        public SafeGuardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemDeleteClickListener{
        void onItemDeleteClick(int position, SwipeMenuLayout itemView);
    }
}
