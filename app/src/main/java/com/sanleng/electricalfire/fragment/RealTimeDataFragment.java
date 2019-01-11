package com.sanleng.electricalfire.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.adapter.RealTimeDataAdapter;
import com.sanleng.electricalfire.bean.ERealTimeDataBean;
import com.sanleng.electricalfire.myview.MarqueeViews;
import com.sanleng.electricalfire.net.NetCallBack;
import com.sanleng.electricalfire.net.RequestUtils;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.util.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 智能电气火灾实时数据
 *
 * @author Qiaoshi
 */
public class RealTimeDataFragment extends BaseFragment implements OnClickListener {
    private ListView realtimedatalslistview;
    private RealTimeDataAdapter realtimedataAdapter;//(有数据版)
    private View view;
    private Receivers receivers;
    private static final String BROADCAST_PERMISSION_DISC = "com.permissions.MY_BROADCAST";
    private static final String BROADCAST_ACTION_DISC = "com.permissions.my_broadcast";
    private ImageView query_im;
    private boolean state = true;

    private LinearLayout yaout;
    private ImageView imageView_item;
    private MarqueeViews marqueeviews;

    private List<ERealTimeDataBean> onelist;
    private List<ERealTimeDataBean> allList;
    private int pageNo = 1;// 设置pageNo的初始化值为1，即默认获取的是第一页的数据。
    private int allpage;
    private boolean is_divPage;// 是否进行分页操作
    private boolean finish = true;// 是否加载完成;
    private List<String> info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.realtimedatafragment, null);
        initview();
        return view;
    }

    //初始化
    private void initview() {
        query_im = view.findViewById(R.id.query_im);
        query_im.setOnClickListener(this);
        yaout = view.findViewById(R.id.yaout);
        imageView_item = view.findViewById(R.id.imageView_item);

        receivers = new Receivers();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION_DISC); // 只有持有相同的action的接受者才能接收此广
        getActivity().registerReceiver(receivers, intentFilter, BROADCAST_PERMISSION_DISC, null);
        marqueeviews = (MarqueeViews) view.findViewById(R.id.marqueeviews);
        realtimedataAdapter = new RealTimeDataAdapter();
    }

    @Override
    public void onResume() {
        allList = new ArrayList<>();
        pageNo = 1;
        loadData(1);
        addPolice();
        super.onResume();
    }

    // 加载数据
    private void loadData(int page) {
        onelist = new ArrayList<>();
        RequestParams params = new RequestParams();
        params.put("pageNum", page + "");
        params.put("pageSize", "10");
        params.put("unit_id", PreferenceUtils.getString(getActivity(), "unitcode"));
        params.put("username", PreferenceUtils.getString(getActivity(), "ElectriFire_username"));
        params.put("platformkey", "app_firecontrol_owner");

        params.put("state", "");
        params.put("device_name", "");
        params.put("buildids", "");
        params.put("floorids", "");

        RequestUtils.ClientPost(URLs.DeviceItem_URL, params, new NetCallBack() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onMySuccess(String result) {
                if (result == null || result.length() == 0) {
                    return;
                }
                System.out.println("数据请求成功" + result);
                try {
                    int length = 10;
                    int SIZE = 0;
                    JSONObject jsonObject = new JSONObject(result);
                    String msg = jsonObject.getString("msg");
                    if (msg.equals("获取成功")) {
                        String data = jsonObject.getString("data");
                        JSONObject objects = new JSONObject(data);
                        String listsize = objects.getString("total");
                        SIZE = Integer.parseInt(listsize);
                        String list = objects.getString("list");

                        JSONArray array = new JSONArray(list);
                        JSONObject object;
                        for (int i = 0; i < array.length(); i++) {
                            ERealTimeDataBean bean = new ERealTimeDataBean();
                            object = (JSONObject) array.get(i);
                            String device_id = object.getString("device_id");
                            String unit_name = object.getString("unit_name");
                            String build_name = object.getString("build_name");
                            String device_name = object.getString("device_name");
                            String state = object.getString("state");
                            String contact_name = object.getString("contact_name");
                            String contact_tel = object.getString("contact_tel");

                            bean.setId(device_id);
                            bean.setAddress(unit_name + build_name + "\n" + device_name);
                            bean.setContact_name(contact_name);
                            bean.setContact_tel(contact_tel);
                            bean.setState(state);
                            onelist.add(bean);
                        }

                        if (SIZE % length == 0) {
                            allpage = SIZE / length;
                        } else {
                            allpage = SIZE / length + 1;
                        }
                        realtimedatalslistview = view.findViewById(R.id.realtimedatalslistview);
                        allList.addAll(onelist);
                        realtimedataAdapter.bindData(getActivity(), allList, mHandler);
                        if (pageNo == 1) {
                            // 没有数据就提示暂无数据。
                            realtimedatalslistview.setEmptyView(view.findViewById(R.id.nodata));
                            realtimedatalslistview.setAdapter(realtimedataAdapter);
                        }
                        realtimedataAdapter.notifyDataSetChanged();
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
                                    loadData(pageNo);
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

                        realtimedatalslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                            }
                        });

                    }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyFailure(Throwable arg0) {

            }
        });

    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            final Bundle data = message.getData();
            switch (message.what) {
                // 拍照确认
                case 66660:
                    int selIndex = data.getInt("selIndex");

                    break;
                //待处理
                case 66661:
                    int selIndexs = data.getInt("selIndex");

                    break;
                //历史轨迹
                case 66662:
                    int selIndex_p = data.getInt("selIndex");


                    break;
                default:
                    break;
            }
        }
    };


    // 收到报警广播处理，刷新界面
    public class Receivers extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BROADCAST_ACTION_DISC)) {
                System.out.println("收到； 1111111111111111");
                //刷新数据
                pageNo = 1;
                loadData(1);
                addPolice();
            }

        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.query_im:
                if (state) {
                    yaout.setVisibility(View.VISIBLE);
                    imageView_item.setVisibility(View.GONE);
                    state = false;
                } else {
                    yaout.setVisibility(View.GONE);
                    imageView_item.setVisibility(View.VISIBLE);
                    state = true;
                }
                break;
            default:
                break;
        }
    }


    //获取报警信息
    private void addPolice() {
        info = new ArrayList<>();
        RequestParams params = new RequestParams();
        params.put("pageNum", "1");
        params.put("pageSize", "100");
        params.put("unit_code", PreferenceUtils.getString(getActivity(), "unitcode"));
        params.put("username", PreferenceUtils.getString(getActivity(), "ElectriFire_username"));
        params.put("platformkey", "app_firecontrol_owner");

        RequestUtils.ClientPost(URLs.Police_URL, params, new NetCallBack() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onMySuccess(String result) {
                if (result == null || result.length() == 0) {
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String msg = jsonObject.getString("msg");
                    if (msg.equals("获取成功")) {
                        String data = jsonObject.getString("data");
                        JSONObject objects = new JSONObject(data);
                        String list = objects.getString("list");
                        JSONArray array = new JSONArray(list);
                        JSONObject object;
                        for (int i = 0; i < array.length(); i++) {
                            object = (JSONObject) array.get(i);
                            String build_name = object.getString("build_name");
                            String detector_name = object.getString("detector_name");
                            String device_name = object.getString("device_name");
                            String str = build_name + device_name + detector_name + "异常";
                            info.add(str);
                        }
                        // 在代码里设置自己的动画
                        marqueeviews.startWithList(info);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onMyFailure(Throwable arg0) {

            }
        });
    }


}
