package com.sanleng.electricalfire;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends MultiDexApplication {
    private static MyApplication instance;

    public static final int MESSLOGIN = 0x123123;
    public static final int MESSREALTIMEDATA = 0x111222;
    public static final int MESSREALDATAITEM = 0x111333;
    public static final int MESSREALTIMEDATAA = 0x66660;
    public static final int MESSREALTIMEDATAB = 0x66661;
    public static final int MESSREALTIMEDATAC = 0x66662;



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
        JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this); // 初始化 JPush
    }
}

