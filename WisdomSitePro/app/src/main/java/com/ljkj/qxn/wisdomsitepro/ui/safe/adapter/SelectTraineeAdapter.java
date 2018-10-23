package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.LabourInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：新增安全教育-选择受训人 adapter
 * 创建人：lxx
 * 创建时间：2018/4/11 14:47
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SelectTraineeAdapter extends BaseRecyclerAdapter<ArrayList<LabourInfo>, SelectTraineeAdapter.ViewHolder> {

    private boolean[] teamsSelect;

    public SelectTraineeAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_select_trainee, null);
        return new ViewHolder(view);
    }

    @Override
    public void loadData(List<? extends ArrayList<LabourInfo>> datas) {
        super.loadData(datas);
        teamsSelect = new boolean[getItemCount()];
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        ArrayList<LabourInfo> labours = getItem(position);
        holder.teamText.setText(labours.get(0).getTeamsType());
        final SelectTraineeChildAdapter childAdapter = new SelectTraineeChildAdapter(mContext);
        holder.recyclerView.setAdapter(childAdapter);
        childAdapter.loadData(labours);

        holder.recyclerView.setVisibility(View.GONE);
        holder.line1.setVisibility(View.GONE);
        holder.line2.setVisibility(View.GONE);

        if (teamsSelect[position]) {
            holder.teamText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, R.mipmap.ic_circle_select), null, null, null);
        } else {
            holder.teamText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, R.mipmap.ic_circle_unselected), null, null, null);
        }

        childAdapter.setOnItemClickListener(new SelectTraineeChildAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LabourInfo labourInfo, int position) {
                //nothing to do
            }
        });

        holder.teamText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (teamsSelect[position]) {
                    holder.teamText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, R.mipmap.ic_circle_unselected), null, null, null);
                    teamsSelect[position] = false;
                    childAdapter.unselectedAll();

                } else {
                    holder.teamText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, R.mipmap.ic_circle_select), null, null, null);
                    teamsSelect[position] = true;
                    childAdapter.selectAll();
                }

            }
        });

        holder.subordinateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = holder.recyclerView.getVisibility();
                holder.recyclerView.setVisibility(visibility == View.VISIBLE ? View.GONE : View.VISIBLE);
                holder.line1.setVisibility(visibility == View.VISIBLE ? View.GONE : View.VISIBLE);
                holder.line2.setVisibility(visibility == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
    }

    public void updateTeamsSelect(boolean select) {
        if (teamsSelect != null) {
            for (int i = 0; i < teamsSelect.length; i++) {
                teamsSelect[i] = select;
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_team)
        TextView teamText;

        @BindView(R.id.tv_subordinate)
        TextView subordinateText;

        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        @BindView(R.id.line1)
        View line1;

        @BindView(R.id.line2)
        View line2;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }

    }

}
