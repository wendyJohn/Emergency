package com.sanleng.electricalfire.ui.bean;

import java.util.List;

public class Station {
    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":1,"nextPage":-1,"list":[{"createtime":1552449365000,"address":"秣周东路地铁站","unitcode":"80799f58a3db4ed8835a83b278b5d394","lng":"118.837243","charge_person":"71e69854fe0947bd9a5ca1f1050f5e39","volunteername":"秣周东路地铁站","telephone":"13812341232","channel_two":"","mac":"B0F1EC8C9C05","unit_name":"秣周东路地铁站","volunteer":"ea816cc5b6df42c0a15355914bd5a838","channel_one":"","name":"秣周东路地铁运营中心","ids":"8e81b5148d7941acb6f49c2ecf10cc95","online":1552449364000,"lat":"31.874349","username":"周俐星2"}]}
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
         * list : [{"createtime":1552449365000,"address":"秣周东路地铁站","unitcode":"80799f58a3db4ed8835a83b278b5d394","lng":"118.837243","charge_person":"71e69854fe0947bd9a5ca1f1050f5e39","volunteername":"秣周东路地铁站","telephone":"13812341232","channel_two":"","mac":"B0F1EC8C9C05","unit_name":"秣周东路地铁站","volunteer":"ea816cc5b6df42c0a15355914bd5a838","channel_one":"","name":"秣周东路地铁运营中心","ids":"8e81b5148d7941acb6f49c2ecf10cc95","online":1552449364000,"lat":"31.874349","username":"周俐星2"}]
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
             * createtime : 1552449365000
             * address : 秣周东路地铁站
             * unitcode : 80799f58a3db4ed8835a83b278b5d394
             * lng : 118.837243
             * charge_person : 71e69854fe0947bd9a5ca1f1050f5e39
             * volunteername : 秣周东路地铁站
             * telephone : 13812341232
             * channel_two :
             * mac : B0F1EC8C9C05
             * unit_name : 秣周东路地铁站
             * volunteer : ea816cc5b6df42c0a15355914bd5a838
             * channel_one :
             * name : 秣周东路地铁运营中心
             * ids : 8e81b5148d7941acb6f49c2ecf10cc95
             * online : 1552449364000
             * lat : 31.874349
             * username : 周俐星2
             */

            private long createtime;
            private String address;
            private String unitcode;
            private String lng;
            private String charge_person;
            private String volunteername;
            private String telephone;
            private String channel_two;
            private String mac;
            private String unit_name;
            private String volunteer;
            private String channel_one;
            private String name;
            private String ids;
            private long online;
            private String lat;
            private String username;

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

            public String getVolunteername() {
                return volunteername;
            }

            public void setVolunteername(String volunteername) {
                this.volunteername = volunteername;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
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

            public String getUnit_name() {
                return unit_name;
            }

            public void setUnit_name(String unit_name) {
                this.unit_name = unit_name;
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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
