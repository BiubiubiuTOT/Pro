package com.ljkj.qxn.wisdomsitepro.ui.safe.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;

import java.util.List;

/**
 * 类描述：检查附件Adapter
 * 创建人：lxx
 * 创建时间：2018/9/6
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CheckAttachmentAdapter extends BaseQuickAdapter<FileEntity, BaseViewHolder> {

    public CheckAttachmentAdapter(@Nullable List<FileEntity> data) {
        super(R.layout.adapter_check_attachment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FileEntity item) {
        TextView attachmentText = helper.getView(R.id.tv_attachment);
        attachmentText.setText(item.fileName);
        attachmentText.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_attachment_word, 0, 0, 0);
        String extend = item.fileExt;
        if ("jpg".equals(extend) || "jpeg".equals(extend) || "png".equals(extend)) {
            attachmentText.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_attachment_image, 0, 0, 0);
        } else if ("doc".equals(extend) || "docx".equals(extend)) {
            attachmentText.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_attachment_word, 0, 0, 0);
        } else if ("pdf".equals(extend)) {
            attachmentText.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_attachment_pdf, 0, 0, 0);
        } else if ("xlsx".equals(extend) || "xls".equals(extend)) {
            attachmentText.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_attachment_excel, 0, 0, 0);
        } else {
            attachmentText.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_attachment_other, 0, 0, 0);
        }
    }

}
