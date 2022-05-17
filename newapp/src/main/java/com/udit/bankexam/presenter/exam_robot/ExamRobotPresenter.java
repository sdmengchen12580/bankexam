package com.udit.bankexam.presenter.exam_robot;

import android.content.Context;
import com.udit.bankexam.view_ok.exam_robot.ExamRobotView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ExamRobotPresenter extends ExamRobotView.Presenter {
  public ExamRobotPresenter(ExamRobotView.View paramView) { super(paramView); }
  
  public void getData() {
    hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetType");
      hashMap.put("IType", "考试内容");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.TypeBean.class);
              if (list != null && list.size() > 0) {
                ((ExamRobotView.View)ExamRobotPresenter.this.mView).setData(list);
                return;
              } 
              ((ExamRobotView.View)ExamRobotPresenter.this.mView).setData(null);
            }
            
            public void onError(String param1String) { ((ExamRobotView.View)ExamRobotPresenter.this.mView).setData(null); }
          });
      return;
    } catch (Exception hashMap) {
      hashMap.printStackTrace();
      ((ExamRobotView.View)this.mView).setData(null);
      return;
    } 
  }
  
  public void getExamHis(Context paramContext, String paramString) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetHisExPract");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.HisPractBean.class);
              if (list != null && list.size() > 0) {
                ((ExamRobotView.View)ExamRobotPresenter.this.mView).setHisExam(list);
              } else {
                ((ExamRobotView.View)ExamRobotPresenter.this.mView).setHisExam(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamRobotView.View)ExamRobotPresenter.this.mView).setHisExam(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      paramContext.printStackTrace();
      ((ExamRobotView.View)this.mView).setHisExam(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void getExamList(Context paramContext, String paramString1, String paramString2) {
    ProgressUtils.showProgressDlg("", paramContext);
    hashMap = new HashMap();
    try {
      hashMap.put("action", "doNewExPractList");
      hashMap.put("uid", paramString1);
      hashMap.put("Content", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null && list.size() > 0) {
                ((ExamRobotView.View)ExamRobotPresenter.this.mView).setExamList(list);
              } else {
                ((ExamRobotView.View)ExamRobotPresenter.this.mView).setExamList(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamRobotView.View)ExamRobotPresenter.this.mView).setExamList(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception hashMap) {
      hashMap.printStackTrace();
      ((ExamRobotView.View)this.mView).setExamList(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void getHisExamList(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetPractTitle");
      hashMap.put("uid", paramString1);
      hashMap.put("PID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null && list.size() > 0) {
                ((ExamRobotView.View)ExamRobotPresenter.this.mView).setExamList(list);
              } else {
                ((ExamRobotView.View)ExamRobotPresenter.this.mView).setExamList(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamRobotView.View)ExamRobotPresenter.this.mView).setExamList(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      paramContext.printStackTrace();
      ((ExamRobotView.View)this.mView).setExamList(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_robot\ExamRobotPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */