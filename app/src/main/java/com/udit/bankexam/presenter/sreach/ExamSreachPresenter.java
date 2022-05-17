package com.udit.bankexam.presenter.sreach;

import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.sreach.ExamSreachView;
import com.udit.bankexam.view.sreach.ExamSreachView.View;
import com.udit.bankexam.view.sreach.ExamSreachView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;

import java.util.HashMap;
import java.util.List;

public class ExamSreachPresenter extends ExamSreachView.Presenter
{
    private final String TAG = this.getClass().getSimpleName();


    public ExamSreachPresenter(View mView)
    {
        super(mView);
    }

    @Override
    public void sreach(String uid, String keyword, String npage, String tCount) {
        HashMap<String,String> map_params = new HashMap<>();

        try {
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETSEARCH);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.KEYWORD,keyword);
            map_params.put(Constant.Params.NPAGE,npage);
            map_params.put(Constant.Params.TCOUNT,tCount);

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


                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.setExam(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            MyLogUtils.e(TAG,e.getMessage());
            mView.setExam(null);
        }


    }


}
