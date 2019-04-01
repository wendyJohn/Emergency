package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.ui.adapter.BottomAdapter;
import com.sanleng.electricalfire.ui.fragment.AlarmRecordFragment;
import com.sanleng.electricalfire.ui.fragment.HomeFragment;
import com.sanleng.electricalfire.ui.fragment.MineFragment;
import com.sanleng.electricalfire.ui.fragment.RealTimeDataFragment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private ViewPager mVp;
    private BottomNavigationView mBv;
    static LinearLayout linearLayout;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setupViewPager(mVp);
        // 消息通知栏是否打开
        if (isNotificationEnabled(MainActivity.this) == false) {
            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("消息通知")
                    .setContentText("请点击设置打开通知消息接收")
                    .setConfirmText("设置")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            goToNotificationSetting(MainActivity.this);
                        }
                    })
                    .show();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    //初始化数据
    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.login_delete);
        findViewById(R.id.home_close).setOnClickListener(this);
        findViewById(R.id.home_delete).setOnClickListener(this);
        mBv = (BottomNavigationView) findViewById(R.id.bv);
        mBv.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mVp = (ViewPager) findViewById(R.id.vp);
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mBv.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //禁止ViewPager滑动
//        mVp.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.item_tab1:
                    mVp.setCurrentItem(0);
                    return true;
                case R.id.item_tab2:
                    mVp.setCurrentItem(1);
                    return true;
                case R.id.item_tab3:
                    mVp.setCurrentItem(2);
                    return true;
                case R.id.item_tab4:
                    mVp.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    private void setupViewPager(ViewPager viewPager) {
        BottomAdapter adapter = new BottomAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new RealTimeDataFragment());
        adapter.addFragment(new AlarmRecordFragment());
        adapter.addFragment(new MineFragment());
        viewPager.setAdapter(adapter);
    }
    //判断消息通知栏是否打开
    private boolean isNotificationEnabled(Context context) {
        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
        /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }



    /**
     * 打开允许通知的设置页
     */
    private void goToNotificationSetting(Context context) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 26) {
            // android 8.0引导
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
        } else if (Build.VERSION.SDK_INT >= 21) {
            // android 5.0-7.0
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", context.getPackageName());
            intent.putExtra("app_uid", context.getApplicationInfo().uid);
        } else {
            // 其他
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @SuppressLint("HandlerLeak")
    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            linearLayout.setVisibility(View.VISIBLE);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_close:
                Message msg1 = new Message();
                msg1.what = 1;// 取消
                new HomeFragment().handler.sendMessage(msg1);
                linearLayout.setVisibility(View.GONE);
                break;
            case R.id.home_delete:
                Message msg2 = new Message();
                msg2.what = 2;// 删除
                new HomeFragment().handler.sendMessage(msg2);
                linearLayout.setVisibility(View.GONE);
                break;
        }
    }
}
