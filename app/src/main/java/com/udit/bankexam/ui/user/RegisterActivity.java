package com.udit.bankexam.ui.user;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.user.RegisterPresenter;
import com.udit.bankexam.ui.home.HomeActivity;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.user.RegisterView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView.View, OnClickListener {
    public static void startRegisterActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, RegisterActivity.class));
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_register);
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private EditText edit_phonenum, edit_yzm, edit_pwd, edit_pwd_qr;

    private Button button_dx, btn_register;

    private String mPhone, mPwd;

    private final String TAG = this.getClass().getSimpleName();

    Handler handler_number = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            MyLogUtils.e(TAG, "   HANDLE");
            if (msg.what == Constant.RESULT.RESULT_MSG_ERR) {
                String yzm = button_dx.getText().toString();
                if (yzm.equals("发送验证码")) {

                } else {
                    try {
                        int yzm_int = Integer.parseInt(yzm);
                        yzm_int--;
                        if (yzm_int <= 0) {
                            button_dx.setText("发送验证码");
                        } else {
                            button_dx.setText(yzm_int + "");
                            handler_number.sendEmptyMessageDelayed(Constant.RESULT.RESULT_MSG_ERR, Constant.RESULT.MSG_NUM_STEP);
                        }
                    } catch (NumberFormatException e) {
                        button_dx.setText("发送验证码");
                    }

                }


            } else {
                button_dx.setText("发送验证码");
            }
        }
    };

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("注册");
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
        button_dx.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new RegisterPresenter(this);
        mPhone = "";
        mPwd = "";

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
            case R.id.button_dx:
                String phone = edit_phonenum.getText().toString();
                if (MyCheckUtils.isMobileCheck(phone, this)) {
                    //  MsgBean bean = new MsgBean();
                    //  bean.setCode("103111");

                    // setDuanxinOk(bean);
                    mPresenter.getDuanxin(this, phone);
                }
                break;
            case R.id.btn_register:
                mPhone = edit_phonenum.getText().toString();
                String yzm = edit_yzm.getText().toString();
                mPwd = edit_pwd.getText().toString();
                String pwd_again = edit_pwd_qr.getText().toString();
                String yzm_save = SaveUtils.getMsg(this);
                if (MyCheckUtils.isEmpty(yzm_save)) {
                    showLongToast("请先获取验证码");
                    return;
                } else {

                }

                if (MyCheckUtils.isMobileCheck(mPhone, this)
                        && MyCheckUtils.isSms(yzm, this)
                        && MyCheckUtils.checkSmsAgain(yzm, yzm_save, this)
                        && MyCheckUtils.isPasswordCheck(mPwd, this)
                        && MyCheckUtils.isPasswordCheck(mPwd, pwd_again, this)) {

                    mPresenter.register(this, mPhone, mPwd);
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void setDuanxinOk(MsgBean bean) {
        if (bean.getCode() == null) {
            showLongToast(bean.getMsg());
            return;
        }
        SaveUtils.saveMsg(this, bean.getCode());
        showLongToast(bean.getMsg());
        button_dx.setText(Constant.RESULT.MSG_NUM + "");
        handler_number.sendEmptyMessageDelayed(Constant.RESULT.RESULT_MSG_ERR, Constant.RESULT.MSG_NUM_STEP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler_number.removeMessages(Constant.RESULT.RESULT_MSG_ERR);
    }

    @Override
    public void setDuanxinErr(String msg) {
        if (!MyCheckUtils.isEmpty(msg))
            showLongToast(msg);
        else
            showLongToast("短信获取失败");
    }

    @Override
    public void registerOk(MsgBean bean) {
        showLongToast(bean.getMsg());
        SaveUtils.deleteMsg(this);
        SaveUtils.saveUser(this, bean.getUid(), mPhone, mPwd, null, "", "", "");
        HomeActivity.startHomeActivity(this);
        this.onLowMemory();
        finish();
    }

    @Override
    public void registerErr() {
        showLongToast("注册失败");

    }

}
