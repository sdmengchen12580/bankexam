package com.udit.bankexam.view.zhibo;

import android.content.Context;
import com.udit.bankexam.bean.ZhiboKeChengBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ZhiboLiebiaoView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ZhiboLiebiaoView.View param1View) { super(param1View); }
    
    public abstract void getZhiboList(Context param1Context, String param1String1, String param1String2);
  }
  
  public static interface View extends IBaseView {
    void setZhiboList(List<ZhiboKeChengBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\zhibo\ZhiboLiebiaoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */