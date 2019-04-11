package com.sanleng.electricalfire.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.Presenter.AlarmLoadRequest;
import com.sanleng.electricalfire.model.AlarmloadModel;
import com.sanleng.electricalfire.myview.DataItem;
import com.sanleng.electricalfire.myview.DiscView;
import com.sanleng.electricalfire.ui.activity.FireAlarmActivity;
import com.sanleng.electricalfire.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 火警信息
 *
 * @author Qiaoshi
 */
@SuppressLint("ResourceAsColor")
public class FireAlarmFragment extends BaseFragment implements AlarmloadModel {

    private TextView tab_1, tab_2;// 选项名称
    private View view;
    private TextView firealarminformation;
    DiscView discView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.firealarmfragment, null);
        initview();
        loadData();
        return view;
    }

    private void initview() {
        tab_1 = view.findViewById(R.id.tab_1);
        tab_2 = view.findViewById(R.id.tab_2);
        firealarminformation = view.findViewById(R.id.firealarminformation);
        firealarminformation.setOnClickListener(new MyOnClickListener(0));
        discView = view.findViewById(R.id.disc);
        List<DataItem> items = new ArrayList<>();
        items.add(new DataItem(-1, "未处理火警", "-1", getResources().getColor(R.color.copa)));
        items.add(new DataItem(-1, "真实火警", "-1", getResources().getColor(R.color.copc)));
        items.add(new DataItem(-1, "误报火警", "-1", getResources().getColor(R.color.copb)));
        discView.setItems(items);

    }

    // 加载数据
    private void loadData() {
        AlarmLoadRequest.GetAlarmLoad(FireAlarmFragment.this, getActivity(), PreferenceUtils.getString(getActivity(), "unitcode"), PreferenceUtils.getString(getActivity(), "ElectriFire_username"), "app_firecontrol_owner");
    }

    @Override
    public void AlarmloadSuccess(int unhandlefire, int todayfire, int truefire, int missfire, int weekfire) {
        tab_1.setText(unhandlefire + "");
        tab_2.setText(todayfire + "");
        List<DataItem> items = new ArrayList<>();
        items.add(new DataItem(weekfire, "未处理火警", weekfire + "", getResources().getColor(R.color.copa)));
        items.add(new DataItem(truefire, "真实火警", truefire + "", getResources().getColor(R.color.copc)));
        items.add(new DataItem(missfire, "误报火警", missfire + "", getResources().getColor(R.color.copb)));
        discView.setItems(items);
    }

    @Override
    public void AlarmloadFailed() {
        new SVProgressHUD(getActivity()).showErrorWithStatus("统计数据加载失败");
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
                    Intent intent_fireAlarm = new Intent(getActivity(), FireAlarmActivity.class);
                    startActivity(intent_fireAlarm);
                    break;

            }

        }
    }

}
