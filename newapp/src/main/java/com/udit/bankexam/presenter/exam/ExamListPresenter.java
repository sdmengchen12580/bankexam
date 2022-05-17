package com.udit.bankexam.presenter.exam;

import com.udit.bankexam.view_ok.exam.ExamListView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import java.util.HashMap;
import java.util.List;

public class ExamListPresenter extends ExamListView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamListPresenter(ExamListView.View paramView) { super(paramView); }
  
  public void getExamList(String paramString1, String paramString2, String paramString3) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetAdvID");
      hashMap.put("uid", paramString1);
      hashMap.put("path", paramString2);
      hashMap.put("title", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamInfoBean.class);
              if (list != null && list.size() > 0) {
                ((ExamListView.View)ExamListPresenter.this.mView).setExamList(list);
                return;
              } 
              ((ExamListView.View)ExamListPresenter.this.mView).setExamList(null);
            }
            
            public void onError(String param1String) { ((ExamListView.View)ExamListPresenter.this.mView).setExamList(null); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((ExamListView.View)this.mView).setExamList(null);
      ((ExamListView.View)this.mView).showLongToast("网络异常，请检查网络后操作");
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam\ExamListPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */