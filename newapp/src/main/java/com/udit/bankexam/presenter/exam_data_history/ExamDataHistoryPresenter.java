package com.udit.bankexam.presenter.exam_data_history;

import com.udit.bankexam.view_ok.exam_data_history.ExamDataHistoryView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import java.util.HashMap;
import java.util.List;

public class ExamDataHistoryPresenter extends ExamDataHistoryView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamDataHistoryPresenter(ExamDataHistoryView.View paramView) { super(paramView); }
  
  public void getExam(String paramString1, String paramString2) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doExaminTitle");
      hashMap.put("uid", paramString1);
      hashMap.put("EID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null && list.size() > 0) {
                ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setExam(list);
                return;
              } 
              ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setExam(null);
            }
            
            public void onError(String param1String) { ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setExam(null); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((ExamDataHistoryView.View)this.mView).setExam(null);
      return;
    } 
  }
  
  public void getHisExPract(String paramString) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetHisExPract");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.HisPractBean.class);
              if (list != null && list.size() > 0) {
                ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setHisExPract(list);
                return;
              } 
              ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setHisExPract(null);
            }
            
            public void onError(String param1String) { ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setHisExPract(null); }
          });
      return;
    } catch (Exception paramString) {
      paramString.printStackTrace();
      ((ExamDataHistoryView.View)this.mView).setHisExPract(null);
      return;
    } 
  }
  
  public void getHisExam(String paramString1, String paramString2) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetPractTitle");
      hashMap.put("uid", paramString1);
      hashMap.put("PID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null && list.size() > 0) {
                ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setExam(list);
                return;
              } 
              ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setExam(null);
            }
            
            public void onError(String param1String) { ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setExam(null); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((ExamDataHistoryView.View)this.mView).setExam(null);
      return;
    } 
  }
  
  public void getHisPract(String paramString) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetAllHisPract");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.HisPractBean.class);
              if (list != null && list.size() > 0) {
                ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setHisPract(list);
                return;
              } 
              ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setHisPract(null);
            }
            
            public void onError(String param1String) { ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setHisPract(null); }
          });
      return;
    } catch (Exception paramString) {
      paramString.printStackTrace();
      ((ExamDataHistoryView.View)this.mView).setHisPract(null);
      return;
    } 
  }
  
  public void getPurch(String paramString) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetPurch");
      hashMap.put("uid", paramString);
      hashMap.put("PType", "试卷");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.PurchBean.class);
              if (list != null && list.size() > 0) {
                ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setPurch(list);
                return;
              } 
              ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setPurch(null);
            }
            
            public void onError(String param1String) { ((ExamDataHistoryView.View)ExamDataHistoryPresenter.this.mView).setPurch(null); }
          });
      return;
    } catch (Exception paramString) {
      ((ExamDataHistoryView.View)this.mView).setPurch(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_data_history\ExamDataHistoryPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */