package com.udit.bankexam.presenter.exam_robot;

import android.content.Context;

import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.HisPractBean;
import com.udit.bankexam.bean.TypeBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_robot.ExamRobotView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamRobotPresenter extends ExamRobotView.Presenter {
    public ExamRobotPresenter(ExamRobotView.View mView) {
        super(mView);
    }

    @Override
    public void getData() {
        HashMap<String,String> map_params = new HashMap<>();
        try {
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETTYPE);
            map_params.put(Constant.Params.ITYPE,"考试内容");

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<TypeBean> list = JsonUtil.jsonToListByArray(json,TypeBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setData(list);
                    }
                    else
                    {
                        mView.setData(null);
                    }

                }

                @Override
                public void onError(String errStr) {
                    mView.setData(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setData(null);
        }

    }

    @Override
    public void getExamList(Context mContext,String uid,String content) {

        ProgressUtils.showProgressDlg("",mContext);
        HashMap<String,String> map_params = new HashMap<>();
        try {
            map_params.put(Constant.Params.ACTION,IHTTP.DONEWEXPRACTLIST);

            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.CONTENT,content);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamTitleBean> list = JsonUtil.jsonToListByArray(json,ExamTitleBean.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.setExamList(list);
                    }
                    else
                        mView.setExamList(null);
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setExamList(null);

                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setExamList(null);
            ProgressUtils.stopProgressDlg();
        }


    }

    @Override
    public void getExamHis(Context mContext, String uid) {
        try {


            ProgressUtils.showProgressDlg("",mContext);
            HashMap<String,String> map_params = new HashMap<>();

            map_params.put(Constant.Params.ACTION, IHTTP.DOGETHISEXPRACT);
            map_params.put(Constant.Params.UID,uid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<HisPractBean> list = JsonUtil.jsonToListByArray(json,HisPractBean.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.setHisExam(list);
                    }
                    else
                        mView.setHisExam(null);
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setHisExam(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setHisExam(null);
            ProgressUtils.stopProgressDlg();
        }
    }

    @Override
    public void getHisExamList(Context mContext, String uid, String eid) {
        try
        {
            ProgressUtils.showProgressDlg("",mContext);
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
                    mView.setExamList(list);
                }
                else
                    mView.setExamList(null);

                ProgressUtils.stopProgressDlg();
            }

            @Override
            public void onError(String errStr) {
                mView.setExamList(null);
                ProgressUtils.stopProgressDlg();
            }
        });
    } catch (Exception e) {
        e.printStackTrace();
        mView.setExamList(null);
            ProgressUtils.stopProgressDlg();
    }



}
}
