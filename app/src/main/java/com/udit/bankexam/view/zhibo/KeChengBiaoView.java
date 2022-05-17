package com.udit.bankexam.view.zhibo;

import com.udit.bankexam.bean.ZhiboKeChengBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface KeChengBiaoView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(KeChengBiaoView.View param1View) { super(param1View); }
    
    public abstract void getKeChengBiao(String param1String1, String param1String2);
  }
  
  public static interface View extends IBaseView {
    void setZhiboKeCheng(List<ZhiboKeChengBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\zhibo\KeChengBiaoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */