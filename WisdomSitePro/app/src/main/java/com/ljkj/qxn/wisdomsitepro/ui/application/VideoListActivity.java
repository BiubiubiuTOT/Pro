package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.application.VideoSourceContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.VideoSourceInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;
import com.ljkj.qxn.wisdomsitepro.presenter.application.VideoSourcePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.VideoAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.BaseRecyclerAdapter;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.DividerGridItemDecoration;
import cdsp.android.util.NetworkUtils;

/**
 * 类描述：视频源列表页
 * 创建人：lxx
 * 创建时间：2018/4/20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class VideoListActivity extends BaseActivity implements VideoSourceContract.View {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ll_no_data)
    ViewGroup noDataLayout;

    @BindView(R.id.tv_title)
    public TextView titleText;

    private VideoSourcePresenter presenter;
    private VideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
    }

    @Override
    protected void initUI() {
        titleText.setText("视频监控");
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected void initData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this, ContextCompat.getDrawable(this, R.drawable.divider_of_select_small_image)));
        recyclerView.setAdapter(adapter = new VideoAdapter(this));

        presenter = new VideoSourcePresenter(this, ApplicationModel.newInstance());
        addPresenter(presenter);
        presenter.getVideoSource(UserManager.getInstance().getProjectId());
//        test();

        adapter.setOnItemViewClickListener(new BaseRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, final int position) {
                if (NetworkUtils.isWifi(VideoListActivity.this)) {
                    VideoSourceInfo info = adapter.getItem(position);
                    VideoActivity.startActivity(VideoListActivity.this, info.getName(), info.getSourceUrl());
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(VideoListActivity.this)
                            .setTitle("提示")
                            .setMessage("当前在非WiFi环境，观看视频将消耗一定流量，是否继续？")
                            .setCancelable(false)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    VideoSourceInfo info = adapter.getItem(position);
                                    VideoActivity.startActivity(VideoListActivity.this, info.getName(), info.getSourceUrl());
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.show();
                }
            }
        });
    }

    private void test() {
        List<VideoSourceInfo> list = new ArrayList<>();
        VideoSourceInfo info = new VideoSourceInfo();
        info.setName("测试");
        info.setSourceUrl("http://hls.open.ys7.com/openlive/c5e01fbf0c074e2ea54658ac77ca9bd8.m3u8");
        list.add(info);
        adapter.loadData(list);
    }

    @Override
    public void showVideoList(List<VideoSourceInfo> sources) {
        adapter.loadData(sources);
    }

    @Override
    public void showVideoMonitor(List<ArrayList<VideoSourceInfo>> sources) {
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        noDataLayout.setVisibility(adapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }

    @OnClick({R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            default:
                break;
        }
    }

}
