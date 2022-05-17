package com.udit.bankexam.view.exam_report_data;

import android.content.Context;

import com.udit.bankexam.bean.ReportBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface ExamReportDataDetailView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamReportDataDetailView.View param1View) { super(param1View); }
    
    public abstract void getRep(String param1String1, String param1String2, Context param1Context);
  }
  
  public static interface View extends IBaseView {
    void setReport(ReportBean param1ReportBean);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_report_data\ExamReportDataDetailView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */