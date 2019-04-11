package com.sanleng.electricalfire.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.Presenter.AlarmUpdateRequest;
import com.sanleng.electricalfire.Presenter.RealTimeDatasRequest;
import com.sanleng.electricalfire.model.RealTimeDataModel;
import com.sanleng.electricalfire.ui.adapter.RealDataAdapter;
import com.sanleng.electricalfire.ui.adapter.RealDatasAdapter;
import com.sanleng.electricalfire.ui.bean.ERealTimeDataBean;
import com.sanleng.electricalfire.Presenter.RealTimeDataRequest;
import com.sanleng.electricalfire.myview.MarqueeViews;
import com.sanleng.electricalfire.ui.bean.ReadTimeItemData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 智能电气火灾实时数据
 *
 * @author Qiaoshi
 */
public class RealDataActivity extends BaseActivity implements OnClickListener, RealTimeDataModel {
    @BindView(R.id.query_im)
    ImageView queryIm;
    @BindView(R.id.imageView_item)
    ImageView imageViewItem;
    @BindView(R.id.marqueeviews)
    MarqueeViews marqueeviews;
    @BindView(R.id.architecture)
    TextView architecture;
    @BindView(R.id.droponea)
    ImageView droponea;
    @BindView(R.id.spinner_architecture)
    RelativeLayout spinnerArchitecture;
    @BindView(R.id.floor)
    TextView floor;
    @BindView(R.id.droponeb)
    ImageView droponeb;
    @BindView(R.id.spinner_floor)
    RelativeLayout spinnerFloor;
    @BindView(R.id.box)
    TextView box;
    @BindView(R.id.droponec)
    ImageView droponec;
    @BindView(R.id.spinner_box)
    RelativeLayout spinnerBox;
    @BindView(R.id.equipment)
    TextView equipment;
    @BindView(R.id.droponed)
    ImageView droponed;
    @BindView(R.id.spinner_equipment)
    RelativeLayout spinnerEquipment;
    @BindView(R.id.yaout)
    LinearLayout yaout;
    @BindView(R.id.nodata)
    TextView nodata;
    RelativeLayout back;
    private RealDataAdapter realDataAdapter;//(有数据版)
    private RealDatasAdapter realDatasAdapter;//(无数据版)
    private boolean state = true;
    private List<ERealTimeDataBean> allList;
    private int pageNo = 1;// 设置pageNo的初始化值为1，即默认获取的是第一页的数据。
    private int allpage;
    private boolean is_divPage;// 是否进行分页操作
    private boolean finish = true;// 是否加载完成;
    private List<String> info;
    ListView realtimedatalslistview;
    private LinearLayout l_realdata, l_realdatas;// 选项名称
    private TextView tab_a, tab_b;// 选项名称


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.realdataactivity);
        initView();
        RealTimeDataRequest.getRealTimeData(RealDataActivity.this,getApplicationContext(),pageNo+"");
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.realdataactivity;
    }

    //初始化
    private void initView() {
        ButterKnife.bind(this);
        realDataAdapter = new RealDataAdapter();
        realDatasAdapter = new RealDatasAdapter();
        back = findViewById(R.id.back);
        l_realdata = findViewById(R.id.l_realdata);
        l_realdatas = findViewById(R.id.l_realdatas);
        tab_a = findViewById(R.id.tab_a);
        tab_b = findViewById(R.id.tab_b);
        back.setOnClickListener(this);
        l_realdata.setOnClickListener(this);
        l_realdatas.setOnClickListener(this);
        allList = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    // 加载有数据的
    private void addData(int size, List<ERealTimeDataBean> list) {
        int length = 10;
        if (size % length == 0) {
            allpage = size / length;
        } else {
            allpage = size / length + 1;
        }
        allList.addAll(list);
        realtimedatalslistview = findViewById(R.id.realtimedatalslistview);
        realDataAdapter.bindData(RealDataActivity.this, allList);
        if (pageNo == 1) {
            // 没有数据就提示暂无数据。
            realtimedatalslistview.setEmptyView(findViewById(R.id.nodata));
            realtimedatalslistview.setAdapter(realDataAdapter);
        }
        realDataAdapter.notifyDataSetChanged();
        pageNo++;
        finish = true;
        realtimedatalslistview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /**
                 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=allpage（ 这里因为服务端有allpage页数据）时，加载更多数据。
                 */
                if (is_divPage && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && pageNo <= allpage
                        && finish) {
                    finish = false;
                    RealTimeDataRequest.getRealTimeData(RealDataActivity.this,getApplicationContext(),pageNo+"");
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
        realtimedatalslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ERealTimeDataBean bean = allList.get(position);
                String device_id = bean.getId();
                String name = bean.getAddress();
                Intent intent = new Intent(RealDataActivity.this, RealDataItemActivity.class);
                intent.putExtra("deviceid", device_id);
                intent.putExtra("name", name);
                startActivity(intent);

            }
        });
    }


    // 加载无数据的（迅鹰设备）
    private void addDatas(int size, List<ERealTimeDataBean> list) {
        int length = 10;
        if (size % length == 0) {
            allpage = size / length;
        } else {
            allpage = size / length + 1;
        }
        allList.addAll(list);
        realtimedatalslistview = findViewById(R.id.realtimedatalslistview);
        realDatasAdapter.bindData(RealDataActivity.this, allList);
        if (pageNo == 1) {
            // 没有数据就提示暂无数据。
            realtimedatalslistview.setEmptyView(findViewById(R.id.nodata));
            realtimedatalslistview.setAdapter(realDatasAdapter);
        }
        realDatasAdapter.notifyDataSetChanged();
        pageNo++;
        finish = true;
        realtimedatalslistview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /**
                 * 当分页操作is_divPage为true时、滑动停止时、且pageNo<=allpage（ 这里因为服务端有allpage页数据）时，加载更多数据。
                 */
                if (is_divPage && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && pageNo <= allpage
                        && finish) {
                    finish = false;
                    RealTimeDatasRequest.getRealTimeDatas(RealDataActivity.this,getApplicationContext(),pageNo+"");
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

        realtimedatalslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ERealTimeDataBean bean = allList.get(position);
                String device_id = bean.getId();
                AlarmUpdateRequest.getAlarmUpdate(RealDataActivity.this,device_id);
                Intent intent = new Intent(RealDataActivity.this, AlarmRecordActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query_im:
                if (state) {
                    yaout.setVisibility(View.VISIBLE);
                    imageViewItem.setVisibility(View.GONE);
                    state = false;
                } else {
                    yaout.setVisibility(View.GONE);
                    imageViewItem.setVisibility(View.VISIBLE);
                    state = true;
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.l_realdata:
                tab_a.setTextColor(RealDataActivity.this.getResources().getColor(R.color.text_blue));
                tab_b.setTextColor(RealDataActivity.this.getResources().getColor(R.color.black));
                allList = new ArrayList<>();
                pageNo=1;
                RealTimeDataRequest.getRealTimeData(RealDataActivity.this,getApplicationContext(),pageNo+"");
                break;
            case R.id.l_realdatas:
                tab_a.setTextColor(RealDataActivity.this.getResources().getColor(R.color.black));
                tab_b.setTextColor(RealDataActivity.this.getResources().getColor(R.color.text_blue));
                allList = new ArrayList<>();
                pageNo=1;
                RealTimeDatasRequest.getRealTimeDatas(RealDataActivity.this,getApplicationContext(),pageNo+"");
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void RealTimeDataSuccess(List<ERealTimeDataBean> list, int size) {
        addData(size, list);
    }

    @Override
    public void RealTimeDatasSuccess(List<ERealTimeDataBean> list, int size) {
        addDatas(size, list);
    }

    @Override
    public void RealDataItemSuccess(List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_temperature, List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_current, List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_residualcurrent, List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_voltage, String buildids, String floorids, String electricalDetectorInfos) {

    }

    @Override
    public void RealTimeDataFailed() {
        new SVProgressHUD(RealDataActivity.this).showErrorWithStatus("数据请求失败");
    }
}
