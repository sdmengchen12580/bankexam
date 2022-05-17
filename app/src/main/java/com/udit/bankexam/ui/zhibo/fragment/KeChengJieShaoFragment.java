package com.udit.bankexam.ui.zhibo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.zhibo.KeChengJieShaoPresenter;
import com.udit.bankexam.view.zhibo.KeChengJieShaoView;
import com.udit.frame.freamwork.activity.BaseFragment;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;
import com.udit.frame.utils.WebUtil;

/**
 * Created by zb on 2017/5/3.
 */

public class KeChengJieShaoFragment extends BaseFragment<KeChengJieShaoPresenter> implements KeChengJieShaoView.View {

    private final String TAG = this.getClass().getSimpleName();

    private static KeChengJieShaoFragment FRAGMENT;

    public static KeChengJieShaoFragment getIntance()
    {
        if(FRAGMENT==null)
        {
            FRAGMENT = new KeChengJieShaoFragment();
        }
        return FRAGMENT;
    }

    private View mView;
    private WebView webview_kechengjieshao;

    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_kechengjieshao,null);


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
        mPresenter = new KeChengJieShaoPresenter(this);
        ZhiBoBean bean_zhibo = (ZhiBoBean) getArguments().getSerializable("zhibo_bean");
        //AllScreen
        WebUtil.WebInit(webview_kechengjieshao);
        String content = bean_zhibo.getAllScreen();
        content = content.replaceAll(Constant.IMAGE.IMG_OLD_BEGIN, Constant.IMAGE.IMG_NEW_BEGIN);

        WebUtil.WebInit(webview_kechengjieshao);
        MyLogUtils.e(TAG, content);
        webview_kechengjieshao.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
        //webview_kechengjieshao.loadData(bean_zhibo.getAllScreen(),"text/html; charset=UTF-8",null);
    }
}
