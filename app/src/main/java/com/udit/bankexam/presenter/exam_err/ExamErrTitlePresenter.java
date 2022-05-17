package com.udit.bankexam.presenter.exam_err;

import android.content.Context;

import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.bean.NoteBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_err.ExamErrTitleView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/19.
 */

public class ExamErrTitlePresenter extends ExamErrTitleView.Presenter {

    private final String TAG = this.getClass().getSimpleName();

    public ExamErrTitlePresenter(ExamErrTitleView.View mView) {
        super(mView);
    }

    @Override
    public void getExam(Context mContext,String uid, String ids) {
        ProgressUtils.showProgressDlg("",mContext);
        HashMap<String,String> map_params = new HashMap<>();
        try {
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETEXAMINTITLES);

            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.IDLIST,ids);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonForOK(json))
                    {
                        List<ExamBean> list = JsonUtil.jsonToList(json,"list",ExamBean.class);
                        if(list!=null && list.size()>0)
                        {
                            mView.setExam(list);
                        }
                        else
                        {
                            mView.setExam(null);
                        }
                    }
                    else
                    {
                        mView.setExam(null);
                    }
                    ProgressUtils.stopProgressDlg();



                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.setExam(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            MyLogUtils.e(TAG,e.getMessage());
            mView.setExam(null);
            ProgressUtils.stopProgressDlg();
        }

    }

    @Override
    public void getBJ(final int postion,String uid, String titleId) {

        HashMap<String,String> map_params = new HashMap<>();

        try {
            map_params.put(Constant.Params.ACTION,IHTTP.DOGETNOTE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.IDS,titleId);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<NoteBean> list = JsonUtil.jsonToListByArray(json,NoteBean.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.getBJ(postion,list.get(0));
                    }
                    else
                        mView.getBJ(postion,null);

                }

                @Override
                public void onError(String errStr) {
                    mView.getBJ(postion,null);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.getBJ(postion,null);
        }


    }

    @Override
    public void setBJ(String uid, String titleId, final String note) {

        HashMap<String,String> map_params = new HashMap<>();

        try {
            map_params.put(Constant.Params.ACTION,IHTTP.DOPUTNOTE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.IDS,titleId);
            map_params.put(Constant.Params.NDATE, MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1));
            map_params.put(Constant.Params.NOTE,note);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<NoteBean> list = JsonUtil.jsonToListByArray(json,NoteBean.class);
                    if(list!=null &&list.size()>0)
                    {
                        mView.setBJOK(list.get(0));
                    }
                    else
                    {
                        mView.setBJERR();
                    }
                }

                @Override
                public void onError(String errStr) {
                    mView.setBJERR();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            MyLogUtils.e(TAG,e.getMessage());
            mView.setBJERR();
        }


    }

    public void quxiaoshoucang(String uid, String linkid, String ftype) {
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
                }

                @Override
                public void onError(String errStr) {
                    mView.shoucangErr();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.shoucangErr();
        }

    }

    @Override
    public void shoucang(String uid, String ftype, String linkid, String time) {
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
            mView.shoucangErr();
        }

    }

    @Override
    public void getRobotExamList(Context mContext, String uid,String list) {
        HashMap<String, String> map_params = new HashMap<>();
        ProgressUtils.showProgressDlg("",mContext);
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
                            mView.setExam(mlist);
                        else
                            mView.setExam(null);
                        ProgressUtils.stopProgressDlg();
                    } else {
                        mView.setExam(null);
                        ProgressUtils.stopProgressDlg();
                    }

                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG, errStr);
                    mView.setExam(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG, e.getMessage());
            mView.setExam(null);
            ProgressUtils.stopProgressDlg();
        }
    }
}


