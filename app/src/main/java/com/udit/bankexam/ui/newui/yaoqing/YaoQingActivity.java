package com.udit.bankexam.ui.newui.yaoqing;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.utils.FastClickUtils;
import com.udit.bankexam.utils.SharedUtils;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyLogUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.UMWXHandler;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

public class YaoQingActivity extends BaseActivity implements ShareBoardlistener, UMShareListener {

    private ImageView img_top_return;
    private ImageView img_yaoqing;
    private TextView tv_yq_detail;
    private ShareAction shareAction;
    private TextView text_top_centent;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_yao_qing);
    }

    @Override
    public void initViews(Bundle bundle) {
        text_top_centent = (TextView) findViewById(R.id.text_top_centent);
        text_top_centent.setText("邀请好友");
        img_yaoqing = (ImageView) findViewById(R.id.img_yaoqing);
        img_top_return = (ImageView) findViewById(R.id.img_top_return);
        tv_yq_detail = (TextView) findViewById(R.id.tv_yq_detail);
        //下划线
        tv_yq_detail.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_yq_detail.getPaint().setAntiAlias(true);
    }

    @Override
    public void initListeners() {
        //返回
        img_top_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                finish();
            }
        });

        //邀请
        img_yaoqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                ShareBoardConfig config = new ShareBoardConfig();
                config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER);
                config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
                shareAction.open(config);
            }
        });

        //邀请记录
        tv_yq_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FastClickUtils.isFastClick()) {
                    return;
                }
                changeAct(null, YaoqingDetailActivity.class);
            }
        });
    }

    @Override
    public void initData() {
        shareAction = SharedUtils.getShareAction((BaseActivity<?>) getActivity(), this, this);
    }


    //--------------------------分享功能-----------------------------
    @Override
    public void onStart(SHARE_MEDIA share_media) {
        MyLogUtils.e("测试分享", "onStart:" + share_media.name());

    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        MyLogUtils.e("测试分享", "SHARE_MEDIA");
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
        MyLogUtils.e("测试分享", "onError：" + throwable.getMessage() + ":" + share_media.name());
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {
        MyLogUtils.e("测试分享", "onCancel");
    }

    @Override
    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
        if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE
                || share_media == SHARE_MEDIA.WEIXIN) {//微信朋友圈
        }
        UMWXHandler handler = new UMWXHandler();
        String s = handler.getSDKVersion();
        UMWeb web = new UMWeb(Constant.Shared.URL);
        web.setThumb(new UMImage(getActivity(), R.drawable.shared));
        web.setDescription(Constant.Shared.content);
        web.setTitle(Constant.Shared.title);
        shareAction.withMedia(web);
        shareAction.setPlatform(share_media).share();
    }
}
