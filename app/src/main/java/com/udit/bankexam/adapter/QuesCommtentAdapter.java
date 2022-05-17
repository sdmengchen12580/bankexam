package com.udit.bankexam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.CommentList.DataBean.ResponseBean.RowsBean;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.view.HoldyComment;
import com.udit.frame.utils.ViewUtils;

import java.util.List;

public class QuesCommtentAdapter extends BaseAdapter {

    private Context mContext;
    private List<RowsBean> mlist_bean;
    private ViewGroup.LayoutParams vpViewG;

    public QuesCommtentAdapter(List<RowsBean> paramList, Context paramContext) {
        this.mlist_bean = paramList;
        this.mContext = paramContext;
        this.vpViewG = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public int getCount() {
        return this.mlist_bean.size();
    }

    public RowsBean getItem(int paramInt) {
        return this.mlist_bean.get(paramInt);
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup) {
        HoldyComment holdyComment;
        if (paramView == null) {
            paramView = LayoutInflater.from(this.mContext).inflate(R.layout.itemlayout_ques_commtent, null);
            holdyComment = new HoldyComment();
            ViewUtils.initHolderView(holdyComment, paramView, R.id.class);
            holdyComment.layout.setLayoutParams(this.vpViewG);
            paramView.setTag(holdyComment);
        } else {
            holdyComment = (HoldyComment) paramView.getTag();
        }
        final RowsBean rowsBean = mlist_bean.get(paramInt);
        if (!rowsBean.getAnswerUserAvatar().equals("")) {
            ImageLoader.getInstance().displayImage(rowsBean.getAnswerUserAvatar(), holdyComment.img_user);
        }
        holdyComment.tv_name.setText(rowsBean.getAnswerUserNickName());
        holdyComment.img_delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                if (QuesCommtentAdapter.this.clickCallBack != null) {
                    if (!rowsBean.isIsCanDel()) {
                        Toast.makeText(mContext, "抱歉,只能删除自己的评论哦~", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    QuesCommtentAdapter.this.clickCallBack.img_delect(rowsBean.getId(), paramInt);
                }
            }
        });
        if (rowsBean.getAnswerContent().equals("")) {
            holdyComment.tv_content.setVisibility(View.GONE);
        } else {
            holdyComment.tv_content.setVisibility(View.VISIBLE);
            holdyComment.tv_content.setText(rowsBean.getAnswerContent());
        }
        holdyComment.tv_zan_num.setText(rowsBean.getPraiseNum() + "");
        holdyComment.tv_time.setText(rowsBean.getAddTime() + "");
        holdyComment.img_zan.setImageResource(rowsBean.isIsPraised() ? R.mipmap.img_dianzanok : R.mipmap.img_dianzan);
        holdyComment.img_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                if (QuesCommtentAdapter.this.clickCallBack != null) {
                    QuesCommtentAdapter.this.clickCallBack.zan(rowsBean.getYkQuestionId(),
                            paramInt, rowsBean.isIsPraised());
                }
            }
        });
        return paramView;
    }

    public boolean isEmpty() {
        return false;
    }

    public void addDataBottom(List<RowsBean> rows) {
        if (mlist_bean != null) {
            mlist_bean.addAll(rows);
            notifyDataSetChanged();
        }
    }

    public void refresh(List<RowsBean> rows) {
        if (mlist_bean != null) {
            mlist_bean.clear();
            mlist_bean.addAll(rows);
            notifyDataSetChanged();
        }
    }

    public void updateZan(int positon, boolean b) {
        if (mlist_bean != null && positon < mlist_bean.size()) {
            mlist_bean.get(positon).setIsPraised(b);
            mlist_bean.get(positon).setPraiseNum(b ? mlist_bean.get(positon).getPraiseNum() + 1 : mlist_bean.get(positon).getPraiseNum() - 1);
            notifyDataSetChanged();
        }
    }

    public void delect(int positon) {
        if (mlist_bean != null && positon < mlist_bean.size()) {
            mlist_bean.remove(positon);
            notifyDataSetChanged();
        }
    }

    public interface ClickCallBack {
        void zan(String id, int positon, boolean isZan);

        void img_delect(String id, int positon);
    }

    private ClickCallBack clickCallBack;

    public void setClickCallBack(ClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }
}
