package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
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
import com.sanleng.electricalfire.Presenter.FireAlarmRequest;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.dialog.FireConfirmDialog;
import com.sanleng.electricalfire.model.FireAlarmModel;
import com.sanleng.electricalfire.ui.adapter.FireAlarmAdapter;
import com.sanleng.electricalfire.ui.bean.FireAlarmBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 火警信息
 *
 * @author Qiaoshi
 */
public class FireAlarmsActivity extends BaseActivity implements FireAlarmModel {
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
    private String status = "processed";// 状态
    private String type = "已处理";// 状态

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        this.setContentView(R.layout.firealarmsactivity);
        initview();
        scope = "oneday";// 日期
        status = "processed";// 状态
        allList = new ArrayList<>();
        FireAlarmRequest.getFireAlarm(FireAlarmsActivity.this, getApplicationContext(), "1", status, scope);
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
        r_back = findViewById(R.id.r_back);
        r_back.setOnClickListener(new MyOnClickListener(99));
    }

    @Override
    public void FireAlarmSuccess(List<FireAlarmBean.DataBean.ListBean> list, int size) {
        loadData(size, list);
    }

    @Override
    public void FireSuccess(List<String> info) {

    }

    @Override
    public void FireAlarmFailed() {
        new SVProgressHUD(FireAlarmsActivity.this).showErrorWithStatus("加载失败");
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
        fireAlarmAdapter.bindData(FireAlarmsActivity.this, allList, type, mHandler);
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
                    FireAlarmRequest.getFireAlarm(FireAlarmsActivity.this, getApplicationContext(), pageNo + "", status, scope);
                } else if (pageNo > allpage && finish) {
                    finish = false;
                    // 如果pageNo>allpage则表示，服务端没有更多的数据可供加载了。
                    Toast.makeText(FireAlarmsActivity.this, "加载完了！", Toast.LENGTH_SHORT).show();
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
                    fireConfirmDialog = new FireConfirmDialog(FireAlarmsActivity.this, taskId, mHandler);
                    fireConfirmDialog.show();
                    break;
                // 查看监控
                case MyApplication.MSGViewMonitoring:
                    Intent intent_Inspection = new Intent(FireAlarmsActivity.this, MonitorsActivity.class);
                    startActivity(intent_Inspection);
                    break;
                // 位置查看
                case MyApplication.MSGViewlocation:
                    Intent intent_mapfunction = new Intent(FireAlarmsActivity.this, EmergencyRescueActivity.class);
                    startActivity(intent_mapfunction);
                    break;
                // 火警确认成功
                case MyApplication.MSGConfirmSuccess:
                    new SVProgressHUD(FireAlarmsActivity.this).showSuccessWithStatus("火警确认成功");
                    allList = new ArrayList<>();
                    FireAlarmRequest.getFireAlarm(FireAlarmsActivity.this, getApplicationContext(), "1", status, scope);
                    String staus = data.getString("staus");
                    if(staus.equals("101")){
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                // 等待1000毫秒后跳到应急救援界面。
                                Intent intent_EmergencyRescue = new Intent(FireAlarmsActivity.this, EmergencyRescueActivity.class);
                                startActivity(intent_EmergencyRescue);
                            }
                        }, 1000);
                    }
                    break;
                // 火警确认失败
                case MyApplication.MSGConfirmFailure:
                    new SVProgressHUD(FireAlarmsActivity.this).showErrorWithStatus("火警确认失败");
                    break;
                // 拨打119电话
                case MyApplication.MSGFiretelePhone:
                    Intent dialIntent =  new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + 119));//直接拨打电话
                    startActivity(dialIntent);
                    break;
                // 处理上报
                case MyApplication.MSGProcessingReport:
                    int selIndexs = data.getInt("selIndex");
                    FireAlarmBean.DataBean.ListBean beans = allList.get(selIndexs);
                    String ids = beans.getIds();
                    Intent intent_Patrol = new Intent(FireAlarmsActivity.this, ProcessingReportActivity.class);
                    intent_Patrol.putExtra("ids", ids);
                    startActivity(intent_Patrol);
                    break;
                // 上报详情
                case MyApplication.MSGReportingDetails:
                    int rselIndexs = data.getInt("selIndex");
                    FireAlarmBean.DataBean.ListBean rbeans = allList.get(rselIndexs);
                    String rids = rbeans.getIds();
                    Intent intent_ReportingDetails = new Intent(FireAlarmsActivity.this, ReportingDetailsActivity.class);
                    intent_ReportingDetails.putExtra("ids", rids);
                    startActivity(intent_ReportingDetails);
                    break;

                default:
                    break;
            }
        }
    };

}
