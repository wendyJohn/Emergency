package com.sanleng.electricalfire.ui.bean;

import java.util.List;

public class NearbyStation {

    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":3,"nextPage":-1,"list":[{"createtime":1546066958000,"address":"江宁","distance":60,"unitcode":"99950f1e2ebd466a8fed2e007cfd7c62","lng":"118.835191","charge_person":"5bb31f6772dc40a6b47453d62fa06f19","channel_two":null,"mac":"454657","channel_one":"1","name":"三棱","ids":"83ef7302eeda44bf816107dd46a52599","online":1547533244000,"lat":"31.874019"},{"createtime":1547522315000,"address":"三棱办公厅","distance":165,"unitcode":"00e31db02e024a05bfe8f91bf79d2be7","lng":"118.834083","charge_person":"23c839083c6b449ca88fe9b3d200fb99","channel_two":"8","mac":"98745632","channel_one":"9","name":"三棱应急站1","ids":"b1ad155d0078468aa7cc7d11ab123b86","online":1547522320000,"lat":"31.874155"},{"createtime":1547523384000,"address":"三棱科技1号室","distance":191,"unitcode":"90c3f0afb55a41d99c3acc4061d4d8cb","lng":"118.833809","charge_person":"fdd54fb40112477e85ba8fe8113c2df2","channel_two":"7","mac":"3698745","channel_one":"6","name":"应急站1号","ids":"428f19ad74a9412bb4658047fa211136","online":1547523389000,"lat":"31.873886"}]}
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
         * total : 3
         * nextPage : -1
         * list : [{"createtime":1546066958000,"address":"江宁","distance":60,"unitcode":"99950f1e2ebd466a8fed2e007cfd7c62","lng":"118.835191","charge_person":"5bb31f6772dc40a6b47453d62fa06f19","channel_two":null,"mac":"454657","channel_one":"1","name":"三棱","ids":"83ef7302eeda44bf816107dd46a52599","online":1547533244000,"lat":"31.874019"},{"createtime":1547522315000,"address":"三棱办公厅","distance":165,"unitcode":"00e31db02e024a05bfe8f91bf79d2be7","lng":"118.834083","charge_person":"23c839083c6b449ca88fe9b3d200fb99","channel_two":"8","mac":"98745632","channel_one":"9","name":"三棱应急站1","ids":"b1ad155d0078468aa7cc7d11ab123b86","online":1547522320000,"lat":"31.874155"},{"createtime":1547523384000,"address":"三棱科技1号室","distance":191,"unitcode":"90c3f0afb55a41d99c3acc4061d4d8cb","lng":"118.833809","charge_person":"fdd54fb40112477e85ba8fe8113c2df2","channel_two":"7","mac":"3698745","channel_one":"6","name":"应急站1号","ids":"428f19ad74a9412bb4658047fa211136","online":1547523389000,"lat":"31.873886"}]
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
             * createtime : 1546066958000
             * address : 江宁
             * distance : 60
             * unitcode : 99950f1e2ebd466a8fed2e007cfd7c62
             * lng : 118.835191
             * charge_person : 5bb31f6772dc40a6b47453d62fa06f19
             * channel_two : null
             * mac : 454657
             * channel_one : 1
             * name : 三棱
             * ids : 83ef7302eeda44bf816107dd46a52599
             * online : 1547533244000
             * lat : 31.874019
             */

            private long createtime;
            private String address;
            private int distance;
            private String unitcode;
            private String lng;
            private String charge_person;
            private String channel_two;
            private String mac;
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
