package com.udit.bankexam.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.user.ModiftyPwdPresenter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.user.ModiftyPwdView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.ViewUtils;

/**
 * Created by zb on 2017/6/6.
 */

public class ModiftyPwdActivity extends BaseActivity<ModiftyPwdPresenter> implements ModiftyPwdView.View, View.OnClickListener {

    public static void startModiftyPwdActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, ModiftyPwdActivity.class));

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_modiftypwd);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("设置");
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private EditText edit_pwd_old, edit_pwd, edit_pwd_again;

    private Button btn_cz;

    private UserBean bean_user;

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        btn_cz.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new ModiftyPwdPresenter(this);

        bean_user = SaveUtils.getUser(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
            case R.id.btn_cz:
                String pwd_old = edit_pwd_old.getText().toString();
                String pwd = edit_pwd.getText().toString();
                String pwd_again = edit_pwd_again.getText().toString();
                if (MyCheckUtils.isEmpty(pwd_old)) {
                    showLongToast("旧密码不能为空");
                    return;
                }
                if (MyCheckUtils.isPasswordCheck(pwd, pwd_again, this)) {
                    mPresenter.modiftyPwd(this, bean_user.getUid(), pwd, edit_pwd_old.getText().toString());
                }
                break;
        }
    }

    @Override
    public void modiftySucess(String pwd) {
        showLongToast("密码修改成功");
        bean_user.setPass(pwd);
        SaveUtils.deleteUser(this);
        SaveUtils.saveUser(this, bean_user.getUid(), bean_user.getMobile(), pwd, bean_user.getPet(), "", "", "");
        this.onLowMemory();
        finish();
    }

    @Override
    public void modiftyErr(String msg) {
        if (msg != null) {
            showLongToast(msg);
            return;
        }
        showLongToast("密码修改失败");
    }
}
