package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.electricalfire.MyApplication;
import com.sanleng.electricalfire.Presenter.FireAlarmPresenter;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.dialog.FireConfirmDialog;
import com.sanleng.electricalfire.model.FireAlarmRequest;
import com.sanleng.electricalfire.ui.adapter.FireAlarmAdapter;
import com.sanleng.electricalfire.ui.bean.FireAlarmBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 火警信息
 *
 * @author Qiaoshi
 */
public class FireAlarmActivity extends BaseActivity implements FireAlarmPresenter {

    private TextView tab_1, tab_2;// 选项名称
    private LinearLayout l_opa, l_opb;// 选项名称
    private TextView text_today, text_thisweek, text_todays, text_thisweeks, text_thismonths;
    private LinearLayout l_date1, l_date2;
    private ListView firealarmlistview;
    private FireAlarmAdapter fireAlarmAdapter;
    private FireConfirmDialog fireConfirmDialog;
    private RelativeLayout r_back;
    private int pageNo = 1;// 设置pageNo的初始化值为1，即默认获取的是第一页的数据。
    private int allpage;
    List<FireAlarmBean.DataBean.ListBean> allList;// 存放所有数据AlarmBean的list集合
    private boolean is_divPage;// 是否进行分页操作
    private boolean finish = true;// 是否加载完成;
    private String scope = "oneday";// 日期
    private String status = "pending";// 状态
    private String type = "待处理";// 状态

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        this.setContentView(R.layout.firealarmactivity);
        initview();
        FireAlarmRequest.getFireAlarm(FireAlarmActivity.this, getApplicationContext(), pageNo + "", status, scope);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.firealarmactivity;
    }

    private void initview() {
        initTextView();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    /**
     * 初始化头标
     */
    private void initTextView() {
        allList = new ArrayList<>();
        fireAlarmAdapter = new FireAlarmAdapter();
        tab_1 = findViewById(R.id.tab_1);
        tab_2 = findViewById(R.id.tab_2);
        text_todays = findViewById(R.id.text_todays);
        text_thisweeks = findViewById(R.id.text_thisweeks);
        text_thismonths = findViewById(R.id.text_thismonths);
        text_today = findViewById(R.id.text_today);
        text_thisweek = findViewById(R.id.text_thisweek);
        l_opa = findViewById(R.id.l_opa);
        l_opb = findViewById(R.id.l_opb);
        l_date1 = findViewById(R.id.l_date1);
        l_date2 = findViewById(R.id.l_date2);
        r_back = findViewById(R.id.r_back);
        r_back.setOnClickListener(new MyOnClickListener(99));
        tab_1.setText("待处理");
        tab_2.setText("已处理");
        l_opa.setOnClickListener(new MyOnClickListener(0));
        l_opb.setOnClickListener(new MyOnClickListener(1));
        text_todays.setOnClickListener(new MyOnClickListener(2));
        text_thisweeks.setOnClickListener(new MyOnClickListener(3));
        text_thismonths.setOnClickListener(new MyOnClickListener(4));
        text_today.setOnClickListener(new MyOnClickListener(5));
        text_thisweek.setOnClickListener(new MyOnClickListener(6));

    }

    @Override
    public void FireAlarmSuccess(List<FireAlarmBean.DataBean.ListBean> list, int size) {
        loadData(size, list);
    }

    @Override
    public void FireAlarmFailed() {
        new SVProgressHUD(FireAlarmActivity.this).showErrorWithStatus("加载失败");
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
                    tab_1.setTextColor(getResources().getColor(R.color.text_blue));
                    tab_2.setTextColor(getResources().getColor(R.color.black));
                    text_today.setTextColor(getResources().getColor(R.color.white));
                    text_thisweek.setTextColor(getResources().getColor(R.color.blue));
                    text_today.setBackground(getResources().getDrawable(R.drawable.text_rounded));
                    text_thisweek.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    l_date1.setVisibility(View.VISIBLE);
                    l_date2.setVisibility(View.GONE);
                    scope = "oneday";// 日期
                    status = "pending";// 状态
                    type = "待处理";

                    allList = new ArrayList<>();
                    FireAlarmRequest.getFireAlarm(FireAlarmActivity.this, getApplicationContext(), "1", status, scope);

                    break;
                case 1:
                    tab_1.setTextColor(getResources().getColor(R.color.black));
                    tab_2.setTextColor(getResources().getColor(R.color.text_blue));
                    text_todays.setTextColor(getResources().getColor(R.color.white));
                    text_thisweeks.setTextColor(getResources().getColor(R.color.blue));
                    text_thismonths.setTextColor(getResources().getColor(R.color.blue));
                    text_todays.setBackground(getResources().getDrawable(R.drawable.text_rounded));
                    text_thisweeks.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    text_thismonths.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    l_date1.setVisibility(View.GONE);
                    l_date2.setVisibility(View.VISIBLE);

                    scope = "oneday";// 日期
                    status = "processed";// 状态
                    type = "已处理";
                    allList = new ArrayList<>();
                    FireAlarmRequest.getFireAlarm(FireAlarmActivity.this, getApplicationContext(), "1", status, scope);
                    break;
                case 2:
                    text_todays.setTextColor(getResources().getColor(R.color.white));
                    text_thisweeks.setTextColor(getResources().getColor(R.color.blue));
                    text_thismonths.setTextColor(getResources().getColor(R.color.blue));
                    text_todays.setBackground(getResources().getDrawable(R.drawable.text_rounded));
                    text_thisweeks.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    text_thismonths.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    scope = "oneday";// 日期
                    status = "processed";// 状态
                    allList = new ArrayList<>();
                    FireAlarmRequest.getFireAlarm(FireAlarmActivity.this, getApplicationContext(), "1", status, scope);
                    break;
                case 3:
                    text_todays.setTextColor(getResources().getColor(R.color.blue));
                    text_thisweeks.setTextColor(getResources().getColor(R.color.white));
                    text_thismonths.setTextColor(getResources().getColor(R.color.blue));
                    text_todays.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    text_thisweeks.setBackground(getResources().getDrawable(R.drawable.text_rounded));
                    text_thismonths.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    scope = "sevendays";// 日期
                    status = "processed";// 状态
                    allList = new ArrayList<>();
                    FireAlarmRequest.getFireAlarm(FireAlarmActivity.this, getApplicationContext(), "1", status, scope);
                    break;
                case 4:
                    text_todays.setTextColor(getResources().getColor(R.color.blue));
                    text_thisweeks.setTextColor(getResources().getColor(R.color.blue));
                    text_thismonths.setTextColor(getResources().getColor(R.color.white));
                    text_todays.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    text_thisweeks.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    text_thismonths.setBackground(getResources().getDrawable(R.drawable.text_rounded));
                    scope = "thirtydays";// 日期
                    status = "processed";// 状态
                    allList = new ArrayList<>();
                    FireAlarmRequest.getFireAlarm(FireAlarmActivity.this, getApplicationContext(), "1", status, scope);
                    break;
                case 5:
                    text_today.setTextColor(getResources().getColor(R.color.white));
                    text_thisweek.setTextColor(getResources().getColor(R.color.blue));
                    text_today.setBackground(getResources().getDrawable(R.drawable.text_rounded));
                    text_thisweek.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    scope = "oneday";// 日期
                    status = "pending";// 状态
                    allList = new ArrayList<>();
                    FireAlarmRequest.getFireAlarm(FireAlarmActivity.this, getApplicationContext(), "1", status, scope);
                    break;
                case 6:
                    text_today.setTextColor(getResources().getColor(R.color.blue));
                    text_thisweek.setTextColor(getResources().getColor(R.color.white));
                    text_today.setBackground(getResources().getDrawable(R.drawable.text_roundeds));
                    text_thisweek.setBackground(getResources().getDrawable(R.drawable.text_rounded));
                    scope = "sevendays";// 日期
                    status = "pending";// 状态
                    allList = new ArrayList<>();
                    FireAlarmRequest.getFireAlarm(FireAlarmActivity.this, getApplicationContext(), "1", status, scope);
                    break;
                case 99:
                    finish();
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
        firealarmlistview = findViewById(R.id.firealarmlistview);
        allList.addAll(list);
        fireAlarmAdapter.bindData(FireAlarmActivity.this, allList, type, mHandler);
        if (pageNo == 1) {
            // 没有数据就提示暂无数据。
            firealarmlistview.setEmptyView(findViewById(R.id.nodata));
            firealarmlistview.setAdapter(fireAlarmAdapter);
        }
        fireAlarmAdapter.notifyDataSetChanged();
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
                    FireAlarmRequest.getFireAlarm(FireAlarmActivity.this, getApplicationContext(), pageNo + "", status, scope);
                } else if (pageNo > allpage && finish) {
                    finish = false;
                    // 如果pageNo>allpage则表示，服务端没有更多的数据可供加载了。
                    Toast.makeText(FireAlarmActivity.this, "加载完了！", Toast.LENGTH_SHORT).show();
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


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message message) {
            final Bundle data = message.getData();
            switch (message.what) {
                // 立即处理
                case MyApplication.MSGHANDLE:
                    int selIndex = data.getInt("selIndex");
                    FireAlarmBean.DataBean.ListBean bean = allList.get(selIndex);
                    String taskId = bean.getIds();
                    fireConfirmDialog = new FireConfirmDialog(FireAlarmActivity.this, taskId, mHandler);
                    fireConfirmDialog.show();
                    break;
                // 查看监控
                case MyApplication.MSGViewMonitoring:
//                    int selIndexs = data.getInt("selIndex");
//                    Intent intent_Inspection = new Intent(FireAlarmActivity.this, MonitorVideoActivity.class);
//                    startActivity(intent_Inspection);
                    break;
                // 位置查看
                case MyApplication.MSGViewlocation:
//                    int selIndex_p = data.getInt("selIndex");
//                    Intent intent_mapfunction = new Intent(FireAlarmActivity.this, MapFunctionActivity.class);
//                    startActivity(intent_mapfunction);
                    break;
                // 火警确认成功
                case MyApplication.MSGConfirmSuccess:
                    new SVProgressHUD(FireAlarmActivity.this).showSuccessWithStatus("火警确认成功");
                    allList = new ArrayList<>();
                    FireAlarmRequest.getFireAlarm(FireAlarmActivity.this, getApplicationContext(), "1", status, scope);
                    break;
                // 火警确认失败
                case MyApplication.MSGConfirmFailure:
                    new SVProgressHUD(FireAlarmActivity.this).showErrorWithStatus("火警确认失败");
                    break;

                default:
                    break;
            }
        }
    };

}
