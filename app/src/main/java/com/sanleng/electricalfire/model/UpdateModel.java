package com.sanleng.electricalfire.model;

public interface UpdateModel {
    void UpdateSuccess(String version, String path,String appDescribe);
    void UpdateFailed();
}
