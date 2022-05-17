package com.udit.bankexam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ZhaoPinInfo.DataBean.ResponseBean.ZhaopinsBean;
import com.udit.bankexam.ui.MyWebUriActivity;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class WorkAdapter extends BaseAdapter {
    private final String TAG = getClass().getSimpleName();

    private Context mContext;

    private List<ZhaopinsBean> mlist_bean;

    private ViewGroup.LayoutParams vpViewG;

    public WorkAdapter(List<ZhaopinsBean> paramList, Context paramContext) {
        this.mlist_bean = paramList;
        this.mContext = paramContext;
        this.vpViewG = new RelativeLayout.LayoutParams(-1, Utils.dip2px(paramContext, 41.0F));
    }

    public int getCount() {
        if(this.mlist_bean==null){
            return 0;
        }
        return this.mlist_bean.size();
    }

    public ZhaopinsBean getItem(int paramInt) {
        return (ZhaopinsBean) this.mlist_bean.get(paramInt);
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        HoldyWork holdyWork;
        if (paramView == null) {
            paramView = LayoutInflater.from(this.mContext).inflate(R.layout.itemlayout_home_work, null);
            holdyWork = new HoldyWork();
            ViewUtils.initHolderView(holdyWork, paramView, com.udit.bankexam.R.id.class);
            holdyWork.layout.setLayoutParams(this.vpViewG);
            paramView.setTag(holdyWork);
        } else {
            holdyWork = (HoldyWork) paramView.getTag();
        }
        final ZhaopinsBean bean = (ZhaopinsBean) this.mlist_bean.get(paramInt);
        holdyWork.tv_content.setText(bean.getTitle());
        holdyWork.layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                MyWebUriActivity.startMyWebUriActivity((BaseActivity) WorkAdapter.this.mContext, "招聘信息", bean.getH5Url());
            }
        });
        View view = holdyWork.tv_line;
        if (paramInt == this.mlist_bean.size() - 1) {
            paramInt = 8;
        } else {
            paramInt = 0;
        }
        view.setVisibility(paramInt);
        return paramView;
    }

    public boolean isEmpty() {
        return false;
    }

    public void refreshData(List<ZhaopinsBean> param1List) {
        if (this.mlist_bean == null) {
            this.mlist_bean = new ArrayList<>();
        }
        if (null != param1List) {
            this.mlist_bean.clear();
            this.mlist_bean.addAll(param1List);
            notifyDataSetChanged();
        }
    }
}
