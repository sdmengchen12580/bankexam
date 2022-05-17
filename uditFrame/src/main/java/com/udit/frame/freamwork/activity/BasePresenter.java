package com.udit.frame.freamwork.activity;

import java.util.HashMap;

import com.udit.frame.freamwork.http.HttpTask;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.freamwork.http.RequestObject;

import android.content.Context;


public class BasePresenter<V extends IBaseView> {
    protected V mView;

    public BasePresenter(V mView) {
        super();
        this.mView = mView;
    }

    public void attachView(V view) {
        mView = view;
        onViewAttached();
    }

    /**
     * 取消View绑定
     */
    public void detachView() {
        mView = null;
    }

    protected void onViewAttached() {

    }

    protected Context getContext() {
        if (mView instanceof Context) {
            return (Context) mView;
        } else {
            throw new NullPointerException("BasePresenter:mView is't instance of Context,can't use getContext() method.");
        }
    }

    protected void setHttp(HashMap<String, String> map, String address, IHttpResponseListener listener)
            throws Exception {
        try {
            RequestObject object = new RequestObject(address, map);
            new HttpTask().start(object, HttpTask.REQUEST_TYPE_POST, listener);
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
