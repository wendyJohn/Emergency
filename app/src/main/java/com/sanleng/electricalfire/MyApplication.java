package com.sanleng.electricalfire;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.baidu.mapapi.SDKInitializer;

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
    public static final int MESSAGE_HISTORRECORD = 0x219988;
    public static final String BROADCAST_PERMISSIONS_DISC = "com.permission.BROADCASTS";
    public static final String BROADCAST_ACTIONS_DISC = "com.permission.broadcasts";
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 0x1231232;

    public static final int MSGHANDLE = 0x121111;
    public static final int MSGViewMonitoring = 0x121112;
    public static final int MSGViewlocation = 0x121113;
    public static final int MSGConfirmSuccess = 0x121114;
    public static final int MSGConfirmFailure = 0x121115;
    public static final int MSGFiretelePhone = 0x662360;
    public static final int MSGReportingDetails = 0x66556;
    public static final int MSGProcessingReport = 0x62356;
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
        SDKInitializer.initialize(this);
    }

    public static Context getAppContext() {
        return instance;
    }
}

