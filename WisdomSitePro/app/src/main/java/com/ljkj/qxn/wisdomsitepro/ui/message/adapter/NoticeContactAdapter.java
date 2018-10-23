package com.ljkj.qxn.wisdomsitepro.ui.message.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.message.NoticeContactInfo;

import java.util.List;

/**
 * 类描述：公告联系人adapter
 * 创建人：lxx
 * 创建时间：2018/6/23
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NoticeContactAdapter extends BaseQuickAdapter<List<NoticeContactInfo>, BaseViewHolder> {
    public NoticeContactAdapter(@Nullable List<List<NoticeContactInfo>> data) {
        super(R.layout.adapter_notice_contact, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, List<NoticeContactInfo> item) {
        int selectCount = 0;
        for (NoticeContactInfo info : item) {
            if (info.isChecked()) {
                selectCount++;
            }
        }
        if (selectCount == 0) {
            helper.setText(R.id.tv_team, item.get(0).getDepartmentName() + "（" + item.size() + "人）");
        } else {
            String str = item.get(0).getDepartmentName() + "（" + selectCount + "/" + item.size() + ")";
            SpannableString spannableString = new SpannableString(str);
            int start = item.get(0).getDepartmentName().length();
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#248bfe")), start, str.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            helper.setText(R.id.tv_team, spannableString);
        }
        helper.addOnClickListener(R.id.tv_subordinate);
        helper.addOnClickListener(R.id.tv_team);

        TextView teamText = helper.getView(R.id.tv_team);
        if (isAllChecked(item)) {
            teamText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, R.mipmap.ic_circle_select), null, null, null);
        } else {
            teamText.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext, R.mipmap.ic_circle_unselected), null, null, null);
        }
    }

    public boolean isAllChecked(List<NoticeContactInfo> items) {
        for (NoticeContactInfo info : items) {
            if (!info.isChecked()) {
                return false;
            }
        }
        return true;
    }

}
