package com.udit.bankexam.view.sreach;

import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamSreachKeyView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamSreachKeyView.View param1View) { super(param1View); }
  }
  
  public static interface View extends IBaseView {
    void setExam(List<String> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\sreach\ExamSreachKeyView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */