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
import com.udit.bankexam.bean.QuesList.DataBean.ResponseBean.RowsBean;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.frame.common.circleImageView.CircleImageView;
import com.udit.frame.utils.Utils;

import java.util.List;

public class QuesSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<RowsBean> mList;
    private ViewGroup.LayoutParams vpViewG;

    public QuesSearchAdapter(Context paramContext, List<RowsBean> paramList, ClickCall clickCall) {
        this.clickCall = clickCall;
        this.context = paramContext;
        this.mList = paramList;
        this.vpViewG = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(paramContext, 36f));
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new HasViewHolder(LayoutInflater.from(this.context).inflate(R.layout.itemlayout_search_ques, null));
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

    public int getItemCount() {
        return this.mList.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
        paramViewHolder.setIsRecyclable(false);
        if (paramViewHolder instanceof HasViewHolder) {
            final RowsBean bean = mList.get(paramInt);
            ((HasViewHolder) paramViewHolder).container.setLayoutParams(this.vpViewG);
            ((HasViewHolder) paramViewHolder).container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (FastClickUtils.isFastClick()) {
                        return;
                    }
                    if (QuesSearchAdapter.this.clickCall != null) {
                        QuesSearchAdapter.this.clickCall.clickItem(bean.getId());
                    }
                }
            });
            ((HasViewHolder) paramViewHolder).tv_name.setText(bean.getTitle());
        }
    }

    private class HasViewHolder extends RecyclerView.ViewHolder {

        public View item;
        private RelativeLayout container;
        private TextView tv_name;

        public HasViewHolder(View param1View) {
            super(param1View);
            this.item = param1View;
            this.container = (RelativeLayout) param1View.findViewById(R.id.container);
            this.tv_name = (TextView) param1View.findViewById(R.id.tv_name);
        }
    }

    private ClickCall clickCall;

    public interface ClickCall {
        void clickItem(String id);
    }
}
