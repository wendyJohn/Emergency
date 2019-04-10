package com.sanleng.electricalfire.Presenter;

import java.util.List;
import java.util.Map;

public interface AlarmRecordPresenter {
    void AlarmRecordSuccess(List<Map<String, Object>> list, int size);
    void AlarmRecordFailed();
}
