package com.sanleng.electricalfire.Presenter;

import android.content.Context;

import com.sanleng.electricalfire.MyApplication;
import com.sanleng.electricalfire.model.AlarmloadModel;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.Alarmload;
import com.sanleng.electricalfire.ui.bean.HistorRecordBean;
import com.sanleng.electricalfire.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistorRecordRequest {
    public static void getHistorRecord(final Context context, final String mac) {
        final ArrayList<HistorRecordBean> lists = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<HistorRecordBean> call = request_Interface.getHistorRecordCall("1","50",mac, "admin", "app_firecontrol_owner");
        call.enqueue(new Callback<HistorRecordBean>() {
            @Override
            public void onResponse(Call<HistorRecordBean> call, Response<HistorRecordBean> response) {
                for (int i = 0; i <response.body().getData().getList().size() ; i++) {
                    HistorRecordBean bean = new HistorRecordBean();
                    String station_name = response.body().getData().getList().get(i).getStation_name();
                    String materialname = response.body().getData().getList().get(i).getName();
                    String storage_location = response.body().getData().getList().get(i).getStorage_location();
                    String state = response.body().getData().getList().get(i).getState();
                    String agent_name = response.body().getData().getList().get(i).getAgent_name();
                    String agent_time =response.body().getData().getList().get(i).getAgent_time();
                    bean.setSitename(station_name);
                    bean.setMaterialname(materialname);
                    bean.setStoragelocation(storage_location);
                    bean.setMaterialstatus(state);
                    bean.setOperator(agent_name);
                    bean.setOperationtime(agent_time);
                    lists.add(bean);
                }
                MessageEvent messageEvent = new MessageEvent(MyApplication.MESSAGE_HISTORRECORD);
                messageEvent.setLists(lists);
                EventBus.getDefault().post(messageEvent);
            }

            @Override
            public void onFailure(Call<HistorRecordBean> call, Throwable t) {

            }
        });

    }
}