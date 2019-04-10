package com.sanleng.electricalfire.ui.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.ui.bean.FireAlarmBean;

import java.util.List;

/**
 * 火警数据适配器
 *
 * @author QiaoShi
 *
 */
@SuppressLint("ResourceAsColor")
public class MisreportAdapter extends BaseAdapter {

	private List<FireAlarmBean.DataBean.ListBean> mList;
	private Context mContext;

	/**
	 * bindData用来传递数据给适配器。
	 *
	 * @param list
	 */
	public void bindData(Context mContext, List<FireAlarmBean.DataBean.ListBean> mList) {
		this.mList = mList;
		this.mContext = mContext;
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.fault_item, null);

			holder = new Holder();
			holder.alarmTime = (TextView) convertView.findViewById(R.id.alarmtime);
			holder.alarmEquipment = (TextView) convertView.findViewById(R.id.alarmequipment);
			holder.alarmPosition = (TextView) convertView.findViewById(R.id.alarmposition);
			holder.alarmUnit = (TextView) convertView.findViewById(R.id.alarmunit);
			holder.immediatetreatment = (TextView) convertView.findViewById(R.id.immediatetreatment);

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.alarmTime.setText(mList.get(position).getReceive_time());
		holder.alarmEquipment.setText(mList.get(position).getDevice_name());
		holder.alarmPosition.setText(mList.get(position).getPosition());
		holder.alarmUnit.setText(mList.get(position).getUnit_name());
		return convertView;
	}

	class Holder {
		TextView alarmTime;
		TextView alarmEquipment;
		TextView alarmPosition;
		TextView alarmUnit;
		TextView immediatetreatment;
	}
}
