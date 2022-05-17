package com.udit.bankexam.view.exam;

import android.content.Context;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface ExamJiuCuoView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamJiuCuoView.View param1View) { super(param1View); }
    
    public abstract void jiucuo(Context param1Context, String param1String1, String param1String2, String param1String3);
  }
  
  public static interface View extends IBaseView {
    void setJiCuo(boolean param1Boolean);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam\ExamJiuCuoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */