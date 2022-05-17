package com.udit.bankexam.wxapi;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.udit.bankexam.MyApplication;
import com.udit.bankexam.bean.NewUserBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.WxAccessToken;
import com.udit.bankexam.bean.WxUserInfo;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.ui.home.HomeActivity;
import com.udit.bankexam.ui.user.WxRegisterActivity;
import com.udit.bankexam.utils.JsonUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.frame.freamwork.http.HttpTask;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.freamwork.http.RequestObject;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import com.udit.frame.utils.ToastUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.UMWXHandler;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;


public class WXEntryActivity extends WXCallbackActivity {

    private final String TAG = this.getClass().getSimpleName();

    private IWXAPI api;

    private boolean flag_contant = true;

    private Activity mContext;

    final String url_user = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";

    final String url_access = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&&code=%s&grant_type=authorization_code";

    private void getAccessToken(String code) {
        String url = String.format(url_access, "wxf41ad321ed049ffa", "503e667c6581dd7008846d56ce97d12a", code);
        MyLogUtils.i(TAG, "url:" + url);
        RequestObject object = new RequestObject(url, null);
        new HttpTask().start(object, HttpTask.REQUEST_TYPE_POST, new IHttpResponseListener() {
            @Override
            public void doHttpResponse(String json) {
                WxAccessToken token = JsonUtil.jsonToBean(json, WxAccessToken.class);
                if (token != null && token.getAccess_token() != null && token.getOpenid() != null)
                    getUserInfo(token.getAccess_token(), token.getOpenid());
                else {
                    MyLogUtils.i(TAG, "getAccessToken:token is null");
                    wxErr();
                }
            }

            @Override
            public void onError(String errStr) {
                MyLogUtils.i(TAG, "getAccessToken:onError");
                wxErr();
            }
        });
    }

    private void getUserInfo(String access, String token) {
        String url = String.format(url_user, access, token);
        MyLogUtils.i(TAG, "url:" + url);
        RequestObject object = new RequestObject(url, null);
        new HttpTask().start(object, HttpTask.REQUEST_TYPE_POST, new IHttpResponseListener() {
            @Override
            public void doHttpResponse(String json) {
                MyLogUtils.i(TAG, "getUserInfo:" + json);
                WxUserInfo info = JsonUtil.jsonToBean(json, WxUserInfo.class);
                SpUtil.put(WXEntryActivity.this, "imgurl", info.getHeadimgurl());
                SpUtil.put(WXEntryActivity.this, "name", info.getNickname());
                if (info != null && info.getUnionid() != null) {
                    registerWx(info.getNickname(), info.getUnionid());
                } else {
                    MyLogUtils.i(TAG, "getUserInfo:info is null");
                    wxErr();
                }
            }

            @Override
            public void onError(String errStr) {
                MyLogUtils.i(TAG, "getUserInfo:onError");
                wxErr();
            }
        });
    }

    private void registerWx(String nickname, String unionid) {
        HashMap<String, String> map = new HashMap<>();
        map.put("action", IHTTP.doRegisterWX);
        map.put("unionid", unionid);
        map.put("nickname", nickname);
        RequestObject object = new RequestObject(IHTTP.PROJECT, map);
        new HttpTask().start(object, HttpTask.REQUEST_TYPE_POST, new IHttpResponseListener() {

            @Override
            public void doHttpResponse(String json) {
                UserBean bean = JsonUtils.parseUser(json);
                if (bean != null) {
                    ProgressUtils.stopProgressDlg();
                    flag_contant = true;
                    if (bean.getMobile() != null && !bean.getMobile().isEmpty()) {
                        SaveUtils.saveUser(mContext, bean.getUid(), bean.getMobile(), bean.getPass(), bean.getPet(), bean.getAcc(), bean.getLastSign(), bean.getSignDays());
                        loginNew(mContext, bean.getMobile(), bean.getPass());
                    } else {
                        SaveUtils.saveUser(mContext, bean.getUid(), bean.getMobile(), bean.getPass(), bean.getPet(), bean.getAcc(), bean.getLastSign(), bean.getSignDays());
                        mContext.startActivity(new Intent(mContext, WxRegisterActivity.class));
                        mContext.onLowMemory();
                        mContext.finish();
                    }
                } else {
                    MyLogUtils.i(TAG, "registerWx:bean is null");
                    wxErr();
                }
            }

            @Override
            public void onError(String errStr) {
                MyLogUtils.i(TAG, "registerWx:onError");
                wxErr();
            }
        });
    }

    protected void setHttp(HashMap<String, String> map, String address, IHttpResponseListener listener)
            throws Exception {
        try {
            RequestObject object = new RequestObject(address, map);
            new HttpTask().start(object, HttpTask.REQUEST_TYPE_POST, listener);
        } catch (Exception e) {
            throw new Exception();
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
                    mContext.startActivity(new Intent(mContext, HomeActivity.class));
                    mContext.onLowMemory();
                    mContext.finish();
                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口", "json=" + json);
                    NewUserBean bean = new Gson().fromJson(json, NewUserBean.class);
                    if (bean != null) {
                        SpUtil.put(param1Context, "sessionKey", bean.getData().getResponse().getSessionKey());
                        mContext.startActivity(new Intent(mContext, HomeActivity.class));
                        mContext.onLowMemory();
                        mContext.finish();
                    }
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG, e.getMessage());
        }
    }

    private void wxErr() {
        flag_contant = true;
        ProgressUtils.stopProgressDlg();
        ToastUtil.showMessage(mContext, "微信授权登录失败");
        mContext.onLowMemory();
        mContext.finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MyApplication.api == null) {
            MyApplication.getWxApi();
        }
        MyApplication.api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        MyApplication.api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        MyLogUtils.e(TAG, "onReq, errCode = " + baseReq.getType() + " baseReq:" + baseReq);
        this.onLowMemory();
        finish();
    }

    @Override
    public void onResp(BaseResp baseResp) {
        MyLogUtils.e(TAG, "onResp, errCode = " + baseResp.getType());
        mContext = this;
        if (!flag_contant)
            return;
        flag_contant = false;
        if (baseResp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            if (baseResp instanceof SendAuth.Resp) {
                SendAuth.Resp authResp = (SendAuth.Resp) baseResp;
                if (authResp.errCode == BaseResp.ErrCode.ERR_OK) {
                    MyLogUtils.i(TAG, "onResp, code = " + authResp.code);
                    ProgressUtils.showProgressDlg("登录中", mContext);
                    getAccessToken(authResp.code);
                    return;
                }
            }
        }
        this.onLowMemory();
        finish();
    }
}