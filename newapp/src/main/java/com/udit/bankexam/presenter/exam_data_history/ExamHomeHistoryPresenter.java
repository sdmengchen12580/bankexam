package com.udit.bankexam.presenter.exam_data_history;

import android.content.Context;
import com.udit.bankexam.view_ok.exam_data_history.ExamHomeHistoryView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ExamHomeHistoryPresenter extends ExamHomeHistoryView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamHomeHistoryPresenter(ExamHomeHistoryView.View paramView) { super(paramView); }
  
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
                  ((ExamHomeHistoryView.View)ExamHomeHistoryPresenter.this.mView).setExamNode(list);
                } else {
                  ((ExamHomeHistoryView.View)ExamHomeHistoryPresenter.this.mView).setExamNode(null);
                } 
              } else {
                ((ExamHomeHistoryView.View)ExamHomeHistoryPresenter.this.mView).setExamNode(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamHomeHistoryView.View)ExamHomeHistoryPresenter.this.mView).setExamNode(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((ExamHomeHistoryView.View)this.mView).setExamNode(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void getExamNodeISOk(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doOutlineTitleHis");
      hashMap.put("uid", paramString1);
      hashMap.put("OID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null && list.size() > 0) {
                ((ExamHomeHistoryView.View)ExamHomeHistoryPresenter.this.mView).setExamNodeIsOk(list);
              } else {
                ((ExamHomeHistoryView.View)ExamHomeHistoryPresenter.this.mView).setExamNodeIsOk(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamHomeHistoryView.View)ExamHomeHistoryPresenter.this.mView).setExamNodeIsOk(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((ExamHomeHistoryView.View)this.mView).setExamNodeIsOk(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_data_history\ExamHomeHistoryPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */