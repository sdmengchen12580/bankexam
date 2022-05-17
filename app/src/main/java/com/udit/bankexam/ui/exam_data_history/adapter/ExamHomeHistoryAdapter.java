package com.udit.bankexam.ui.exam_data_history.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.ui.exam_err.holder.ExamHomeErrNodeHolder;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class ExamHomeHistoryAdapter extends BaseAdapter {
    private final String TAG = this.getClass().getSimpleName();

    private List<ExamNodeBean> mlist_bean;

    private List<ExamNodeBean> templist = new LinkedList<>();

    private Context mContext;

    private ExamGroupOnClick examGroupClick;


    public ExamHomeHistoryAdapter(List<ExamNodeBean> mlist_bean, Context mContext) {
        this.mlist_bean = mlist_bean;
        this.mContext = mContext;

        removeUnExpandItem(mlist_bean);
    }

    /**
     * 移除没有展开的item。注意：父亲没有展开，儿子也不给他显示
     *
     * @param list
     */
    private void removeUnExpandItem(List<ExamNodeBean> list) {
        // templist.clear();
        for (ExamNodeBean node : list) {

            if (!node.getSuperID().equals("0")) {
                node.setExpand(false);
                mlist_bean.remove(node);

            }
            removeUnExpandItem(node.getList_outlineinfo());

        }

    }


    private void sortItem(List<ExamNodeBean> list) {

        Collections.sort(list, new Comparator<ExamNodeBean>() {

            @Override
            public int compare(ExamNodeBean o1, ExamNodeBean o2) {
                int order1 = Integer.parseInt(o1.getOrdID());
                int order2 = Integer.parseInt(o2.getOrdID());


                return order1 - order2;
            }
        });


    }


    @Override
    public int getCount() {
        return mlist_bean.size();
    }

    @Override
    public ExamNodeBean getItem(int position) {
        return mlist_bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ExamHomeErrNodeHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_exam_node_home_history, null);
            holder = new ExamHomeErrNodeHolder();
            ViewUtils.initHolderView(holder, convertView, R.id.class);
            convertView.setTag(holder);
        } else {
            holder = (ExamHomeErrNodeHolder) convertView.getTag();
        }
        final ExamNodeBean node = getItem(position);
        if (node.getSuperID().equals("0")) {
            holder.view_top_line.setVisibility(View.INVISIBLE);
            if (node.getList_outlineinfo() != null && node.getList_outlineinfo().size() > 0) {
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
                if (mlist_bean.get(position + 1).getSuperID().equals(node.getID())) {
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
            if (node.getList_outlineinfo() != null && node.getList_outlineinfo().size() > 0) {
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
                holder.imageView.setVisibility(View.GONE);
                holder.view_bottom_line.setVisibility(View.INVISIBLE);
            } else {
                holder.imageView.setVisibility(View.VISIBLE);
            }
            holder.view_bottom_line.setVisibility(View.VISIBLE);
        }


        holder.frame_exam_node.setTag(R.id.node, node);
        holder.frame_exam_node.setTag(R.id.node_postion, position);
        holder.frame_exam_node.setOnClickListener(onClickListener);

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

       /* if(node.getErrCount()>0)
        {
            holder.text_exam_jx.setText("练习错题（"+node.getErrCount()+"）道");
            holder.text_exam_jx.setVisibility(View.VISIBLE);
            holder.text_exam_jx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    examGroupClick.ExamErrGo(node);
                }
            });
           // holder.text_lxct.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.text_exam_jx.setVisibility(View.INVISIBLE);
           // holder.text_lxct.setVisibility(View.INVISIBLE);
        }*/
        holder.imageView.setVisibility((node.isFirstDate())?View.VISIBLE:View.GONE);
        //fixme 需要测试
        return convertView;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ExamNodeBean bean = (ExamNodeBean) v.getTag(R.id.node);
            int postion = (int) v.getTag(R.id.node_postion);
            MyLogUtils.e(TAG, "name:" + bean.getName());
            if (bean.getList_outlineinfo() != null && bean.getList_outlineinfo().size() > 0) {
                if (bean.isExpand()) {//已经展开，则执行关闭
                    bean.setExpand(false);
                    removeUnExpandItem(bean.getList_outlineinfo());
                } else {//已经关闭，则执行打开
                    bean.setExpand(true);
                    List<ExamNodeBean> list_add = bean.getList_outlineinfo();
                    sortItem(list_add);
                    bean.setLast(false);
                    for (int i = 0; i < list_add.size(); i++) {
                        ExamNodeBean bean_child = list_add.get(i);
                        if (i == list_add.size() - 1) {
                            int last_last = postion + 1 + i + 1;
                            if (last_last < mlist_bean.size() && !bean.getSuperID().equals("0")) {
                                ExamNodeBean bean_last_last = mlist_bean.get(last_last);
                                if (bean_last_last.getSuperID().equals(bean.getSuperID())) {
                                    bean_child.setLast(false);
                                } else
                                    bean_child.setLast(true);
                            } else {
                                bean_child.setLast(true);
                            }
                        } else {
                            bean_child.setLast(false);
                        }
                        ((ExamNodeBean) list_add.get(i)).setFirstDate(false);
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
        public void ExamGo(ExamNodeBean bean);

        public void ExamErrGo(ExamNodeBean bean);
    }
}
