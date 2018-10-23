package com.ljkj.qxn.wisdomsitepro.ui.application;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ljkj.qxn.wisdomsitepro.R;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.ui.widget.MLoadingDialog;

/**
 * 类描述：全屏视频播放页
 * 创建人：lxx
 * 创建时间：2018/6/13
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class FullScreenVideoActivity extends BaseActivity {
    private static final String KEY_PATH = "key_path";

    @BindView(R.id.video_view)
    VideoView videoView;

    @BindView(R.id.iv_back)
    ImageView backImage;

    public static void startActivity(Context context, String videoPath) {
        Intent intent = new Intent(context, FullScreenVideoActivity.class);
        intent.putExtra(KEY_PATH, videoPath);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_video);
    }

    @Override
    protected void initUI() {
        backImage.setVisibility(View.GONE);
        showLoading();
    }

    private void showLoading() {
        MLoadingDialog.show(this, "视频加载中...", new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                MLoadingDialog.dismiss();
                videoView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 100);
            }
        });
    }

    @Override
    protected void initData() {
        String videoPath = getIntent().getStringExtra(KEY_PATH);
        final MediaController controller = new MediaController(this, false);

        controller.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                backImage.setVisibility(controller.isShowing() ? View.VISIBLE : View.GONE);
                videoView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (backImage != null) {
                            backImage.setVisibility(controller.isShowing() ? View.VISIBLE : View.GONE);
                        }
                    }
                }, 3000);
            }
        });

        videoView.setMediaController(controller);
        controller.setMediaPlayer(videoView);
        videoView.setVideoPath(videoPath);
        videoView.start();

        setListener();
    }

    private void setListener() {
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                MLoadingDialog.dismiss();
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
                MLoadingDialog.dismiss();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!videoView.isPlaying()) {
            showLoading();
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
    protected boolean needStatusBar() {
        return false;
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
