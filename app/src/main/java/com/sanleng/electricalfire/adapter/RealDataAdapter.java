package com.sanleng.electricalfire.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.bean.ERealTimeDataBean;

import java.util.List;

/**
 * 智能电气火灾实时数据适配器
 *
 * @author QiaoShi
 */
public class RealDataAdapter extends BaseAdapter {
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

    @SuppressWarnings("deprecation")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.realedata_item, null);
            holder = new Holder();
            holder.address = convertView.findViewById(R.id.w_address);
            holder.temperaturealarm = convertView.findViewById(R.id.temperaturealarm);
            holder.temperaturealarms = convertView.findViewById(R.id.temperaturealarms);
            holder.img_down = convertView.findViewById(R.id.img_down);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.address.setText(mList.get(position).getAddress());
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(R.drawable.ealarm).apply(options).into(holder.temperaturealarm);
        String state = mList.get(position).getState();

        if (state.equals("0")) {
            holder.temperaturealarm.setVisibility(View.GONE);
            holder.temperaturealarms.setVisibility(View.VISIBLE);
            holder.temperaturealarms.setBackground(mContext.getResources().getDrawable(R.drawable.ealarms));
        }
        if (state.equals("1")) {
            holder.temperaturealarm.setVisibility(View.VISIBLE);
            holder.temperaturealarms.setVisibility(View.GONE);
        }

        return convertView;
    }

    class Holder {
        TextView address;
        ImageView temperaturealarm;
        ImageView temperaturealarms;
        ImageView img_down;

    }
}
