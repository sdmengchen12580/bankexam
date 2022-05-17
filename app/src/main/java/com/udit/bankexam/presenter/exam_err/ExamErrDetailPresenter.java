package com.udit.bankexam.presenter.exam_err;

import android.content.Context;

import com.udit.bankexam.bean.AdvBean;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_err.ExamErrDetailView;
import com.udit.bankexam.view.exam_err.ExamErrView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamErrDetailPresenter extends ExamErrDetailView.Presenter {

    private final String TAG = this.getClass().getSimpleName();

    public ExamErrDetailPresenter(ExamErrDetailView.View mView) {
        super(mView);
    }


    @Override
    public void getSJDetail(String uid, String eid) {
        HashMap<String,String> map_params = new HashMap<>();

        try {
            map_params.put(Constant.Params.ACTION,IHTTP.DOEXAMINTITLE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.EID,eid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamTitleBean> list = JsonUtil.jsonToListByArray(json,ExamTitleBean.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.setSJDetail(list);
                    }
                    else
                    {
                        mView.setSJDetail(null);
                    }

                }

                @Override
                public void onError(String errStr) {
                    mView.setSJDetail(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setSJDetail(null);
        }
    }

    @Override
    public void getHomeDetail(String uid, String eid) {

        HashMap<String,String> map_params = new HashMap<>();


        try {
            map_params.put(Constant.Params.ACTION,IHTTP.DOGETERRTITLE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.EID,eid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamTitleBean> list = JsonUtil.jsonToListByArray(json,ExamTitleBean.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.setSJDetail(list);
                    }
                    else
                    {
                        mView.setSJDetail(null);
                    }

                }

                @Override
                public void onError(String errStr) {
                    mView.setSJDetail(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setSJDetail(null);
        }
    }

    @Override
    public void getExamTitles(String uid, String eid) {

        try {
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION,IHTTP.DOEXAMINTITLE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.EID,eid);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamTitleBean> list = JsonUtil.jsonToListByArray(json,ExamTitleBean.class);
                    if(list!=null && list.size()>0)
                        mView.setSJDetail(list);
                    else
                        mView.setSJDetail(null);
                }

                @Override
                public void onError(String errStr) {
                    mView.setSJDetail(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setSJDetail(null);
        }

    }

    @Override
    public void getZhinengTitles(Context mContext,String uid,String ids) {

        try {
            ProgressUtils.showProgressDlg("",mContext);
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION,IHTTP.DOGETPRACTTITLES);
            map_params.put(Constant.Params.IDLIST,ids);
            map_params.put(Constant.Params.UID,uid);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonForOK(json))
                    {
                        List<ExamBean> mlist = JsonUtil.jsonToList(json,"list",ExamBean.class);
                        if(mlist!=null && mlist.size()>0)
                        {
                            mView.setSJ(mlist);
                        }
                        else
                            mView.setSJ(null);

                    }
                    else
                        mView.setSJ(null);

                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setSJ(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setSJ(null);
            ProgressUtils.stopProgressDlg();
        }

    }

    @Override
    public void getExamTtils(Context mContext,String uid, String ids) {

        try {
            ProgressUtils.showProgressDlg("",mContext);
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION,IHTTP.DOGETEXAMINTITLES);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.IDLIST,ids);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonForOK(json))
                    {
                        List<ExamBean> mlist = JsonUtil.jsonToList(json,"list",ExamBean.class);
                        if(mlist!=null && mlist.size()>0)
                        {
                            mView.setSJ(mlist);
                        }
                        else
                            mView.setSJ(null);

                    }
                    else
                        mView.setSJ(null);

                   ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setSJ(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            mView.setSJ(null);
            ProgressUtils.stopProgressDlg();
        }
    }

    @Override
    public void getAdv() {
        HashMap<String,String> map_params = new HashMap<>();
        try {
            map_params.put(Constant.Params.ACTION,IHTTP.DOGETADV);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonForOK(json))
                    {
                        List<AdvBean> list = JsonUtil.jsonToList(json,"list",AdvBean.class);
                        if(list!=null && list.size()>0)
                        {
                            mView.setAdv(list);
                        }
                        else
                            mView.setAdv(null);
                    }
                    else
                    {
                        mView.setAdv(null);
                    }
                }

                @Override
                public void onError(String errStr) {
                    mView.setAdv(null);
                }
            });
        } catch (Exception e) {
            mView.setAdv(null);
        }
    }


}
