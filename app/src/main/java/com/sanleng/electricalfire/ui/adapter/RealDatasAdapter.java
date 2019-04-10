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
import com.sanleng.electricalfire.ui.bean.ERealTimeDataBean;

import java.util.List;

/**
 * 智能电气火灾实时数据适配器
 *
 * @author QiaoShi
 */
public class RealDatasAdapter extends BaseAdapter {
    private List<ERealTimeDataBean> mList;
    private Context mContext;

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

    @SuppressLint("SetTextI18n")
    @SuppressWarnings("deprecation")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.realedatas_item, null);
            holder = new Holder();
            holder.address = convertView.findViewById(R.id.w_address);
            holder.temperaturealarm = convertView.findViewById(R.id.temperaturealarm);
            holder.temperaturealarms = convertView.findViewById(R.id.temperaturealarms);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.address.setText("位置: " + mList.get(position).getAddress() );
        String state = mList.get(position).getState();

        if (state.equals("0")) {
            holder.temperaturealarm.setVisibility(View.GONE);
            holder.temperaturealarms.setVisibility(View.VISIBLE);
            holder.temperaturealarms.setBackground(mContext.getResources().getDrawable(R.drawable.e_ealarms));
        }
        if (state.equals("1")) {
            holder.temperaturealarm.setVisibility(View.VISIBLE);
            holder.temperaturealarms.setVisibility(View.GONE);
            holder.temperaturealarm.setBackground(mContext.getResources().getDrawable(R.drawable.e_ealarm));
        }

        return convertView;
    }

    class Holder {
        TextView address;
        ImageView temperaturealarm;
        ImageView temperaturealarms;

    }
}
