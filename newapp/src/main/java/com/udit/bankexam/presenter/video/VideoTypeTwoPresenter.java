package com.udit.bankexam.presenter.video;

import com.udit.bankexam.bean_ok.VideoType;
import com.udit.bankexam.view_ok.video.VideoTypeTwoViewNew;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import java.util.HashMap;
import java.util.List;

public class VideoTypeTwoPresenter extends VideoTypeTwoViewNew.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public VideoTypeTwoPresenter(VideoTypeTwoViewNew.View paramView) { super(paramView); }
  
  public void getTypeTwo(String paramString1, String paramString2) {
    try {
      ((VideoTypeTwoViewNew.View)this.mView).showProgressDialog("");
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetVideoCatalogNew");
      hashMap.put("uid", paramString1);
      hashMap.put("VType", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToList(param1String, "list", VideoType.class);
              if (list != null && list.size() > 0) {
                ((VideoType)list.get(0)).setIsfirst(true);
                ((VideoType)list.get(list.size() - 1)).setLast(true);
                ((VideoTypeTwoViewNew.View)VideoTypeTwoPresenter.this.mView).setTypeTwo(list);
              } else {
                ((VideoTypeTwoViewNew.View)VideoTypeTwoPresenter.this.mView).setTypeTwo(null);
              } 
              ((VideoTypeTwoViewNew.View)VideoTypeTwoPresenter.this.mView).dismissProgressDialog();
            }
            
            public void onError(String param1String) {
              ((VideoTypeTwoViewNew.View)VideoTypeTwoPresenter.this.mView).setTypeTwo(null);
              ((VideoTypeTwoViewNew.View)VideoTypeTwoPresenter.this.mView).dismissProgressDialog();
            }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((VideoTypeTwoViewNew.View)this.mView).setTypeTwo(null);
      ((VideoTypeTwoViewNew.View)this.mView).dismissProgressDialog();
      return;
    } 
  }
  
  public void getTypeTwoByID(String paramString1, String paramString2) {
    try {
      ((VideoTypeTwoViewNew.View)this.mView).showProgressDialog("");
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetVideoCatalogNewByID");
      hashMap.put("uid", paramString1);
      hashMap.put("VType", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToList(param1String, "list", VideoType.class);
              if (list != null && list.size() > 0) {
                ((VideoType)list.get(0)).setIsfirst(true);
                ((VideoType)list.get(list.size() - 1)).setLast(true);
                ((VideoTypeTwoViewNew.View)VideoTypeTwoPresenter.this.mView).setTypeTwo(list);
              } else {
                ((VideoTypeTwoViewNew.View)VideoTypeTwoPresenter.this.mView).setTypeTwo(null);
              } 
              ((VideoTypeTwoViewNew.View)VideoTypeTwoPresenter.this.mView).dismissProgressDialog();
            }
            
            public void onError(String param1String) {
              ((VideoTypeTwoViewNew.View)VideoTypeTwoPresenter.this.mView).setTypeTwo(null);
              ((VideoTypeTwoViewNew.View)VideoTypeTwoPresenter.this.mView).dismissProgressDialog();
            }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((VideoTypeTwoViewNew.View)this.mView).setTypeTwo(null);
      ((VideoTypeTwoViewNew.View)this.mView).dismissProgressDialog();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\video\VideoTypeTwoPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */