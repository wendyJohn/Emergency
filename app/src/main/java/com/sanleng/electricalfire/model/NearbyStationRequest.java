package com.sanleng.electricalfire.model;

import android.content.Context;

import com.sanleng.electricalfire.Presenter.NearbyStationPresenter;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.Article;
import com.sanleng.electricalfire.ui.bean.NearbyStation;
import com.sanleng.electricalfire.ui.bean.Sosbean;
import com.sanleng.electricalfire.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NearbyStationRequest {
    //附件应急站
    public static void getNearbyStation(final NearbyStationPresenter nearbyStationPresenter, final Context context, final String lat, final String lng, final String name) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对发送请求进行封装
        Call<NearbyStation> call = request_Interface.getNearbyStationCall(lat, lng, "1", "100", PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner", name);
        call.enqueue(new Callback<NearbyStation>() {
            @Override
            public void onResponse(Call<NearbyStation> call, Response<NearbyStation> response) {
                if(response.body().getData().getList().size()>0){
                    nearbyStationPresenter.NearbyStationSuccess(response.body().getData().getList());
                }

            }

            @Override
            public void onFailure(Call<NearbyStation> call, Throwable t) {
                nearbyStationPresenter.NearbyStationFailed();
            }
        });
    }


    //附件SOS
    public static void getSos(final NearbyStationPresenter nearbyStationPresenter, final Context context) {
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
                    nearbyStationPresenter.SosSuccess(response.body().getData().getList());
                }
            }

            @Override
            public void onFailure(Call<Sosbean> call, Throwable t) {
                nearbyStationPresenter.NearbyStationFailed();
            }
        });
    }

}
