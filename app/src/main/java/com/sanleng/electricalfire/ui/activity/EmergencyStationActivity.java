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
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.electricalfire.Presenter.StationRequest;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.model.StationModel;
import com.sanleng.electricalfire.ui.adapter.EmergencystationAdapter;
import com.sanleng.electricalfire.ui.bean.ArchitectureBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 站点列表
 *
 * @author Qiaoshi
 */
public class EmergencyStationActivity extends BaseActivity implements StationModel {

    private RelativeLayout r_back;
    private ListView eslistview;
    private EmergencystationAdapter emergencystationAdapter;
    private int pageNo = 1;// 设置pageNo的初始化值为1，即默认获取的是第一页的数据。
    private int allpage;
    private List<ArchitectureBean> allList;// 存放所有数据AlarmBean的list集合
    private List<ArchitectureBean> onelist;// 存放一页数据实体类的Bean
    private boolean is_divPage;// 是否进行分页操作
    private boolean finish = true;// 是否加载完成;
    private String mode;
    private String channel_two;
    private String channel_one;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        this.setContentView(R.layout.emergencystationactivity);
        initview();
        StationRequest.getStation(EmergencyStationActivity.this, context, pageNo + "");
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.emergencystationactivity;
    }

    private void initview() {
        Intent intent = getIntent();
        mode = intent.getStringExtra("mode");
        emergencystationAdapter = new EmergencystationAdapter();
        allList = new ArrayList<>();
        r_back = findViewById(R.id.r_back);
        r_back.setOnClickListener(new MyOnClickListener(0));
    }

    // 加载数据
    private void loadData(List<ArchitectureBean> list, int size) {
        int length = 10;
        if (size % length == 0) {
            allpage = size / length;
        } else {
            allpage = size / length + 1;
        }
        eslistview = findViewById(R.id.eslistview);
        allList.addAll(list);
        emergencystationAdapter.bindData(context, allList);
        if (pageNo == 1) {
            // 没有数据就提示暂无数据。
            eslistview.setEmptyView(findViewById(R.id.nodata));
            eslistview.setAdapter(emergencystationAdapter);
        }
        emergencystationAdapter.notifyDataSetChanged();
        pageNo++;
        finish = true;
        eslistview.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /**
                 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=allpage（ 这里因为服务端有allpage页数据）时，加载更多数据。
                 */
                if (is_divPage && scrollState == OnScrollListener.SCROLL_STATE_IDLE && pageNo <= allpage
                        && finish) {
                    finish = false;
                    StationRequest.getStation(EmergencyStationActivity.this, context, pageNo + "");
                } else if (pageNo > allpage && finish) {
                    finish = false;
                    // 如果pageNo>allpage则表示，服务端没有更多的数据可供加载了。
                    Toast.makeText(context, "加载完了！", Toast.LENGTH_SHORT).show();
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

        eslistview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArchitectureBean bean = allList.get(position);
                String mac = bean.getMac();
                String ids = bean.getId();
                channel_one = bean.getChannel_one();
                channel_two = bean.getChannel_two();

                if (mode.equals("应急开门")) {
                    Intent intent = new Intent(EmergencyStationActivity.this,
                            EmergencyUnlockingActivity.class);
                    intent.putExtra("mac", mac);
                    startActivity(intent);
                }
                if (mode.equals("物资查询")) {
                    Intent intent = new Intent(EmergencyStationActivity.this, MaterialActivity.class);
                    intent.putExtra("ids", ids);
                    startActivity(intent);
                }
                if (mode.equals("视频监控")) {
                    Intent intent_Monitor = new Intent(EmergencyStationActivity.this, MonitorsActivity.class);
                    intent_Monitor.putExtra("channel_one", channel_one);
                    intent_Monitor.putExtra("channel_two", channel_two);
                    startActivity(intent_Monitor);
                }
                if (mode.equals("历史纪录")) {
                    Intent intent_Historicalrecord = new Intent(EmergencyStationActivity.this, HistoricalrecordActivity.class);
                    intent_Historicalrecord.putExtra("mac", mac);
                    startActivity(intent_Historicalrecord);
                }
            }
        });
    }


    @Override
    public void StationSuccess(List<ArchitectureBean> list, int size) {
        loadData(list,size);
    }

    @Override
    public void StationFailed() {
        new SVProgressHUD(EmergencyStationActivity.this).showErrorWithStatus("加载失败");
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
                    finish();
                    break;
            }
        }
    }

}
