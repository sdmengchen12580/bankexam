package com.udit.bankexam.presenter.user;

import android.content.Context;

import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.user.ModiftyNameView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/4.
 */

public class ModiftyNamePresenter extends ModiftyNameView.Presenter {
    public ModiftyNamePresenter(ModiftyNameView.View mView) {
        super(mView);
    }

    @Override
    public void modiftyName(Context mContext, String uid, final String pet) {

        try {
            ProgressUtils.showProgressDlg("修改昵称中",mContext);
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOPUTPET);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.PET,pet);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonArrayOk(json))
                    {
                        mView.modiftySuccess(pet);
                    }
                    else
                    {
                        List<MsgBean> list =  JsonUtil.jsonToListByArrayNoSucess(json, MsgBean.class);
                        if(list!=null && list.size()>0)
                             mView.modiftyErr(list.get(0).getMsg());
                        else
                            mView.modiftyErr(null);
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
