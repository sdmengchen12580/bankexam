package com.udit.bankexam.presenter.user;

import android.content.Context;

import com.udit.bankexam.bean.AddressBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.user.UserInfoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/4.
 */

public class UserInfoPresenter extends UserInfoView.UserInfoPresenter {
    public UserInfoPresenter(UserInfoView.View mView) {
        super(mView);
    }

    @Override
    public void doGetAddr(Context context, String uid) {
        try {
            ProgressUtils.showProgressDlg("",context);
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETADDR);

            map_params.put(Constant.Params.UID,uid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<AddressBean> list = JsonUtil.jsonToListByArray(json,AddressBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.getAddr(list.get(0));
                    }
                    else
                        mView.getAddr(null);

                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.getAddr(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            mView.getAddr(null);

            ProgressUtils.stopProgressDlg();
        }
    }
}
