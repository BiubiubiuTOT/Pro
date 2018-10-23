package com.ljkj.qxn.wisdomsitepro.ui.message.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.message.NoticeInfo;

import java.util.List;

/**
 * 类描述：公告adapter
 * 创建人：lxx
 * 创建时间：2018/6/22
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NoticeAdapter extends BaseQuickAdapter<NoticeInfo, BaseViewHolder> {

    public NoticeAdapter(@Nullable List<NoticeInfo> data) {
        super(R.layout.adapter_notice, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeInfo item) {
        helper.setText(R.id.tv_title, item.getNoticeTitle());
        helper.setText(R.id.tv_date, item.getCreateTime());
        helper.setText(R.id.tv_publisher, item.getFromUser());
        helper.setText(R.id.tv_read, "已读：" + item.getRead());
        helper.setText(R.id.tv_unread, "未读:" + item.getNoRead());
        if (item.getStatus() == NoticeInfo.NOTICE_READ) {
            helper.setGone(R.id.tv_red_dot, false);
            helper.setTextColor(R.id.tv_title, Color.parseColor("#666666"));
        } else {
            helper.setGone(R.id.tv_red_dot, true);
            helper.setTextColor(R.id.tv_title, Color.BLACK);
        }
    }

}
