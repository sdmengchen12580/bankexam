package com.udit.bankexam.ui.zixun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ZiXunBean;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.List;

/**
 * Created by zb on 2017/6/24.
 */

public class ZiXunAdapter extends BaseAdapter {

    private List<ZiXunBean> mlist;

    private Context mContext;

    public ZiXunAdapter(List<ZiXunBean> mlist, Context mContext) {
        this.mlist = mlist;
        this.mContext = mContext;
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
        if(convertView==null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_zixun,null);
            holder = new Holder();
            ViewUtils.initHolderView(holder,convertView,R.id.class);
            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }
        ZiXunBean bean = mlist.get(position);
        if(!MyCheckUtils.isEmpty(bean.getTitle()))
            holder.text_zixun.setText(bean.getTitle());
        if(!MyCheckUtils.isEmpty(bean.getPubdate()))
            holder.text_zixun_time.setText(bean.getPubdate());
        return convertView;
    }

    private class Holder
    {
        TextView text_zixun,text_zixun_time;


    }
}
