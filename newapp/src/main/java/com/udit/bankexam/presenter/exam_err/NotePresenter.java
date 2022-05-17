package com.udit.bankexam.presenter.exam_err;

import com.udit.bankexam.bean_ok.ExamNoteBean;
import com.udit.bankexam.view_ok.exam_err.NoteView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import java.util.HashMap;
import java.util.List;

public class NotePresenter extends NoteView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public NotePresenter(NoteView.View paramView) { super(paramView); }
  
  public void setBJ(String paramString1, String paramString2, String paramString3) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doPutNote");
      hashMap.put("uid", paramString1);
      hashMap.put("ID", paramString2);
      hashMap.put("nDate", MyDateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
      hashMap.put("Note", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, ExamNoteBean.class);
              if (list != null && list.size() > 0) {
                ((NoteView.View)NotePresenter.this.mView).setBJ((ExamNoteBean)list.get(0));
                return;
              } 
              ((NoteView.View)NotePresenter.this.mView).setBJ(null);
            }
            
            public void onError(String param1String) { ((NoteView.View)NotePresenter.this.mView).setBJ(null); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((NoteView.View)this.mView).setBJ(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_err\NotePresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */