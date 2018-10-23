package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.SafeGuardEntity;
import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeGuardAddFloorActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：安全防护新增楼层设置避难层高度列表适配器
 * 创建人：rjf
 * 创建时间：2018/3/13 10:29.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class SafeGuardFloorAddAdapter extends BaseRecyclerAdapter<SafeGuardEntity, SafeGuardFloorAddAdapter.SafeGuardViewHolder> {

    private int floors;

    private List<String> refugeFloorList = new ArrayList<>();

    public SafeGuardFloorAddAdapter(Context mContext) {
        super(mContext);
    }

    public void setFloors(int floors) {
        this.floors = floors;
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
    public void onBindViewHolder(final SafeGuardViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        holder.tvBuildingName.setText(getItem(position).getBuildingName());
        holder.tvBuildingNum.setText(getItem(position).getBuildingNum());
        holder.tvBuildingNum.setTextColor(ContextCompat.getColor(mContext,R.color.color_grey_cccccc));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refugeFloorList.clear();
                int currentFloors = floors - (position * SafeGuardAddFloorActivity.AVERAGE_REFUGE_FLOOR);
                int averageFloors = Math.min(currentFloors, SafeGuardAddFloorActivity.AVERAGE_REFUGE_FLOOR);
                for (int i = 1; i <= averageFloors; i++) {
                    refugeFloorList.add(String.valueOf(i));
                }

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
            ButterKnife.bind(this,itemView);
        }
    }
}
