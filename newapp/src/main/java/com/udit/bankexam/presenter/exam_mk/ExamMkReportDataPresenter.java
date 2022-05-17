package com.udit.bankexam.presenter.exam_mk;

import com.udit.bankexam.bean_ok.ReportBean;
import com.udit.bankexam.view_ok.exam_mk.ExamMkReportDataView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ExamMkReportDataPresenter extends ExamMkReportDataView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamMkReportDataPresenter(ExamMkReportDataView.View paramView) { super(paramView); }
  
  public void getMKDTK(String paramString1, String paramString2) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doExaminTitle");
      hashMap.put("uid", paramString1);
      hashMap.put("EID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null) {
                ((ExamMkReportDataView.View)ExamMkReportDataPresenter.this.mView).setMkExamTdk(list);
              } else {
                ((ExamMkReportDataView.View)ExamMkReportDataPresenter.this.mView).setMkExamTdk(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamMkReportDataView.View)ExamMkReportDataPresenter.this.mView).setMkExamTdk(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramString1) {
      ((ExamMkReportDataView.View)this.mView).setMkExamTdk(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void getMkExamNode(String paramString1, String paramString2) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetOutline");
      hashMap.put("uid", paramString1);
      hashMap.put("EID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", com.udit.bankexam.bean_ok.ExamNodeBean.class);
                if (list != null && list.size() > 0) {
                  ((ExamMkReportDataView.View)ExamMkReportDataPresenter.this.mView).setMkExamNode(list);
                  return;
                } 
                ((ExamMkReportDataView.View)ExamMkReportDataPresenter.this.mView).setMkExamNode(null);
                return;
              } 
              ((ExamMkReportDataView.View)ExamMkReportDataPresenter.this.mView).setMkExamNode(null);
            }
            
            public void onError(String param1String) { ((ExamMkReportDataView.View)ExamMkReportDataPresenter.this.mView).setMkExamNode(null); }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((ExamMkReportDataView.View)this.mView).setMkExamNode(null);
      return;
    } 
  }
  
  public void getMkRepPractice(String paramString1, String paramString2) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetRepPractice");
      hashMap.put("uid", paramString1);
      hashMap.put("EID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, ReportBean.class);
              if (list != null && list.size() > 0) {
                ((ExamMkReportDataView.View)ExamMkReportDataPresenter.this.mView).setMkRep((ReportBean)list.get(0));
                return;
              } 
              ((ExamMkReportDataView.View)ExamMkReportDataPresenter.this.mView).setMkRep(null);
            }
            
            public void onError(String param1String) { ((ExamMkReportDataView.View)ExamMkReportDataPresenter.this.mView).setMkRep(null); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((ExamMkReportDataView.View)this.mView).setMkRep(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_mk\ExamMkReportDataPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */