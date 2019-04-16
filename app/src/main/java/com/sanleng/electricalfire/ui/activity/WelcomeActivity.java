package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.sanleng.electricalfire.R;

import me.wangyuwei.particleview.ParticleView;


/**
 * 引导界面
 *
 * @author Qiaoshi
 * @date 创建时间：2018年12月18日
 */

public class WelcomeActivity extends BaseActivity {
    private ParticleView mPv;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.welcome_activity);
        mPv = findViewById(R.id.pv);
        mPv.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
            }
        });
        mPv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPv.startAnim();
            }
        }, 800);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.welcome_activity;
    }
}