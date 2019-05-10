package com.sanleng.electricalfire.ui.bean;

import java.util.List;

public class NearbyStation {
    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":1,"nextPage":-1,"list":[{"createtime":1551938453000,"address":"江苏省南京市江宁区","distance":35,"unitcode":"fa4dbf53997343c8b5ebd2cd47651735","lng":"118.835712","charge_person":"ce9f417b817e4a7e9f1047a2349381f4","format":null,"channel_two":"","mac":"561456465","volunteer":"3bde9957548f4bb6b29c4d305c2d4c1d","channel_one":"","name":"三棱科技站点B","ids":"e7a1cca4f7ea4123bedebc4360a0de50","online":1551938452000,"lat":"31.874322"}]}
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
         * list : [{"createtime":1551938453000,"address":"江苏省南京市江宁区","distance":35,"unitcode":"fa4dbf53997343c8b5ebd2cd47651735","lng":"118.835712","charge_person":"ce9f417b817e4a7e9f1047a2349381f4","format":null,"channel_two":"","mac":"561456465","volunteer":"3bde9957548f4bb6b29c4d305c2d4c1d","channel_one":"","name":"三棱科技站点B","ids":"e7a1cca4f7ea4123bedebc4360a0de50","online":1551938452000,"lat":"31.874322"}]
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
             * createtime : 1551938453000
             * address : 江苏省南京市江宁区
             * distance : 35
             * unitcode : fa4dbf53997343c8b5ebd2cd47651735
             * lng : 118.835712
             * charge_person : ce9f417b817e4a7e9f1047a2349381f4
             * format : null
             * channel_two :
             * mac : 561456465
             * volunteer : 3bde9957548f4bb6b29c4d305c2d4c1d
             * channel_one :
             * name : 三棱科技站点B
             * ids : e7a1cca4f7ea4123bedebc4360a0de50
             * online : 1551938452000
             * lat : 31.874322
             */

            private long createtime;
            private String address;
            private int distance;
            private String unitcode;
            private String lng;
            private String charge_person;
            private String format;
            private String channel_two;
            private String mac;
            private String volunteer;
            private String channel_one;
            private String name;
            private String ids;
            private long online;
            private String lat;

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public String getUnitcode() {
                return unitcode;
            }

            public void setUnitcode(String unitcode) {
                this.unitcode = unitcode;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getCharge_person() {
                return charge_person;
            }

            public void setCharge_person(String charge_person) {
                this.charge_person = charge_person;
            }

            public String getFormat() {
                return format;
            }

            public void setFormat(String format) {
                this.format = format;
            }

            public String getChannel_two() {
                return channel_two;
            }

            public void setChannel_two(String channel_two) {
                this.channel_two = channel_two;
            }

            public String getMac() {
                return mac;
            }

            public void setMac(String mac) {
                this.mac = mac;
            }

            public String getVolunteer() {
                return volunteer;
            }

            public void setVolunteer(String volunteer) {
                this.volunteer = volunteer;
            }

            public String getChannel_one() {
                return channel_one;
            }

            public void setChannel_one(String channel_one) {
                this.channel_one = channel_one;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIds() {
                return ids;
            }

            public void setIds(String ids) {
                this.ids = ids;
            }

            public long getOnline() {
                return online;
            }

            public void setOnline(long online) {
                this.online = online;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }
        }
    }
}
