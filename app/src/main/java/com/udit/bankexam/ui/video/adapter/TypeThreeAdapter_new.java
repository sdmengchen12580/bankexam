package com.udit.bankexam.ui.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.VideoFile;
import com.udit.bankexam.bean.VideoType;
import com.udit.bankexam.constant.Constant;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.LinkedList;
import java.util.List;


public class TypeThreeAdapter_new extends BaseAdapter {
    private final String TAG = this.getClass().getSimpleName();

    private List<VideoFile> mlist_bean;

    private List<VideoFile> templist = new LinkedList<>();

    private Context mContext;

    private ExamGroupOnClick examGroupClick;


    public TypeThreeAdapter_new(List<VideoFile> mlist_bean, Context mContext) {
        this.mlist_bean = mlist_bean;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mlist_bean.size();
    }

    @Override
    public VideoFile getItem(int position) {
        return mlist_bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VideoNodeHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_video_node_two_new, null);
            holder = new VideoNodeHolder();
            ViewUtils.initHolderView(holder, convertView, R.id.class);
            convertView.setTag(holder);
        } else {
            holder = (VideoNodeHolder) convertView.getTag();
        }
        final VideoFile node = getItem(position);
        if (node.isfirst()) {//第一个
            holder.view_top_line.setVisibility(View.VISIBLE);

            holder.img_node.setImageResource(Constant.ExamNode.node_no);

            holder.view_bottom_line_.setVisibility(View.VISIBLE);

        } else {
            holder.view_top_line.setVisibility(View.VISIBLE);
            holder.img_node.setImageResource(Constant.ExamNode.node_no);
            holder.view_bottom_line_.setVisibility(View.VISIBLE);


        }

        holder.frame_video_node.setTag(R.id.node, node);
        holder.frame_video_node.setTag(R.id.node_postion, position);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (examGroupClick != null) {
                    examGroupClick.ExamGo(node);
                }
            }
        });

        //数据层
        if (!MyCheckUtils.isEmpty(node.getName()))
            holder.text_node_name.setText(node.getName());

        return convertView;
    }


    public void setExamGroupClick(ExamGroupOnClick examGroupClick) {
        this.examGroupClick = examGroupClick;
    }

    public interface ExamGroupOnClick {
        public void ExamGo(VideoFile bean);
    }
}
