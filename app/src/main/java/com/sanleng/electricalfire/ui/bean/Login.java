package com.sanleng.electricalfire.ui.bean;

public class Login {

    /**
     * msg : 登录成功
     * code : 0
     * data : {"img":"","accesstype":"owner","unitcode":"99950f1e2ebd466a8fed2e007cfd7c62","lng":null,"phone":"13532145632","name":"周俐星","usertype":"unit_type_admin_owner","ids":"e98fecfd241f4b019aded234efe2ac58","departmentids":null,"account":"school","lat":null,"token":""}
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
         * img :
         * accesstype : owner
         * unitcode : 99950f1e2ebd466a8fed2e007cfd7c62
         * lng : null
         * phone : 13532145632
         * name : 周俐星
         * usertype : unit_type_admin_owner
         * ids : e98fecfd241f4b019aded234efe2ac58
         * departmentids : null
         * account : school
         * lat : null
         * token :
         */

        private String img;
        private String accesstype;
        private String unitcode;
        private Object lng;
        private String phone;
        private String name;
        private String usertype;
        private String ids;
        private Object departmentids;
        private String account;
        private Object lat;
        private String token;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getAccesstype() {
            return accesstype;
        }

        public void setAccesstype(String accesstype) {
            this.accesstype = accesstype;
        }

        public String getUnitcode() {
            return unitcode;
        }

        public void setUnitcode(String unitcode) {
            this.unitcode = unitcode;
        }

        public Object getLng() {
            return lng;
        }

        public void setLng(Object lng) {
            this.lng = lng;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }

        public String getIds() {
            return ids;
        }

        public void setIds(String ids) {
            this.ids = ids;
        }

        public Object getDepartmentids() {
            return departmentids;
        }

        public void setDepartmentids(Object departmentids) {
            this.departmentids = departmentids;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
