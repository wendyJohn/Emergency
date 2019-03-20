package com.sanleng.electricalfire.bean;

import java.util.List;

public class RealtimeData {
    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":8,"nextPage":-1,"list":[{"device_id":"f19f7b751d1711e849437398c29b0133","build_name":"教学楼东楼","floor_name":"1层","device_name":"东区一层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null},{"device_id":"f19f7b751d1711e849237858c29b0133","build_name":"教学楼东楼","floor_name":"3层","device_name":"东区三层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null},{"device_id":"f19f7b751d1711e849437858c29b0133","build_name":"教学楼东楼","floor_name":"2层","device_name":"东区二层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null},{"device_id":"f19f7b751d1711e849233058c29b0133","build_name":"教学楼东楼","floor_name":"4层","device_name":"东区四层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null},{"device_id":"f19f7b751d1711e89b437300c29b0133","build_name":"教学楼西楼","floor_name":"1层","device_name":"西区一层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null},{"device_id":"f19f7b751d1711e849437300c29b0133","build_name":"教学楼西楼","floor_name":"3层","device_name":"西区三层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null},{"device_id":"f19f7b751d1711e893437300c29b0133","build_name":"教学楼西楼","floor_name":"2层","device_name":"西区二层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null},{"device_id":"f19f7b751d1711e849867300c29b0133","build_name":"教学楼西楼","floor_name":"4层","device_name":"西区四层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null}]}
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
         * nextPage : -1
         * list : [{"device_id":"f19f7b751d1711e849437398c29b0133","build_name":"教学楼东楼","floor_name":"1层","device_name":"东区一层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null},{"device_id":"f19f7b751d1711e849237858c29b0133","build_name":"教学楼东楼","floor_name":"3层","device_name":"东区三层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null},{"device_id":"f19f7b751d1711e849437858c29b0133","build_name":"教学楼东楼","floor_name":"2层","device_name":"东区二层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null},{"device_id":"f19f7b751d1711e849233058c29b0133","build_name":"教学楼东楼","floor_name":"4层","device_name":"东区四层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null},{"device_id":"f19f7b751d1711e89b437300c29b0133","build_name":"教学楼西楼","floor_name":"1层","device_name":"西区一层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null},{"device_id":"f19f7b751d1711e849437300c29b0133","build_name":"教学楼西楼","floor_name":"3层","device_name":"西区三层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null},{"device_id":"f19f7b751d1711e893437300c29b0133","build_name":"教学楼西楼","floor_name":"2层","device_name":"西区二层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null},{"device_id":"f19f7b751d1711e849867300c29b0133","build_name":"教学楼西楼","floor_name":"4层","device_name":"西区四层配电箱","unit_name":"南京工程学院","state":"0","contact_name":"张三","contact_tel":"15961736537","lng":null,"lat":null}]
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
             * device_id : f19f7b751d1711e849437398c29b0133
             * build_name : 教学楼东楼
             * floor_name : 1层
             * device_name : 东区一层配电箱
             * unit_name : 南京工程学院
             * state : 0
             * contact_name : 张三
             * contact_tel : 15961736537
             * lng : null
             * lat : null
             */

            private String device_id;
            private String build_name;
            private String floor_name;
            private String device_name;
            private String unit_name;
            private String state;
            private String contact_name;
            private String contact_tel;
            private Object lng;
            private Object lat;

            public String getDevice_id() {
                return device_id;
            }

            public void setDevice_id(String device_id) {
                this.device_id = device_id;
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

            public String getUnit_name() {
                return unit_name;
            }

            public void setUnit_name(String unit_name) {
                this.unit_name = unit_name;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getContact_name() {
                return contact_name;
            }

            public void setContact_name(String contact_name) {
                this.contact_name = contact_name;
            }

            public String getContact_tel() {
                return contact_tel;
            }

            public void setContact_tel(String contact_tel) {
                this.contact_tel = contact_tel;
            }

            public Object getLng() {
                return lng;
            }

            public void setLng(Object lng) {
                this.lng = lng;
            }

            public Object getLat() {
                return lat;
            }

            public void setLat(Object lat) {
                this.lat = lat;
            }
        }
    }
}
