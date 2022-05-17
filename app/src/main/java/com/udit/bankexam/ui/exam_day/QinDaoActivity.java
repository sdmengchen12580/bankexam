package com.udit.bankexam.ui.exam_day;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.Del;
import com.udit.bankexam.bean.QianDao;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.presenter.exam_day.QianDaoPresenter;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.bankexam.utils.TimeUtils;
import com.udit.bankexam.view.QianDaoPop;
import com.udit.bankexam.view.exam_day.QianDaoView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.http.HttpTask;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.freamwork.http.RequestObject;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;
import com.zhy.android.percent.support.PercentRelativeLayout;

import java.util.HashMap;

/**
 * Created by zb on 2017/6/29.
 */

public class QinDaoActivity extends BaseActivity<QianDaoPresenter> implements QianDaoView.View, View.OnClickListener {

    private PercentRelativeLayout rl_layout;
    private UserBean bean_user;
    private QianDaoPop qianDaoPop;
    private ImageView img_qiandao, img_day, img_top_return;
    private TextView text_qiandao, text_top_centent;
    private TextView tv_qiandao;

    public static void startQianDaoActivity(BaseActivity<?> mActivity) {
        mActivity.startActivity(new Intent(mActivity, QinDaoActivity.class));
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_qiandao);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_qiandao.setVisibility(View.INVISIBLE);
        text_top_centent.setText("每日一练 天天签到");
    }

    @Override
    public void initListeners() {
        img_day.setOnClickListener(this);
        img_qiandao.setOnClickListener(this);
        img_top_return.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new QianDaoPresenter(this);
        bean_user = SaveUtils.getUser(this);
        mPresenter.login(bean_user.getMobile(), bean_user.getPass());
    }

    protected void onResume() {
        super.onResume();
        this.rl_layout.post(new Runnable() {
            public void run() {
                int i = ((WindowManager) QinDaoActivity.this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
                int j = i * 742 / 460;
                ViewGroup.LayoutParams layoutParams = QinDaoActivity.this.rl_layout.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    layoutParams.width = i;
                    layoutParams.height = j;
                    QinDaoActivity.this.rl_layout.setLayoutParams(layoutParams);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                finish();
                break;
            case R.id.img_day:
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                ExamDayActivity.startExamDayActivity(this);
                break;
            case R.id.img_qiandao:
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                if (!tv_qiandao.getText().toString().equals("签到")) {
                    return;
                }
                qiandao();
                mPresenter.doCard(bean_user.getUid());
                break;
        }
    }

    @Override
    public void loginOk(UserBean bean) {
        if (bean != null) {
            String signDay = bean.getSignDays();
            String s = String.format(getString(R.string.string_leijiqiandao), signDay);
            text_qiandao.setText(s);
        } else {
            String s = String.format(getString(R.string.string_leijiqiandao), "0");
            text_qiandao.setText(s);
        }
        text_qiandao.setVisibility(View.VISIBLE);
        //签到按钮的变化
        try {
            String date = TimeUtils.getTimeToday();
            String dateArr[] = date.split("-");
            String dateArrApi[] = bean.getLastSign().split(" ")[0].split("-");
            if (Integer.parseInt(dateArr[0]) == Integer.parseInt(dateArrApi[0])
                    && Integer.parseInt(dateArr[1]) == Integer.parseInt(dateArrApi[1])
                    && Integer.parseInt(dateArr[2]) == Integer.parseInt(dateArrApi[2])) {
                String signDay = bean.getSignDays();
                String s = String.format(getString(R.string.string_leijiqiandao), signDay);
                tv_qiandao.setText(s);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void qiandao(UserBean bean) {
        if (bean != null) {
            String signDay = bean.getSignDays();
            String s = String.format(getString(R.string.string_leijiqiandao), signDay);
            this.text_qiandao.setText(s);
            this.tv_qiandao.setText(s);
        } else {
            String s = String.format(getString(R.string.string_leijiqiandao), "0");
            this.text_qiandao.setText(s);
            this.tv_qiandao.setText(s);
        }
        this.text_qiandao.setVisibility(View.VISIBLE);
        qianDaoPop = new QianDaoPop(QinDaoActivity.this);
        qianDaoPop.showPop(bean.getSignDays());
    }

    //新签到
    private void qiandao() {
        try {
            String signDate = TimeUtils.getTimeToday();
            String sessionKey = (String) SpUtil.get(this, "sessionKey", "");
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put("appId", IHTTP.APP_ID);
            map_params.put("sessionKey", sessionKey);
            map_params.put("signDate", signDate);//2020-10-23
            setHttp(map_params, IHTTP.QIANDAO, new IHttpResponseListener() {

                @Override
                public void onError(String errStr) {

                }

                @Override
                public void doHttpResponse(String json) {
                    MyLogUtils.e("测试新接口", "json=" + json);
//                    QianDao bean = new Gson().fromJson(json, QianDao.class);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e("测试新接口", e.getMessage());
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
