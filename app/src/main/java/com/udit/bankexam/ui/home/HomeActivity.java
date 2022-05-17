package com.udit.bankexam.ui.home;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.AppParams;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.ui.exam_day.QinDaoActivity;
import com.udit.bankexam.ui.home.fragment.KeChengFragment;
import com.udit.bankexam.ui.home.fragment.QuesDataFragment;
import com.udit.bankexam.ui.home.fragment.VideoFragment_new;
import com.udit.bankexam.ui.newui.ques.QuesFragment;
import com.udit.bankexam.utils.SpUtil;
import com.udit.frame.freamwork.http.HttpTask;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.freamwork.http.RequestObject;
import com.udit.frame.freamwork.updateManager.AppVersion;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.presenter.home.HomePresenter;
import com.udit.bankexam.ui.home.fragment.MainFragment;
import com.udit.bankexam.ui.home.fragment.MeFragment;
import com.udit.bankexam.ui.home.fragment.ZhiboFragment;
import com.udit.bankexam.ui.home.fragment.ZixunFragment;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.home.HomeView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.updateManager.UpdateManager;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import java.io.IOException;
import java.util.HashMap;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeView.View {

    public static void startHomeActivity(BaseActivity<?> activity) {
        activity.startActivity(new Intent(activity, HomeActivity.class));
        activity.onLowMemory();
        activity.finish();
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_home);
    }

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment mContent;
    public static RadioGroup group_main;
    private long firstTime;
    public static BaseActivity<?> HOMEACTIVITY;
    private final String TAG = this.getClass().getSimpleName();
    private UserBean bean_user;
    private ImageView img_day;
    public RadioButton radio_tuku;

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        String str = (String) SpUtil.get(this, "imgurl", "");
        if (!str.equals("")) {
            setAvatar(str);
        }
    }

    @Override
    public void initListeners() {
        this.img_day.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                QinDaoActivity.startQianDaoActivity(HomeActivity.this.getActivity());
            }
        });

        group_main.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radio_zixun:
                        HomeActivity.this.replaceFragment(ZixunFragment.getIntance());
                        break;
                    case R.id.radio_zhibo:
                        HomeActivity.this.replaceFragment(ZhiboFragment.getIntance());
                        break;
                    case R.id.radio_video:
                        HomeActivity.this.replaceFragment(VideoFragment_new.getIntance());
                        break;
                    case R.id.radio_tuku:
                        HomeActivity.this.replaceFragment(QuesDataFragment.newInstance());
                        break;
                    case R.id.radio_me:
                        HomeActivity.this.replaceFragment(MeFragment.getIntance());
                        break;
                    case R.id.radio_main:
                        HomeActivity.this.replaceFragment(MainFragment.getIntance());
                        break;
                    case R.id.radio_kechen:
                        HomeActivity.this.replaceFragment(KeChengFragment.newInstance());
                        break;
                    case R.id.radio_wenda:
                        HomeActivity.this.replaceFragment(QuesFragment.newInstance());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void changeToTiku() {
        this.radio_tuku.setChecked(true);
        replaceFragment(QuesDataFragment.newInstance());
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        MyLogUtils.d(TAG, "onActivityResult____requestCode:" + requestCode + "   resultCode" + resultCode);
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getFragments() == null || fm.getFragments().size() == 0) {
            MyLogUtils.e(TAG, "Activity result fragment index out of range: 0x"
                    + Integer.toHexString(requestCode) + "" + "  " + fm.getFragments().size());
            return;
        }
        for (int i = 0; i < fm.getFragments().size(); i++) {
            Fragment frag = fm.getFragments().get(i);
            if (frag == null) {
                MyLogUtils.e(TAG, "Activity result no fragment exists for index: 0x"
                        + Integer.toHexString(requestCode));
            } else {
                if (requestCode == Constant.RESULT.RESULT_VIDEO_TYPE_ONE) {
                    if (frag instanceof VideoFragment_new)
                        frag.onActivityResult(requestCode & 0xffff, resultCode, data);
                } else if (requestCode == Constant.RESULT.RESULT_MAIN_OUTLINE) {
                    if (frag instanceof MainFragment) {
                        frag.onActivityResult(requestCode & 0xffff, resultCode, data);
                    }
                } else if (requestCode == Constant.RESULT.RESULT_VIDEO_PAY) {
                    if (frag instanceof VideoFragment_new) {
                        frag.onActivityResult(requestCode & 0xffff, resultCode, data);
                    }
                } else if (requestCode == Constant.RESULT.RESULT_ZHIBO_DETAIL) {
                    if (frag instanceof ZhiboFragment)
                        frag.onActivityResult(requestCode & 0xffff, resultCode, data);
                } else if (requestCode == Constant.RESULT.RESULT_EXAM_HOME) {
                    if (frag instanceof MainFragment)
                        frag.onActivityResult(requestCode & 0xffff, resultCode, data);
                }
            }
        }
        return;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            String deviceToken = (String) SpUtil.get(HomeActivity.this, "umeng_token", "");
            if(deviceToken.equals("")){
                return;
            }
            mPresenter.updateUserToken(bean_user.getUid(), deviceToken);
        }
    };

    @Override
    public void initData() {
        bean_user = SaveUtils.getUser(this);
        mPresenter = new HomePresenter(this);
        new Thread(runnable).start();
        checkPermission();
        //获取配置
        mPresenter.getParams();

        mPresenter.updateApp();
        HOMEACTIVITY = this;
        fragmentManager = getSupportFragmentManager();
        replaceFragment(MainFragment.getIntance());
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            MyLogUtils.e(TAG, "CALL_PHONE--permission");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        }

    }


    public void replaceFragment(Fragment fragment) {
        MyLogUtils.e(TAG, "replaceFragment");
        if (mContent == null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_main, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (mContent != fragment) {
            MyLogUtils.e(TAG, "mContent != fragment");
            fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            if (!fragment.isAdded()) {
                // 先判断是否被add过
                fragmentTransaction.hide(mContent).add(R.id.fragment_main, fragment).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                fragmentTransaction.hide(mContent).show(fragment).commit(); // 隐藏当前的fragment，显示下一个
                mContent.onPause();
                fragment.onResume();
            }
        }
        mContent = fragment;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (mContent instanceof ZixunFragment) {
                    if (ZixunFragment.getIntance().getWebview().canGoBack()) {
                        ZixunFragment.getIntance().getWebview().goBack();
                        return true;
                    } else {
                        MyLogUtils.i(TAG, "-----KEYCODE_BACK-------");
                        long secondTime = System.currentTimeMillis();
                        if (secondTime - firstTime > 2000) { // 如果两次按键时间间隔大于2秒，则不退出
                            showLongToast(getString(R.string.string_back_two));
                            // showToast(R.string.string_back_two, Toast.LENGTH_SHORT);
                            firstTime = secondTime;// 更新firstTime
                            return true;
                        } else { // 两次按键小于2秒时，退出应用
                            MyLogUtils.i(TAG, "2次退出应用");
                            this.onLowMemory();
                            finish();
                            return true;
                        }
                    }
                } else {
                    MyLogUtils.i(TAG, "-----KEYCODE_BACK-------");
                    long secondTime = System.currentTimeMillis();
                    if (secondTime - firstTime > 2000) { // 如果两次按键时间间隔大于2秒，则不退出
                        showLongToast(getString(R.string.string_back_two));
                        // showToast(R.string.string_back_two, Toast.LENGTH_SHORT);
                        firstTime = secondTime;// 更新firstTime
                        return true;
                    } else { // 两次按键小于2秒时，退出应用
                        MyLogUtils.i(TAG, "2次退出应用");
                        this.onLowMemory();
                        finish();
                        return true;
                    }
                }

            default:
                return super.onKeyDown(keyCode, event);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void saveParams(AppParams params) {
        if (params != null)
            SaveUtils.saveParams(this, params.getFirstCnt(), params.getOutlineCnt());

    }

    @Override
    public void updateApp(AppVersion appVersion) {

        UpdateManager manager = new UpdateManager(this);

        try {
            // manager.checkUpdate(null);
            manager.checkUpdate(appVersion);
        } catch (IOException e) {


        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyLogUtils.e(TAG, "-------onPause--------");

    }

    @Override
    protected void onResume() {
        super.onResume();
        MyLogUtils.e(TAG, "-------onResume--------");
    }

    //上传头像
    public void setAvatar(String str) {
        try {
            HashMap<String, String> map_params = new HashMap<>();
            String sessionKey = (String) SpUtil.get(this, "sessionKey", "");
            map_params.put("appId", IHTTP.APP_ID);
            map_params.put("avatarUrl", str);
            map_params.put("sessionKey", sessionKey);
            setHttp(map_params, IHTTP.UP_IMG, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {
                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口上传头像", "json=" + json);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG, e.getMessage());
        }
    }

    protected void setHttp(HashMap<String, String> map, String address, IHttpResponseListener listener)
            throws Exception {
        try {
            RequestObject object = new RequestObject(address, map);
            new HttpTask().start(object, HttpTask.REQUEST_TYPE_POST, listener);
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
