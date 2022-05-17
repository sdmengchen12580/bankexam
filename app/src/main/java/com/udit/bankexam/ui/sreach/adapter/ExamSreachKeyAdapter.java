package com.udit.bankexam.ui.sreach.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.udit.bankexam.R;

import java.util.List;

/**
 * Created by zb on 2018-07-13.
 */

public class ExamSreachKeyAdapter extends BaseAdapter {

    private List<String> list;

    private Context mContext;

    public ExamSreachKeyAdapter(List<String> list,Context mContext)
    {
        this.list = list;
        this.mContext = mContext;
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

        Holder holder = null;
        if(convertView==null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_key,null);
            holder = new Holder();
            holder.text_key= (TextView) convertView.findViewById(R.id.text_key);
            convertView.setTag(holder);
        }
        else{
            holder = (Holder) convertView.getTag();
        }

        String key =  list.get(position);
        holder.text_key.setText(key);

        return convertView;
    }

    class Holder{

        TextView text_key;

    }
}
