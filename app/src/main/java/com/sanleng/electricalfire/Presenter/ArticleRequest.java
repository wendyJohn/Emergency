package com.sanleng.electricalfire.Presenter;

import android.content.Context;

import com.sanleng.electricalfire.model.ArticleModel;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.bean.Article;
import com.sanleng.electricalfire.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleRequest {
    //文章列表
    public static void getArticle(final ArticleModel articleModel, final Context context, final String page, final String publicitytype) {
        final List<Article.DataBean.ListBean> list = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();
        Request_Interface request_Interface = retrofit.create(Request_Interface.class);
        //对发送请求进行封装
        Call<Article> call = request_Interface.getArticleCall(page, "10", PreferenceUtils.getString(context, "ElectriFire_username"), "app_firecontrol_owner", publicitytype);
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                int size = response.body().getData().getTotal();
                for (int i = 0; i < response.body().getData().getList().size(); i++) {
                    Article.DataBean.ListBean bean = new Article.DataBean.ListBean();
                    String id = response.body().getData().getList().get(i).getIds();
                    String topic = response.body().getData().getList().get(i).getTopic();
                    String subject_type = response.body().getData().getList().get(i).getSubject_type();
                    int browse_times = response.body().getData().getList().get(i).getBrowse_times();
                    String cover_img = response.body().getData().getList().get(i).getCover_img();

                    bean.setIds(id);
                    bean.setTopic(topic);
                    bean.setSubject_type( subject_type);
                    bean.setBrowse_times(browse_times);
                    bean.setCover_img(URLs.HOST + cover_img);
                    list.add(bean);
                }
                articleModel.ArticleSuccess(list,size);
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                articleModel.ArticleFailed();
            }
        });
    }

}
