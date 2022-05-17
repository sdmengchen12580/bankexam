package com.udit.bankexam.presenter.exam_notebook;

import android.content.Context;
import com.udit.bankexam.bean_ok.ExamNoteBean;
import com.udit.bankexam.view_ok.exam_notebook.ExamNoteBookView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ExamNoteBookPresenter extends ExamNoteBookView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamNoteBookPresenter(ExamNoteBookView.View paramView) { super(paramView); }
  
  public void getNote(Context paramContext, String paramString1, String paramString2) {
    hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetNote");
      hashMap.put("uid", paramString1);
      hashMap.put("ID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, ExamNoteBean.class);
              if (list != null && list.size() > 0) {
                ((ExamNoteBookView.View)ExamNoteBookPresenter.this.mView).setNote((ExamNoteBean)list.get(0));
                return;
              } 
              ((ExamNoteBookView.View)ExamNoteBookPresenter.this.mView).setNote(null);
            }
            
            public void onError(String param1String) { ((ExamNoteBookView.View)ExamNoteBookPresenter.this.mView).setNote(null); }
          });
      return;
    } catch (Exception hashMap) {
      MyLogUtils.e(this.TAG, hashMap.getMessage());
      ((ExamNoteBookView.View)this.mView).setNote(null);
      return;
    } 
  }
  
  public void getNotes(Context paramContext, String paramString) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetNoteS");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToList(param1String, "list", com.udit.bankexam.bean_ok.ExamBean.class);
              if (list != null && list.size() > 0) {
                ((ExamNoteBookView.View)ExamNoteBookPresenter.this.mView).setNotes(list);
              } else {
                ((ExamNoteBookView.View)ExamNoteBookPresenter.this.mView).setNotes(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamNoteBookView.View)ExamNoteBookPresenter.this.mView).setNotes(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((ExamNoteBookView.View)this.mView).setNotes(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_notebook\ExamNoteBookPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */