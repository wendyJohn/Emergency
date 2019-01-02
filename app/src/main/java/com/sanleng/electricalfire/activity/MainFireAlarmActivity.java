package com.sanleng.electricalfire.activity;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RadioButton;

import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.fragment.AlarmRecordFragment;
import com.sanleng.electricalfire.fragment.E_RealTimeDataFragment;
import com.sanleng.electricalfire.fragment.NewMineFragment;
import com.sanleng.electricalfire.util.DrawableUtil;

import org.xclcharts.common.DensityUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 智能电气火灾报警系统
 *
 * @author Qaioshi
 */
public class MainFireAlarmActivity extends FragmentActivity {

    private ViewPager viewPager;// 页卡内容
    private List<Fragment> fragments;// Tab页面列表
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度
    private static final int pageSize = 3;
    private RadioButton opa, opb, opc;
    private int mBottomDrawableSize;
    // 默认图片
    private static final int[] TAB_ICON_NORMAL_IDS = new int[]{R.drawable.emergencya_on,
            R.drawable.emergencyb_on, R.drawable.emergencyc_on};
    // 点击图片
    private static final int[] TAB_ICON_ACTIVE_IDS = new int[]{R.drawable.emergencya_in,
            R.drawable.emergencyb_in, R.drawable.emergencyc_in};

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        this.setContentView(R.layout.mainfirealarmactivity);
        initview();
        // 消息通知栏是否打开
        if (isNotificationEnabled(MainFireAlarmActivity.this) == false) {
            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("消息通知")
                    .setContentText("请打开通知消息接收")
                    .setConfirmText("确认")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            Message mymsg = new Message();
                            mymsg.what = 76565;
                            m_handler.sendMessage(mymsg);
                        }
                    })
                    .show();
        }
    }

    private void initview() {
        mBottomDrawableSize = DensityUtil.dip2px(this, 30);
        opa = (RadioButton) findViewById(R.id.opa);
        opb = (RadioButton) findViewById(R.id.opb);
        opc = (RadioButton) findViewById(R.id.opc);
        InitTextView();
        InitViewPager();
        setBottomTextColor(0);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    /**
     * 初始化Viewpager页
     */
    private void InitViewPager() {
        viewPager = (ViewPager) findViewById(R.id.vPager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new E_RealTimeDataFragment());
        fragments.add(new AlarmRecordFragment());
        fragments.add(new NewMineFragment());
        viewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 初始化头标
     */
    private void InitTextView() {
        opa.setOnClickListener(new MyOnClickListener(0));
        opb.setOnClickListener(new MyOnClickListener(1));
        opc.setOnClickListener(new MyOnClickListener(2));
    }

    /**
     * 头标点击监听
     */
    private class MyOnClickListener implements OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            switch (index) {
                case 0:
                    setBottomTextColor(0);
                    break;
                case 1:
                    setBottomTextColor(1);
                    break;
                case 2:
                    setBottomTextColor(2);
                    break;
            }
            viewPager.setCurrentItem(index);
        }
    }

    /**
     * 为选项卡绑定监听器
     */
    public class MyOnPageChangeListener implements OnPageChangeListener {
        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        int two = one * 2;// 页卡1 -> 页卡3 偏移量

        public void onPageScrollStateChanged(int index) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        public void onPageSelected(int index) {
            Animation animation = new TranslateAnimation(one * currIndex, one * index, 0, 0);// 显然这个比较简洁，只有一行代码。
            currIndex = index;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);

            switch (index) {
                case 0:
                    setBottomTextColor(0);
                    break;
                case 1:
                    setBottomTextColor(1);
                    break;
                case 2:
                    setBottomTextColor(2);
                    break;
            }
        }
    }

    /**
     * 定义适配器
     */
    class myPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        public myPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        /**
         * 得到每个页面
         */
        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(arg0);
        }

        /**
         * 每个页面的title
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        /**
         * 页面的总个数
         */
        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }

    private void setBottomTextColor(int position) {
        opa.setTextColor(getResources().getColor(R.color.gray_bold));
        opb.setTextColor(getResources().getColor(R.color.gray_bold));
        opc.setTextColor(getResources().getColor(R.color.gray_bold));
        Drawable d1 = DrawableUtil.getDrawable(MainFireAlarmActivity.this, TAB_ICON_NORMAL_IDS[0]);

        d1.setBounds(0, 0, mBottomDrawableSize, mBottomDrawableSize);
        opa.setCompoundDrawables(null, d1, null, null);
        Drawable d2 = DrawableUtil.getDrawable(MainFireAlarmActivity.this, TAB_ICON_NORMAL_IDS[1]);
        d2.setBounds(0, 0, mBottomDrawableSize, mBottomDrawableSize);
        opb.setCompoundDrawables(null, d2, null, null);
        Drawable d3 = DrawableUtil.getDrawable(MainFireAlarmActivity.this, TAB_ICON_NORMAL_IDS[2]);
        d3.setBounds(0, 0, mBottomDrawableSize, mBottomDrawableSize);
        opc.setCompoundDrawables(null, d3, null, null);
        switch (position) {
            case 0:
                Drawable dz = DrawableUtil.getDrawable(MainFireAlarmActivity.this, TAB_ICON_ACTIVE_IDS[0]);
                dz.setBounds(0, 0, mBottomDrawableSize, mBottomDrawableSize);
                opa.setTextColor(getResources().getColor(R.color.text_blue));
                opa.setCompoundDrawables(null, dz, null, null);
                break;
            case 1:
                Drawable dz1 = DrawableUtil.getDrawable(MainFireAlarmActivity.this, TAB_ICON_ACTIVE_IDS[1]);
                dz1.setBounds(0, 0, mBottomDrawableSize, mBottomDrawableSize);
                opb.setTextColor(getResources().getColor(R.color.text_blue));
                opb.setCompoundDrawables(null, dz1, null, null);
                break;
            case 2:
                Drawable dz2 = DrawableUtil.getDrawable(MainFireAlarmActivity.this, TAB_ICON_ACTIVE_IDS[2]);
                dz2.setBounds(0, 0, mBottomDrawableSize, mBottomDrawableSize);
                opc.setTextColor(getResources().getColor(R.color.text_blue));
                opc.setCompoundDrawables(null, dz2, null, null);
                break;
            default:
                break;
        }

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

    @SuppressLint("HandlerLeak")
    private Handler m_handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 76565:
                    goToNotificationSetting(MainFireAlarmActivity.this);
                    break;
                default:
                    break;
            }

        }
    };

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

}
