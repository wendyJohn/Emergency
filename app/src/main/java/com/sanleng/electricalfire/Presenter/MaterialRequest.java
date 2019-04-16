package com.sanleng.electricalfire.Presenter;

import android.content.Context;

import com.sanleng.electricalfire.model.MaterialDetailModel;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.Material;
import com.sanleng.electricalfire.util.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MaterialRequest {
    public static void GetMaterial(final MaterialDetailModel materialDetailModel, final Context context, final String ids) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<Material> call = request_Interface.getMaterialDetailsCall(ids,PreferenceUtils.getString(context, "ElectriFire_username"),"app_firecontrol_owner");
        call.enqueue(new Callback<Material>() {
            @Override
            public void onResponse(Call<Material> call, Response<Material> response) {
                materialDetailModel.MaterialDetailSuccess(response.body().getData().getName(),response.body().getData().getNumber(),response.body().getData().getSpecification(),response.body().getData().getModel());
            }

            @Override
            public void onFailure(Call<Material> call, Throwable t) {
                materialDetailModel.MaterialDetailFailed();
            }
        });

    }
}