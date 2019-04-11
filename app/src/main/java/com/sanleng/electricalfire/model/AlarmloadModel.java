package com.sanleng.electricalfire.model;

public interface AlarmloadModel {
    void AlarmloadSuccess(int unhandlefire, int todayfire, int truefire, int missfire, int weekfire);
    void AlarmloadFailed();
}
