package com.udit.bankexam.presenter.exam_day;


import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.utils.JsonUtils;
import com.udit.bankexam.view.exam_day.QianDaoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;


/**
 * Created by zb on 2017/5/12.
 */

public class QianDaoPresenter extends QianDaoView.Presenter {

    private final String TAG = this.getClass().getSimpleName();

    public QianDaoPresenter(QianDaoView.View mView) {
        super(mView);
    }

    @Override
    public void login(String phone, String pwd) {
        try {
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOLOGIN);
            map_params.put(Constant.Params.MOBILE, phone);
            map_params.put(Constant.Params.PASS, pwd);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void onError(String errStr) {

                }

                @Override
                public void doHttpResponse(String json) {
                    UserBean bean = JsonUtils.parseUser(json);
                    if (bean != null) {
                        mView.loginOk(bean);
                    } else {

                    }
                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void doCard(String uid) {
        HashMap<String, String> map_params = new HashMap<>();
        map_params.put(Constant.Params.ACTION, IHTTP.DOCARD);
        map_params.put(Constant.Params.UID, uid);


        try {
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    UserBean bean = JsonUtils.parseUser(json);
                    if (bean != null) {
                        mView.qiandao(bean);
                    } else {
                        mView.qiandao(null);
                    }
                }

                @Override
                public void onError(String errStr) {
                    mView.qiandao(null);
                }
            });
        } catch (Exception e) {
            mView.qiandao(null);
        }
    }


}
