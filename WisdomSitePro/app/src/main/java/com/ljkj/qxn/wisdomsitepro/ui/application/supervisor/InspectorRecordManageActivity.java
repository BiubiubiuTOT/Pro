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
import com.ljkj.qxn.wisdomsitepro.contract.supervisor.InspectorRecordContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.InspectorRecorderManageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SupervisorModel;
import com.ljkj.qxn.wisdomsitepro.presenter.supervisor.InspectorRecordPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.InspectorRecordManageAdapter;
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
 * 类描述：巡视记录管理
 * 创建人：lxx
 * 创建时间：2018/8/30
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class InspectorRecordManageActivity extends BaseActivity implements InspectorRecordContract.View {

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

    private InspectorRecordManageAdapter inspectorRecordManageAdapter;
    private int page = 1;
    private int loadType;
    private String keywords;
    private String name, beginTime;
    private InspectorRecordPresenter inspectorRecordPresenter;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspector_record_manage);
    }

    @Override
    protected void initUI() {
        titleText.setText("巡视记录管理");
        refreshLayout.setEnableLoadMore(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        recyclerView.setAdapter(inspectorRecordManageAdapter = new InspectorRecordManageAdapter(null));

        initListener();

        inspectorRecordPresenter = new InspectorRecordPresenter(this, SupervisorModel.newInstance());
        addPresenter(inspectorRecordPresenter);
        refreshLayout.autoRefresh();
    }

    private void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                inspectorRecordPresenter.getInspectorRecordList(UserManager.getInstance().getProjectId(), name, beginTime, 1, 10);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                inspectorRecordPresenter.getInspectorRecordList(UserManager.getInstance().getProjectId(), name, beginTime, page, 10);
            }
        });

        inspectorRecordManageAdapter.setOnSwipeListener(new SwipeListener() {
            @Override
            public void onDelete(int pos) {
                InspectorRecorderManageInfo item = inspectorRecordManageAdapter.getItem(pos);
                InspectorRecordManageActivity.this.pos = pos;
                inspectorRecordPresenter.deleteRecord(item.getId(), item.getCreateUserName(), UserManager.getInstance().getUserId());
            }

            @Override
            public void onClick(int pos) {
                Intent intent = new Intent(InspectorRecordManageActivity.this, InspectorRecordDetailActivity.class);
                intent.putExtra("id", inspectorRecordManageAdapter.getItem(pos).getId());
                startActivity(intent);
            }
        });

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    name = searchText.getText().toString().trim();
                    refreshLayout.autoRefresh();
                    KeyboardUtil.closeKeyboard(InspectorRecordManageActivity.this);
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
                startActivityForResult(new Intent(InspectorRecordManageActivity.this,
                        AddInspectorRecordActivity.class), ADD_SUCCESS);
                break;
            case R.id.rb_start:
                showTimeDialog();
                break;
            default:
                break;
        }
    }


    @Override
    public void showInspectorRecordList(PageInfo<InspectorRecorderManageInfo> datas) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            inspectorRecordManageAdapter.setNewData(datas.getList());
            page = 2;
        } else {
            inspectorRecordManageAdapter.addData(datas.getList());
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > inspectorRecordManageAdapter.getItemCount());
    }

    @Override
    public void dealDeleteRecordResult() {
        inspectorRecordManageAdapter.remove(pos);
        showError("删除成功");
    }

    @Override
    public void showInspectorRecordDetail(InspectorRecorderManageInfo info) {

    }

    @Override
    public void dealAddRecordResult() {

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ADD_SUCCESS) {
                refreshLayout.autoRefresh();
            }
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
}
