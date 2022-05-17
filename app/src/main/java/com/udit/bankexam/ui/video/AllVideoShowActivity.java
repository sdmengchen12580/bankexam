package com.udit.bankexam.ui.video;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.udit.bankexam.R;
import com.udit.bankexam.ui.home.fragment.VideoFragment_new;
import com.udit.frame.freamwork.activity.BaseActivity;

public class AllVideoShowActivity extends BaseActivity {

    @Override
    public void initData() {
    }

    @Override
    public void initListeners() {
    }

    @Override
    public void initViews(Bundle paramBundle) {
//    VideoFragment_new videoFragment_new = VideoFragment_new.getIntance(true);
        VideoFragment_new videoFragment_new = VideoFragment_new.getIntance(true);//fixme 临时修改的
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fravideo_container, videoFragment_new);
        fragmentTransaction.commit();
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_all_video_show);
    }
}
