package com.sanleng.electricalfire.model;

import com.sanleng.electricalfire.ui.bean.ERealTimeDataBean;
import com.sanleng.electricalfire.ui.bean.ReadTimeItemData;

import java.util.List;

public interface RealTimeDataModel {

    void RealTimeDataSuccess(List<ERealTimeDataBean> list, int size);
    void RealTimeDatasSuccess(List<ERealTimeDataBean> list, int size);
    void RealDataItemSuccess(List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_temperature, List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_current, List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_residualcurrent, List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_voltage, String buildids, String floorids, String electricalDetectorInfos);

    void RealTimeDataFailed();
}
