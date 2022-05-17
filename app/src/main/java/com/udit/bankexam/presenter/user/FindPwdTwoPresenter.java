package com.udit.bankexam.presenter.user;

import java.util.HashMap;

import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.constant.Constant.Params;
import com.udit.bankexam.utils.JsonUtils;
import com.udit.bankexam.view.user.FindPwdTwoView;
import com.udit.bankexam.view.user.FindPwdTwoView.View;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import android.content.Context;

public class FindPwdTwoPresenter extends FindPwdTwoView.Presenter
{
    private final String TAG = this.getClass().getSimpleName();
    
    public FindPwdTwoPresenter(View mView)
    {
        super(mView);
    }

    @Override
    public void doGetPass(Context mContext, String phone, String pwd)
    {
        try
        {
            ProgressUtils.showProgressDlg("重置密码中", mContext);
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put(Params.ACTION, IHTTP.DOGETPASS);
            map_params.put(Params.MOBILE, phone);
            map_params.put(Params.PASS, pwd);
            
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener()
            {
                
                @Override
                public void onError(String errStr)
                {
                    mView.setPassErr();
                    ProgressUtils.stopProgressDlg();
                }
                
                @Override
                public void doHttpResponse(String json)
                {
                    MsgBean bean =  JsonUtils.parseDuanxin(json);
                    if(bean!=null)
                    {
                        mView.setPassOk(bean);
                    }
                    else
                    {
                        mView.setPassErr();
                    }
                    ProgressUtils.stopProgressDlg();
                }
            });
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, e.getMessage());
            mView.setPassErr();
            ProgressUtils.stopProgressDlg();
        }           
    }

}
