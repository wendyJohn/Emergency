package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanleng.electricalfire.MyApplication;
import com.sanleng.electricalfire.Presenter.HistorRecordRequest;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.ui.adapter.HistorRecordAdapter;
import com.sanleng.electricalfire.ui.bean.HistorRecordBean;
import com.sanleng.electricalfire.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 历史记录
 */
public class HistoricalrecordActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout back;
    private ListView listview;
    private HistorRecordAdapter historRecordAdapter;
    private String mac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historrecord);
        intiView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_historrecord;
    }

    //初始化
    @SuppressLint("WrongViewCast")
    private void intiView() {
        EventBus.getDefault().register(this);
        back = findViewById(R.id.r_back);
        listview= findViewById(R.id.listview);
        back.setOnClickListener(this);
        Intent intent=getIntent();
        mac=intent.getStringExtra("mac");
        HistorRecordRequest.getHistorRecord(HistoricalrecordActivity.this,mac);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.r_back:
                finish();
                break;

        }
    }

    /**
     * 接收EventBus返回数据
     *
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void backData(MessageEvent messageEvent) {
        switch (messageEvent.getTAG()) {
            case MyApplication.MESSAGE_HISTORRECORD:
                List<HistorRecordBean> lists = messageEvent.getLists();
                historRecordAdapter=new HistorRecordAdapter(HistoricalrecordActivity.this,lists);
                listview.setAdapter(historRecordAdapter);
                break;
        }
    }
}
