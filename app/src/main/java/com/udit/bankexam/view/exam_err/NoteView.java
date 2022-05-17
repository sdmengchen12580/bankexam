package com.udit.bankexam.view.exam_err;

import com.udit.bankexam.bean.ExamNoteBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface NoteView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(NoteView.View param1View) { super(param1View); }
    
    public abstract void setBJ(String param1String1, String param1String2, String param1String3);
  }
  
  public static interface View extends IBaseView {
    void setBJ(ExamNoteBean param1ExamNoteBean);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_err\NoteView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */