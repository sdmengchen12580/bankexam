package com.udit.bankexam.ui.home.fragment.adapter;

import java.util.List;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.VideoTypeTwoBean;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VideoAdapter extends BaseAdapter {
    private List<VideoTypeTwoBean> mlist;

    private Context mContext;


    /**
     * <默认构造函数>
     */
    public VideoAdapter(List<VideoTypeTwoBean> mlist, Context mContext) {
        super();
        this.mlist = mlist;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mlist.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        Holder holder = null;
        if (arg1 == null) {
            holder = new Holder();
            arg1 = LayoutInflater.from(mContext).inflate(R.layout.item_video, null);
            ViewUtils.initHolderView(holder, arg1, R.id.class);
            arg1.setTag(holder);
        } else
            holder = (Holder) arg1.getTag();
        VideoTypeTwoBean type = mlist.get(arg0);
        if (!MyCheckUtils.isEmpty(type.getName())) {
            holder.text_typeone.setText(type.getName());
        }

        String price_s = Utils.doubleOutPut(type.getPrice(), 2);
        if (price_s.equals("0")) {
            holder.ll_price.setVisibility(View.INVISIBLE);
        } else {
            if (type.getIsGet().equals("是")) {
                holder.textview_price_type.setVisibility(View.INVISIBLE);
                holder.textview_total_price.setText("已购买");
            } else
                holder.textview_total_price.setText(price_s);
            holder.ll_price.setVisibility(View.VISIBLE);
        }
        return arg1;
    }

    private class Holder {
        LinearLayout ll_price;

        TextView text_typeone;

        TextView textview_price_type;

        TextView textview_total_price;

    }

}
