package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.electricalfire.Presenter.ArticlePresenter;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.model.ArticleItemRequest;
import com.sanleng.electricalfire.model.ArticleRequest;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.ui.adapter.ArticleAdapter;
import com.sanleng.electricalfire.ui.bean.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章列表数据
 *
 * @author Qiaoshi
 */
public class ArticleActivity extends BaseActivity implements ArticlePresenter, OnClickListener {
    private ListView listView;
    private ArticleAdapter articleAdapter;
    private List<Article.DataBean.ListBean> allList = new ArrayList<>();
    private int pageNo = 1;// 设置pageNo的初始化值为1，即默认获取的是第一页的数据。
    private int allpage;
    private boolean is_divPage;// 是否进行分页操作
    private boolean finish = true;// 是否加载完成;
    private RelativeLayout r_back;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        initView();
        request();
    }

    //初始化
    private void initView() {
        listView = findViewById(R.id.listview);
        r_back = findViewById(R.id.r_back);
        r_back.setOnClickListener(this);
        articleAdapter = new ArticleAdapter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_article;
    }

    //加载数据
    private void request() {
        ArticleRequest.getArticle(ArticleActivity.this, getApplicationContext(), pageNo + "", "1");
    }

    @Override
    public void ArticleSuccess(List<Article.DataBean.ListBean> list, int size) {
        loadData(list, size);
    }

    @Override
    public void ArticleItemSuccess(String msg, String name, String category, int frequency) {
                Intent intent = new Intent(ArticleActivity.this, ArticleItemActivity.class);
                intent.putExtra("url", URLs.HOST + "/" + msg);
                intent.putExtra("name", name);
                intent.putExtra("category", category);
                intent.putExtra("frequency", frequency);
                startActivity(intent);

    }


    @Override
    public void ArticleFailed() {
        new SVProgressHUD(ArticleActivity.this).showErrorWithStatus("加载失败");
    }

    // 加载数据
    private void loadData(List<Article.DataBean.ListBean> list, int size) {
        int length = 10;
        if (size % length == 0) {
            allpage = size / length;
        } else {
            allpage = size / length + 1;
        }
        allList.addAll(list);
        articleAdapter.bindData(ArticleActivity.this, allList);
        if (pageNo == 1) {
            // 没有数据就提示暂无数据。
            listView.setEmptyView(findViewById(R.id.nodata));
            listView.setAdapter(articleAdapter);
        }
        articleAdapter.notifyDataSetChanged();
        pageNo++;
        finish = true;
        listView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /**
                 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=allpage（ 这里因为服务端有allpage页数据）时，加载更多数据。
                 */
                if (is_divPage && scrollState == OnScrollListener.SCROLL_STATE_IDLE && pageNo <= allpage
                        && finish) {
                    finish = false;
                    ArticleRequest.getArticle(ArticleActivity.this, getApplicationContext(), pageNo + "", "1");
                } else if (pageNo > allpage && finish) {
                    finish = false;
                    // 如果pageNo>allpage则表示，服务端没有更多的数据可供加载了。
                }
            }

            // 当：第一个可见的item（firstVisibleItem）+可见的item的个数（visibleItemCount）=
            // 所有的item总数的时候， is_divPage变为TRUE，这个时候才会加载数据。
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                is_divPage = (firstVisibleItem + visibleItemCount == totalItemCount);
            }
        });

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ids = allList.get(position).getIds();
                String name = allList.get(position).getTopic();
                String category = allList.get(position).getSubject_type();
                int frequency = allList.get(position).getBrowse_times();
                ArticleItemRequest.getArticleItem(ArticleActivity.this, getApplicationContext(), ids, name, category, frequency);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.r_back:
                finish();
                break;

        }
    }
}
