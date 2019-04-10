package com.sanleng.electricalfire.model;

import android.content.Context;

import com.sanleng.electricalfire.Presenter.ArticlePresenter;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.ArticleItem;
import com.sanleng.electricalfire.util.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleItemRequest {
    //文章详情
    public static void getArticleItem(final ArticlePresenter articlePresenter, final Context context, final String ids,final String name,final String category,final int frequency) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对发送请求进行封装
        Call<ArticleItem> call = request_Interface.getArticleItemCall(ids,  PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner");
        call.enqueue(new Callback<ArticleItem>() {
            @Override
            public void onResponse(Call<ArticleItem> call, Response<ArticleItem> response) {
                String content=response.body().getData().getContent();
                articlePresenter.ArticleItemSuccess(content,name,category,frequency);
            }

            @Override
            public void onFailure(Call<ArticleItem> call, Throwable t) {
                articlePresenter.ArticleFailed();
            }
        });
    }

}
