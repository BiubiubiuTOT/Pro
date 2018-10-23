package com.ljkj.qxn.wisdomsitepro.ui.common;

import android.graphics.Color;
import android.view.View;

/**
 * 类描述：巡检详情-待审核
 * 创建人：lxx
 * 创建时间：2018/2/6 10:46
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class InspectionDetailsOfTobeAuditedActivity2 extends BaseInspectionDetailsActivity2 {

    @Override
    protected void initUI() {
        super.initUI();
        nextText.setText("审核中");
        nextText.setBackgroundColor(Color.parseColor("#939699"));
        nextText.setVisibility(View.GONE);
        examineLayout.setVisibility(View.GONE);
        rectifyLayout.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();

    }


}
