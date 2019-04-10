package com.sanleng.electricalfire.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanleng.electricalfire.R;

/**
 * 事件
 * @author Qiaoshi
 */
public class EventFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.eventfragment, null);
        initView();
        return view;
    }

    private void initView() {
    }

}
