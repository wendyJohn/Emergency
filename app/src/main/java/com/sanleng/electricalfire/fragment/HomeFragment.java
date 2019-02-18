package com.sanleng.electricalfire.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.activity.FirsafetyAtivity;
import com.sanleng.electricalfire.activity.MainActivity;
import com.sanleng.electricalfire.activity.SafetyPatrolAtivity;
import com.sanleng.electricalfire.activity.SearchActivity;
import com.sanleng.electricalfire.adapter.HomeAdapter;
import com.sanleng.electricalfire.bean.UserBean;
import com.sanleng.electricalfire.data.HomeData;
import com.sanleng.electricalfire.myview.ZQScrollGridView;
import com.sanleng.electricalfire.util.ACache;
import com.sanleng.electricalfire.util.UtilFileDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
@SuppressLint("InflateParams")
public class HomeFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Intent intent;
    private ZQScrollGridView gridView;
    private static HomeAdapter adapter;
    public static List<Integer> listPosition;
    public static List<UserBean> list;
    private static ACache aCache;
    private TextView emtext_a, emtext_b, emtext_c, emtext_d, emtext_e, emtext_f, emtext_g, emtext_h;

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
        emtext_a.setOnClickListener(this);
        emtext_b.setOnClickListener(this);
        emtext_c.setOnClickListener(this);
        emtext_d.setOnClickListener(this);
        emtext_e.setOnClickListener(this);
        emtext_f.setOnClickListener(this);
        emtext_g.setOnClickListener(this);
        emtext_h.setOnClickListener(this);
        onLoad();
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
            //物资信息
            case R.id.emtext_a:

                break;
            //救援力量
            case R.id.emtext_b:

                break;
            //应急救援
            case R.id.emtext_c:

                break;
            //应急调度
            case R.id.emtext_d:

                break;
            //应急预案
            case R.id.emtext_e:

                break;
            //一键救援
            case R.id.emtext_f:

                break;
            //处置报告
            case R.id.emtext_g:

                break;
            //应急小程序
            case R.id.emtext_h:

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

                }
                if (name.equals("消防安全")) {
                Intent intent_Firsafety =new Intent(getActivity(),FirsafetyAtivity.class);
                startActivity(intent_Firsafety);
                }
                if (name.equals("安防监控")) {

                }
                if (name.equals("实验室安全")) {

                }
                if (name.equals("安全巡查")) {
                    Intent intent_SafetyPatrol =new Intent(getActivity(),SafetyPatrolAtivity.class);
                    startActivity(intent_SafetyPatrol);
                }
                if (name.equals("预警事件")) {

                }
                if (name.equals("地图监控")) {

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
            MainActivity.handler.sendMessage(msg);
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
        list = new ArrayList<UserBean>();
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

        HomeFragment.list.get(7).setIsvisibility(false);
        notifyData();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}
