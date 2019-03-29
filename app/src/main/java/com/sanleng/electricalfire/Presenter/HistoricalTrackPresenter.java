package com.sanleng.electricalfire.Presenter;

import com.sanleng.electricalfire.ui.bean.ERealTimeDataBean;
import com.sanleng.electricalfire.ui.bean.ReadTimeItemData;

import java.util.HashMap;
import java.util.List;

public interface HistoricalTrackPresenter {

    void HistoricalTrackSuccess(List<HashMap<String, Object>> c_list, List<HashMap<String, Object>> h_list);

    void HistoricalTrackFailed();
}
