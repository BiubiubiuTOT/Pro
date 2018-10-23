package com.ljkj.qxn.wisdomsitepro.ui.common.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.DateUtil;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.data.entity.EnclosureInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.InspectionInfo;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：巡检详情-整改回复adapter
 * 创建人：lxx
 * 创建时间：2018/4/8 09:33
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class InspectionReformAdapter extends BaseRecyclerAdapter<InspectionInfo.Reform, InspectionReformAdapter.ViewHolder> {
    public InspectionReformAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reform, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        InspectionInfo.Reform reform = getItem(position);
        holder.checkDateItem.setContent(DateUtil.format(reform.createDate));
        holder.groupLeaderItem.setContent(reform.teamLeader);
        holder.postManItem.setContent(reform.postMan);
        holder.rectifyContentText.setText(reform.reformDetail);
        if ("5".equals(reform.statusHistory)) {
            holder.rectifyResultText.setText("合格");
            holder.rectifyResultText.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bg_rectangle_color_green_20dp));
            holder.reasonText.setVisibility(View.GONE);
        } else if ("3".equals(reform.statusHistory)) {
            holder.rectifyResultText.setText("不合格");
            holder.rectifyResultText.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bg_rectangle_orange_20dp));
            holder.reasonText.setVisibility(View.VISIBLE);
            holder.reasonText.setText("不合格原因：" + reform.noThroughReason);
        } else if ("2".equals(reform.statusHistory) || "4".equals(reform.statusHistory)) {
            holder.rectifyResultText.setText("待核查");
            holder.rectifyResultText.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bg_rectangle_orange_20dp));
            holder.reasonText.setVisibility(View.GONE);
        } else {
            holder.reasonText.setVisibility(View.GONE);
            holder.resultLayout.setVerticalGravity(View.GONE);
        }

        ShowImageAdapter rectifyImageAdapter = new ShowImageAdapter(mContext);
        holder.rectifyImageRV.setLayoutManager(new GridLayoutManager(mContext, 3));
        holder.rectifyImageRV.setAdapter(rectifyImageAdapter);
        showImage(reform.id, rectifyImageAdapter);

        holder.replyText.setText("整改回复" + (position + 1));
        holder.replyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.contentLayout.setVisibility(holder.contentLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                Drawable right = holder.contentLayout.getVisibility() == View.VISIBLE ? ContextCompat.getDrawable(mContext, R.mipmap.ic_collapse) : ContextCompat.getDrawable(mContext, R.mipmap.ic_expand);
                holder.replyText.setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
            }
        });
    }

    private void showImage(String id, final ShowImageAdapter adapter) {
        CommonModel.newInstance().listFiles("reform_file", id, new JsonCallback<ResponseData<List<EnclosureInfo>>>(new TypeToken<ResponseData<List<EnclosureInfo>>>() {
        }) {
            @Override
            public void onSuccess(ResponseData<List<EnclosureInfo>> data) {
                if (data.isSuccess()) {
//                    adapter.loadData(data.getResult());
                }
            }

            @Override
            protected void onError(int errcode, String errmsg) {
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_reply)
        TextView replyText;

        @BindView(R.id.item_check_date)
        ItemView checkDateItem;

        @BindView(R.id.item_group_leader)
        ItemView groupLeaderItem;

        @BindView(R.id.item_post_man)
        ItemView postManItem;

        @BindView(R.id.tv_rectify_content)
        TextView rectifyContentText;

        @BindView(R.id.rl_rectify_image)
        RecyclerView rectifyImageRV;

        @BindView(R.id.tv_rectify_result)
        TextView rectifyResultText;

        @BindView(R.id.tv_reason)
        TextView reasonText;

        @BindView(R.id.ll_content)
        LinearLayout contentLayout;

        @BindView(R.id.rl_result)
        RelativeLayout resultLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
