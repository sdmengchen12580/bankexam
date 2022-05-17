package com.udit.bankexam.view.exam_report_week;

import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface ExamReportWeekView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamReportWeekView.View param1View) { super(param1View); }
  }
  
  public static interface View extends IBaseView {}
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_report_week\ExamReportWeekView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */