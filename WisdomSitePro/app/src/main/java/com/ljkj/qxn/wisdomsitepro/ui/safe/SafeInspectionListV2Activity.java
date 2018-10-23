package com.ljkj.qxn.wisdomsitepro.ui.safe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.DateUtil;
import com.ljkj.qxn.wisdomsitepro.Utils.SelectWindowHelper;
import com.ljkj.qxn.wisdomsitepro.contract.common.DistictDataContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.AuthorityContract;
import com.ljkj.qxn.wisdomsitepro.contract.safe.SafePatrolContract;
import com.ljkj.qxn.wisdomsitepro.data.AuthorityId;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.DeptTeamInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.DistictInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafePatrolDetailInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.SafePatrolInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.TeamLeader;
import com.ljkj.qxn.wisdomsitepro.data.event.InspectionEvent;
import com.ljkj.qxn.wisdomsitepro.manager.AuthorityManager;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.AuthorityModel;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.AuthorityPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.common.DistictDataPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.SafePatrolPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.SafeInspectionListV2Adapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.timessquare.CalendarPickerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.logging.Logger;
import cdsp.android.ui.base.BaseActivity;

public class SafeInspectionListV2Activity extends BaseActivity implements
        SafePatrolContract.View, DistictDataContract.View, AuthorityContract.View {

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

    public SafeInspectionListV2Adapter adapter;
    public int loadType;
    public int page = 1;
    public String isDealWith = "0";

    public String accidentReason = ""; //事故原因
    public String changeGroup = ""; //整改班组
    //    public String accidentType = ""; //事故类型
    public String startDate = ""; //开始时间
    public String endDate = ""; //结束时间

    private SafePatrolPresenter patrolPresenter;

    protected DistictDataPresenter reasonPresenter;
    private AuthorityPresenter authorityPresenter;

    protected List<DistictInfo> reasonList;
    private AlertDialog alertDialog;
    private List<String> options1Items = new ArrayList<>();
    private List<List<String>> options2Items = new ArrayList<>();
    private Map<String, String> options = new HashMap<>();
    private OptionsPickerView<String> pvOptions;
    private ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_inspection_list_v2);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("安全巡检");
        tvRight.setText("新增");
        tvRight.setVisibility(UserManager.getInstance().isProjectManager() ? View.VISIBLE : View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new SafeInspectionListV2Adapter(this));
        refreshLayout.setEnableLoadMore(false);
        rbDate.setTag(Calendar.getInstance());
        initListener();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.rb_pending) {
                    isDealWith = "0";//待处理
                    refreshLayout.autoRefresh();
                } else if (checkedId == R.id.rb_has_finished) {
                    isDealWith = "1";//已整改
                    refreshLayout.autoRefresh();
                }
            }
        });
        initDia();
    }

    @Override
    protected void handleAuthority() {
        AuthorityManager.getInstance().handleViewByAuthority(tvRight, AuthorityId.SafeManage.SAFE_INSPECTION, true);
    }

    private void initListener() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                patrolPresenter.getSafePatrolList(UserManager.getInstance().getProjectId(), accidentReason, changeGroup, startDate, endDate, isDealWith, Consts.APPParams.ROWS, 1);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                patrolPresenter.getSafePatrolList(UserManager.getInstance().getProjectId(), accidentReason, changeGroup, startDate, endDate, isDealWith, Consts.APPParams.ROWS, page);
            }
        });


        adapter.setOnItemClickListener(new SafeInspectionListV2Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int flag) {

                SafePatrolInfo item = adapter.getItem(position);
                boolean canEdit;
                if (TextUtils.isEmpty(item.getTeamManagerId())) {
                    canEdit = false;
                } else
                    canEdit = item.getTeamManagerId().equals(UserManager.getInstance().getUserId());

                switch (flag) {

                    case 2://待整改
                        SafeInspectionDetailV2Activity.startActivity(SafeInspectionListV2Activity.this, item.getId(), canEdit ? "巡检详情-立即整改" : "巡检详情",
                                canEdit ? SafeInspectionDetailsOfImmeRectiV2Activity.class : SafeInspectionDetailsOfQualifiedV2.class);
                        break;

                    case 3://待核查
                        SafeInspectionDetailV2Activity.startActivity(SafeInspectionListV2Activity.this, item.getId(), "巡检详情-待核查", SafeInspectionDetailsOfTobeAuditedV2Activity.class);
                        break;

                    case 4://不合格，重新整改
                        SafeInspectionDetailV2Activity.startActivity(SafeInspectionListV2Activity.this, item.getId(), canEdit ? "巡检详情-重新整改" : "巡检详情", canEdit ?
                                SafeInspectionDetailsOfQualifiedV2Activity.class : SafeInspectionDetailsOfQualifiedV2.class);
                        break;

                    case 5://合格
                        SafeInspectionDetailV2Activity.startActivity(SafeInspectionListV2Activity.this, item.getId(), "巡检详情-合格", SafeInspectionDetailsOfQualifiedV2.class);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        patrolPresenter = new SafePatrolPresenter(this, SafeModel.newInstance());
        addPresenter(patrolPresenter);

        refreshLayout.autoRefresh();

        reasonPresenter = new DistictDataPresenter(this, CommonModel.newInstance());
        addPresenter(reasonPresenter);
        reasonPresenter.listDisticts("securityPatrolAccidentCause");

        authorityPresenter = new AuthorityPresenter(this, AuthorityModel.newInstance());
        addPresenter(authorityPresenter);
        authorityPresenter.getDeptTeamList(UserManager.getInstance().getProjectId());
    }


    private void showSelectReasonWindow(RadioButton button) {
        if (reasonList == null) {
            reasonPresenter.listDisticts("securityPatrolAccidentCause");
            button.setChecked(false);
            return;
        }

        SelectWindowHelper.showSingleListWindow(this, items, lineView, button, new SelectWindowHelper.OnItemSelectListener() {
            @Override
            public void onItemSelect(String item, int position) {
                rbReason.setText(item);
                accidentReason = position == 0 ? "" : reasonList.get(position - 1).getValue();
                refreshLayout.autoRefresh();
            }
        });
    }

    @Override
    public void showSafePatrolList(PageInfo<SafePatrolInfo> pageInfo) {
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
    public void showSafePatrolDetail(SafePatrolDetailInfo info) {

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

    private void initDia() {
        Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, -1);
        Date today = new Date();
        Calendar to = Calendar.getInstance();
        to.add(Calendar.DAY_OF_MONTH, 1);

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_time, null);
        final TextView tv_start = (TextView) view.findViewById(R.id.tv_start_time);
        final TextView tv_end = (TextView) view.findViewById(R.id.tv_end_time);
        final CalendarPickerView calendar = (CalendarPickerView) view.findViewById(R.id.calendarView);

        calendar.init(lastYear.getTime(), to.getTime()).inMode(CalendarPickerView.SelectionMode.RANGE);
        calendar.scrollToDate(today);
        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(final Date date) {
                final int size = calendar.getSelectedDates().size();
                String formatDate = DateUtil.format(String.valueOf(date.getTime()));
                Logger.d("size: " + size + " onDateSelected = " + formatDate + " time: " + date.getTime());

                if (size > 1) {
                    tv_end.setText(formatDate);
                } else {
                    tv_start.setText(formatDate);
                    tv_end.setText("");
                }
            }

            @Override
            public void onDateUnselected(Date date) {
                Logger.d("onDateUnselected = " + DateUtil.format(String.valueOf(date.getTime())));
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        alertDialog = builder.setView(view)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startDate = tv_start.getText().toString();
                        endDate = tv_end.getText().toString();
                        if (!startDate.isEmpty() && !endDate.isEmpty()) {

                            Logger.d("start: " + startDate + " end: " + endDate);
                            refreshLayout.autoRefresh();

                            rbDate.setText(startDate + "至" + endDate);
                        }
                    }
                }).create();
    }

    @OnClick({R.id.tv_back, R.id.rb_reason, R.id.rb_group, R.id.rb_date, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.rb_reason: //事故原因
                showSelectReasonWindow(rbReason);
                break;
            case R.id.rb_group: //整改班组
                showSelectGroupWindow(rbGroup);
                break;
            case R.id.rb_date: //检查日期
                if (!alertDialog.isShowing()) {
                    alertDialog.show();
                }
                break;
            case R.id.tv_right:
                SafeInspectionAddV2Activity.startActivity(SafeInspectionListV2Activity.this);
                break;
            default:
                break;
        }
    }

    private void showSelectGroupWindow(RadioButton rbGroup) {
        if (pvOptions != null)
            pvOptions.show();
        else {
            authorityPresenter.getDeptTeamList(UserManager.getInstance().getProjectId());
        }
    }

    private void initGroupWindow() {
        //条件选择器
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                List<String> list = options2Items.get(options1);
                if (!list.isEmpty()) {
                    String str = list.get(option2);
                    changeGroup = options.get(str);
                    rbGroup.setText(str);

                } else {
                    rbGroup.setText("整改班组");
                    changeGroup = "";
                }
                rbGroup.setChecked(false);
                refreshLayout.autoRefresh();
            }
        }).setSubmitColor(ContextCompat.getColor(this, R.color.color_main))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(this, R.color.color_main))//取消按钮文字颜色
                .setTextColorCenter(ContextCompat.getColor(this, R.color.color_main))//选中颜色
                .isDialog(true)
                .setTitleText("选择整改班组")
                .setTitleColor(ContextCompat.getColor(this, R.color.color_main))
                .setOutSideCancelable(false)
                .build();
        pvOptions.setPicker(options1Items, options2Items);
    }

    @Override
    public void showDisticts(List<DistictInfo> data) {
        reasonList = data;
        items = new ArrayList<>();
        items.add("全部");
        for (DistictInfo info : reasonList) {
            items.add(info.getName());
        }
    }

    @Override
    public void showDeptTeamList(List<DeptTeamInfo> list) {
        List<String> list1;
        options1Items.add("全部");
        ArrayList<String> arr = new ArrayList<>();
        arr.add("全部");
        options2Items.add(arr);
        options.put("全部", "");
        for (DeptTeamInfo deptTeamInfo : list) {
            String name = deptTeamInfo.getName();
            options.put(name, deptTeamInfo.getId());
            options1Items.add(name);
            list1 = new ArrayList<>();
            List<DeptTeamInfo.LmTeamListBean> lmTeamList = deptTeamInfo.getLmTeamList();
            for (DeptTeamInfo.LmTeamListBean lmTeamListBean : lmTeamList) {
                String name1 = lmTeamListBean.getName();
                options.put(name1, lmTeamListBean.getId());
                list1.add(name1);
            }
            options2Items.add(list1);
        }
        initGroupWindow();
    }

    @Override
    public void showTeamLeader(TeamLeader leader) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(InspectionEvent event) {
        int msgType = event.getMsgType();
        if (msgType == Consts.EVENT_TYPE.INSPECTION_TYPE || msgType == Consts.EVENT_TYPE.INSPECTION_PASS
                || msgType == Consts.EVENT_TYPE.INSPECTION_ADD) {
            refreshLayout.autoRefresh();
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
