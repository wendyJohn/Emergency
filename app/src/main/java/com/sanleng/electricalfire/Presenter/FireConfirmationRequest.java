package com.sanleng.electricalfire.Presenter;

import android.content.Context;

import com.sanleng.electricalfire.model.FireConfirmationModel;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.ConfirmFire;
import com.sanleng.electricalfire.util.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FireConfirmationRequest {
    //火灾确认
    public static void GetFireConfirmation(final FireConfirmationModel fireConfirmationModel, final Context context, final String ids, final String alarmtypefinal) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<ConfirmFire> call = request_Interface.getFireConfirmationCall(ids,alarmtypefinal ,PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner");
        call.enqueue(new Callback<ConfirmFire>() {
            @Override
            public void onResponse(Call<ConfirmFire> call, Response<ConfirmFire> response) {
                fireConfirmationModel.FireConfirmationSuccess(response.body().getMsg());
            }

            @Override
            public void onFailure(Call<ConfirmFire> call, Throwable t) {
                fireConfirmationModel.FireConfirmationFailed();
            }
        });

    }
}