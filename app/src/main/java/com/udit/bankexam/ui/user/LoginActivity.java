package com.udit.bankexam.ui.user;

import com.udit.bankexam.MyApplication;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.user.LoginPresenter;
import com.udit.bankexam.ui.home.HomeActivity;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.bankexam.view.user.LoginView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Map;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView.View, OnClickListener {

    public static void startLoginActivity(BaseActivity<?> activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
        activity.onLowMemory();
        activity.finish();
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login);
    }

    private EditText edit_pwd;
    private EditText edit_pwd_qr;
    private EditText edit_yzm;
    private EditText edit_phonenum;
    private Button button_dx;
    private EditText edit_login_name, edit_login_pwd;
    private TextView text_login_findpwd;
    private Button btn_login, btn_register;
    private final String TAG = this.getClass().getSimpleName();
    private ImageView img_pwd_ess;
    private boolean flag_pwd = false;
    private ImageView btn_wx;
    private ImageView img_top_return;
    private LinearLayout ll_login;
    private LinearLayout ll_rigst;
    private String mPhone;
    private String mPwd;
    private RelativeLayout rl_login_button;
    private RelativeLayout rl_right_button;
    private TextView tv1;
    private TextView tv2;
    private TextView tv_line1;
    private TextView tv_line2;
    private TextView tv_toptitle;

    Handler handler_number = new Handler() {
        public void handleMessage(Message param1Message) {
            MyLogUtils.e(LoginActivity.this.TAG, "   HANDLE");
            if (param1Message.what == 21) {
                String str = LoginActivity.this.button_dx.getText().toString();
                if (str.equals("发送验证码"))
                    return;
                try {
                    int i = Integer.parseInt(str) - 1;
                    if (i <= 0) {
                        LoginActivity.this.button_dx.setText("发送验证码");
                        return;
                    }
                    Button button = LoginActivity.this.button_dx;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(i);
                    stringBuilder.append("");
                    button.setText(stringBuilder.toString());
                    LoginActivity.this.handler_number.sendEmptyMessageDelayed(21, 1000L);
                    return;
                } catch (NumberFormatException e) {
                    LoginActivity.this.button_dx.setText("发送验证码");
                    return;
                }
            }
            LoginActivity.this.button_dx.setText("发送验证码");
        }
    };

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
    }

    @Override
    public void initListeners() {
        this.text_login_findpwd.setOnClickListener(this);
        this.btn_login.setOnClickListener(this);
        this.img_pwd_ess.setOnClickListener(this);
        this.btn_wx.setOnClickListener(this);
        this.button_dx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (!LoginActivity.this.button_dx.getText().toString().equals("发送验证码")) {
                    LoginActivity.this.showLongToast("请稍后再试");
                    return;
                }
                String str = LoginActivity.this.edit_phonenum.getText().toString();
                if (MyCheckUtils.isMobileCheck(str, LoginActivity.this))
                    ((LoginPresenter) LoginActivity.this.mPresenter).getDuanxin(LoginActivity.this, str);
            }
        });
        this.btn_register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                LoginActivity.this.mPhone = LoginActivity.this.edit_phonenum.getText().toString();
                LoginActivity.this.mPwd = LoginActivity.this.edit_pwd.getText().toString();
                String str1 = LoginActivity.this.edit_yzm.getText().toString();
                String str2 = LoginActivity.this.edit_pwd_qr.getText().toString();
                String str3 = SaveUtils.getMsg(LoginActivity.this);
                if (MyCheckUtils.isEmpty(edit_phonenum.getText().toString().trim())) {
                    LoginActivity.this.showLongToast("请先输入手机号");
                    return;
                }
                if (MyCheckUtils.isEmpty(edit_yzm.getText().toString().trim())) {
                    LoginActivity.this.showLongToast("请先获取验证码");
                    return;
                }
                if (MyCheckUtils.isEmpty(edit_pwd.getText().toString().trim())) {
                    LoginActivity.this.showLongToast("请先输入密码");
                    return;
                }
                if (MyCheckUtils.isMobileCheck(LoginActivity.this.mPhone, LoginActivity.this) &&
                        MyCheckUtils.isSms(str1, LoginActivity.this) &&
                        MyCheckUtils.checkSmsAgain(str1, str3, LoginActivity.this) &&
                        MyCheckUtils.isPasswordCheck(LoginActivity.this.mPwd, LoginActivity.this) &&
                        MyCheckUtils.isPasswordCheck(LoginActivity.this.mPwd, str2, LoginActivity.this))
                    ((LoginPresenter) LoginActivity.this.mPresenter).register(LoginActivity.this, LoginActivity.this.mPhone, LoginActivity.this.mPwd);
            }
        });
        this.rl_login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                LoginActivity.this.tv_toptitle.setText("登录");
                LoginActivity.this.ll_login.setVisibility(View.VISIBLE);
                LoginActivity.this.ll_rigst.setVisibility(View.GONE);
                LoginActivity.this.tv1.setTextColor(Color.parseColor("#333333"));
                LoginActivity.this.tv2.setTextColor(Color.parseColor("#999999"));
                LoginActivity.this.tv_line1.setVisibility(View.VISIBLE);
                LoginActivity.this.tv_line2.setVisibility(View.GONE);
            }
        });
        this.rl_right_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                LoginActivity.this.tv_toptitle.setText("注册");
                LoginActivity.this.ll_login.setVisibility(View.GONE);
                LoginActivity.this.ll_rigst.setVisibility(View.VISIBLE);
                LoginActivity.this.tv1.setTextColor(Color.parseColor("#999999"));
                LoginActivity.this.tv2.setTextColor(Color.parseColor("#333333"));
                LoginActivity.this.tv_line1.setVisibility(View.GONE);
                LoginActivity.this.tv_line2.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void initData() {
        this.mPhone = "";
        this.mPwd = "";
        mPresenter = new LoginPresenter(this);
        flag_pwd = false;
        try {
            UserBean bean = SaveUtils.getUser(this);
            if (bean != null) {
                if (!MyCheckUtils.isEmpty(bean.getMobile())
                        && !MyCheckUtils.isEmpty(bean.getPass())
                ) {
                    if (MyCheckUtils.isMobileCheck(bean.getMobile(), this)
                            && MyCheckUtils.isPasswordCheck(bean.getPass(), this)) {
                        edit_login_name.setText(bean.getMobile());
                        edit_login_pwd.setText(bean.getPass());
                        mPresenter.login(this, bean.getMobile(), bean.getPass());
                    }
                }

            }
        } catch (Exception e) {

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_wx:
                /*boolean flag = mShareAPI.isInstall(this, SHARE_MEDIA.WEIXIN);
                if (flag) {
                    mShareAPI.doOauthVerify(this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
                        @Override
                        public void onStart(SHARE_MEDIA share_media) {
                            MyLogUtils.i(TAG, "onStart");
                        }

                        @Override
                        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                            MyLogUtils.i(TAG, "weixin_onComplete");
                            if (map != null) {
                                Iterator iterator = map.keySet().iterator();
                                while (iterator.hasNext()) {
                                    String key = (String) iterator.next();
                                    String value = map.get(key);
                                    MyLogUtils.i(TAG, "weixin-map: " + key + "   " + value);
                                }
                            }
                        }

                        @Override
                        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                            showLongToast("授权登录失败");
                        }

                        @Override
                        public void onCancel(SHARE_MEDIA share_media, int i) {
                            showLongToast("授权登录取消");
                        }
                    });
                } else {
                    showLongToast("未安装微信");
                }*/
                break;
            case R.id.img_pwd_ess:
                if (flag_pwd) {//密码
                    edit_login_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    img_pwd_ess.setImageResource(R.mipmap.img_login_no_look);
                    flag_pwd = false;
                } else {//显示明文
                    edit_login_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    img_pwd_ess.setImageResource(R.mipmap.img_login_look);
                    flag_pwd = true;
                }
                break;
            case R.id.text_login_findpwd:
                FindPwdOneActivity.startFindPwdOneActivity(this);
                break;
            case R.id.btn_login:
                SpUtil.put(this, "name", "pet");
                SpUtil.put(this, "imgurl", "");
                SaveUtils.deleteUser(this);
                String phone = edit_login_name.getText().toString();
                String pwd = edit_login_pwd.getText().toString();
                if (MyCheckUtils.isMobileCheck(phone, this) && MyCheckUtils.isPasswordCheck(pwd, this)) {
                    mPresenter.loginNew(this, phone, pwd);//fixme 新增登录接口
                    mPresenter.login(this, phone, pwd);
                }
                break;
            case R.id.btn_register:
                RegisterActivity.startRegisterActivity(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void loginOk(UserBean bean) {
        showLongToast("登录成功");
        SaveUtils.saveUser(this, bean.getUid(), bean.getMobile(), bean.getPass(), bean.getPet(), bean.getAcc(), bean.getLastSign(), bean.getSignDays());
        HomeActivity.startHomeActivity(this);
        this.onLowMemory();
        finish();
    }

    @Override
    public void registerErr() {
        showLongToast("注册失败");
    }

    @Override
    public void registerOk(MsgBean param1MsgBean) {
        showLongToast(param1MsgBean.getMsg());
        SaveUtils.deleteMsg(this);
        SaveUtils.saveUser(this, param1MsgBean.getUid(), this.mPhone, this.mPwd, null, "", "", "");
        HomeActivity.startHomeActivity(this);
        onLowMemory();
        finish();
    }

    @Override
    public void setDuanxinErr(String param1String) {
        if (!MyCheckUtils.isEmpty(param1String)) {
            showLongToast(param1String);
            return;
        }
        showLongToast("短信获取失败");
    }

    @Override
    public void setDuanxinOk(MsgBean param1MsgBean) {
        if (param1MsgBean.getMsg() != null && param1MsgBean.getMsg().contains("频率限制")) {
            showLongToast(param1MsgBean.getMsg());
            return;
        }
        if (param1MsgBean.getCode() == null) {
            showLongToast(param1MsgBean.getMsg());
            return;
        }
        SaveUtils.saveMsg(this, param1MsgBean.getCode());
        showLongToast(param1MsgBean.getMsg());
        this.button_dx.setText("60");
        this.handler_number.sendEmptyMessageDelayed(21, 1000L);
    }

    @Override
    public void loginErr(String msg) {
        if (!MyCheckUtils.isEmpty(msg)) {
            showLongToast(msg);
        } else
            showLongToast("登录失败");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.handler_number.removeMessages(21);
    }

}
