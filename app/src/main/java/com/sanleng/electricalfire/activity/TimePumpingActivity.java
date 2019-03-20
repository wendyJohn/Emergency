package com.sanleng.electricalfire.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.jaeger.library.StatusBarUtil;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.adapter.TimePumpAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * 时间轴
 */
public class TimePumpingActivity extends AppCompatActivity {

    private ListView timepumplistview;
    private String deviceid;
    private TimePumpAdapter timePumpAdapter;
    private List<HashMap<String, Object>> mylist;
    private RelativeLayout task_ac_back;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timepumpactivity);
        StatusBarUtil.setColor(TimePumpingActivity.this,R.color.translucency);
        initView();
    }

    //初始化数据
    private void initView() {
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
    protected void onResume() {
//        addData();
        super.onResume();
    }

    //获取历史轨迹
//    private void addData() {
//        mylist = new ArrayList<>();
//        Intent intent = getIntent();
//        deviceid = intent.getStringExtra("deviceid");
//        RequestParams params = new RequestParams();
//        params.put("deviceId", deviceid);
//        params.put("username", PreferenceUtils.getString(TimePumpingActivity.this, "ElectriFire_username"));
//        params.put("platformkey", "app_firecontrol_owner");
//        RequestUtils.ClientPost(URLs.TIMEPUMP_URL, params, new NetCallBack() {
//            @Override
//            public void onStart() {
//                super.onStart();
//            }
//
//            @Override
//            public void onMySuccess(String result) {
//                if (result == null || result.length() == 0) {
//                    return;
//                }
//                System.out.println("数据请求成功" + result);
//                try {
//                    JSONObject jsonObject = new JSONObject(result);
//                    String msg = jsonObject.getString("msg");
//                    if (msg.equals("获取成功！")) {
//                        String data = jsonObject.getString("data");
//                        JSONArray array = new JSONArray(data);
//                        JSONObject object;
//                        for (int i = 0; i < array.length(); i++) {
//                            HashMap<String, Object> map = new HashMap<>();
//                            object = (JSONObject) array.get(i);
//                            String receive_time = object.getString("receive_time");
//                            String detector_name = object.getString("detector_name");
//                            map.put("time", receive_time);
//                            map.put("content", detector_name);
//                            mylist.add(map);
//                        }
//                        timepumplistview.setDividerHeight(0);
//                        timepumplistview.setEmptyView(findViewById(R.id.nodata));
//                        timePumpAdapter = new TimePumpAdapter(TimePumpingActivity.this, mylist);
//                        timepumplistview.setAdapter(timePumpAdapter);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onMyFailure(Throwable arg0) {
//            }
//        });
//    }
}
