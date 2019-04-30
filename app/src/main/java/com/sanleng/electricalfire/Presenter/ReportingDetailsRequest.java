package com.sanleng.electricalfire.Presenter;

import android.content.Context;

import com.sanleng.electricalfire.model.AlarmloadModel;
import com.sanleng.electricalfire.model.ReportingDetailsModel;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.Alarmload;
import com.sanleng.electricalfire.ui.bean.ReportingDetailsBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReportingDetailsRequest {
    public static void getReportingDetails(final ReportingDetailsModel reportingDetailsmodel, final Context context, final String ids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<ReportingDetailsBean> call = request_Interface.getReportingDetailsCall("1","1",ids,"fire_alarm" ,"admin", "app_firecontrol_owner");
        call.enqueue(new Callback<ReportingDetailsBean>() {
            @Override
            public void onResponse(Call<ReportingDetailsBean> call, Response<ReportingDetailsBean> response) {
                response.body().getData().getList().size();
                for (int i = 0; i <response.body().getData().getList().size() ; i++) {
                    String patrol_url=response.body().getData().getList().get(i).getPatrol_url();
                    reportingDetailsmodel.ReportingDetailsSuccess(patrol_url);
                }
            }

            @Override
            public void onFailure(Call<ReportingDetailsBean> call, Throwable t) {
                reportingDetailsmodel.ReportingDetailsFailed();
            }
        });

    }
}