package com.udit.bankexam.ui.exam_robot.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.TypeBean;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.List;

/**
 * Created by zb on 2017/5/16.
 */

public class TypeAdapter extends BaseAdapter {
    private Context mContext;

    private List<TypeBean> mlist;

    private int selectedIndex;

    private ViewGroup.LayoutParams vpViewG;

    public TypeAdapter(Context mContext, List<TypeBean> mlist) {
        this.mContext = mContext;
        this.mlist = mlist;
        this.vpViewG = new RelativeLayout.LayoutParams(-1, Utils.dip2px(mContext, 45.0F));
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

    public void setSelectedIndex(int postion) {
        this.selectedIndex = postion;
        notifyDataSetChanged();

    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_type, null);
            ViewUtils.initHolderView(holder, convertView, R.id.class);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.ll_type.setLayoutParams(this.vpViewG);
        TypeBean bean = mlist.get(position);
        holder.textview_type.setText(bean.getName());
        if (position == selectedIndex) {
            holder.radio_type.setChecked(true);
        } else {
            holder.radio_type.setChecked(false);
        }
        return convertView;
    }

    public class Holder {
        public RelativeLayout ll_type;

        public RadioButton radio_type;

        public TextView textview_type;
    }
}
