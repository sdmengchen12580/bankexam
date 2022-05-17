package com.udit.bankexam.presenter.exam_day;

import com.udit.bankexam.view_ok.exam_day.ExamDayDetailView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import java.util.HashMap;

public class ExamDayDetailPresenter extends ExamDayDetailView.Presenter {
  public ExamDayDetailPresenter(ExamDayDetailView.View paramView) { super(paramView); }
  
  public void isPay(String paramString1, String paramString2, String paramString3) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doIsPay");
      hashMap.put("uid", paramString1);
      hashMap.put("id", paramString2);
      hashMap.put("type", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonArrayOk(param1String)) {
                ((ExamDayDetailView.View)ExamDayDetailPresenter.this.mView).payOk();
                return;
              } 
              ((ExamDayDetailView.View)ExamDayDetailPresenter.this.mView).payErr();
            }
            
            public void onError(String param1String) { ((ExamDayDetailView.View)ExamDayDetailPresenter.this.mView).payErr(); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((ExamDayDetailView.View)this.mView).payErr();
      return;
    } 
  }
  
  public void setPay(String paramString1, String paramString2, String paramString3) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doPutPurch");
      hashMap.put("uid", paramString1);
      hashMap.put("PType", paramString2);
      hashMap.put("FeeDate", MyDateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
      hashMap.put("LinkID", paramString3);
      hashMap.put("Abstract", "");
      hashMap.put("Fee", "0");
      hashMap.put("Intro", "免费");
      hashMap.put("PState", "已支付");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonArrayOk(param1String)) {
                ((ExamDayDetailView.View)ExamDayDetailPresenter.this.mView).setPayOk();
                return;
              } 
              ((ExamDayDetailView.View)ExamDayDetailPresenter.this.mView).setPayErr();
            }
            
            public void onError(String param1String) { ((ExamDayDetailView.View)ExamDayDetailPresenter.this.mView).setPayErr(); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((ExamDayDetailView.View)this.mView).setPayErr();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_day\ExamDayDetailPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */