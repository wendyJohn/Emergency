package com.sanleng.electricalfire;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends MultiDexApplication {
    private static MyApplication instance;
    public static final int MESSREALDATAITEM = 0x111333;
    public static final int MESSRHistoricalTrack = 0x111444;
    public static final int MESSRHistoricalTracks = 0x111555;
    public static final int MESSRWaterSystemStatistics = 0x111666;
    public static final int MESSRWaterSystem = 0x111777;
    public static final int MESSRFireTips = 0x111545;
    public static final int MESSRPENDIND = 0x111666;
    public static final int MESSREALTIMEDATAA = 0x66660;
    public static final int MESSREALTIMEDATAB = 0x66661;
    public static final int MESSREALTIMEDATAC = 0x66662;
    public static final String BROADCAST_PERMISSIONS_DISC = "com.permission.BROADCASTS";
    public static final String BROADCAST_ACTIONS_DISC = "com.permission.broadcasts";


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

