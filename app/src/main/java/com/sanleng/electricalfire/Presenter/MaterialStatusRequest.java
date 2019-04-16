package com.sanleng.electricalfire.Presenter;

import android.content.Context;

import com.sanleng.electricalfire.model.MaterialStatusModel;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.util.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MaterialStatusRequest {
    public static void GetMaterialStatus(final MaterialStatusModel materialStatusModel, final Context context, final String ids, final String stationName, final String stationId, final String storageLocation, final String state,final String reason) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<String> call = request_Interface.getMaterialStatusCall(ids, PreferenceUtils.getString(context, "agentName"), PreferenceUtils.getString(context, "ids"),stationName,stationId,storageLocation,state,reason,PreferenceUtils.getString(context, "ElectriFire_username"),"app_firecontrol_owner");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(state.equals("emergencystation_in")){
                    materialStatusModel.WarehousingSuccess(response.body().toString());
                }
                if(state.equals("emergencystation_out")){
                    materialStatusModel.OutOfStockSuccess(response.body().toString());
                }
                if(state.equals("emergencystation_break")){
                    materialStatusModel.ReportLossSuccess(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if(state.equals("emergencystation_in")){
                    materialStatusModel.WarehousingFailed();
                }
                if(state.equals("emergencystation_out")){
                    materialStatusModel.OutOfStockFailed();
                }
                if(state.equals("emergencystation_break")){
                    materialStatusModel.ReportLossFailed();
                }
            }
        });

    }
}