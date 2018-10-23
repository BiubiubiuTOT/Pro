package com.ljkj.qxn.wisdomsitepro.ui.application.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.LabourInfo;
import com.ljkj.qxn.wisdomsitepro.ui.application.LaborPersonDetailActivity;

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

public class LaborPersonFileListAdapter extends BaseRecyclerAdapter<LabourInfo, LaborPersonFileListAdapter.LaborPersonFileListViewHolder> {

    public LaborPersonFileListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public LaborPersonFileListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_labor_personnel_file, parent, false);
        return new LaborPersonFileListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(LaborPersonFileListViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        String name = getItem(position).getName();
        holder.tvName.setText(getItem(position).getName());
        holder.tvPhone.setText(getItem(position).getPhoneNum());
        holder.tvWorkType.setText("班组：" + getItem(position).getTeamsType());
        if (name != null && name.length() > 0) {
            String str = String.valueOf(name.charAt(name.length() - 1));
            holder.imgAvatar.setText(str);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getItem(position).getId();
                Intent intent = new Intent(mContext, LaborPersonDetailActivity.class);
                intent.putExtra("id", id);
                mContext.startActivity(intent);
            }
        });
    }

    static class LaborPersonFileListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_avatar)
        TextView imgAvatar;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_detail)
        TextView tvDetail;

        @BindView(R.id.tv_phone)
        TextView tvPhone;

        @BindView(R.id.tv_work_type)
        TextView tvWorkType;

        public LaborPersonFileListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
