package com.udit.bankexam.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.constant.Constant.IntentParams;
import com.udit.bankexam.presenter.user.RegisterTwoPresenter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.user.WxRegisterTwoView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.ViewUtils;

public class WxRegisterTwoActivity extends BaseActivity<RegisterTwoPresenter> implements WxRegisterTwoView.View, OnClickListener {
    public static void startWxRegisterTwoActivity(BaseActivity<?> mActivity, String phone) {
        Intent intent = new Intent(mActivity, WxRegisterTwoActivity.class);
        intent.putExtra(IntentParams.PHONE, phone);
        mActivity.startActivity(intent);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_findpwdtwo);
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private EditText edit_pwd, edit_pwd_again;

    private Button btn_cz;

    private String mPhone, mPwd;

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("设置密码");
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
        btn_cz.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new RegisterTwoPresenter(this);
        mPhone = "";
        mPhone = getIntent().getStringExtra(IntentParams.PHONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                finish();
                break;
            case R.id.btn_cz:
                mPwd = edit_pwd.getText().toString();
                String pwd_again = edit_pwd_again.getText().toString();
                if (MyCheckUtils.isPasswordCheck(mPwd, this)
                        && MyCheckUtils.isPasswordCheck(mPwd, pwd_again, this)
                ) {
                    mPresenter.doGetPass(this, mPhone, mPwd);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void setPassOk(MsgBean bean) {
        SaveUtils.saveUser(this, bean.getUid(), mPhone, mPwd, null, "", "", "");
        showLongToast("密码重置成功");
        LoginActivity.startLoginActivity(this);
    }

    @Override
    public void setPassErr() {
        showLongToast("密码重置失败");
    }

}
