package com.udit.bankexam.ui.zhibo.adapter;

import java.util.List;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.ViewUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Adapter_ShouCan extends BaseAdapter {
    private Context mContext;

    private List<ZhiBoBean> mlist_zhibo;


    /**
     * <默认构造函数>
     */
    public Adapter_ShouCan(Context mContext, List<ZhiBoBean> mlist_zhibo) {
        super();
        this.mContext = mContext;
        this.mlist_zhibo = mlist_zhibo;
    }

    @Override
    public int getCount() {
        return mlist_zhibo.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mlist_zhibo.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {

        Holder holder = null;

        if (arg1 == null) {
            holder = new Holder();
            arg1 = LayoutInflater.from(mContext).inflate(R.layout.item_shoucang, null);
            ViewUtils.initHolderView(holder, arg1, R.id.class);
            arg1.setTag(holder);
        } else {
            holder = (Holder) arg1.getTag();
        }

        ZhiBoBean bean = mlist_zhibo.get(arg0);
        if (!MyCheckUtils.isEmpty(bean.getName())) {
            holder.textview_kecheng_name.setText(bean.getName());
        }
        if (!MyCheckUtils.isEmpty(bean.getBegDate())
                && !MyCheckUtils.isEmpty(bean.getEndDate())) {
            String begin = MyDateUtil.chgFmt(bean.getBegDate(), MyDateUtil.DATE_FORMAT_2, MyDateUtil.DATE_FORMAT_13);
            String end = MyDateUtil.chgFmt(bean.getEndDate(), MyDateUtil.DATE_FORMAT_2, MyDateUtil.DATE_FORMAT_13);
            holder.textview_kecheng_time.setText(begin + "-" + end);
        }
        if (!MyCheckUtils.isEmpty(bean.getLCount())) {
            holder.textview_kecheng_num.setText("(" + bean.getLCount() + ")");
        }

        if (!MyCheckUtils.isEmpty(bean.getLCount())) {
            TextView textView1 = holder.textview_kecheng_num;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(bean.getLCount());
            stringBuilder.append("(课时)");
            textView1.setText(stringBuilder.toString());
        }
        if (!MyCheckUtils.isEmpty(bean.getPurchCount())) {
            holder.rl_buy.setVisibility(View.VISIBLE);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(bean.getPurchCount());
            stringBuilder.append("人已购");
            holder.tv_buy_num.setText(stringBuilder.toString());
            return arg1;
        }
        holder.rl_buy.setVisibility(View.GONE);
        return arg1;
    }

    private class Holder {
        //课程名称
        TextView textview_kecheng_name,
        //课程时间
        textview_kecheng_time,
        //课时
        textview_kecheng_num;

        TextView tv_buy_num;

        RelativeLayout rl_buy;

    }

}
