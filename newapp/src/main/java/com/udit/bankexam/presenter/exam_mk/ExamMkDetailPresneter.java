package com.udit.bankexam.presenter.exam_mk;

import android.content.Context;
import com.udit.bankexam.bean_ok.MKBean;
import com.udit.bankexam.view_ok.exam_mk.ExamMkDetailView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ExamMkDetailPresneter extends ExamMkDetailView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamMkDetailPresneter(ExamMkDetailView.View paramView) { super(paramView); }
  
  public void MkSign(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("正在报名", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doPutSign");
      hashMap.put("uid", paramString1);
      hashMap.put("MockID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonArrayOk(param1String)) {
                ((ExamMkDetailView.View)ExamMkDetailPresneter.this.mView).setMkSignOk();
              } else {
                ((ExamMkDetailView.View)ExamMkDetailPresneter.this.mView).setMkSignErr();
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ExamMkDetailPresneter.this.TAG, param1String);
              ((ExamMkDetailView.View)ExamMkDetailPresneter.this.mView).setMkSignErr();
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((ExamMkDetailView.View)this.mView).setMkSignErr();
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void getExam(Context paramContext, String paramString1, String paramString2) {
    HashMap hashMap = new HashMap();
    try {
      ProgressUtils.showProgressDlg("获取题目中", paramContext);
      hashMap.put("action", "doExaminTitle");
      hashMap.put("uid", paramString1);
      hashMap.put("EID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null) {
                ((ExamMkDetailView.View)ExamMkDetailPresneter.this.mView).setExamTitleList(list);
              } else {
                ((ExamMkDetailView.View)ExamMkDetailPresneter.this.mView).setExamTitleList(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ExamMkDetailPresneter.this.TAG, param1String);
              ((ExamMkDetailView.View)ExamMkDetailPresneter.this.mView).setExamTitleList(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((ExamMkDetailView.View)this.mView).setExamTitleList(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void getMKDetail(Context paramContext, String paramString1, String paramString2, String paramString3) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetAdvID");
      hashMap.put("uid", paramString1);
      hashMap.put("path", paramString2);
      hashMap.put("title", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, MKBean.class);
              if (list != null && list.size() > 0) {
                ((ExamMkDetailView.View)ExamMkDetailPresneter.this.mView).setMKDetail((MKBean)list.get(0));
              } else {
                ((ExamMkDetailView.View)ExamMkDetailPresneter.this.mView).setMKDetail(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ExamMkDetailPresneter.this.TAG, param1String);
              ((ExamMkDetailView.View)ExamMkDetailPresneter.this.mView).setMKDetail(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((ExamMkDetailView.View)this.mView).setMKDetail(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void saveExam(String paramString1, String paramString2, String paramString3) {
    HashMap hashMap = new HashMap();
    hashMap.put("action", "doPutPurch");
    hashMap.put("uid", paramString1);
    hashMap.put("PType", "模考试卷");
    hashMap.put("FeeDate", MyDateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
    hashMap.put("LinkID", paramString2);
    hashMap.put("Fee", "0");
    hashMap.put("Abstract", paramString3);
    hashMap.put("Intro", "免费");
    hashMap.put("PState", "已支付");
    try {
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {}
            
            public void onError(String param1String) {}
          });
      return;
    } catch (Exception paramString1) {
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_mk\ExamMkDetailPresneter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */