package com.ljkj.qxn.wisdomsitepro.ui.contacts;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.SpannableStringUtils;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectInfoContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectDoneProgress;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;
import com.ljkj.qxn.wisdomsitepro.presenter.project.ProjectInfoPresenter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;


/**
 * 类描述：项目组织管理
 * 创建人：njt
 * 创建时间：2018/6/23 11:23
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectOrganizationManagementActivity extends BaseActivity implements ProjectInfoContract.View {
    private static final String PHONE_NUMBER = "0851-86869595";
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.item_QR_code)
    RelativeLayout itemQRCode;
    @BindView(R.id.item_project_overview)
    ItemView itemProjectOverview;
    @BindView(R.id.item_add_departments_and_members)
    ItemView itemAddDepartmentsAndMembers;
    @BindView(R.id.item_project_cloud_space)
    ItemView itemProjectCloudSpace;
    @BindView(R.id.tv_contact_number)
    TextView tvContactNumber;
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_project_id)
    TextView tvProjectId;

    private ProjectInfoPresenter infoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_organization_management);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("项目组织管理");
        String projectName = UserManager.getInstance().getProjectName();
        tvProjectName.setText(projectName);
        tvOne.setText(projectName.substring(0, 1));

        String contactNumber = tvContactNumber.getText().toString().trim();
        tvContactNumber.setText(changeTextColor(contactNumber, contactNumber.length() - 13, contactNumber.length()));
        tvProjectId.setText("项目编号：" + UserManager.getInstance().getProjectCode());
    }

    @Override
    protected void initData() {
        infoPresenter = new ProjectInfoPresenter(this, ProjectModel.newInstance());
        addPresenter(infoPresenter);
//        infoPresenter.getProjectInfo(UserManager.getInstance().getProjectId());
    }

    @OnClick({R.id.tv_back, R.id.item_QR_code, R.id.item_project_overview,
            R.id.item_add_departments_and_members, R.id.item_project_cloud_space
            , R.id.tv_contact_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_QR_code:
//                startActivity(new Intent(ProjectOrganizationManagementActivity.this, ProjectQRActivity.class));
                break;
            case R.id.item_project_overview:
                startActivity(new Intent(ProjectOrganizationManagementActivity.this, ProjectOverviewActivity.class));
                break;
            case R.id.item_add_departments_and_members:
                startActivity(new Intent(ProjectOrganizationManagementActivity.this, ContractDepartmentActivity.class));
                break;
            case R.id.item_project_cloud_space:
                break;
            case R.id.tv_contact_number:
                callPhone();
                break;
        }
    }

    public SpannableString changeTextColor(String text, int start, int end) {
        SpannableString spStr = new SpannableString(text);
        spStr.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getBaseContext(), R.color.color_orange)),
                start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spStr;
    }


    private void callPhone() {
        AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.CALL_PHONE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + PHONE_NUMBER));
                        startActivity(intent);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showError("用户已禁止访问电话权限");
                    }
                })
                .start();
    }

    @Override
    public void showProjectInfo(ProjectDoneProgress info) {
        if (info != null) {
            String title = info.getCompleteness();
            itemProjectOverview.getContentTextView().setText(SpannableStringUtils.getBuilder("完整度")
                    .append(title + "%")
                    .setForegroundColor(ContextCompat.getColor(this, R.color.color_orange))
                    .create());
            tvProjectId.setText("项目编号：" + info.getPro_no());
        }
    }
}
