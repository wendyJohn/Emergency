package com.sanleng.electricalfire.ui.bean;

import java.util.List;

public class WaterSystem {

    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":1,"nextPage":-1,"list":[{"device_type_name":"水位","range_min":"0.0","current_state":"0.0","device_type":"watersystem_level","build_name":"英莱楼","floor_name":"30层","device_name":"末端试水","min_amt":null,"unit_code":"22d1a7248ee049de823704c4eb5e981b","max_amt":null,"ids":"2b27b4c393554590a67251c027588cc","device_address":"","range_max":"2.0","state":"异常","isnormal":"0"}]}
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
         * total : 1
         * nextPage : -1
         * list : [{"device_type_name":"水位","range_min":"0.0","current_state":"0.0","device_type":"watersystem_level","build_name":"英莱楼","floor_name":"30层","device_name":"末端试水","min_amt":null,"unit_code":"22d1a7248ee049de823704c4eb5e981b","max_amt":null,"ids":"2b27b4c393554590a67251c027588cc","device_address":"","range_max":"2.0","state":"异常","isnormal":"0"}]
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
             * device_type_name : 水位
             * range_min : 0.0
             * current_state : 0.0
             * device_type : watersystem_level
             * build_name : 英莱楼
             * floor_name : 30层
             * device_name : 末端试水
             * min_amt : null
             * unit_code : 22d1a7248ee049de823704c4eb5e981b
             * max_amt : null
             * ids : 2b27b4c393554590a67251c027588cc
             * device_address :
             * range_max : 2.0
             * state : 异常
             * isnormal : 0
             */

            private String device_type_name;
            private String range_min;
            private String current_state;
            private String device_type;
            private String build_name;
            private String floor_name;
            private String device_name;
            private Object min_amt;
            private String unit_code;
            private Object max_amt;
            private String ids;
            private String device_address;
            private String range_max;
            private String state;
            private String isnormal;

            public String getDevice_type_name() {
                return device_type_name;
            }

            public void setDevice_type_name(String device_type_name) {
                this.device_type_name = device_type_name;
            }

            public String getRange_min() {
                return range_min;
            }

            public void setRange_min(String range_min) {
                this.range_min = range_min;
            }

            public String getCurrent_state() {
                return current_state;
            }

            public void setCurrent_state(String current_state) {
                this.current_state = current_state;
            }

            public String getDevice_type() {
                return device_type;
            }

            public void setDevice_type(String device_type) {
                this.device_type = device_type;
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

            public Object getMin_amt() {
                return min_amt;
            }

            public void setMin_amt(Object min_amt) {
                this.min_amt = min_amt;
            }

            public String getUnit_code() {
                return unit_code;
            }

            public void setUnit_code(String unit_code) {
                this.unit_code = unit_code;
            }

            public Object getMax_amt() {
                return max_amt;
            }

            public void setMax_amt(Object max_amt) {
                this.max_amt = max_amt;
            }

            public String getIds() {
                return ids;
            }

            public void setIds(String ids) {
                this.ids = ids;
            }

            public String getDevice_address() {
                return device_address;
            }

            public void setDevice_address(String device_address) {
                this.device_address = device_address;
            }

            public String getRange_max() {
                return range_max;
            }

            public void setRange_max(String range_max) {
                this.range_max = range_max;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getIsnormal() {
                return isnormal;
            }

            public void setIsnormal(String isnormal) {
                this.isnormal = isnormal;
            }
        }
    }
}
