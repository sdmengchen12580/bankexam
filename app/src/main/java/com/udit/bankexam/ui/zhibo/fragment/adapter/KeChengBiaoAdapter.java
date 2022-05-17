package com.udit.bankexam.ui.zhibo.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ZhiboKeChengBean;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.ViewUtils;

import java.util.List;

/**
 * Created by zb on 2017/5/3.
 */

public class KeChengBiaoAdapter extends BaseAdapter {

    private List<ZhiboKeChengBean> mlist;

    private Context mContext;

    public KeChengBiaoAdapter(List<ZhiboKeChengBean> mlist, Context mContext) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_kechengbiao, null);
            holder = new Holder();
            ViewUtils.initHolderView(holder, convertView, R.id.class);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        ZhiboKeChengBean bean = mlist.get(position);

        if (!MyCheckUtils.isEmpty(bean.getTecheName())) {
            holder.textview_zhibo_teacher.setText(bean.getTecheName() + "-");
        }
        if (!MyCheckUtils.isEmpty(bean.getIntro())) {
            holder.textview_zhibo_name.setText(bean.getIntro());
        }
        if (!MyCheckUtils.isEmpty(bean.getBegDate())
                && !MyCheckUtils.isEmpty(bean.getEndDate())) {
            //修改 时间格式
            String begin = MyDateUtil.chgFmt(bean.getBegDate(), MyDateUtil.DATE_FORMAT_11, MyDateUtil.DATE_FORMAT_11);
            String end = MyDateUtil.chgFmt(bean.getEndDate(), MyDateUtil.DATE_FORMAT_11, MyDateUtil.DATE_FORMAT_7);
            holder.textview_zhibo_time.setText(begin + "-" + end);
        }
        holder.img_kecheng_in.setVisibility(View.GONE);
        return convertView;
    }

    private class Holder {
        TextView textview_zhibo_teacher, textview_zhibo_name, textview_zhibo_time;

        ImageView img_kecheng_in;

    }
}
