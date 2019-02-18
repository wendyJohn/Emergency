package com.sanleng.electricalfire.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.fragment.AlarmRecordFragment;
import com.sanleng.electricalfire.fragment.HomeFragment;
import com.sanleng.electricalfire.fragment.NewMineFragment;
import com.sanleng.electricalfire.fragment.RealTimeDataFragment;

import java.util.ArrayList;
import java.util.List;

public class MainTabActivity extends FragmentActivity implements View.OnClickListener {
    //声明ViewPager
    private ViewPager mViewPager;
    //适配器
    private FragmentPagerAdapter mAdapter;
    //装载Fragment的集合
    private List<Fragment> mFragments;

    //四个Tab对应的布局
    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSetting;

    //四个Tab对应的ImageButton
    private ImageButton mImgWeixin;
    private ImageButton mImgFrd;
    private ImageButton mImgAddress;
    private ImageButton mImgSetting;
    //四个Tab对应的TextView
    private TextView texttab_a;
    private TextView texttab_b;
    private TextView texttab_c;
    private TextView texttab_d;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_maintab);
        StatusBarUtil.setColor(MainTabActivity.this,R.color.translucency);
        initViews();//初始化控件
        initEvents();//初始化事件
        initDatas();//初始化数据
    }

    private void initDatas() {
        mFragments = new ArrayList<>();
        //将四个Fragment加入集合中
        mFragments.add(new HomeFragment());
        mFragments.add(new RealTimeDataFragment());
        mFragments.add(new AlarmRecordFragment());
        mFragments.add(new NewMineFragment());

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
        mTabAddress.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);

    }

    //初始化控件
    private void initViews() {
        mViewPager = findViewById(R.id.id_viewpager);

        mTabWeixin = findViewById(R.id.id_tab_weixin);
        mTabFrd = findViewById(R.id.id_tab_frd);
        mTabAddress = findViewById(R.id.id_tab_address);
        mTabSetting = findViewById(R.id.id_tab_setting);

        mImgWeixin = findViewById(R.id.id_tab_weixin_img);
        mImgFrd = findViewById(R.id.id_tab_frd_img);
        mImgAddress = findViewById(R.id.id_tab_address_img);
        mImgSetting = findViewById(R.id.id_tab_setting_img);
        texttab_a = findViewById(R.id.texttab_a);
        texttab_b = findViewById(R.id.texttab_b);
        texttab_c = findViewById(R.id.texttab_c);
        texttab_d = findViewById(R.id.texttab_d);
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
                texttab_c.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                texttab_d.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                break;
            case 1:
                mImgFrd.setImageResource(R.drawable.emergencyb_in);
                texttab_a.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                texttab_b.setTextColor(MainTabActivity.this.getResources().getColor(R.color.text_blue));
                texttab_c.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                texttab_d.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                break;
            case 2:
                mImgAddress.setImageResource(R.drawable.emergencyc_in);
                texttab_a.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                texttab_b.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                texttab_c.setTextColor(MainTabActivity.this.getResources().getColor(R.color.text_blue));
                texttab_d.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                break;
            case 3:
                mImgSetting.setImageResource(R.drawable.emergencyd_in);
                texttab_a.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                texttab_b.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
                texttab_c.setTextColor(MainTabActivity.this.getResources().getColor(R.color.gray));
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
        mImgAddress.setImageResource(R.drawable.emergencyc_on);
        mImgSetting.setImageResource(R.drawable.emergencyd_on);
    }
}
