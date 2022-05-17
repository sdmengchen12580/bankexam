package com.udit.bankexam.view.exam_err;

import android.content.Context;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.bean.NoteBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamErrTitleView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamErrTitleView.View param1View) { super(param1View); }
    
    public abstract void getBJ(int param1Int, String param1String1, String param1String2);
    
    public abstract void getExam(Context param1Context, String param1String1, String param1String2);
    
    public abstract void getRobotExamList(Context param1Context, String param1String1, String param1String2);
    
    public abstract void setBJ(String param1String1, String param1String2, String param1String3);
    
    public abstract void shoucang(String param1String1, String param1String2, String param1String3, String param1String4);
  }
  
  public static interface View extends IBaseView {
    void getBJ(int param1Int, NoteBean param1NoteBean);
    
    void setBJERR();
    
    void setBJOK(NoteBean param1NoteBean);
    
    void setExam(List<ExamBean> param1List);
    
    void shoucangErr();
    
    void shoucangOK(List<FavoriteRecord> param1List, boolean param1Boolean);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_err\ExamErrTitleView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */