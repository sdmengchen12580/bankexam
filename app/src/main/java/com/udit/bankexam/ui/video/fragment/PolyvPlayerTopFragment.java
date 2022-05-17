package com.udit.bankexam.ui.video.fragment;

import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.VideoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.ui.video.VideoInfoActivity;
import com.udit.bankexam.utils.SharedUtils;
import com.udit.bankexam.utils_pov.PolyvScreenUtils;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ToastUtil;
import com.udit.frame.utils.Utils;
import com.udit.frame.utils.ViewUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.UMWXHandler;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;


public class PolyvPlayerTopFragment extends Fragment implements View.OnClickListener, ShareBoardlistener, UMShareListener {

    private final String TAG = this.getClass().getSimpleName();
    //fragmentView
    private View view;
    // 返回按钮,分享按钮
    private ImageView img_top_return, share_video;

    public static TextView text_top_centent;
    // 顶部布局
    private RelativeLayout rl_item_top_exam;

    private VideoBean bean_video;


    private ShareAction shareAction;


    private SharedUtils shareUtils;
    private String share_ip;
    private LinearLayout ll_kefu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.item_top_return_, container, false);
        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (PolyvScreenUtils.isLandscape(getActivity())) {
            rl_item_top_exam.setVisibility(View.GONE);
        } else {
            rl_item_top_exam.setVisibility(View.VISIBLE);
        }
    }

    private void findIdAndNew() {
        try {
            ViewUtils.initView(this, view, R.id.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        text_top_centent.setText("");

        shareAction = SharedUtils.getShareAction((VideoInfoActivity) getActivity(), this, this);
        bean_video = (VideoBean) getArguments().getSerializable("video");

        text_top_centent.setText(bean_video.getName());
        text_top_centent.requestFocus();
        share_video.setVisibility(View.VISIBLE);
        share_video.setOnClickListener(this);

        img_top_return.setOnClickListener(this);
        ll_kefu.setVisibility(View.GONE);
        ll_kefu.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findIdAndNew();
        initView();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_top_return:
                ((VideoInfoActivity) getActivity()).finish();
                break;
            case R.id.ll_kefu:
                if (Utils.isQQAvailable(getActivity())) {
                    Utils.startQQ(getActivity(), Constant.QQ_ZIXUN);
                } else {
                    ToastUtil.showMessage(getContext(), "请先安装QQ");
                }
                break;
            case R.id.share_video:
                String file = bean_video.getAFile();
                if (!MyCheckUtils.isEmpty(file)) {
                    share_ip = IHTTP.VIDEO_SHARE_IP + file;
                    // ToastUtil.showMessage((VideoInfoActivity)getActivity(),file);
                    ShareBoardConfig config = new ShareBoardConfig();

                    config.setShareboardPostion(ShareBoardConfig.SHAREBOARD_POSITION_CENTER);
                    config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
                    shareAction.open(config);

                } else {
                    ToastUtil.showMessage((VideoInfoActivity) getActivity(), "暂无视频，无法分享");
                }
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
        if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE
                || share_media == SHARE_MEDIA.WEIXIN) {//微信朋友圈
        }
        UMWXHandler handler = new UMWXHandler();
        String s = handler.getSDKVersion();
        MyLogUtils.e(TAG, "handle:" + s);
        UMWeb web = new UMWeb(share_ip);
        web.setThumb(new UMImage(getActivity(), R.drawable.shared));
        web.setDescription(bean_video.getName());
        web.setTitle(Constant.Shared.title);
        shareAction.withMedia(web);
        shareAction.setPlatform(share_media).share();
    }
}
