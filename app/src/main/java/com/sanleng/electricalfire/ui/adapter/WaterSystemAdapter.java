package com.sanleng.electricalfire.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.ui.bean.WaterSystem;

import java.util.List;

/**
 * 水系统数据适配器
 *
 * @author QiaoShi
 */
public class WaterSystemAdapter extends BaseAdapter {
    private List<WaterSystem.DataBean.ListBean> mList;
    private Context mContext;

    /**
     * bindData用来传递数据给适配器。
     *
     * @list
     */
    public void bindData(Context mContext, List<WaterSystem.DataBean.ListBean> mList) {
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

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.watersystem_item, null);
            holder = new Holder();
            holder.name = convertView.findViewById(R.id.name);
            holder.value = convertView.findViewById(R.id.value);
            holder.range = convertView.findViewById(R.id.range);

            holder.image_devicetype = convertView.findViewById(R.id.image_devicetype);
            holder.img_state = convertView.findViewById(R.id.img_state);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        String device_type = mList.get(position).getDevice_type();
        String build_name = mList.get(position).getBuild_name();
        String device_address = mList.get(position).getDevice_address();
        String current_state = mList.get(position).getCurrent_state();
        String range_min = mList.get(position).getRange_min();
        String range_max = mList.get(position).getRange_max();
        //液压
        if (device_type.equals("watersystem_hyrant")) {
            holder.image_devicetype.setBackground(mContext.getResources().getDrawable(R.drawable.waterc_icon));
            holder.name.setText("地址: " + build_name + device_address);
            holder.value.setText("当前压力: " + current_state + " Mpa");
            holder.range.setText("安全压力: " + range_min + "～" + range_max + " Mpa");
        }
        //液位
        if (device_type.equals("watersystem_level")) {
            holder.image_devicetype.setBackground(mContext.getResources().getDrawable(R.drawable.waterc_icon));
            holder.name.setText("地址: " + build_name + device_address);
            holder.value.setText("当前液位: " + current_state + "M");
            holder.range.setText("安全液位: " + range_min + "～" + range_max + "M");
        }

        String state = mList.get(position).getState();
        if (state.equals("异常")) {
            holder.img_state.setBackground(mContext.getResources().getDrawable(R.drawable.abnormal));
        }
        if (state.equals("正常")) {
            holder.img_state.setBackground(mContext.getResources().getDrawable(R.drawable.normal));
        }

        return convertView;
    }

    class Holder {
        TextView name;
        TextView value;
        TextView range;
        ImageView image_devicetype;
        ImageView img_state;

    }
}
