package com.udit.bankexam.ui.exam_data_history.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.HisPractBean;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.List;

/**
 * Created by zb on 2017/6/5.
 */

public class ExamDataHistoryAdapter extends BaseAdapter {

    private List<HisPractBean> mlist;
    private ViewGroup.LayoutParams vpViewG;
    private Context mContext;

    public ExamDataHistoryAdapter(List<HisPractBean> mlist, Context mContext) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_data_history, null);
            holder = new Holder();
            ViewUtils.initHolderView(holder, convertView, R.id.class);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.layout.setLayoutParams(this.vpViewG);
        HisPractBean bean = mlist.get(position);
        if (!MyCheckUtils.isEmpty(bean.getKeyWord()))
            holder.text_history_name.setText(bean.getKeyWord());

        if (!MyCheckUtils.isEmpty(bean.getPDate()))
            holder.text_history_time.setText(bean.getPDate());
        return convertView;
    }

    private class Holder {
        RelativeLayout layout;

        TextView text_history_name;

        TextView text_history_time;

    }
}
