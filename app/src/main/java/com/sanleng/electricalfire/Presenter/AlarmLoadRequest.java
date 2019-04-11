package com.sanleng.electricalfire.Presenter;

import android.content.Context;

import com.sanleng.electricalfire.model.AlarmloadModel;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.Alarmload;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlarmLoadRequest {
    public static void GetAlarmLoad(final AlarmloadModel alarmloadmodel, final Context context, final String unitcode, final String username, final String platformkey) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<Alarmload> call = request_Interface.getAlarmLoadCall(unitcode, username, platformkey);
        call.enqueue(new Callback<Alarmload>() {
            @Override
            public void onResponse(Call<Alarmload> call, Response<Alarmload> response) {
                alarmloadmodel.AlarmloadSuccess(response.body().getUnhandlefire(), response.body().getTodayfire(), response.body().getTruefire(), response.body().getMissfire(), response.body().getWeekfire());
            }

            @Override
            public void onFailure(Call<Alarmload> call, Throwable t) {
                alarmloadmodel.AlarmloadFailed();
            }
        });

    }
}