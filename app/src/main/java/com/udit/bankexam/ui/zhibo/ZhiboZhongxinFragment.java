package com.udit.bankexam.ui.zhibo;

import java.util.ArrayList;
import java.util.List;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.zhibo.ZhiboZhongxinPresenter;
import com.udit.bankexam.ui.zhibo.adapter.Adapter_ZX;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.zhibo.ZhiboZhongxinView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseFragment;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

@SuppressLint("InflateParams")
public class ZhiboZhongxinFragment extends BaseFragment<ZhiboZhongxinPresenter> implements ZhiboZhongxinView.View {
    private final String TAG = this.getClass().getSimpleName();

    public static ZhiboZhongxinFragment getIntance() {
        ZhiboZhongxinFragment ZHIBOZHONGXINFRAGMENT = new ZhiboZhongxinFragment();
        return ZHIBOZHONGXINFRAGMENT;
    }

    private View mView;

    private ListView listview_zhongxing;

    private List<ZhiBoBean> mlist_zb;

    private Adapter_ZX adapter;

    private UserBean bean_user;

    private BaseActivity<?> mContent;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_zhibozhongxin, null);
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
        listview_zhongxing.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ZhiBoBean bean = mlist_zb.get(arg2);
                KeChengDetailActivity.startKeChengDetailActivity((BaseActivity<?>) getActivity(), bean,
                        Constant.RESULT.RESULT_ZHIBO_DETAIL);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyLogUtils.d(TAG, "onActivityResult____requestCode:" + requestCode + "   resultCode" + resultCode);
        if (requestCode == Constant.RESULT.RESULT_ZHIBO_DETAIL && resultCode == mContent.RESULT_OK) {
            mPresenter.getZhiBoList(bean_user.getUid());
        }
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        bean_user = SaveUtils.getUser(getActivity());
        mContent = (BaseActivity<?>) getActivity();
        mPresenter = new ZhiboZhongxinPresenter(this);

        mlist_zb = new ArrayList<>();

        adapter = new Adapter_ZX(getActivity(), mlist_zb);

        listview_zhongxing.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        mPresenter.getZhiBoList(bean_user.getUid());

    }

    @Override
    public void setZhiBo(List<ZhiBoBean> list_zb) {
        mlist_zb.clear();
        if (list_zb != null && list_zb.size() > 0)
            mlist_zb.addAll(list_zb);
        adapter.notifyDataSetChanged();
    }

}
