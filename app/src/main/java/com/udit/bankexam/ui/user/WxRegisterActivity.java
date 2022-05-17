package com.udit.bankexam.ui.user;

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

import com.udit.bankexam.R;
import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.user.FindPwdOnePresenter;
import com.udit.bankexam.presenter.user.RegisterOnePresenter;
import com.udit.bankexam.ui.home.HomeActivity;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.user.FindPwdOneView;
import com.udit.bankexam.view.user.WxRegisterOneView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

public class WxRegisterActivity extends BaseActivity<RegisterOnePresenter> implements WxRegisterOneView.View, OnClickListener {
    public static void startWxRegisterActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, WxRegisterActivity.class));
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_findpwdone);
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private EditText edit_phonenum, edit_yzm;

    private Button button_yzm, btn_find_next;

    private String mPhone;

    private final String TAG = this.getClass().getSimpleName();

    Handler handler_number = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            MyLogUtils.e(TAG, "   HANDLE");
            if (msg.what == Constant.RESULT.RESULT_MSG_ERR) {
                String yzm = button_yzm.getText().toString();
                if (yzm.equals("发送验证码")) {

                } else {
                    try {
                        int yzm_int = Integer.parseInt(yzm);
                        yzm_int--;
                        if (yzm_int <= 0) {
                            button_yzm.setText("发送验证码");
                        } else {
                            button_yzm.setText(yzm_int + "");
                            handler_number.sendEmptyMessageDelayed(Constant.RESULT.RESULT_MSG_ERR, Constant.RESULT.MSG_NUM_STEP);
                        }
                    } catch (NumberFormatException e) {
                        button_yzm.setText("发送验证码");
                    }

                }


            } else {
                button_yzm.setText("发送验证码");
            }
        }
    };

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("绑定手机号");
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
        btn_find_next.setOnClickListener(this);
        button_yzm.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new RegisterOnePresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
            case R.id.button_yzm:
                String phone = edit_phonenum.getText().toString();
                if (MyCheckUtils.isMobileCheck(phone, this) && button_yzm.getText().equals("发送验证码")) {
                    mPresenter.getDuanxin(this, phone);
                }
                break;
            case R.id.btn_find_next:
                mPhone = edit_phonenum.getText().toString();
                String yzm = edit_yzm.getText().toString();
                String yzm_save = SaveUtils.getMsg(this);
                if (MyCheckUtils.isEmpty(yzm_save)) {
                    showLongToast("请先获取验证码");
                    return;
                } else {

                }

                if (MyCheckUtils.isMobileCheck(mPhone, this)
                        && MyCheckUtils.isSms(yzm, this)
                        && MyCheckUtils.checkSmsAgain(yzm, yzm_save, this)
                ) {
                    UserBean user = SaveUtils.getUser(this);
                    mPresenter.checkPhoneIsRegister(this, mPhone, user.getUid());
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void setDuanxinOk(MsgBean bean) {
        SaveUtils.saveMsg(this, bean.getCode());
        showLongToast(bean.getMsg());
        button_yzm.setText(Constant.RESULT.MSG_NUM + "");
        handler_number.sendEmptyMessageDelayed(Constant.RESULT.RESULT_MSG_ERR, Constant.RESULT.MSG_NUM_STEP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler_number.removeMessages(Constant.RESULT.RESULT_MSG_ERR);
    }

    @Override
    public void setDuanxinErr(String msg) {
        if (!MyCheckUtils.isEmpty(msg)) {
            showLongToast(msg);
        } else
            showLongToast("短信发送失败");
    }

    @Override
    public void setCheckPhoneIsRegisterOk(String type) {
        if (type.equals("1")) {//设置密码
            String phone = edit_phonenum.getText().toString();
            WxRegisterTwoActivity.startWxRegisterTwoActivity(this, phone);

        } else if (type.equals("0")) {
            // 无需设置密码
            HomeActivity.startHomeActivity(this);
        }
    }

    @Override
    public void setCheckPhoneIsRegisterErr(String err) {
        showLongToast(err);
    }

}
