package com.udit.bankexam.presenter.exam;


import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam.ExamListView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import java.util.HashMap;
import java.util.List;


public class ExamListPresenter extends ExamListView.Presenter {
    private final String TAG = this.getClass().getSimpleName();

    public ExamListPresenter(ExamListView.View mView) {
        super(mView);
    }


    @Override
    public void getExamList(String uid, String eid, String type) {

        try {
            HashMap<String,String> map_params = new HashMap<>();

            map_params.put(Constant.Params.ACTION, IHTTP.DOGETADVID);

            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.PATH,eid);
            map_params.put(Constant.Params.TITLE,type);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamInfoBean> list = JsonUtil.jsonToListByArray(json,ExamInfoBean.class);

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
            mView.showLongToast("网络异常，请检查网络后操作");
        }


    }

}
