package com.sanleng.electricalfire.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.loopj.android.http.RequestParams;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.net.NetCallBack;
import com.sanleng.electricalfire.net.RequestUtils;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.util.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 时间轴
 */
public class TimePumpingActivity extends AppCompatActivity {

    private ListView timepumplistview;
    private String deviceid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    //初始化数据
    private void initView() {
        timepumplistview = findViewById(R.id.timepumplistview);

    }

    //获取历史轨迹
    private void addData() {
        RequestParams params = new RequestParams();
        params.put("device_id", deviceid);
        params.put("username", PreferenceUtils.getString(TimePumpingActivity.this, "ElectriFire_username"));
        params.put("platformkey", "app_firecontrol_owner");
        RequestUtils.ClientPost(URLs.RealTimeData_URL, params, new NetCallBack() {
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
            }

            @Override
            public void onMyFailure(Throwable arg0) {
            }
        });
    }
}
