package com.sanleng.electricalfire.model;

import com.sanleng.electricalfire.ui.bean.Sosbean;

import java.util.List;

public interface NearbyStationModel {

    void NearbyStationSuccess(List<com.sanleng.electricalfire.ui.bean.NearbyStation.DataBean.ListBean> stationlist);

    void SosSuccess(List<Sosbean.DataBean.ListBean> soslist);

    void NearbyStationFailed();

}
