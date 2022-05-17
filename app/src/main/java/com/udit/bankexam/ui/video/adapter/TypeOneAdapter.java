package com.udit.bankexam.ui.video.adapter;

import android.content.Context;
import android.content.SyncStatusObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.List;

/**
 * Created by zb on 2017/4/25.
 */

public class TypeOneAdapter extends BaseAdapter {

    List<String> mlist;

    private Context mContext;

    public TypeOneAdapter(List<String> mlist, Context mContext) {
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
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_typeone, null);
            ViewUtils.initHolderView(holder, convertView, R.id.class);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        String title = mlist.get(position);
        if (!MyCheckUtils.isEmpty(title))
            holder.text_typeone.setText(title);

        boolean flag = holder.text_typeone.getLinksClickable();
        MyLogUtils.e("FLAG:", "flag:" + flag);

        return convertView;
    }

    private class Holder {
        TextView text_typeone;

        ImageView img_typeone;
    }
}
