package com.udit.bankexam.view.exam_day;

import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface ExamDayDetailView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamDayDetailView.View param1View) { super(param1View); }
    
    public abstract void isPay(String param1String1, String param1String2, String param1String3);
    
    public abstract void setPay(String param1String1, String param1String2, String param1String3);
  }
  
  public static interface View extends IBaseView {
    void payErr();
    
    void payOk();
    
    void setPayErr();
    
    void setPayOk();
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_day\ExamDayDetailView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */