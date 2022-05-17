package com.udit.bankexam.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.presenter.MyWebPresenter;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.view.MyWebView;
import com.udit.frame.common.down.DownFile;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;
import com.udit.frame.utils.WebUtil;

import java.net.URLDecoder;

/**
 * Created by zb on 2017/6/13.
 */

public class MyWebUriActivity extends BaseActivity<MyWebPresenter> implements MyWebView.View, View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private ImageView img_top_return;

    private TextView text_top_centent;

    private WebView webview_info;

    public static void startMyWebUriActivity(BaseActivity<?> mActivity, String name, String msg) {
        Intent intent = new Intent(mActivity, MyWebUriActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("msg", msg);
        mActivity.startActivity(intent);
    }


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_web);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        text_top_centent.setText("详情");
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
        webview_info.setDownloadListener(new MyDown());
    }

    @Override
    public void initData() {
        mPresenter = new MyWebPresenter(this);
        UserBean bean = SaveUtils.getUser(this);
        String msg = getIntent().getStringExtra("msg");
        String name = getIntent().getStringExtra("name");
        text_top_centent.setText(name);
//        WebUtil.WebInit(webview_info);
        if (!MyCheckUtils.isEmpty(msg)) {
            if (name.equals("网申模拟")) {
                msg += bean.getMobile();
            }
            WebSettings setting = webview_info.getSettings();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 设置允许加载混合内容
                webview_info.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
            setting.setJavaScriptEnabled(true);
            setting.setDefaultTextEncodingName("utf-8"); // 设置文本编码
            setting.setAppCacheEnabled(true);
            setting.setCacheMode(WebSettings.LOAD_NO_CACHE);// 设置缓存模式</span>
            webview_info.setWebViewClient(new WebViewClient() {
                //当收到证书错误时，忽略掉，直接继续处理就行了
                @Override
                public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                    handler.proceed();
                }

                //return false;
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return super.shouldOverrideUrlLoading(view, url);
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                }
            });
            webview_info.loadUrl(msg);
        }
    }

    private class MyDown implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
           /* if (url.contains("http://10."))
            {

                // activity_noticedetail_web.loadUrl(IHTTP_IP.HTTP_APP);
            }
            else
            {*/
            DownFile file = new DownFile(MyWebUriActivity.this);
            String name = url.substring(url.lastIndexOf("/"));
            MyLogUtils.e(TAG, "down:" + name);
            file.setDownInfo(url, name);
            /*}*/
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                if (webview_info.canGoBack()) {
                    webview_info.goBack();
                } else
                    finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (webview_info.canGoBack()) {
            webview_info.goBack();
        } else
            finish();
    }
}
