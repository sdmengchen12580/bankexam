package com.udit.frame.freamwork.activity;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.PermissionChecker;
import android.view.WindowManager;

import com.jaeger.library.StatusBarUtil;
import com.udit.frame.utils.ProgressUtils;
import com.udit.frame.utils.StatusBar;
import com.udit.frame.utils.ToastUtil;
import com.udit.frame.view.CustomDialog;

@SuppressWarnings("rawtypes")
public abstract class BaseActivity<P extends BasePresenter> extends FragmentActivity implements IBaseView {

    private final String TAG = this.getClass().getSimpleName();

    private CustomDialog customDialog;
    protected P mPresenter;
    protected final int PERMISSION_REQUEST_CAMEAR_CODE1 = 0x1;//上部的身份证
    protected final int PERMISSION_REQUEST_CAMEAR_CODE2 = 0x2;//下部的身份证
    protected final int PERMISSION_REQUEST_CAMEAR_CODE3 = 0x3;//拍照+相册
    protected final int PERMISSION_REQUEST_CAMEAR_CODE = 0x4;
    private static BaseActivity curActivity;
    protected int mNoPermissionIndex = 0;
    //all
    protected final String[] permissionManifestAll = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
//            ,Manifest.permission.ACCESS_FINE_LOCATION
    };

    //camera
    protected final String[] permissionManifestCamera = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBar.transparencyBar(this);
        StatusBar.StatusBarDarkMode(this);
        StatusBarUtil.setLightMode(this);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        super.onCreate(savedInstanceState);
        setContentView();
        BaseApplication.getInstance().addActivity(this);
        initViews(savedInstanceState);
        initListeners();
        initData();
    }

    protected void showLoadingDialog(String hint) {
        if (customDialog == null) {
            customDialog = new CustomDialog(this, hint);
        }
        customDialog.setContent(hint);
        customDialog.show();
    }

    protected void hideLoadingDialog() {
        if (customDialog != null) {
            if (customDialog.isShowing()) {
                customDialog.dismiss();
            }
        }
    }

    protected void hideLoadingDialogDelay() {
        if (customDialog != null) {
            if (customDialog.isShowing()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        customDialog.dismiss();
                    }
                }, 500);
            }
        }
    }

    protected void changeAct(Bundle paramBundle, Class<?> paramClass) {
        Intent intent = new Intent();
        if (paramBundle != null)
            intent.putExtras(paramBundle);
        intent.setClass(getActivity(), paramClass);
        getActivity().startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public int getMyColor(int resId) {
        return getResources().getColor(resId);
    }

    public abstract void setContentView();

    public abstract void initViews(Bundle bundle);

    public abstract void initListeners();

    public abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
        if(curActivity!=null){
            curActivity = getActivity();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void showProgressDialog(String content) {
        ProgressUtils.showProgressDlg(content, this);
    }

    @Override
    public void dismissProgressDialog() {
        ProgressUtils.stopProgressDlg();
    }

    @Override
    public void showShortToast(String message) {
        ToastUtil.showMessage(this, message);

    }

    //检测是否有权限 1：camera
    protected boolean permissionCheck(int type) {
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        String permission;
        if (type == 1) {
            for (int i = 0; i < permissionManifestAll.length; i++) {
                permission = permissionManifestAll[i];
                mNoPermissionIndex = i;
                if (PermissionChecker.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionCheck = PackageManager.PERMISSION_DENIED;
                }
            }
        } else if (type == 2) {
            for (int i = 0; i < permissionManifestCamera.length; i++) {
                permission = permissionManifestCamera[i];
                mNoPermissionIndex = i;
                if (PermissionChecker.checkSelfPermission(this, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    permissionCheck = PackageManager.PERMISSION_DENIED;
                }
            }
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void showLongToast(String message) {
        ToastUtil.showMessageLong(this, message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.detachView();
        }
    }

    public static BaseActivity getCurActivity() {
        return curActivity;
    }

    protected BaseActivity getActivity() {
        return this;
    }

    protected Application getAppApplication() {
        return (Application) getApplication();
    }


}
