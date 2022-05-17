package com.udit.bankexam.view.exam_report;

import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface ExamReportView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamReportView.View param1View) { super(param1View); }
  }
  
  public static interface View extends IBaseView {}
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_report\ExamReportView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */