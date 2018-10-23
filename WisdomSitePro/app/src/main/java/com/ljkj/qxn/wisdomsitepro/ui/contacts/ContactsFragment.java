package com.ljkj.qxn.wisdomsitepro.ui.contacts;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.contacts.DepartmentListContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.DeptBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.LaborCompanyBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.RoleBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DepartmentListInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.ProjectDeptInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.RefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ContactsModel;
import com.ljkj.qxn.wisdomsitepro.presenter.contacts.DepartmentListPresenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：联系人
 * 创建人：lxx
 * 创建时间：2018/6/19
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ContactsFragment extends BaseFragment implements DepartmentListContract.View {
    public static final String TAG = ContactsFragment.class.getName();
    public static String companyId = "";

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.tv_back)
    TextView backText;
    @BindView(R.id.tv_project_info)
    TextView tvProjectInfo;
    @BindView(R.id.tv_my_department_info)
    TextView tvMyDepartmentInfo;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.ll_my_department)
    LinearLayout llMyDepartment;
    @BindView(R.id.tv_project_manager_name)
    TextView tvProjectManagerName;
    @BindView(R.id.ll_manage)
    LinearLayout ll_manage;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    //获取信息
    private DepartmentListPresenter mListPresenter;

    private UserManager.OnProjectChangeListener onProjectChangeListener = new UserManager.OnProjectChangeListener() {
        @Override
        public void onProjectChange(String projectId, String name) {
            getData();
        }
    };
    private ProjectDeptInfo memberNumer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (memberNumer == null) {
            getData();
        }
    }

    @Override
    protected void initUI() {
        titleText.setText("联系人");
        tvProjectInfo.setText(UserManager.getInstance().getProjectName());
        backText.setVisibility(View.GONE);
        refreshLayout.setEnableLoadMore(false);

        ll_manage.setVisibility(UserManager.getInstance().isProjectManager() ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        mListPresenter = new DepartmentListPresenter(this, ContactsModel.newInstance());
        addPresenter(mListPresenter);
        UserManager.getInstance().addOnProjectChangeListener(onProjectChangeListener);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getData();
            }
        });
        refreshLayout.autoRefresh();
    }

    private void getData() {
        String projectId = UserManager.getInstance().getProjectId();
        mListPresenter.getProjectInfo(projectId, UserManager.getInstance().getUserId());
    }

    @OnClick({R.id.tv_create_project, R.id.tv_address_book, R.id.tv_friends, R.id.tv_group,
            R.id.ll_manage, R.id.tv_my_department, R.id.ll_department})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_create_project:
                startActivity(new Intent(getActivity(), CreateProjectActivity.class));
                break;
            case R.id.tv_address_book:
                checkPermission();
                break;
            case R.id.tv_friends:
                break;
            case R.id.tv_group:
                break;
            case R.id.ll_manage:
                startActivity(new Intent(mContext, ProjectOrganizationManagementActivity.class));
                break;
            case R.id.tv_my_department:
                if (memberNumer != null) {
                    Intent intent = new Intent(mContext, ContractMemberActivity.class);
                    intent.putExtra("DepartmentInfo", new DeptBean(memberNumer.getDeptId(),
                            memberNumer.getDeptName(), memberNumer.getDeptUserNum()));
                    startActivity(intent);
                }
                break;
            case R.id.ll_department:
                startActivity(new Intent(mContext, ContractDepartmentActivity.class));
                break;
            default:
                break;
        }
    }

    private void checkPermission() {
        AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.READ_CONTACTS)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Intent intent = new Intent(getActivity(), ContactListActivity.class);
                        startActivity(intent);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showError("用户已禁止访问通讯录权限");
                    }
                })
                .start();
    }

    @Override
    public void showProjectInfo(ProjectDeptInfo memberNumber) {
        this.memberNumer = memberNumber;
        if (!refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
        if (memberNumber != null) {
            companyId = memberNumber.getProjDeptId();
            String projectName = UserManager.getInstance().getProjectName();
            tvOne.setText(projectName.substring(0, 1));
            String projectInfo = projectName + "（" + memberNumber.getProjUserNum() + "）";
            tvProjectInfo.setText(projectInfo);

            String deptName = memberNumber.getDeptName();
            String deptInfo = deptName + "（" + memberNumber.getDeptUserNum() + "）";
            tvMyDepartmentInfo.setText(deptInfo);

            llMyDepartment.setVisibility(!TextUtils.isEmpty(deptName) ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void showProjectDepartmentList(List<DepartmentListInfo> listInfo) {

    }

    @Override
    public void showBanZuDepartmentList(List<DepartmentListInfo> listInfo) {

    }

    @Override
    public void showUserDepartmentList(List<DepartmentListInfo> listInfo) {
    }

    @Override
    public void showLaborCompanyList(List<LaborCompanyBean> list) {

    }

    @Override
    public void showRoleList(RoleBean list) {

    }

    @Override
    public void dealAddDepartmentResult() {

    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        refreshLayout.finishRefresh();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(RefreshEvent event) {
        getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UserManager.getInstance().removeOnProjectChangeListener(onProjectChangeListener);
        EventBus.getDefault().unregister(this);
    }
}
