package com.sanleng.electricalfire.model;

import com.sanleng.electricalfire.ui.bean.StationBean;

import java.util.List;
import java.util.Map;

public interface MaterialsListModel {
    void MaterialsListSuccess(List<StationBean> list, String stationId, String mac,String name,  String address, double distance);

    void MaterialsListFailed();
}
