package com.sanleng.electricalfire.Presenter;

import android.content.Context;

import com.sanleng.electricalfire.model.RealTimeDataModel;
import com.sanleng.electricalfire.ui.bean.ERealTimeDataBean;
import com.sanleng.electricalfire.ui.bean.ReadTimeItemData;
import com.sanleng.electricalfire.ui.bean.RealtimeData;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RealTimeDataRequest {

    //设备列表
    public static void getRealTimeData(final RealTimeDataModel realTimeDataModel, final Context context, final String pageNum) {
        final List<ERealTimeDataBean> mylist = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<RealtimeData> call = request_Interface.getReadtimeDataCall(pageNum, "10", PreferenceUtils.getString(context, "unitcode"), PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner");
        call.enqueue(new Callback<RealtimeData>() {
            @Override
            public void onResponse(Call<RealtimeData> call, Response<RealtimeData> response) {
                try {
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
                        bean.setAddress(unit_name + build_name + "\n" + "设备名称:" + device_name);
                        bean.setContact_name(contact_name);
                        bean.setContact_tel(contact_tel);
                        bean.setState(state);
                        mylist.add(bean);
                    }
                    realTimeDataModel.RealTimeDataSuccess(mylist, size);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RealtimeData> call, Throwable t) {
                realTimeDataModel.RealTimeDataFailed();
            }
        });
    }

    //设备实时数据
    public static void getRealDataItem(final RealTimeDataModel realTimeDataModel, final Context context, final String device_id) {
        final List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_temperature = new ArrayList<>();
        final List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_current = new ArrayList<>();
        final List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_residualcurrent = new ArrayList<>();
        final List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_voltage = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<ReadTimeItemData> call = request_Interface.getReadtimeItemDataCall(device_id, PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner");
        call.enqueue(new Callback<ReadTimeItemData>() {
            @Override
            public void onResponse(Call<ReadTimeItemData> call, Response<ReadTimeItemData> response) {
                String buildids = response.body().getData().getBuildids();
                String floorids = response.body().getData().getFloorids();
                String electricalDetectorInfos = response.body().getData().getElectricalDetectorInfos().toString();
                for (int i = 0; i < response.body().getData().getElectricalDetectorInfos().size(); i++) {
                    String detector_name = response.body().getData().getElectricalDetectorInfos().get(i).getDetector_name();
                    String realtime_data = response.body().getData().getElectricalDetectorInfos().get(i).getCurrent_value();
                    String measurement_unit = response.body().getData().getElectricalDetectorInfos().get(i).getMeasurement_unit();
                    String lower_limit = response.body().getData().getElectricalDetectorInfos().get(i).getLower_limit();
                    String upper_limit = response.body().getData().getElectricalDetectorInfos().get(i).getUpper_limit();
                    String detector_portVal = response.body().getData().getElectricalDetectorInfos().get(i).getDetector_portVal();

                    if (detector_name.equals("temperature_detector")) {
                        ReadTimeItemData.DataBean.ElectricalDetectorInfosBean electricalDetectorInfosBean = new ReadTimeItemData.DataBean.ElectricalDetectorInfosBean();
                        electricalDetectorInfosBean.setDetector_name(detector_name);
                        electricalDetectorInfosBean.setDetector_portVal(detector_portVal);
                        electricalDetectorInfosBean.setRealtime_data(realtime_data);
                        electricalDetectorInfosBean.setMeasurement_unit(measurement_unit);
                        electricalDetectorInfosBean.setLower_limit(lower_limit);
                        electricalDetectorInfosBean.setUpper_limit(upper_limit);
                        list_temperature.add(electricalDetectorInfosBean);
                    }
                    if (detector_name.equals("residualcurrent_detector")) {
                        ReadTimeItemData.DataBean.ElectricalDetectorInfosBean electricalDetectorInfosBean = new ReadTimeItemData.DataBean.ElectricalDetectorInfosBean();
                        electricalDetectorInfosBean.setDetector_name(detector_name);
                        electricalDetectorInfosBean.setDetector_portVal(detector_portVal);
                        electricalDetectorInfosBean.setRealtime_data(realtime_data);
                        electricalDetectorInfosBean.setMeasurement_unit(measurement_unit);
                        electricalDetectorInfosBean.setLower_limit(lower_limit);
                        electricalDetectorInfosBean.setUpper_limit(upper_limit);
                        list_current.add(electricalDetectorInfosBean);
                    }
                    if (detector_name.equals("electricity_detector")) {
                        ReadTimeItemData.DataBean.ElectricalDetectorInfosBean electricalDetectorInfosBean = new ReadTimeItemData.DataBean.ElectricalDetectorInfosBean();
                        electricalDetectorInfosBean.setDetector_name(detector_name);
                        electricalDetectorInfosBean.setDetector_portVal(detector_portVal);
                        electricalDetectorInfosBean.setRealtime_data(realtime_data);
                        electricalDetectorInfosBean.setMeasurement_unit(measurement_unit);
                        electricalDetectorInfosBean.setLower_limit(lower_limit);
                        electricalDetectorInfosBean.setUpper_limit(upper_limit);
                        list_residualcurrent.add(electricalDetectorInfosBean);
                    }
                    if (detector_name.equals("voltage_detector")) {
                        ReadTimeItemData.DataBean.ElectricalDetectorInfosBean electricalDetectorInfosBean = new ReadTimeItemData.DataBean.ElectricalDetectorInfosBean();
                        electricalDetectorInfosBean.setDetector_name(detector_name);
                        electricalDetectorInfosBean.setDetector_portVal(detector_portVal);
                        electricalDetectorInfosBean.setRealtime_data(realtime_data);
                        electricalDetectorInfosBean.setMeasurement_unit(measurement_unit);
                        electricalDetectorInfosBean.setLower_limit(lower_limit);
                        electricalDetectorInfosBean.setUpper_limit(upper_limit);
                        list_voltage.add(electricalDetectorInfosBean);
                    }
                }
                realTimeDataModel.RealDataItemSuccess(list_temperature, list_current, list_residualcurrent, list_voltage, floorids, buildids, electricalDetectorInfos);
            }

            @Override
            public void onFailure(Call<ReadTimeItemData> call, Throwable t) {
                realTimeDataModel.RealTimeDataFailed();
            }
        });
    }


}