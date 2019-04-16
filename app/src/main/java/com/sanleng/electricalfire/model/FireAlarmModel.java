package com.sanleng.electricalfire.model;

import com.sanleng.electricalfire.ui.bean.FireAlarmBean;

import java.util.List;

public interface FireAlarmModel {

    void FireAlarmSuccess(List<FireAlarmBean.DataBean.ListBean> list, int size);
    void FireSuccess(List<String> info);
    void FireAlarmFailed();


}
