package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.content.Intent;

import com.ljkj.qxn.wisdomsitepro.ui.quality.QuaGenerHiddenDangerOfImmeRectiActivity;


/**
 * 类描述：一般隐患（立即整改）
 * 创建人：mhl
 * 创建时间：2018/2/6 10:48
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeGenerHiddenDangerOfImmeRectiActivity extends QuaGenerHiddenDangerOfImmeRectiActivity {

    public static void startActivity(Context context, int type, String id) {
        Intent intent = new Intent(context, SafeGenerHiddenDangerOfImmeRectiActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void next() {
        SafeImmediateRectificationActivity.startActivity(this, detail.proInspect.getId(), detail.proInspect.getProjectName(), detail.getProAddress(), detail.proInspect.getCheckNo());
    }

    @Override
    protected void initUI() {
        super.initUI();
        checkCodeItem.setTitle("安全检查编号");
    }
}
