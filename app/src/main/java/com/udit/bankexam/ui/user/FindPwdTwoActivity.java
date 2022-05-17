package com.udit.bankexam.ui.user;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant.IntentParams;
import com.udit.bankexam.presenter.user.FindPwdTwoPresenter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.user.FindPwdTwoView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FindPwdTwoActivity extends BaseActivity<FindPwdTwoPresenter> implements FindPwdTwoView.View, OnClickListener {
    public static void startFindPwdTwoActivity(BaseActivity<?> mActivity, String phone) {
        Intent intent = new Intent(mActivity, FindPwdTwoActivity.class);
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
        text_top_centent.setText("重置密码");
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
        btn_cz.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new FindPwdTwoPresenter(this);
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
