package com.udit.bankexam.view.zixun;

import android.content.Context;
import com.udit.bankexam.bean.ZiXunBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ZiXunView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ZiXunView.View param1View) { super(param1View); }
    
    public abstract void getZiXun(Context param1Context, String param1String, int param1Int1, int param1Int2);
  }
  
  public static interface View extends IBaseView {
    void setZiXun(List<ZiXunBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\zixun\ZiXunView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */