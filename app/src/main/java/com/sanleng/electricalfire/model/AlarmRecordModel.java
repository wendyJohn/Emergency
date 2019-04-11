package com.sanleng.electricalfire.model;

import java.util.List;
import java.util.Map;

public interface AlarmRecordModel {
    void AlarmRecordSuccess(List<Map<String, Object>> list, int size);
    void AlarmRecordFailed();
}
