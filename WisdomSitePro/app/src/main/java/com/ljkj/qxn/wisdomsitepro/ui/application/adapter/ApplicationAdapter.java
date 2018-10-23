package com.ljkj.qxn.wisdomsitepro.ui.application.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.ApplicationItemEntity;

import java.util.List;

/**
 * 类描述：应用中心Adapter
 * 创建人：lxx
 * 创建时间：2018/6/20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ApplicationAdapter extends BaseSectionQuickAdapter<ApplicationItemEntity, BaseViewHolder> {

    public ApplicationAdapter(int layoutResId, int sectionHeadResId, List<ApplicationItemEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, ApplicationItemEntity item) {
        helper.setText(R.id.tv_application_header, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplicationItemEntity item) {
        TextView itemText = helper.getView(R.id.tv_application_item);
        itemText.setText(item.t.name);
        itemText.setCompoundDrawablesWithIntrinsicBounds(0, item.t.resId, 0, 0);
    }

}
