package com.sanleng.electricalfire.model;

import android.content.Context;

import com.sanleng.electricalfire.Presenter.AlarmRecordPresenter;
import com.sanleng.electricalfire.Presenter.FireAlarmPresenter;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.AlarmRecord;
import com.sanleng.electricalfire.ui.bean.FireAlarmBean;
import com.sanleng.electricalfire.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlarmRecordRequest {
    //报警记录
    public static void getAlarmRecord(final AlarmRecordPresenter alarmRecordPresenter, final Context context, final String page) {
        final List<Map<String, Object>> list = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对发送请求进行封装
        Call<AlarmRecord> call = request_Interface.getAlarmRecordCall(page, "10", PreferenceUtils.getString(context, "unitcode"), PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner");
        call.enqueue(new Callback<AlarmRecord>() {
            @Override
            public void onResponse(Call<AlarmRecord> call, Response<AlarmRecord> response) {
                int size = response.body().getData().getTotal();
                for (int i = 0; i < response.body().getData().getList().size(); i++) {
                    // 获取数据
                    String build_name = response.body().getData().getList().get(i).getBuild_name();
                    String equipment_name =response.body().getData().getList().get(i).getPosition();
                    String device_name = response.body().getData().getList().get(i).getUnit_name();
                    String receive_time = response.body().getData().getList().get(i).getReceive_time();

                    Map<String, Object> map = new HashMap<>();
                    map.put("name", "设备名称：" + equipment_name);
                    map.put("postion", "设备位置：" + device_name + build_name);
                    map.put("time", "报警时间：" + receive_time);
                    list.add(map);
                }
                alarmRecordPresenter.AlarmRecordSuccess(list, size);
            }

            @Override
            public void onFailure(Call<AlarmRecord> call, Throwable t) {
                alarmRecordPresenter.AlarmRecordFailed();
            }
        });
    }

}
