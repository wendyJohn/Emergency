package com.sanleng.electricalfire.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.util.ImageDown;

import java.util.List;

/**
 * 上报详情适配器
 *
 * @author QiaoShi
 */
public class ReportingDetailsAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public ReportingDetailsAdapter(Context context, List<String> list) {
        // TODO Auto-generated constructor stub
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
            convertView = LayoutInflater.from(context).inflate(R.layout.reportingdetails_item, parent, false);
            holder.imageviewdata = convertView.findViewById(R.id.imageviewdata);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 接口回调的方法，完成图片的读取;
        ImageDown downImage = new ImageDown("https://slyj.slicity.com"+list.get(position).toString());
        downImage.loadImage(new ImageDown.ImageCallBack() {
            @Override
            public void getDrawable(Drawable drawable) {
                holder.imageviewdata.setImageDrawable(drawable);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView imageviewdata;
    }
}
