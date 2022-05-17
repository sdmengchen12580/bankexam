package com.udit.bankexam.presenter.user;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.bean.NewUserBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.constant.Constant.Params;
import com.udit.bankexam.utils.JsonUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.bankexam.view.user.LoginView;
import com.udit.bankexam.view.user.LoginView.View;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import android.content.Context;

public class LoginPresenter extends LoginView.Presenter {
    private final String TAG = this.getClass().getSimpleName();

    public LoginPresenter(View mView) {
        super(mView);
    }

    @Override
    public void login(Context mContext, String phone, String pwd) {
        try {
            ProgressUtils.showProgressDlg("登录中", mContext);
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put(Params.ACTION, IHTTP.DOLOGIN);
            map_params.put(Params.MOBILE, phone);
            map_params.put(Params.PASS, pwd);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {
                    mView.loginErr(null);
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void doHttpResponse(String json) {
                    UserBean bean = JsonUtils.parseUser(json);
                    if (bean != null) {
                        mView.loginOk(bean);
                    } else {
                        List<MsgBean> list = JsonUtil.jsonToListByArrayNoSucess(json, MsgBean.class);
                        if (list != null && list.size() > 0) {
                            mView.loginErr(list.get(0).getMsg());
                        } else
                            mView.loginErr(null);
                    }
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG, e.getMessage());
            mView.loginErr("网络异常，登录失败");
            ProgressUtils.stopProgressDlg();
        }
    }

    @Override
    public void loginNew(final Context param1Context, String phone, String pwd) {
        try {
            String clientSign = "__ACBadf" + phone + pwd;
            clientSign = encrypt32(clientSign);
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put("appId", IHTTP.APP_ID);
            map_params.put("clientSign", clientSign);
            map_params.put("mobile", phone);
            map_params.put("password", pwd);
            setHttp(map_params, IHTTP.NEW_LOGIN_IN, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {
                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口", "json=" + json);
                    NewUserBean bean = new Gson().fromJson(json, NewUserBean.class);
                    if (bean != null) {
                        SpUtil.put(param1Context, "sessionKey", bean.getData().getResponse().getSessionKey());
                        SpUtil.put(param1Context, "imgurl", bean.getData().getResponse().getAvatar());
                    }
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG, e.getMessage());
        }
    }

    //MD5 32位小写加密
    public static String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    public void getDuanxin(Context paramContext, String paramString) {
        try {
            ProgressUtils.showProgressDlg("获取短信中", paramContext);
            HashMap hashMap = new HashMap();
            hashMap.put("action", "doIdentifying");
            hashMap.put("mobile", paramString);
            hashMap.put("type", "0");
            setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
                public void doHttpResponse(String param1String) {
                    MsgBean msgBean = JsonUtils.parseDuanxin(param1String);
                    if (msgBean != null) {
                        ((LoginView.View) LoginPresenter.this.mView).setDuanxinOk(msgBean);
                    } else {
                        List list = JsonUtil.jsonToListByArrayNoSucess(param1String, MsgBean.class);
                        if (list != null && list.size() > 0) {
                            ((LoginView.View) LoginPresenter.this.mView).setDuanxinErr(((MsgBean) list.get(0)).getMsg());
                        } else {
                            ((LoginView.View) LoginPresenter.this.mView).setDuanxinErr(null);
                        }
                    }
                    ProgressUtils.stopProgressDlgDelay();
                }

                public void onError(String param1String) {
                    ((LoginView.View) LoginPresenter.this.mView).setDuanxinErr(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
            return;
        } catch (Exception context) {
            MyLogUtils.e(this.TAG, context.getMessage());
            ((LoginView.View) this.mView).setDuanxinErr(null);
            ProgressUtils.stopProgressDlgDelay();
            return;
        }
    }

    public void register(Context paramContext, String paramString1, String paramString2) {
        try {
            ProgressUtils.showProgressDlg("账号注册中", paramContext);
            HashMap hashMap = new HashMap();
            hashMap.put("action", "doRegister");
            hashMap.put("mobile", paramString1);
            hashMap.put("pass", paramString2);
            setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
                public void doHttpResponse(String param1String) {
                    MsgBean msgBean = JsonUtils.parseDuanxin(param1String);
                    if (msgBean != null) {
                        ((LoginView.View) LoginPresenter.this.mView).registerOk(msgBean);
                    } else {
                        ((LoginView.View) LoginPresenter.this.mView).registerErr();
                    }
                    ProgressUtils.stopProgressDlg();
                }

                public void onError(String param1String) {
                    ((LoginView.View) LoginPresenter.this.mView).registerErr();
                    ProgressUtils.stopProgressDlg();
                }
            });
            return;
        } catch (Exception context) {
            MyLogUtils.e(this.TAG, context.getMessage());
            ((LoginView.View) this.mView).registerErr();
            ProgressUtils.stopProgressDlg();
            return;
        }
    }

}


