package com.udit.bankexam;

import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.MainPresenter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.MainView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.ViewUtils;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView.View {

    private ImageView main_logo;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
    }

    @Override
    public void initListeners() {
    }

    @Override
    public void initData() {
        mPresenter = new MainPresenter(this);
        mPresenter.doAnimator();
    }

    @Override
    public void doAnimator() {
        main_logo.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity();
            }
        }, 3000);
    }

    @Override
    public void startActivity() {
        WelcomeActivity.startWelcomeActivity(this);
    }

    @Override
    public void loginOk(UserBean bean) {
        SaveUtils.saveUser(this, bean.getUid(), bean.getMobile(), bean.getPass(), bean.getPet(), bean.getAcc(), bean.getLastSign(), bean.getSignDays());
        WelcomeActivity.startWelcomeActivity(this);
    }

    @Override
    public void loginErr(String msg) {
        SaveUtils.deleteUser(this);
        WelcomeActivity.startWelcomeActivity(this);
    }
}
