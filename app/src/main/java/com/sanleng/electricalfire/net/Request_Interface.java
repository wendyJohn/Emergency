package com.sanleng.electricalfire.net;

import com.sanleng.electricalfire.ui.bean.HistoricalTrack;
import com.sanleng.electricalfire.ui.bean.Login;
import com.sanleng.electricalfire.ui.bean.ReadTimeItemData;
import com.sanleng.electricalfire.ui.bean.RealtimeData;
import com.sanleng.electricalfire.ui.bean.Version;
import com.sanleng.electricalfire.ui.bean.WaterSystem;
import com.sanleng.electricalfire.ui.bean.WaterSystemStatistics;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Request_Interface {

    //登录
    @POST("/kspf/app/user/login?")
    Call<Login> getloginCall(@Query("username") String username, @Query("password") String password, @Query("platformkey") String platformkey);

    //获取设备列表
    @POST("/kspf/app/electricalfire/deviceList?")
    Call<RealtimeData> getReadtimeDataCall(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("unit_id") String unit_id, @Query("username") String username, @Query("platformkey") String platformkey);

    //获取设备实时数据
    @POST("/kspf/app/electricalfire/deviceRealTimeData?")
    Call<ReadTimeItemData> getReadtimeItemDataCall(@Query("device_id") String device_id , @Query("username") String username, @Query("platformkey") String platformkey);

    //获取报警信息
    @POST("/kspf/app/electricalfire/recordlist?")
    Call<RealtimeData> getAlarmDataCall(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("unit_id") String unit_id, @Query("username") String username, @Query("platformkey") String platformkey);

    //获取设备历史轨迹
    @POST("/kspf/app/electricalfire/trajectory?")
    Call<HistoricalTrack> getHistoricalTrackCall(@Query("deviceId") String device_id , @Query("username") String username, @Query("platformkey") String platformkey);

    //水系统异常统计
    @POST("/kspf/app/water/count?")
    Call<WaterSystemStatistics> getWaterSystemStatisticsCall(@Query("unitcode") String unitcode, @Query("username") String username, @Query("platformkey") String platformkey);

    //获取水系统数据
    @POST("/kspf/app/water/list?")
    Call<WaterSystem> getWaterSystemCall(@Query("page") String page, @Query("pageSize") String pageSize, @Query("unitcode") String unitcode, @Query("username") String username, @Query("platformkey") String platformkey, @Query("fireSysName") String fireSysName);

    //获取版本号与下载链接
    @POST("/kspf/app/version/getApk?")
    Call<Version> getVersionCall(@Query("osType") String osType, @Query("platformkey") String platformkey);
}
