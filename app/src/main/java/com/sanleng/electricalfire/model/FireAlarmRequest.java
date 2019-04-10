package com.sanleng.electricalfire.model;

import android.content.Context;

import com.sanleng.electricalfire.Presenter.FireAlarmPresenter;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.FireAlarmBean;
import com.sanleng.electricalfire.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FireAlarmRequest {
    //火警列表
    public static void getFireAlarm(final FireAlarmPresenter fireAlarmPresenter, final Context context, final String page, final String status, final String scope) {
        final List<FireAlarmBean.DataBean.ListBean> list = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对发送请求进行封装
        Call<FireAlarmBean> call = request_Interface.getFireAlarmCall(page, "10", PreferenceUtils.getString(context, "unitcode"), PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner", status, scope);
        call.enqueue(new Callback<FireAlarmBean>() {
            @Override
            public void onResponse(Call<FireAlarmBean> call, Response<FireAlarmBean> response) {
                int size = response.body().getData().getTotal();
                for (int i = 0; i < response.body().getData().getList().size(); i++) {
                    FireAlarmBean.DataBean.ListBean bean = new FireAlarmBean.DataBean.ListBean();
                    String taskId = response.body().getData().getList().get(i).getIds();
                    String receive_time = response.body().getData().getList().get(i).getReceive_time();
                    String device_name = response.body().getData().getList().get(i).getDevice_name();
                    String position = response.body().getData().getList().get(i).getPosition();
                    String unit_name = response.body().getData().getList().get(i).getUnit_name();

                    bean.setReceive_time(receive_time);
                    bean.setDevice_name(device_name);
                    bean.setPosition(position);
                    bean.setUnit_name(unit_name);
                    bean.setIds(taskId);
                    list.add(bean);
                }
                fireAlarmPresenter.FireAlarmSuccess(list, size);
            }

            @Override
            public void onFailure(Call<FireAlarmBean> call, Throwable t) {
                fireAlarmPresenter.FireAlarmFailed();
            }
        });
    }

    //误报火警列表
    public static void getFireAlarms(final FireAlarmPresenter fireAlarmPresenter, final Context context, final String page, final String status, final String scope) {
        final List<FireAlarmBean.DataBean.ListBean> list = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对发送请求进行封装
        Call<FireAlarmBean> call = request_Interface.getFireAlarmCall(page, "10", PreferenceUtils.getString(context, "unitcode"), PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner", status, scope);
        call.enqueue(new Callback<FireAlarmBean>() {
            @Override
            public void onResponse(Call<FireAlarmBean> call, Response<FireAlarmBean> response) {
                int size = response.body().getData().getTotal();
                for (int i = 0; i < response.body().getData().getList().size(); i++) {
                    FireAlarmBean.DataBean.ListBean bean = new FireAlarmBean.DataBean.ListBean();
                    String taskId = response.body().getData().getList().get(i).getIds();
                    String receive_time = response.body().getData().getList().get(i).getReceive_time();
                    String device_name = response.body().getData().getList().get(i).getDevice_name();
                    String position = response.body().getData().getList().get(i).getPosition();
                    String unit_name = response.body().getData().getList().get(i).getUnit_name();
                    String alarm_type= response.body().getData().getList().get(i).getAlarm_type();
                    if(alarm_type.equals(102)) {
                        bean.setReceive_time(receive_time);
                        bean.setDevice_name(device_name);
                        bean.setPosition(position);
                        bean.setUnit_name(unit_name);
                        bean.setIds(taskId);
                        list.add(bean);
                    }
                }
                fireAlarmPresenter.FireAlarmSuccess(list, size);
            }

            @Override
            public void onFailure(Call<FireAlarmBean> call, Throwable t) {
                fireAlarmPresenter.FireAlarmFailed();
            }
        });
    }

}
