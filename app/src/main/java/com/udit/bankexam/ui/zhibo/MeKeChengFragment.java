package com.udit.bankexam.ui.zhibo;

import java.util.ArrayList;
import java.util.List;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.zhibo.MeKeChengPresenter;
import com.udit.bankexam.ui.zhibo.adapter.Adapter_ShouCan;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.zhibo.MeKeChengView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseFragment;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

@SuppressLint("InflateParams")
public class MeKeChengFragment extends BaseFragment<MeKeChengPresenter> implements MeKeChengView.View, OnClickListener {
    private final String TAG = this.getClass().getSimpleName();

    private static MeKeChengFragment MEKECHENGFRAGMENT;

    public static MeKeChengFragment getIntance() {
        if (MEKECHENGFRAGMENT == null) {
            MEKECHENGFRAGMENT = new MeKeChengFragment();
        }
        return MEKECHENGFRAGMENT;
    }

    private View mView;

    private ListView listview_kecheng;

    private Adapter_ShouCan adapter;

    private List<ZhiBoBean> mlist;

    private LinearLayout ll_kecheng_date, ll_kecheng_shoucang;

    private UserBean bean_user;

    private RelativeLayout rl_no_date;

    private BaseActivity<?> mActivity;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mekecheng, null);
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
        ll_kecheng_date.setOnClickListener(this);

        ll_kecheng_shoucang.setOnClickListener(this);

        listview_kecheng.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ZhiBoBean bean = mlist.get(position);

                ZhiboLiebiaoActivity.startZhiboLiebiaoActivity(mActivity, bean);

            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        bean_user = SaveUtils.getUser(getActivity());

        mActivity = (BaseActivity<?>) getActivity();

        mPresenter = new MeKeChengPresenter(this);

        mlist = new ArrayList<>();

        adapter = new Adapter_ShouCan(getActivity(), mlist);

        listview_kecheng.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        mPresenter.getMyKeCheng(bean_user.getUid());

    }

    @Override
    public void setKeCheng(List<ZhiBoBean> list) {
        mlist.clear();
        if (list != null) {
            mlist.addAll(list);
        }
        adapter.notifyDataSetChanged();
        rl_no_date.setVisibility((mlist.size() > 0) ? View.GONE : View.VISIBLE);
        listview_kecheng.setVisibility((this.mlist.size() > 0) ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_kecheng_date:
                DateActivity.startDateActivity((BaseActivity<?>) getActivity());
                break;
            case R.id.ll_kecheng_shoucang:

                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        MyLogUtils.d(TAG, "onActivityResult____requestCode:" + requestCode + "   resultCode" + resultCode);
        if (requestCode == Constant.RESULT.RESULT_ZHIBO_DETAIL && resultCode == mActivity.RESULT_OK) {
            mPresenter.getMyKeCheng(bean_user.getUid());
        }
    }
}
