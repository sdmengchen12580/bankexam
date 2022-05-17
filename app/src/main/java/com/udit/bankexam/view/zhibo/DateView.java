package com.udit.bankexam.view.zhibo;

import com.udit.bankexam.bean.ZhiboKeChengBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface DateView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(DateView.View param1View) { super(param1View); }
    
    public abstract void getZhiboDate(String param1String);
  }
  
  public static interface View extends IBaseView {
    void setZhiboDate(List<ZhiboKeChengBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\zhibo\DateView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */