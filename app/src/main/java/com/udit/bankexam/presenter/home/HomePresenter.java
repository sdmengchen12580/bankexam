package com.udit.bankexam.presenter.home;

import com.udit.bankexam.bean.AppParams;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.home.HomeView;
import com.udit.bankexam.view.home.HomeView.View;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.freamwork.updateManager.AppVersion;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;

import java.util.HashMap;
import java.util.List;

public class HomePresenter extends HomeView.Presenter
{
    private final String TAG = this.getClass().getSimpleName();
    public HomePresenter(View mView)
    {
        super(mView);
    }

    @Override
    public void getParams() {
        try {
            HashMap<String,String> map_params= new HashMap<>();

            map_params.put(Constant.Params.ACTION,IHTTP.DOGETPARAM);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                  List<AppParams> list =  JsonUtil.jsonToListByArray(json, AppParams.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.saveParams(list.get(0));
                    }
                }

                @Override
                public void onError(String errStr) {

                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());

        }

    }

    @Override
    public void updateApp() {
        try {
        HashMap<String,String> map_params = new HashMap<>();
        map_params.put(Constant.Params.ACTION,IHTTP.ANDROIDUPDATE);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonForOK(json))
                    {
                        AppVersion appVersion = JsonUtil.jsonToObject(json,AppVersion.class,"AppVersion");
                        if(appVersion!=null)
                        {
                            mView.updateApp(appVersion);
                        }
                    }
                }

                @Override
                public void onError(String errStr) {

                }
            });
        } catch (Exception e) {

        }


    }

    @Override
    public void updateUserToken(String uid, String token) {
        try {
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION,IHTTP.DOPUTMSGTOKEN);

            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.TOKEN,token);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e(TAG,json);
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
        }


    }


}
