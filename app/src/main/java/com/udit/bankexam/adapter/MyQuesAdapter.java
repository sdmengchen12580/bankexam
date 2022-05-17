package com.udit.bankexam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.Myques.DataBean.ResponseBean.RowsBean;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.frame.common.circleImageView.CircleImageView;
import java.util.List;

public class MyQuesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<RowsBean> mList;
    private ViewGroup.LayoutParams vpViewG;

    public MyQuesAdapter(Context paramContext, List<RowsBean> paramList,ClickCall clickCall) {
        this.clickCall = clickCall;
        this.context = paramContext;
        this.mList = paramList;
//        this.vpViewG = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(paramContext, 130F));
        this.vpViewG = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public int getItemCount() {
        return this.mList.size();
    }

    public void addBottomDate(List<RowsBean> rows) {
        if (mList != null) {
            mList.addAll(rows);
            notifyDataSetChanged();
        }
    }

    public void refresh(List<RowsBean> rows) {
        if (mList != null) {
            mList.clear();
            mList.addAll(rows);
            notifyDataSetChanged();
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, final int paramInt) {
        paramViewHolder.setIsRecyclable(false);
        if (paramViewHolder instanceof HasViewHolder) {
            ((HasViewHolder) paramViewHolder).container.setLayoutParams(this.vpViewG);
            ((HasViewHolder) paramViewHolder).container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(FastClickUtils.isFastClick()){
                        return;
                    }
                    if(MyQuesAdapter.this.clickCall!=null){
                        MyQuesAdapter.this.clickCall.clickItem(mList.get(paramInt).getId());
                    }
                }
            });
            //数据展示
            final RowsBean bean = mList.get(paramInt);
            if (!bean.getUserAvatar().equals("")) {
                ImageLoader.getInstance().displayImage(bean.getUserAvatar(), ((HasViewHolder) paramViewHolder).img_user);
            } else {
                ((HasViewHolder) paramViewHolder).img_user.setImageDrawable(context.getResources().getDrawable(R.mipmap.img_user));
            }
            ((HasViewHolder) paramViewHolder).tv_name.setText(bean.getUserNickName());
            ((HasViewHolder) paramViewHolder).tv_type.setText(bean.getQuestionCatCodeName());
            if (!bean.getTitle().equals("")) {
                ((HasViewHolder) paramViewHolder).tv_content.setText(bean.getTitle());
                ((HasViewHolder) paramViewHolder).tv_content.setVisibility(View.VISIBLE);
            } else {
                ((HasViewHolder) paramViewHolder).tv_content.setVisibility(View.GONE);
            }
            ((HasViewHolder) paramViewHolder).tv_answerNum.setText(bean.getAnswerNum() + "");
            ((HasViewHolder) paramViewHolder).tv_viewNum.setText(bean.getViewNum() + "");
            ((HasViewHolder) paramViewHolder).tv_time.setText(bean.getAddTime());
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new HasViewHolder(LayoutInflater.from(this.context).inflate(R.layout.itemlayout_my_ques, null));
    }

    private class HasViewHolder extends RecyclerView.ViewHolder {

        public View item;
        private LinearLayout container;
        private CircleImageView img_user;
        private TextView tv_name;
        private TextView tv_type;
        private TextView tv_content;
        private TextView tv_answerNum;
        private TextView tv_viewNum;
        private TextView tv_time;

        public HasViewHolder(View param1View) {
            super(param1View);
            this.item = param1View;
            this.tv_name = (TextView) param1View.findViewById(R.id.tv_name);
            this.container = (LinearLayout) param1View.findViewById(R.id.container);
            this.img_user = (CircleImageView) param1View.findViewById(R.id.img_user);
            this.tv_type = (TextView) param1View.findViewById(R.id.tv_type);
            this.tv_content = (TextView) param1View.findViewById(R.id.tv_content);
            this.tv_viewNum = (TextView) param1View.findViewById(R.id.tv_viewNum);
            this.tv_answerNum = (TextView) param1View.findViewById(R.id.tv_answerNum);
            this.tv_time = (TextView) param1View.findViewById(R.id.tv_time);
        }
    }

    private ClickCall clickCall;

    public interface ClickCall{
        void clickItem(String id);
    }
}
