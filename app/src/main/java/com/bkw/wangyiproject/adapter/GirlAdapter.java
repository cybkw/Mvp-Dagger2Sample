package com.bkw.wangyiproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bkw.wangyiproject.R;
import com.bkw.wangyiproject.entity.GirlBean;

import java.util.List;

public class GirlAdapter extends BaseAdapter {
    private Context context;
    private List<GirlBean> girlBeans;

    public GirlAdapter(Context context, List<GirlBean> girlBeans) {
        this.context = context;
        this.girlBeans = girlBeans;
    }

    @Override
    public int getCount() {
        Log.e("GirlAdapter", "getCount" + girlBeans.size());
        return girlBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return girlBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (null == holder) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_girl, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.iv_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setImageResource(R.mipmap.ic_launcher_round);

        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
    }
}
