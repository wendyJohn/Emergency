package com.sanleng.electricalfire.ui.bean;

import java.util.List;

public class HistorRecordBean {
    private String company;
    private String sitename;
    private String materialname;
    private String storagelocation;
    private String materialstatus;
    private String operator;
    private String operationtime;
    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":1375,"nextPage":2,"list":[{"station_name":"三棱科技2","storage_location":"A","reason":null,"agent_id":null,"agent_name":"乔","agent_time":"2019-05-07 16:33:36","station_id":"9dc13fdf3bf6417d9a0b59c74c6fb798","name":"消防头盔","ids":"e69c62447a8e4de3b2065358e227938b","material_id":"211f7ed27ec64edca38200f5d2bd7a17","state":"emergencystation_out"}]}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    public String getStoragelocation() {
        return storagelocation;
    }

    public void setStoragelocation(String storagelocation) {
        this.storagelocation = storagelocation;
    }

    public String getMaterialstatus() {
        return materialstatus;
    }

    public void setMaterialstatus(String materialstatus) {
        this.materialstatus = materialstatus;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(String operationtime) {
        this.operationtime = operationtime;
    }

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
         * total : 1375
         * nextPage : 2
         * list : [{"station_name":"三棱科技2","storage_location":"A","reason":null,"agent_id":null,"agent_name":"乔","agent_time":"2019-05-07 16:33:36","station_id":"9dc13fdf3bf6417d9a0b59c74c6fb798","name":"消防头盔","ids":"e69c62447a8e4de3b2065358e227938b","material_id":"211f7ed27ec64edca38200f5d2bd7a17","state":"emergencystation_out"}]
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
             * station_name : 三棱科技2
             * storage_location : A
             * reason : null
             * agent_id : null
             * agent_name : 乔
             * agent_time : 2019-05-07 16:33:36
             * station_id : 9dc13fdf3bf6417d9a0b59c74c6fb798
             * name : 消防头盔
             * ids : e69c62447a8e4de3b2065358e227938b
             * material_id : 211f7ed27ec64edca38200f5d2bd7a17
             * state : emergencystation_out
             */

            private String station_name;
            private String storage_location;
            private Object reason;
            private Object agent_id;
            private String agent_name;
            private String agent_time;
            private String station_id;
            private String name;
            private String ids;
            private String material_id;
            private String state;

            public String getStation_name() {
                return station_name;
            }

            public void setStation_name(String station_name) {
                this.station_name = station_name;
            }

            public String getStorage_location() {
                return storage_location;
            }

            public void setStorage_location(String storage_location) {
                this.storage_location = storage_location;
            }

            public Object getReason() {
                return reason;
            }

            public void setReason(Object reason) {
                this.reason = reason;
            }

            public Object getAgent_id() {
                return agent_id;
            }

            public void setAgent_id(Object agent_id) {
                this.agent_id = agent_id;
            }

            public String getAgent_name() {
                return agent_name;
            }

            public void setAgent_name(String agent_name) {
                this.agent_name = agent_name;
            }

            public String getAgent_time() {
                return agent_time;
            }

            public void setAgent_time(String agent_time) {
                this.agent_time = agent_time;
            }

            public String getStation_id() {
                return station_id;
            }

            public void setStation_id(String station_id) {
                this.station_id = station_id;
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

            public String getMaterial_id() {
                return material_id;
            }

            public void setMaterial_id(String material_id) {
                this.material_id = material_id;
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
