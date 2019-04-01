package com.sanleng.electricalfire.Presenter;

public interface UpdatePresenter {
    void UpdateSuccess(String version, String path);
    void UpdateFailed();
}
