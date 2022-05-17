package com.udit.bankexam.ui.newui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.view.LollipopFixedWebView;
import com.udit.frame.freamwork.activity.BaseActivity;

public class YinSiActivity extends BaseActivity {

    private ImageView img_top_return;
    private TextView text_top_centent;
    private LollipopFixedWebView webView;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_yin_si);
    }

    @Override
    public void initViews(Bundle bundle) {
        img_top_return = (ImageView) findViewById(R.id.img_top_return);
        text_top_centent = (TextView) findViewById(R.id.text_top_centent);
        text_top_centent.setText("隐私协议");
        webView = findViewById(R.id.webview);
        //加载网页
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return false;
            }
        });
        webView.loadUrl("http://ykv2.project.njagan.com/m/privacy.html");
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
            finish();
            return true;//super.onKeyDown(keyCode, event)
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.stopLoading();
            webView.setWebViewClient(null);
            webView.clearHistory();
            webView.clearCache(true);
            webView.loadUrl("about:blank");
            webView.pauseTimers();
            webView = null;
        }
    }
}
