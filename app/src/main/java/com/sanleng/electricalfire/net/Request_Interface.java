package com.sanleng.electricalfire.net;

import com.sanleng.electricalfire.ui.bean.AlarmRecord;
import com.sanleng.electricalfire.ui.bean.Alarmload;
import com.sanleng.electricalfire.ui.bean.Article;
import com.sanleng.electricalfire.ui.bean.ArticleItem;
import com.sanleng.electricalfire.ui.bean.FireAlarmBean;
import com.sanleng.electricalfire.ui.bean.HistoricalTrack;
import com.sanleng.electricalfire.ui.bean.Login;
import com.sanleng.electricalfire.ui.bean.NearbyStation;
import com.sanleng.electricalfire.ui.bean.ReadTimeItemData;
import com.sanleng.electricalfire.ui.bean.RealtimeData;
import com.sanleng.electricalfire.ui.bean.RealtimeDatas;
import com.sanleng.electricalfire.ui.bean.Rescue;
import com.sanleng.electricalfire.ui.bean.Sosbean;
import com.sanleng.electricalfire.ui.bean.Version;
import com.sanleng.electricalfire.ui.bean.WaterSystem;
import com.sanleng.electricalfire.ui.bean.WaterSystemStatistics;
import com.sanleng.electricalfire.util.PreferenceUtils;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Request_Interface {

    //登录
    @POST("/kspf/app/user/login?")
    Call<Login> getloginCall(@Query("username") String username, @Query("password") String password, @Query("platformkey") String platformkey);

    //获取有数据的设备列表
    @POST("/kspf/app/electricalfire/deviceList?")
    Call<RealtimeData> getReadtimeDataCall(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("unit_id") String unit_id, @Query("username") String username, @Query("platformkey") String platformkey);

    //获取无数据的设备列表（巡鹰设备）
    @POST("/kspf/app/gsm/list?")
    Call<RealtimeDatas> getReadtimeDatasCall(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("unit_code") String unit_code, @Query("username") String username, @Query("platformkey") String platformkey);

    //获取设备实时数据
    @POST("/kspf/app/electricalfire/deviceRealTimeData?")
    Call<ReadTimeItemData> getReadtimeItemDataCall(@Query("device_id") String device_id, @Query("username") String username, @Query("platformkey") String platformkey);

    //获取报警信息
    @POST("/kspf/app/electricalfire/recordlist?")
    Call<RealtimeData> getAlarmDataCall(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("unit_id") String unit_id, @Query("username") String username, @Query("platformkey") String platformkey);

    //获取设备历史轨迹
    @POST("/kspf/app/electricalfire/trajectory?")
    Call<HistoricalTrack> getHistoricalTrackCall(@Query("deviceId") String device_id, @Query("username") String username, @Query("platformkey") String platformkey);

    //水系统异常统计
    @POST("/kspf/app/water/count?")
    Call<WaterSystemStatistics> getWaterSystemStatisticsCall(@Query("unitcode") String unitcode, @Query("username") String username, @Query("platformkey") String platformkey);

    //获取水系统数据
    @POST("/kspf/app/water/list?")
    Call<WaterSystem> getWaterSystemCall(@Query("page") String page, @Query("pageSize") String pageSize, @Query("unitcode") String unitcode, @Query("username") String username, @Query("platformkey") String platformkey, @Query("fireSysName") String fireSysName);

    //获取版本号与下载链接
    @POST("/kspf/app/version/getApk?")
    Call<Version> getVersionCall(@Query("osType") String osType, @Query("platformkey") String platformkey);

    //火警统计
    @POST("/kspf/app/ferecord/count?")
    Call<Alarmload> getAlarmLoadCall(@Query("unitcode") String unitcode, @Query("username") String username, @Query("platformkey") String platformkey);

    //获取火警
    @POST("/kspf/app/ferecord/firealertlist?")
    Call<FireAlarmBean> getFireAlarmCall(@Query("page") String page, @Query("pageSize") String pageSize, @Query("unitcode") String unitcode, @Query("username") String username, @Query("platformkey") String platformkey, @Query("status") String status, @Query("scope") String scope);

    //火警确认
    @POST("/kspf/app/ferecord/isTrueFire?")
    Call<String> getFireConfirmationCall(@Query("ids") String ids, @Query("alarmtype") String alarmtype, @Query("username") String username, @Query("platformkey") String platformkey);

    //文章视频
    @POST("/kspf/app/publicityedu/list?")
    Call<Article> getArticleCall(@Query("page") String page, @Query("pageSize") String pageSize, @Query("username") String username, @Query("platformkey") String platformkey, @Query("publicitytype") String publicitytype);

    //文章视频详情
    @POST("/kspf/app/publicityedu/info?")
    Call<ArticleItem> getArticleItemCall(@Query("ids") String ids, @Query("username") String username, @Query("platformkey") String platformkey);

    //获取附近的应急站
    @POST("/kspf/app/station/distance?")
    Call<NearbyStation> getNearbyStationCall(@Query("lat") String lat, @Query("lng") String lng, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("username") String username, @Query("platformkey") String platformkey, @Query("name") String name);

    //获取附近的SOS
    @POST("/kspf/app/stationassistant/list?")
    Call<Sosbean> getSosCall(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("unit_code") String unitcode, @Query("username") String username, @Query("platformkey") String platformkey);

    //应急开锁
    @POST("/emergencystation/{position}/{mac}")
    Call<String> getUnlockCall(@Path("position") String position, @Path("mac") String mac);

    //SOS救助
    @POST("/kspf/app/stationassistant/add?")
    Call<Rescue> getRescueCall(@Query("lat") String lat, @Query("lng") String lng, @Query("name") String name, @Query("phone") String phone, @Query("identitycrad") String identitycrad, @Query("type") String type, @Query("address") String address, @Query("unitCode") String unitCode, @Query("username") String username, @Query("platformkey") String platformkey);

    //报警记录
    @POST("/kspf/app/gsm/recordlist?")
    Call<AlarmRecord> getAlarmRecordCall(@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("unit_code") String unit_code, @Query("username") String username, @Query("platformkey") String platformkey);

    //报警状态修改
    @POST("/kspf/app/gsm/update?")
    Call<String> getAlarmUpdateCall(@Query("ids") String ids, @Query("state") String state, @Query("username") String username, @Query("platformkey") String platformkey);

}
