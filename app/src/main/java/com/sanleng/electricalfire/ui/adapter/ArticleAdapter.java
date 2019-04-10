package com.sanleng.electricalfire.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.ui.bean.Article;
import com.sanleng.electricalfire.util.ImageDown;

import java.util.List;
import java.util.Map;

/**
 * 文章数据适配器
 *
 * @author QiaoShi
 */
public class ArticleAdapter extends BaseAdapter {
    private Context context;
    private List<Article.DataBean.ListBean> list;

    /**
     * bindData用来传递数据给适配器。
     *
     * @param list
     */
    public void bindData(Context context, List<Article.DataBean.ListBean> list) {
        this.list = list;
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.article_item, parent, false);
            holder.imageviewdata = (ImageView) convertView.findViewById(R.id.imageviewdata);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.category = (TextView) convertView.findViewById(R.id.category);
            holder.frequency = (TextView) convertView.findViewById(R.id.frequency);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//		holder.imageviewdata.setImageResource(R.drawable.ic_launcher);
        holder.name.setText("专题类别：" +list.get(position).getTopic());
        holder.category.setText(list.get(position).getSubject_type());
        holder.frequency.setText(list.get(position).getBrowse_times()+ "次");

        // 接口回调的方法，完成图片的读取;
        ImageDown downImage = new ImageDown(list.get(position).getCover_img());
        downImage.loadImage(new ImageDown.ImageCallBack() {
            @Override
            public void getDrawable(Drawable drawable) {
                holder.imageviewdata.setImageDrawable(drawable);
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView name, category, frequency;
        ImageView imageviewdata;
    }
}
