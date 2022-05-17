package com.udit.bankexam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.frame.utils.Utils;

import java.util.List;

public class HomeWorkInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    private List<String> mList;

    private ViewGroup.LayoutParams vpViewG;

    public HomeWorkInfoAdapter(Context paramContext, List<String> paramList) {
        this.context = paramContext;
        this.mList = paramList;
        this.vpViewG = new RelativeLayout.LayoutParams(-1, Utils.dip2px(paramContext, 41.0F));
    }

    public int getItemCount() {
        return this.mList.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
        paramViewHolder.setIsRecyclable(false);
        if (paramViewHolder instanceof HasViewHolder)
            ((HasViewHolder) paramViewHolder).layout.setLayoutParams(this.vpViewG);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new HasViewHolder(LayoutInflater.from(this.context).inflate(R.layout.itemlayout_home_work, null));
    }

    private class HasViewHolder extends RecyclerView.ViewHolder {
        public View item;

        public RelativeLayout layout;

        private TextView tv_content;

        public HasViewHolder(View param1View) {
            super(param1View);
            this.item = param1View;
            this.layout = (RelativeLayout) param1View.findViewById(R.id.layout);
            this.tv_content = (TextView) param1View.findViewById(R.id.tv_content);
        }
    }
}

