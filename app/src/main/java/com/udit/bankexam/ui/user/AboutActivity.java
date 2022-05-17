package com.udit.bankexam.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.presenter.user.AboutPresenter;
import com.udit.bankexam.view.user.AboutView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;

import org.w3c.dom.Text;

/**
 * Created by zb on 2017/5/19.
 */

public class AboutActivity extends BaseActivity<AboutPresenter> implements AboutView.View, View.OnClickListener {

    public static void startAboutActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, AboutActivity.class));
    }

    private ImageView img_top_return;

    private TextView text_top_centent;

    private LinearLayout ll_phone;

    private TextView about_version;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_about);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("关于我们");
        double version = Utils.getVersionCode(this);
        about_version.setText("银行易考 V" + version);
        about_version.setVisibility(View.VISIBLE);
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
        ll_phone.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new AboutPresenter(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
            case R.id.ll_phone:
                Utils.sysPhone_call("025-83692169", this);
                break;
        }
    }
}
