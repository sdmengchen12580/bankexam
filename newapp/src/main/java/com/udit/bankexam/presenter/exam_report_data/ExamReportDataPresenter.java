package com.udit.bankexam.presenter.exam_report_data;

import com.udit.bankexam.view_ok.exam_report_data.ExamReportDataView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import java.util.HashMap;
import java.util.List;

public class ExamReportDataPresenter extends ExamReportDataView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamReportDataPresenter(ExamReportDataView.View paramView) { super(paramView); }
  
  public void getReport(String paramString) {}
  
  public void getReportDTK(String paramString) {}
  
  public void getSJ(String paramString) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetPurch");
      hashMap.put("uid", paramString);
      hashMap.put("PType", "试卷");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.PurchBean.class);
              if (list != null && list.size() > 0) {
                ((ExamReportDataView.View)ExamReportDataPresenter.this.mView).setSJ(list);
                return;
              } 
              ((ExamReportDataView.View)ExamReportDataPresenter.this.mView).setSJ(null);
            }
            
            public void onError(String param1String) { ((ExamReportDataView.View)ExamReportDataPresenter.this.mView).setSJ(null); }
          });
      return;
    } catch (Exception paramString) {
      paramString.printStackTrace();
      MyLogUtils.e(this.TAG, paramString.getMessage());
      ((ExamReportDataView.View)this.mView).setSJ(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_report_data\ExamReportDataPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */