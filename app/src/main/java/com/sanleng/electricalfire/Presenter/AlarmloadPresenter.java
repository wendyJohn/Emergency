package com.sanleng.electricalfire.Presenter;

public interface AlarmloadPresenter {
    void AlarmloadSuccess(int unhandlefire, int todayfire, int truefire, int missfire, int weekfire);
    void AlarmloadFailed();
}
