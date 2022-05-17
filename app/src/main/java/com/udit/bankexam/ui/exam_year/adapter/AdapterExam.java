package com.udit.bankexam.ui.exam_year.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

public class AdapterExam extends BaseAdapter {

    private List<ExamInfoBean> list;

    private Context mContext;

    private ViewGroup.LayoutParams vpViewG;

    public AdapterExam(List<ExamInfoBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        this.vpViewG = new RelativeLayout.LayoutParams(-1, Utils.dip2px(mContext, 95.0F));
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_examinfo, null);
            holder = new Holder();
            ViewUtils.initHolderView(holder, convertView, R.id.class);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.layout_container.setLayoutParams(this.vpViewG);
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
        String price = Utils.doubleOutPut(bean.getPrice(), 2);
        if (price.equals("0.00") || price.equals("0") || bean.getIsGet().equals("是")) {
            if (bean.getIsGet().equals("是")) {
                if (price.equals("0.00") || price.equals("0")) {
                    holder.textview_examinfo_price.setVisibility(View.GONE);
                } else {
                    holder.textview_examinfo_price.setText("已订购");
                }
                holder.textview_examinfo_price_bz.setVisibility(View.GONE);
            } else {
                holder.ll_examinfo_price.setVisibility(View.INVISIBLE);
                holder.textview_examinfo_price.setVisibility(View.INVISIBLE);
            }
        } else {
            holder.ll_examinfo_price.setVisibility(View.VISIBLE);
            holder.textview_examinfo_price.setVisibility(View.VISIBLE);
            holder.textview_examinfo_price_bz.setVisibility(View.VISIBLE);
            holder.textview_examinfo_price.setText(price);
        }
      /*
        String price_s = Utils.doubleOutPut(bean.getPrice(),2);
        if(!price_s.equals("0"))
        {
          //  String price =  Utils.doubleOutPut(bean.getPrice(),2);
            holder.textview_examinfo_price.setText(price_s);
            holder.ll_examinfo_price.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.ll_examinfo_price.setVisibility(View.GONE);
        }*/

        if (!MyCheckUtils.isEmpty(bean.getiCount())) {
            holder.textview_examinfo_goumairenshu.setText(bean.getiCount());
        } else
            holder.textview_examinfo_goumairenshu.setText("0");

        holder.img_edit.setVisibility(View.VISIBLE);

        int day = MyDateUtil.getDays(MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_2), bean.getEndDate(), MyDateUtil.DATE_FORMAT_2);
        holder.textview_examinfo_tinshou.setText(day + "");
        return convertView;
    }

    private class Holder {
        ImageView img_edit;

        RelativeLayout layout_container;

        LinearLayout ll_examinfo_price;

        TextView textview_examinfo_goumairenshu;

        TextView textview_examinfo_name;

        TextView textview_examinfo_price;

        TextView textview_examinfo_price_bz;

        TextView textview_examinfo_time;

        TextView textview_examinfo_tinshou;
    }
}
