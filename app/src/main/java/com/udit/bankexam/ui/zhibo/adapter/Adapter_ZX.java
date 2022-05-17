package com.udit.bankexam.ui.zhibo.adapter;

import java.util.List;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDataUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Adapter_ZX extends BaseAdapter {
    private Context mContext;

    private List<ZhiBoBean> mlist_zhibo;

    private ViewGroup.LayoutParams vpViewG;

    /**
     * <默认构造函数>
     */
    public Adapter_ZX(Context mContext, List<ZhiBoBean> mlist_zhibo) {
        super();
        this.mContext = mContext;
        this.mlist_zhibo = mlist_zhibo;
        this.vpViewG = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(mContext, 97.0F));
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
            arg1 = LayoutInflater.from(mContext).inflate(R.layout.item_zhibo, null);
            ViewUtils.initHolderView(holder, arg1, R.id.class);
            arg1.setTag(holder);
        } else {
            holder = (Holder) arg1.getTag();
        }
        holder.layout_container.setLayoutParams(this.vpViewG);
        ZhiBoBean bean = mlist_zhibo.get(arg0);
        if (!MyCheckUtils.isEmpty(bean.getName())) {
            holder.textview_zhibo_name.setText(bean.getName());
        }
        if (!MyCheckUtils.isEmpty(bean.getBegDate())
                && !MyCheckUtils.isEmpty(bean.getEndDate())) {
            String begin = MyDateUtil.chgFmt(bean.getBegDate(), MyDateUtil.DATE_FORMAT_2, MyDateUtil.DATE_FORMAT_13);
            String end = MyDateUtil.chgFmt(bean.getEndDate(), MyDateUtil.DATE_FORMAT_2, MyDateUtil.DATE_FORMAT_13);
            holder.textview_zhibo_time.setText(begin + "至" + end);
        }
        if (!MyCheckUtils.isEmpty(bean.getLCount())) {
            TextView textView1 = holder.textview_zhibo_keshi;
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append(bean.getLCount());
            stringBuilder1.append("课时");
            textView1.setText(stringBuilder1.toString());
        }
        String str = Utils.doubleOutPut(bean.getPrice(), 2);
        if (str.equals("0.00") || str.equals("0") || bean.getIsGet().equals("是")) {
            if (bean.getIsGet().equals("是")) {
                if (str.equals("0.00") || str.equals("0")) {
                    holder.textview_zhibo_price.setVisibility(View.GONE);
                } else {
                    holder.textview_zhibo_price.setText("已订购");
                }
                holder.textview_zhibo_price_bz.setVisibility(View.GONE);
            } else if ((str.equals("0.00") || str.equals("0")) && bean.getIsGet().equals("否")) {
                holder.ll_price.setVisibility(View.VISIBLE);
                holder.textview_zhibo_price.setText(str);
                holder.textview_zhibo_price.setVisibility(View.VISIBLE);
                holder.textview_zhibo_price_bz.setVisibility(View.VISIBLE);
            } else {
                holder.ll_price.setVisibility(View.INVISIBLE);
                holder.textview_zhibo_price.setVisibility(View.INVISIBLE);
            }
        } else {
            holder.ll_price.setVisibility(View.VISIBLE);
            holder.textview_zhibo_price.setVisibility(View.VISIBLE);
            holder.textview_zhibo_price_bz.setVisibility(View.VISIBLE);
            holder.textview_zhibo_price.setText(str);
        }
        if (!MyCheckUtils.isEmpty(bean.getPurchCount()))
            holder.textview_zhibo_goumairenshu.setText(bean.getPurchCount());
        int paramInt = MyDateUtil.getDays(MyDateUtil.getDate("yyyy-MM-dd"), bean.getEndDate(), "yyyy-MM-dd");
        if (paramInt < 0) {
            holder.tv_des.setText("已停售");
            holder.tv_des.setBackground(this.mContext.getResources().getDrawable(R.drawable.shape_12_5_corner_dcdcdc_bg));
            holder.textview_zhibo_.setText("已停售");
            holder.textview_zhibo_tinshou.setVisibility(View.GONE);
            return arg1;
        }
        holder.tv_des.setText("进入详情");
        holder.tv_des.setBackground(this.mContext.getResources().getDrawable(R.drawable.shape_12_stroke_558cf4_bg));
        holder.textview_zhibo_.setText("距停售时间还有");
        TextView textView = holder.textview_zhibo_tinshou;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(paramInt);
        stringBuilder.append("天");
        textView.setText(stringBuilder.toString());
        holder.textview_zhibo_tinshou.setVisibility(View.VISIBLE);
        return arg1;
    }

    private class Holder {
        RelativeLayout layout_container;

        LinearLayout ll_price;

        TextView textview_zhibo_;

        TextView textview_zhibo_goumairenshu;

        TextView textview_zhibo_keshi;

        TextView textview_zhibo_name;

        TextView textview_zhibo_price;

        TextView textview_zhibo_price_bz;

        TextView textview_zhibo_teacher;

        TextView textview_zhibo_time;

        TextView textview_zhibo_tinshou;

        TextView tv_des;
    }

}
