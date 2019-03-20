package com.sanleng.electricalfire.bean;

import java.util.List;

public class ReadTimeItemData {

    /**
     * msg : 获取数据成功
     * code : 0
     * data : {"device_id":"f19f7b751d1711e89b437300c29b0133","build_name":"教学楼西楼","buildids":"9ec1d3021f854ced83f8c4dbb30e0a7e","floorids":"05954fd290c649b8a7b5c5017fc59c8d","floor_name":"1层","device_name":"西区一层配电箱","device_state":null,"lng":null,"lat":null,"electricalDetectorInfos":[{"detector_id":"866a82d413d911e98fa300163e047d1c","detector_name":"electricity_detector","detector_nameVal":"电流","detector_model":"","mac":"2806053637","detector_address_number":"2","detector_port":"akwd","detector_portVal":"A口","upper_limit":"0","lower_limit":"7","measurement_unit":"A","realtime_data":null,"state":"1","current_value":"0.0","lng":null,"lat":null},{"detector_id":"9526724f13d911e98fa300163e047d1c","detector_name":"electricity_detector","detector_nameVal":"电流","detector_model":"","mac":"2806053637","detector_address_number":"2","detector_port":"bkwd","detector_portVal":"B口","upper_limit":"","lower_limit":"","measurement_unit":"A","realtime_data":null,"state":"1","current_value":"0.0","lng":null,"lat":null},{"detector_id":"9b72ad2913d911e98fa300163e047d1c","detector_name":"electricity_detector","detector_nameVal":"电流","detector_model":"","mac":"2806053637","detector_address_number":"2","detector_port":"ckwd","detector_portVal":"C口","upper_limit":"","lower_limit":"","measurement_unit":"A","realtime_data":null,"state":"0","current_value":"3.725","lng":null,"lat":null},{"detector_id":"a8c55c8413d811e98fa300163e047d1c","detector_name":"temperature_detector","detector_nameVal":"温度","detector_model":"","mac":"2806053637","detector_address_number":"","detector_port":"akwd","detector_portVal":"A口","upper_limit":"","lower_limit":"","measurement_unit":"℃","realtime_data":null,"state":"0","current_value":"15.1","lng":null,"lat":null},{"detector_id":"c08127f513d911e98fa300163e047d1c","detector_name":"residualcurrent_detector","detector_nameVal":"剩余电流","detector_model":"","mac":"2806053637","detector_address_number":"3","detector_port":"sydl","detector_portVal":"剩余电流口","upper_limit":"","lower_limit":"","measurement_unit":"A","realtime_data":null,"state":"0","current_value":"0.518","lng":null,"lat":null},{"detector_id":"c7afe89013d811e98fa300163e047d1c","detector_name":"temperature_detector","detector_nameVal":"温度","detector_model":"","mac":"2806053637","detector_address_number":"","detector_port":"bkwd","detector_portVal":"B口","upper_limit":"","lower_limit":"","measurement_unit":"℃","realtime_data":null,"state":"1","current_value":"16.0","lng":null,"lat":null},{"detector_id":"d5cbe74213d811e98fa300163e047d1c","detector_name":"temperature_detector","detector_nameVal":"温度","detector_model":"","mac":"2806053637","detector_address_number":"","detector_port":"ckwd","detector_portVal":"C口","upper_limit":"","lower_limit":"","measurement_unit":"℃","realtime_data":null,"state":"0","current_value":"15.1","lng":null,"lat":null},{"detector_id":"e3b957b613d811e98fa300163e047d1c","detector_name":"temperature_detector","detector_nameVal":"温度","detector_model":"","mac":"2806053637","detector_address_number":"","detector_port":"dkwd","detector_portVal":"D口","upper_limit":"","lower_limit":"","measurement_unit":"℃","realtime_data":null,"state":"0","current_value":"15.7","lng":null,"lat":null}]}
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
         * device_id : f19f7b751d1711e89b437300c29b0133
         * build_name : 教学楼西楼
         * buildids : 9ec1d3021f854ced83f8c4dbb30e0a7e
         * floorids : 05954fd290c649b8a7b5c5017fc59c8d
         * floor_name : 1层
         * device_name : 西区一层配电箱
         * device_state : null
         * lng : null
         * lat : null
         * electricalDetectorInfos : [{"detector_id":"866a82d413d911e98fa300163e047d1c","detector_name":"electricity_detector","detector_nameVal":"电流","detector_model":"","mac":"2806053637","detector_address_number":"2","detector_port":"akwd","detector_portVal":"A口","upper_limit":"0","lower_limit":"7","measurement_unit":"A","realtime_data":null,"state":"1","current_value":"0.0","lng":null,"lat":null},{"detector_id":"9526724f13d911e98fa300163e047d1c","detector_name":"electricity_detector","detector_nameVal":"电流","detector_model":"","mac":"2806053637","detector_address_number":"2","detector_port":"bkwd","detector_portVal":"B口","upper_limit":"","lower_limit":"","measurement_unit":"A","realtime_data":null,"state":"1","current_value":"0.0","lng":null,"lat":null},{"detector_id":"9b72ad2913d911e98fa300163e047d1c","detector_name":"electricity_detector","detector_nameVal":"电流","detector_model":"","mac":"2806053637","detector_address_number":"2","detector_port":"ckwd","detector_portVal":"C口","upper_limit":"","lower_limit":"","measurement_unit":"A","realtime_data":null,"state":"0","current_value":"3.725","lng":null,"lat":null},{"detector_id":"a8c55c8413d811e98fa300163e047d1c","detector_name":"temperature_detector","detector_nameVal":"温度","detector_model":"","mac":"2806053637","detector_address_number":"","detector_port":"akwd","detector_portVal":"A口","upper_limit":"","lower_limit":"","measurement_unit":"℃","realtime_data":null,"state":"0","current_value":"15.1","lng":null,"lat":null},{"detector_id":"c08127f513d911e98fa300163e047d1c","detector_name":"residualcurrent_detector","detector_nameVal":"剩余电流","detector_model":"","mac":"2806053637","detector_address_number":"3","detector_port":"sydl","detector_portVal":"剩余电流口","upper_limit":"","lower_limit":"","measurement_unit":"A","realtime_data":null,"state":"0","current_value":"0.518","lng":null,"lat":null},{"detector_id":"c7afe89013d811e98fa300163e047d1c","detector_name":"temperature_detector","detector_nameVal":"温度","detector_model":"","mac":"2806053637","detector_address_number":"","detector_port":"bkwd","detector_portVal":"B口","upper_limit":"","lower_limit":"","measurement_unit":"℃","realtime_data":null,"state":"1","current_value":"16.0","lng":null,"lat":null},{"detector_id":"d5cbe74213d811e98fa300163e047d1c","detector_name":"temperature_detector","detector_nameVal":"温度","detector_model":"","mac":"2806053637","detector_address_number":"","detector_port":"ckwd","detector_portVal":"C口","upper_limit":"","lower_limit":"","measurement_unit":"℃","realtime_data":null,"state":"0","current_value":"15.1","lng":null,"lat":null},{"detector_id":"e3b957b613d811e98fa300163e047d1c","detector_name":"temperature_detector","detector_nameVal":"温度","detector_model":"","mac":"2806053637","detector_address_number":"","detector_port":"dkwd","detector_portVal":"D口","upper_limit":"","lower_limit":"","measurement_unit":"℃","realtime_data":null,"state":"0","current_value":"15.7","lng":null,"lat":null}]
         */

        private String device_id;
        private String build_name;
        private String buildids;
        private String floorids;
        private String floor_name;
        private String device_name;
        private Object device_state;
        private Object lng;
        private Object lat;
        private List<ElectricalDetectorInfosBean> electricalDetectorInfos;

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

        public String getBuildids() {
            return buildids;
        }

        public void setBuildids(String buildids) {
            this.buildids = buildids;
        }

        public String getFloorids() {
            return floorids;
        }

        public void setFloorids(String floorids) {
            this.floorids = floorids;
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

        public Object getDevice_state() {
            return device_state;
        }

        public void setDevice_state(Object device_state) {
            this.device_state = device_state;
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

        public List<ElectricalDetectorInfosBean> getElectricalDetectorInfos() {
            return electricalDetectorInfos;
        }

        public void setElectricalDetectorInfos(List<ElectricalDetectorInfosBean> electricalDetectorInfos) {
            this.electricalDetectorInfos = electricalDetectorInfos;
        }

        public static class ElectricalDetectorInfosBean {
            /**
             * detector_id : 866a82d413d911e98fa300163e047d1c
             * detector_name : electricity_detector
             * detector_nameVal : 电流
             * detector_model :
             * mac : 2806053637
             * detector_address_number : 2
             * detector_port : akwd
             * detector_portVal : A口
             * upper_limit : 0
             * lower_limit : 7
             * measurement_unit : A
             * realtime_data : null
             * state : 1
             * current_value : 0.0
             * lng : null
             * lat : null
             */

            private String detector_id;
            private String detector_name;
            private String detector_nameVal;
            private String detector_model;
            private String mac;
            private String detector_address_number;
            private String detector_port;
            private String detector_portVal;
            private String upper_limit;
            private String lower_limit;
            private String measurement_unit;
            private Object realtime_data;
            private String state;
            private String current_value;
            private Object lng;
            private Object lat;

            public String getDetector_id() {
                return detector_id;
            }

            public void setDetector_id(String detector_id) {
                this.detector_id = detector_id;
            }

            public String getDetector_name() {
                return detector_name;
            }

            public void setDetector_name(String detector_name) {
                this.detector_name = detector_name;
            }

            public String getDetector_nameVal() {
                return detector_nameVal;
            }

            public void setDetector_nameVal(String detector_nameVal) {
                this.detector_nameVal = detector_nameVal;
            }

            public String getDetector_model() {
                return detector_model;
            }

            public void setDetector_model(String detector_model) {
                this.detector_model = detector_model;
            }

            public String getMac() {
                return mac;
            }

            public void setMac(String mac) {
                this.mac = mac;
            }

            public String getDetector_address_number() {
                return detector_address_number;
            }

            public void setDetector_address_number(String detector_address_number) {
                this.detector_address_number = detector_address_number;
            }

            public String getDetector_port() {
                return detector_port;
            }

            public void setDetector_port(String detector_port) {
                this.detector_port = detector_port;
            }

            public String getDetector_portVal() {
                return detector_portVal;
            }

            public void setDetector_portVal(String detector_portVal) {
                this.detector_portVal = detector_portVal;
            }

            public String getUpper_limit() {
                return upper_limit;
            }

            public void setUpper_limit(String upper_limit) {
                this.upper_limit = upper_limit;
            }

            public String getLower_limit() {
                return lower_limit;
            }

            public void setLower_limit(String lower_limit) {
                this.lower_limit = lower_limit;
            }

            public String getMeasurement_unit() {
                return measurement_unit;
            }

            public void setMeasurement_unit(String measurement_unit) {
                this.measurement_unit = measurement_unit;
            }

            public Object getRealtime_data() {
                return realtime_data;
            }

            public void setRealtime_data(Object realtime_data) {
                this.realtime_data = realtime_data;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getCurrent_value() {
                return current_value;
            }

            public void setCurrent_value(String current_value) {
                this.current_value = current_value;
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
