package com.sanleng.electricalfire.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.loopj.android.http.RequestParams;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.bean.ERealTimeDataBean;
import com.sanleng.electricalfire.net.NetCallBack;
import com.sanleng.electricalfire.net.RequestUtils;
import com.sanleng.electricalfire.net.URLs;
import com.sanleng.electricalfire.util.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 智能电气火灾实时数据适配器
 *
 * @author QiaoShi
 */
public class RealTimeDataAdapter extends BaseAdapter {

    private List<ERealTimeDataBean> mList;
    private Context mContext;
    private Handler handler;
    private String t_data;
    private String t_limit;

    private String e_data;
    private String e_limit;

    private String c_data;
    private String c_limit;

    /**
     * bindData用来传递数据给适配器。
     *
     * @list
     */
    public void bindData(Context mContext, List<ERealTimeDataBean> mList, Handler handler) {
        this.mContext = mContext;
        this.mList = mList;
        this.handler = handler;
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
            holder.address = (TextView) convertView.findViewById(R.id.w_address);
            holder.temperature = (TextView) convertView.findViewById(R.id.temperature);
            holder.temperaturelimit = (TextView) convertView.findViewById(R.id.temperaturelimit);
            holder.residualcurrent = (TextView) convertView.findViewById(R.id.residualcurrent);
            holder.currentlimit = (TextView) convertView.findViewById(R.id.currentlimit);
            holder.temperaturealarm = (ImageView) convertView.findViewById(R.id.temperaturealarm);
            holder.temperaturealarms = (ImageView) convertView.findViewById(R.id.temperaturealarms);
            holder.img_down = (ImageView) convertView.findViewById(R.id.img_down);

            holder.contactnumber = convertView.findViewById(R.id.contactnumber);


            holder.current = (TextView) convertView.findViewById(R.id.current);
            holder.current_limits = (TextView) convertView.findViewById(R.id.current_limits);

            holder.electricalmaintenance = (TextView) convertView.findViewById(R.id.electricalmaintenance);
            holder.pendingdisposal = (TextView) convertView.findViewById(R.id.pendingdisposal);

            holder.confirmphoto = (TextView) convertView.findViewById(R.id.confirmphoto);

            holder.historicaltrack = (TextView) convertView.findViewById(R.id.historicaltrack);
            holder.message_item_unread = (TextView) convertView.findViewById(R.id.message_item_unread);

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
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putInt("selIndex", position);
                msg.setData(data);
                msg.what = 66660;
                handler.sendMessage(msg);
            }
        });
        //待处理
        holder.pendingdisposal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putInt("selIndex", position);
                msg.setData(data);
                msg.what = 66661;
                handler.sendMessage(msg);
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
                    final String contact_name = mList.get(position).getContact_name();
                    String contact_tel = mList.get(position).getContact_tel();
                    holder.electricalmaintenance.setText("电气维修：" + contact_name);
                    holder.contactnumber.setText("电话：" + contact_tel);

                    RequestParams params = new RequestParams();
                    params.put("device_id", device_id);
                    params.put("username", PreferenceUtils.getString(mContext, "ElectriFire_username"));
                    params.put("platformkey", "app_firecontrol_owner");
                    RequestUtils.ClientPost(URLs.RealTimeData_URL, params, new NetCallBack() {
                        @Override
                        public void onStart() {
                            super.onStart();
                        }

                        @Override
                        public void onMySuccess(String result) {
                            if (result == null || result.length() == 0) {
                                return;
                            }
                            System.out.println("数据请求成功" + result);
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                String msg = jsonObject.getString("msg");
                                if (msg.equals("获取数据成功")) {
                                    String data = jsonObject.getString("data");
                                    JSONObject Object = new JSONObject(data);
                                    String build_name = Object.getString("build_name");
                                    String device_name = Object.getString("device_name");
                                    String electricalDetectorInfos = Object.getString("electricalDetectorInfos");
                                    JSONArray array = new JSONArray(electricalDetectorInfos);
                                    JSONObject object;
                                    for (int i = 0; i < array.length(); i++) {
                                        object = (JSONObject) array.get(i);
                                        String detector_name = object.getString("detector_name");
                                        String realtime_data = object.getString("current_value");
                                        String measurement_unit = object.getString("measurement_unit");
                                        String lower_limit = object.getString("lower_limit");
                                        String upper_limit = object.getString("upper_limit");
                                        if (detector_name.equals("temperature_detector")) {
                                            t_data = "温度：" + realtime_data + measurement_unit;
                                            t_limit = "限值：" + lower_limit + "-" + upper_limit;
                                            holder.temperature.setText(t_data);
                                            holder.temperaturelimit.setText(t_limit);
                                        }
                                        if (detector_name.equals("electricity_detector")) {
                                            e_data = "剩余电流：" + realtime_data + measurement_unit;
                                            e_limit = "限值：" + lower_limit + "-" + upper_limit;
                                            holder.residualcurrent.setText(e_data);
                                            holder.currentlimit.setText(e_limit);
                                        }
                                        if (detector_name.equals("residualcurrent_detector")) {
                                            c_data = "电流：" + realtime_data + measurement_unit;
                                            c_limit = "限值：" + lower_limit + "-" + upper_limit;
                                            holder.current.setText(c_data);
                                            holder.current_limits.setText(c_limit);

                                        }
                                    }


                                }

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onMyFailure(Throwable arg0) {
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
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putInt("selIndex", position);
                msg.setData(data);
                msg.what = 66662;
                handler.sendMessage(msg);
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
