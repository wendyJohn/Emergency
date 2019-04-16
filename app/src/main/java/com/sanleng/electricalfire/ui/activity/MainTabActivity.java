package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.electricalfire.MyApplication;
import com.sanleng.electricalfire.Presenter.UpdateRequest;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.data.Version_mag;
import com.sanleng.electricalfire.dialog.CustomDialog;
import com.sanleng.electricalfire.dialog.FireTipsDialog;
import com.sanleng.electricalfire.model.UpdateModel;
import com.sanleng.electricalfire.service.UpdateService;
import com.sanleng.electricalfire.ui.fragment.EventFragment;
import com.sanleng.electricalfire.ui.fragment.HomeFragment;
import com.sanleng.electricalfire.ui.fragment.MapMonitoringFragment;
import com.sanleng.electricalfire.ui.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainTabActivity extends FragmentActivity implements View.OnClickListener, UpdateModel {
    //声明ViewPager
    private ViewPager mViewPager;
    //适配器
    private FragmentPagerAdapter mAdapter;
    //装载Fragment的集合
    private List<Fragment> mFragments;

    //四个Tab对应的布局
    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
//    private LinearLayout mTabAddress;
    private LinearLayout mTabSetting;

    //四个Tab对应的ImageButton
    private ImageButton mImgWeixin;
    private ImageButton mImgFrd;
//    private ImageButton mImgAddress;
    private ImageButton mImgSetting;
    //四个Tab对应的TextView
    private TextView texttab_a;
    private TextView texttab_b;
//    private TextView texttab_c;
    private TextView texttab_d;
    static LinearLayout linearLayout;
    private Receivers receivers;
    public static final String ROUTE_PLAN_NODE = "routePlanNode";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_maintab);
        initViews();//初始化控件
        initEvents();//初始化事件
        initDatas();//初始化数据

        UpdateRequest.GetUpdate(MainTabActivity.this, getApplicationContext(), Version_mag.ostype, Version_mag.platformkey);
    }

    private void initDatas() {
        mFragments = new ArrayList<>();
        //将四个Fragment加入集合中
        mFragments.add(new HomeFragment());
        mFragments.add(new MapMonitoringFragment());
//        mFragments.add(new EventFragment());
        mFragments.add(new MineFragment());

        //初始化适配器
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {//从集合中获取对应位置的Fragment
                return mFragments.get(position);
            }

            @Override
            public int getCount() {//获取集合中Fragment的总数
                return mFragments.size();
            }

        };
        //不要忘记设置ViewPager的适配器
        mViewPager.setAdapter(mAdapter);
        //设置ViewPager的切换监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            //页面滚动事件
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //页面选中事件
            @Override
            public void onPageSelected(int position) {
                //设置position对应的集合中的Fragment
                mViewPager.setCurrentItem(position);
                resetImgs();
                selectTab(position);
            }

            @Override
            //页面滚动状态改变事件
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initEvents() {
        //设置四个Tab的点击事件
        mTabWeixin.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
//        mTabAddress.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);

    }

    //初始化控件
    @SuppressLint("ClickableViewAccessibility")
    private void initViews() {
        linearLayout = findViewById(R.id.login_delete);
        findViewById(R.id.home_close).setOnClickListener(this);
        findViewById(R.id.home_delete).setOnClickListener(this);
        mViewPager = findViewById(R.id.id_viewpager);
        // 禁止ViewPager滑动
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mTabWeixin = findViewById(R.id.id_tab_weixin);
        mTabFrd = findViewById(R.id.id_tab_frd);
//        mTabAddress = findViewById(R.id.id_tab_address);
        mTabSetting = findViewById(R.id.id_tab_setting);

        mImgWeixin = findViewById(R.id.id_tab_weixin_img);
        mImgFrd = findViewById(R.id.id_tab_frd_img);
//        mImgAddress = findViewById(R.id.id_tab_address_img);
        mImgSetting = findViewById(R.id.id_tab_setting_img);
        texttab_a = findViewById(R.id.texttab_a);
        texttab_b = findViewById(R.id.texttab_b);
//        texttab_c = findViewById(R.id.texttab_c);
        texttab_d = findViewById(R.id.texttab_d);

        receivers = new Receivers();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyApplication.BROADCAST_ACTIONS_DISC); // 只有持有相同的action的接受者才能接收此广
        registerReceiver(receivers, intentFilter, MyApplication.BROADCAST_PERMISSIONS_DISC, null);
    }

    @Override
    public void onClick(View v) {
        //先将四个ImageButton置为灰色
        resetImgs();
        //根据点击的Tab切换不同的页面及设置对应的ImageButton为绿色
        switch (v.getId()) {
            case R.id.id_tab_weixin:
                selectTab(0);
                break;
            case R.id.id_tab_frd:
                selectTab(1);
                break;
            case R.id.id_tab_address:
                selectTab(2);
                break;
            case R.id.id_tab_setting:
                selectTab(3);
                break;
            case R.id.home_close:
                Message msg1 = new Message();
                msg1.what = 1;// qux
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

    @SuppressLint("ResourceAsColor")
    private void selectTab(int i) {
        //根据点击的Tab设置对应的ImageButton为蓝色
        switch (i) {
            case 0:
                mImgWeixin.setImageResource(R.drawable.emergencya_in);
                texttab_a.setTextColor(MainTabActivity.this.getResources().getColor(R.color.text_blue));
                texttab_b.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
//                texttab_c.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                texttab_d.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                break;
            case 1:
                mImgFrd.setImageResource(R.drawable.emergencyb_in);
                texttab_a.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                texttab_b.setTextColor(MainTabActivity.this.getResources().getColor(R.color.text_blue));
//                texttab_c.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                texttab_d.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                break;
//            case 2:
//                mImgAddress.setImageResource(R.drawable.emergencyc_in);
//                texttab_a.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
//                texttab_b.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
//                texttab_c.setTextColor(MainTabActivity.this.getResources().getColor(R.color.text_blue));
//                texttab_d.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
//                break;
            case 2:
                mImgSetting.setImageResource(R.drawable.emergencyd_in);
                texttab_a.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                texttab_b.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
//                texttab_c.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                texttab_d.setTextColor(MainTabActivity.this.getResources().getColor(R.color.text_blue));
                break;
        }
        //设置当前点击的Tab所对应的页面
        mViewPager.setCurrentItem(i);
    }

    //将四个ImageButton设置为灰色
    @SuppressLint("ResourceAsColor")
    private void resetImgs() {
        mImgWeixin.setImageResource(R.drawable.emergencya_on);
        mImgFrd.setImageResource(R.drawable.emergencyb_on);
//        mImgAddress.setImageResource(R.drawable.emergencyc_on);
        mImgSetting.setImageResource(R.drawable.emergencyd_on);
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
    public void UpdateSuccess(String version, final String path, String appDescribe) {
        int versions = Integer.parseInt(version);
        if (versions > getLocalVersion(MainTabActivity.this)) {
            // 是否更新
            CustomDialog.Builder builder = new CustomDialog.Builder(this);
            String messageitems = "更新内容如下：" + appDescribe;
            builder.setMessage(messageitems);
            builder.setTitle("检测到新的版本信息");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Intent i = new Intent(MainTabActivity.this, UpdateService.class);
                    i.putExtra("apkurl", "https://slyj.slicity.com" + path);
                    startService(i);
                    new SVProgressHUD(MainTabActivity.this).showWithStatus("版本正在更新...");
                }
            });
            builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }

    }


    @Override
    public void UpdateFailed() {
        new SVProgressHUD(MainTabActivity.this).showErrorWithStatus("更新失败");
    }

    // 收到报警广播处理，刷新界面
    public class Receivers extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(MyApplication.BROADCAST_ACTIONS_DISC)) {
                String str = intent.getStringExtra("str_test");
                FireTipsDialog fireTipsDialog = new FireTipsDialog(context, str);
                Window window = fireTipsDialog.getWindow();
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.gravity = Gravity.TOP;
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                window.setAttributes(layoutParams);
                fireTipsDialog.show();
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receivers);
    }

    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

}
