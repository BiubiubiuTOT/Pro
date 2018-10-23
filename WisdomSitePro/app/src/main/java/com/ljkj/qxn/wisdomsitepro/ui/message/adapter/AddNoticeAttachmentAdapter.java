package com.ljkj.qxn.wisdomsitepro.ui.message.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;

import java.io.File;
import java.util.List;

/**
 * 类描述：新增公告附件Adapter
 * 创建人：lxx
 * 创建时间：2018/6/22
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AddNoticeAttachmentAdapter extends BaseItemDraggableAdapter<File, BaseViewHolder> {

    public AddNoticeAttachmentAdapter(@Nullable List<File> data) {
        super(R.layout.adapter_add_notice_attachment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, File item) {
        helper.setText(R.id.tv_name, item.getName());
    }
}
