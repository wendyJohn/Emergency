package com.sanleng.electricalfire.ui.bean;

import java.util.List;

public class ArchitecturesBean {

    /**
     * msg : 获取成功
     * code : 0
     * data : [{"name":"逃生应急箱","model":"TSYJX","num":"1","location":"A","state":"0","lack":"0"},{"name":"AED自动体外除颤仪","model":"AEDZDTWCCY","num":"1","location":"A","state":"0","lack":"0"},{"name":"水带接头","model":"SDJT","num":"1","location":"D","state":"0","lack":"0"},{"name":"消防枪头","model":"XFQT","num":"1","location":"D","state":"0","lack":"0"},{"name":"手套","model":"ST","num":"2","location":"A","state":"0","lack":"0"},{"name":"消防头盔","model":"XFTK","num":"2","location":"A","state":"0","lack":"0"},{"name":"防护服","model":"FHF","num":"2","location":"A","state":"0","lack":"0"},{"name":"水带","model":"SD","num":"1","location":"D","state":"0","lack":"0"},{"name":"灭火器","model":"MHQ","num":"2","location":"D","state":"0","lack":"0"}]
     * state : ok
     */

    private String msg;
    private String code;
    private String state;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 逃生应急箱
         * model : TSYJX
         * num : 1
         * location : A
         * state : 0
         * lack : 0
         */

        private String name;
        private String model;
        private String num;
        private String location;
        private String state;
        private String lack;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getLack() {
            return lack;
        }

        public void setLack(String lack) {
            this.lack = lack;
        }
    }
}
