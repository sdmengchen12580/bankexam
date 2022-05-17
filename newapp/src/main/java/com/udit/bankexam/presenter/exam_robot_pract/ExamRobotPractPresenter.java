package com.udit.bankexam.presenter.exam_robot_pract;

import com.udit.bankexam.view_ok.exam_robot_pract.ExamRobotPractView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import java.util.HashMap;
import java.util.List;

public class ExamRobotPractPresenter extends ExamRobotPractView.Presenter {
  public ExamRobotPractPresenter(ExamRobotPractView.View paramView) { super(paramView); }
  
  public void getData() {
    hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetType");
      hashMap.put("IType", "考点");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.TypeBean.class);
              if (list != null && list.size() > 0) {
                ((ExamRobotPractView.View)ExamRobotPractPresenter.this.mView).setData(list);
                return;
              } 
              ((ExamRobotPractView.View)ExamRobotPractPresenter.this.mView).setData(null);
            }
            
            public void onError(String param1String) { ((ExamRobotPractView.View)ExamRobotPractPresenter.this.mView).setData(null); }
          });
      return;
    } catch (Exception hashMap) {
      hashMap.printStackTrace();
      ((ExamRobotPractView.View)this.mView).setData(null);
      return;
    } 
  }
  
  public void getExamList(String paramString1, String paramString2) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doNewPractList");
      hashMap.put("uid", paramString1);
      hashMap.put("QPoint", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null && list.size() > 0) {
                ((ExamRobotPractView.View)ExamRobotPractPresenter.this.mView).setExamList(list);
                return;
              } 
              ((ExamRobotPractView.View)ExamRobotPractPresenter.this.mView).setExamList(null);
            }
            
            public void onError(String param1String) { ((ExamRobotPractView.View)ExamRobotPractPresenter.this.mView).setExamList(null); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((ExamRobotPractView.View)this.mView).setExamList(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_robot_pract\ExamRobotPractPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */