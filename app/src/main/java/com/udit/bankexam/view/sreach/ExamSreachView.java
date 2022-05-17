package com.udit.bankexam.view.sreach;

import com.udit.bankexam.bean.ExamBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamSreachView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamSreachView.View param1View) { super(param1View); }
    
    public abstract void sreach(String param1String1, String param1String2, String param1String3, String param1String4);
  }
  
  public static interface View extends IBaseView {
    void setExam(List<ExamBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\sreach\ExamSreachView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */