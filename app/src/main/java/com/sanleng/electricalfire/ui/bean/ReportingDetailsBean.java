package com.sanleng.electricalfire.ui.bean;

import java.util.List;

public class ReportingDetailsBean {

    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":5,"nextPage":2,"list":[{"point_id":"ea92a1561e2242b98b3d60535ace091b","finish_describe":null,"patrol_describe":null,"finish_url":null,"receive_time":"2019-04-30 11:30:01","type":"fire_alarm","detector_name":null,"finish_time":null,"build_name":null,"floor_name":null,"device_name":null,"build_id":null,"ids":"93c9544900e241aa87d3ff4e8d79b7b0","unit_code":"22d1a7248ee049de823704c4eb5e981b","floor_id":null,"patrol_url":"/RootFile/FirePatrol/20190430/1556595001085.JPEG,","state":"1"}]}
     */

    private String msg;
    private String code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * total : 5
         * nextPage : 2
         * list : [{"point_id":"ea92a1561e2242b98b3d60535ace091b","finish_describe":null,"patrol_describe":null,"finish_url":null,"receive_time":"2019-04-30 11:30:01","type":"fire_alarm","detector_name":null,"finish_time":null,"build_name":null,"floor_name":null,"device_name":null,"build_id":null,"ids":"93c9544900e241aa87d3ff4e8d79b7b0","unit_code":"22d1a7248ee049de823704c4eb5e981b","floor_id":null,"patrol_url":"/RootFile/FirePatrol/20190430/1556595001085.JPEG,","state":"1"}]
         */

        private int total;
        private int nextPage;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * point_id : ea92a1561e2242b98b3d60535ace091b
             * finish_describe : null
             * patrol_describe : null
             * finish_url : null
             * receive_time : 2019-04-30 11:30:01
             * type : fire_alarm
             * detector_name : null
             * finish_time : null
             * build_name : null
             * floor_name : null
             * device_name : null
             * build_id : null
             * ids : 93c9544900e241aa87d3ff4e8d79b7b0
             * unit_code : 22d1a7248ee049de823704c4eb5e981b
             * floor_id : null
             * patrol_url : /RootFile/FirePatrol/20190430/1556595001085.JPEG,
             * state : 1
             */

            private String point_id;
            private Object finish_describe;
            private Object patrol_describe;
            private Object finish_url;
            private String receive_time;
            private String type;
            private Object detector_name;
            private Object finish_time;
            private Object build_name;
            private Object floor_name;
            private Object device_name;
            private Object build_id;
            private String ids;
            private String unit_code;
            private Object floor_id;
            private String patrol_url;
            private String state;

            public String getPoint_id() {
                return point_id;
            }

            public void setPoint_id(String point_id) {
                this.point_id = point_id;
            }

            public Object getFinish_describe() {
                return finish_describe;
            }

            public void setFinish_describe(Object finish_describe) {
                this.finish_describe = finish_describe;
            }

            public Object getPatrol_describe() {
                return patrol_describe;
            }

            public void setPatrol_describe(Object patrol_describe) {
                this.patrol_describe = patrol_describe;
            }

            public Object getFinish_url() {
                return finish_url;
            }

            public void setFinish_url(Object finish_url) {
                this.finish_url = finish_url;
            }

            public String getReceive_time() {
                return receive_time;
            }

            public void setReceive_time(String receive_time) {
                this.receive_time = receive_time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getDetector_name() {
                return detector_name;
            }

            public void setDetector_name(Object detector_name) {
                this.detector_name = detector_name;
            }

            public Object getFinish_time() {
                return finish_time;
            }

            public void setFinish_time(Object finish_time) {
                this.finish_time = finish_time;
            }

            public Object getBuild_name() {
                return build_name;
            }

            public void setBuild_name(Object build_name) {
                this.build_name = build_name;
            }

            public Object getFloor_name() {
                return floor_name;
            }

            public void setFloor_name(Object floor_name) {
                this.floor_name = floor_name;
            }

            public Object getDevice_name() {
                return device_name;
            }

            public void setDevice_name(Object device_name) {
                this.device_name = device_name;
            }

            public Object getBuild_id() {
                return build_id;
            }

            public void setBuild_id(Object build_id) {
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

            public Object getFloor_id() {
                return floor_id;
            }

            public void setFloor_id(Object floor_id) {
                this.floor_id = floor_id;
            }

            public String getPatrol_url() {
                return patrol_url;
            }

            public void setPatrol_url(String patrol_url) {
                this.patrol_url = patrol_url;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }
        }
    }
}
