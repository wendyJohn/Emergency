package com.sanleng.electricalfire.ui.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sanleng.electricalfire.MyApplication;
import com.sanleng.electricalfire.Presenter.FireAlarmRequest;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.data.HomeData;
import com.sanleng.electricalfire.dialog.FireTipsDialog;
import com.sanleng.electricalfire.dialog.SosDialog;
import com.sanleng.electricalfire.model.FireAlarmModel;
import com.sanleng.electricalfire.myview.MarqueeViews;
import com.sanleng.electricalfire.myview.ZQScrollGridView;
import com.sanleng.electricalfire.ui.activity.ArticleActivity;
import com.sanleng.electricalfire.ui.activity.EmergencyRescueActivity;
import com.sanleng.electricalfire.ui.activity.EmergencyStationActivity;
import com.sanleng.electricalfire.ui.activity.FireAlarmActivity;
import com.sanleng.electricalfire.ui.activity.FireAlarmsActivity;
import com.sanleng.electricalfire.ui.activity.FirsafetyAtivity;
import com.sanleng.electricalfire.ui.activity.HazardousChemicalsActivity;
import com.sanleng.electricalfire.ui.activity.MainTabActivity;
import com.sanleng.electricalfire.ui.activity.MapMonitoringActivity;
import com.sanleng.electricalfire.ui.activity.MonStationActivity;
import com.sanleng.electricalfire.ui.activity.MonitorsActivity;
import com.sanleng.electricalfire.ui.activity.RealDataActivity;
import com.sanleng.electricalfire.ui.activity.SafetyPatrolAtivity;
import com.sanleng.electricalfire.ui.activity.SearchActivity;
import com.sanleng.electricalfire.ui.activity.VideoPlayerActivity;
import com.sanleng.electricalfire.ui.adapter.HomeAdapter;
import com.sanleng.electricalfire.ui.bean.FireAlarmBean;
import com.sanleng.electricalfire.ui.bean.UserBean;
import com.sanleng.electricalfire.util.ACache;
import com.sanleng.electricalfire.util.UtilFileDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
@SuppressLint("InflateParams")
public class HomeFragment extends Fragment implements View.OnClickListener, FireAlarmModel {

    private View view;
    private Intent intent;
    private ZQScrollGridView gridView;
    private static HomeAdapter adapter;
    public static List<Integer> listPosition;
    public static List<UserBean> list;
    private static ACache aCache;
    private TextView emtext_a, emtext_b, emtext_c, emtext_d, emtext_e, emtext_f, emtext_g, emtext_h,emtext_k;
    private LinearLayout article;
    private LinearLayout video;
    private MarqueeViews marqueeviews;
    private Receivers receivers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        view = getView();
        initView();
        addFrieData();
    }

    public void initView() {
        aCache = ACache.get(getActivity());
        gridView = view.findViewById(R.id.home_gridview);
        emtext_a = view.findViewById(R.id.emtext_a);
        emtext_b = view.findViewById(R.id.emtext_b);
        emtext_c = view.findViewById(R.id.emtext_c);
        emtext_d = view.findViewById(R.id.emtext_d);
        emtext_e = view.findViewById(R.id.emtext_e);
        emtext_f = view.findViewById(R.id.emtext_f);
        emtext_g = view.findViewById(R.id.emtext_g);
        emtext_h = view.findViewById(R.id.emtext_h);
        emtext_k = view.findViewById(R.id.emtext_k);
        article = view.findViewById(R.id.article);
        video = view.findViewById(R.id.video);
        emtext_a.setOnClickListener(this);
        emtext_b.setOnClickListener(this);
        emtext_c.setOnClickListener(this);
        emtext_d.setOnClickListener(this);
        emtext_e.setOnClickListener(this);
        emtext_f.setOnClickListener(this);
        emtext_g.setOnClickListener(this);
        emtext_h.setOnClickListener(this);
        emtext_k.setOnClickListener(this);
        article.setOnClickListener(this);
        video.setOnClickListener(this);
        onLoad();
        marqueeviews = view.findViewById(R.id.marqueeviews);
        marqueeviews.setOnClickListener(this);

        receivers = new Receivers();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyApplication.BROADCAST_ACTIONS_DISC); // 只有持有相同的action的接受者才能接收此广
        getActivity().registerReceiver(receivers, intentFilter, MyApplication.BROADCAST_PERMISSIONS_DISC, null);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    public void onLoad() {
        adapter = new HomeAdapter(getActivity());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(onItemClickListener);
        gridView.setOnItemLongClickListener(onItemLongClickListener);
        showData();
    }

    /*****
     * 刷新数据
     */
    private void notifyData() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //物资管理
            case R.id.emtext_a:
//                Intent intent_MonStation = new Intent(getActivity(), MonStationActivity.class);
//                startActivity(intent_MonStation);
                Intent intent_emergencystation = new Intent(getActivity(), EmergencyStationActivity.class);
                intent_emergencystation.putExtra("mode", "物资查询");
                startActivity(intent_emergencystation);
                break;
            //志愿者
            case R.id.emtext_b:
                Intent intent_HazardousChemicals = new Intent(getActivity(), HazardousChemicalsActivity.class);
                intent_HazardousChemicals.putExtra("name", "志愿者");
                startActivity(intent_HazardousChemicals);
                break;
            //应急救援
            case R.id.emtext_c:
                Intent intent_EmergencyRescue = new Intent(getActivity(), EmergencyRescueActivity.class);
                startActivity(intent_EmergencyRescue);
                break;
            //物资调度
            case R.id.emtext_d:
//                Intent intent_HazardousChemicalss = new Intent(getActivity(), HazardousChemicalsActivity.class);
//                intent_HazardousChemicalss.putExtra("name", "物资调度");
//                startActivity(intent_HazardousChemicalss);

                Intent intent_emergencyStation = new Intent(getActivity(), EmergencyStationActivity.class);
                intent_emergencyStation.putExtra("mode", "应急开门");
                startActivity(intent_emergencyStation);
                break;
            //站点监控
            case R.id.emtext_e:
                Intent intent_MonitorsStation = new Intent(getActivity(), EmergencyStationActivity.class);
                intent_MonitorsStation.putExtra("mode", "视频监控");
                startActivity(intent_MonitorsStation);
                break;
            //一键救援
            case R.id.emtext_f:
                SosDialog sosDialog = new SosDialog(getActivity());
                sosDialog.show();
                break;
            //历史记录
            case R.id.emtext_k:
                Intent intent_Historicalrecord = new Intent(getActivity(), EmergencyStationActivity.class);
                intent_Historicalrecord.putExtra("mode", "历史纪录");
                startActivity(intent_Historicalrecord);
                break;
            //处置报告
            case R.id.emtext_g:

                break;
            //应急小程序
            case R.id.emtext_h:

                break;
            //文章
            case R.id.article:
                Intent intent_Article = new Intent(getActivity(), ArticleActivity.class);
                startActivity(intent_Article);
                break;
            //视频
            case R.id.video:
                Intent intent_VideoPlayer = new Intent(getActivity(), VideoPlayerActivity.class);
                startActivity(intent_VideoPlayer);
                break;
            //火警提示查看火警详情
            case R.id.marqueeviews:
                Intent intent_FireAlarm = new Intent(getActivity(), FireAlarmActivity.class);
                startActivity(intent_FireAlarm);
                break;

        }
    }

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                showLongClick(false);
            } else if (msg.what == 2)// 删除
            {
                showDelete();
            }
        }
    };

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == (listPosition.size() - 1)) {
                intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("list", (Serializable) listPosition);
                startActivityForResult(intent, 1);
            } else {
                UserBean bean = list.get(listPosition.get(position));
                String name = bean.getTitle();
                if (name.equals("电气安全")) {
                    Intent intent_RealTimeData = new Intent(getActivity(), RealDataActivity.class);
                    startActivity(intent_RealTimeData);
                }
                if (name.equals("消防安全")) {
                    Intent intent_Firsafety = new Intent(getActivity(), FirsafetyAtivity.class);
                    startActivity(intent_Firsafety);
                }
                if (name.equals("安防监控")) {
                    Intent intent_Monitor = new Intent(getActivity(), MonitorsActivity.class);
                    startActivity(intent_Monitor);
                }
                if (name.equals("危化品柜")) {
                    Intent intent_HazardousChemicals = new Intent(getActivity(), HazardousChemicalsActivity.class);
                    intent_HazardousChemicals.putExtra("name", "危化品柜");
                    startActivity(intent_HazardousChemicals);
                }
                if (name.equals("安全巡查")) {
                    Intent intent_SafetyPatrol = new Intent(getActivity(), SafetyPatrolAtivity.class);
                    startActivity(intent_SafetyPatrol);
                }
                if (name.equals("预警事件")) {
                    Intent intent_HazardousChemicals = new Intent(getActivity(), HazardousChemicalsActivity.class);
                    intent_HazardousChemicals.putExtra("name", "预警事件");
                    startActivity(intent_HazardousChemicals);
                }
                if (name.equals("地图监控")) {
                    Intent intent_MapMonitoring = new Intent(getActivity(), MapMonitoringActivity.class);
                    startActivity(intent_MapMonitoring);
                }
                if (name.equals("处理上报")) {
                    Intent intent_FireAlarms = new Intent(getActivity(), FireAlarmsActivity.class);
                    startActivity(intent_FireAlarms);
                }
            }
        }
    };
    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == (listPosition.size() - 1)) {
                return false;
            }
            showLongClick(true);
            Message msg = new Message();
            msg.what = 1;// 删除
            msg.obj = 1;
            MainTabActivity.handler.sendMessage(msg);
            return false;
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 1) {
                if (data.getStringExtra("key").equals("2")) {
                    listPosition.clear();
                    listPosition = HomeData.POSITION(aCache);
                    notifyData();
                }
            }
        }
    }

    private void showData() {
        listPosition = HomeData.POSITION(aCache);
        list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            UserBean user = new UserBean(HomeData.IMG[i], HomeData.TITLE[i], false, false);
            list.add(user);
        }
        notifyData();
    }

    /***
     * 删除
     */
    private void showDelete() {
        // 删除缓存
        String home = "";
        for (int i = 0; i < listPosition.size() - 1; i++) {
            UserBean userBean = list.get(listPosition.get(i));
            if (!userBean.isCheck()) {
                home += listPosition.get(i) + ",";
            }
        }
        aCache.remove("home");
        listPosition.clear();
        try {
            UtilFileDB.ADDFile(aCache, "home", home.substring(0, (home.length() - 1)));
            if (listPosition == null || listPosition.size() <= 1) {
                listPosition = HomeData.POSITION(aCache);
            }
        } catch (Exception e) {
            listPosition.add((list.size() - 1));
        }
        showLongClick(false);
    }

    /****
     * 重新刷新数据
     *
     * @param isvisibility
     */
    private void showLongClick(boolean isvisibility) {
        list.clear();
        for (int i = 0; i < 8; i++) {
            UserBean user = new UserBean(HomeData.IMG[i], HomeData.TITLE[i], false, isvisibility);
            list.add(user);
        }

        HomeFragment.list.get(8).setIsvisibility(false);
        notifyData();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    private void addFrieData() {
        FireAlarmRequest.getFireAlarm(HomeFragment.this, getActivity(), "1", "pending", "oneday");
    }

    @Override
    public void FireAlarmSuccess(List<FireAlarmBean.DataBean.ListBean> list, int size) {

    }

    @Override
    public void FireSuccess(List<String> info) {
        marqueeviews.startWithList(info);
    }

    @Override
    public void FireAlarmFailed() {

    }

    // 收到报警广播处理，刷新界面
    public class Receivers extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(MyApplication.BROADCAST_ACTIONS_DISC)) {
                addFrieData();
            }
        }
    }
}
