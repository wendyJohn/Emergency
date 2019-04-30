package com.sanleng.electricalfire.Presenter;

import android.content.Context;

import com.sanleng.electricalfire.model.LoginModel;
import com.sanleng.electricalfire.ui.activity.LoginActivity;
import com.sanleng.electricalfire.ui.bean.Login;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.util.PreferenceUtils;

import cn.jpush.android.api.JPushInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRequest {
    //登录
    public static void GetLogin(final LoginModel loginModel, final Context context, final String username, final String password, final String platformkey) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<Login> call = request_Interface.getloginCall(username, password, platformkey);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                String msg = response.body().getMsg();
                if (msg.equals("登录成功")) {
                    loginModel.LoginSuccess(msg);
                    String unitcode = response.body().getData().getUnitcode();
                    String agentName = response.body().getData().getName();
                    String ids =response.body().getData().getIds();

//                    //绑定唯一标识
                    JPushInterface.setAlias(context, 1, unitcode);
                    //绑定唯一标识
//                    JPushInterface.setAlias(context, 1, ids);

                    // 存入数据库中（登录名称和密码）
                    PreferenceUtils.setString(context, "ElectriFire_username", username);
                    PreferenceUtils.setString(context, "ElectriFire_password", password);

                    //存入数据库中（登录名称和密码用来判断是否需要重新登录问题）
                    PreferenceUtils.setString(context, "ElectriFire_usernames", username);
                    PreferenceUtils.setString(context, "ElectriFire_passwords", password);

                    // 单位ID
                    PreferenceUtils.setString(context, "unitcode", unitcode);
                    // 人员名称
                    PreferenceUtils.setString(context, "agentName", agentName);
                    PreferenceUtils.setString(context, "ids", ids);
                } else {
                    loginModel.LoginSuccess(msg);
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                loginModel.LoginFailed();
            }
        });

    }
}