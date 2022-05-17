package com.udit.bankexam.ui.exam_report_data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.PurchBean;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.List;

/**
 * Created by zb on 2017/5/19.
 */

public class SJAdapter extends BaseAdapter {

    private List<PurchBean> mlist;
    private Context mContext;
    private ViewGroup.LayoutParams vpViewG;

    public SJAdapter(List<PurchBean> mlist, Context mContext) {
        this.mlist = mlist;
        this.mContext = mContext;
        this.vpViewG = new RelativeLayout.LayoutParams(-1, Utils.dip2px(mContext, 75.0F));
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sj, null);
            holder = new Holder();
            ViewUtils.initHolderView(holder, convertView, R.id.class);
            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.layout.setLayoutParams(this.vpViewG);
        PurchBean bean = mlist.get(position);
        if (!MyCheckUtils.isEmpty(bean.getAbstract())) {
            holder.textview_sj_name.setText(bean.getAbstract());
        }
        if (!MyCheckUtils.isEmpty(bean.getFeeDate()))
            holder.tv_time.setText(bean.getFeeDate());
        return convertView;
    }

    private class Holder {
        RelativeLayout layout;

        TextView textview_sj_name;

        TextView tv_time;
    }
}
