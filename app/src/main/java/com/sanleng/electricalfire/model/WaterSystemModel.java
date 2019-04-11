package com.sanleng.electricalfire.model;

import java.util.List;

public interface WaterSystemModel {

    void WaterSystemSuccess(List<com.sanleng.electricalfire.ui.bean.WaterSystem.DataBean.ListBean> list, int size);

    void WaterSystemNumberSuccess(int hyrant, int eqt, int water);

    void WaterSystemFailed();
}
