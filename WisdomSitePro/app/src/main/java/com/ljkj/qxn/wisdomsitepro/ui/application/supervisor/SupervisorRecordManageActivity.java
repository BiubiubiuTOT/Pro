package com.ljkj.qxn.wisdomsitepro.ui.application.supervisor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.contract.supervisor.SupervisorRecordContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SupervisorRecordManageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SupervisorModel;
import com.ljkj.qxn.wisdomsitepro.presenter.supervisor.SupervisorRecordPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.SupervisorRecordManageAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.ClearEditTextView;
import cdsp.android.util.DateUtils;
import cdsp.android.util.KeyboardUtil;

/**
 * 类描述：监理记录管理
 * 创建人：lxx
 * 创建时间：2018/9/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SupervisorRecordManageActivity extends BaseActivity implements SupervisorRecordContract.View {

    private static final int ADD_SUCCESS = 2;
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

    @BindView(R.id.rb_start)
    RadioButton reStart;

    private SupervisorRecordManageAdapter supervisorRecordManageAdapter;
    private SupervisorRecordPresenter supervisorRecordPresenter;
    private int page = 1;
    private int loadType;
    private String keywords;
    private String name;
    private String beginTime;
    private int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_record_manage);
    }

    @Override
    protected void initUI() {
        titleText.setText("监理记录管理");
        refreshLayout.setEnableLoadMore(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        supervisorRecordPresenter = new SupervisorRecordPresenter(this, SupervisorModel.newInstance());
        addPresenter(supervisorRecordPresenter);

        recyclerView.setAdapter(supervisorRecordManageAdapter = new SupervisorRecordManageAdapter(null));
        refreshLayout.autoRefresh();

        initListener();
    }

    private void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                supervisorRecordPresenter.getSupervisorRecordList(UserManager.getInstance().getProjectId(), name, beginTime, 1, 10);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                supervisorRecordPresenter.getSupervisorRecordList(UserManager.getInstance().getProjectId(), name, beginTime, page, 10);
            }
        });

        supervisorRecordManageAdapter.setOnSwipeListener(new SwipeListener() {
            @Override
            public void onDelete(int pos) {
                SupervisorRecordManageInfo item = supervisorRecordManageAdapter.getItem(pos);
                SupervisorRecordManageActivity.this.pos = pos;
                supervisorRecordPresenter.deleteRecord(item.getId(),UserManager.getInstance().getUserName(), UserManager.getInstance().getUserId());
            }

            @Override
            public void onClick(int pos) {
                Intent intent = new Intent(SupervisorRecordManageActivity.this, SupervisorRecordDetailActivity.class);
                intent.putExtra("id", supervisorRecordManageAdapter.getItem(pos).getId());
                startActivity(intent);
            }
        });


        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    name = searchText.getText().toString().trim();
                    refreshLayout.autoRefresh();
                    KeyboardUtil.closeKeyboard(SupervisorRecordManageActivity.this);
                }
                return false;
            }
        });
    }

    @OnClick({R.id.tv_back, R.id.fab, R.id.rb_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.fab:
                startActivityForResult(new Intent(this, AddSupervisorRecordActivity.class), ADD_SUCCESS);
                break;
            case R.id.rb_start:
                showTimeDialog();
                break;
            default:
                break;
        }
    }

    private void showTimeDialog() {
        if (isFastDoubleClick()) {
            return;
        }
        PickerDialogHelper.showTimePicker(this, Calendar.getInstance(), true, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                beginTime = DateUtils.date2str(date, DateUtils.PATTERN_DATE);
                reStart.setText(beginTime);
                refreshLayout.autoRefresh();
                reStart.setChecked(false);
            }
        });
    }

    @Override
    public void showSupervisorRecordList(PageInfo<SupervisorRecordManageInfo> datas) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            supervisorRecordManageAdapter.setNewData(datas.getList());
            page = 2;
        } else {
            supervisorRecordManageAdapter.addData(datas.getList());
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > supervisorRecordManageAdapter.getItemCount());
    }

    @Override
    public void dealDeleteRecordResult() {
        supervisorRecordManageAdapter.remove(pos);
        showError("删除成功");
    }

    @Override
    public void showSupervisorRecordDetail(SupervisorRecordManageInfo info) {

    }

    @Override
    public void dealAddRecordResult() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_SUCCESS) {
                refreshLayout.autoRefresh();
            }
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
}
