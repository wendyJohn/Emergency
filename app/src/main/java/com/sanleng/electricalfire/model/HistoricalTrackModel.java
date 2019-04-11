package com.sanleng.electricalfire.model;

import java.util.HashMap;
import java.util.List;

public interface HistoricalTrackModel {

    void HistoricalTrackSuccess(List<HashMap<String, Object>> c_list, List<HashMap<String, Object>> h_list);

    void HistoricalTrackFailed();
}
