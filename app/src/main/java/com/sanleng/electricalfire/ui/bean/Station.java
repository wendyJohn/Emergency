package com.sanleng.electricalfire.ui.bean;

import java.util.List;

public class Station {

    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":5,"nextPage":2,"list":[{"createtime":1557365107000,"address":"河津经济开发区管委会","unitcode":"22d1a7248ee049de823704c4eb5e981b","lng":"118.834105","charge_person":"0358eda87d8a4f51aec3623fb05d44f1","volunteername":"三棱研发中心","format":"enterprise","telephone":"13812341231","channel_two":"http://hls.open.ys7.com/openlive/8057c092897e4b269c4c56624f2bae86.m3u8","mac":"10D07AC38A31","unit_name":"三棱科技","volunteer":"41bfde8e383745a0bcebfa758b75db89","channel_one":"http://hls01open.ys7.com/openlive/f51656f1f4d2431ea96c6cfeabccc629.m3u8","name":"河津应急站","ids":"9dc13fdf3bf6417d9a0b59c74c6fb798","online":1557365107000,"lat":"31.874817","username":"周俐星1"}]}
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
         * list : [{"createtime":1557365107000,"address":"河津经济开发区管委会","unitcode":"22d1a7248ee049de823704c4eb5e981b","lng":"118.834105","charge_person":"0358eda87d8a4f51aec3623fb05d44f1","volunteername":"三棱研发中心","format":"enterprise","telephone":"13812341231","channel_two":"http://hls.open.ys7.com/openlive/8057c092897e4b269c4c56624f2bae86.m3u8","mac":"10D07AC38A31","unit_name":"三棱科技","volunteer":"41bfde8e383745a0bcebfa758b75db89","channel_one":"http://hls01open.ys7.com/openlive/f51656f1f4d2431ea96c6cfeabccc629.m3u8","name":"河津应急站","ids":"9dc13fdf3bf6417d9a0b59c74c6fb798","online":1557365107000,"lat":"31.874817","username":"周俐星1"}]
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
             * createtime : 1557365107000
             * address : 河津经济开发区管委会
             * unitcode : 22d1a7248ee049de823704c4eb5e981b
             * lng : 118.834105
             * charge_person : 0358eda87d8a4f51aec3623fb05d44f1
             * volunteername : 三棱研发中心
             * format : enterprise
             * telephone : 13812341231
             * channel_two : http://hls.open.ys7.com/openlive/8057c092897e4b269c4c56624f2bae86.m3u8
             * mac : 10D07AC38A31
             * unit_name : 三棱科技
             * volunteer : 41bfde8e383745a0bcebfa758b75db89
             * channel_one : http://hls01open.ys7.com/openlive/f51656f1f4d2431ea96c6cfeabccc629.m3u8
             * name : 河津应急站
             * ids : 9dc13fdf3bf6417d9a0b59c74c6fb798
             * online : 1557365107000
             * lat : 31.874817
             * username : 周俐星1
             */

            private long createtime;
            private String address;
            private String unitcode;
            private String lng;
            private String charge_person;
            private String volunteername;
            private String format;
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

            public String getFormat() {
                return format;
            }

            public void setFormat(String format) {
                this.format = format;
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
