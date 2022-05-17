package com.udit.bankexam.view.exam_year;

import android.content.Context;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamYearView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamYearView.View param1View) { super(param1View); }
    
    public abstract void getExamTitle(Context param1Context, String param1String1, String param1String2);
    
    public abstract void getExamYear(Context param1Context, String param1String1, String param1String2);
    
    public abstract void saveExam(String param1String1, String param1String2, String param1String3);
  }
  
  public static interface View extends IBaseView {
    void setExamList(List<ExamInfoBean> param1List);
    
    void setExamTitleList(List<ExamTitleBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_year\ExamYearView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */