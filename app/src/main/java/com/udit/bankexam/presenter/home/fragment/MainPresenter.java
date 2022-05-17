package com.udit.bankexam.presenter.home.fragment;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.AdvBean;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.bean.ModuleBean;
import com.udit.bankexam.bean.ZhaoPinInfo;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.Constant.Params;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.home.fragment.MainView;
import com.udit.bankexam.view.home.fragment.MainView.View;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

public class MainPresenter extends MainView.Presenter
{
    private final String TAG = this.getClass().getSimpleName();
    
    public MainPresenter(View mView)
    {
        super(mView);
    }

    @Override
    public void getModule_net()
    {
        HashMap<String,String> map_params = new HashMap<>();
        try {
            map_params.put(Params.ACTION,IHTTP.GETMODULE);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonForOK(json))
                    {
                        List<ModuleBean> list = JsonUtil.jsonToList(json,"list",ModuleBean.class);
                        if(list!=null && list.size()>0)
                        {
                            mView.setModule(list);
                        }
                        else
                            mView.setModule(null);
                    }
                    else
                    {
                        mView.setModule(null);
                    }
                }

                @Override
                public void onError(String errStr) {
                    mView.setModule(null);
                }
            });
        } catch (Exception e) {
            mView.setModule(null);
        }
    }

    @Override
    public void getModule()
    {
        ModuleBean bean1 = new ModuleBean();
        ModuleBean bean2 = new ModuleBean();
        ModuleBean bean3 = new ModuleBean();
        ModuleBean bean4 = new ModuleBean();
        ModuleBean bean5 = new ModuleBean();
       /* ModuleBean bean6 = new ModuleBean();
        ModuleBean bean7 = new ModuleBean();
        ModuleBean bean8 = new ModuleBean();*/
        List<ModuleBean> mlist1 = new ArrayList<>();
        mlist1.add(bean1);
        mlist1.add(bean2);
        mlist1.add(bean3);
        mlist1.add(bean4);
        mlist1.add(bean5);
      /*  mlist1.add(bean6);
        mlist1.add(bean7);
        mlist1.add(bean8);*/
        
      /*  ModuleBean bean9 = new ModuleBean();
        ModuleBean bean10 = new ModuleBean();
        ModuleBean bean11 = new ModuleBean();
        ModuleBean bean12 = new ModuleBean();*/
       // ModuleBean bean13 = new ModuleBean();
      /* // ModuleBean bean14 = new ModuleBean();
        List<ModuleBean> mlist2 = new ArrayList<>();
        mlist2.add(bean9);
        mlist2.add(bean10);
        mlist2.add(bean11);
        mlist2.add(bean12);*/
        //mlist2.add(bean13);
       // mlist2.add(bean14);
        
        List<List<ModuleBean>> list = new ArrayList<>();
        list.add(mlist1);
      //  list.add(mlist2);
       
        bean1.setTitle(Constant.MainModule.MODULE_15);
        bean1.setImg(R.mipmap.nav_zuoti);
        
        bean2.setTitle(Constant.MainModule.MODULE_16);
        bean2.setImg(R.mipmap.nav_meiriyilian);
        
      /*  bean3.setTitle(Constant.MainModule.MODULE_3);
        bean3.setImg(R.mipmap.nav_zhinengzujuan);
        
        bean4.setTitle(Constant.MainModule.MODULE_4);
        bean4.setImg(R.mipmap.nav_zhinenglianxi);*/
        
        bean3.setTitle(Constant.MainModule.MODULE_5);
        bean3.setImg(R.mipmap.nav_zhenti);
        
        bean4.setTitle(Constant.MainModule.MODULE_6);
        bean4.setImg(R.mipmap.nav_mijuan);
        
        bean5.setTitle(Constant.MainModule.MODULE_7);
        bean5.setImg(R.mipmap.nav_mokao);
       /*
        bean8.setTitle(Constant.MainModule.MODULE_8);
        bean8.setImg(R.mipmap.nav_shuju);
        
        bean9.setTitle(Constant.MainModule.MODULE_9);
        bean9.setImg(R.mipmap.nav_cuotiben);
        
        bean10.setTitle(Constant.MainModule.MODULE_10);
        bean10.setImg(R.mipmap.nav_shoucang);
        
        bean11.setTitle(Constant.MainModule.MODULE_11);
        bean11.setImg(R.mipmap.nav_bijiben);
        
        bean12.setTitle(Constant.MainModule.MODULE_12);
        bean12.setImg(R.mipmap.nav_lianxilishi);*/
        
        /*bean13.setTitle(Constant.MainModule.MODULE_13);
        bean13.setImg(R.mipmap.nav_lianxibaogao);
        
        bean14.setTitle(Constant.MainModule.MODULE_14);
        bean14.setImg(R.mipmap.nav_lianxizhoubao);*/
        
       // mView.setModule(list);
        
    }

    @Override
    public void getAdv()
    {

        HashMap<String,String> map_params = new HashMap<>();
        try {
            map_params.put(Params.ACTION,IHTTP.DOGETADV);
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

    @Override
    public void getExamNode(String uid)
    {
        try
        {
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put(Params.ACTION, IHTTP.DOGETFIRSTHIS);
            map_params.put(Params.UID,uid);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener()
            {
                
                @Override
                public void onError(String errStr)
                {
                    mView.setExamNode(null);
                }
                
                @Override
                public void doHttpResponse(String json)
                {
                    if(JsonUtil.getJsonForOK(json))
                    {
                        List<ExamNodeBean> list = 
                            JsonUtil.jsonToList(json, "list", ExamNodeBean.class);
                        if(list!=null && list.size()>0)
                        {
                            mView.setExamNode(list);
                        }
                        else
                            mView.setExamNode(null);
                    
                    }
                    else
                    {
                        mView.setExamNode(null);
                    }
                }
            });
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, e.getMessage());
            mView.setExamNode(null);
        }
        
        
    }

    @Override
    public void getHomeExam(Context mContext,String uid, String tid) {
        HashMap<String,String> map_params = new HashMap<>();

        try {
            ProgressUtils.showProgressDlg("",mContext);
            map_params.put(Params.ACTION,IHTTP.DOOUTLINETITLE);
            map_params.put(Params.UID,uid);
            map_params.put(Params.OID,tid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamTitleBean> list =JsonUtil.jsonToListByArray(json, ExamTitleBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setHomeExam(list);
                    }
                    else
                    {
                        mView.setHomeExam(null);

                    }

                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.setHomeExam(null);
                    ProgressUtils.stopProgressDlg();

                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setHomeExam(null);
            ProgressUtils.stopProgressDlg();
        }

    }

    @Override
    public void getHomeSJ(String uid) {

        HashMap<String,String> map_params = new HashMap<>();

        try {
            map_params.put(Params.ACTION,IHTTP.DOGETEXAMIN);
            map_params.put(Params.UID,uid);
            map_params.put(Params.TYPEINFO,Constant.DataType.TYPE_SHOUYE);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamInfoBean> list = JsonUtil.jsonToListByArray(json,ExamInfoBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setHomeExmaSJ(list.get(0));
                    }
                    else
                    {
                        mView.setHomeExmaSJ(null);
                    }

                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.setHomeExmaSJ(null);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setHomeExmaSJ(null);

        }


    }

    @Override
    public void getHomeSJDTK(String uid, String eid) {

        HashMap<String,String> map_params = new HashMap<>();
        try {
            map_params.put(Params.ACTION,IHTTP.DOEXAMINTITLE);
            map_params.put(Params.UID,uid);
            map_params.put(Params.EID,eid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamTitleBean> list = JsonUtil.jsonToListByArray(json,ExamTitleBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setHomeExamDTK(list);
                    }
                    else
                    {
                        mView.setHomeExamDTK(null);
                    }

                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.setHomeExamDTK(null);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setHomeExamDTK(null);
        }


    }

    @Override
    public void saveShouYe(String uid,final String linkid, String name) {
        HashMap<String,String> map_params = new HashMap<>();

        map_params.put(Params.ACTION,IHTTP.DOPUTPURCH);
        map_params.put(Params.UID,uid);
        map_params.put(Params.PTYPE,Constant.DataType.TYPE_SHIJUAN);
        map_params.put(Params.FEEDATE, MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1));
        map_params.put(Params.LINKID,linkid);
        map_params.put(Params.FEE,"0");
        map_params.put(Params.ABSTRACT,name);
        map_params.put(Params.INTRO,Constant.PAY.PAY_MIANFEI);
        map_params.put(Params.PSTATE,Constant.PAY.PAY_STATUS_OK);

        try {
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamInfoBean> list = JsonUtil.jsonToListByArray(json,ExamInfoBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.saveExamShouYe(linkid);
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
    public void shoucang(String uid) {

        try {
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Params.ACTION,IHTTP.DOGETFAVORITE);
            map_params.put(Params.UID,uid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<FavoriteRecord> mlist = JsonUtil.jsonToListByArray(json,FavoriteRecord.class);
                    if(mlist!=null && mlist.size()>0)
                        mView.saveShoucang(mlist);
                    else
                        mView.saveShoucang(null);

                }

                @Override
                public void onError(String errStr) {
                    mView.saveShoucang(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.saveShoucang(null);
        }

    }

    @Override
    public void checkHomeNode(String uid, String oid) {

        try {
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Params.ACTION,IHTTP.DOFREOUTLINE);
            map_params.put(Params.UID,uid);
            map_params.put(Params.OID,oid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonForOK(json))
                    {
                         List<ExamNodeBean> list =  JsonUtil.jsonToList(json,"list",ExamNodeBean.class);
                         if(list!=null && list.size()>0)
                         {
                             mView.checkHomeNode(list);
                         }

                    }
                }

                @Override
                public void onError(String errStr) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getTypeOne(String paramString) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("action", "doGetVideoTypeNew");
            hashMap.put("uid", paramString);
            setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
                public void doHttpResponse(String param1String) {
                    List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean.VideoTypeOneBean.class);
                    if (list != null && list.size() > 0) {
                        ((MainView.View)MainPresenter.this.mView).setTypeOne(list);
                        return;
                    }
                    ((MainView.View)MainPresenter.this.mView).setTypeOne(null);
                }

                public void onError(String param1String) { ((MainView.View)MainPresenter.this.mView).setTypeOne(null); }
            });
            return;
        } catch (Exception context) {
            MyLogUtils.e(this.TAG, context.getMessage());
            ((MainView.View)this.mView).setTypeOne(null);
            return;
        }
    }

    @Override
    public void getZpList() {
        HashMap hashMap = new HashMap();
        try {//http://yhyk.project.njagan.org//
            setHttp(hashMap, IHTTP.UP_GET_ZZ_INFO, new IHttpResponseListener() {
                public void doHttpResponse(String param1String) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("date=");
                    stringBuilder.append(param1String);
                    Log.e("doHttpResponse: ", stringBuilder.toString());
                    ZhaoPinInfo zhaoPinInfo = (ZhaoPinInfo)(new Gson()).fromJson(param1String, ZhaoPinInfo.class);
                    if (zhaoPinInfo.getCode() == 200) {
                        ((MainView.View)MainPresenter.this.mView).setWork(zhaoPinInfo.getData().getResponse().getZhaopins(),
                                zhaoPinInfo.getData().getResponse().getZhaopinsMoreUrl());
                        return;
                    }
                    ((MainView.View)MainPresenter.this.mView).setWork(null,null);
                }

                public void onError(String param1String) { ((MainView.View)MainPresenter.this.mView).setWork(null,null); }
            });
            return;
        } catch (Exception content) {
            content.printStackTrace();
            ((MainView.View)this.mView).setWork(null,null);
            return;
        }
    }
}
