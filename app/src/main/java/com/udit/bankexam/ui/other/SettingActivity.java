package com.udit.bankexam.ui.other;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.LoginOutBean;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.ui.home.HomeActivity;
import com.udit.bankexam.ui.newui.UserXyActivity;
import com.udit.bankexam.ui.newui.YinSiActivity;
import com.udit.bankexam.ui.user.AboutActivity;
import com.udit.bankexam.ui.user.LoginActivity;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.bankexam.view.DelectUserInfoPop;
import com.udit.bankexam.view.LoginOutPop;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.http.HttpTask;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.freamwork.http.RequestObject;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import java.util.HashMap;

public class SettingActivity extends BaseActivity {

    Handler handler_qingli = new Handler() {
        public void handleMessage(Message param1Message) {
            ProgressUtils.stopProgressDlg();
        }
    };

    private ImageView img_top_return;
    private TextView text_top_centent;
    private TextView tv_version;
    private LinearLayout ll_yinsi;
    private LinearLayout ll_user;
    private LinearLayout ll_me_about;
    private LinearLayout ll_me_qingli;
    private LinearLayout ll_delect_code;
    private TextView tv_login_out;

    @Override
    public void initData() {
        this.text_top_centent.setText("设置");
    }

    @Override
    public void initListeners() {
        //注销账号
        this.ll_delect_code.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                DelectUserInfoPop delectUserInfoPop = new DelectUserInfoPop(SettingActivity.this);
                delectUserInfoPop.showPop(new DelectUserInfoPop.ClickCallback() {
                    @Override
                    public void clickTrue() {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + "4000880828");
                        intent.setData(data);
                        startActivity(intent);
                    }

                    @Override
                    public void clickFalse() {
                    }
                });
            }
        });
        //用户协议
        this.ll_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, UserXyActivity.class);
                SettingActivity.this.startActivity(intent);
            }
        });
        this.img_top_return.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                SettingActivity.this.finish();
            }
        });
        this.ll_me_about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                AboutActivity.startAboutActivity(SettingActivity.this.getActivity());
            }
        });
        this.ll_me_qingli.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                ProgressUtils.showProgressDlg("缓存清理中", SettingActivity.this.getActivity());
                SettingActivity.this.handler_qingli.sendEmptyMessageDelayed(0, 3000L);
            }
        });
        this.tv_login_out.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                (new LoginOutPop(SettingActivity.this)).showPop(new LoginOutPop.ClickCallback() {
                    public void clickTrue() {
                        loginOut();
                    }
                });
            }
        });
        this.ll_yinsi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                changeAct(null, YinSiActivity.class);
            }
        });
        tv_version.setText(Utils.getVersionCode(this) + "");
    }

    public void loginOut() {
        try {
            String sessionKey = (String) SpUtil.get(this, "sessionKey", "");
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put("appId", IHTTP.APP_ID);
            map_params.put("sessionKey", sessionKey);
            setHttp(map_params, IHTTP.NEW_LOGIN_OUT, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {
                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口", "json=" + json);
                    LoginOutBean bean = new Gson().fromJson(json, LoginOutBean.class);
                    if (bean != null) {
                        SpUtil.put(SettingActivity.this, "sessionKey", "");
                        SpUtil.put(SettingActivity.this, "name", "pet");
                        SpUtil.put(SettingActivity.this, "imgurl", "");
                        SaveUtils.deleteUser(SettingActivity.this);
                        LoginActivity.startLoginActivity(SettingActivity.this);
                        HomeActivity.HOMEACTIVITY.finish();
                        SettingActivity.this.finish();
                    }
                }
            });
        } catch (Exception e) {
            MyLogUtils.e("测试新接口", e.getMessage());
        }
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

    @Override
    public void initViews(Bundle paramBundle) {
        ViewUtils.initView(this, com.udit.bankexam.R.id.class);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_setting);
    }
}
