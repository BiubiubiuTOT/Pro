package com.ljkj.qxn.wisdomsitepro.ui.common;

import android.view.View;

/**
 * 类描述：巡检详情-无操作
 * 创建人：lxx
 * 创建时间：2018/4/8 10:28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class InspectionDetailsActivity2 extends BaseInspectionDetailsActivity2 {

    @Override
    protected void initUI() {
        super.initUI();
        nextText.setVisibility(View.GONE);
        examineLayout.setVisibility(View.GONE);
        rectifyLayout.setVisibility(View.GONE);
    }

}
