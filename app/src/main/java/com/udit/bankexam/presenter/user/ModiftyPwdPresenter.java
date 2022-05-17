package com.udit.bankexam.presenter.user;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.udit.bankexam.bean.ErrorBean;
import com.udit.bankexam.bean.UpImgBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.user.ModiftyPwdView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;

/**
 * Created by zb on 2017/5/4.
 */

public class ModiftyPwdPresenter extends ModiftyPwdView.Presenter {
    public ModiftyPwdPresenter(ModiftyPwdView.View mView) {
        super(mView);
    }

    @Override
    public void modiftyPwd(Context mContext, String uid, final String pwd, final String oldpAS) {

        try {
            ProgressUtils.showProgressDlg("修改密码中", mContext);
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOUPPASS);
            map_params.put(Constant.Params.UID, uid);
            map_params.put(Constant.Params.PASS, pwd);
            map_params.put("passOld", oldpAS);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if (JsonUtil.getJsonArrayOk(json)) {
                        mView.modiftySucess(pwd);
                    } else {
//                        [{"result":"fail","msg":"您输入的密码错误，请重新输入"}]
                        json = json.substring(1);
                        json = json.substring(0, json.length() - 1);
                        Log.e("json: ", "json=" + json);
                        ErrorBean bean = new Gson().fromJson(json, ErrorBean.class);
                        mView.modiftyErr(bean.getMsg());
                    }
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.modiftyErr(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            mView.modiftyErr(null);
            ProgressUtils.stopProgressDlg();
        }


    }
}
