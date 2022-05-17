package com.udit.bankexam.presenter.zhibo;

import com.udit.bankexam.view_ok.zhibo.TeacherView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import java.util.HashMap;
import java.util.List;

public class TeacherPresenter extends TeacherView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public TeacherPresenter(TeacherView.View paramView) { super(paramView); }
  
  public void getTeacherInfo(String paramString1, String paramString2) {
    HashMap hashMap = new HashMap();
    hashMap.put("action", "doGetLiveTeche");
    hashMap.put("uid", paramString1);
    hashMap.put("LID", paramString2);
    try {
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.TeacherBean.class);
              if (list != null && list.size() > 0) {
                ((TeacherView.View)TeacherPresenter.this.mView).setTeacher(list);
                return;
              } 
              ((TeacherView.View)TeacherPresenter.this.mView).setTeacher(null);
            }
            
            public void onError(String param1String) { MyLogUtils.e(TeacherPresenter.this.TAG, param1String); }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\zhibo\TeacherPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */