package com.sanleng.electricalfire.net;

import com.sanleng.electricalfire.bean.Login;
import com.sanleng.electricalfire.bean.ReadTimeItemData;
import com.sanleng.electricalfire.bean.RealtimeData;

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


}
