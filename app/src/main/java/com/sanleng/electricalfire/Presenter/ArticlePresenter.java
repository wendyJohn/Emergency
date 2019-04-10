package com.sanleng.electricalfire.Presenter;

import com.sanleng.electricalfire.ui.bean.Article;

import java.util.List;

public interface ArticlePresenter {

    void ArticleSuccess(List<Article.DataBean.ListBean> list, int size);

    void ArticleItemSuccess(String msg, String name, String category, int frequency);

    void ArticleFailed();
}
