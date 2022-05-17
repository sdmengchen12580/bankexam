package com.udit.bankexam.presenter.exam_day;

import com.udit.bankexam.bean.PayInfo;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_day.ExamDayDetailView;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;

import java.util.HashMap;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamDayDetailPresenter extends ExamDayDetailView.Presenter {


    public ExamDayDetailPresenter(ExamDayDetailView.View mView) {
        super(mView);
    }

    @Override
    public void setPay(String uid,String type,String linkid) {
        HashMap<String,String> map_params = new HashMap<>();
        try {
            map_params.put(Constant.Params.ACTION, IHTTP.DOPUTPURCH);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.PTYPE,type);
            map_params.put(Constant.Params.FEEDATE, MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1));
            map_params.put(Constant.Params.LINKID,linkid);
            map_params.put(Constant.Params.ABSTRACT,"");
            map_params.put(Constant.Params.FEE,"0");
            map_params.put(Constant.Params.INTRO,"免费");

            map_params.put(Constant.Params.PSTATE,"已支付");

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonArrayOk(json))
                    {
                        mView.setPayOk();
                    }
                    else
                    {
                        mView.setPayErr();
                    }
                }

                @Override
                public void onError(String errStr) {
                    mView.setPayErr();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setPayErr();
        }

    }

    @Override
    public void isPay(String uid, String id, String type) {
        HashMap<String,String> map_params = new HashMap<>();
        try {
            map_params.put(Constant.Params.ACTION,IHTTP.DOISPAY);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.ID,id);
            map_params.put(Constant.Params.TYPE,type);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonArrayOk(json))
                    {
                        mView.payOk();
                    }
                    else
                        mView.payErr();
                }

                @Override
                public void onError(String errStr) {
                    mView.payErr();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.payErr();
        }
    }


}
