package com.udit.bankexam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.VideoTypeOneBean;
import com.udit.bankexam.ui.video.VideoTypeTwoActivityNew;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.view.CustomFiveAngleImg;
import com.udit.bankexam.view.ShadowContainer;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseApplication;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyCommonUtils;
import com.udit.frame.utils.Utils;

import java.util.List;

public class YqDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> mList;
    private ViewGroup.LayoutParams vpViewG;

    public YqDetailAdapter(Context paramContext, List<String> paramList) {
        this.context = paramContext;
        this.mList = paramList;
        this.vpViewG = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(paramContext, 65F));
    }

    public int getItemCount() {
        return this.mList.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
        if (paramViewHolder instanceof HasViewHolder) {
            ((HasViewHolder) paramViewHolder).container.setLayoutParams(vpViewG);
            ((HasViewHolder) paramViewHolder).tv_line.setVisibility((paramInt == mList.size() - 1) ? View.GONE : View.VISIBLE);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new HasViewHolder(LayoutInflater.from(this.context).inflate(R.layout.itemlayout_yaoqing_detail, null));
    }

    private class HasViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout container;
        public View tv_line;
        public TextView tv_time;
        public TextView tv_content;

        public HasViewHolder(View param1View) {
            super(param1View);
            this.container = (RelativeLayout) param1View.findViewById(R.id.container);
            this.tv_line = param1View.findViewById(R.id.tv_line);
            this.tv_time = (TextView) param1View.findViewById(R.id.tv_time);
            this.tv_content = (TextView) param1View.findViewById(R.id.tv_content);
        }
    }
}
