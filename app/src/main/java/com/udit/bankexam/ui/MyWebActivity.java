package com.udit.bankexam.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.presenter.MyWebPresenter;
import com.udit.bankexam.view.MyWebView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.ViewUtils;
import com.udit.frame.utils.WebUtil;

/**
 * Created by zb on 2017/6/13.
 */

public class MyWebActivity extends BaseActivity<MyWebPresenter> implements MyWebView.View, View.OnClickListener {

    private ImageView img_top_return;

    private TextView text_top_centent;

    private WebView webview_info;

    private ScrollView scrollview;

    private boolean selfDeal = false;

    private TextView tv_content;

    private TextView tv_time;

    private TextView tv_title;

    public static void startMyWebActivity(BaseActivity<?> mActivity,String name,String msg)
    {
        Intent intent = new Intent(mActivity,MyWebActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("msg",msg);
        mActivity.startActivity(intent);
    }

    public static void startMyWebActivity(BaseActivity<?> paramBaseActivity, String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
        Intent intent = new Intent(paramBaseActivity, MyWebActivity.class);
        intent.putExtra("name", paramString1);
        intent.putExtra("msg", paramString2);
        intent.putExtra("selfDeal", paramBoolean);
        intent.putExtra("creattime", paramString3);
        paramBaseActivity.startActivity(intent);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_web);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this,R.id.class);
        text_top_centent.setText("我的消息");
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
    }

    @Override
    public void initData() {
        this.mPresenter = new MyWebPresenter(this);
        String str = getIntent().getStringExtra("msg");
        try {
            if (getIntent().getBooleanExtra("selfDeal", false) == true)
                this.selfDeal = true;
        } catch (Exception exception) {
            this.selfDeal = false;
        }
        if (this.selfDeal) {
            this.webview_info.setVisibility(View.GONE);
            this.scrollview.setVisibility(View.VISIBLE);
            this.tv_title.setText(getIntent().getStringExtra("name"));
            this.tv_time.setText(getIntent().getStringExtra("creattime"));
            this.tv_content.setText(getIntent().getStringExtra("msg"));
            return;
        }
        WebUtil.WebInit(this.webview_info);
        if (!MyCheckUtils.isEmpty(str))
            this.webview_info.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_top_return:
                this.onLowMemory();
                finish();
                break;
        }
    }
}
