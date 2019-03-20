package com.sanleng.electricalfire.model;

import android.content.Context;

import com.sanleng.electricalfire.MyApplication;
import com.sanleng.electricalfire.bean.Login;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.util.MessageEvent;
import com.sanleng.electricalfire.util.PreferenceUtils;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.android.api.JPushInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRequest  {
    private Context context;

    public LoginRequest(Context context) {
        super();
        this.context=context;
    }

    public void getLogin(final String username, final String password, final String platformkey) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface=retrofit.create(Request_Interface.class);
        //对 发送请求 进行封装
        Call<Login> call = request_Interface.getloginCall(username,password,platformkey);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                    String msg=response.body().getMsg();
                    if(msg.equals("登录成功")) {
                        MessageEvent messageEvent = new MessageEvent(MyApplication.MESSLOGIN);
                        messageEvent.setMessage(msg);
                        EventBus.getDefault().post(messageEvent);

                        String unitcode = response.body().getData().getUnitcode();
                        String agentName = response.body().getData().getName();
                        //绑定唯一标识
                        JPushInterface.setAlias(context, 1, unitcode);
                        // 存入数据库中（登录名称和密码）
                        PreferenceUtils.setString(context, "ElectriFire_username", username);
                        PreferenceUtils.setString(context, "ElectriFire_password", password);
                        // 单位ID
                        PreferenceUtils.setString(context, "unitcode", unitcode);
                        // 人员名称
                        PreferenceUtils.setString(context, "agentName", agentName);
                    }else{
                        MessageEvent messageEvent = new MessageEvent(MyApplication.MESSLOGIN);
                        messageEvent.setMessage(msg);
                        EventBus.getDefault().post(messageEvent);
                    }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                MessageEvent messageEvent = new MessageEvent(MyApplication.MESSLOGIN);
                messageEvent.setMessage("请求失败");
                EventBus.getDefault().post(messageEvent);
            }
        });
    }
}
