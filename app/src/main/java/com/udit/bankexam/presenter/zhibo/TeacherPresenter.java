package com.udit.bankexam.presenter.zhibo;



import com.udit.bankexam.bean.TeacherBean;
import com.udit.bankexam.bean.ZhiboKeChengBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.zhibo.TeacherView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;

import java.util.HashMap;
import java.util.List;


public class TeacherPresenter extends TeacherView.Presenter
{
    private final String TAG = this.getClass().getSimpleName();


    public TeacherPresenter(TeacherView.View mView) {
        super(mView);
    }

    @Override
    public void getTeacherInfo(String uid, String lid) {
        HashMap<String,String> map_params = new HashMap<>();
        map_params.put(Constant.Params.ACTION, IHTTP.DOGETLIVETECHE);
        map_params.put(Constant.Params.UID,uid);
        map_params.put(Constant.Params.LID,lid);

        try {
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<TeacherBean> list = JsonUtil.jsonToListByArray(json,TeacherBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setTeacher(list);
                    }
                    else
                        mView.setTeacher(null);
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());

        }
    }


}
