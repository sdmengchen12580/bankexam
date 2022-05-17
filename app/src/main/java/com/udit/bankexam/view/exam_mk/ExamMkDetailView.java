package com.udit.bankexam.view.exam_mk;

import android.content.Context;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.MKBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamMkDetailView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamMkDetailView.View param1View) { super(param1View); }
    
    public abstract void MkSign(Context param1Context, String param1String1, String param1String2);
    
    public abstract void getExam(Context param1Context, String param1String1, String param1String2);
    
    public abstract void getMKDetail(Context param1Context, String param1String1, String param1String2, String param1String3);
    
    public abstract void saveExam(String param1String1, String param1String2, String param1String3);
  }
  
  public static interface View extends IBaseView {
    void setExamTitleList(List<ExamTitleBean> param1List);
    
    void setMKDetail(MKBean param1MKBean);
    
    void setMkSignErr();
    
    void setMkSignOk();
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_mk\ExamMkDetailView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */