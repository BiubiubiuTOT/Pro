package com.ljkj.qxn.wisdomsitepro.ui.message.adapter;

import android.support.annotation.Nullable;
import android.text.format.Formatter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.WApplication;
import com.ljkj.qxn.wisdomsitepro.data.AttachmentInfo;

import java.util.List;

/**
 * 类描述：公告详情附件adapter
 * 创建人：lxx
 * 创建时间：2018/6/22
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NoticeDetailAttachmentAdapter extends BaseQuickAdapter<AttachmentInfo, BaseViewHolder> {

    public NoticeDetailAttachmentAdapter(@Nullable List<AttachmentInfo> data) {
        super(R.layout.adapter_notice_detail_attachment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AttachmentInfo item) {
        helper.setText(R.id.tv_title, item.getAttachmentTitle());
        long size = item.getFileSize();
        helper.setText(R.id.tv_size, Formatter.formatFileSize(WApplication.getApplication(), size));
    }


}
