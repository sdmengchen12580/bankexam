package com.udit.bankexam.ui.zhibo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.duobeiyun.def.controller.DefLiveActivity;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.bankexam.bean.ZhiboKeChengBean;
import com.udit.bankexam.presenter.zhibo.KeChengBiaoPresenter;
import com.udit.bankexam.ui.zhibo.fragment.adapter.KeChengBiaoAdapter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.zhibo.KeChengBiaoView;
import com.udit.frame.freamwork.activity.BaseFragment;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/5/3.
 */

public class KeChengBiaoFragment extends BaseFragment<KeChengBiaoPresenter> implements KeChengBiaoView.View {

    private static KeChengBiaoFragment FRAGMENT;

    public static KeChengBiaoFragment getIntance() {
        if (FRAGMENT == null) {
            FRAGMENT = new KeChengBiaoFragment();
        }
        return FRAGMENT;
    }


    private View mView;

    private ListView listview_kechengbiao;

    private KeChengBiaoAdapter adapter;

    private List<ZhiboKeChengBean> mlist;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_kechengbiao, null);
        return mView;
    }

    @Override
    public void initViews() {
        try {
            ViewUtils.initView(this, mView, R.id.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter = new KeChengBiaoPresenter(this);

        mlist = new ArrayList<>();
        adapter = new KeChengBiaoAdapter(mlist, getActivity());
        listview_kechengbiao.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        UserBean bean_user = SaveUtils.getUser(getActivity());
        ZhiBoBean bean_zhibo = (ZhiBoBean) getArguments().getSerializable("zhibo_bean");
        mPresenter.getKeChengBiao(bean_user.getUid(), bean_zhibo.getLID());
    }

    @Override
    public void setZhiboKeCheng(List<ZhiboKeChengBean> list) {
        mlist.clear();
        if (list != null) {
            mlist.addAll(list);
            adapter.notifyDataSetChanged();
        } else
            adapter.notifyDataSetChanged();
    }


}
