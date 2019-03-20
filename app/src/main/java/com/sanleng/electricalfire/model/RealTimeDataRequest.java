package com.sanleng.electricalfire.model;

import android.content.Context;

import com.sanleng.electricalfire.MyApplication;
import com.sanleng.electricalfire.bean.ERealTimeDataBean;
import com.sanleng.electricalfire.bean.RealtimeData;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.util.MessageEvent;
import com.sanleng.electricalfire.util.PreferenceUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RealTimeDataRequest {
    private Context context;
    private List<ERealTimeDataBean> list = new ArrayList<>();

    public RealTimeDataRequest(Context context) {
        super();
        this.context = context;
    }

    public void getRealTimeData(final String pageNum) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<RealtimeData> call = request_Interface.getReadtimeDataCall(pageNum, "10", PreferenceUtils.getString(context,"unitcode"),  PreferenceUtils.getString(context,"ElectriFire_username"), "app_firecontrol_owner");
        call.enqueue(new Callback<RealtimeData>() {
            @Override
            public void onResponse(Call<RealtimeData> call, Response<RealtimeData> response) {
                int size = response.body().getData().getTotal();
                for (int i = 0; i < response.body().getData().getList().size(); i++) {
                    ERealTimeDataBean bean = new ERealTimeDataBean();
                    String device_id = response.body().getData().getList().get(i).getDevice_id();
                    String unit_name = response.body().getData().getList().get(i).getUnit_name();
                    String build_name = response.body().getData().getList().get(i).getBuild_name();
                    String device_name = response.body().getData().getList().get(i).getDevice_name();
                    String state = response.body().getData().getList().get(i).getState();
                    String contact_name = response.body().getData().getList().get(i).getContact_name();
                    String contact_tel = response.body().getData().getList().get(i).getContact_tel();
                    bean.setId(device_id);
                    bean.setAddress(unit_name + build_name + "\n" + device_name);
                    bean.setContact_name(contact_name);
                    bean.setContact_tel(contact_tel);
                    bean.setState(state);
                    list.add(bean);
                }
                MessageEvent messageEvent = new MessageEvent(MyApplication.MESSREALTIMEDATA);
                messageEvent.setList(list);
                messageEvent.setSize(size);
                EventBus.getDefault().post(messageEvent);
            }

            @Override
            public void onFailure(Call<RealtimeData> call, Throwable t) {

            }
        });
    }
}
