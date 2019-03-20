package com.sanleng.electricalfire.util;

import com.sanleng.electricalfire.bean.ERealTimeDataBean;
import com.sanleng.electricalfire.bean.ReadTimeItemData;

import java.util.ArrayList;
import java.util.List;

public class MessageEvent {
    private int TAG;
    private String message;
    private List<ERealTimeDataBean> list;

    private List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_temperature;
    private List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_current;
    private List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_residualcurrent;
    private List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_voltage;

    private int position;
    private int size;
    private String electricalDetectorInfos;
    private String buildids;
    private String floorids;

    public MessageEvent(int TAG) {
        this.TAG = TAG;
    }

    public MessageEvent(int TAG, String message) {
        this.TAG = TAG;
        this.message = message;
    }

    public int getTAG() {
        return TAG;
    }

    public void setTAG(int TAG) {
        this.TAG = TAG;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ERealTimeDataBean> getList() {
        return list;
    }

    public void setList(List<ERealTimeDataBean> list) {
        this.list = list;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getElectricalDetectorInfos() {
        return electricalDetectorInfos;
    }

    public void setElectricalDetectorInfos(String electricalDetectorInfos) {
        this.electricalDetectorInfos = electricalDetectorInfos;
    }

    public String getBuildids() {
        return buildids;
    }

    public void setBuildids(String buildids) {
        this.buildids = buildids;
    }

    public String getFloorids() {
        return floorids;
    }

    public void setFloorids(String floorids) {
        this.floorids = floorids;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> getList_temperature() {
        return list_temperature;
    }

    public void setList_temperature(List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_temperature) {
        this.list_temperature = list_temperature;
    }

    public List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> getList_current() {
        return list_current;
    }

    public void setList_current(List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_current) {
        this.list_current = list_current;
    }

    public List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> getList_residualcurrent() {
        return list_residualcurrent;
    }

    public void setList_residualcurrent(List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_residualcurrent) {
        this.list_residualcurrent = list_residualcurrent;
    }

    public List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> getList_voltage() {
        return list_voltage;
    }

    public void setList_voltage(List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_voltage) {
        this.list_voltage = list_voltage;
    }
}
