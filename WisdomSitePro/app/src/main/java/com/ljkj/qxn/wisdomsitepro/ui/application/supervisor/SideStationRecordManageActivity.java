package com.ljkj.qxn.wisdomsitepro.ui.application.supervisor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.contract.supervisor.SideStationRecordContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SideStationRecordUnit;
import com.ljkj.qxn.wisdomsitepro.data.entity.SiteStationRecorderManageDetailInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SiteStationRecorderManageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SupervisorModel;
import com.ljkj.qxn.wisdomsitepro.presenter.supervisor.SideStationRecordPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.SiteStationRecorderManageAdapter;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.ClearEditTextView;
import cdsp.android.util.DateUtils;
import cdsp.android.util.KeyboardUtil;

/**
 * 类描述：旁站记录管理
 * 创建人：lxx
 * 创建时间：2018/8/28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SideStationRecordManageActivity extends BaseActivity implements SideStationRecordContract.View {

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

    private SiteStationRecorderManageAdapter siteStationRecorderManageAdapter;
    private int page = 1;
    private int loadType;
    private SideStationRecordPresenter presenter;
    private String name, beginTime;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_station_recorder_manager);
    }

    @Override
    protected void initUI() {
        titleText.setText("旁站记录管理");
        refreshLayout.setEnableLoadMore(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        presenter = new SideStationRecordPresenter(this, SupervisorModel.newInstance());
        addPresenter(presenter);
        recyclerView.setAdapter(siteStationRecorderManageAdapter = new SiteStationRecorderManageAdapter(null));
        refreshLayout.autoRefresh();

        initListener();
    }

    private void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                presenter.getSideStationRecordList(UserManager.getInstance().getProjectId(), name, beginTime, 1, 10);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                presenter.getSideStationRecordList(UserManager.getInstance().getProjectId(), name, beginTime, page, 10);
            }
        });

        siteStationRecorderManageAdapter.setOnSwipeListener(new SwipeListener() {
            @Override
            public void onDelete(int pos) {
                SideStationRecordManageActivity.this.pos = pos;
                SiteStationRecorderManageInfo item = siteStationRecorderManageAdapter.getItem(pos);
                presenter.deleteRecord(item.getId(), item.getCreateUserName(), UserManager.getInstance().getUserId());
            }

            @Override
            public void onClick(int pos) {
                Intent intent = new Intent(SideStationRecordManageActivity.this, AddSideStationRecordActivity.class);
                intent.putExtra("id", siteStationRecorderManageAdapter.getItem(pos).getId());
                startActivity(intent);
            }
        });
        // 可以用在：当点击外部空白处时，关闭正在展开的侧滑菜单。我个人觉得意义不大，
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    SwipeMenuLayout viewCache = SwipeMenuLayout.getViewCache();
                    if (null != viewCache) {
                        viewCache.smoothClose();
                    }
                }
                return false;
            }
        });

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    name = searchText.getText().toString().trim();
                    refreshLayout.autoRefresh();
                    KeyboardUtil.closeKeyboard(SideStationRecordManageActivity.this);
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
                startActivityForResult(new Intent(SideStationRecordManageActivity.this,
                        AddSideStationRecordActivity.class), ADD_SUCCESS);
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
    public void showSideStationRecordList(PageInfo<SiteStationRecorderManageInfo> datas) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            siteStationRecorderManageAdapter.setNewData(datas.getList());
            page = 2;
        } else {
            siteStationRecorderManageAdapter.addData(datas.getList());
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > siteStationRecorderManageAdapter.getItemCount());
    }

    @Override
    public void dealDeleteRecordResult() {
        siteStationRecorderManageAdapter.remove(pos);
        showError("删除成功");
    }

    @Override
    public void showSideStationRecordDetail(SiteStationRecorderManageDetailInfo info) {

    }

    @Override
    public void dealAddRecordResult() {

    }

    @Override
    public void showUnits(List<SideStationRecordUnit> info) {

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
}
