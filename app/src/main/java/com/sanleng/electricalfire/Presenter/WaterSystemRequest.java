package com.sanleng.electricalfire.Presenter;

import android.content.Context;

import com.sanleng.electricalfire.model.WaterSystemModel;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.WaterSystem;
import com.sanleng.electricalfire.ui.bean.WaterSystemStatistics;
import com.sanleng.electricalfire.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WaterSystemRequest {
    //水系统列表
    public static void getWaterSystem(final WaterSystemModel waterSystemModel, final Context context, final String page) {
        final List<WaterSystem.DataBean.ListBean> list = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<WaterSystem> call = request_Interface.getWaterSystemCall(page, "10", PreferenceUtils.getString(context, "unitcode"), PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner", "system_name_water");
        call.enqueue(new Callback<WaterSystem>() {
            @Override
            public void onResponse(Call<WaterSystem> call, Response<WaterSystem> response) {
                int size = response.body().getData().getTotal();
                for (int i = 0; i < response.body().getData().getList().size(); i++) {
                    WaterSystem.DataBean.ListBean bean = new WaterSystem.DataBean.ListBean();
                    String ids = response.body().getData().getList().get(i).getIds();
                    String device_name = response.body().getData().getList().get(i).getDevice_name();
                    String build_name = response.body().getData().getList().get(i).getBuild_name();
                    String device_address = response.body().getData().getList().get(i).getDevice_address();
                    String current_state = response.body().getData().getList().get(i).getCurrent_state();
                    String range_min = response.body().getData().getList().get(i).getRange_min();
                    String range_max = response.body().getData().getList().get(i).getRange_max();
                    String state = response.body().getData().getList().get(i).getState();
                    String device_type = response.body().getData().getList().get(i).getDevice_type();

                    bean.setIds(ids);
                    bean.setDevice_name(device_name);
                    bean.setBuild_name(build_name);
                    bean.setDevice_address(device_address);
                    bean.setCurrent_state(current_state);
                    bean.setRange_max(range_max);
                    bean.setRange_min(range_min);
                    bean.setState(state);
                    bean.setDevice_type(device_type);
                    list.add(bean);
                }
                waterSystemModel.WaterSystemSuccess(list, size);
            }

            @Override
            public void onFailure(Call<WaterSystem> call, Throwable t) {
                waterSystemModel.WaterSystemFailed();
            }
        });
    }

    //异常数量统计
    public static void getWaterSystemStatistics(final WaterSystemModel waterSystemModel, final Context context) {
        Retrofit retrofits = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interfaces = retrofits.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<WaterSystemStatistics> calls = request_Interfaces.getWaterSystemStatisticsCall(PreferenceUtils.getString(context, "unitcode"), PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner");
        calls.enqueue(new Callback<WaterSystemStatistics>() {
            @Override
            public void onResponse(Call<WaterSystemStatistics> call, Response<WaterSystemStatistics> response) {
                int hyrant = response.body().getHyrant();
                int eqt = response.body().getEqt();
                int water = response.body().getWater();
                waterSystemModel.WaterSystemNumberSuccess(hyrant, eqt, water);
            }

            @Override
            public void onFailure(Call<WaterSystemStatistics> call, Throwable t) {
                waterSystemModel.WaterSystemFailed();
            }
        });
    }


}
