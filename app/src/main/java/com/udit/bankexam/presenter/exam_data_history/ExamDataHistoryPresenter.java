package com.udit.bankexam.presenter.exam_data_history;

import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.HisPractBean;
import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_data_history.ExamDataHistoryView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamDataHistoryPresenter extends ExamDataHistoryView.Presenter {
    public ExamDataHistoryPresenter(ExamDataHistoryView.View mView) {
        super(mView);
    }

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void getHisPract(String uid) {

        try {
            HashMap<String,String> map_params = new HashMap<>();

            map_params.put(Constant.Params.ACTION, IHTTP.DOGETALLHISPRACT);
            map_params.put(Constant.Params.UID,uid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<HisPractBean> list = JsonUtil.jsonToListByArray(json,HisPractBean.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.setHisPract(list);
                    }
                    else
                        mView.setHisPract(null);

                }

                @Override
                public void onError(String errStr) {
                    mView.setHisPract(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setHisPract(null);
        }

    }

    @Override
    public void getHisExPract(String uid) {
        try {
            HashMap<String,String> map_params = new HashMap<>();

            map_params.put(Constant.Params.ACTION, IHTTP.DOGETHISEXPRACT);
            map_params.put(Constant.Params.UID,uid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<HisPractBean> list = JsonUtil.jsonToListByArray(json,HisPractBean.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.setHisExPract(list);
                    }
                    else
                        mView.setHisExPract(null);

                }

                @Override
                public void onError(String errStr) {
                    mView.setHisExPract(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setHisExPract(null);
        }
    }

    @Override
    public void getPurch(String uid) {

        try {
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION,IHTTP.DOGETPURCH);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.PTYPE,"试卷");

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<PurchBean> list = JsonUtil.jsonToListByArray(json,PurchBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setPurch(list);
                    }
                    else
                        mView.setPurch(null);
                }

                @Override
                public void onError(String errStr) {
                    mView.setPurch(null);
                }
            });
        } catch (Exception e) {
            mView.setPurch(null);
        }


    }

    @Override
    public void getExam(String uid, String eid) {
        try {
            HashMap<String,String> map_params = new HashMap<>();

            map_params.put(Constant.Params.ACTION,IHTTP.DOEXAMINTITLE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.EID,eid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamTitleBean> list =JsonUtil.jsonToListByArray(json,ExamTitleBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setExam(list);
                    }
                    else
                        mView.setExam(null);
                }

                @Override
                public void onError(String errStr) {
                    mView.setExam(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setExam(null);
        }


    }

    @Override
    public void getHisExam(String uid, String eid) {
        try {
            HashMap<String,String> map_params = new HashMap<>();

            map_params.put(Constant.Params.ACTION,IHTTP.DOGETPRACTTITLE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.PID,eid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamTitleBean> list =JsonUtil.jsonToListByArray(json,ExamTitleBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setExam(list);
                    }
                    else
                        mView.setExam(null);
                }

                @Override
                public void onError(String errStr) {
                    mView.setExam(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setExam(null);
        }

    }
}
