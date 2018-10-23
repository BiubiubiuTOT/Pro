package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.WApplication;
import com.pnikosis.materialishprogress.ProgressWheel;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;

/**
 * 类描述：视频播放页
 * 创建人：lxx
 * 创建时间：2018/4/20
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class VideoActivity extends BaseActivity {
    private static final String KEY_TITLE = "key_title";
    private static final String KEY_PATH = "key_path";

    @BindView(R.id.tv_title)
    public TextView titleText;

    @BindView(R.id.video_view)
    VideoView videoView;

    @BindView(R.id.progress_wheel)
    ProgressWheel progressWheel;

    @BindView(R.id.tv_loading)
    TextView loadingText;

    @BindView(R.id.btn_play)
    ImageButton playBtn;

    @BindView(R.id.tv_progress_time)
    TextView progressTimeText;

    private String videoPath;
    private int currentPlayTime;

    public static void startActivity(Context context, String title, String videoPath) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra(KEY_PATH, videoPath);
        intent.putExtra(KEY_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }

    @Override
    protected void initUI() {
        String title = getIntent().getStringExtra(KEY_TITLE);
        titleText.setText(title);
        videoPath = getIntent().getStringExtra(KEY_PATH);
        playBtn.setEnabled(false);
    }

    @Override
    protected void initData() {
        MediaController controller = new MediaController(this);
        videoView.setVideoPath(videoPath);
//        videoView.setVideoPath("http://hls.open.ys7.com/openlive/c5e01fbf0c074e2ea54658ac77ca9bd8.m3u8");
//        videoView.setMediaController(controller);
//        controller.setMediaPlayer(videoView);
        videoView.start();
        setLoadingLayout(true);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                Log.e("lxx", "开始播放");
                playBtn.setEnabled(true);
                setLoadingLayout(false);
                playBtn.setImageResource(R.mipmap.ic_video_pause);
                startProgress();
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                showError("播放出错了");
                return false;
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                showError("播放结束");
                playBtn.setEnabled(false);
                setLoadingLayout(false);
            }
        });


    }

    private void setLoadingLayout(boolean visibility) {
        progressWheel.setVisibility(visibility ? View.VISIBLE : View.GONE);
        loadingText.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!videoView.isPlaying()) {
            videoView.start();
        }
    }

    @Override
    protected void onDestroy() {
        WApplication.getMainHandler().removeCallbacks(ProgressTask);
        videoView.stopPlayback();
        super.onDestroy();
    }

    @OnClick({R.id.tv_back, R.id.btn_play})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.btn_play:
                if (videoView.isPlaying()) {
                    videoView.pause();
                    playBtn.setImageResource(R.mipmap.ic_video_play);
                } else {
                    videoView.start();
                    startProgress();
                    playBtn.setImageResource(R.mipmap.ic_video_pause);
                }
                break;
            default:
                break;
        }
    }

    private void startProgress() {
        WApplication.getMainHandler().post(ProgressTask);
    }

    private final Runnable ProgressTask = new Runnable() {
        @Override
        public void run() {
            if (videoView != null && videoView.isPlaying()) {
                currentPlayTime += 1;
                int s = currentPlayTime % 60;
                int m = currentPlayTime / 60;
                String second = s > 9 ? String.valueOf(s) : String.valueOf("0" + s);
                String minute = m > 9 ? String.valueOf(m) : String.valueOf("0" + m);
                progressTimeText.setText(minute + ":" + second);
                WApplication.getMainHandler().postDelayed(ProgressTask, 1000);
            }
        }
    };


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }


}
