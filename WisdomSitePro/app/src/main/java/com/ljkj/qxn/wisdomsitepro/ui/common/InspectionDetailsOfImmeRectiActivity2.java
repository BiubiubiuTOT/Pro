package com.ljkj.qxn.wisdomsitepro.ui.common;

import android.view.View;

/**
 * 类描述：巡检详情-立即整改
 * 创建人：lxx
 * 创建时间：2018/4/9 15:27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class InspectionDetailsOfImmeRectiActivity2 extends BaseInspectionDetailsActivity2 {

    @Override
    protected void initUI() {
        super.initUI();
        nextText.setText("立即整改");
        examineLayout.setVisibility(View.GONE);
        rectifyLayout.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void doNext() {
        super.doNext();
        if (inspectionInfo != null) {
            BaseInspectionDetailsActivity2.startActivity(this, id, "立即整改", InspectionRectificationActivity2.class);
            finish();
        }
    }

}
