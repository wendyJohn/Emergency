package com.sanleng.electricalfire.model;

import android.content.Context;

import com.sanleng.electricalfire.Presenter.RescuePresenter;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.Rescue;
import com.sanleng.electricalfire.util.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RescueRequest {
    //附件应急站
    public static void getRescue(final RescuePresenter rescuePresenter, final Context context, final String lat, final String lng, final String name, final String phone, final String identitycrad, final String type, final String address) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对发送请求进行封装
        Call<Rescue> call = request_Interface.getRescueCall(lat, lng, name, phone, identitycrad, type, address, PreferenceUtils.getString(context, "unitcode"), PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner");
        call.enqueue(new Callback<Rescue>() {
            @Override
            public void onResponse(Call<Rescue> call, Response<Rescue> response) {
                rescuePresenter.RescueSuccess(response.body().getMsg().toString());
            }

            @Override
            public void onFailure(Call<Rescue> call, Throwable t) {
                rescuePresenter.RescueFailed();
            }
        });
    }

}
