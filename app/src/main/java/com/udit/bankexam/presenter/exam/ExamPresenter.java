package com.udit.bankexam.presenter.exam;


import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alipay.android.phone.mrpc.core.NetworkUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamOptionBean;
import com.udit.bankexam.bean.ExamTitle;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;

import com.udit.bankexam.utils.ExamUtils;
import com.udit.bankexam.view.exam.ExamView;
import com.udit.bankexam.view.exam.ExamView.View;
import com.udit.frame.freamwork.activity.BaseApplication;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.MyNetUtils;
import com.udit.frame.utils.ProgressUtils;
import com.udit.frame.utils.ToastUtil;
import com.udit.frame.utils.Utils;

import org.json.JSONArray;

public class ExamPresenter extends ExamView.Presenter {
    private final String TAG = this.getClass().getSimpleName();

    public ExamPresenter(View mView) {
        super(mView);

    }

    @Override
    public void getExamList(Context mContext,String list, String uid) {
        HashMap<String, String> map_params = new HashMap<>();

        try {
            ProgressUtils.showProgressDlg("加载试题中",mContext);
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETEXAMINTITLES);
            map_params.put(Constant.Params.IDLIST, list);
            map_params.put(Constant.Params.UID, uid);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if (JsonUtil.getJsonForOK(json)) {
                        List<ExamBean> mlist = JsonUtil.jsonToList(json, "list", ExamBean.class);

                        if (mlist != null && mlist.size() > 0)
                            mView.setExamList(mlist);
                        else
                            mView.setExamList(null);
                    } else {
                        mView.setExamList(null);
                    }
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG, errStr);
                    mView.setExamList(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG, e.getMessage());
            mView.setExamList(null);
            mView.showLongToast("网络异常，请检查网络后操作");
            ProgressUtils.stopProgressDlg();
        }

    }

    @Override
    public void getRobotExamList(Context mContext,String list, String uid) {
        HashMap<String, String> map_params = new HashMap<>();
        ProgressUtils.showProgressDlg("加载试题中",mContext);
        try {
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETPRACTTITLES);
            map_params.put(Constant.Params.IDLIST, list);
            map_params.put(Constant.Params.UID, uid);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if (JsonUtil.getJsonForOK(json)) {
                        List<ExamBean> mlist = JsonUtil.jsonToList(json, "list", ExamBean.class);

                        if (mlist != null && mlist.size() > 0)
                            mView.setExamList(mlist);
                        else
                            mView.setExamList(null);
                    } else {
                        mView.setExamList(null);
                    }
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG, errStr);
                    mView.setExamList(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG, e.getMessage());
            mView.setExamList(null);
            mView.showLongToast("网络异常，请检查网络后操作");
            ProgressUtils.stopProgressDlg();
        }
    }

    @Override
    public void setSolution(Context mContext, String uid, final String slist) {

        ProgressUtils.showProgressDlg("正在提交中", mContext);



        HashMap<String, String> map_params = new HashMap<>();
        try {

            map_params.put(Constant.Params.UID, uid);
            map_params.put(Constant.Params.SLIST, slist);
            map_params.put(Constant.Params.ACTION, IHTTP.DOPUTEXAMINSCANTRON);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if (JsonUtil.getJsonArrayOk(json)) {
                        mView.setSolutionOK();
                    } else {
                        mView.setSolutionErr();
                    }
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG, errStr);
                    mView.setSolutionErr();
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG, e.getMessage());
            mView.setSolutionErr();
            mView.showLongToast("网络异常，请检查网络后操作");
            ProgressUtils.stopProgressDlg();

        }


    }

    @Override
    public void setSolutionList(Context mContext, String uid, List<ExamBean> list) {
             ProgressUtils.showProgressDlg("正在提交中", mContext);
            boolean flags =  MyNetUtils.checkNetwork(BaseApplication.getInstance());
            if(!flags)
            {
                mView.showLongToast("网络异常，请检查网络");
                return;
            }
            final  List<String> list_ids =  ExamUtils.getExamBeanLists(list,50);
            for(int i = 0;i<list_ids.size();i++)
            {
                HashMap<String, String> map_params = new HashMap<>();
                try {
                    map_params.put(Constant.Params.UID, uid);
                    map_params.put(Constant.Params.SLIST, list_ids.get(i));
                    map_params.put(Constant.Params.ACTION, IHTTP.DOPUTEXAMINSCANTRON);
                    setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                        @Override
                        public void doHttpResponse(String json) {
                            if (JsonUtil.getJsonArrayOk(json)) {
                                mView.setSolutionOK(list_ids.size(),1);
                            } else {
                                mView.setSolutionErr();
                            }
                        }

                        @Override
                        public void onError(String errStr) {
                            mView.setSolutionErr();
                        }
                    });
                } catch (Exception e) {
                    mView.setSolutionErr();
                }


            }
        //mView.setSolutionOK();
      //  ProgressUtils.stopProgressDlg();

    }

    @Override
    public void setSolutionZhiNeng(Context mContext, String uid, List<ExamBean> list) {
        ProgressUtils.showProgressDlg("正在提交中", mContext);
        boolean flags =  MyNetUtils.checkNetwork(BaseApplication.getInstance());
        if(!flags)
        {
            mView.showLongToast("网络异常，请检查网络");
            return;
        }
        final  List<String> list_ids =  ExamUtils.getExamBeanLists(list,50);
        for(int i = 0;i<list_ids.size();i++)
        {
            HashMap<String, String> map_params = new HashMap<>();
            try {
                map_params.put(Constant.Params.UID, uid);
                map_params.put(Constant.Params.SLIST, list_ids.get(i));
                map_params.put(Constant.Params.ACTION, IHTTP.DOPUTPRACTSCANTRON);
                setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                    @Override
                    public void doHttpResponse(String json) {
                        if (JsonUtil.getJsonArrayOk(json)) {
                            mView.setSolutionOK(list_ids.size(),1);
                        } else {
                            mView.setSolutionErr();
                        }
                    }

                    @Override
                    public void onError(String errStr) {
                        mView.setSolutionErr();
                    }
                });
            } catch (Exception e) {
                mView.setSolutionErr();
            }
        }
    }

    @Override
    public void shoucang(Context mContext,String uid, String ftype, String linkid, String time) {

        HashMap<String, String> map_params = new HashMap<>();
        try {
            map_params.put(Constant.Params.ACTION, IHTTP.DOPUTFAVORITE);
            map_params.put(Constant.Params.UID, uid);
            map_params.put(Constant.Params.ATIME, MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1));
            map_params.put(Constant.Params.LINKID, linkid);
            map_params.put(Constant.Params.FTYPE, ftype);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<FavoriteRecord> list = JsonUtil.jsonToListByArray(json, FavoriteRecord.class);
                    if (list != null && list.size() > 0) {
                        mView.shoucangOK(list,true);
                    } else {
                        mView.shoucangErr();
                    }
                }

                @Override
                public void onError(String errStr) {
                    mView.shoucangErr();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.showLongToast("网络异常，请检查网络后操作");
            mView.shoucangErr();
        }
    }

    @Override
    public void quxiaoshoucang(Context mContext,String uid, String linkid, String ftype) {

        ProgressUtils.showProgressDlg("取消收藏中",mContext);
        HashMap<String, String> map_params = new HashMap<>();
        try {
            map_params.put(Constant.Params.ACTION, IHTTP.DODELFAVORITE);
            map_params.put(Constant.Params.UID, uid);
            map_params.put(Constant.Params.LINKID, linkid);
            map_params.put(Constant.Params.FTYPE, ftype);


            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<FavoriteRecord> list = JsonUtil.jsonToListByArray(json, FavoriteRecord.class);
                    if (list != null && list.size() > 0) {
                        mView.shoucangOK(list,false);
                    } else {
                        mView.shoucangErr();
                    }
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.shoucangErr();
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.shoucangErr();
            ProgressUtils.stopProgressDlg();
        }

    }

    @Override
    public void setSolutionZhiNeng(Context mContext, String uid, String slist) {
        HashMap<String, String> map_params = new HashMap<>();
        try {
            ProgressUtils.showProgressDlg("正在提交中", mContext);
            map_params.put(Constant.Params.UID, uid);
            map_params.put(Constant.Params.SLIST, slist);
            map_params.put(Constant.Params.ACTION, IHTTP.DOPUTPRACTSCANTRON);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if (JsonUtil.getJsonArrayOk(json)) {
                        mView.setSolutionOK();
                    } else {
                        mView.setSolutionErr();
                    }
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG, errStr);
                    mView.setSolutionErr();
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG, e.getMessage());
            mView.setSolutionErr();
            ProgressUtils.stopProgressDlg();

        }

    }


}
