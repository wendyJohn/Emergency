package com.sanleng.electricalfire.Presenter;

import com.sanleng.electricalfire.ui.bean.NearbyStation;
import com.sanleng.electricalfire.ui.bean.Sosbean;

import java.util.List;

public interface NearbyStationPresenter {

    void NearbyStationSuccess(List<NearbyStation.DataBean.ListBean> stationlist);

    void SosSuccess(List<Sosbean.DataBean.ListBean> soslist);

    void NearbyStationFailed();

}
