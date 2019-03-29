package com.sanleng.electricalfire.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sanleng.electricalfire.MyApplication;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.ui.bean.ERealTimeDataBean;
import com.sanleng.electricalfire.ui.bean.ReadTimeItemData;
import com.sanleng.electricalfire.net.Request_Interface;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.util.MessageEvent;
import com.sanleng.electricalfire.util.PreferenceUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 智能电气火灾实时数据适配器
 *
 * @author QiaoShi
 */
public class RealTimeDataAdapter extends BaseAdapter {

    private List<ERealTimeDataBean> mList;
    private Context mContext;
    private String t_data;
    private String t_limit;

    private String e_data;
    private String e_limit;

    private String c_data;
    private String c_limit;
    private String buildids;
    private String floorids;
    private String electricalDetectorInfos;

    /**
     * bindData用来传递数据给适配器。
     *
     * @list
     */
    public void bindData(Context mContext, List<ERealTimeDataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("deprecation")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.e_realtimedata_items, null);
            holder = new Holder();
            holder.address = convertView.findViewById(R.id.w_address);
            holder.temperature = convertView.findViewById(R.id.temperature);
            holder.temperaturelimit = convertView.findViewById(R.id.temperaturelimit);
            holder.residualcurrent = convertView.findViewById(R.id.residualcurrent);
            holder.currentlimit = convertView.findViewById(R.id.currentlimit);
            holder.temperaturealarm = convertView.findViewById(R.id.temperaturealarm);
            holder.temperaturealarms = convertView.findViewById(R.id.temperaturealarms);
            holder.img_down = convertView.findViewById(R.id.img_down);

            holder.contactnumber = convertView.findViewById(R.id.contactnumber);

            holder.current = convertView.findViewById(R.id.current);
            holder.current_limits = convertView.findViewById(R.id.current_limits);

            holder.electricalmaintenance = convertView.findViewById(R.id.electricalmaintenance);
            holder.pendingdisposal = convertView.findViewById(R.id.pendingdisposal);

            holder.confirmphoto = convertView.findViewById(R.id.confirmphoto);

            holder.historicaltrack = convertView.findViewById(R.id.historicaltrack);
            holder.message_item_unread = convertView.findViewById(R.id.message_item_unread);

            holder.dress_youtc = convertView.findViewById(R.id.dress_youtc);
            holder.dress_youtb = convertView.findViewById(R.id.dress_youtb);
            holder.dress_youte = convertView.findViewById(R.id.dress_youte);
            holder.dress_youtk = convertView.findViewById(R.id.dress_youtk);
            holder.ll_dialog_bottom = convertView.findViewById(R.id.ll_dialog_bottom);
            holder.r_onclister = convertView.findViewById(R.id.r_onclister);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.address.setText(mList.get(position).getAddress());
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(R.drawable.ealarm).apply(options).into(holder.temperaturealarm);
        String state = mList.get(position).getState();

        if (state.equals("0")) {
            holder.message_item_unread.setVisibility(View.GONE);
            holder.temperaturealarm.setVisibility(View.GONE);
            holder.temperaturealarms.setVisibility(View.VISIBLE);
            holder.temperaturealarms.setBackground(mContext.getResources().getDrawable(R.drawable.ealarms));
        }
        if (state.equals("1")) {
            holder.message_item_unread.setVisibility(View.VISIBLE);
            holder.temperaturealarm.setVisibility(View.VISIBLE);
            holder.temperaturealarms.setVisibility(View.GONE);
        }

        //确认拍照
        holder.confirmphoto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                MessageEvent messageEvent = new MessageEvent(MyApplication.MESSREALTIMEDATAA);
                messageEvent.setPosition(position);
                messageEvent.setBuildids(buildids);
                messageEvent.setFloorids(floorids);
                messageEvent.setElectricalDetectorInfos(electricalDetectorInfos);
                EventBus.getDefault().post(messageEvent);
            }
        });
        //待处理
        holder.pendingdisposal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                MessageEvent messageEvent = new MessageEvent(MyApplication.MESSREALTIMEDATAB);
                messageEvent.setPosition(position);
                EventBus.getDefault().post(messageEvent);
            }
        });
        //ITEM点击切换
        holder.r_onclister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isopen = mList.get(position).isopen;
                if (isopen == true) {
                    holder.img_down.animate().setDuration(500).rotation(0).start();
                    holder.dress_youte.setVisibility(View.GONE);
                    holder.dress_youtb.setVisibility(View.GONE);
                    holder.dress_youtc.setVisibility(View.GONE);
                    holder.dress_youtk.setVisibility(View.GONE);
                    holder.ll_dialog_bottom.setVisibility(View.GONE);
                    mList.get(position).isopen = false;
                } else {
                    holder.img_down.animate().setDuration(500).rotation(180).start();
                    holder.dress_youte.setVisibility(View.VISIBLE);
                    holder.dress_youtb.setVisibility(View.VISIBLE);
                    holder.dress_youtc.setVisibility(View.VISIBLE);
                    holder.dress_youtk.setVisibility(View.VISIBLE);
                    holder.ll_dialog_bottom.setVisibility(View.VISIBLE);

                    String device_id = mList.get(position).getId();
                    String contact_name = mList.get(position).getContact_name();
                    String contact_tel = mList.get(position).getContact_tel();
                    holder.electricalmaintenance.setText("电气维修：" + contact_name);
                    holder.contactnumber.setText("电话：" + contact_tel);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(URLs.HOST) // 设置 网络请求 Url
                            .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                            .build();
                    Request_Interface request_Interface = retrofit.create(Request_Interface.class);
                    //对 发送请求 进行封装
                    Call<ReadTimeItemData> call = request_Interface.getReadtimeItemDataCall(device_id, PreferenceUtils.getString(mContext, "ElectriFire_username"), "app_firecontrol_owner");
                    call.enqueue(new Callback<ReadTimeItemData>() {
                        @Override
                        public void onResponse(Call<ReadTimeItemData> call, Response<ReadTimeItemData> response) {
                            buildids = response.body().getData().getBuildids();
                            floorids = response.body().getData().getFloorids();
                            electricalDetectorInfos = response.body().getData().getElectricalDetectorInfos().toString();

                            for (int i = 0; i < response.body().getData().getElectricalDetectorInfos().size(); i++) {
                                String detector_name = response.body().getData().getElectricalDetectorInfos().get(i).getDetector_name();
                                String realtime_data = response.body().getData().getElectricalDetectorInfos().get(i).getCurrent_value();
                                String measurement_unit = response.body().getData().getElectricalDetectorInfos().get(i).getMeasurement_unit();
                                String lower_limit = response.body().getData().getElectricalDetectorInfos().get(i).getLower_limit();
                                String upper_limit = response.body().getData().getElectricalDetectorInfos().get(i).getUpper_limit();
                                String detector_portVal = response.body().getData().getElectricalDetectorInfos().get(i).getDetector_portVal();


                                if (detector_name.equals("temperature_detector")) {
                                    if (detector_portVal.equals("D口")) {
                                        t_data = "温度：" + realtime_data +" "+ measurement_unit;
                                        t_limit = "限值：" + lower_limit + "-" + upper_limit+" "+measurement_unit;;
                                        holder.temperature.setText(t_data);
                                        holder.temperaturelimit.setText(t_limit);
                                    }
                                }
                                if (detector_name.equals("residualcurrent_detector")) {
                                    if (detector_portVal.equals("A口")) {
                                        e_data = "剩余电流：" + realtime_data +" "+measurement_unit;
                                        e_limit = "限值：" + lower_limit + "-" + upper_limit+" "+measurement_unit;;
                                        holder.residualcurrent.setText(e_data);
                                        holder.currentlimit.setText(e_limit);
                                    }
                                }
                                if (detector_name.equals("electricity_detector")) {
                                    if (detector_portVal.equals("A口")) {
                                        c_data = "电流：" + realtime_data +" "+measurement_unit;
                                        c_limit = "限值：" + lower_limit + "-" + upper_limit+" "+measurement_unit;;
                                        holder.current.setText(c_data);
                                        holder.current_limits.setText(c_limit);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ReadTimeItemData> call, Throwable t) {

                        }
                    });
                    mList.get(position).isopen = true;
                }
            }
        });
        //历史轨迹
        holder.historicaltrack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                MessageEvent messageEvent = new MessageEvent(MyApplication.MESSREALTIMEDATAC);
                messageEvent.setPosition(position);
                EventBus.getDefault().post(messageEvent);
            }
        });


        return convertView;
    }

    class Holder {
        TextView address;
        TextView temperature;
        TextView temperaturelimit;
        TextView current;
        TextView current_limits;
        TextView residualcurrent;
        TextView electricalmaintenance;
        TextView currentlimit;
        TextView contactnumber;
        TextView confirmphoto;
        TextView pendingdisposal;
        TextView historicaltrack;
        TextView message_item_unread;

        ImageView temperaturealarm;
        ImageView temperaturealarms;
        ImageView img_down;

        LinearLayout dress_youtc;
        LinearLayout dress_youtb;
        LinearLayout dress_youte;
        LinearLayout dress_youtk;
        LinearLayout ll_dialog_bottom;
        RelativeLayout r_onclister;
    }
}
