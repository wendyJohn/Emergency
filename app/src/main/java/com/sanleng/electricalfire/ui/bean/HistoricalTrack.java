package com.sanleng.electricalfire.ui.bean;

import java.util.List;

public class HistoricalTrack {

    /**
     * msg : 获取成功！
     * code : 0
     * data : [{"point_id":"9b72ad2913d911e98fa300163e047d1c","finish_describe":"计划瑞士刚刚好是帝国骄","finish_url":"sferwgergerge","receive_time":1547122662000,"detector_name":"electricity_detector","finish_time":1547122725000,"build_name":"教学楼西楼","floor_name":"1层","device_name":"西区一层配电箱","build_id":"9ec1d3021f854ced83f8c4dbb30e0a7e","ids":"1","unit_code":"99950f1e2ebd466a8fed2e007cfd7c62","floor_id":"05954fd290c649b8a7b5c5017fc59c8d","state":"0"},{"point_id":"c08127f513d911e98fa300163e047d1c","finish_describe":null,"finish_url":null,"receive_time":1547119650000,"detector_name":"residualcurrent_detector","finish_time":1547119650000,"build_name":"教学楼西楼","floor_name":"1层","device_name":"西区一层配电箱","build_id":"9ec1d3021f854ced83f8c4dbb30e0a7e","ids":"2","unit_code":"99950f1e2ebd466a8fed2e007cfd7c62","floor_id":"05954fd290c649b8a7b5c5017fc59c8d","state":"1"}]
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
         * point_id : 9b72ad2913d911e98fa300163e047d1c
         * finish_describe : 计划瑞士刚刚好是帝国骄
         * finish_url : sferwgergerge
         * receive_time : 1547122662000
         * detector_name : electricity_detector
         * finish_time : 1547122725000
         * build_name : 教学楼西楼
         * floor_name : 1层
         * device_name : 西区一层配电箱
         * build_id : 9ec1d3021f854ced83f8c4dbb30e0a7e
         * ids : 1
         * unit_code : 99950f1e2ebd466a8fed2e007cfd7c62
         * floor_id : 05954fd290c649b8a7b5c5017fc59c8d
         * state : 0
         */

        private String point_id;
        private String finish_describe;
        private String finish_url;
        private String receive_time;
        private String detector_name;
        private long finish_time;
        private String build_name;
        private String floor_name;
        private String device_name;
        private String build_id;
        private String ids;
        private String unit_code;
        private String floor_id;
        private String state;

        public String getPoint_id() {
            return point_id;
        }

        public void setPoint_id(String point_id) {
            this.point_id = point_id;
        }

        public String getFinish_describe() {
            return finish_describe;
        }

        public void setFinish_describe(String finish_describe) {
            this.finish_describe = finish_describe;
        }

        public String getFinish_url() {
            return finish_url;
        }

        public void setFinish_url(String finish_url) {
            this.finish_url = finish_url;
        }

        public String getReceive_time() {
            return receive_time;
        }

        public void setReceive_time(String receive_time) {
            this.receive_time = receive_time;
        }

        public String getDetector_name() {
            return detector_name;
        }

        public void setDetector_name(String detector_name) {
            this.detector_name = detector_name;
        }

        public long getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(long finish_time) {
            this.finish_time = finish_time;
        }

        public String getBuild_name() {
            return build_name;
        }

        public void setBuild_name(String build_name) {
            this.build_name = build_name;
        }

        public String getFloor_name() {
            return floor_name;
        }

        public void setFloor_name(String floor_name) {
            this.floor_name = floor_name;
        }

        public String getDevice_name() {
            return device_name;
        }

        public void setDevice_name(String device_name) {
            this.device_name = device_name;
        }

        public String getBuild_id() {
            return build_id;
        }

        public void setBuild_id(String build_id) {
            this.build_id = build_id;
        }

        public String getIds() {
            return ids;
        }

        public void setIds(String ids) {
            this.ids = ids;
        }

        public String getUnit_code() {
            return unit_code;
        }

        public void setUnit_code(String unit_code) {
            this.unit_code = unit_code;
        }

        public String getFloor_id() {
            return floor_id;
        }

        public void setFloor_id(String floor_id) {
            this.floor_id = floor_id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
