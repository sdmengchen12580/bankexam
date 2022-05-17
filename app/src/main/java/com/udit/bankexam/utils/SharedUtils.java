package com.udit.bankexam.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.udit.bankexam.R;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.ui.video.view.MediaController;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.UMWXHandler;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by zb on 2017/5/18.
 */

public class SharedUtils {

    public static ArrayList<SnsPlatform> platforms = new ArrayList<SnsPlatform>();

    private static ShareAction mShareAction;
    static
    {
        platforms.add(SHARE_MEDIA.QQ.toSnsPlatform());
        platforms.add(SHARE_MEDIA.QZONE.toSnsPlatform());
        platforms.add(SHARE_MEDIA.WEIXIN.toSnsPlatform());
        platforms.add(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform());
    }

    public static ShareAction getShareAction(BaseActivity<?> mActivity,ShareBoardlistener listener_board, UMShareListener listener_share)
    {

        ShareAction action =  new ShareAction(mActivity)
                 .setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                .setShareboardclickCallback(listener_board)
                .setCallback(listener_share);

        return action;
    }



}
