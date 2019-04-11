package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.electricalfire.Presenter.HistoricalTrackRequest;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.model.HistoricalTrackModel;
import com.sanleng.electricalfire.ui.adapter.PendingAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 待处理
 */
public class PendingActivity extends BaseActivity implements View.OnClickListener ,HistoricalTrackModel {
    private ListView pendinglistview;
    private RelativeLayout back;
    private String deviceid;
    private List<HashMap<String, Object>> mylist = new ArrayList<>();
    private PendingAdapter pendingAdapter;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pendingactivity);
        initView();
        HistoricalTrackRequest.getHistoricalTrack(PendingActivity.this,getApplicationContext(),deviceid);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.pendingactivity;
    }

    //初始化数据
    private void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        deviceid = intent.getStringExtra("deviceid");
        pendinglistview = findViewById(R.id.pendinglistview);
        back = findViewById(R.id.task_ac_back);
        back.setOnClickListener(this);
    }

    private PendingAdapter.Control control = new PendingAdapter.Control() {
        @Override
        public void getPosition(int position) {
            // TODO Auto-generated method stub
            String ids = mylist.get(position).get("ids").toString();
            String point_id = mylist.get(position).get("point_id").toString();
            Intent intent = new Intent(PendingActivity.this, RectificationitemActivity.class);
            intent.putExtra("ids", ids);
            intent.putExtra("point_id", point_id);
            startActivity(intent);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.task_ac_back:
                finish();
                break;
        }
    }

    @Override
    public void HistoricalTrackSuccess(List<HashMap<String, Object>> c_list, List<HashMap<String, Object>> h_list) {
        mylist = c_list;
        pendingAdapter = new PendingAdapter(PendingActivity.this,control,mylist);
        pendinglistview.setAdapter(pendingAdapter);
    }

    @Override
    public void HistoricalTrackFailed() {
        new SVProgressHUD(PendingActivity.this).showErrorWithStatus("数据加载失败");
    }
}
