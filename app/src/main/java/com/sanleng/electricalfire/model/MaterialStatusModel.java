package com.sanleng.electricalfire.model;

public interface MaterialStatusModel {


    void WarehousingSuccess(String msg);
    void WarehousingFailed();

    void OutOfStockSuccess(String msg);
    void OutOfStockFailed();

    void ReportLossSuccess(String msg);
    void ReportLossFailed();
}
