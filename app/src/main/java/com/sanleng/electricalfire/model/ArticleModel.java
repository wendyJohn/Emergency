package com.sanleng.electricalfire.model;

import java.util.List;

public interface ArticleModel {

    void ArticleSuccess(List<com.sanleng.electricalfire.ui.bean.Article.DataBean.ListBean> list, int size);

    void ArticleItemSuccess(String msg, String name, String category, int frequency);

    void ArticleFailed();
}
