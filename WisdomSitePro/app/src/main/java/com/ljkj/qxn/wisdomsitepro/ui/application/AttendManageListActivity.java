package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.application.AttendanceContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.AttendanceHistoryInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.AttendanceInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;
import com.ljkj.qxn.wisdomsitepro.presenter.application.AttendanceHistoryPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.AttendManageListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：考勤管理详情
 * 创建人：mhl
 * 创建时间：2018/2/2 10:18
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AttendManageListActivity extends BaseActivity implements AttendanceContract.View {
    private static final String KEY_TEAM = "key_team";

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.edit_search)
    EditText etSearch;

    @BindView(R.id.tv_right)
    TextView tvRight;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.ll_no_data)
    ViewGroup noDataLayout;

    AttendManageListAdapter adapter;

    AttendanceHistoryPresenter attnencePresenter;

    int page = 1;
    private int loadType;
    private String keywords;
    private String teamName;

    public static void startActivity(Context context, String team) {
        Intent intent = new Intent(context, AttendManageListActivity.class);
        intent.putExtra(KEY_TEAM, team);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend_manage_list);
    }

    @Override
    protected void initUI() {
        teamName = getIntent().getStringExtra(KEY_TEAM);
        tvTitle.setText("考勤管理详情");
        etSearch.setHint("姓名关键字搜索");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter = new AttendManageListAdapter(this));
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                attnencePresenter.listPageAttendanceInfo(
                        keywords, 1, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS, teamName);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {

                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                attnencePresenter.listPageAttendanceInfo(
                        keywords, page, UserManager.getInstance().getProjectId(), Consts.APPParams.ROWS, teamName);
            }
        });

        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    keywords = etSearch.getText().toString().trim();
                    if (TextUtils.isEmpty(keywords)) {
                        showError("请输入搜索关键字");
                        return false;
                    } else {
                        refreshLayout.autoRefresh();
                    }
                }
                return false;
            }
        });
    }

    private void doSearch() {
        keywords = etSearch.getText().toString().trim();
        if (TextUtils.isEmpty(keywords)) {
            showError("请输入搜索关键字");
        } else {
            refreshLayout.autoRefresh();
        }
    }


    @Override
    protected void initData() {

        attnencePresenter = new AttendanceHistoryPresenter(this, ApplicationModel.newInstance());
        addPresenter(attnencePresenter);
        refreshLayout.autoRefresh(400);
    }

    @OnClick({R.id.tv_back, R.id.tv_right, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_search:
                doSearch();
                break;
            case R.id.tv_right:
                break;
            default:
                break;
        }
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadmore();
        }
        noDataLayout.setVisibility(adapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showPageAttendanceInfo(PageInfo<AttendanceInfo> data) {

        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            adapter.loadData(data.getList());
            page = 2;
            if (data.getList() != null && data.getList().size() > 0) {
                refreshLayout.setEnableLoadMore(true);
            }
        } else {
            adapter.insertData(adapter.getItemCount(), data.getList());
            ++page;
        }
        if (data.getTotal() <= adapter.getItemCount()) {
            refreshLayout.resetNoMoreData();
        }
    }

    @Override
    public void showAttendanceHitoryInfo(PageInfo<AttendanceHistoryInfo> data) {

    }
}
