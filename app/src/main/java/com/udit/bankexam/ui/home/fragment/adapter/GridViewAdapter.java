package com.udit.bankexam.ui.home.fragment.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.ModuleBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.ui.MyWebUriActivity;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseApplication;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.ViewUtils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class GridViewAdapter extends BaseAdapter {
    private List<ModuleBean> mlist;
    private Context mContext;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    public GridViewAdapter(List<ModuleBean> mlist, Context mContext) {
        super();
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

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        holder = new Holder();
        convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_gridview_item_two, null);
        ViewUtils.initHolderView(holder, convertView, R.id.class);
        ModuleBean moduleBean = mlist.get(position);
        String str = moduleBean.getTitle();
        holder.gridview_home_text.setText(str);
        int paramInt = 0;
        if (str.equals("招聘信息")) {
            paramInt = R.mipmap.img_zpxx;
        } else if (str.equals("最新校招")) {
            paramInt = R.mipmap.img_zxxz;
        } else if (str.equals("每日一练")) {
            paramInt = R.mipmap.img_mryl;
        } else if (str.equals("历年真题")) {
            paramInt = R.mipmap.img_lnzt;
        } else if (str.equals("模考大赛")) {
            paramInt = R.mipmap.img_mkds;
        } else if (str.equals("图书资料")) {
            paramInt = R.mipmap.img_tszl;
        } else if (str.equals("报考指南")) {
            paramInt = R.mipmap.img_bkzn;
        } else {
            paramInt = R.mipmap.img_ques_djmj;
        }
        holder.gridview_home_img.setImageResource(paramInt);
        return convertView;
    }

    class Holder {
        ImageView gridview_home_img;

        TextView gridview_home_text;
    }

}
