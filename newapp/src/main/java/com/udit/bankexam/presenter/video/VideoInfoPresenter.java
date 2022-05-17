package com.udit.bankexam.presenter.video;

import android.content.Context;
import com.udit.bankexam.view_ok.video.VideoInfoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class VideoInfoPresenter extends VideoInfoView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public VideoInfoPresenter(VideoInfoView.View paramView) { super(paramView); }
  
  public void getExamInfo(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("获取试卷中", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetExamin");
      hashMap.put("uid", paramString1);
      hashMap.put("EID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null && list.size() > 0) {
                ((VideoInfoView.View)VideoInfoPresenter.this.mView).setExamInfo(list);
              } else {
                ((VideoInfoView.View)VideoInfoPresenter.this.mView).setExamInfo(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((VideoInfoView.View)VideoInfoPresenter.this.mView).setExamInfo(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((VideoInfoView.View)this.mView).setExamInfo(null);
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


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\video\VideoInfoPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */