package com.udit.bankexam.presenter.zhibo;


import android.content.Context;

import com.udit.bankexam.bean.MKBean;
import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.zhibo.KeChengDetailView;
import com.udit.bankexam.view.zhibo.KeChengDetailView.View;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

public class KeChengDetailPresenter extends KeChengDetailView.Presenter
{
    private final String TAG = this.getClass().getSimpleName();


    public KeChengDetailPresenter(View mView)
    {
        super(mView);
    }

    @Override
    public void doPutFavorite(String uid, String type, String id, String time) {
        HashMap<String,String> map_params = new HashMap<>();

        try {
            map_params.put(Constant.Params.ACTION, IHTTP.DOPUTFAVORITE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.FTYPE,type);
            map_params.put(Constant.Params.ATIME,time);
            map_params.put(Constant.Params.LINKID,id);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                   List<FavoriteRecord> list =
                           JsonUtil.jsonToListByArray(json, FavoriteRecord.class);
                    if(list!=null && list.size()>0)
                    {
                        //TODO 收藏存库
                        mView.setFavorite(list);
                    }
                    else
                        mView.setFavorite(null);
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.setFavorite(null);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setFavorite(null);
        }


    }

    @Override
    public void doDelFavorite(String uid, String type, String id) {
        HashMap<String,String> map_params = new HashMap<>();

        try {
            map_params.put(Constant.Params.ACTION, IHTTP.DODELFAVORITE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.FTYPE,type);
            map_params.put(Constant.Params.LINKID,id);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<FavoriteRecord> list =
                            JsonUtil.jsonToListByArray(json, FavoriteRecord.class);
                    if(list!=null && list.size()>0)
                    {
                        //TODO 去除库存
                        mView.setDelFavorite(list);
                    }
                    else
                    {
                        mView.setDelFavorite(null);
                    }
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.setDelFavorite(null);

                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setDelFavorite(null);
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

    @Override
    public void pay(String uid, String linkid, String name) {
        HashMap<String,String> map_params = new HashMap<>();

        map_params.put(Constant.Params.ACTION,IHTTP.DOPUTPURCH);
        map_params.put(Constant.Params.UID,uid);
        map_params.put(Constant.Params.PTYPE,Constant.DataType.TYPE_ZHIBO);
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
                    mView.baomingOk();
                }

                @Override
                public void onError(String errStr) {
                    mView.baomingErr();
                }
            });
        } catch (Exception e) {
            mView.baomingErr();
        }
    }

    @Override
    public void getZhiboDetail(Context mContext, String uid, String eid, String type) {
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
                    List<ZhiBoBean> list = JsonUtil.jsonToListByArray(json,ZhiBoBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setZhiboDetail(list.get(0));
                    }
                    else
                    {
                        mView.setZhiboDetail(null);
                    }
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.setZhiboDetail(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setZhiboDetail(null);
            ProgressUtils.stopProgressDlg();
        }
    }


}
