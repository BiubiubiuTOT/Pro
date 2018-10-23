package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.content.Intent;

import com.ljkj.qxn.wisdomsitepro.ui.quality.QuaCheckDetailsOfToBeAuditedActivity;


/**
 * 类描述：检查详情-待审核
 * 创建人：mhl
 * 创建时间：2018/2/6 14:33
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeCheckDetailsOfToBeAuditedActivity extends QuaCheckDetailsOfToBeAuditedActivity {

    public static void startActivity(Context context, String id) {
        Intent intent = new Intent(context, SafeCheckDetailsOfToBeAuditedActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void initUI() {
        super.initUI();
        checkCodeItem.setTitle("安全检查编号");
    }
}
