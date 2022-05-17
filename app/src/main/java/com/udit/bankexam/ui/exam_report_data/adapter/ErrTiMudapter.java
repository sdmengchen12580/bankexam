package com.udit.bankexam.ui.exam_report_data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.PurchBean;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;
import java.util.List;

public class ErrTiMudapter extends BaseAdapter {
  private Context mContext;
  
  private List<PurchBean> mlist;
  
  private ViewGroup.LayoutParams vpViewG;
  
  public ErrTiMudapter(List<PurchBean> paramList, Context paramContext) {
    this.mlist = paramList;
    this.mContext = paramContext;
    this.vpViewG = new RelativeLayout.LayoutParams(-1, Utils.dip2px(paramContext, 45.0F));
  }
  
  public int getCount() { return this.mlist.size(); }
  
  public Object getItem(int paramInt) { return this.mlist.get(paramInt); }
  
  public long getItemId(int paramInt) { return paramInt; }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
    Holder holder;
    if (paramView == null) {
      paramView = LayoutInflater.from(this.mContext).inflate(R.layout.item_error_timu, null);
      holder = new Holder();
      ViewUtils.initHolderView(holder, paramView, com.udit.bankexam.R.id.class);
      holder.layout.setLayoutParams(this.vpViewG);
      paramView.setTag(holder);
    } else {
      holder = (Holder)paramView.getTag();
    } 
    PurchBean purchBean = (PurchBean)this.mlist.get(paramInt);
    if (!MyCheckUtils.isEmpty(purchBean.getAbstract()))
      holder.textview_sj_name.setText(purchBean.getAbstract()); 
    return paramView;
  }
  
  private class Holder {
    RelativeLayout layout;
    
    TextView textview_sj_name;
    
    private Holder() {}
  }
}

