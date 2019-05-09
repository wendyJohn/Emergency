package com.sanleng.electricalfire.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sanleng.electricalfire.MyApplication;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.ui.bean.FireAlarmBean;

import java.util.List;

/**
 * 火警数据适配器
 *
 * @author QiaoShi
 */
@SuppressLint("ResourceAsColor")
public class FireAlarmAdapter extends BaseAdapter {

    private List<FireAlarmBean.DataBean.ListBean> mList;
    private Context mContext;
    private Handler handler;
    private String type;

    /**
     * bindData用来传递数据给适配器。
     *
     * @param
     */
    public void bindData(Context mContext, List<FireAlarmBean.DataBean.ListBean> mList, String type, Handler handler) {
        this.mList = mList;
        this.mContext = mContext;
        this.type = type;
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.firealarm_item, null);

            holder = new Holder();
            holder.alarmTime = convertView.findViewById(R.id.alarmtime);
            holder.alarmEquipment = convertView.findViewById(R.id.alarmequipment);
            holder.alarmPosition = convertView.findViewById(R.id.alarmposition);
            holder.alarmUnit = convertView.findViewById(R.id.alarmunit);
            holder.cancle = convertView.findViewById(R.id.cancle);
            holder.viewlocation = convertView.findViewById(R.id.viewlocation);
            holder.immediatetreatment = convertView.findViewById(R.id.immediatetreatment);
            holder.type_yout = convertView.findViewById(R.id.type_yout);
            holder.cancles = convertView.findViewById(R.id.cancles);
            holder.processingreport = convertView.findViewById(R.id.processingreport);
            holder. type_youts = convertView.findViewById(R.id.type_youts);

            holder.firetelephone = convertView.findViewById(R.id.firetelephone);
            holder.reportingdetails = convertView.findViewById(R.id.reportingdetails);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        if (type.equals("已处理")) {
            holder.type_youts.setVisibility(View.VISIBLE);
            holder.type_yout.setVisibility(View.GONE);
        }
        if (type.equals("待处理")) {
            holder. type_youts.setVisibility(View.GONE);
            holder.type_yout.setVisibility(View.VISIBLE);
        }

        holder.alarmTime.setText(mList.get(position).getReceive_time());
        holder.alarmEquipment.setText(mList.get(position).getDevice_name());
        holder.alarmPosition.setText(mList.get(position).getPosition());
        holder.alarmUnit.setText(mList.get(position).getUnit_name());
        holder.cancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putInt("selIndex", position);
                msg.setData(data);
                msg.what = MyApplication.MSGViewMonitoring;
                handler.sendMessage(msg);
            }
        });
        holder.cancles.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putInt("selIndex", position);
                msg.setData(data);
                msg.what = MyApplication.MSGViewMonitoring;
                handler.sendMessage(msg);
            }
        });
        holder.immediatetreatment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putInt("selIndex", position);
                msg.setData(data);
                msg.what = MyApplication.MSGHANDLE;
                handler.sendMessage(msg);
            }
        });

        holder.viewlocation.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putInt("selIndex", position);
                msg.setData(data);
                msg.what = MyApplication.MSGViewlocation;
                handler.sendMessage(msg);
            }
        });
        holder.processingreport.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putInt("selIndex", position);
                msg.setData(data);
                msg.what = MyApplication.MSGProcessingReport;
                handler.sendMessage(msg);
            }
        });

        holder.firetelephone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putInt("selIndex", position);
                msg.setData(data);
                msg.what = MyApplication.MSGFiretelePhone;
                handler.sendMessage(msg);
            }
        });

        holder.reportingdetails.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putInt("selIndex", position);
                msg.setData(data);
                msg.what = MyApplication.MSGReportingDetails;
                handler.sendMessage(msg);
            }
        });
        return convertView;
    }

    class Holder {
        TextView alarmTime;
        TextView alarmEquipment;
        TextView alarmPosition;
        TextView alarmUnit;
        TextView cancle;
        TextView viewlocation;
        TextView cancles;
        TextView processingreport;
        TextView immediatetreatment;
        TextView firetelephone;
        TextView reportingdetails;
        LinearLayout type_yout;
        LinearLayout type_youts;
    }
}
