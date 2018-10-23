package com.ljkj.qxn.wisdomsitepro.ui.personal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.DateUtil;
import com.ljkj.qxn.wisdomsitepro.data.entity.MessageInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import cdsp.android.ui.BaseRecyclerAdapter;

/**
 * 类描述：消息
 * 创建人：liufei
 * 创建时间：2018/2/2 10:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class MessageAdapter extends BaseRecyclerAdapter<MessageInfo, MessageAdapter.MsgViewHolder> {

    public MessageAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected boolean useItemAnimation() {
        return false;
    }

    @Override
    public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MsgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        MessageInfo messageInfo = getItem(position);
        if (messageInfo.getType().equals("1")) {
            holder.tvType.setBackgroundResource(R.color.color_green);
            holder.tvType.setText("安全");
        } else if (messageInfo.getType().equals("2")) {
            holder.tvType.setBackgroundResource(R.color.color_orange);
            holder.tvType.setText("质量");
        }
        holder.tvTitle.setText(messageInfo.getProject_name());
        holder.tvTroubleType.setText("隐患类型：" + messageInfo.getYhdj());
        holder.tvRectifyType.setText("整改类型：" + messageInfo.getZglx());
        holder.tvCheckDate.setText("检查日期：" + DateUtil.format(messageInfo.getJcrq_time()));
        holder.tvSendDate.setText("下发时间：" + DateUtil.format(messageInfo.getIssuedDate()));
    }

    public int getFlag(MessageInfo messageInfo) {
        String yhdj = messageInfo.getYhdj(); //隐患等级
        String sfsh = messageInfo.getSfsh(); // 是否审核

        int flag = 0;
        if ("一般隐患".equals(yhdj) && sfsh.equals("立即整改")) { //1
            flag = 1;

        } else if ("一般隐患".equals(yhdj) && sfsh.equals("待核查")) { //2
            flag = 2;

        } else if ("一般隐患".equals(yhdj) && sfsh.startsWith("合格")) { //3
            flag = 3;

        } else if ("一般隐患".equals(yhdj) && sfsh.equals("不合格")) { //4
            flag = 4;

        } else if ("重大隐患".equals(yhdj) && sfsh.equals("立即整改")) { //5
            flag = 5;

        } else if ("重大隐患".equals(yhdj) && sfsh.equals("待核查")) { //6
            flag = 6;

        } else if ("重大隐患".equals(yhdj) && sfsh.startsWith("合格")) { //7
            flag = 7;

        } else if ("重大隐患".equals(yhdj) && sfsh.equals("不合格")) { //8
            flag = 8;
        }
        return flag;
    }


    class MsgViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_detail)
        TextView tvDetail;
        @BindView(R.id.tv_trouble_type)
        TextView tvTroubleType;
        @BindView(R.id.tv_rectify_type)
        TextView tvRectifyType;
        @BindView(R.id.tv_check_date)
        TextView tvCheckDate;
        @BindView(R.id.tv_send_date)
        TextView tvSendDate;

        public MsgViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
