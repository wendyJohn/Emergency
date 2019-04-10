package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.electricalfire.Presenter.AlarmRecordPresenter;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.model.AlarmRecordRequest;
import com.sanleng.electricalfire.model.ArticleRequest;
import com.sanleng.electricalfire.ui.adapter.AlarmAdapter;
import com.sanleng.electricalfire.ui.bean.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 历史报警记录
 *
 * @author Qiaoshi
 */
public class AlarmRecordActivity extends BaseActivity implements View.OnClickListener, AlarmRecordPresenter {

    private ListView alarmlistview;
    private int pageNo = 1;// 设置pageNo的初始化值为1，即默认获取的是第一页的数据。
    private int allpage;
    private boolean is_divPage;// 是否进行分页操作
    private boolean finish = true;// 是否加载完成;
    private AlarmAdapter alarmAdapter;
    private List<Map<String, Object>> allList = new ArrayList<>();
    private RelativeLayout r_back;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmrecordactivity);
        initView();
        AlarmRecordRequest.getAlarmRecord(AlarmRecordActivity.this, getApplicationContext(), pageNo + "");
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.alarmrecordactivity;
    }

    private void initView() {
        alarmAdapter = new AlarmAdapter();
        r_back = findViewById(R.id.r_back);
        r_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.r_back:
                finish();
                break;
        }
    }

    //获取历史报警信息
    private void Record(List<Map<String, Object>> list, int size) {
        int length = 10;
        if (size % length == 0) {
            allpage = size / length;
        } else {
            allpage = size / length + 1;
        }
        allList.addAll(list);
        alarmlistview = findViewById(R.id.alarmlistview);
        alarmAdapter.bindData(AlarmRecordActivity.this, allList);
        if (pageNo == 1) {
            // 没有数据就提示暂无数据。
            alarmlistview.setEmptyView(findViewById(R.id.nodata));
            alarmlistview.setAdapter(alarmAdapter);
        }
        alarmAdapter.notifyDataSetChanged();
        pageNo++;
        finish = true;
        alarmlistview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /**
                 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=allpage（ 这里因为服务端有allpage页数据）时，加载更多数据。
                 */
                if (is_divPage && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && pageNo <= allpage
                        && finish) {
                    finish = false;
                    AlarmRecordRequest.getAlarmRecord(AlarmRecordActivity.this, getApplicationContext(), pageNo + "");
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
    }

    @Override
    public void AlarmRecordSuccess(List<Map<String, Object>> list, int size) {
        Record(list, size);
    }

    @Override
    public void AlarmRecordFailed() {
    new SVProgressHUD(AlarmRecordActivity.this).showErrorWithStatus("数据加载失败");
    }
}
