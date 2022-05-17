package com.udit.bankexam.presenter.exam_day;

import android.content.Context;
import com.udit.bankexam.view_ok.exam_day.ExamDayCalenderView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ExamDayCalenderPresenter extends ExamDayCalenderView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamDayCalenderPresenter(ExamDayCalenderView.View paramView) { super(paramView); }
  
  public void getExamDay(Context paramContext, String paramString1, String paramString2) {
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
                ((ExamDayCalenderView.View)ExamDayCalenderPresenter.this.mView).setExamList(list);
              } else {
                ((ExamDayCalenderView.View)ExamDayCalenderPresenter.this.mView).setExamList(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ExamDayCalenderPresenter.this.TAG, param1String);
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
  
  public void getExamDayByTime(Context paramContext, String paramString1, String paramString2, String paramString3) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetExamDay");
      hashMap.put("uid", paramString1);
      hashMap.put("begin", paramString2);
      hashMap.put("end", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamInfoBean.class);
              if (list != null && list.size() > 0) {
                ((ExamDayCalenderView.View)ExamDayCalenderPresenter.this.mView).setExamInfo(list);
              } else {
                ((ExamDayCalenderView.View)ExamDayCalenderPresenter.this.mView).setExamInfo(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamDayCalenderView.View)ExamDayCalenderPresenter.this.mView).setExamInfo(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((ExamDayCalenderView.View)this.mView).setExamInfo(null);
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
                ((ExamDayCalenderView.View)ExamDayCalenderPresenter.this.mView).setExamTitleList(list);
              } else {
                ((ExamDayCalenderView.View)ExamDayCalenderPresenter.this.mView).setExamTitleList(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ExamDayCalenderPresenter.this.TAG, param1String);
              ((ExamDayCalenderView.View)ExamDayCalenderPresenter.this.mView).setExamTitleList(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((ExamDayCalenderView.View)this.mView).setExamTitleList(null);
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


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_day\ExamDayCalenderPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */