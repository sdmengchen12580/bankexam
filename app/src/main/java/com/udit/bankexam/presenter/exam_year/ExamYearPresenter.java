package com.udit.bankexam.presenter.exam_year;

import android.content.Context;

import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_year.ExamYearView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamYearPresenter extends ExamYearView.Presenter {

    private final String TAG = this.getClass().getSimpleName();

    public ExamYearPresenter(ExamYearView.View mView) {
        super(mView);
    }

    @Override
    public void getExamYear(Context mContext, String uid, String type) {
        HashMap<String,String> map_params = new HashMap<>();

        try {
            ProgressUtils.showProgressDlg("获取试卷中",mContext);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.TYPEINFO,type);
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETEXAMIN);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamInfoBean> list = JsonUtil.jsonToListByArray(json,ExamInfoBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setExamList(list);
                    }
                    else
                    {
                        mView.setExamList(null);
                    }
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            ProgressUtils.stopProgressDlg();
        }
    }

    @Override
    public void getExamTitle(Context mContext, String uid, String eid) {
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

    @Override
    public void saveExam(String uid, String linkid, String name) {
        HashMap<String,String> map_params = new HashMap<>();

        map_params.put(Constant.Params.ACTION,IHTTP.DOPUTPURCH);
        map_params.put(Constant.Params.UID,uid);
        map_params.put(Constant.Params.PTYPE,Constant.DataType.TYPE_SHIJUAN);
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
}
