package com.udit.bankexam.presenter.video;

import com.udit.bankexam.view_ok.video.VideoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import java.util.HashMap;
import java.util.List;

public class VideoPresenter extends VideoView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public VideoPresenter(VideoView.View paramView) { super(paramView); }
  
  public void getVideList(String paramString1, String paramString2) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetVideo");
      hashMap.put("uid", paramString1);
      hashMap.put("cID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              MyLogUtils.e(VideoPresenter.this.TAG, param1String);
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.VideoBean.class);
              if (list != null && list.size() > 0) {
                ((VideoView.View)VideoPresenter.this.mView).setVideo(list);
                return;
              } 
              ((VideoView.View)VideoPresenter.this.mView).setVideo(null);
            }
            
            public void onError(String param1String) { ((VideoView.View)VideoPresenter.this.mView).setVideo(null); }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((VideoView.View)this.mView).setVideo(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\video\VideoPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */