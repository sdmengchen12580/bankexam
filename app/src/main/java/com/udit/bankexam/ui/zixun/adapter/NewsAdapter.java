package com.udit.bankexam.ui.zixun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.NewBean;
import com.udit.bankexam.bean.ZiXunBean;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.CustomFiveAngleImg;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.List;

/**
 * Created by zb on 2017/6/24.
 */

public class NewsAdapter extends BaseAdapter {

    private List<NewBean> mlist;

    private Context mContext;

    private ViewGroup.LayoutParams vpViewG;

    public NewsAdapter(List<NewBean> mlist, Context mContext) {
        this.mlist = mlist;
        this.mContext = mContext;
        this.vpViewG = new ViewGroup.LayoutParams(-1, Utils.dip2px(mContext, 120.0F));
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_news, null);
            holder = new Holder();
            ViewUtils.initHolderView(holder, convertView, R.id.class);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.layout_container.setLayoutParams(this.vpViewG);
        NewBean bean = mlist.get(position);
        holder.img_zixun.setImageResource(R.mipmap.default_news);
        if (!MyCheckUtils.isEmpty(bean.getName()))
            holder.text_zixun.setText(bean.getName());
        if (!MyCheckUtils.isEmpty(bean.getCreateDate()))
            holder.text_time.setText(bean.getCreateDate());
        if (!MyCheckUtils.isEmpty(bean.getScreen()))
            ImageLoader.getInstance().displayImage(IHTTP.IP_ + "/" + bean.getScreen(), holder.img_zixun);
        return convertView;
    }

    private class Holder {
        CustomFiveAngleImg img_zixun;

        RelativeLayout layout_container;

        TextView text_time;

        TextView text_zixun;
    }
}
