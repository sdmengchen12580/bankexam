package com.udit.bankexam.ui.newui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.ui.zhibo.MeKeChengFragment;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.ViewUtils;

public class MyKcActivity extends BaseActivity {

    private ImageView img_top_return;

    private LinearLayout ll_kefu;

    private TextView text_top_centent;

    @Override
    public void initData() {
        this.ll_kefu.setVisibility(View.GONE);
        this.text_top_centent.setText("我的课程");
        this.img_top_return.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                MyKcActivity.this.finish();
            }
        });
    }

    @Override
    public void initListeners() {
    }

    @Override
    public void initViews(Bundle paramBundle) {
        ViewUtils.initView(this, com.udit.bankexam.R.id.class);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, MeKeChengFragment.getIntance());
        fragmentTransaction.commit();
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_my_kc);
    }
}

