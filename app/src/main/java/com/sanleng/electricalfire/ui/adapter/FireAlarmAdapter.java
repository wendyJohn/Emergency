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
    private LinearLayout type_yout;
    private LinearLayout type_youts;

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
            type_yout = convertView.findViewById(R.id.type_yout);
            holder.cancles = convertView.findViewById(R.id.cancles);
            holder.viewlocations = convertView.findViewById(R.id.viewlocations);
            type_youts = convertView.findViewById(R.id.type_youts);

            holder.firetelephone = convertView.findViewById(R.id.firetelephone);
            holder.firetelephones = convertView.findViewById(R.id.firetelephones);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        if (type.equals("已处理")) {
            type_youts.setVisibility(View.VISIBLE);
            type_yout.setVisibility(View.GONE);
        }
        if (type.equals("待处理")) {
            type_youts.setVisibility(View.GONE);
            type_yout.setVisibility(View.VISIBLE);
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
        holder.viewlocations.setOnClickListener(new OnClickListener() {
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

        holder.firetelephones.setOnClickListener(new OnClickListener() {
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
        TextView viewlocations;
        TextView immediatetreatment;
        TextView firetelephone;
        TextView firetelephones;
    }
}
