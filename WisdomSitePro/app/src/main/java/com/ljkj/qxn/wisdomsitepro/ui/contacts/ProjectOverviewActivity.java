package com.ljkj.qxn.wisdomsitepro.ui.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.DateUtil;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.ItemView;
import com.ljkj.qxn.wisdomsitepro.contract.common.QueryFileContract;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectOverViewContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.ProjectOverViewInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.QueryFilePresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.project.ProjectOverViewPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.project.AddProjectImageProgressActivity;
import com.ljkj.qxn.wisdomsitepro.ui.project.ProgressImageHolderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cdsp.android.banner.ConvenientBanner;
import cdsp.android.banner.holder.CBViewHolderCreator;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：项目概况
 * 创建人：njt
 * 创建时间：2018/6/23 13:53
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ProjectOverviewActivity extends BaseActivity implements ProjectOverViewContract.View, QueryFileContract.View {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;

    @BindView(R.id.tv_banner_page)
    TextView bannerPageText;

    @BindView(R.id.tv_banner_date)
    TextView bannerDateText;

    @BindView(R.id.item_project_name)
    ItemView projectNameItem;

    @BindView(R.id.item_project_place)
    ItemView projectPlaceItem;

    @BindView(R.id.item_project_date)
    ItemView projectDateItem;

    @BindView(R.id.item_project_time_limit)
    ItemView projectTimeLimitItem;

    private ProjectOverViewPresenter projectProgressPresenter;
    private QueryFilePresenter queryFilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_overview);
        ButterKnife.bind(this);
    }

    @Override
    protected void initUI() {
        titleText.setText("项目概况");
        projectNameItem.setContent(UserManager.getInstance().getProjectName());
    }

    @Override
    protected void initData() {
        projectProgressPresenter = new ProjectOverViewPresenter(this, ProjectModel.newInstance());
        addPresenter(projectProgressPresenter);
        projectProgressPresenter.getProjectOverView(UserManager.getInstance().getProjectId());

        queryFilePresenter = new QueryFilePresenter(this, CommonModel.newInstance());
        addPresenter(queryFilePresenter);
    }

    //设置轮播图
    private void setConvenientBanner(final List<FileEntity> fileEntities) {
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
                        if (bannerPageText != null) {
                            bannerPageText.setText((position + 1) + "/" + fileEntities.size());
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
    }

    @OnClick({R.id.tv_back, R.id.item_nine_brand_one_chart, R.id.item_add_project_progress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.item_nine_brand_one_chart:
                startActivity(new Intent(ProjectOverviewActivity.this, NineBrandOneChartActivity.class));
                break;
            case R.id.item_add_project_progress:
                startActivity(new Intent(this, AddProjectImageProgressActivity.class));
                break;
            default:
                break;
        }
    }


    @Override
    public void showProjectOverview(ProjectOverViewInfo info) {
        ProjectOverViewInfo.ProjectBase base = info.base;
        projectPlaceItem.setContent(base.projAddress);
        projectDateItem.setContent(base.startDate);
        projectTimeLimitItem.setContent(DateUtil.differentDays(base.startDate, base.endDate) + "天");
    }

    @Override
    public void showFiles(List<FileEntity> fileEntities) {
        setConvenientBanner(fileEntities);
    }
}
