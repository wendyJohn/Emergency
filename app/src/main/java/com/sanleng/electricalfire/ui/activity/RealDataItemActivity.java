package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.Presenter.RealTimeDataRequest;
import com.sanleng.electricalfire.model.RealTimeDataModel;
import com.sanleng.electricalfire.ui.adapter.RealDataItemAdapter;
import com.sanleng.electricalfire.ui.bean.ERealTimeDataBean;
import com.sanleng.electricalfire.ui.bean.ReadTimeItemData;

import java.util.List;

import butterknife.ButterKnife;

/**
 * 智能电气火灾实时数据
 *
 * @author Qiaoshi
 */
public class RealDataItemActivity extends BaseActivity implements OnClickListener, RealTimeDataModel {
    private String mybuildids;
    private String myfloorids;
    private String myelectricalDetectorInfos;
    private String deviceid;
    private RelativeLayout back;
    private ListView dataitemlistviewa;
    private ListView dataitemlistviewb;
    private ListView dataitemlistviewc;
    private ListView dataitemlistviewd;
    private TextView e_name;
    private TextView historicaltrack;
    private TextView pendingdisposal;
    private TextView confirmphoto;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.realdataitemactivitys);
        initView();
        RealTimeDataRequest.getRealDataItem(RealDataItemActivity.this, getApplicationContext(), deviceid);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.realdataitemactivitys;
    }

    //初始化
    private void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        deviceid = intent.getStringExtra("deviceid");
        String name = intent.getStringExtra("name");
        e_name = findViewById(R.id.e_name);
        e_name.setText(name);
        back = findViewById(R.id.back);
        historicaltrack = findViewById(R.id.historicaltrack);
        pendingdisposal = findViewById(R.id.pendingdisposal);
        confirmphoto = findViewById(R.id.confirmphoto);
        back.setOnClickListener(this);
        confirmphoto.setOnClickListener(this);
        pendingdisposal.setOnClickListener(this);
        historicaltrack.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        handler.postDelayed(runnable, 10 * 3000);
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.confirmphoto:
                handler.removeCallbacks(runnable);
                Intent intent_Patrol = new Intent(RealDataItemActivity.this, PatrolActivity.class);
                intent_Patrol.putExtra("buildids", mybuildids);
                intent_Patrol.putExtra("floorids", myfloorids);
                intent_Patrol.putExtra("electricalDetectorInfos", myelectricalDetectorInfos);
                startActivity(intent_Patrol);
                break;
            case R.id.pendingdisposal:
                handler.removeCallbacks(runnable);
                Intent intents = new Intent(RealDataItemActivity.this, PendingActivity.class);
                intents.putExtra("deviceid", deviceid);
                startActivity(intents);
                break;
            case R.id.historicaltrack:
                handler.removeCallbacks(runnable);
                Intent intent = new Intent(RealDataItemActivity.this, TimePumpingActivity.class);
                intent.putExtra("deviceid", deviceid);
                startActivity(intent);
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
            RealTimeDataRequest.getRealDataItem(RealDataItemActivity.this, getApplicationContext(), deviceid);
            handler.postDelayed(this, 10 * 3000);//实现循环
        }
    };
    private Handler handler = new Handler();

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    @Override
    public void RealTimeDataSuccess(List<ERealTimeDataBean> list, int size) {

    }

    @Override
    public void RealTimeDatasSuccess(List<ERealTimeDataBean> list, int size) {

    }

    @Override
    public void RealDataItemSuccess(List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_temperature, List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_current,
                                    List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_residualcurrent, List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_voltage, String buildids, String floorids, String electricalDetectorInfos) {
        mybuildids = buildids;
        myfloorids = floorids;
        myelectricalDetectorInfos = electricalDetectorInfos;
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
        RealDataItemAdapter realDataItemAdapterd = new RealDataItemAdapter(RealDataItemActivity.this, list_voltage);
        dataitemlistviewd.setAdapter(realDataItemAdapterd);
    }

    @Override
    public void RealTimeDataFailed() {
        new SVProgressHUD(RealDataItemActivity.this).showErrorWithStatus("数据请求失败");
    }


}
