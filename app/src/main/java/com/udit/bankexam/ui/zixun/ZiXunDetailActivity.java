package com.udit.bankexam.ui.zixun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.NewBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.presenter.zixun.ZiXunDetailPresenter;
import com.udit.bankexam.ui.video.VideoInfoActivity;
import com.udit.bankexam.utils.SharedUtils;
import com.udit.bankexam.view.zixun.ZiXunDetailView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ViewUtils;
import com.udit.frame.utils.WebUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.UMWXHandler;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

/**
 * Created by zb on 2018-06-08.
 */

public class ZiXunDetailActivity extends BaseActivity<ZiXunDetailPresenter> implements ZiXunDetailView.View, View.OnClickListener, UMShareListener, ShareBoardlistener {

    private final String TAG = this.getClass().getSimpleName();

    public static void startZiXunDetailActivity(BaseActivity<?> mActivity, NewBean bean) {
        Intent intent = new Intent(mActivity, ZiXunDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean", bean);
        intent.putExtra("bean", bundle);
        mActivity.startActivity(intent);
    }

    private ImageView img_top_return, share_video;

    private TextView text_top_centent, text_zixun, text_time;

    private WebView webview_info;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_new_detail);
    }

    @Override
    public void initViews(Bundle bundle) {
        ViewUtils.initView(this, R.id.class);
        share_video.setVisibility(View.VISIBLE);
    }

    @Override
    public void initListeners() {
        img_top_return.setOnClickListener(this);
        share_video.setOnClickListener(this);

    }

    private ShareAction shareAction;


    private NewBean bean;

    @Override
    public void initData() {
        mPresenter = new ZiXunDetailPresenter(this);
        shareAction = SharedUtils.getShareAction((ZiXunDetailActivity) getActivity(), this, this);
        Bundle bundle = getIntent().getBundleExtra("bean");
        bean = (NewBean) bundle.getSerializable("bean");
        setNewDetail(bean);
    }

    @Override
    public void setNewDetail(NewBean bean) {
        if (!MyCheckUtils.isEmpty(bean.getName())) {
            text_zixun.setText(bean.getName());
            text_top_centent.setText("详情");
        }
        text_time.setVisibility(View.GONE);
        if (!MyCheckUtils.isEmpty(bean.getCreateDate()))
            text_time.setText(bean.getCreateDate());

        if (!MyCheckUtils.isEmpty(bean.getInfo())) {
            WebUtil.WebInit(webview_info);
            String content = bean.getInfo();
            content = content.replaceAll(Constant.IMAGE.IMG_OLD_BEGIN, Constant.IMAGE.IMG_NEW_BEGIN);

            WebUtil.WebInit(webview_info);
            webview_info.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);


        }

    }

    private String share_ip;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_top_return:
                finish();
                break;
            case R.id.share_video:
                share_ip = IHTTP.ZIXUN_SHARE_IP + bean.getID();
                // ToastUtil.showMessage((VideoInfoActivity)getActivity(),file);

                ShareBoardConfig config = new ShareBoardConfig();

                config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER);
                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
                shareAction.open(config);

                break;
        }
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {

    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {

    }

    @Override
    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
        MyLogUtils.e(TAG, "onClick:" + share_media.name());
        if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE || share_media == SHARE_MEDIA.WEIXIN) {//微信朋友圈
        }
        UMWXHandler handler = new UMWXHandler();
        String s = handler.getSDKVersion();
        MyLogUtils.e(TAG, "handle:" + s);
        UMWeb web = new UMWeb(share_ip);
        web.setThumb(new UMImage(getActivity(), R.drawable.shared));
        web.setDescription(bean.getName());
        web.setTitle(Constant.Shared.title);
        shareAction.withMedia(web);
        shareAction.setPlatform(share_media).share();
    }
}
