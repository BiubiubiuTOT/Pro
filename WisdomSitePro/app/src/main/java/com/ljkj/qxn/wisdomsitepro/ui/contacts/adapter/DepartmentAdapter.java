package com.ljkj.qxn.wisdomsitepro.ui.contacts.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DeptEntity;

import java.util.List;

/**
 * 类描述：联系人部门列表数据适配器
 * 创建人：rjf
 * 创建时间：2018/6/26 11:40.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class DepartmentAdapter extends BaseSectionQuickAdapter<DeptEntity, BaseViewHolder> {

    public DepartmentAdapter(int layoutResId, int sectionHeadResId, List<DeptEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, DeptEntity item) {
        helper.setText(R.id.tv_header, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeptEntity item) {
        String departmentInfo = item.t.getName() + "（" + item.t.getNum() + "）";
        helper.setText(R.id.tv_department_info, departmentInfo);
    }
}
