package com.sanleng.electricalfire.ui.bean;

import java.util.List;

public class AlarmRecord {

    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":8,"nextPage":2,"list":[{"floor_name":"4层","unit_name":"南京工程学院","equipment_name":"电气火灾探测器","receive_time":"2019-04-10 12:55:45","position":"东区四层配电箱","build_name":"教学楼东楼"}]}
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
         * list : [{"floor_name":"4层","unit_name":"南京工程学院","equipment_name":"电气火灾探测器","receive_time":"2019-04-10 12:55:45","position":"东区四层配电箱","build_name":"教学楼东楼"}]
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
             * floor_name : 4层
             * unit_name : 南京工程学院
             * equipment_name : 电气火灾探测器
             * receive_time : 2019-04-10 12:55:45
             * position : 东区四层配电箱
             * build_name : 教学楼东楼
             */

            private String floor_name;
            private String unit_name;
            private String equipment_name;
            private String receive_time;
            private String position;
            private String build_name;

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

            public String getEquipment_name() {
                return equipment_name;
            }

            public void setEquipment_name(String equipment_name) {
                this.equipment_name = equipment_name;
            }

            public String getReceive_time() {
                return receive_time;
            }

            public void setReceive_time(String receive_time) {
                this.receive_time = receive_time;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getBuild_name() {
                return build_name;
            }

            public void setBuild_name(String build_name) {
                this.build_name = build_name;
            }
        }
    }
}
