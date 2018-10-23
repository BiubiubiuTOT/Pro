package com.ljkj.qxn.wisdomsitepro.ui.quality;

import com.ljkj.qxn.wisdomsitepro.ui.safe.SafeHiddenAccountActivity;

public class QualityHiddenAccountActivity extends SafeHiddenAccountActivity {

    @Override
    protected void initUI() {
        titleText.setText("质量隐患台");
    }

    @Override
    protected int getSecurityOrQuality() {
        return 2;
    }
}
