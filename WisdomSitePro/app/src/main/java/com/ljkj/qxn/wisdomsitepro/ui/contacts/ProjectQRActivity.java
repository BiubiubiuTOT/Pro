package com.ljkj.qxn.wisdomsitepro.ui.contacts;

import android.os.Bundle;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：项目二维码
 * 创建人：njt
 * 创建时间：2018/6/23 11:23
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectQRActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;
    @BindView(R.id.tv_one)
    TextView tvOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_qr);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("项目二维码");
    }

    @Override
    protected void initData() {
        String projectName = UserManager.getInstance().getProjectName();
        tvProjectName.setText(projectName);
        tvOne.setText(projectName.substring(0, 1));
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }
}
