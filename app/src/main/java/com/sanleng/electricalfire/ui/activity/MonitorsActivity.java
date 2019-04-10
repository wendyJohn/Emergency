package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.RelativeLayout;


import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.myview.FullVideoView;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 视频监控
 */
public class MonitorsActivity extends BaseActivity {
    private RelativeLayout r_back;
    private RelativeLayout ryout;
    private FullVideoView videoa;
    private FullVideoView videob;
    private boolean fullscreen;
    private int orientation;
    private String channel_two;
    private String channel_one;
    private int i = -1;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitorsactivity);
        initview();

        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("正在加载...");
        pDialog.show();
        pDialog.setCancelable(false);
        new CountDownTimer(800 * 10, 800) {
            public void onTick(long millisUntilFinished) {
                // you can change the progress bar color by ProgressHelper every 800 millis
                i++;
                switch (i) {
                    case 0:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.blue_btn_bg_color));
                        break;
                    case 1:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_50));
                        break;
                    case 2:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                        break;
                    case 3:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_deep_teal_20));
                        break;
                    case 4:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.material_blue_grey_80));
                        break;
                    case 5:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.warning_stroke_color));
                        break;
                    case 6:
                        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.success_stroke_color));
                        break;
                }
            }

            public void onFinish() {
                i = -1;
                pDialog.cancel();
            }
        }.start();
    }

    private void initview() {
        r_back = findViewById(R.id.r_back);
        ryout = findViewById(R.id.ryout);
        r_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        videoa = findViewById(R.id.videoa);
        videob = findViewById(R.id.videob);
//        Intent inten = getIntent();
//        channel_one = inten.getStringExtra("channel_one");
//        channel_two = inten.getStringExtra("channel_two");
        setVideoa();
        setVideob();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.monitorsactivity;
    }

    /**
     * 设置视频参数
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setVideoa() {
        MediaController mediaController = new MediaController(this);
        mediaController.setVisibility(View.GONE);//隐藏进度条
        videoa.setMediaController(mediaController);
        videoa.setVideoURI(Uri.parse("http://hls.open.ys7.com/openlive/0914d875ae71473ca7dad4edd27690e8.m3u8"));
        videoa.start();
        videoa.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });

        videoa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setOratation("VIDEO_A");
                return false;
            }
        });
    }


    /**
     * 保存 旋转 之前的 数据
     *
     * @param outState
     */
    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        int sec = outState.getInt("time");
        videoa.seekTo(sec);
        super.onRestoreInstanceState(outState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        int sec = videoa.getCurrentPosition();
        outState.putInt("time", sec);
        super.onSaveInstanceState(outState);
    }


    /**
     * 横竖屏
     */
    public void setOratation(String str) {
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        if (width > height) {
            ryout.setVisibility(View.VISIBLE);
            orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;  //竖屏
            if (str.equals("VIDEO_A")) {
                //待调试
                setVideob();
                videob.setVisibility(View.VISIBLE);
            }
            if (str.equals("VIDEO_B")) {
                //待调试
                setVideoa();
                videoa.setVisibility(View.VISIBLE);
            }
        } else {
            ryout.setVisibility(View.GONE);
            orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横屏
            if (str.equals("VIDEO_A")) {
                videob.setVisibility(View.GONE);
            }
            if (str.equals("VIDEO_B")) {
                videoa.setVisibility(View.GONE);
            }
        }
        this.setRequestedOrientation(orientation);
    }

    /**
     * 设置视频参数
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setVideob() {
        MediaController mediaController = new MediaController(this);
        mediaController.setVisibility(View.GONE);//隐藏进度条
        videob.setMediaController(mediaController);
        videob.setVideoURI(Uri.parse("http://hls.open.ys7.com/openlive/ff814ef5eacf479c8ee045a24fbc21a7.m3u8"));
        videob.start();
        videob.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
        });

        videob.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setOratation("VIDEO_B");
                return false;
            }
        });
    }
}