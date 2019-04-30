package com.sanleng.electricalfire.ui.bean;

import java.util.List;

public class ArchitecturesBean {
    /**
     * msg : 获取成功
     * code : 0
     * data : [{"storage_location":"D","total":1,"name":"水带接头","specification":"对","model":"SDJT"}]
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
         * storage_location : D
         * total : 1
         * name : 水带接头
         * specification : 对
         * model : SDJT
         */

        private String storage_location;
        private String total;
        private String name;
        private String specification;
        private String model;

        public String getStorage_location() {
            return storage_location;
        }

        public void setStorage_location(String storage_location) {
            this.storage_location = storage_location;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }
    }
}
