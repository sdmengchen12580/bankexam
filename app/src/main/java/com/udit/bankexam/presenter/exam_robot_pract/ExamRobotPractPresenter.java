package com.udit.bankexam.presenter.exam_robot_pract;

import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.TypeBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_robot_pract.ExamRobotPractView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamRobotPractPresenter extends ExamRobotPractView.Presenter {
    public ExamRobotPractPresenter(ExamRobotPractView.View mView) {
        super(mView);
    }

    @Override
    public void getData() {
        HashMap<String,String> map_params = new HashMap<>();
        try {
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETTYPE);
            map_params.put(Constant.Params.ITYPE,"考点");

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
    public void getExamList(String uid, String content) {
        HashMap<String,String> map_params = new HashMap<>();
        try {
            map_params.put(Constant.Params.ACTION,IHTTP.DONEWPRACTLIST);

            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.QPOINT,content);
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

                }

                @Override
                public void onError(String errStr) {
                    mView.setExamList(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setExamList(null);
        }
    }
}
