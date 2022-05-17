package com.udit.bankexam.view.exam_data_history;

import android.content.Context;

import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamHomeHistoryView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamHomeHistoryView.View param1View) { super(param1View); }
    
    public abstract void getExamNode(Context param1Context, String param1String);
    
    public abstract void getExamNodeISOk(Context param1Context, String param1String1, String param1String2);
  }
  
  public static interface View extends IBaseView {
    void setExamNode(List<ExamNodeBean> param1List);
    
    void setExamNodeIsOk(List<ExamTitleBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_data_history\ExamHomeHistoryView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */