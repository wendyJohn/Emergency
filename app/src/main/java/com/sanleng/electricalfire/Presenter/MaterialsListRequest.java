package com.sanleng.electricalfire.Presenter;

import android.content.Context;

import com.sanleng.electricalfire.model.AlarmRecordModel;
import com.sanleng.electricalfire.model.MaterialsListModel;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.AlarmRecord;
import com.sanleng.electricalfire.ui.bean.ArchitecturesBean;
import com.sanleng.electricalfire.ui.bean.MaterialsList;
import com.sanleng.electricalfire.ui.bean.StationBean;
import com.sanleng.electricalfire.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MaterialsListRequest {
    //物资列表
    public static void getMaterialsList(final MaterialsListModel materialsListModel, final Context context, final String stationId, final String mac, final String name, final String address, final double distance, final List<StationBean> slists, final String format) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对发送请求进行封装
        Call<ArchitecturesBean> call = request_Interface.getArchitecturesCall(stationId, format, "admin", "app_firecontrol_owner");
        call.enqueue(new Callback<ArchitecturesBean>() {
            @Override
            public void onResponse(Call<ArchitecturesBean> call, Response<ArchitecturesBean> response) {

                for (int i = 0; i < response.body().getData().size(); i++) {
                    StationBean bean = new StationBean();
                    // 获取数据
                    String name = response.body().getData().get(i).getName();
                    String lack = response.body().getData().get(i).getLack();
                    String location = response.body().getData().get(i).getLocation();
                    String model = response.body().getData().get(i).getModel();
                    String num = response.body().getData().get(i).getNum();//规格数量
                    String state = response.body().getData().get(i).getState();//状态为O时即为物资短缺

                    bean.setName("物资名称:" + name);
                    bean.setNumber("现有数量:" + lack);
                    bean.setAddress("所在位置:" + location + "箱");
                    if (state.equals("0")) {
                        int nums = Integer.parseInt(num);
                        int lacks = Integer.parseInt(lack);
                        int str = nums - lacks;
                        bean.setShortage("短缺数量:" + str + "");
                    } else {
                        bean.setShortage("短缺数量:" + "0");
                    }
                    bean.setType(1);
                    bean.setImage_type(model);
                    bean.setMac(format);
                    slists.add(bean);
                }
                materialsListModel.MaterialsListSuccess(slists, stationId, format, name, address, distance);
            }

            @Override
            public void onFailure(Call<ArchitecturesBean> call, Throwable t) {
                materialsListModel.MaterialsListFailed();
            }
        });
    }

}
