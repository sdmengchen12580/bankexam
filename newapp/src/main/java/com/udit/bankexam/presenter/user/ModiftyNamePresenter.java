package com.udit.bankexam.presenter.user;

import android.content.Context;
import com.udit.bankexam.bean_ok.MsgBean;
import com.udit.bankexam.view_ok.user.ModiftyNameView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ModiftyNamePresenter extends ModiftyNameView.Presenter {
  public ModiftyNamePresenter(ModiftyNameView.View paramView) { super(paramView); }
  
  public void modiftyName(Context paramContext, String paramString1, final String pet) {
    try {
      ProgressUtils.showProgressDlg("修改昵称中", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doPutPet");
      hashMap.put("uid", paramString1);
      hashMap.put("Pet", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonArrayOk(param1String)) {
                ((ModiftyNameView.View)ModiftyNamePresenter.this.mView).modiftySuccess(pet);
              } else {
                List list = JsonUtil.jsonToListByArrayNoSucess(param1String, MsgBean.class);
                if (list != null && list.size() > 0) {
                  ((ModiftyNameView.View)ModiftyNamePresenter.this.mView).modiftyErr(((MsgBean)list.get(0)).getMsg());
                } else {
                  ((ModiftyNameView.View)ModiftyNamePresenter.this.mView).modiftyErr(null);
                } 
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ModiftyNameView.View)ModiftyNamePresenter.this.mView).modiftyErr(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((ModiftyNameView.View)this.mView).modiftyErr(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presente\\user\ModiftyNamePresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */