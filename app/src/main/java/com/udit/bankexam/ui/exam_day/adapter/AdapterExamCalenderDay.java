package com.udit.bankexam.ui.exam_day.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class AdapterExamCalenderDay extends BaseAdapter {

    private List<ExamInfoBean> list;

    private Context mContext;

    public AdapterExamCalenderDay(List<ExamInfoBean> list, Context mContext) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_examinfo_2, null);
            holder = new Holder();
            ViewUtils.initHolderView(holder, convertView, R.id.class);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        ExamInfoBean bean = list.get(position);
        if (!MyCheckUtils.isEmpty(bean.getName())) {
            holder.textview_examinfo_name.setText(bean.getName());
        }

        if (!MyCheckUtils.isEmpty(bean.getBegDate())
                && !MyCheckUtils.isEmpty(bean.getEndDate())) {
            String begin = MyDateUtil.chgFmt(bean.getBegDate(), MyDateUtil.DATE_FORMAT_2, MyDateUtil.DATE_FORMAT_13);
            String end = MyDateUtil.chgFmt(bean.getEndDate(), MyDateUtil.DATE_FORMAT_2, MyDateUtil.DATE_FORMAT_13);
            holder.textview_examinfo_time.setText(begin + "-" + end);
        }

        String str = Utils.doubleOutPut(bean.getPrice(), 2);
        if (str.equals("0.00") || str.equals("0") || bean.getIsGet().equals("是")) {
            if (bean.getIsGet().equals("是")) {
                if (str.equals("0.00") || str.equals("0")) {
                    holder.textview_examinfo_price.setVisibility(View.GONE);
                    holder.textview_examinfo_price_bz.setVisibility(View.GONE);
                    return convertView;
                }
                holder.textview_examinfo_price.setText("已订购");
                holder.textview_examinfo_price_bz.setVisibility(View.GONE);
                return convertView;
            }
            holder.textview_examinfo_price.setVisibility(View.INVISIBLE);
            return convertView;
        }
        holder.textview_examinfo_price.setVisibility(View.VISIBLE);
        holder.textview_examinfo_price_bz.setVisibility(View.VISIBLE);
        holder.textview_examinfo_price.setText(str);
        return convertView;
    }

    private class Holder {
        TextView textview_examinfo_name;

        TextView textview_examinfo_time;

        TextView textview_examinfo_price, textview_examinfo_price_bz;

        LinearLayout ll_examinfo_price;

        TextView textview_examinfo_goumairenshu;

        TextView textview_examinfo_tinshou;

        TextView go_lianxi;
    }
}
