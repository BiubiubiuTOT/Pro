package com.ljkj.qxn.wisdomsitepro.ui.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.H5Helper;
import com.ljkj.qxn.wisdomsitepro.contract.project.ProjectProgressContract;
import com.ljkj.qxn.wisdomsitepro.data.cache.ProjectItemCache;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectBannerInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ProjectProgressInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.RefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ProjectModel;
import com.ljkj.qxn.wisdomsitepro.presenter.project.ProjectProgressPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.common.WebViewFragment;
import com.ljkj.qxn.wisdomsitepro.ui.personal.MessgeActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.banner.ConvenientBanner;
import cdsp.android.banner.holder.CBViewHolderCreator;
import cdsp.android.ui.base.BaseFragment;

/**
 * 类描述：项目
 * 创建人：liufei
 * 创建时间：2018/1/31 15:28
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@Deprecated
public class ProjectFragment extends BaseFragment implements ProjectProgressContract.View {
    public static final String TAG = ProjectFragment.class.getName();

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.cb_items)
    ConvenientBanner cbItems;
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.iv_banner_left)
    ImageView ivBannerLeft;
    @BindView(R.id.iv_banner_right)
    ImageView ivBannerRight;
    @BindView(R.id.tv_banner_page)
    TextView tvBannerPage;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private ProjectProgressPresenter projectProgressPresenter;
    private WebViewFragment webViewFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initUI() {
        tvTitle.setText(UserManager.getInstance().getProjectName());
        refreshLayout.setEnableLoadMore(false);
        tvRight.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(getActivity(), R.mipmap.ic_mail), null);
        tvRight.setVisibility(View.VISIBLE);

        //项目条目列表
        cbItems.setPages(new CBViewHolderCreator() {
            @Override
            public ProjectItemHolder createHolder() {
                return new ProjectItemHolder();
            }
        }, ProjectItemCache.listProItems).setPageIndicator(new int[]{R.mipmap.ic_pro_items_normal, R.mipmap.ic_pro_items_check});
        cbItems.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        cbItems.setCanLoop(false);
        cbItems.setcurrentitem(0);

        webViewFragment = H5Helper.getHomeFragment(UserManager.getInstance().getProjectId());
        getFragmentManager().beginTransaction().replace(R.id.fragment_main, webViewFragment).commit();
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        projectProgressPresenter = new ProjectProgressPresenter(this, ProjectModel.newInstance());
        addPresenter(projectProgressPresenter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                projectProgressPresenter.getProjectProgress(UserManager.getInstance().getProjectId(), "");
                webViewFragment.reload(null);
            }
        });
        refreshLayout.autoRefresh();
        UserManager.getInstance().addOnProjectChangeListener(onProjectChangeListener);
    }

    private UserManager.OnProjectChangeListener onProjectChangeListener = new UserManager.OnProjectChangeListener() {
        @Override
        public void onProjectChange(String projectId, String name) {
            tvTitle.setText(name);
            webViewFragment.reload(H5Helper.getHomeUrl(UserManager.getInstance().getProjectId()));
            refreshLayout.autoRefresh();
        }
    };

    //设置轮播图
    private void setConvenientBanner(final List<ProjectBannerInfo> list) {
        convenientBanner.setPages(new CBViewHolderCreator<ProjectBannerView>() {
            @Override
            public ProjectBannerView createHolder() {
                return new ProjectBannerView();
            }
        }, list)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                        tvBannerPage.setText((position + 1) + "/" + list.size());
                        ivBannerLeft.setVisibility(position > 0 ? View.VISIBLE : View.GONE);
                        ivBannerRight.setVisibility(position + 1 < list.size() ? View.VISIBLE : View.GONE);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
        convenientBanner.setCanLoop(false);
        convenientBanner.setcurrentitem(0);

        tvBannerPage.setText("1" + "/" + list.size());
    }

    @OnClick({R.id.iv_banner_left, R.id.iv_banner_right, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_banner_left:
                convenientBanner.setcurrentitem(convenientBanner.getCurrentItem() - 1);
                break;
            case R.id.iv_banner_right:
                convenientBanner.setcurrentitem(convenientBanner.getCurrentItem() + 1);
                break;
            case R.id.tv_right:
                startActivity(new Intent(getContext(), MessgeActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        refreshLayout.finishRefresh();
    }

    @Override
    public void showProjectProgressList(List<ProjectProgressInfo> data) {
        List<ProjectBannerInfo> list = new ArrayList<>();
//        if (data.getFiles() == null) {
//            return;
//        }
//        for (ProjectProgressInfo.FilesBean filesBean : data.getFiles()) {
//            RequestParams requestParams = new RequestParams();
//            requestParams.put("fileid", filesBean.getFileKey());
//            requestParams.put("isDownload", true);
//            StringBuilder url = new StringBuilder();
//            url.append(HostConfig.getHost()).append(HostConfig.IMAGR_URL);
//            url.append(UrlUtil.buildProtocolParams(url.toString())).append(UrlUtil.encodeParams(requestParams));
//            list.add(new ProjectBannerInfo(data.getProgress(), DateUtil.format(data.getDate()), url.toString()));
//        }
        setConvenientBanner(list);
    }

    @Override
    public void showRecentProjectProgressInfo(ProjectProgressInfo data) {

    }

    /** @see AddProjectImageProgressActivity */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshEvent(RefreshEvent event) {
        projectProgressPresenter.getProjectProgress(UserManager.getInstance().getProjectId(), "");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        UserManager.getInstance().removeOnProjectChangeListener(onProjectChangeListener);
    }
}
