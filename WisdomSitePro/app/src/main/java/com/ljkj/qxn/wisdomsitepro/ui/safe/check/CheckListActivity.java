package com.ljkj.qxn.wisdomsitepro.ui.safe.check;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.SelectWindowHelper;
import com.ljkj.qxn.wisdomsitepro.contract.safe.CheckListContract;
import com.ljkj.qxn.wisdomsitepro.data.Consts;
import com.ljkj.qxn.wisdomsitepro.data.entity.CheckInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.SafeModel;
import com.ljkj.qxn.wisdomsitepro.presenter.safe.CheckListPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.safe.adapter.CheckListAdapter2;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;

/**
 * 类描述：检查列表
 * 创建人：lxx
 * 创建时间：2018/9/5
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CheckListActivity extends BaseActivity implements CheckListContract.View {
    /** 安全检查 */
    public static final int TYPE_SAFE_CHECK = 1;
    /** 质量检查 */
    public static final int TYPE_QUALITY_CHECK = 2;

    @BindView(R.id.tv_title)
    protected TextView titleText;

    @BindView(R.id.refreshLayout)
    protected SmartRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;

    @BindView(R.id.ll_no_data)
    protected ViewGroup llNoData;

    @BindView(R.id.line)
    protected View lineView;

    @BindView(R.id.rb_trouble_level)
    protected RadioButton troubleLevelBtn;

    @BindView(R.id.rb_rectify_type)
    protected RadioButton rectifyTypeBtn;

    @BindView(R.id.rb_check_data)
    protected RadioButton checkDateBtn;

    protected int page = 1;
    protected int loadType;
    protected int checkType;
    private CheckListAdapter2 checkListAdapter;
    private CheckListPresenter checkListPresenter;
    protected String dangerLevel = "", rectifyType = "", checkDate = "";
    private Calendar startCalendar, endCalendar;

    public static void startActivity(Context context, int checkType) {
        Intent intent = new Intent(context, CheckListActivity.class);
        intent.putExtra("checkType", checkType);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        refreshLayout.autoRefresh();
    }

    @Override
    protected void initUI() {
        checkType = getIntent().getIntExtra("checkType", TYPE_SAFE_CHECK);
        titleText.setText(checkType == TYPE_SAFE_CHECK ? "安全检查" : "质量检查");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshLayout.setEnableLoadMore(false);

        checkDateBtn.setTag(Calendar.getInstance());
        startCalendar = Calendar.getInstance();
        startCalendar.add(Calendar.YEAR, -10);
        endCalendar = Calendar.getInstance();
    }

    @Override
    protected void initData() {
        checkListPresenter = new CheckListPresenter(this, SafeModel.newInstance());
        addPresenter(checkListPresenter);
        recyclerView.setAdapter(checkListAdapter = new CheckListAdapter2(null, false));
        initListener();
        refreshLayout.autoRefresh();
    }

    private void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.REFRESH;
                if (checkType == TYPE_SAFE_CHECK) {
                    checkListPresenter.getSafeCheckList(UserManager.getInstance().getProjectId(), 1, 10, dangerLevel, rectifyType, checkDate, false);
                } else {
                    checkListPresenter.getQualityCheckList(UserManager.getInstance().getProjectId(), 1, 10, dangerLevel, rectifyType, checkDate, false);
                }

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadType = Consts.XR_LOAD_TYPE.LOAD_MORE;
                if (checkType == TYPE_SAFE_CHECK) {
                    checkListPresenter.getSafeCheckList(UserManager.getInstance().getProjectId(), page, 10, dangerLevel, rectifyType, checkDate, false);
                } else {
                    checkListPresenter.getQualityCheckList(UserManager.getInstance().getProjectId(), page, 10, dangerLevel, rectifyType, checkDate, false);
                }
            }
        });

        checkListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CheckInfo checkInfo = checkListAdapter.getItem(position);
                if (checkInfo == null) {
                    return;
                }
                int status = checkInfo.reformStatus;
                CheckDetailActivity.startActivity(CheckListActivity.this, checkType, checkInfo.id, status, false);
            }
        });
    }

    @OnClick({R.id.tv_back, R.id.rb_trouble_level, R.id.rb_rectify_type, R.id.rb_check_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.rb_trouble_level: //隐患等级
                showSelectWindow(1, troubleLevelBtn);
                break;
            case R.id.rb_rectify_type: //整改类型
                showSelectWindow(2, rectifyTypeBtn);
                break;
            case R.id.rb_check_data: //检查日期
                showDateDialog();
                break;
            default:
                break;
        }
    }

    private void showSelectWindow(final int type, RadioButton button) {
        List<String> items = new ArrayList<>();
        if (type == 1) {
            items.add("全部");
            items.add("一般隐患");
            items.add("重大隐患");
        } else if (type == 2) {
            items.add("全部");
            items.add("限期整改");
            items.add("局部停工整改");
            items.add("停工挂牌督办");
        }

        SelectWindowHelper.showSingleListWindow(this, items, lineView, button, new SelectWindowHelper.OnItemSelectListener() {
            @Override
            public void onItemSelect(String item, int position) {
                if (type == 1) {
                    troubleLevelBtn.setText(item);
                    dangerLevel = position == 0 ? "" : position + "";
                } else if (type == 2) {
                    rectifyTypeBtn.setText(item);
                    rectifyType = position == 0 ? "" : position + "";
                }
                refreshLayout.autoRefresh();
            }
        });
    }

    private void showDateDialog() {
        if (isFastDoubleClick()) {
            return;
        }
        final Calendar calendar = (Calendar) checkDateBtn.getTag();
        PickerDialogHelper.showTimePicker(this, calendar, startCalendar, endCalendar, true, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                calendar.setTime(date);
                checkDateBtn.setTag(calendar);
                checkDateBtn.setText(DateUtils.date2str(date, DateUtils.PATTERN_DATE));
                if (checkType == CheckListActivity.TYPE_SAFE_CHECK) {
                    checkDate = date.getTime() + "";
                } else {
                    checkDate = checkDateBtn.getText().toString();
                }

                refreshLayout.autoRefresh();
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
    public void showSafeCheckList(PageInfo<CheckInfo> datas) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            checkListAdapter.setNewData(datas.getList());
            page = 2;
        } else {
            checkListAdapter.addData(datas.getList());
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > checkListAdapter.getItemCount());
    }

    @Override
    public void showQualityCheckList(PageInfo<CheckInfo> datas) {
        if (loadType == Consts.XR_LOAD_TYPE.REFRESH) {
            checkListAdapter.setNewData(datas.getList());
            page = 2;
        } else {
            checkListAdapter.addData(datas.getList());
            ++page;
        }
        refreshLayout.setEnableLoadMore(datas.getTotal() > checkListAdapter.getItemCount());
    }

}
