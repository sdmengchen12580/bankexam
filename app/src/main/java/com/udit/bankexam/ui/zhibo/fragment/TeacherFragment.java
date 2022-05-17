package com.udit.bankexam.ui.zhibo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.TeacherBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.bankexam.bean.ZhiboKeChengBean;
import com.udit.bankexam.presenter.zhibo.TeacherPresenter;
import com.udit.bankexam.ui.zhibo.fragment.adapter.KeChengBiaoAdapter;
import com.udit.bankexam.ui.zhibo.fragment.adapter.TeacherAdapter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.zhibo.TeacherView;
import com.udit.frame.freamwork.activity.BaseFragment;
import com.udit.frame.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/5/3.
 */

public class TeacherFragment extends BaseFragment<TeacherPresenter> implements TeacherView.View {

    private static TeacherFragment FRAGMENT;

    public static TeacherFragment getIntance()
    {
        if(FRAGMENT==null)
        {
            FRAGMENT = new TeacherFragment();
        }
        return FRAGMENT;
    }

    private View mView;

    private ListView listview_teacher;

    private List<TeacherBean> mlist;

    private TeacherAdapter adapter;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_teacher,null);


        return mView;
    }

    @Override
    public void initViews() {

        try {
            ViewUtils.initView(this,mView,R.id.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter = new TeacherPresenter(this);

        mlist = new ArrayList<>();
        adapter = new TeacherAdapter(mlist,getActivity());
        listview_teacher.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        UserBean bean_user = SaveUtils.getUser(getActivity());
        ZhiBoBean bean_zhibo = (ZhiBoBean) getArguments().getSerializable("zhibo_bean");

        mPresenter.getTeacherInfo(bean_user.getUid(),bean_zhibo.getLID());
    }

    @Override
    public void setTeacher(List<TeacherBean> list) {
        mlist.clear();
        if(list!=null)
        {
            mlist.addAll(list);
            adapter.notifyDataSetChanged();
        }
        else
            adapter.notifyDataSetChanged();
    }
}
