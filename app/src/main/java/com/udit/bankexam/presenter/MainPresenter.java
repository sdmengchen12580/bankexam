package com.udit.bankexam.presenter;

import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.utils.JsonUtils;
import com.udit.bankexam.view.MainView;
import com.udit.bankexam.view.MainView.View;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

public class MainPresenter extends MainView.Presenter
{
    private  final String TAG = this.getClass().getSimpleName();
    public MainPresenter(View mView)
    {
        super(mView);
    }

    @Override
    public void doAnimator()
    {
        mView.doAnimator();
    }

    @Override
    public void login(String mobile, String pass) {
        try
        {
           // ProgressUtils.showProgressDlg("登录中", mContext);
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOLOGIN);
            map_params.put(Constant.Params.MOBILE, mobile);
            map_params.put(Constant.Params.PASS, pass);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener()
            {

                @Override
                public void onError(String errStr)
                {
                    mView.loginErr(null);
                   // ProgressUtils.stopProgressDlg();
                }

                @Override
                public void doHttpResponse(String json)
                {
                    UserBean bean =   JsonUtils.parseUser(json);
                    if(bean!=null)
                    {
                        mView.loginOk(bean);
                    }
                    else
                    {
                        List<MsgBean> list = JsonUtil.jsonToListByArrayNoSucess(json,MsgBean.class);
                        if(list!=null && list.size()>0)
                        {
                            mView.loginErr(list.get(0).getMsg());
                        }
                        else
                            mView.loginErr(null);
                    }
                    // ProgressUtils.stopProgressDlg();
                }
            });
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, e.getMessage());
            mView.loginErr(null);
           // mView.loginErr("网络异常，登录失败");
            // ProgressUtils.stopProgressDlg();
        }
    }


}
