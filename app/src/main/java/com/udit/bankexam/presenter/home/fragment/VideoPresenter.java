package com.udit.bankexam.presenter.home.fragment;

import android.content.Context;

import com.udit.bankexam.bean.MKBean;
import com.udit.bankexam.bean.VideoTypeOneBean;
import com.udit.bankexam.bean.VideoTypeTwoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.home.fragment.VideoView;
import com.udit.bankexam.view.home.fragment.VideoView.View;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

public class VideoPresenter extends VideoView.Presenter
{
    private final String TAG = this.getClass().getSimpleName();


    public VideoPresenter(View mView)
    {
        super(mView);
    }


    @Override
    public void getTypeList(String uid, String typeOne) {
        try {
            mView.showProgressDialog("");
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETVIDEOCATALOG);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.VTYPE,typeOne);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {

                    List<VideoTypeTwoBean> list =  JsonUtil.jsonToListByArray(json, VideoTypeTwoBean.class);
                    if(list!=null &&list.size()>0)
                    {
                        mView.setTypeList(list);
                    }
                    else
                        mView.setTypeList(null);

                    mView.dismissProgressDialog();
                }

                @Override
                public void onError(String errStr) {
                    mView.setTypeList(null);
                    mView.dismissProgressDialog();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setTypeList(null);
            mView.dismissProgressDialog();

        }
    }

    @Override
    public void getTypeOne(String uid) {
        try {
            mView.showProgressDialog("");
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.doGetVideoTypeNew);
            map_params.put(Constant.Params.UID,uid);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {

                    List<VideoTypeOneBean> list =  JsonUtil.jsonToListByArray(json, VideoTypeOneBean.class);
                    if(list!=null &&list.size()>0)
                    {
                        mView.setTypeOne(list);
                    }
                    else
                        mView.setTypeOne(null);
                    mView.dismissProgressDialog();
                }

                @Override
                public void onError(String errStr) {
                    mView.setTypeOne(null);
                    mView.dismissProgressDialog();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setTypeOne(null);
            mView.dismissProgressDialog();

        }
    }

    @Override
    public void getTypeList(String uid, String eid, String type) {
        try
        {
        HashMap<String,String> map_params = new HashMap<>();
        map_params.put(Constant.Params.ACTION, IHTTP.DOGETADVID);
        map_params.put(Constant.Params.UID,uid);
        map_params.put(Constant.Params.PATH,eid);
        map_params.put(Constant.Params.TITLE,type);

        setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
            @Override
            public void doHttpResponse(String json) {
                List<VideoTypeTwoBean> list = JsonUtil.jsonToListByArray(json,VideoTypeTwoBean.class);
                if(list!=null && list.size()>0)
                {
                    mView.setTypeTwo(list.get(0));
                }
                else
                {
                    mView.setTypeTwo(null);
                }
            }

            @Override
            public void onError(String errStr) {
                MyLogUtils.e(TAG,errStr);
                mView.setTypeTwo(null);
                ProgressUtils.stopProgressDlg();
            }
        });
        }
        catch (Exception e) {
        MyLogUtils.e(TAG,e.getMessage());
        mView.setTypeTwo(null);
    }
    }


}
