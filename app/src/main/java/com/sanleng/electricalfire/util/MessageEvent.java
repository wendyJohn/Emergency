package com.sanleng.electricalfire.util;

import com.sanleng.electricalfire.ui.bean.ERealTimeDataBean;
import com.sanleng.electricalfire.ui.bean.HistorRecordBean;
import com.sanleng.electricalfire.ui.bean.ReadTimeItemData;
import com.sanleng.electricalfire.ui.bean.WaterSystem;

import java.util.HashMap;
import java.util.List;

public class MessageEvent {
    private int TAG;
    private String message;
    private List<ERealTimeDataBean> list;
    private List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_temperature;
    private List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_current;
    private List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_residualcurrent;
    private List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list_voltage;
    private List<WaterSystem.DataBean.ListBean> list_watersystem;
    private HashMap<String, Object> map;
    private List<HashMap<String, Object>> hashMap_list;
    private int position;
    private int size;
    private String electricalDetectorInfos;
    private String buildids;
    private String floorids;
    private int hyrant;
    private int eqt;
    private int water;
    private List<HistorRecordBean> lists;

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

    public HashMap<String, Object> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Object> map) {
        this.map = map;
    }

    public List<HashMap<String, Object>> getHashMap_list() {
        return hashMap_list;
    }

    public void setHashMap_list(List<HashMap<String, Object>> hashMap_list) {
        this.hashMap_list = hashMap_list;
    }

    public int getHyrant() {
        return hyrant;
    }

    public void setHyrant(int hyrant) {
        this.hyrant = hyrant;
    }

    public int getEqt() {
        return eqt;
    }

    public void setEqt(int eqt) {
        this.eqt = eqt;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public List<WaterSystem.DataBean.ListBean> getList_watersystem() {
        return list_watersystem;
    }

    public void setList_watersystem(List<WaterSystem.DataBean.ListBean> list_watersystem) {
        this.list_watersystem = list_watersystem;
    }

    public List<HistorRecordBean> getLists() {
        return lists;
    }

    public void setLists(List<HistorRecordBean> lists) {
        this.lists = lists;
    }
}
