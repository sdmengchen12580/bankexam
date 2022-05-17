package com.udit.frame.utils;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebUtil
{
    public static void WebInit(WebView view)
    {
        WebSettings setting = view.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setDefaultTextEncodingName("utf-8"); // 设置文本编码
        setting.setAppCacheEnabled(true);
        setting.setCacheMode(WebSettings.LOAD_NO_CACHE);// 设置缓存模式</span>
        view.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                return false;

            }
        });

    }
}
