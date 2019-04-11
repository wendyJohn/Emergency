package com.sanleng.electricalfire.Presenter;

import android.content.Context;

import com.sanleng.electricalfire.model.FireConfirmationModel;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FireConfirmationRequest {
    //登录
    public static void GetFireConfirmation(final FireConfirmationModel fireConfirmationModel, final Context context, final String ids, final String alarmtypefinal, String username, final String platformkey) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<String> call = request_Interface.getFireConfirmationCall(ids,alarmtypefinal ,username, platformkey);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                fireConfirmationModel.FireConfirmationSuccess(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                fireConfirmationModel.FireConfirmationFailed();
            }
        });

    }
}