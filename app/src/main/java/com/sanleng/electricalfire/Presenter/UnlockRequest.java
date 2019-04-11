package com.sanleng.electricalfire.Presenter;

import android.content.Context;

import com.sanleng.electricalfire.model.NearbyStationModel;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.Sosbean;
import com.sanleng.electricalfire.util.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnlockRequest {
    //开锁
    public static void getUnlock(final NearbyStationModel nearbyStationModel, final Context context, final String position, final String mac) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://101.132.139.37:8091") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对发送请求进行封装
        Call<String> call = request_Interface.getUnlockCall(position,mac);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                nearbyStationModel.NearbyStationFailed();
            }
        });
    }


    //附件SOS
    public static void getSos(final NearbyStationModel nearbyStationModel, final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对发送请求进行封装
        Call<Sosbean> call = request_Interface.getSosCall("1", "100", PreferenceUtils.getString(context, "unitcode"), PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner");
        call.enqueue(new Callback<Sosbean>() {
            @Override
            public void onResponse(Call<Sosbean> call, Response<Sosbean> response) {
                if(response.body().getData().getList().size()>0){
                    nearbyStationModel.SosSuccess(response.body().getData().getList());
                }
            }

            @Override
            public void onFailure(Call<Sosbean> call, Throwable t) {
                nearbyStationModel.NearbyStationFailed();
            }
        });
    }

}
