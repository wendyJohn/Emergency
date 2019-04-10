package com.sanleng.electricalfire.model;

import android.content.Context;

import com.sanleng.electricalfire.Presenter.AlarmRecordPresenter;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.AlarmRecord;
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

public class AlarmUpdateRequest {
    //报警记录
    public static void getAlarmUpdate(final Context context, final String ids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对发送请求进行封装
        Call<String> call = request_Interface.getAlarmUpdateCall(ids, "0", PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}
