package com.udit.frame.freamwork.activity;

import java.util.HashSet;
import java.util.Set;



import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udit.frame.utils.ProgressUtils;
import com.udit.frame.utils.ToastUtil;


/**
 *自定义fragment 的基础类  
 * @author 曾宝
 * @version  [V1.00, 2015-3-31]
 * @see  [相关类/方法]
 * @since V1.00
 */
@SuppressWarnings("rawtypes")
@SuppressLint("HandlerLeak")
public abstract class BaseFragment<P extends BasePresenter>  extends Fragment implements IBaseView
{
    //TAG
    private static final  String TAG=BaseFragment.class.getSimpleName();
    
    protected P mPresenter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = setContentView(inflater,container,savedInstanceState);
        
        return view;
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initData(savedInstanceState);
        initListeners();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    public abstract View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    
    public abstract void initViews();
    
    public abstract void initListeners();
    
    public abstract void initData(Bundle savedInstanceState);
    
    @Override
    public void showProgressDialog(String content)
    {
        ProgressUtils.showProgressDlg(content,getActivity());
    }

    @Override
    public void dismissProgressDialog()
    {
       ProgressUtils.stopProgressDlg();
    }

    @Override
    public void showShortToast(String message)
    {
        ToastUtil.showMessage(getActivity(), message);
        
    }

    @Override
    public void showLongToast(String message)
    {
        ToastUtil.showMessageLong(getActivity(), message);
    }
}
