package com.sanleng.electricalfire.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sanleng.electricalfire.MyApplication;
import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

public class PendingAdapter extends BaseAdapter {

    private List<HashMap<String, Object>> list;
    private Context context;
    private Control control;

    private static class ViewHolder {
        private TextView name;
        private TextView postion;
        private TextView time;
        private LinearLayout type_yout;
    }

    public PendingAdapter(Context context,Control control, List<HashMap<String, Object>> list) {
        this.context = context;
        this.control = control;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.pending_item, null);

            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.postion = convertView.findViewById(R.id.postion);
            viewHolder.time = convertView.findViewById(R.id.time);
            viewHolder.type_yout = convertView.findViewById(R.id.type_yout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText("设备名称：" + list.get(position).get("name").toString() + "异常");
        viewHolder.postion.setText("设备位置：" + list.get(position).get("postion").toString());
        viewHolder.time.setText("报警时间：" + list.get(position).get("time").toString());

        viewHolder.type_yout.setOnClickListener(new MyListen(position));

        return convertView;
    }


    private class MyListen implements View.OnClickListener {
        int position;
        public MyListen(int position) {
            // TODO Auto-generated constructor stub
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            control.getPosition(position);
        }

    }

    // 内部接口
    public interface Control {
        void getPosition(int position);
    }
}