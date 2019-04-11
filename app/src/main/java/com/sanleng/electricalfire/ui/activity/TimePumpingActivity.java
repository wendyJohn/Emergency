package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.Presenter.HistoricalTrackRequest;
import com.sanleng.electricalfire.model.HistoricalTrackModel;
import com.sanleng.electricalfire.ui.adapter.TimePumpAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 时间轴
 */
public class TimePumpingActivity extends BaseActivity implements HistoricalTrackModel {
    private ListView timepumplistview;
    private String deviceid;
    private TimePumpAdapter timePumpAdapter;
    private List<HashMap<String, Object>> mylist=new ArrayList<>();
    private RelativeLayout task_ac_back;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timepumpactivity);
        initView();
        HistoricalTrackRequest.getHistoricalTrack(TimePumpingActivity.this,getApplicationContext(),deviceid);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.timepumpactivity;
    }

    //初始化数据
    private void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        deviceid = intent.getStringExtra("deviceid");
        timepumplistview = findViewById(R.id.timepumplistview);
        task_ac_back = findViewById(R.id.task_ac_back);
        task_ac_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void HistoricalTrackSuccess(List<HashMap<String, Object>> c_list, List<HashMap<String, Object>> h_list) {
        mylist = h_list;
        timePumpAdapter = new TimePumpAdapter(TimePumpingActivity.this, mylist);
        timepumplistview.setAdapter(timePumpAdapter);
    }

    @Override
    public void HistoricalTrackFailed() {
        new SVProgressHUD(TimePumpingActivity.this).showErrorWithStatus("数据加载失败");
    }
}
