package com.sanleng.electricalfire.Presenter;

import com.sanleng.electricalfire.ui.bean.WaterSystem;

import java.util.List;

public interface WaterSystemPresenter {

    void WaterSystemSuccess(List<WaterSystem.DataBean.ListBean> list, int size);

    void WaterSystemNumberSuccess(int hyrant, int eqt,int water );

    void WaterSystemFailed();
}
