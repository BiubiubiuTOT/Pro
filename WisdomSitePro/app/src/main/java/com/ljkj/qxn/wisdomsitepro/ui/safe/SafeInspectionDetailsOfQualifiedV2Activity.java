package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.view.View;

import com.ljkj.qxn.wisdomsitepro.ui.common.BaseInspectionDetailsActivity2;
import com.ljkj.qxn.wisdomsitepro.ui.common.InspectionRectificationActivity2;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QualityInspectionRectificationV2Activity;

/**
 * 类描述：巡检详情-重新整改
 * 创建人：lxx
 * 创建时间：2018/4/9 15:27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeInspectionDetailsOfQualifiedV2Activity extends SafeInspectionDetailV2Activity {


    @Override
    protected void initUI() {
        super.initUI();
        nextText.setText("重新整改");
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
        if (safePatrolDetailInfo != null) {
            BaseInspectionDetailsActivity2.startActivity(this, id, "重新整改", SafeInspectionRectificationV2Activity.class);
            finish();
        }
    }


}
