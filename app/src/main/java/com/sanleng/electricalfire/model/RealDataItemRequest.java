package com.sanleng.electricalfire.model;

import android.content.Context;

import com.sanleng.electricalfire.MyApplication;
import com.sanleng.electricalfire.bean.ReadTimeItemData;
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

public class RealDataItemRequest {
    private Context context;
    List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_temperature = new ArrayList<>();
    List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_current = new ArrayList<>();
    List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_residualcurrent = new ArrayList<>();
    List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_voltage = new ArrayList<>();
    String buildids;
    String floorids;
    String electricalDetectorInfos;

    public RealDataItemRequest(Context context) {
        super();
        this.context = context;
    }

    public void getRealDataItem(final String device_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<ReadTimeItemData> call = request_Interface.getReadtimeItemDataCall(device_id, PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner");
        call.enqueue(new Callback<ReadTimeItemData>() {
            @Override
            public void onResponse(Call<ReadTimeItemData> call, Response<ReadTimeItemData> response) {
                buildids = response.body().getData().getBuildids();
                floorids = response.body().getData().getFloorids();
                electricalDetectorInfos = response.body().getData().getElectricalDetectorInfos().toString();
                for (int i = 0; i < response.body().getData().getElectricalDetectorInfos().size(); i++) {
                    String detector_name = response.body().getData().getElectricalDetectorInfos().get(i).getDetector_name();
                    String realtime_data = response.body().getData().getElectricalDetectorInfos().get(i).getCurrent_value();
                    String measurement_unit = response.body().getData().getElectricalDetectorInfos().get(i).getMeasurement_unit();
                    String lower_limit = response.body().getData().getElectricalDetectorInfos().get(i).getLower_limit();
                    String upper_limit = response.body().getData().getElectricalDetectorInfos().get(i).getUpper_limit();
                    String detector_portVal = response.body().getData().getElectricalDetectorInfos().get(i).getDetector_portVal();
                    if (detector_name.equals("temperature_detector")) {
                        ReadTimeItemData.DataBean.ElectricalDetectorInfosBean electricalDetectorInfosBean=new ReadTimeItemData.DataBean.ElectricalDetectorInfosBean();
                        electricalDetectorInfosBean.setDetector_name(detector_name);
                        electricalDetectorInfosBean.setDetector_portVal(detector_portVal);
                        electricalDetectorInfosBean.setRealtime_data(realtime_data);
                        electricalDetectorInfosBean.setMeasurement_unit(measurement_unit);
                        electricalDetectorInfosBean.setLower_limit(lower_limit);
                        electricalDetectorInfosBean.setUpper_limit(upper_limit);
                        list_temperature.add(electricalDetectorInfosBean);
                    }
                    if (detector_name.equals("residualcurrent_detector")) {
                        ReadTimeItemData.DataBean.ElectricalDetectorInfosBean electricalDetectorInfosBean=new ReadTimeItemData.DataBean.ElectricalDetectorInfosBean();
                        electricalDetectorInfosBean.setDetector_name(detector_name);
                        electricalDetectorInfosBean.setDetector_portVal(detector_portVal);
                        electricalDetectorInfosBean.setRealtime_data(realtime_data);
                        electricalDetectorInfosBean.setMeasurement_unit(measurement_unit);
                        electricalDetectorInfosBean.setLower_limit(lower_limit);
                        electricalDetectorInfosBean.setUpper_limit(upper_limit);
                        list_current.add(electricalDetectorInfosBean);
                    }
                    if (detector_name.equals("electricity_detector")) {
                        ReadTimeItemData.DataBean.ElectricalDetectorInfosBean electricalDetectorInfosBean=new ReadTimeItemData.DataBean.ElectricalDetectorInfosBean();
                        electricalDetectorInfosBean.setDetector_name(detector_name);
                        electricalDetectorInfosBean.setDetector_portVal(detector_portVal);
                        electricalDetectorInfosBean.setRealtime_data(realtime_data);
                        electricalDetectorInfosBean.setMeasurement_unit(measurement_unit);
                        electricalDetectorInfosBean.setLower_limit(lower_limit);
                        electricalDetectorInfosBean.setUpper_limit(upper_limit);
                        list_residualcurrent.add(electricalDetectorInfosBean);
                    }
                }
                MessageEvent messageEvent = new MessageEvent(MyApplication.MESSREALDATAITEM);
                messageEvent.setList_temperature(list_temperature);
                messageEvent.setList_current(list_current);
                messageEvent.setList_residualcurrent(list_residualcurrent);
                messageEvent.setFloorids(floorids);
                messageEvent.setBuildids(buildids);
                messageEvent.setElectricalDetectorInfos(electricalDetectorInfos);
                EventBus.getDefault().post(messageEvent);

            }

            @Override
            public void onFailure(Call<ReadTimeItemData> call, Throwable t) {

            }
        });
    }
}
