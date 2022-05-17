package com.udit.bankexam.presenter.exam_sole;

import android.content.Context;
import com.udit.bankexam.view_ok.exam_sole.ExamSoleView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ExamSolePresenter extends ExamSoleView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamSolePresenter(ExamSoleView.View paramView) { super(paramView); }
  
  public void getExamSole(Context paramContext, String paramString1, String paramString2) {
    HashMap hashMap = new HashMap();
    try {
      ProgressUtils.showProgressDlg("获取试卷中", paramContext);
      hashMap.put("uid", paramString1);
      hashMap.put("TypeInfo", paramString2);
      hashMap.put("action", "doGetExamin");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamInfoBean.class);
              if (list != null && list.size() > 0) {
                ((ExamSoleView.View)ExamSolePresenter.this.mView).setExamList(list);
              } else {
                ((ExamSoleView.View)ExamSolePresenter.this.mView).setExamList(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ExamSolePresenter.this.TAG, param1String);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void getExamTitle(Context paramContext, String paramString1, String paramString2) {
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
                ((ExamSoleView.View)ExamSolePresenter.this.mView).setExamTitleList(list);
              } else {
                ((ExamSoleView.View)ExamSolePresenter.this.mView).setExamTitleList(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ExamSolePresenter.this.TAG, param1String);
              ((ExamSoleView.View)ExamSolePresenter.this.mView).setExamTitleList(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((ExamSoleView.View)this.mView).setExamTitleList(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void saveExam(String paramString1, String paramString2, String paramString3) {
    HashMap hashMap = new HashMap();
    hashMap.put("action", "doPutPurch");
    hashMap.put("uid", paramString1);
    hashMap.put("PType", "试卷");
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


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_sole\ExamSolePresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */