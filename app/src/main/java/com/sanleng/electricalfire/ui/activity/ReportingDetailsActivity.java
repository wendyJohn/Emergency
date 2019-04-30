package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.electricalfire.Presenter.AlarmRecordRequest;
import com.sanleng.electricalfire.Presenter.ReportingDetailsRequest;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.model.AlarmRecordModel;
import com.sanleng.electricalfire.model.ReportingDetailsModel;
import com.sanleng.electricalfire.ui.adapter.AlarmAdapter;
import com.sanleng.electricalfire.ui.adapter.ReportingDetailsAdapter;
import com.sanleng.electricalfire.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 上报详情
 * @author Qiaoshi
 */
public class ReportingDetailsActivity extends BaseActivity implements View.OnClickListener, ReportingDetailsModel {

    private RelativeLayout r_back;
    private ListView reportinglistview;
    private ReportingDetailsAdapter reportingDetailsAdapter;
    private String ids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportingdetailsactivity);
        initView();

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.reportingdetailsactivity;
    }

    private void initView() {
        Intent intent = getIntent();
        ids = intent.getStringExtra("ids");
        r_back = findViewById(R.id.r_back);
        r_back.setOnClickListener(this);
        ReportingDetailsRequest.getReportingDetails(ReportingDetailsActivity.this,getApplicationContext(),ids);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.r_back:
                finish();
                break;
        }
    }

    @Override
    public void ReportingDetailsSuccess(String patrol_url) {
        String[] arr = patrol_url.split(",");//分割字符串得到数组
        List<String> list = java.util.Arrays.asList(arr);//字符数组转list
        reportinglistview= findViewById(R.id.reportinglistview);
        reportingDetailsAdapter=new ReportingDetailsAdapter(ReportingDetailsActivity.this,list);
        reportinglistview.setAdapter(reportingDetailsAdapter);
    }

    @Override
    public void ReportingDetailsFailed() {

    }
}
