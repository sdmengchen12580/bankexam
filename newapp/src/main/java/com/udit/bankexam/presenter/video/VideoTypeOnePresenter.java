package com.udit.bankexam.presenter.video;

import com.udit.bankexam.view_ok.video.VideoTypeOneView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import java.util.HashMap;
import java.util.List;

public class VideoTypeOnePresenter extends VideoTypeOneView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public VideoTypeOnePresenter(VideoTypeOneView.View paramView) { super(paramView); }
  
  public void getTypeOne(String paramString) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetVideoType");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByValue(param1String, "VType");
              if (list != null && list.size() > 0) {
                ((VideoTypeOneView.View)VideoTypeOnePresenter.this.mView).setTypeOne(list);
                return;
              } 
              ((VideoTypeOneView.View)VideoTypeOnePresenter.this.mView).setTypeOne(null);
            }
            
            public void onError(String param1String) { ((VideoTypeOneView.View)VideoTypeOnePresenter.this.mView).setTypeOne(null); }
          });
      return;
    } catch (Exception paramString) {
      MyLogUtils.e(this.TAG, paramString.getMessage());
      ((VideoTypeOneView.View)this.mView).setTypeOne(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\video\VideoTypeOnePresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */