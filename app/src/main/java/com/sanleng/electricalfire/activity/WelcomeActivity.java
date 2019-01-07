package com.sanleng.electricalfire.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.sanleng.electricalfire.R;

import me.wangyuwei.particleview.ParticleView;


/**
 * 引导界面
 *
 * @author Qiaoshi
 * @date 创建时间：2018年12月18日
 */

public class WelcomeActivity extends AppCompatActivity {
    private ParticleView mPv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.welcome_activity);
        mPv = (ParticleView) findViewById(R.id.pv);
        mPv.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            }
        });
        mPv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPv.startAnim();
            }
        }, 1000);
    }
}