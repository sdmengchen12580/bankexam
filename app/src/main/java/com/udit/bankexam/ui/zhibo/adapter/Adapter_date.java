package com.udit.bankexam.ui.zhibo.adapter;

import java.util.List;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.bankexam.bean.ZhiboKeChengBean;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.ViewUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter_date extends BaseAdapter
{
    private List<ZhiboKeChengBean> mlist;
    
    private Context mContext;
    
    
    
    /** 
     * <默认构造函数>
     */
    public Adapter_date(List<ZhiboKeChengBean> mlist, Context mContext)
    {
        super();
        this.mlist = mlist;
        this.mContext = mContext;
    }

    @Override
    public int getCount()
    {
        return mlist.size();
    }

    @Override
    public Object getItem(int arg0)
    {
        return mlist.get(arg0);
    }

    @Override
    public long getItemId(int arg0)
    {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2)
    {
        Holder holder = null;
        if(arg1==null)
        {
            holder = new Holder();
            arg1 = LayoutInflater.from(mContext).inflate(R.layout.item_date, null);
            ViewUtils.initHolderView(holder,arg1,R.id.class);
            arg1.setTag(holder);
        }
        else
        {
            holder = (Holder) arg1.getTag();
        }
        ZhiboKeChengBean bean = mlist.get(arg0);

        if(!MyCheckUtils.isEmpty(bean.getTecheName()))
        {
            holder.textview_date_teacher_name.setText(bean.getTecheName());
        }
        if(!MyCheckUtils.isEmpty(bean.getIntro()))
        {
            holder.textview_date_kecheng_name.setText(bean.getIntro());
        }
        if (!MyCheckUtils.isEmpty(bean.getBegDate())
                && !MyCheckUtils.isEmpty(bean.getEndDate())) {
            String begin = MyDateUtil.chgFmt(bean.getBegDate(), MyDateUtil.DATE_FORMAT_11, MyDateUtil.DATE_FORMAT_11);
            String end = MyDateUtil.chgFmt(bean.getEndDate(), MyDateUtil.DATE_FORMAT_11, MyDateUtil.DATE_FORMAT_7);
            holder.textview_date_kecheng_time.setText(begin + "-" + end);
        }

        return arg1;
    }

    private class Holder
    {
        TextView textview_date_teacher_name,textview_date_kecheng_name,textview_date_kecheng_time;

    }

}
