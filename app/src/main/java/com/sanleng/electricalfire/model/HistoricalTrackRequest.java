package com.sanleng.electricalfire.model;

import android.content.Context;

import com.sanleng.electricalfire.Presenter.HistoricalTrackPresenter;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.HistoricalTrack;
import com.sanleng.electricalfire.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoricalTrackRequest {

    public static void getHistoricalTrack(final HistoricalTrackPresenter historicalTrackPresenter, final Context context, final String device_id) {
        final List<HashMap<String, Object>> c_list = new ArrayList<>();
        final List<HashMap<String, Object>> h_list = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<HistoricalTrack> call = request_Interface.getHistoricalTrackCall(device_id, PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner");
        call.enqueue(new Callback<HistoricalTrack>() {
            @Override
            public void onResponse(Call<HistoricalTrack> call, Response<HistoricalTrack> response) {

                for (int i = 0; i < response.body().getData().size(); i++) {
                    HashMap<String, Object> map = new HashMap<>();
                    String state = response.body().getData().get(i).getState();
                    if (state.equals("1")) {
                        String point_id = response.body().getData().get(i).getPoint_id();
                        String build_name = response.body().getData().get(i).getBuild_name();
                        String device_name = response.body().getData().get(i).getDevice_name();
                        String receive_time = response.body().getData().get(i).getReceive_time();
                        String detector_name = response.body().getData().get(i).getDetector_name();
                        String ids = response.body().getData().get(i).getIds();

                        map.put("point_id", point_id);
                        map.put("name", detector_name);
                        map.put("postion", build_name + device_name);
                        map.put("time", receive_time);
                        map.put("ids", ids);
                        c_list.add(map);
                    }
                    if (state.equals("0")) {
                        String point_id = response.body().getData().get(i).getPoint_id();
                        String build_name = response.body().getData().get(i).getBuild_name();
                        String device_name = response.body().getData().get(i).getDevice_name();
                        String receive_time = response.body().getData().get(i).getReceive_time();
                        String detector_name = response.body().getData().get(i).getDetector_name();
                        String ids = response.body().getData().get(i).getIds();

                        map.put("point_id", point_id);
                        map.put("name", detector_name);
                        map.put("postion", build_name + device_name);
                        map.put("time", receive_time);
                        map.put("ids", ids);
                        h_list.add(map);
                    }
                }
                historicalTrackPresenter.HistoricalTrackSuccess(c_list,h_list);
            }

            @Override
            public void onFailure(Call<HistoricalTrack> call, Throwable t) {
                historicalTrackPresenter.HistoricalTrackFailed();
            }
        });
    }
}
