package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.view.View;

import com.ljkj.qxn.wisdomsitepro.ui.common.BaseInspectionDetailsActivity2;

/**
 * 类描述：巡检详情-合格
 * 创建人：mhl
 * 创建时间：2018/4/8 17:01
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeInspectionDetailsOfQualifiedV2 extends SafeInspectionDetailV2Activity {

    @Override
    protected void initUI() {
        super.initUI();
        nextText.setVisibility(View.GONE);
        examineLayout.setVisibility(View.GONE);
        rectifyLayout.setVisibility(View.GONE);
    }

}
