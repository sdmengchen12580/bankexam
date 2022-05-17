package com.udit.bankexam.presenter.exam_report_data;

import android.content.Context;
import com.udit.bankexam.bean_ok.ReportBean;
import com.udit.bankexam.view_ok.exam_report_data.ExamReportDataDetailView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ExamReportDataDetailPresenter extends ExamReportDataDetailView.Presenter {
  public ExamReportDataDetailPresenter(ExamReportDataDetailView.View paramView) { super(paramView); }
  
  public void getRep(String paramString1, String paramString2, Context paramContext) {
    HashMap hashMap = new HashMap();
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      hashMap.put("action", "doGetRepPractice");
      hashMap.put("uid", paramString1);
      hashMap.put("EID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, ReportBean.class);
              if (list != null && list.size() > 0) {
                ((ExamReportDataDetailView.View)ExamReportDataDetailPresenter.this.mView).setReport((ReportBean)list.get(0));
              } else {
                ((ExamReportDataDetailView.View)ExamReportDataDetailPresenter.this.mView).setReport(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamReportDataDetailView.View)ExamReportDataDetailPresenter.this.mView).setReport(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((ExamReportDataDetailView.View)this.mView).setReport(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_report_data\ExamReportDataDetailPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */