package com.udit.bankexam.ui.video.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.VideoFile;
import com.udit.bankexam.bean.VideoType;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.ui.home.fragment.adapter.ExamNodeHolder;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class TypeTwoAdapter_new extends BaseAdapter {
    private final String TAG = this.getClass().getSimpleName();

    private List<VideoType> mlist_bean;

    private List<VideoType> templist = new LinkedList<>();

    private Context mContext;

    private ExamGroupOnClick examGroupClick;


    public TypeTwoAdapter_new(List<VideoType> mlist_bean, Context mContext) {
        this.mlist_bean = mlist_bean;
        this.mContext = mContext;
        removeUnExpandItem(mlist_bean);
    }

    private void removeUnExpandItem(List<VideoType> list) {
        for (VideoType node : list) {
            if (!node.isfirst()) {
                node.setExpand(false);
                mlist_bean.remove(node);
            }
            removeUnExpandItem(node.getList_file());
        }
    }


    private void sortItem(List<VideoType> list) {

        Collections.sort(list, new Comparator<VideoType>() {

            @Override
            public int compare(VideoType o1, VideoType o2) {
                int order1 = Integer.parseInt(o1.getOrdid());
                int order2 = Integer.parseInt(o2.getOrdid());


                return order1 - order2;
            }
        });


    }


    @Override
    public int getCount() {
        return mlist_bean.size();
    }

    @Override
    public VideoType getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_video_node_new, null);
            holder = new VideoNodeHolder();
            ViewUtils.initHolderView(holder, convertView, R.id.class);
            convertView.setTag(holder);
        } else {
            holder = (VideoNodeHolder) convertView.getTag();
        }
        final VideoType node = getItem(position);
        if (node.getCid() == null || node.getCid().isEmpty()) {//第一个
            holder.view_top_line.setVisibility(View.INVISIBLE);
            if (node.getList_file() != null && node.getList_file().size() > 0) {
                if (node.isExpand()) {//展开状态
                    holder.img_node.setImageResource(Constant.ExamNode.node_one[0]);
                    holder.view_bottom_line.setVisibility(View.VISIBLE);
                } else {
                    holder.img_node.setImageResource(Constant.ExamNode.node_one[1]);
                    holder.view_bottom_line.setVisibility(View.INVISIBLE);
                }
            } else {
                holder.img_node.setImageResource(Constant.ExamNode.node_no);
                holder.view_bottom_line.setVisibility(View.INVISIBLE);
            }
            holder.view_top_line_.setVisibility(View.VISIBLE);
            if (position + 1 < mlist_bean.size()) {
                if (mlist_bean.get(position + 1).getCid().equals(node.getId())) {
                    holder.view_bottom_line_.setVisibility(View.GONE);
                } else {
                    holder.view_bottom_line_.setVisibility(View.VISIBLE);
                }
            } else {
                holder.view_bottom_line_.setVisibility(View.GONE);
            }

        } else {
            holder.view_top_line_.setVisibility(View.GONE);
            holder.view_bottom_line_.setVisibility(View.GONE);
            if (node.getList_file() != null && node.getList_file().size() > 0) {
                if (node.isExpand()) {
                    holder.img_node.setImageResource(Constant.ExamNode.node_two[0]);

                } else {
                    holder.img_node.setImageResource(Constant.ExamNode.node_two[1]);
                }
            } else {//没有子节点
                holder.img_node.setImageResource(Constant.ExamNode.node_no);
            }
            holder.view_top_line.setVisibility(View.VISIBLE);
            if (node.isLast()) {
                holder.view_bottom_line.setVisibility(View.INVISIBLE);
            } else
                holder.view_bottom_line.setVisibility(View.VISIBLE);
        }

        holder.frame_video_node.setTag(R.id.node, node);
        holder.frame_video_node.setTag(R.id.node_postion, position);
        holder.frame_video_node.setOnClickListener(onClickListener);

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

        if (!MyCheckUtils.isEmpty(node.getPrice())) {
            String price_s = Utils.doubleOutPut(node.getPrice(), 2);
            if (price_s.equals("0")) {
                holder.ll_price.setVisibility(View.INVISIBLE);
            } else {
                if (node.getIsget().equals("是")) {
                    holder.textview_price_type.setVisibility(View.INVISIBLE);
                    holder.textview_total_price.setText("已购买");
                } else
                    holder.textview_total_price.setText(price_s);
                holder.ll_price.setVisibility(View.VISIBLE);
            }
        } else {
            holder.ll_price.setVisibility(View.INVISIBLE);
        }


        return convertView;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            VideoType bean = (VideoType) v.getTag(R.id.node);
            int postion = (int) v.getTag(R.id.node_postion);
            MyLogUtils.e(TAG, "name:" + bean.getName());

            if (bean.getList_file() != null && bean.getList_file().size() > 0) {
                if (bean.isExpand()) {//已经展开，则执行关闭
                    bean.setExpand(false);
                    removeUnExpandItem(bean.getList_file());
                } else {//已经关闭，则执行打开
                    bean.setExpand(true);
                    List<VideoType> list_add = bean.getList_file();
                    sortItem(list_add);
                    bean.setLast(false);
                    for (int i = 0; i < list_add.size(); i++) {
                        VideoType bean_child = list_add.get(i);
                        if (i == list_add.size() - 1) {
                            int last_last = postion + 1 + i + 1;
                            if (last_last < mlist_bean.size() && !bean.getCid().equals("0")) {
                                VideoType bean_last_last = mlist_bean.get(last_last);
                                if (bean_last_last.getCid().equals(bean.getId())) {
                                    bean_child.setLast(false);
                                } else
                                    bean_child.setLast(true);
                            } else {
                                bean_child.setLast(true);
                            }
                        } else {
                            bean_child.setLast(false);
                        }
                        mlist_bean.add(postion + 1 + i, list_add.get(i));

                    }
                }

                notifyDataSetChanged();
            } else {//没有子类，不关心点击事件
                return;
            }

        }

    };


    public void setExamGroupClick(ExamGroupOnClick examGroupClick) {
        this.examGroupClick = examGroupClick;
    }

    public interface ExamGroupOnClick {
        public void ExamGo(VideoType bean);
    }
}
