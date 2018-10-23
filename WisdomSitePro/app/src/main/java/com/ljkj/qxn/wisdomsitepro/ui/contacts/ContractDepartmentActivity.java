package com.ljkj.qxn.wisdomsitepro.ui.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.SpannableStringUtils;
import com.ljkj.qxn.wisdomsitepro.contract.contacts.DepartmentListContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.DeptBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.LaborCompanyBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.RoleBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DepartmentListInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DeptEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.ProjectDeptInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.RefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ContactsModel;
import com.ljkj.qxn.wisdomsitepro.presenter.contacts.DepartmentListPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.contacts.adapter.DepartmentAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：添加部门和成员
 * 创建人：rjf
 * 创建时间：2018/6/25 09:44.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class ContractDepartmentActivity extends BaseActivity implements DepartmentListContract.View, OnRefreshListener, OnRefreshLoadMoreListener {

    private static final int DEPARTMENT_DETAIL_REQUEST_CODE = 0x2001;
    public static final int MEMBER_ADD_REQUEST_CODE = 0x2002;


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_contract_info)
    TextView tvContractInfo;
    @BindView(R.id.tv_append_member)
    TextView tvAppendMember;
    @BindView(R.id.tv_create_department)
    TextView tvCreateDepartment;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    FrameLayout llNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    //获取部门列表
    private DepartmentListPresenter mListPresenter;

    private DepartmentAdapter mProjectDepartmentAdapter;

    private List<DepartmentListInfo> mProjectDepartmentList;
    private List<DepartmentListInfo> mBanZuDepartmentList;

    List<DeptEntity> data;

    //项目成员数量
    private int mProMemberNum;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_department);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("部门和成员");

        boolean isManager = UserManager.getInstance().isProjectManager();
        if (isManager) {
            tvRight.setText("新增部门");
            tvRight.setVisibility(View.VISIBLE);
        } else {
            tvRight.setVisibility(View.GONE);
        }

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        recyclerView.setNestedScrollingEnabled(false);
        refreshLayout.autoRefresh();
    }

    private void setAdapter() {
        buildData();

        //设置列表信息
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProjectDepartmentAdapter = new DepartmentAdapter(R.layout.item_contract_deparemnt, R.layout.adapter_dept_header, data);
        recyclerView.setAdapter(mProjectDepartmentAdapter);
        mProjectDepartmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DeptEntity item = mProjectDepartmentAdapter.getItem(position);
                if (!item.isHeader)
                    gotoDetail(item.t);
            }
        });
    }

    private void buildData() {
        data = new ArrayList<>();
        for (DepartmentListInfo departmentListInfo : mProjectDepartmentList) {
            String name = departmentListInfo.getName();
            DeptEntity entity = new DeptEntity(true, name);
            data.add(entity);

            List<DeptBean> deptList = departmentListInfo.getDeptList();
            for (DeptBean deptListBean : deptList) {
                DeptEntity deptEntity = new DeptEntity(deptListBean);
                data.add(deptEntity);
            }
        }

        for (DepartmentListInfo departmentListInfo : mBanZuDepartmentList) {
            String name = departmentListInfo.getName();
            DeptEntity entity = new DeptEntity(true, name);
            data.add(entity);

            List<DeptBean> deptList = departmentListInfo.getDeptList();
            if (deptList == null) return;
            for (DeptBean deptListBean : deptList) {
                DeptEntity deptEntity = new DeptEntity(deptListBean);
                data.add(deptEntity);
            }
        }
    }

    private void gotoDetail(DeptBean info) {
        Intent intent = new Intent(ContractDepartmentActivity.this, ContractMemberActivity.class);
        intent.putExtra("DepartmentInfo", info);
        startActivityForResult(intent, DEPARTMENT_DETAIL_REQUEST_CODE);
    }

    private void getData() {
        mListPresenter.getProjectDepartmentList(UserManager.getInstance().getProjectId(), 1, 999);
        mListPresenter.getBanZuDepartmentList(UserManager.getInstance().getProjectId(), 1, 999);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        mListPresenter = new DepartmentListPresenter(this, ContactsModel.newInstance());
        addPresenter(mListPresenter);
        mListPresenter.getProjectInfo(UserManager.getInstance().getProjectId(), UserManager.getInstance().getUserId());
    }

    @OnClick({R.id.tv_back, R.id.tv_append_member, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_append_member:
                Intent intent = new Intent(this, MemberAppendActivity.class);
                startActivityForResult(intent, MEMBER_ADD_REQUEST_CODE);
                break;
            case R.id.tv_right:
                startActivity(new Intent(ContractDepartmentActivity.this, DeptAppendActivity.class));
                break;
        }
    }

    @Override
    public void showProjectInfo(ProjectDeptInfo memberNumber) {
        mProMemberNum = memberNumber.getProjUserNum();
        setMsg();
    }

    @Override
    public void showProjectDepartmentList(List<DepartmentListInfo> listInfo) {
        this.mProjectDepartmentList = listInfo;

        if (mBanZuDepartmentList != null) {
            setAdapter();
        }
    }

    @Override
    public void showBanZuDepartmentList(List<DepartmentListInfo> listInfo) {
        this.mBanZuDepartmentList = listInfo;

        if (mProjectDepartmentList != null) {
            setAdapter();
        }
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
        refreshLayout.autoRefresh();
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
//        llNoData.setVisibility(recyclerView.getAdapter().getItemCount() > 0 ? View.GONE : View.VISIBLE);
        refreshLayout.finishRefresh();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        getData();
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DEPARTMENT_DETAIL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                refreshLayout.autoRefresh();
            }
        } else if (requestCode == MEMBER_ADD_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                refreshLayout.autoRefresh();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void countChange(RefreshEvent event) {
        if (event.message.equals("count")) {
            int count = event.getCount();
            mProMemberNum += count;
            setMsg();
        }
        refreshLayout.autoRefresh();
    }

    private void setMsg() {
        String projectName = "【" + UserManager.getInstance().getProjectName() + "】";
        String projectInfo = "（" + mProMemberNum + "）";
        tvContractInfo.setText(SpannableStringUtils.getBuilder(projectName)
                .setForegroundColor(ContextCompat.getColor(this, R.color.color_blue))
                .append(projectInfo)
                .create());
    }

}
