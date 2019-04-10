package com.sanleng.electricalfire.ui.bean;

import java.util.List;

public class Article {
    /**
     * msg : 获取成功
     * code : 0
     * data : {"total":1,"nextPage":-1,"list":[{"live_start_time":null,"publish_type":"1","live_end_time":null,"subject_type":"灭火类","create_time":"2019-01-18 09:07:22","mobile_live":"","is_published":"0","record_url":"","super_admin":"管理员","name":"呼和浩特市支队","topic":"应急救援","ids":"02f44a139a634b409ce51b1fda601412","browse_times":1,"web_live":"","cover_img":"/RootFile/Platform/20190118/1547773642167.png"}]}
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
         * list : [{"live_start_time":null,"publish_type":"1","live_end_time":null,"subject_type":"灭火类","create_time":"2019-01-18 09:07:22","mobile_live":"","is_published":"0","record_url":"","super_admin":"管理员","name":"呼和浩特市支队","topic":"应急救援","ids":"02f44a139a634b409ce51b1fda601412","browse_times":1,"web_live":"","cover_img":"/RootFile/Platform/20190118/1547773642167.png"}]
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
             * live_start_time : null
             * publish_type : 1
             * live_end_time : null
             * subject_type : 灭火类
             * create_time : 2019-01-18 09:07:22
             * mobile_live :
             * is_published : 0
             * record_url :
             * super_admin : 管理员
             * name : 呼和浩特市支队
             * topic : 应急救援
             * ids : 02f44a139a634b409ce51b1fda601412
             * browse_times : 1
             * web_live :
             * cover_img : /RootFile/Platform/20190118/1547773642167.png
             */

            private Object live_start_time;
            private String publish_type;
            private Object live_end_time;
            private String subject_type;
            private String create_time;
            private String mobile_live;
            private String is_published;
            private String record_url;
            private String super_admin;
            private String name;
            private String topic;
            private String ids;
            private int browse_times;
            private String web_live;
            private String cover_img;

            public Object getLive_start_time() {
                return live_start_time;
            }

            public void setLive_start_time(Object live_start_time) {
                this.live_start_time = live_start_time;
            }

            public String getPublish_type() {
                return publish_type;
            }

            public void setPublish_type(String publish_type) {
                this.publish_type = publish_type;
            }

            public Object getLive_end_time() {
                return live_end_time;
            }

            public void setLive_end_time(Object live_end_time) {
                this.live_end_time = live_end_time;
            }

            public String getSubject_type() {
                return subject_type;
            }

            public void setSubject_type(String subject_type) {
                this.subject_type = subject_type;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getMobile_live() {
                return mobile_live;
            }

            public void setMobile_live(String mobile_live) {
                this.mobile_live = mobile_live;
            }

            public String getIs_published() {
                return is_published;
            }

            public void setIs_published(String is_published) {
                this.is_published = is_published;
            }

            public String getRecord_url() {
                return record_url;
            }

            public void setRecord_url(String record_url) {
                this.record_url = record_url;
            }

            public String getSuper_admin() {
                return super_admin;
            }

            public void setSuper_admin(String super_admin) {
                this.super_admin = super_admin;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTopic() {
                return topic;
            }

            public void setTopic(String topic) {
                this.topic = topic;
            }

            public String getIds() {
                return ids;
            }

            public void setIds(String ids) {
                this.ids = ids;
            }

            public int getBrowse_times() {
                return browse_times;
            }

            public void setBrowse_times(int browse_times) {
                this.browse_times = browse_times;
            }

            public String getWeb_live() {
                return web_live;
            }

            public void setWeb_live(String web_live) {
                this.web_live = web_live;
            }

            public String getCover_img() {
                return cover_img;
            }

            public void setCover_img(String cover_img) {
                this.cover_img = cover_img;
            }
        }
    }
}
