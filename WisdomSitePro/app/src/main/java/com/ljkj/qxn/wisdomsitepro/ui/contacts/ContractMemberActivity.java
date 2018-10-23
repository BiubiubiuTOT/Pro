package com.ljkj.qxn.wisdomsitepro.ui.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.DialogUtil;
import com.ljkj.qxn.wisdomsitepro.Utils.SpannableStringUtils;
import com.ljkj.qxn.wisdomsitepro.contract.contacts.DepartmentDetailContract;
import com.ljkj.qxn.wisdomsitepro.contract.contacts.MemberAddContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.DeptBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DepartmentListInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DutyListInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.QueryMemberInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.RefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.ContactsModel;
import com.ljkj.qxn.wisdomsitepro.presenter.contacts.DepartmentDetailPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.contacts.MemberAddPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.contacts.adapter.DepartmentMemberAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.http.JsonCallback;
import cdsp.android.http.ResponseData;
import cdsp.android.ui.base.BaseActivity;

import static com.ljkj.qxn.wisdomsitepro.ui.contacts.ContractDepartmentActivity.MEMBER_ADD_REQUEST_CODE;

/**
 * 类描述：添加部门和成员
 * 创建人：rjf
 * 创建时间：2018/6/25 09:44.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class ContractMemberActivity extends BaseActivity implements DepartmentDetailContract.View,
        MemberAddContract.View, OnRefreshListener {

    private static final int UPDATE_DEPT = 10;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_contract_info)
    TextView tvContractInfo;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_append_member)
    TextView tvAppendMember;
    @BindView(R.id.tv_update_department)
    TextView tvUpdateDepartment;
    @BindView(R.id.tv_delete_department)
    TextView tvDeleteDepartment;
    @BindView(R.id.ll_no_data)
    FrameLayout llNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    private DeptBean mDepartmentInfo;


    //获取详情信息
    private DepartmentDetailPresenter mDetailPresenter;
    private MemberAddPresenter mAddPresenter;
    private List<QueryMemberInfo> dataList;
    private DepartmentMemberAdapter mMemberAdapter;
    private int counts;
    private boolean isDestroy;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_member);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        //设置标题
        boolean isManager = UserManager.getInstance().isProjectManager();
        if (isManager) {
            tvTitle.setText("添加部门和成员");
            llBottom.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setText("部门和成员");
            llBottom.setVisibility(View.GONE);
        }

        llBottom.setVisibility(View.VISIBLE);

        //设置列表信息
        mDepartmentInfo = getIntent().getParcelableExtra("DepartmentInfo");
        counts = mDepartmentInfo.getNum();
        setMsg();

        dataList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMemberAdapter = new DepartmentMemberAdapter(R.layout.item_department_member, dataList);
        recyclerView.setAdapter(mMemberAdapter);

        mMemberAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ContractMemberActivity.this, MemberInfoActivity.class);
                QueryMemberInfo queryMemberInfo = dataList.get(position);
                intent.putExtra("data", queryMemberInfo);
                intent.putExtra("department", mDepartmentInfo.getName());
                startActivity(intent);
            }
        });
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.autoRefresh();
    }

    private void setMsg() {
        String projectName = "【" + UserManager.getInstance().getProjectName() + "】";
        String str1 = mDepartmentInfo.getName() + "（" + counts + "）";
        tvContractInfo.setText(SpannableStringUtils.getBuilder(projectName)
                .setForegroundColor(ContextCompat.getColor(this, R.color.color_blue))
                .append(str1)
                .create());
    }

    @Override
    protected void initData() {
        mDetailPresenter = new DepartmentDetailPresenter(this, ContactsModel.newInstance());
        addPresenter(mDetailPresenter);
        mAddPresenter = new MemberAddPresenter(this, ContactsModel.newInstance());
        addPresenter(mAddPresenter);
    }

    @OnClick({R.id.tv_back, R.id.tv_append_member, R.id.tv_update_department, R.id.tv_delete_department})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_append_member:
                Intent intent = new Intent(this, MemberAppendActivity.class);
                intent.putExtra("deptId", mDepartmentInfo.getId());
                startActivityForResult(intent, MEMBER_ADD_REQUEST_CODE);
                break;
            case R.id.tv_update_department:
                Intent intent1 = new Intent(ContractMemberActivity.this, DeptAppendActivity.class);
                intent1.putExtra("id", mDepartmentInfo.getId());
                intent1.putExtra("name", mDepartmentInfo.getName());
                startActivityForResult(intent1, UPDATE_DEPT);
                break;
            case R.id.tv_delete_department:
                DialogUtil.showDeleteDepartmentDialog(this, new DialogUtil.OnDepartmentDeleteListener() {
                    @Override
                    public void onDepartmentDelete() {
                        if (mDepartmentInfo != null) {
                            //调用删除部门接口
                            mDetailPresenter.deleteDepartment(mDepartmentInfo.getId(), UserManager.getInstance().getProjectId());
                        }
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == MEMBER_ADD_REQUEST_CODE) {
                refreshLayout.autoRefresh();
                counts += 1;
                setMsg();
            } else if (requestCode == UPDATE_DEPT) {
                String name = data.getStringExtra("name");
                mDepartmentInfo.setName(name);
                setMsg();
            }
        }
    }

    @Override
    public void dealUpdateDepartmentResult() {
    }

    @Override
    public void dealDeleteDepartmentResult() {
        EventBus.getDefault().post(new RefreshEvent("count", -counts));
        showError("删除成功");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showDepartmentDetail(DepartmentListInfo data) {

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
        refreshLayout.finishRefresh();
        List<QueryMemberInfo> list = data.getList();
        if (list != null && list.size() > 0) {
            mMemberAdapter.setNewData(list);
            dataList = list;
        }
        llNoData.setVisibility(recyclerView.getAdapter().getItemCount() > 0 ? View.GONE : View.VISIBLE);

        count = dataList.size();
        queryDeptFileId();
    }

    private void queryDeptFileId() {
        if (dataList == null) {
            return;
        }

        for (final QueryMemberInfo queryMemberInfo : dataList) {
            CommonModel.newInstance().queryFile(queryMemberInfo.getId(), new JsonCallback<ResponseData<List<FileEntity>>>(new TypeToken<ResponseData<List<FileEntity>>>() {
            }) {
                @Override
                protected void onError(int errcode, String errmsg) {
                }

                @Override
                public void onSuccess(ResponseData<List<FileEntity>> data) {
                    if (isDestroy) {
                        return;
                    }
                    List<FileEntity> fileEntities = data.getResult();


                    if (fileEntities != null && !fileEntities.isEmpty()) {
                        queryMemberInfo.setPhotoId(fileEntities.get(0).fileId);
                    }

                    count--;
                    if (count == 0) {
                        mMemberAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        getData();
    }

    private void getData() {
        if (mDepartmentInfo != null) {
            mAddPresenter.searachMember(mDepartmentInfo.getId(), UserManager.getInstance().getProjectId());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroy = true;
    }
}
