package com.sanleng.electricalfire.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.sanleng.electricalfire.R;

/**
 * 远程监控
 * @author Qiaoshi
 */
public class RemoteMonitoringFragment extends BaseFragment implements OnClickListener {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.remotemonitoringfragment, null);
        initview();
        return view;
    }

    //初始化
    private void initview() {




    }


}
