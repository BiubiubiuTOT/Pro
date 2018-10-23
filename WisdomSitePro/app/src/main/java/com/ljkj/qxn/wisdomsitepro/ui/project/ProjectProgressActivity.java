package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectProgressContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectProgressInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.RefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.project.ProjectProgressPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
import cdsp.android.banner.ConvenientBanner;
import cdsp.android.banner.holder.CBViewHolderCreator;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;

/**
 * 类描述：工程形象进度
 * 创建人：liufei
 * 创建时间：2018/2/1 17:21
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectProgressActivity extends BaseActivity implements ProjectProgressContract.View, QueryFileContract.View {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.tv_banner_page)
    TextView tvBannerPage;
    @BindView(R.id.tv_progress_explain)
    TextView tvProgressExplain;
    @BindView(R.id.ll_banner)
    RelativeLayout llBanner;
    @BindView(R.id.ll_progress_explain)
    LinearLayout llProgressExplain;
    @BindView(R.id.ll_no_data)
    ViewGroup llNoData;
    @BindView(R.id.item_date)
    ItemView itemDate;
    @BindView(R.id.item_time)
    ItemView timeItem;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private QueryFilePresenter queryFilePresenter;

    private List<String> times;
    private Map<String, ProjectProgressInfo> timeMap;

    private ProjectProgressPresenter projectProgressPresenter;
    private String time;


    public static void startActivity(Context context, String time) {
        Intent intent = new Intent(context, ProjectProgressActivity.class);
        intent.putExtra("time", time);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_progress);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("工程形象进度");
        tvRight.setVisibility(View.VISIBLE);

        tvRight.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(this, R.mipmap.ic_title_add_grey), null);

        convenientBanner.setCanLoop(false);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                projectProgressPresenter.getProjectProgress(UserManager.getInstance().getProjectId(), time);
            }
        });
    }

    @Override
    protected void initData() {
        initTime();
        EventBus.getDefault().register(this);
        projectProgressPresenter = new ProjectProgressPresenter(this, ProjectModel.newInstance());
        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(projectProgressPresenter);
        addPresenter(queryFilePresenter);

        if (time != null) {
            refreshLayout.autoRefresh();
        } else {
            projectProgressPresenter.getRecentProjectProgress(UserManager.getInstance().getProjectId());
        }
    }

    @Override
    public void showProjectProgressList(List<ProjectProgressInfo> data) {
        timeMap = new HashMap<>();
        times = new ArrayList<>();

        if (data != null && !data.isEmpty()) {
            llNoData.setVisibility(View.GONE);
            llBanner.setVisibility(View.VISIBLE);
            llProgressExplain.setVisibility(View.VISIBLE);

            if (data.size() != 1) {
                timeItem.setVisibility(View.VISIBLE);
                for (ProjectProgressInfo datum : data) {
                    String progressDate = datum.getProgressDate();
                    timeMap.put(progressDate, datum);
                    times.add(progressDate);
                }
            } else
                timeItem.setVisibility(View.GONE);

            ProjectProgressInfo projectProgressInfo = data.get(0);
            queryFilePresenter.queryFile(projectProgressInfo.getId());
            timeItem.setContent(projectProgressInfo.getProgressDate());
            tvProgressExplain.setText(projectProgressInfo.getProgressExplain());
        } else {
            timeItem.setVisibility(View.GONE);
            llNoData.setVisibility(View.VISIBLE);
            llBanner.setVisibility(View.GONE);
            llProgressExplain.setVisibility(View.GONE);
        }
    }

    @Override
    public void showRecentProjectProgressInfo(ProjectProgressInfo data) {
        if (data != null) {
            time = data.getProgressDate().split(" ")[0];
            itemDate.setContent(time);

            refreshLayout.autoRefresh();
        }
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        refreshLayout.finishRefresh();
    }

    private void initTime() {
        itemDate.setTag(Calendar.getInstance());
        String time = getIntent().getStringExtra("time");
        if (time != null) {
            this.time = time;
            itemDate.setContent(this.time);
        }
    }

    private void showTimeDialog() {
        if (isFastDoubleClick()) {
            return;
        }
        final Calendar calendar = (Calendar) itemDate.getTag();
        PickerDialogHelper.showTimePicker(this, calendar, null, Calendar.getInstance(), false, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                calendar.setTime(date);
                itemDate.setTag(calendar);
                time = DateUtils.date2str(date, DateUtils.PATTERN_DATE);
                itemDate.setContent(time);
                refreshLayout.autoRefresh();
            }
        });
    }

    @OnClick({R.id.tv_back, R.id.tv_right, R.id.item_date, R.id.item_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                startActivity(new Intent(this, AddProjectImageProgressActivity.class));
                break;
            case R.id.item_date:
                showTimeDialog();
                break;
            case R.id.item_time:
                selectTime();
                break;
        }
    }

    private void selectTime() {
        if (isFastDoubleClick()) {
            return;
        }
        PickerDialogHelper.showPickerOption(this, times, 0, false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String content = times.get(options1);
                timeItem.setContent(content);

                ProjectProgressInfo projectProgressInfo = timeMap.get(content);
                queryFilePresenter.queryFile(projectProgressInfo.getId());
                tvProgressExplain.setText(projectProgressInfo.getProgressExplain());
            }
        });
    }

    /**
     * @see AddProjectImageProgressActivity
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(RefreshEvent event) {
        projectProgressPresenter.getRecentProjectProgress(UserManager.getInstance().getProjectId());
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void showFiles(final List<FileEntity> fileEntities) {
        tvBannerPage.setText("1" + "/" + fileEntities.size());
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public ProgressImageHolderView createHolder() {
                return new ProgressImageHolderView();
            }
        }, fileEntities).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                        if (tvBannerPage != null) {
                            tvBannerPage.setText((position + 1) + "/" + fileEntities.size());
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
    }
}
