package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.ui.quality.QuaCheckDetailsOfRectifyActivity;

import butterknife.OnClick;

/**
 * 类描述：检查详情-重新整改
 * 创建人：mhl
 * 创建时间：2018/2/6 14:15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeCheckDetailsOfRectifyActivity extends QuaCheckDetailsOfRectifyActivity {

    public static void startActivity(Context context, String id) {
        Intent intent = new Intent(context, SafeCheckDetailsOfRectifyActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void initUI() {
        super.initUI();
        tvTitle.setText("整改详情");
        checkCodeItem.setTitle("安全检查编号");
    }


    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick({R.id.tv_next, R.id.tv_back})
    @Override
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_next:
                if (detail != null) {
                    SafeImmediateRectificationActivity.startActivity(this, detail.proInspect.getId(), detail.proInspect.getProjectName(), detail.getProAddress(), detail.proInspect.getCheckNo());
                }
                break;
            default:
                break;
        }
    }



}
