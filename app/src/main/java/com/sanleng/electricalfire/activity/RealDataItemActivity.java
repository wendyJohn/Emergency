package com.sanleng.electricalfire.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.sanleng.electricalfire.MyApplication;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.adapter.RealDataItemAdapter;
import com.sanleng.electricalfire.bean.ReadTimeItemData;
import com.sanleng.electricalfire.model.RealDataItemRequest;
import com.sanleng.electricalfire.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;

/**
 * 智能电气火灾实时数据
 *
 * @author Qiaoshi
 */
public class RealDataItemActivity extends AppCompatActivity implements OnClickListener {
    List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_temperature = new ArrayList<>();
    List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_current = new ArrayList<>();
    List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_residualcurrent = new ArrayList<>();
    List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_voltage = new ArrayList<>();
    String buildids;
    String floorids;
    String electricalDetectorInfos;
    String deviceid;
    RelativeLayout back;
    ListView dataitemlistviewa;
    ListView dataitemlistviewb;
    ListView dataitemlistviewc;
    ListView dataitemlistviewd;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.realdataitemactivity);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(RealDataItemActivity.this, R.color.translucency);
        EventBus.getDefault().register(this);
        initView();
    }

    //初始化
    private void initView() {
        Intent intent = getIntent();
        deviceid = intent.getStringExtra("deviceid");
        new RealDataItemRequest(RealDataItemActivity.this).getRealDataItem(deviceid);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        handler.postDelayed(runnable, 10 * 3000);
        super.onResume();
    }


    // 声明一个订阅方法，用于接收事件
    @Subscribe
    public void onEvent(MessageEvent messageEvent) {
        switch (messageEvent.getTAG()) {
            case MyApplication.MESSREALDATAITEM:
                list_temperature = messageEvent.getList_temperature();
                list_current = messageEvent.getList_current();
                list_residualcurrent = messageEvent.getList_residualcurrent();
                list_voltage = messageEvent.getList_voltage();
                buildids = messageEvent.getBuildids();
                floorids = messageEvent.getFloorids();
                electricalDetectorInfos = messageEvent.getElectricalDetectorInfos();
                dataitemlistviewa = findViewById(R.id.dataitemlistviewa);
                dataitemlistviewb = findViewById(R.id.dataitemlistviewb);
                dataitemlistviewc = findViewById(R.id.dataitemlistviewc);
                dataitemlistviewd = findViewById(R.id.dataitemlistviewd);
                RealDataItemAdapter realDataItemAdaptera = new RealDataItemAdapter(RealDataItemActivity.this, list_temperature);
                dataitemlistviewa.setAdapter(realDataItemAdaptera);

                RealDataItemAdapter realDataItemAdapterb = new RealDataItemAdapter(RealDataItemActivity.this, list_residualcurrent);
                dataitemlistviewb.setAdapter(realDataItemAdapterb);

                RealDataItemAdapter realDataItemAdapterc = new RealDataItemAdapter(RealDataItemActivity.this, list_current);
                dataitemlistviewc.setAdapter(realDataItemAdapterc);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //要做的事情
            new RealDataItemRequest(RealDataItemActivity.this).getRealDataItem(deviceid);
            handler.postDelayed(this, 10 * 3000);//实现循环
        }
    };
    private Handler handler = new Handler();

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this); // 解绑
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }


}
