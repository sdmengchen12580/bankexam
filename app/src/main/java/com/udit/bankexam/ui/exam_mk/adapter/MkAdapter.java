package com.udit.bankexam.ui.exam_mk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.MKBean;
import com.udit.bankexam.view.CustomFiveAngleImg;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.List;

/**
 * Created by zb on 2017/6/5.
 */

public class MkAdapter extends BaseAdapter {
    private List<MKBean> mlist;
    private ViewGroup.LayoutParams vpViewG;
    private Context mContext;

    public MkAdapter(List<MKBean> mlist, Context mContext) {
        this.mlist = mlist;
        this.mContext = mContext;
        this.vpViewG = new RelativeLayout.LayoutParams(-1, Utils.dip2px(mContext, 120.0F));
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
        MkHolder holder = null;
        if (holder == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mk, null);
            holder = new MkHolder();
            ViewUtils.initHolderView(holder, convertView, R.id.class);
            convertView.setTag(holder);
        } else {
            holder = (MkHolder) convertView.getTag();
        }
        holder.layout_container.setLayoutParams(this.vpViewG);
        MKBean bean = mlist.get(position);
        if (!MyCheckUtils.isEmpty(bean.getScreen())) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("http://yk.yinhangzhaopin.com/");
            stringBuilder.append(bean.getScreen());
            ImageLoader.getInstance().displayImage(stringBuilder.toString(), holder.img);
        } else {
            holder.img.setImageResource(R.mipmap.default_news);
        }
        if (!MyCheckUtils.isEmpty(bean.getName()))
            holder.text_mk_name.setText(bean.getName());

        if (!MyCheckUtils.isEmpty(bean.getiCount())) {
            holder.text_mk_baoming.setText(bean.getiCount() + "");
        } else {
            holder.text_mk_baoming.setText("0");
        }

        if (!MyCheckUtils.isEmpty(bean.getBegDate())
                && !MyCheckUtils.isEmpty(bean.getEndDate())) {
            String begin = MyDateUtil.chgFmt(bean.getBegDate(), MyDateUtil.DATE_FORMAT_2, MyDateUtil.DATE_FORMAT_13);
            String end = MyDateUtil.chgFmt(bean.getEndDate(), MyDateUtil.DATE_FORMAT_2, MyDateUtil.DATE_FORMAT_13);
            holder.text_mk_time.setText(begin + "-" + end);
        }
       /* if(!MyCheckUtils.isEmpty(bean.getBegDate())
                && !MyCheckUtils.isEmpty(bean.getEndDate()))
        {
            holder.text_mk_time.setText(bean.getBegDate()+" - "+bean.getEndDate());
        }*/
        return convertView;
    }

    private class MkHolder {
        CustomFiveAngleImg img;

        RelativeLayout layout_container;

        TextView text_mk_baoming;

        TextView text_mk_name;

        TextView text_mk_time;
    }
}
