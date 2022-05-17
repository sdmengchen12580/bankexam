package com.udit.bankexam.ui.zhibo;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.duobeiyun.type.RoleType;
import com.duobeiyun.widget.JYVideoView;
import com.duobeiyun.widget.LivePlayer;
import com.udit.bankexam.R;
import com.udit.bankexam.presenter.zhibo.ZhiboDetailPresenter;
import com.udit.bankexam.view.zhibo.ZhiboDetailView;
import com.udit.frame.freamwork.activity.BaseActivity;

/**
 * Created by zb on 2017/5/3.
 */

public class ZhiboDetailActivity extends BaseActivity<ZhiboDetailPresenter> implements ZhiboDetailView.View {

    public static void startZhiboDetailActivity(BaseActivity<?> mActivity)
    {
        Intent intent = new Intent(mActivity,ZhiboDetailActivity.class);
        mActivity.startActivity(intent);

    }

    private final String TAG = this.getClass().getSimpleName();


    @Override
    public void setContentView() {
        setContentView(com.duobei.dbysdk.R.layout.activity_def_live);
    }

    @Override
    public void initViews(Bundle bundle) {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {

    }
}
