package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.LabourInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：新增安全教育-选择受训人 子adapter
 * 创建人：lxx
 * 创建时间：2018/4/11 15:47
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SelectTraineeChildAdapter extends BaseRecyclerAdapter<LabourInfo, SelectTraineeChildAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener;

    public SelectTraineeChildAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_select_trainee_child, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        final LabourInfo labourInfo = getItem(position);
        holder.nameText.setText(labourInfo.getName());
        if (labourInfo.isSelected()) {
            holder.nameText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, R.mipmap.ic_circle_select), null, null, null);
        } else {
            holder.nameText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, R.mipmap.ic_circle_unselected), null, null, null);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (labourInfo.isSelected()) {
                    labourInfo.setSelected(false);
                    holder.nameText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, R.mipmap.ic_circle_unselected), null, null, null);
                } else {
                    holder.nameText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, R.mipmap.ic_circle_select), null, null, null);
                    labourInfo.setSelected(true);
                }
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(labourInfo, position);
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(LabourInfo labourInfo, int position);
    }

    /**
     * 全选
     */
    public void selectAll() {
        List<LabourInfo> datas = getData();
        for (LabourInfo labourInfo : datas) {
            labourInfo.setSelected(true);
        }
        notifyDataSetChanged();
    }

    /**
     * 全不选
     */
    public void unselectedAll() {
        List<LabourInfo> datas = getData();
        for (LabourInfo labourInfo : datas) {
            labourInfo.setSelected(false);
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView nameText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
