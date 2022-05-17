package com.udit.bankexam.ui.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.ui.zhibo.ZhiboZhongxinFragment;
import com.udit.bankexam.utils.FastClickUtils;

import java.util.ArrayList;
import java.util.List;

public class KeChengFragment extends Fragment {

    private List<Fragment> fragments ;

    private String[] mTitles = {"视频", "直播"};

    private TabLayout tab;

    private TextView tv_my_kc;

    private View view;

    private ViewPager vp;

    public static KeChengFragment newInstance() {
        return new KeChengFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle paramBundle) {
        super.onActivityCreated(paramBundle);
        fragments = new ArrayList();
        this.fragments.add(VideoFragment_new.getIntance());
        this.fragments.add(ZhiboZhongxinFragment.getIntance());
        for (int i = 0; i < fragments.size(); i++) {
            this.tab.addTab(this.tab.newTab().setText(this.mTitles[i]));
        }
        this.vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            public int getCount() {
                return KeChengFragment.this.fragments.size();
            }

            public Fragment getItem(int param1Int) {
                return (Fragment) KeChengFragment.this.fragments.get(param1Int);
            }

            public CharSequence getPageTitle(int param1Int) {
                return KeChengFragment.this.mTitles[param1Int];
            }
        });
        this.tab.setupWithViewPager(this.vp);
        this.vp.setOffscreenPageLimit(this.fragments.size());
        this.tv_my_kc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (FastClickUtils.isFastClick())
                    return;
                KeChengFragment.this.getActivity().startActivity(new Intent(KeChengFragment.this.getActivity(), com.udit.bankexam.ui.newui.MyKcActivity.class));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View view1 = paramLayoutInflater.inflate(R.layout.fragment_ke_cheng, paramViewGroup, false);
        this.tab = (TabLayout) view1.findViewById(R.id.tab);
        this.vp = (ViewPager) view1.findViewById(R.id.vp);
        this.tv_my_kc = (TextView) view1.findViewById(R.id.tv_my_kc);
        return view1;
    }
}
