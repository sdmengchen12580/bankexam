package com.udit.bankexam.view.exam_notebook;

import android.content.Context;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamNoteBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamNoteBookView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamNoteBookView.View param1View) { super(param1View); }
    
    public abstract void getNote(Context param1Context, String param1String1, String param1String2);
    
    public abstract void getNotes(Context param1Context, String param1String);
  }
  
  public static interface View extends IBaseView {
    void setNote(ExamNoteBean param1ExamNoteBean);
    
    void setNotes(List<ExamBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_notebook\ExamNoteBookView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */