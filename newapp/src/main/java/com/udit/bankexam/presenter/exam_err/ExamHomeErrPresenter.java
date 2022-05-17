package com.udit.bankexam.presenter.exam_err;

import android.content.Context;
import com.udit.bankexam.view_ok.exam_err.ExamHomeErrView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExamHomeErrPresenter extends ExamHomeErrView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamHomeErrPresenter(ExamHomeErrView.View paramView) { super(paramView); }
  
  public void getExamNode(Context paramContext, String paramString) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetFirstHis");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", com.udit.bankexam.bean_ok.ExamNodeBean.class);
                if (list != null && list.size() > 0) {
                  ((ExamHomeErrView.View)ExamHomeErrPresenter.this.mView).setExamNode(list);
                } else {
                  ((ExamHomeErrView.View)ExamHomeErrPresenter.this.mView).setExamNode(null);
                } 
              } else {
                ((ExamHomeErrView.View)ExamHomeErrPresenter.this.mView).setExamNode(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamHomeErrView.View)ExamHomeErrPresenter.this.mView).setExamNode(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((ExamHomeErrView.View)this.mView).setExamNode(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void getExamNodeErr(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetErrTitleOut");
      hashMap.put("uid", paramString1);
      hashMap.put("OID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null && list.size() > 0) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(list);
                ((ExamHomeErrView.View)ExamHomeErrPresenter.this.mView).setExamNodeErr(arrayList);
              } else {
                ((ExamHomeErrView.View)ExamHomeErrPresenter.this.mView).setExamNodeErr(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamHomeErrView.View)ExamHomeErrPresenter.this.mView).setExamNodeErr(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((ExamHomeErrView.View)this.mView).setExamNodeErr(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_err\ExamHomeErrPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */