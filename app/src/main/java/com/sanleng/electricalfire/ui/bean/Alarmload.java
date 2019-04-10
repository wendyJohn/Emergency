package com.sanleng.electricalfire.ui.bean;

public class Alarmload {


    /**
     * msg : 首页统计查询
     * truefire : 2
     * missfire : 0
     * code : 0
     * unhandlefire : 3
     * data : null
     * todayfire : 0
     * weekfire : 0
     * state : ok
     */

    private String msg;
    private int truefire;
    private int missfire;
    private String code;
    private int unhandlefire;
    private Object data;
    private int todayfire;
    private int weekfire;
    private String state;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTruefire() {
        return truefire;
    }

    public void setTruefire(int truefire) {
        this.truefire = truefire;
    }

    public int getMissfire() {
        return missfire;
    }

    public void setMissfire(int missfire) {
        this.missfire = missfire;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUnhandlefire() {
        return unhandlefire;
    }

    public void setUnhandlefire(int unhandlefire) {
        this.unhandlefire = unhandlefire;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTodayfire() {
        return todayfire;
    }

    public void setTodayfire(int todayfire) {
        this.todayfire = todayfire;
    }

    public int getWeekfire() {
        return weekfire;
    }

    public void setWeekfire(int weekfire) {
        this.weekfire = weekfire;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
