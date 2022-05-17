package com.udit.bankexam.ui.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.VideoBean;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.List;

/**
 * Created by zb on 2017/4/26.
 */

public class VideoAdapter extends BaseAdapter {
    private List<VideoBean> mlist;

    private Context mContext;

    public VideoAdapter(List<VideoBean> mlist, Context mContext) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_videolist, null);
            holder = new Holder();
            ViewUtils.initHolderView(holder, convertView, R.id.class);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        VideoBean bean = mlist.get(position);
        if (!MyCheckUtils.isEmpty(bean.getName())) {
            holder.textview_video_name.setText(bean.getName());
        }
        String exam_num = String.format(mContext.getResources().getString(R.string.str_video_exam_num), "0");
        holder.textview_video_exam_num.setText(exam_num);

        String exam_total = String.format(mContext.getResources().getString(R.string.str_video_exam_total), "0", "0");
        holder.textview_video_exam_total.setText(exam_total);


        return convertView;
    }

    private class Holder {
        //视频名称
        TextView textview_video_name,
        //题目数
        textview_video_exam_num,
        //已做题目/总体目
        textview_video_exam_total,
        //完成率
        textview_video_exam_wcl,
        //标识
        textview_bs;

    }
}
