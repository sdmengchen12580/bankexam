package com.udit.bankexam.ui.sreach.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamBean;
import com.udit.frame.common.texthtml.HtmlTextView;
import com.udit.frame.utils.ViewUtils;

import java.util.List;

/**
 * Created by zb on 2017/5/15.
 */

public class ExamSreachAdapter extends BaseAdapter {

    private List<ExamBean> mlist;

    private Context mContext;

    private ExamListener examListener;

    public ExamSreachAdapter(List<ExamBean> mlist, Context mContext,ExamListener examListener) {
        this.mlist = mlist;
        this.mContext = mContext;
        this.examListener = examListener;
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
        if(convertView ==null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_exam_sreach,null);
            holder = new Holder();
            ViewUtils.initHolderView(holder,convertView,R.id.class);
            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }
        final ExamBean bean = mlist.get(position);
        String qtype= bean.getQType();
        String title = bean.getTitle();
        String content = bean.getContent();
        holder.text_sreach_exam_title.setHtmlFromString("("+qtype+")   "+ title,false,false);
        holder.ll_item_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examListener.setExamStart(bean);
            }
        });
        holder.text_sreach_exam_laiyuan.setText("来源:"+content);

        return convertView;
    }

    public interface ExamListener
    {

        public void setExamStart(ExamBean bean);

    }

    private class Holder
    {
        LinearLayout ll_item_exam;

        HtmlTextView text_sreach_exam_title;

        TextView text_sreach_exam_laiyuan;

    }
}
