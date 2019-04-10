package com.sanleng.electricalfire.Presenter;

import com.sanleng.electricalfire.ui.bean.FireAlarmBean;

import java.util.List;

public interface FireAlarmPresenter {

    void FireAlarmSuccess(List<FireAlarmBean.DataBean.ListBean> list, int size);
    void FireAlarmFailed();
}
