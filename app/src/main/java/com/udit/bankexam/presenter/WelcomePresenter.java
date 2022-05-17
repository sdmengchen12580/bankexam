package com.udit.bankexam.presenter;

import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.WelcomeView.View;
import com.udit.bankexam.view.WelcomeView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;

import java.util.HashMap;
import java.util.List;

public class WelcomePresenter extends WelcomeView.Presenter {


    public WelcomePresenter(View mView) {
        super(mView);
    }

    @Override
    public void getWelcome() {
        HashMap<String, String> map_params = new HashMap<>();
        try {
            map_params.put(Constant.Params.ACTION, IHTTP.GETBOOTSCROLLPICS);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<String> list = JsonUtil.jsonToListByValue(json, "pic_url");
                    if (list != null) {
                        mView.setWelcome(list);
                    } else {
                        mView.setWelcome(null);
                    }
                }

                @Override
                public void onError(String errStr) {
                    mView.setWelcome(null);
                }
            });
        } catch (Exception e) {
            mView.setWelcome(null);
        }

    }
}
