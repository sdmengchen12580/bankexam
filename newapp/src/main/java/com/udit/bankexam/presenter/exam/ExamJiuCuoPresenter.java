package com.udit.bankexam.presenter.exam;

import android.content.Context;
import com.udit.bankexam.view_ok.exam.ExamJiuCuoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;

public class ExamJiuCuoPresenter extends ExamJiuCuoView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ExamJiuCuoPresenter(ExamJiuCuoView.View paramView) { super(paramView); }
  
  public void jiucuo(Context paramContext, String paramString1, String paramString2, String paramString3) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doPutExaminTitleErr");
      hashMap.put("uid", paramString1);
      hashMap.put("ID", paramString2);
      hashMap.put("intro", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonArrayOk(param1String)) {
                ((ExamJiuCuoView.View)ExamJiuCuoPresenter.this.mView).setJiCuo(true);
              } else {
                ((ExamJiuCuoView.View)ExamJiuCuoPresenter.this.mView).setJiCuo(false);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamJiuCuoView.View)ExamJiuCuoPresenter.this.mView).setJiCuo(false);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((ExamJiuCuoView.View)this.mView).setJiCuo(false);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam\ExamJiuCuoPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */