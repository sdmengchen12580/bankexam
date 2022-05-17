package com.udit.bankexam.presenter.exam_mk;

import android.content.Context;
import com.udit.bankexam.view_ok.exam_mk.ExamMkView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ExamMkPresenter extends ExamMkView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamMkPresenter(ExamMkView.View paramView) { super(paramView); }
  
  public void getMK(Context paramContext, String paramString) {
    try {
      ProgressUtils.showProgressDlg("获取模考大赛信息", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetAllMock");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.MKBean.class);
              if (list != null && list.size() > 0) {
                ((ExamMkView.View)ExamMkPresenter.this.mView).setMkList(list);
              } else {
                ((ExamMkView.View)ExamMkPresenter.this.mView).setMkList(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamMkView.View)ExamMkPresenter.this.mView).setMkList(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((ExamMkView.View)this.mView).setMkList(null);
      ((ExamMkView.View)this.mView).showLongToast("网络异常，请检查网络后操作");
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_mk\ExamMkPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */