package com.udit.bankexam.presenter.exam_err;

import android.content.Context;
import com.udit.bankexam.view_ok.exam_err.ExamErrView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ExamErrPresenter extends ExamErrView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamErrPresenter(ExamErrView.View paramView) { super(paramView); }
  
  public void getHomeSJDTK(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doExaminTitle");
      hashMap.put("uid", paramString1);
      hashMap.put("EID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null && list.size() > 0) {
                ((ExamErrView.View)ExamErrPresenter.this.mView).setExamTitle(list);
              } else {
                ((ExamErrView.View)ExamErrPresenter.this.mView).setExamTitle(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamErrView.View)ExamErrPresenter.this.mView).setExamTitle(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((ExamErrView.View)this.mView).setExamTitle(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
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
                ((ExamErrView.View)ExamErrPresenter.this.mView).setSJ(list);
                return;
              } 
              ((ExamErrView.View)ExamErrPresenter.this.mView).setSJ(null);
            }
            
            public void onError(String param1String) { ((ExamErrView.View)ExamErrPresenter.this.mView).setSJ(null); }
          });
      return;
    } catch (Exception paramString) {
      paramString.printStackTrace();
      MyLogUtils.e(this.TAG, paramString.getMessage());
      ((ExamErrView.View)this.mView).setSJ(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_err\ExamErrPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */