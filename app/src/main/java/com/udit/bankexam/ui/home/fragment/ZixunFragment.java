package com.udit.bankexam.ui.home.fragment;

import com.udit.bankexam.R;
import com.udit.bankexam.presenter.home.fragment.ZixunPresenter;
import com.udit.bankexam.presenter.zixun.ZiXunPresenter;
import com.udit.bankexam.view.home.fragment.ZixunView;
import com.udit.bankexam.view.zixun.ZiXunView;
import com.udit.frame.freamwork.activity.BaseFragment;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;
import com.udit.frame.utils.WebUtil;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class ZixunFragment extends BaseFragment<ZixunPresenter> implements ZixunView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private static ZixunFragment ZIXUNFRAGMENT;

    public static ZixunFragment getIntance() {
        if (ZIXUNFRAGMENT == null) {
            ZIXUNFRAGMENT = new ZixunFragment();
        }
        return ZIXUNFRAGMENT;
    }

    private View mView;

    private ImageView img_top_return;

    private TextView text_top_centent;

    private final String IP = "http://m.yinhangzhaopin.com/";

    private WebView webview;


    public WebView getWebview() {
        return webview;
    }


    @Override
    public View setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_zixun, null);
        return mView;
    }

    @Override
    public void initViews() {
        try {
            ViewUtils.initView(this, mView, R.id.class);
            img_top_return.setVisibility(View.INVISIBLE);
            text_top_centent.setText("资讯");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);

        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                if (!url.equals("about:blank")) {
                    if (IP.contains(url)) {
                        img_top_return.setVisibility(View.INVISIBLE);
                    } else {
                        img_top_return.setVisibility(View.VISIBLE);
                    }
                }
                super.onPageStarted(view, url, favicon);
            }
        });
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter = new ZixunPresenter(this);

        WebUtil.WebInit(webview);

        webview.loadUrl(IP);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:

                if (webview.canGoBack()) {
                    webview.goBack();
                }

                break;
        }
    }
}
