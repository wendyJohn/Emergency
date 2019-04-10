package com.sanleng.electricalfire.ui.bean;

import java.util.List;

public class RealtimeDatas {

    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":8,"nextPage":2,"list":[{"floor_no":"abd374ce5b8f4ade9e00c331b08a7e6a","equipment_name":"电气火灾探测器","equipment_no":"","createtime":1554872145000,"type":"18","source_mac":"740037629552","build_name":"教学楼东楼","floor_name":"4层","unit_name":"南京工程学院","owner_building":"a0d5544de09e424b933d2c7915b60802","ids":"36ec3aaf04f941babfe9e3c44bd1350d","unit_code":"99950f1e2ebd466a8fed2e007cfd7c62","position":"东区四层配电箱","sector_no":"001","state":"1"}]}
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
         * total : 8
         * nextPage : 2
         * list : [{"floor_no":"abd374ce5b8f4ade9e00c331b08a7e6a","equipment_name":"电气火灾探测器","equipment_no":"","createtime":1554872145000,"type":"18","source_mac":"740037629552","build_name":"教学楼东楼","floor_name":"4层","unit_name":"南京工程学院","owner_building":"a0d5544de09e424b933d2c7915b60802","ids":"36ec3aaf04f941babfe9e3c44bd1350d","unit_code":"99950f1e2ebd466a8fed2e007cfd7c62","position":"东区四层配电箱","sector_no":"001","state":"1"}]
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
             * floor_no : abd374ce5b8f4ade9e00c331b08a7e6a
             * equipment_name : 电气火灾探测器
             * equipment_no :
             * createtime : 1554872145000
             * type : 18
             * source_mac : 740037629552
             * build_name : 教学楼东楼
             * floor_name : 4层
             * unit_name : 南京工程学院
             * owner_building : a0d5544de09e424b933d2c7915b60802
             * ids : 36ec3aaf04f941babfe9e3c44bd1350d
             * unit_code : 99950f1e2ebd466a8fed2e007cfd7c62
             * position : 东区四层配电箱
             * sector_no : 001
             * state : 1
             */

            private String floor_no;
            private String equipment_name;
            private String equipment_no;
            private long createtime;
            private String type;
            private String source_mac;
            private String build_name;
            private String floor_name;
            private String unit_name;
            private String owner_building;
            private String ids;
            private String unit_code;
            private String position;
            private String sector_no;
            private String state;

            public String getFloor_no() {
                return floor_no;
            }

            public void setFloor_no(String floor_no) {
                this.floor_no = floor_no;
            }

            public String getEquipment_name() {
                return equipment_name;
            }

            public void setEquipment_name(String equipment_name) {
                this.equipment_name = equipment_name;
            }

            public String getEquipment_no() {
                return equipment_no;
            }

            public void setEquipment_no(String equipment_no) {
                this.equipment_no = equipment_no;
            }

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getSource_mac() {
                return source_mac;
            }

            public void setSource_mac(String source_mac) {
                this.source_mac = source_mac;
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

            public String getUnit_name() {
                return unit_name;
            }

            public void setUnit_name(String unit_name) {
                this.unit_name = unit_name;
            }

            public String getOwner_building() {
                return owner_building;
            }

            public void setOwner_building(String owner_building) {
                this.owner_building = owner_building;
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

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getSector_no() {
                return sector_no;
            }

            public void setSector_no(String sector_no) {
                this.sector_no = sector_no;
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
