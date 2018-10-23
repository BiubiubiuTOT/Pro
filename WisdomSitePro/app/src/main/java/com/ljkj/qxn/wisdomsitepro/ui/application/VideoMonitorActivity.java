package com.ljkj.qxn.wisdomsitepro.ui.application;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.contract.application.VideoSourceContract;
import com.ljkj.qxn.wisdomsitepro.data.entity.VideoSourceInfo;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.ApplicationModel;
import com.ljkj.qxn.wisdomsitepro.presenter.application.VideoSourcePresenter;
import com.ljkj.qxn.wisdomsitepro.ui.application.adapter.VideoMonitorAdapter;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.DividerGridItemDecoration;
import cdsp.android.util.ToastUtils;

/**
 * 类描述：视频监控
 * 创建人：lxx
 * 创建时间：2018/6/13
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class VideoMonitorActivity extends BaseActivity implements VideoSourceContract.View {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.video_view)
    VideoView videoView;

    @BindView(R.id.progress_wheel)
    ProgressWheel progressWheel;

    @BindView(R.id.rl_area)
    RecyclerView areaRV;

    @BindView(R.id.rl_camera)
    RecyclerView cameraRV;

    @BindView(R.id.ic_play_pause)
    ImageView playPauseImage;

    private VideoMonitorAdapter areaAdapter;
    private VideoMonitorAdapter cameraAdapter;
    private VideoSourcePresenter presenter;
    private VideoSourceInfo currentVideoSourceInfo;
    private HashMap<String, ArrayList<VideoSourceInfo>> videoSourceMap;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, VideoMonitorActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_monitor);
    }

    @Override
    protected void initUI() {
        titleText.setText("视频监控");
        playPauseImage.setEnabled(false);
        areaRV.setNestedScrollingEnabled(false);
        cameraRV.setNestedScrollingEnabled(false);
        areaRV.setLayoutManager(new GridLayoutManager(this, 4));
        cameraRV.setLayoutManager(new GridLayoutManager(this, 3));
        areaRV.addItemDecoration(new DividerGridItemDecoration(this, ContextCompat.getDrawable(this, R.drawable.divider_of_video_monitor)));
        cameraRV.addItemDecoration(new DividerGridItemDecoration(this, ContextCompat.getDrawable(this, R.drawable.divider_of_video_monitor)));
    }

    @Override
    protected void initData() {
        presenter = new VideoSourcePresenter(this, ApplicationModel.newInstance());
        addPresenter(presenter);

        areaRV.setAdapter(areaAdapter = new VideoMonitorAdapter(null, VideoMonitorAdapter.TYPE_AREA));
        cameraRV.setAdapter(cameraAdapter = new VideoMonitorAdapter(null, VideoMonitorAdapter.TYPE_CAMERA));
        setListener();

        presenter.getVideoMonitor(UserManager.getInstance().getProjectId());
    }

    private void start(String videoPath) {
        progressWheel.spin();
        videoView.setVideoPath(videoPath);
        videoView.start();
    }

    private void setListener() {
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                progressWheel.stopSpinning();
                playPauseImage.setEnabled(true);
                playPauseImage.setImageResource(R.mipmap.ic_video_monitor_pause);
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                ToastUtils.showShort("播放出错了");
                return false;
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ToastUtils.showShort("播放结束");
                playPauseImage.setEnabled(false);
            }
        });

        areaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoSourceInfo info = areaAdapter.getItem(position);
                if (info == null || info.isChecked()) {
                    return;
                }
                for (VideoSourceInfo sourceInfo : areaAdapter.getData()) {
                    sourceInfo.setChecked(false);
                }
                info.setChecked(true);
                areaAdapter.notifyDataSetChanged();
                cameraAdapter.setNewData(videoSourceMap.get(info.getAreaName()));
            }
        });

        cameraAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoSourceInfo info = cameraAdapter.getItem(position);
                if (info == null || info.isChecked()) {
                    return;
                }
                if (currentVideoSourceInfo != null) {
                    currentVideoSourceInfo.setChecked(false);
                }
                info.setChecked(true);
                currentVideoSourceInfo = info;

                cameraAdapter.notifyDataSetChanged();
                start(info.getSourceUrl());
            }
        });

    }

    @OnClick({R.id.tv_back, R.id.iv_fullscreen, R.id.ic_play_pause,R.id.iv_capture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                onBackPressed();
                break;
            case R.id.iv_fullscreen:
                if (currentVideoSourceInfo != null) {
                    FullScreenVideoActivity.startActivity(VideoMonitorActivity.this, currentVideoSourceInfo.getSourceUrl());
                }
                break;
            case R.id.ic_play_pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                    playPauseImage.setImageResource(R.mipmap.ic_video_monitor_play);
                } else {
                    videoView.start();
                    playPauseImage.setImageResource(R.mipmap.ic_video_monitor_pause);
                }
                break;
            case R.id.iv_capture:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!videoView.isPlaying()) {
            progressWheel.spin();
            videoView.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        videoView.stopPlayback();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showVideoList(List<VideoSourceInfo> sources) {
    }

    @Override
    public void showVideoMonitor(List<ArrayList<VideoSourceInfo>> sources) {
        //TODO
        videoSourceMap = new HashMap<>();
        ArrayList<VideoSourceInfo> areaList = new ArrayList<>();
        for (ArrayList<VideoSourceInfo> list : sources) {
            if (!list.isEmpty()) {
                videoSourceMap.put(list.get(0).getAreaName(), list);
                VideoSourceInfo info = new VideoSourceInfo();
                info.setAreaName(list.get(0).getAreaName());
                areaList.add(info);
            }
        }
        if (areaList.isEmpty()) {
            return;
        }

        areaList.get(0).setChecked(true);
        areaAdapter.setNewData(areaList);

        ArrayList<VideoSourceInfo> cameraList = videoSourceMap.get(areaList.get(0).getAreaName());
        if (cameraList != null && !cameraList.isEmpty()) {
            currentVideoSourceInfo = cameraList.get(0);
            currentVideoSourceInfo.setChecked(true);
            start(currentVideoSourceInfo.getSourceUrl());
            cameraAdapter.setNewData(cameraList);
        }
    }
}
