package com.ljkj.qxn.wisdomsitepro.ui.application.supervisor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.supervisor.StandardRecordContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SupervisorStandardInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SupervisorModel;
import com.ljkj.qxn.wisdomsitepro.presenter.supervisor.StandardRecordPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.SupervisorStandardAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.ClearEditTextView;
import cdsp.android.util.KeyboardUtil;

/**
 * 类描述：监理标准规范
 * 创建人：lxx
 * 创建时间：2018/8/30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SupervisorStandardActivity extends BaseActivity implements StandardRecordContract.View {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.edit_search)
    ClearEditTextView searchText;

    @BindView(R.id.refreshLayout)
    public SmartRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;

    @BindView(R.id.ll_no_data)
    ViewGroup llNoData;

    private int page = 1;
    private int loadType;
    private SupervisorStandardAdapter supervisorStandardAdapter;
    private StandardRecordPresenter standardRecordPresenter;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_standard);
    }

    @Override
    protected void initUI() {
        titleText.setText("监理标准规范");
        refreshLayout.setEnableLoadMore(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        standardRecordPresenter = new StandardRecordPresenter(this, SupervisorModel.newInstance());
        addPresenter(standardRecordPresenter);


        recyclerView.setAdapter(supervisorStandardAdapter = new SupervisorStandardAdapter(null));
        refreshLayout.autoRefresh();

        initListener();
    }

    private void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                standardRecordPresenter.getStandardRecordList(UserManager.getInstance().getProjectId(), name, 1, 10);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                standardRecordPresenter.getStandardRecordList(UserManager.getInstance().getProjectId(), name, page, 10);
            }
        });

        supervisorStandardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SupervisorStandardInfo item = supervisorStandardAdapter.getItem(position);
                Intent intent = new Intent(SupervisorStandardActivity.this, SupervisorStandardDetailActivity.class);
                intent.putExtra("data", item);
                startActivity(intent);
            }
        });

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    name = searchText.getText().toString().trim();
                    refreshLayout.autoRefresh();
                    KeyboardUtil.closeKeyboard(SupervisorStandardActivity.this);
                }
                return false;
            }
        });
    }

    @OnClick({R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    public void hideProgress() {
        super.hideProgress();
        llNoData.setVisibility(recyclerView.getAdapter().getItemCount() > 0 ? View.GONE : View.VISIBLE);
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();
        }
    }

    @Override
    public void showStandardRecordList(PageInfo<SupervisorStandardInfo> datas) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            supervisorStandardAdapter.setNewData(datas.getList());
            page = 2;
        } else {
            supervisorStandardAdapter.addData(datas.getList());
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > supervisorStandardAdapter.getItemCount());
    }

    @Override
    public void dealDeleteRecordResult() {

    }

    @Override
    public void showStandardRecordDetail(SupervisorStandardInfo info) {

    }

    @Override
    public void dealAddRecordResult() {

    }
}
