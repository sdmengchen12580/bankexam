package com.udit.bankexam.presenter.exam_err;

import android.content.Context;
import com.udit.bankexam.bean_ok.NoteBean;
import com.udit.bankexam.view_ok.exam_err.ExamErrTitleView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ExamErrTitlePresenter extends ExamErrTitleView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamErrTitlePresenter(ExamErrTitleView.View paramView) { super(paramView); }
  
  public void getBJ(final int postion, String paramString1, String paramString2) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetNote");
      hashMap.put("uid", paramString1);
      hashMap.put("ID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, NoteBean.class);
              if (list != null && list.size() > 0) {
                ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).getBJ(postion, (NoteBean)list.get(0));
                return;
              } 
              ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).getBJ(postion, null);
            }
            
            public void onError(String param1String) { ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).getBJ(postion, null); }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((ExamErrTitleView.View)this.mView).getBJ(paramInt, null);
      return;
    } 
  }
  
  public void getExam(Context paramContext, String paramString1, String paramString2) {
    ProgressUtils.showProgressDlg("", paramContext);
    hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetExaminTitles");
      hashMap.put("uid", paramString1);
      hashMap.put("idlist", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", com.udit.bankexam.bean_ok.ExamBean.class);
                if (list != null && list.size() > 0) {
                  ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).setExam(list);
                } else {
                  ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).setExam(null);
                } 
              } else {
                ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).setExam(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ExamErrTitlePresenter.this.TAG, param1String);
              ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).setExam(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception hashMap) {
      hashMap.printStackTrace();
      MyLogUtils.e(this.TAG, hashMap.getMessage());
      ((ExamErrTitleView.View)this.mView).setExam(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void getRobotExamList(Context paramContext, String paramString1, String paramString2) {
    HashMap hashMap = new HashMap();
    ProgressUtils.showProgressDlg("", paramContext);
    try {
      hashMap.put("action", "doGetPractTitles");
      hashMap.put("idlist", paramString2);
      hashMap.put("uid", paramString1);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", com.udit.bankexam.bean_ok.ExamBean.class);
                if (list != null && list.size() > 0) {
                  ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).setExam(list);
                } else {
                  ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).setExam(null);
                } 
                ProgressUtils.stopProgressDlg();
                return;
              } 
              ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).setExam(null);
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ExamErrTitlePresenter.this.TAG, param1String);
              ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).setExam(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((ExamErrTitleView.View)this.mView).setExam(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void quxiaoshoucang(String paramString1, String paramString2, String paramString3) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doDelFavorite");
      hashMap.put("uid", paramString1);
      hashMap.put("LinkID", paramString2);
      hashMap.put("FType", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.FavoriteRecord.class);
              if (list != null && list.size() > 0) {
                ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).shoucangOK(list, false);
                return;
              } 
              ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).shoucangErr();
            }
            
            public void onError(String param1String) { ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).shoucangErr(); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((ExamErrTitleView.View)this.mView).shoucangErr();
      return;
    } 
  }
  
  public void setBJ(String paramString1, String paramString2, String paramString3) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doPutNote");
      hashMap.put("uid", paramString1);
      hashMap.put("ID", paramString2);
      hashMap.put("nDate", MyDateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
      hashMap.put("Note", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, NoteBean.class);
              if (list != null && list.size() > 0) {
                ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).setBJOK((NoteBean)list.get(0));
                return;
              } 
              ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).setBJERR();
            }
            
            public void onError(String param1String) { ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).setBJERR(); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((ExamErrTitleView.View)this.mView).setBJERR();
      return;
    } 
  }
  
  public void shoucang(String paramString1, String paramString2, String paramString3, String paramString4) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doPutFavorite");
      hashMap.put("uid", paramString1);
      hashMap.put("ATime", MyDateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
      hashMap.put("LinkID", paramString3);
      hashMap.put("FType", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.FavoriteRecord.class);
              if (list != null && list.size() > 0) {
                ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).shoucangOK(list, true);
                return;
              } 
              ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).shoucangErr();
            }
            
            public void onError(String param1String) { ((ExamErrTitleView.View)ExamErrTitlePresenter.this.mView).shoucangErr(); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((ExamErrTitleView.View)this.mView).shoucangErr();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_err\ExamErrTitlePresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */