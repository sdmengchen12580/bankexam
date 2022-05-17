package com.udit.bankexam.presenter.exam_mk;

import android.content.Context;

import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.MKBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_mk.ExamMkDetailView;
import com.udit.bankexam.view.exam_mk.ExamMkView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/6/5.
 */

public class ExamMkDetailPresneter extends ExamMkDetailView.Presenter {

    private final String TAG = this.getClass().getSimpleName();

    public ExamMkDetailPresneter(ExamMkDetailView.View mView) {
        super(mView);
    }


    public void saveExam(String uid, String linkid, String name) {
        HashMap<String,String> map_params = new HashMap<>();

        map_params.put(Constant.Params.ACTION,IHTTP.DOPUTPURCH);
        map_params.put(Constant.Params.UID,uid);
        map_params.put(Constant.Params.PTYPE,Constant.DataType.TYPE_SHIJUAN_MK);
        map_params.put(Constant.Params.FEEDATE, MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1));
        map_params.put(Constant.Params.LINKID,linkid);
        map_params.put(Constant.Params.FEE,"0");
        map_params.put(Constant.Params.ABSTRACT,name);
        map_params.put(Constant.Params.INTRO,Constant.PAY.PAY_MIANFEI);
        map_params.put(Constant.Params.PSTATE,Constant.PAY.PAY_STATUS_OK);

        try {
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {

                }

                @Override
                public void onError(String errStr) {

                }
            });
        } catch (Exception e) {

        }
    }


    @Override
    public void MkSign(Context mContext, String uid, String eid) {
        try {

            ProgressUtils.showProgressDlg("正在报名",mContext);
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOPUTSIGN);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.MOCKID,eid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonArrayOk(json))
                    {
                        mView.setMkSignOk();
                    }
                    else
                    {
                        mView.setMkSignErr();
                    }
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.setMkSignErr();
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setMkSignErr();
            ProgressUtils.stopProgressDlg();
        }

    }

    @Override
    public void getMKDetail(Context mContext, String uid, String eid, String type) {
        try {

            ProgressUtils.showProgressDlg("",mContext);
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETADVID);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.PATH,eid);
            map_params.put(Constant.Params.TITLE,type);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<MKBean> list = JsonUtil.jsonToListByArray(json,MKBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setMKDetail(list.get(0));
                    }
                    else
                    {
                        mView.setMKDetail(null);
                    }
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.setMKDetail(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setMKDetail(null);
            ProgressUtils.stopProgressDlg();
        }
    }

    @Override
    public void getExam(Context mContext, String uid, String eid) {
        HashMap<String,String> map_params = new HashMap<>();
        try {
            ProgressUtils.showProgressDlg("获取题目中",mContext);
            map_params.put(Constant.Params.ACTION,IHTTP.DOEXAMINTITLE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.EID,eid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamTitleBean> list = JsonUtil.jsonToListByArray(json,ExamTitleBean.class);
                    if(list!=null)
                    {
                        mView.setExamTitleList(list);
                    }
                    else
                        mView.setExamTitleList(null);

                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.setExamTitleList(null);

                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setExamTitleList(null);

            ProgressUtils.stopProgressDlg();
        }
    }
}
