package com.udit.bankexam.view.exam;

import android.content.Context;

import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamView.View param1View) { super(param1View); }
    
    public abstract void getExamList(Context param1Context, String param1String1, String param1String2);
    
    public abstract void getRobotExamList(Context param1Context, String param1String1, String param1String2);
    
    public abstract void quxiaoshoucang(Context param1Context, String param1String1, String param1String2, String param1String3);
    
    public abstract void setSolution(Context param1Context, String param1String1, String param1String2);
    
    public abstract void setSolutionList(Context param1Context, String param1String, List<ExamBean> param1List);
    
    public abstract void setSolutionZhiNeng(Context param1Context, String param1String1, String param1String2);
    
    public abstract void setSolutionZhiNeng(Context param1Context, String param1String, List<ExamBean> param1List);
    
    public abstract void shoucang(Context param1Context, String param1String1, String param1String2, String param1String3, String param1String4);
  }
  
  public static interface View extends IBaseView {
    void setExamList(List<ExamBean> param1List);
    
    void setSolutionErr();
    
    void setSolutionOK();
    
    void setSolutionOK(int param1Int1, int param1Int2);
    
    void shoucangErr();
    
    void shoucangOK(List<FavoriteRecord> param1List, boolean param1Boolean);
  }
}
