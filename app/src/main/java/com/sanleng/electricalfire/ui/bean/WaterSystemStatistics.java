package com.sanleng.electricalfire.ui.bean;

public class WaterSystemStatistics {

    /**
     * msg : 首页统计查询成功
     * hyrant : 0
     * code : 0
     * data : null
     * eqt : 0
     * state : ok
     * water : 0
     */

    private String msg;
    private int hyrant;
    private String code;
    private Object data;
    private int eqt;
    private String state;
    private int water;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getHyrant() {
        return hyrant;
    }

    public void setHyrant(int hyrant) {
        this.hyrant = hyrant;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getEqt() {
        return eqt;
    }

    public void setEqt(int eqt) {
        this.eqt = eqt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }
}
