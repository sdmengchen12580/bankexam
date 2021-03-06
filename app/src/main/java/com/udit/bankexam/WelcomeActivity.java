package com.udit.bankexam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.presenter.WelcomePresenter;
import com.udit.bankexam.ui.home.HomeActivity;
import com.udit.bankexam.ui.user.LoginActivity;
import com.udit.bankexam.utils.PushHelper;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.bankexam.view.HomeXyExitPop;
import com.udit.bankexam.view.HomeXyPop;
import com.udit.bankexam.view.WelcomeView;
import com.udit.frame.bugUtils.AppManger;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/5/5.
 */

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeView.View {

    private final String TAG = this.getClass().getSimpleName();

    public static void startWelcomeActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, WelcomeActivity.class));
        mActivity.onLowMemory();
        mActivity.finish();
    }

    private ImageView loading_welcome_iv;
    private RelativeLayout rl_circlepager;
    private ViewPager viewpager_circlepager;
    private CirclePageIndicator circleindicator;
    private GuidePagerAdapter adapter;
    private int num = 0;
    private Context mContext;
    private SparseArray<Bitmap> mList = new SparseArray<Bitmap>();
    public static PageLoadBroard pageLoadBroard;
    private List<String> list;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
    }

    @Override
    public void initListeners() {
        viewpager_circlepager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MyLogUtils.e(TAG, "postion:" + position);
                if (position == num - 1) {
                    handler.sendEmptyMessageDelayed(1, Constant.DataType.WelcomeTime);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {
        mContext = this;
        mPresenter = new WelcomePresenter(this);
        registreBroadcast();
        loading_welcome_iv.setVisibility(View.VISIBLE);
        Log.e("????????????: ", "??????????????????");
        mPresenter.getWelcome();
    }

    private void registreBroadcast() {
        IntentFilter loginFilter = new IntentFilter();
        loginFilter.addAction(Constant.Broard.WELCOME_LOAD);
        loginFilter.addAction(Constant.Broard.WELCOME_NEXT);
        loginFilter.addAction(Constant.Broard.WELCOME_GO);
        pageLoadBroard = new PageLoadBroard();
        registerReceiver(pageLoadBroard, loginFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (pageLoadBroard != null)
                unregisterReceiver(pageLoadBroard);
        } catch (Exception e) {

        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                MyLogUtils.i(TAG, "????????????????????????????????????");
                Intent intent = new Intent(Constant.Broard.WELCOME_NEXT);
                MyApplication.getInstance().sendBroadcast(intent);
            } else if (msg.what == 1) {
                MyLogUtils.i(TAG, "????????????????????????????????????");
                handler.removeMessages(0);
                Intent intent = new Intent(Constant.Broard.WELCOME_GO);
                MyApplication.getInstance().sendBroadcast(intent);
            }
        }
    };

    class PageLoadBroard extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //????????????
            if (Constant.Broard.WELCOME_LOAD.equals(intent.getAction())) {
                MyLogUtils.i(TAG, "??????????????????");
                // mList.add(BitmapFactory.decodeResource(getResources(), R.mipmap.home_welcome));
                adapter = new GuidePagerAdapter(WelcomeActivity.this, mList, WelcomeActivity.this);
                viewpager_circlepager.setAdapter(adapter);
                //?????????CirclePageIndicator ????????????ViewPager??????
                circleindicator.setViewPager(viewpager_circlepager);

                rl_circlepager.setVisibility(View.VISIBLE);
                loading_welcome_iv.setVisibility(View.GONE);
                handler.sendEmptyMessageDelayed(0, Constant.DataType.WelcomeTime);

            }
            if (Constant.Broard.WELCOME_NEXT.equals(intent.getAction())) {

                MyLogUtils.i(TAG, "??????????????????????????????------??????");
                int postion = viewpager_circlepager.getCurrentItem();
                postion++;
                if (postion < num) {
                    viewpager_circlepager.setCurrentItem(postion++);
                    handler.sendEmptyMessageDelayed(0, Constant.DataType.WelcomeTime);

                }
            }
            if (Constant.Broard.WELCOME_GO.equals(intent.getAction())) {
                UserBean bean = SaveUtils.getUser(mContext);
                //  UserBean bean = SaveUtils.getUser(this);
                if (bean != null && !MyCheckUtils.isEmpty(bean.getUid())
                        && !MyCheckUtils.isEmpty(bean.getMobile())
                        && !MyCheckUtils.isEmpty(bean.getPass())) {
                    HomeActivity.startHomeActivity((BaseActivity<?>) mContext);
                } else
                    LoginActivity.startLoginActivity((BaseActivity<?>) mContext);
            }
        }
    }

    //-------------------------------------????????????-------------------------------------
    private HomeXyPop homeXyPop;
    private PopupWindow popHomeXy;
    private HomeXyExitPop homeXyExitPop;
    private PopupWindow popHomeXyExit;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (homeXyPop != null) {
                if (popHomeXy != null) {
                    if (popHomeXy.isShowing()) {
                        return true;
                    }
                }
            }
            if (homeXyExitPop != null) {
                if (popHomeXyExit != null) {
                    if (popHomeXyExit.isShowing()) {
                        return true;
                    }
                }
            }
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void setWelcome(final List<String> list) {
        Log.e("????????????: ", "??????????????????");
        this.list = list;
        String aggreeurl = (String) SpUtil.get(WelcomeActivity.this, "isaggreeurl", "0");
        if (aggreeurl.equals("0")) {
            showAgreeXy();
        } else {
            showWelcome();
        }
    }

    //??????????????????
    private void showAgreeXy() {
        homeXyPop = new HomeXyPop(this);
        popHomeXy = homeXyPop.showPop(new HomeXyPop.ClickCallback() {
            @Override
            public void clickTrue() {
                PushHelper.init(MyApplication.getAppContext());
                SpUtil.put(WelcomeActivity.this, "isaggreeurl", "1");
                showWelcome();
            }

            @Override
            public void clickFalse() {
                showExitXy();
            }
        });
    }

    //??????????????????
    private void showExitXy() {
        homeXyExitPop = new HomeXyExitPop(WelcomeActivity.this);
        popHomeXyExit = homeXyExitPop.showPop(new HomeXyExitPop.ClickCallback() {
            @Override
            public void clickTrue() {
                //????????????
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //????????????
                        showAgreeXy();
                    }
                }, 300);
            }

            @Override
            public void clickFalse() {
                finish();
            }
        });
    }

    //??????????????????????????????
    private void showWelcome() {
        if (list != null && list.size() > 0) {
            for (String str : list) {
                ImageLoader.getInstance().loadImage(IHTTP.IP + "/" + str,
                        new ImageLoadingListener() {
                            @Override
                            public void onLoadingStarted(String arg0, View arg1) {
                            }

                            @Override
                            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                                MyLogUtils.e(TAG, "onLoadingFailed");
                                Intent intent = new Intent(Constant.Broard.WELCOME_GO);
                                MyApplication.getInstance().sendBroadcast(intent);
                            }

                            @Override
                            public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
                                num = num + 1;
                                int indexs = arg0.indexOf("upload");
                                String arg_s = arg0.substring(indexs);
                                int index = list.indexOf(arg_s);
                                mList.put(index, arg2);
                                MyLogUtils.e(TAG, "arg0:" + arg0);
                                if (num == list.size()) {//???????????????????????????????????????????????????????????????
                                    Intent intent = new Intent(Constant.Broard.WELCOME_LOAD);
                                    MyApplication.getInstance().sendBroadcast(intent);
                                }
                            }

                            @Override
                            public void onLoadingCancelled(String arg0, View arg1) {
                            }
                        });
            }
            Log.e("????????????: ", "??????????????????");
        } else {
            UserBean bean = SaveUtils.getUser(this);
            if (bean != null && !MyCheckUtils.isEmpty(bean.getUid())
                    && !MyCheckUtils.isEmpty(bean.getMobile())
                    && !MyCheckUtils.isEmpty(bean.getPass())) {
                HomeActivity.startHomeActivity(this);
            } else
                LoginActivity.startLoginActivity(this);
        }
    }
}
