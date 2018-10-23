package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.H5Helper;
import com.ljkj.qxn.wisdomsitepro.data.AuthorityId;
import com.ljkj.qxn.wisdomsitepro.manager.AuthorityManager;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：劳务管理
 * 创建人：lxx
 * 创建时间：2018/6/21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LabourManagerActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.item_labor_statistics)
    TextView laborStatisticsText;

    @BindView(R.id.item_labor_person)
    TextView laborPersonText;

    @BindView(R.id.item_attendance_manager)
    TextView attendanceManagerText;

    @BindView(R.id.item_app_payroll)
    TextView appPayrollText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_manager);
    }

    @Override
    protected void initUI() {
        titleText.setText("劳务管理");
    }

    @Override
    protected void handleAuthority() {
        AuthorityManager manager = AuthorityManager.getInstance();
        manager.handleViewByAuthority(laborStatisticsText, AuthorityId.LaborManage.LABOR_STATISTICS);
        manager.handleViewByAuthority(laborPersonText, AuthorityId.LaborManage.LABOR_ARCHIVE);
        manager.handleViewByAuthority(attendanceManagerText, AuthorityId.LaborManage.LABOR_ATTENDANCE);
        manager.handleViewByAuthority(appPayrollText, AuthorityId.LaborManage.LABOR_WAGES);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_back, R.id.item_labor_statistics, R.id.item_labor_person, R.id.item_attendance_manager, R.id.item_app_payroll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_labor_statistics: //人员统计
                H5Helper.toLaborPersonStatistics(this, "人员统计", UserManager.getInstance().getProjectId());
                break;
            case R.id.item_labor_person: //人员档案
                startActivity(new Intent(this, PersonArchiveActivity.class));
                break;
            case R.id.item_attendance_manager: //考勤管理
                startActivity(new Intent(this, AttendManagerActivity.class));
                break;
            case R.id.item_app_payroll: //工资发放
                startActivity(new Intent(this, PayrollListActivity.class));
                break;
            default:
                break;
        }
    }

}
