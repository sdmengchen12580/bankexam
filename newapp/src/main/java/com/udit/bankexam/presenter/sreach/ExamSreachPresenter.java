package com.udit.bankexam.presenter.sreach;

import com.udit.bankexam.view_ok.sreach.ExamSreachView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import java.util.HashMap;
import java.util.List;

public class ExamSreachPresenter extends ExamSreachView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamSreachPresenter(ExamSreachView.View paramView) { super(paramView); }
  
  public void sreach(String paramString1, String paramString2, String paramString3, String paramString4) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetSearch");
      hashMap.put("uid", paramString1);
      hashMap.put("KeyWord", paramString2);
      hashMap.put("NPage", paramString3);
      hashMap.put("tCount", paramString4);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", com.udit.bankexam.bean_ok.ExamBean.class);
                if (list != null && list.size() > 0) {
                  ((ExamSreachView.View)ExamSreachPresenter.this.mView).setExam(list);
                  return;
                } 
                ((ExamSreachView.View)ExamSreachPresenter.this.mView).setExam(null);
                return;
              } 
              ((ExamSreachView.View)ExamSreachPresenter.this.mView).setExam(null);
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ExamSreachPresenter.this.TAG, param1String);
              ((ExamSreachView.View)ExamSreachPresenter.this.mView).setExam(null);
            }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((ExamSreachView.View)this.mView).setExam(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\sreach\ExamSreachPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */