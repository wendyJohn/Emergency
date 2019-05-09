package com.sanleng.electricalfire.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.ui.bean.HistorRecordBean;

import java.util.List;

/**
 * 出入库记录适配器
 *
 * @author QiaoShi
 */
public class HistorRecordAdapter extends BaseAdapter {

    private List<HistorRecordBean> mList;
    private Context mContext;

    public HistorRecordAdapter(Context mContext, List<HistorRecordBean> mList) {
        super();
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
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.historrecord_item, null);
            holder = new Holder();
//            holder.company = convertView.findViewById(R.id.company);
            holder.sitename = convertView.findViewById(R.id.sitename);
            holder.materialname = convertView.findViewById(R.id.materialname);
            holder.storagelocation = convertView.findViewById(R.id.storagelocation);
            holder.materialstatus = convertView.findViewById(R.id.materialstatus);
            holder.operator = convertView.findViewById(R.id.operator);
            holder.operationtime = convertView.findViewById(R.id.operationtime);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
//        holder.company.setText(mList.get(position).getCompany());
        holder.sitename .setText(mList.get(position).getSitename());
        holder.materialname .setText(mList.get(position).getMaterialname());
        holder.storagelocation.setText(mList.get(position).getStoragelocation());
        if(mList.get(position).getMaterialstatus().equals("emergencystation_in")){
            holder.materialstatus.setText("入库");
        }
        if(mList.get(position).getMaterialstatus().equals("emergencystation_out")){
            holder.materialstatus.setText("出库");
        }
        holder.operator.setText(mList.get(position).getOperator());
        holder.operationtime.setText(mList.get(position).getOperationtime());
        return convertView;
    }

    class Holder {
//        TextView company;
        TextView sitename;
        TextView materialname;
        TextView storagelocation;
        TextView materialstatus;
        TextView operator;
        TextView operationtime;
    }
}
