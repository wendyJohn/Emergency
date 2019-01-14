package com.sanleng.electricalfire.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.loopj.android.http.RequestParams;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.adapter.PendingAdapter;
import com.sanleng.electricalfire.adapter.TimePumpAdapter;
import com.sanleng.electricalfire.net.NetCallBack;
import com.sanleng.electricalfire.net.RequestUtils;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.util.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 待处理
 */
public class PendingActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView pendinglistview;
    private RelativeLayout back;
    private String deviceid;
    private List<HashMap<String, Object>> mylist;
    private PendingAdapter pendingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pendingactivity);
        initView();
        addData();
    }

    //初始化数据
    private void initView() {
        mylist = new ArrayList<>();
        pendinglistview = findViewById(R.id.pendinglistview);
        back = findViewById(R.id.task_ac_back);
        back.setOnClickListener(this);
    }

    //获取隐患事件
    private void addData() {
        Intent intent = getIntent();
        deviceid = intent.getStringExtra("deviceid");
        RequestParams params = new RequestParams();
        params.put("deviceId", deviceid);
        params.put("username", PreferenceUtils.getString(PendingActivity.this, "ElectriFire_username"));
        params.put("platformkey", "app_firecontrol_owner");
        RequestUtils.ClientPost(URLs.TIMEPUMP_URL, params, new NetCallBack() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onMySuccess(String result) {
                if (result == null || result.length() == 0) {
                    return;
                }
                System.out.println("数据请求成功" + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String msg = jsonObject.getString("msg");
                    if (msg.equals("获取成功！")) {
                        String data = jsonObject.getString("data");
                        JSONArray array = new JSONArray(data);
                        JSONObject object;
                        for (int i = 0; i < array.length(); i++) {
                            HashMap<String, Object> map = new HashMap<>();
                            object = (JSONObject) array.get(i);
                            String state = object.getString("state");
                            if (state.equals("1")) {
                                String point_id = object.getString("point_id");
                                String build_name = object.getString("build_name");
                                String device_name = object.getString("device_name");
                                String receive_time = object.getString("receive_time");
                                String detector_name = object.getString("detector_name");
                                String ids = object.getString("ids");

                                map.put("point_id", point_id);
                                map.put("name", detector_name);
                                map.put("postion", build_name + device_name);
                                map.put("time", receive_time);
                                map.put("ids", ids);
                                mylist.add(map);
                            }
                        }
                        pendinglistview.setEmptyView(findViewById(R.id.nodata));
                        pendingAdapter = new PendingAdapter(PendingActivity.this, mylist, mHandler);
                        pendinglistview.setAdapter(pendingAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onMyFailure(Throwable arg0) {
            }
        });
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message message) {
            final Bundle data = message.getData();
            switch (message.what) {
                // 立即处理
                case 2552555:
                    int selIndex = data.getInt("selIndex");
                    String ids = mylist.get(selIndex).get("ids").toString();
                    String point_id = mylist.get(selIndex).get("point_id").toString();
                    Intent intent = new Intent(PendingActivity.this, RectificationitemActivity.class);
                    intent.putExtra("ids", ids);
                    intent.putExtra("point_id", point_id);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
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
}
