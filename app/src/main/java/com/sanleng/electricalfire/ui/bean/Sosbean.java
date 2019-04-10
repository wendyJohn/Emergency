package com.sanleng.electricalfire.ui.bean;

import java.util.List;

public class Sosbean {

    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":5,"nextPage":2,"list":[{"ids":"fa2fe2f8bf004e7b9302a8dae9bbd4da","name":"hhhq","phone":"11","unitCode":"22d1a7248ee049de823704c4eb5e981b","identitycrad":"111","photo":null,"address":"中国江苏省南京市江宁区秣周东路","type":"firecontrol","examineAuditor":"ea816cc5b6df42c0a15355914bd5a838","examineResult":"1","createtime":1553682942000,"lat":"31.87408","lng":"118.835831"}]}
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
         * list : [{"ids":"fa2fe2f8bf004e7b9302a8dae9bbd4da","name":"hhhq","phone":"11","unitCode":"22d1a7248ee049de823704c4eb5e981b","identitycrad":"111","photo":null,"address":"中国江苏省南京市江宁区秣周东路","type":"firecontrol","examineAuditor":"ea816cc5b6df42c0a15355914bd5a838","examineResult":"1","createtime":1553682942000,"lat":"31.87408","lng":"118.835831"}]
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
             * ids : fa2fe2f8bf004e7b9302a8dae9bbd4da
             * name : hhhq
             * phone : 11
             * unitCode : 22d1a7248ee049de823704c4eb5e981b
             * identitycrad : 111
             * photo : null
             * address : 中国江苏省南京市江宁区秣周东路
             * type : firecontrol
             * examineAuditor : ea816cc5b6df42c0a15355914bd5a838
             * examineResult : 1
             * createtime : 1553682942000
             * lat : 31.87408
             * lng : 118.835831
             */

            private String ids;
            private String name;
            private String phone;
            private String unitCode;
            private String identitycrad;
            private Object photo;
            private String address;
            private String type;
            private String examineAuditor;
            private String examineResult;
            private long createtime;
            private String lat;
            private String lng;

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

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getUnitCode() {
                return unitCode;
            }

            public void setUnitCode(String unitCode) {
                this.unitCode = unitCode;
            }

            public String getIdentitycrad() {
                return identitycrad;
            }

            public void setIdentitycrad(String identitycrad) {
                this.identitycrad = identitycrad;
            }

            public Object getPhoto() {
                return photo;
            }

            public void setPhoto(Object photo) {
                this.photo = photo;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getExamineAuditor() {
                return examineAuditor;
            }

            public void setExamineAuditor(String examineAuditor) {
                this.examineAuditor = examineAuditor;
            }

            public String getExamineResult() {
                return examineResult;
            }

            public void setExamineResult(String examineResult) {
                this.examineResult = examineResult;
            }

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }
        }
    }
}
