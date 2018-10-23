package com.ljkj.qxn.wisdomsitepro.ui.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.SpannableStringUtils;
import com.ljkj.qxn.wisdomsitepro.contract.contacts.MemberAddContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DepartmentListInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DutyListInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.QueryMemberInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ContactsModel;
import com.ljkj.qxn.wisdomsitepro.presenter.contacts.MemberAddPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.contacts.adapter.DepartmentMemberAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：部门组织架构
 * 创建人：rjf
 * 创建时间：2018/6/26 13:46.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class DepartmentStructureActivity extends BaseActivity implements OnRefreshLoadMoreListener, MemberAddContract.View {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.et_search_department)
    EditText etSearchDepartment;
    @BindView(R.id.tv_structure_title)
    TextView tvStructureTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    FrameLayout llNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    //当前刷新布局加载类型
    private int mLoadType;

    //记录分页列表第几页
    private int mPageNumber;

    private MemberAddPresenter mAddPresenter;

    private DepartmentMemberAdapter mMemberAdapter;

    private DepartmentListInfo departmentListInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_structure);
    }

    @Override
    protected void initUI() {
        //设置标题
        departmentListInfo = getIntent().getParcelableExtra("data");
        String projectName = UserManager.getInstance().getProjectName();
        tvTitle.setText(projectName);

        tvStructureTitle.setText(SpannableStringUtils.getBuilder(projectName)
                .setForegroundColor(ContextCompat.getColor(this, R.color.color_blue))
                .append(" > " + departmentListInfo.getName())
                .create());

        refreshLayout.setEnableLoadMore(false);
//        refreshLayout.setOnRefreshLoadMoreListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMemberAdapter = new DepartmentMemberAdapter(R.layout.item_department_member, null);
        recyclerView.setAdapter(mMemberAdapter);

        mMemberAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(DepartmentStructureActivity.this, MemberInfoActivity.class);
                QueryMemberInfo queryMemberInfo  = mMemberAdapter.getItem(position);
                intent.putExtra("data", queryMemberInfo);
                intent.putExtra("department", departmentListInfo.getName());
                startActivity(intent);
            }
        });
    }


    @Override
    protected void initData() {
        if (departmentListInfo != null) {
            mAddPresenter = new MemberAddPresenter(this, ContactsModel.newInstance());
            addPresenter(mAddPresenter);
            mAddPresenter.searachMember(departmentListInfo.getId(), departmentListInfo.getId());
        }
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        initData();
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        llNoData.setVisibility(recyclerView.getAdapter().getItemCount() > 0 ? View.GONE : View.VISIBLE);
        if (mLoadType == Consts.XR_LOAD_TYPE.REFRESH) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void dealAddMemberResult() {

    }

    @Override
    public void showDutyList(PageInfo<DutyListInfo> data) {

    }

    @Override
    public void dealDeleteResult() {

    }

    @Override
    public void showSearchMembers(PageInfo<QueryMemberInfo> data) {
        List<QueryMemberInfo> list = data.getList();
        if (list != null && list.size() > 0) {
            mMemberAdapter.setNewData(list);
        }
    }

}
