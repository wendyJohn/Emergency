package com.sanleng.electricalfire.ui.bean;

public class Material {


    /**
     * msg : 获取物资成功
     * code : 0
     * data : {"ids":"26faca9a1874488ab3d740faed1ce242","name":"自救呼吸器","number":"ZJHXQ000003","specification":"一个","model":"ZJHXQ","stationName":"u谷管委会","stationId":"220c920916c546e1b8d40161f71ab095","storageLocation":"C","effective":"2026-12-10","state":"emergencystation_in","createtime":"2019-02-20 14:45:24","unitCode":"2abff0176ed8410d8be83f86739cb0db"}
     * state : ok
     */

    private String msg;
    private String code;
    private DataBean data;
    private String state;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static class DataBean {
        /**
         * ids : 26faca9a1874488ab3d740faed1ce242
         * name : 自救呼吸器
         * number : ZJHXQ000003
         * specification : 一个
         * model : ZJHXQ
         * stationName : u谷管委会
         * stationId : 220c920916c546e1b8d40161f71ab095
         * storageLocation : C
         * effective : 2026-12-10
         * state : emergencystation_in
         * createtime : 2019-02-20 14:45:24
         * unitCode : 2abff0176ed8410d8be83f86739cb0db
         */

        private String ids;
        private String name;
        private String number;
        private String specification;
        private String model;
        private String stationName;
        private String stationId;
        private String storageLocation;
        private String effective;
        private String state;
        private String createtime;
        private String unitCode;

        public String getIds() {
            return ids;
        }

        public void setIds(String ids) {
            this.ids = ids;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
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

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public String getStationId() {
            return stationId;
        }

        public void setStationId(String stationId) {
            this.stationId = stationId;
        }

        public String getStorageLocation() {
            return storageLocation;
        }

        public void setStorageLocation(String storageLocation) {
            this.storageLocation = storageLocation;
        }

        public String getEffective() {
            return effective;
        }

        public void setEffective(String effective) {
            this.effective = effective;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUnitCode() {
            return unitCode;
        }

        public void setUnitCode(String unitCode) {
            this.unitCode = unitCode;
        }
    }
}
