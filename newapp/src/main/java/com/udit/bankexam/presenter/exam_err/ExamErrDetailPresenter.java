package com.udit.bankexam.presenter.exam_err;

import android.content.Context;
import com.udit.bankexam.view_ok.exam_err.ExamErrDetailView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ExamErrDetailPresenter extends ExamErrDetailView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamErrDetailPresenter(ExamErrDetailView.View paramView) { super(paramView); }
  
  public void getAdv() {
    hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetAdv");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", com.udit.bankexam.bean_ok.AdvBean.class);
                if (list != null && list.size() > 0) {
                  ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setAdv(list);
                  return;
                } 
                ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setAdv(null);
                return;
              } 
              ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setAdv(null);
            }
            
            public void onError(String param1String) { ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setAdv(null); }
          });
      return;
    } catch (Exception hashMap) {
      ((ExamErrDetailView.View)this.mView).setAdv(null);
      return;
    } 
  }
  
  public void getExamTitles(String paramString1, String paramString2) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doExaminTitle");
      hashMap.put("uid", paramString1);
      hashMap.put("EID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null && list.size() > 0) {
                ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJDetail(list);
                return;
              } 
              ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJDetail(null);
            }
            
            public void onError(String param1String) { ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJDetail(null); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((ExamErrDetailView.View)this.mView).setSJDetail(null);
      return;
    } 
  }
  
  public void getExamTtils(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetExaminTitles");
      hashMap.put("uid", paramString1);
      hashMap.put("idlist", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", com.udit.bankexam.bean_ok.ExamBean.class);
                if (list != null && list.size() > 0) {
                  ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJ(list);
                } else {
                  ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJ(null);
                } 
              } else {
                ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJ(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJ(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((ExamErrDetailView.View)this.mView).setSJ(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void getHomeDetail(String paramString1, String paramString2) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetErrTitle");
      hashMap.put("uid", paramString1);
      hashMap.put("EID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null && list.size() > 0) {
                ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJDetail(list);
                return;
              } 
              ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJDetail(null);
            }
            
            public void onError(String param1String) { ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJDetail(null); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((ExamErrDetailView.View)this.mView).setSJDetail(null);
      return;
    } 
  }
  
  public void getSJDetail(String paramString1, String paramString2) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doExaminTitle");
      hashMap.put("uid", paramString1);
      hashMap.put("EID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null && list.size() > 0) {
                ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJDetail(list);
                return;
              } 
              ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJDetail(null);
            }
            
            public void onError(String param1String) { ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJDetail(null); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((ExamErrDetailView.View)this.mView).setSJDetail(null);
      return;
    } 
  }
  
  public void getZhinengTitles(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetPractTitles");
      hashMap.put("idlist", paramString2);
      hashMap.put("uid", paramString1);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", com.udit.bankexam.bean_ok.ExamBean.class);
                if (list != null && list.size() > 0) {
                  ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJ(list);
                } else {
                  ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJ(null);
                } 
              } else {
                ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJ(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamErrDetailView.View)ExamErrDetailPresenter.this.mView).setSJ(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      paramContext.printStackTrace();
      ((ExamErrDetailView.View)this.mView).setSJ(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_err\ExamErrDetailPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */