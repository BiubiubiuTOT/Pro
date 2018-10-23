package com.ljkj.qxn.wisdomsitepro.ui.message.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.message.NoticeContactInfo;

import java.util.List;

/**
 * 类描述：公告联系人选择Adapter
 * 创建人：lxx
 * 创建时间：2018/6/23
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NoticeContactSelectAdapter extends BaseQuickAdapter<NoticeContactInfo, BaseViewHolder> {
    public NoticeContactSelectAdapter(@Nullable List<NoticeContactInfo> data) {
        super(R.layout.adapter_notice_contact_select, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeContactInfo item) {
        TextView personText = helper.getView(R.id.tv_person);
        personText.setText(item.getRealName());
        if (item.isChecked()) {
            personText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, R.mipmap.ic_circle_select), null, null, null);
        } else {
            personText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, R.mipmap.ic_circle_unselected), null, null, null);
        }
    }

}
