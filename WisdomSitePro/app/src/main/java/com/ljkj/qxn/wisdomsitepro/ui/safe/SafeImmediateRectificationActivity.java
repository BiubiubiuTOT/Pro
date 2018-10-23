package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.content.Intent;

import com.ljkj.qxn.wisdomsitepro.ui.quality.QuaImmediateRectificationActivity;

/**
 * 立即整改
 * 创建人：lxx
 * 创建时间：2018/3/17 15:23
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeImmediateRectificationActivity extends QuaImmediateRectificationActivity {
    public static void startActivity(Context context, String id, String projectName, String projectAddress, String checkNo) {
        Intent intent = new Intent(context, SafeImmediateRectificationActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("projectName", projectName);
        intent.putExtra("projectAddress", projectAddress);
        intent.putExtra("checkNo", checkNo);
        context.startActivity(intent);
    }

    @Override
    protected void jump() {
        Intent intent = new Intent(this, SafeCheckActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
