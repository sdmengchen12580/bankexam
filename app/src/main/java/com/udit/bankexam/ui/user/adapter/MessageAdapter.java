package com.udit.bankexam.ui.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.MessageBean;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.List;

/**
 * Created by zb on 2017/6/7.
 */

public class MessageAdapter extends BaseAdapter {

    private List<MessageBean> mlist;
    private Context mContext;
    private ViewGroup.LayoutParams vpViewG;


    public MessageAdapter(List<MessageBean> mlist, Context mContext) {
        this.mlist = mlist;
        this.mContext = mContext;
        this.vpViewG = new RelativeLayout.LayoutParams(-1, Utils.dip2px(mContext, 141.0F));
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

        if(convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_message,null);
            holder = new Holder();
            ViewUtils.initHolderView(holder,convertView,R.id.class);
            convertView.setTag(holder);

        }
        else
        {
            holder = (Holder) convertView.getTag();
        }
        holder.container.setLayoutParams(this.vpViewG);
        MessageBean bean = mlist.get(position);
        if(!MyCheckUtils.isEmpty(bean.getName()))
            holder.text_message_title.setText(bean.getName());

        if(!MyCheckUtils.isEmpty(bean.getMsg()))
            holder.text_message_content.setText(bean.getMsg());

        if(!MyCheckUtils.isEmpty(bean.getBegTime()))
            holder.text_message_time.setText(bean.getBegTime());

        return convertView;
    }

    private class Holder
    {
        RelativeLayout container;

        TextView text_message_content;

        TextView text_message_time;

        TextView text_message_title;
    }
}
