package com.ljkj.qxn.wisdomsitepro.ui.common;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.SelectWindowHelper;
import com.ljkj.qxn.wisdomsitepro.contract.common.DistictDataContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.PatrolContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.DistictInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PatrolInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.InspectionEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.DistictDataPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.PatrolPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.adapter.InspectionListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;

/**
 * 类描述：巡检 -列表基础UI
 * 创建人：liufei
 * 创建时间：2018/2/7 11:57
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseInspectionListActivity extends BaseActivity implements PatrolContract.View {
    public static final String SAFE_TYPE = "2";
    public static final String QUALITY_TYPE = "3";

    /** 事故原因 */
    public static final int TYPE_REASON = 1;
    /** 整改班组 */
    public static final int TYPE_GROUP = 2;

    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.tv_right)
    public TextView tvRight;
    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    public ViewGroup llNoData;
    @BindView(R.id.refreshLayout)
    public SmartRefreshLayout refreshLayout;
    @BindView(R.id.radioGroup)
    public RadioGroup radioGroup;

    @BindView(R.id.rb_reason)
    public RadioButton rbReason;

    @BindView(R.id.rb_group)
    public RadioButton rbGroup;

    @BindView(R.id.rb_date)
    public RadioButton rbDate;

    @BindView(R.id.tv_back)
    TextView tvBack;

    @BindView(R.id.line)
    View lineView;

    public InspectionListAdapter adapter;
    public PatrolPresenter presenter;
    public int loadType;
    public int page = 1;
    public String isDealWith = "0";

    public String whyType = ""; //事故原因
    public String zgbz = ""; //整改班组
    public String jcrqTime = ""; //检查日期

    protected DistictDataPresenter reasonPresenter;
    protected DistictDataPresenter groupPresenter;
    protected List<DistictInfo> reasonList = new ArrayList<>();
    protected List<DistictInfo> groupList = new ArrayList<>();

    protected String inspectionType = QUALITY_TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_patrol);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        tvRight.setText("新增");
        tvRight.setVisibility(UserManager.getInstance().isProjectManager() ? View.VISIBLE : View.GONE);


        inspectionType = getType();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new InspectionListAdapter(this, inspectionType));
        refreshLayout.setEnableLoadMore(false);
        rbDate.setTag(Calendar.getInstance());
        initListener();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.rb_pending) {
                    isDealWith = "0";
                    refreshLayout.autoRefresh();
                } else if (checkedId == R.id.rb_has_finished) {
                    isDealWith = "1";
                    refreshLayout.autoRefresh();
                }
            }
        });
    }

    @Override
    protected void initData() {
        presenter = new PatrolPresenter(this, SafeModel.newInstance());
        addPresenter(presenter);
        refreshLayout.autoRefresh();

        reasonPresenter = buildDistictDataPresenter(TYPE_REASON);
        groupPresenter = buildDistictDataPresenter(TYPE_GROUP);
        addPresenter(reasonPresenter);
        addPresenter(groupPresenter);
        reasonPresenter.listDisticts("sgfsyyfl");
        groupPresenter.listDisticts("pro_banzu");

        EventBus.getDefault().register(this);
    }

    @Override
    public void showPatrolList(PageInfo<PatrolInfo> pageInfo) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            adapter.loadData(pageInfo.getList());
            recyclerView.scrollToPosition(0);
            page = 2;
        } else {
            page++;
            adapter.insertData(adapter.getItemCount(), pageInfo.getList());
        }
        refreshLayout.setEnableLoadMore(pageInfo.getTotal() > adapter.getItemCount());
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        llNoData.setVisibility(recyclerView.getAdapter().getItemCount() > 0 ? View.GONE : View.VISIBLE);
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadmore();
        }
    }

    public void getSelectData() {
        whyType = "";
        zgbz = "";
        jcrqTime = "";
    }

    protected abstract void initListener();

    @OnClick({R.id.tv_back, R.id.rb_reason, R.id.rb_group, R.id.rb_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.rb_reason: //事故原因
                showSelectWindow(TYPE_REASON, rbReason);
                break;
            case R.id.rb_group: //整改班组
                showSelectWindow(TYPE_GROUP, rbGroup);
                break;
            case R.id.rb_date: //检查日期
                showDateDialog();
                break;
            default:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(InspectionEvent event) {
        int msgType = event.getMsgType();
        if (msgType == Consts.EVENT_TYPE.INSPECTION_TYPE || msgType == Consts.EVENT_TYPE.INSPECTION_PASS
                || msgType == Consts.EVENT_TYPE.INSPECTION_ADD) {
            refreshLayout.autoRefresh();
        }
    }


    private DistictDataPresenter buildDistictDataPresenter(final int type) {
        final DistictDataPresenter presenter = new DistictDataPresenter(new DistictDataContract.View() {
            @Override
            public void showDisticts(List<DistictInfo> data) {
                if (data != null && !data.isEmpty()) {
                    if (type == TYPE_REASON) {
                        reasonList = data;
                    } else if (type == TYPE_GROUP) {
                        groupList = data;
                    }
                }
            }

            @Override
            public void showProgress(String message) {
            }

            @Override
            public void hideProgress() {
            }

            @Override
            public void showError(String message) {
                BaseInspectionListActivity.this.showError(message);
            }
        }, CommonModel.newInstance());
        return presenter;
    }


    private void showSelectWindow(final int type, RadioButton button) {
        if (type == TYPE_REASON && reasonList.isEmpty()) {
            reasonPresenter.listDisticts("sgfsyyfl");
            button.setChecked(false);
            return;
        } else if (type == TYPE_GROUP && groupList.isEmpty()) {
            groupPresenter.listDisticts("pro_banzu");
            button.setChecked(false);
            return;
        }
        List<String> items = new ArrayList<>();
        items.add("全部");
        if (type == TYPE_REASON) {
            for (DistictInfo info : reasonList) {
                items.add(info.getName());
            }
        }
        if (type == TYPE_GROUP) {
            for (DistictInfo info : groupList) {
                items.add(info.getName());
            }
        }

        SelectWindowHelper.showSingleListWindow(this, items, lineView, button, new SelectWindowHelper.OnItemSelectListener() {
            @Override
            public void onItemSelect(String item, int position) {
                if (type == TYPE_REASON) {
                    rbReason.setText(item);
                    whyType = position == 0 ? "" : reasonList.get(position - 1).getValue();
                } else if (type == TYPE_GROUP) {
                    rbGroup.setText(item);
                    zgbz = position == 0 ? "" : groupList.get(position - 1).getValue();
                }
                refreshLayout.autoRefresh();
            }
        });
    }

    private void showDateDialog() {
        if (isFastDoubleClick()) {
            return;
        }
        final Calendar calendar = (Calendar) rbDate.getTag();
        PickerDialogHelper.showTimePicker(this, calendar, true, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                calendar.setTime(date);
                rbDate.setTag(calendar);
                rbDate.setText(DateUtils.date2str(date, DateUtils.PATTERN_DATE));
                jcrqTime = rbDate.getText().toString();
                refreshLayout.autoRefresh();
            }
        });
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    protected abstract String getType();
}
