package com.sanleng.electricalfire.net;

/**
 * @author qiaoshi
 */
public class URLs {

    //云平台地址
    public static String HOST_IP = "47.100.192.169";
    public static String HOST_PORT = "8080";

    //调试地址
//    public static String HOST_IP = "10.101.80.113";
//    public static String HOST_PORT = "8080";

    public static String HOST = "http://" + HOST_IP + ":" + HOST_PORT;
    // 登陆
    public static String BULOGIN_URL = HOST + "/kspf/app/user/login";
    // 电气火灾建筑列表
    public static String Architecture_URL = HOST + "/kspf/app/electricalfire/buildList";
    // 电气火灾楼层列表
    public static String Floor_URL = HOST + "/kspf/app/electricalfire/floorList";
    // 电气火灾设备名列表
    public static String Device_URL = HOST + "/kspf/app/electricalfire/deviceNames";
    // 电气火灾设备列表
    public static String DeviceItem_URL = HOST + "/kspf/app/electricalfire/deviceList";
    // 电气火灾设备实时数据列表
    public static String RealTimeData_URL = HOST + "/kspf/app/electricalfire/deviceRealTimeData";
    // 密码修改
    public static String PasswordModification = HOST + "/kspf/app/user/appPassChange";
    // 电气火灾点位列表
    public static String PointPosition_URL = HOST + "/kspf/app/gsm/list";
    // 电气火灾报警列表
    public static String Police_URL = HOST + "/kspf/app/electricalfire/recordlist";
    // 电气火灾状态修改
    public static String ElectricalFire_URL = HOST + "/kspf/app/gsm/update";
    // 电气火灾时间轴数据
    public static String TIMEPUMP_URL = HOST + "/kspf/app/electricalfire/trajectory";

}
