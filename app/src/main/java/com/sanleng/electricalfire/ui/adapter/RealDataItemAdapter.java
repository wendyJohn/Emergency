package com.sanleng.electricalfire.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.ui.bean.ReadTimeItemData;

import java.util.List;

/**
 * 电气火灾数据适配器
 * @author QiaoShi
 */
public class RealDataItemAdapter extends BaseAdapter {
    private Context context;
    List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list;

    public RealDataItemAdapter(Context context,List<ReadTimeItemData.DataBean.ElectricalDetectorInfosBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.realdata_item, parent, false);
            holder.name = convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String detector_name=list.get(position).getDetector_name();
        if(detector_name.equals("temperature_detector")){

//            String strs=list.get(position).getRealtime_data().toString();
//            int str=Double.valueOf(strs).intValue();//转换为Int类型
//            if(str >= 80){
//                holder.name.setTextColor(context.getResources().getColor(R.color.red));
//            }
            holder.name.setText(list.get(position).getDetector_portVal()+" | "+"温度: "+list.get(position).getRealtime_data()+" "+list.get(position).getMeasurement_unit()+"  限值:"+list.get(position).getLower_limit()+"～"+list.get(position).getUpper_limit()+" "+list.get(position).getMeasurement_unit());
        }
        if(detector_name.equals("electricity_detector")){
//            int str=Integer.parseInt(list.get(position).getRealtime_data().toString());
//            if(str >= 80){
//                holder.name.setTextColor(context.getResources().getColor(R.color.red));
//            }
            holder.name.setText(list.get(position).getDetector_portVal()+" | "+"电流: "+list.get(position).getRealtime_data()+" "+list.get(position).getMeasurement_unit()+"  限值:"+list.get(position).getLower_limit()+"～"+list.get(position).getUpper_limit()+" "+list.get(position).getMeasurement_unit());
        }
        if(detector_name.equals("residualcurrent_detector")){
//            int str=Double.valueOf(list.get(position).getRealtime_data().toString()).intValue();//转换为Int类型
//            if(str >= 300){
//                holder.name.setTextColor(context.getResources().getColor(R.color.red));
//            }
            holder.name.setText(list.get(position).getDetector_portVal()+" | "+"剩余电流: "+list.get(position).getRealtime_data()+" "+list.get(position).getMeasurement_unit()+"\n"+"限值："+list.get(position).getLower_limit()+"～"+list.get(position).getUpper_limit()+" "+list.get(position).getMeasurement_unit());
        }
        if(detector_name.equals("voltage_detector")){
            holder.name.setText(list.get(position).getDetector_portVal()+" | "+"电压: "+list.get(position).getRealtime_data()+" "+list.get(position).getMeasurement_unit()+"  限值:"+list.get(position).getLower_limit()+"～"+list.get(position).getUpper_limit()+" "+list.get(position).getMeasurement_unit());
        }
        return convertView;
    }

    class ViewHolder {
        TextView name;
    }
}
