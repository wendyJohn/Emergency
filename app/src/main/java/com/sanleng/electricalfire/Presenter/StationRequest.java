package com.sanleng.electricalfire.Presenter;

import android.content.Context;

import com.sanleng.electricalfire.model.AlarmRecordModel;
import com.sanleng.electricalfire.model.StationModel;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.AlarmRecord;
import com.sanleng.electricalfire.ui.bean.ArchitectureBean;
import com.sanleng.electricalfire.ui.bean.Station;
import com.sanleng.electricalfire.util.PreferenceUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StationRequest {
    //站点列表
    public static void getStation(final StationModel stationModel, final Context context, final String page) {
        final List<ArchitectureBean> list = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对发送请求进行封装
        Call<Station> call = request_Interface.getStationCall(page, "10", PreferenceUtils.getString(context, "unitcode"), PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner");
        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                try{
                int size = response.body().getData().getTotal();
                for (int i = 0; i < response.body().getData().getList().size(); i++) {
                    // 获取数据
                    ArchitectureBean bean = new ArchitectureBean();
                    String name =response.body().getData().getList().get(i).getName();
                    String mac = response.body().getData().getList().get(i).getMac();
                    String ids = response.body().getData().getList().get(i).getIds();
                    String address = response.body().getData().getList().get(i).getAddress();
                    bean.setName(name);
                    bean.setAddress(address);
                    bean.setMac(mac);
                    bean.setId(ids);
                    list.add(bean);
                }
                stationModel.StationSuccess(list,size);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                stationModel.StationFailed();
            }
        });
    }

}
