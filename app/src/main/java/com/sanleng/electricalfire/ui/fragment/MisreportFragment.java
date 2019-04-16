package com.sanleng.electricalfire.ui.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.Presenter.FireAlarmRequest;
import com.sanleng.electricalfire.model.FireAlarmModel;
import com.sanleng.electricalfire.ui.adapter.MisreportAdapter;
import com.sanleng.electricalfire.ui.bean.FireAlarmBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 误报信息
 *
 * @author Qiaoshi
 */
@SuppressLint("ResourceAsColor")
public class MisreportFragment extends BaseFragment implements FireAlarmModel {

    private View view;
    private TextView text_todays, text_thisweeks, text_thismonths;
    private ListView firealarmlistview;
    private MisreportAdapter misreportAdapter;
    private int pageNo = 1;// 设置pageNo的初始化值为1，即默认获取的是第一页的数据。
    private int allpage;
    List<FireAlarmBean.DataBean.ListBean> allList;// 存放所有数据AlarmBean的list集合
    private boolean is_divPage;// 是否进行分页操作
    private boolean finish = true;// 是否加载完成;
    private String scope = "oneday";// 日期
    private String status = "processed";// 状态
    private TextView data_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.faultfragment, null);
        initview();
        FireAlarmRequest.getFireAlarms(MisreportFragment.this, getActivity(), pageNo + "", status, scope);
        return view;
    }

    private void initview() {
        InitTextView();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    /**
     * 初始化头标
     */
    private void InitTextView() {
        allList=new ArrayList<>();
        misreportAdapter = new MisreportAdapter();
        text_todays = view.findViewById(R.id.text_todays);
        text_thisweeks = view.findViewById(R.id.text_thisweeks);
        text_thismonths = view.findViewById(R.id.text_thismonths);

        text_todays.setOnClickListener(new MyOnClickListener(0));
        text_thisweeks.setOnClickListener(new MyOnClickListener(1));
        text_thismonths.setOnClickListener(new MyOnClickListener(2));
        data_text = view.findViewById(R.id.data_text);
    }

    @Override
    public void FireAlarmSuccess(List<FireAlarmBean.DataBean.ListBean> list, int size) {
        System.out.println("========AAAAA========"+list.size());
        loadData(size, list);
    }

    @Override
    public void FireSuccess(List<String> info) {

    }

    @Override
    public void FireAlarmFailed() {
        new SVProgressHUD(getActivity()).showErrorWithStatus("加载失败");
    }

    /**
     * 头标点击监听
     */
    private class MyOnClickListener implements OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            switch (index) {
                case 0:
                    text_todays.setTextColor(getResources().getColor(R.color.white));
                    text_thisweeks.setTextColor(getResources().getColor(R.color.blue));
                    text_thismonths.setTextColor(getResources().getColor(R.color.blue));
                    text_todays.setBackground(getResources().getDrawable(R.drawable.text_rounded));
                    text_thisweeks.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    text_thismonths.setBackground(getResources().getDrawable(R.drawable.text_roundeds));

                    scope = "oneday";// 日期
                    status = "processed";// 状态
                    allList = new ArrayList<>();
                    FireAlarmRequest.getFireAlarms(MisreportFragment.this, getActivity(), "1", status, scope);
                    break;
                case 1:
                    text_todays.setTextColor(getResources().getColor(R.color.blue));
                    text_thisweeks.setTextColor(getResources().getColor(R.color.white));
                    text_thismonths.setTextColor(getResources().getColor(R.color.blue));

                    text_todays.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    text_thisweeks.setBackground(getResources().getDrawable(R.drawable.text_rounded));
                    text_thismonths.setBackground(getResources().getDrawable(R.drawable.text_roundeds));

                    scope = "sevendays";// 日期
                    status = "processed";// 状态
                    allList = new ArrayList<>();
                    FireAlarmRequest.getFireAlarms(MisreportFragment.this, getActivity(), "1", status, scope);
                    break;
                case 2:
                    text_todays.setTextColor(getResources().getColor(R.color.blue));
                    text_thisweeks.setTextColor(getResources().getColor(R.color.blue));
                    text_thismonths.setTextColor(getResources().getColor(R.color.white));

                    text_todays.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    text_thisweeks.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    text_thismonths.setBackground(getResources().getDrawable(R.drawable.text_rounded));

                    scope = "thirtydays";// 日期
                    status = "processed";// 状态
                    allList = new ArrayList<>();
                    FireAlarmRequest.getFireAlarms(MisreportFragment.this, getActivity(), "1", status, scope);
                    break;
            }
        }
    }

    // 加载数据
    private void loadData(int size, List<FireAlarmBean.DataBean.ListBean> list) {
        int length = 10;
        if (size % length == 0) {
            allpage = size / length;
        } else {
            allpage = size / length + 1;
        }
        if (scope.equals("oneday")) {
            data_text.setText("今日共有" + size + "起误报");
        }
        if (scope.equals("sevendays")) {
            data_text.setText("本周共有" + size + "起误报");
        }
        if (scope.equals("thirtydays")) {
            data_text.setText("本月共有" + size + "起误报");
        }
        firealarmlistview = view.findViewById(R.id.firealarmlistview);
        allList.addAll(list);
        misreportAdapter.bindData(getActivity(), allList);
        if (pageNo == 1) {
            // 没有数据就提示暂无数据。
            firealarmlistview.setEmptyView(view.findViewById(R.id.nodata));
            firealarmlistview.setAdapter(misreportAdapter);
        }
        misreportAdapter.notifyDataSetChanged();
        pageNo++;
        finish = true;
        firealarmlistview.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /**
                 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=allpage（ 这里因为服务端有allpage页数据）时，加载更多数据。
                 */
                if (is_divPage && scrollState == OnScrollListener.SCROLL_STATE_IDLE && pageNo <= allpage
                        && finish) {
                    finish = false;
                    FireAlarmRequest.getFireAlarms(MisreportFragment.this, getActivity(), pageNo + "", status, scope);
                } else if (pageNo > allpage && finish) {
                    finish = false;
                    // 如果pageNo>allpage则表示，服务端没有更多的数据可供加载了。
                    Toast.makeText(getActivity(), "加载完了！", Toast.LENGTH_SHORT).show();
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
}

