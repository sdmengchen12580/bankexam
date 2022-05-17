package com.udit.bankexam.ui.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.easefun.polyvsdk.PolyvSDKUtil;
import com.easefun.polyvsdk.srt.PolyvSRTItemVO;
import com.easefun.polyvsdk.video.PolyvMediaInfoType;
import com.easefun.polyvsdk.video.PolyvPlayErrorReason;
import com.easefun.polyvsdk.video.PolyvVideoView;
import com.easefun.polyvsdk.video.listener.IPolyvOnAdvertisementCountDownListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnAdvertisementEventListener2;
import com.easefun.polyvsdk.video.listener.IPolyvOnAdvertisementOutListener2;
import com.easefun.polyvsdk.video.listener.IPolyvOnCompletionListener2;
import com.easefun.polyvsdk.video.listener.IPolyvOnErrorListener2;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureClickListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureLeftDownListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureLeftUpListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureRightDownListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureRightUpListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureSwipeLeftListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnGestureSwipeRightListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnInfoListener2;
import com.easefun.polyvsdk.video.listener.IPolyvOnPreloadPlayListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnPreparedListener2;
import com.easefun.polyvsdk.video.listener.IPolyvOnQuestionAnswerTipsListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnQuestionOutListener2;
import com.easefun.polyvsdk.video.listener.IPolyvOnTeaserCountDownListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnTeaserOutListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnVideoPlayErrorListener2;
import com.easefun.polyvsdk.video.listener.IPolyvOnVideoSRTListener;
import com.easefun.polyvsdk.video.listener.IPolyvOnVideoStatusListener;
import com.easefun.polyvsdk.vo.PolyvADMatterVO;
import com.easefun.polyvsdk.vo.PolyvQuestionVO;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.bean.VideoBean;
import com.udit.bankexam.bean.VideoType;
import com.udit.bankexam.presenter.video.VideoInfoPresenter;
import com.udit.bankexam.ui.video.fragment.PolyvPlayContentFragment;
import com.udit.bankexam.ui.video.fragment.PolyvPlayerTopFragment;
import com.udit.bankexam.ui.video.player.PolyvPlayerLightView;
import com.udit.bankexam.ui.video.player.PolyvPlayerMediaController;
import com.udit.bankexam.ui.video.player.PolyvPlayerPreviewView;
import com.udit.bankexam.ui.video.player.PolyvPlayerProgressView;
import com.udit.bankexam.ui.video.player.PolyvPlayerVolumeView;
import com.udit.bankexam.utils_pov.PolyvScreenUtils;
import com.udit.bankexam.view.video.VideoInfoView;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.ViewUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zb on 2017/4/26.
 */

public class VideoInfoActivity extends BaseActivity<VideoInfoPresenter> implements VideoInfoView.View, View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private PolyvPlayerTopFragment topFragment;//头部控件
    private PolyvPlayContentFragment contentFragment;//底部信息
    //播放器的parentView
    private RelativeLayout view_layout = null;
    //播放主视频播放器
    private PolyvVideoView polyv_video_view = null;
    //视频控制栏
    private PolyvPlayerMediaController polyv_player_media_controller = null;
    //视频广告，视频片头加载缓冲视图
    private ProgressBar auxiliary_loading_progress = null;
    //缩略图界面
    private PolyvPlayerPreviewView polyv_player_first_start_view = null;
    //手势出现的亮度界面
    // private PolyvPlayerLightView polyv_player_light_view = null;
    //手势出现的音量界面
    private PolyvPlayerVolumeView polyv_player_volume_view = null;
    //手势出现的进度界面
    private PolyvPlayerProgressView polyv_player_progress_view = null;
    //视频加载缓冲视图
    private ProgressBar loading_progress = null;
    private int fastForwardPos = 0;
    private boolean isPlay = false;
    private ArrayList<VideoBean> mlist_video;
    private VideoBean bean_video;
    private UserBean bean_user;
    private int curpostion;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_videoinfo);
    }

    public static void startVideoInfoActivity(BaseActivity<?> mActivity, ArrayList<VideoBean> list_bean, int curpostion) {
        Log.e("测试视频播放: ", "入口1");
        Intent intent = new Intent(mActivity, VideoInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("list_video", list_bean);
        bundle.putInt("postion", curpostion);
        intent.putExtra("videoinfo", bundle);
        //intent.put("",list_bean);
        mActivity.startActivity(intent);
    }

    public static void startVideoInfoActivityByType(BaseActivity<?> mActivity, List<VideoType> list_bean, int curpostion) {
        Log.e("测试视频播放: ", "入口2");
        Intent intent = new Intent(mActivity, VideoInfoActivity.class);
        Bundle bundle = new Bundle();
        ArrayList<VideoBean> mlist_video = new ArrayList<>();
        for (int i = 0; list_bean != null && i < list_bean.size(); i++) {
            VideoType type = list_bean.get(i);
            VideoBean bean_ = new VideoBean();
            bean_.setID(type.getId());
            bean_.setVideoIdAli(type.getVideoIdAli());
            bean_.setcID(type.getCid());
            bean_.setAFile(type.getAfile());
            bean_.setOrdID(type.getOrdid());
            bean_.setName(type.getName());
            bean_.setEID(type.getEid());
            bean_.setVTime(type.getVtime());
            bean_.setPoints(type.getPoints() == null ? "" : type.getPoints());
            mlist_video.add(bean_);
        }
        bundle.putSerializable("list_video", mlist_video);
        bundle.putInt("postion", curpostion);
        intent.putExtra("videoinfo", bundle);
        //intent.put("",list_bean);
        mActivity.startActivity(intent);
    }

    @Override
    public void initViews(Bundle bundle) {
        Log.e("测试视频播放: ", "VideoInfoActivity启动");
        ViewUtils.initView(this, R.id.class);
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void initData() {
        mPresenter = new VideoInfoPresenter(this);
        Bundle bundle = getIntent().getBundleExtra("videoinfo");
        mlist_video = (ArrayList<VideoBean>) bundle.getSerializable("list_video");
        curpostion = bundle.getInt("postion");
        bean_video = mlist_video.get(curpostion);
        addFragment(bean_video);
        initView();

        PlayMode playMode = PlayMode.portrait;
        String vid = getIntent().getStringExtra("value");
        boolean startNow = getIntent().getBooleanExtra("startNow", false);
        boolean isMustFromLocal = getIntent().getBooleanExtra("isMustFromLocal", false);
        switch (playMode) {
            case landScape:
                polyv_player_media_controller.changeToLandscape();
                break;
            case portrait:
                polyv_player_media_controller.changeToPortrait();
                break;
        }
        play(bean_video.getEID(), bean_video.getVideoIdAli(), 0, startNow, isMustFromLocal);
    }

    private void addFragment(VideoBean bean_video) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // 网校的在线视频才添加下面的控件
     /*   if (!getIntent().getBooleanExtra(PolyvMainActivity.IS_VLMS_ONLINE, false)) {
            ft.commit();
            return;
        }*/
        topFragment = new PolyvPlayerTopFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("video", bean_video);
        topFragment.setArguments(bundle);

        contentFragment = new PolyvPlayContentFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("videolist", mlist_video);
        bundle1.putInt("postion", curpostion);
        contentFragment.setArguments(bundle1);

        ft.add(R.id.fl_top, topFragment, "topFragmnet");
        ft.add(R.id.fl_countent, contentFragment, "tabFragment");
        ft.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (contentFragment != null)
            contentFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //回来后继续播放
        if (isPlay) {
            polyv_video_view.onActivityResume();

        }
        polyv_player_media_controller.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        polyv_player_media_controller.pause();
        polyv_player_progress_view.hide();
        polyv_player_volume_view.hide();
        // polyv_player_light_view.hide();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //弹出去暂停
        isPlay = polyv_video_view.onActivityStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        polyv_video_view.destroy();
        polyv_player_first_start_view.hide();
        polyv_player_media_controller.disable();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (PolyvScreenUtils.isLandscape(this) && polyv_player_media_controller != null) {
                polyv_player_media_controller.changeToPortrait();
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {

    }

    public PolyvVideoView getPolyv_video_view() {
        return polyv_video_view;
    }

    @Override
    public void getVidoeInfoNext(int postion) {

    }

    @Override
    public void playVideo(String video_url) {

    }

    @Override
    public void setExamInfo(List<ExamTitleBean> list) {

    }

    private void initView() {
        polyv_player_media_controller.initConfig(view_layout);
        polyv_video_view.setMediaController(polyv_player_media_controller);
        polyv_video_view.setPlayerBufferingIndicator(loading_progress);


        polyv_video_view.setOpenAd(true);
        polyv_video_view.setOpenTeaser(true);
        polyv_video_view.setOpenQuestion(true);
        polyv_video_view.setOpenSRT(true);
        polyv_video_view.setOpenPreload(true, 2);
        polyv_video_view.setAutoContinue(true);
        polyv_video_view.setNeedGestureDetector(true);

        polyv_video_view.setOnPreparedListener(new IPolyvOnPreparedListener2() {
            @Override
            public void onPrepared() {
                polyv_player_media_controller.preparedView();

            }
        });

        polyv_video_view.setOnPreloadPlayListener(new IPolyvOnPreloadPlayListener() {
            @Override
            public void onPlay() {
                // 开启预加载在这里开始弹幕
                // danmuFragment.start();
            }
        });


        polyv_video_view.setOnVideoStatusListener(new IPolyvOnVideoStatusListener() {
            @Override
            public void onStatus(int status) {
                if (status < 60) {
                    Toast.makeText(VideoInfoActivity.this, String.format("状态错误 %d", status), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, String.format("状态正常 %d", status));
                }
            }
        });

        polyv_video_view.setOnVideoPlayErrorListener(new IPolyvOnVideoPlayErrorListener2() {
            @Override
            public boolean onVideoPlayError(@PolyvPlayErrorReason.PlayErrorReason int playErrorReason) {
                switch (playErrorReason) {
                    case PolyvPlayErrorReason.NETWORK_DENIED:
                        showLongToast("无法连接网络，请连接网络后播放");
                        break;
                    case PolyvPlayErrorReason.OUT_FLOW:
                        showLongToast("流量超标(error code " + PolyvPlayErrorReason.OUT_FLOW + ")");
                        break;
                    case PolyvPlayErrorReason.TIMEOUT_FLOW:
                        showLongToast("账号过期(error code " + PolyvPlayErrorReason.TIMEOUT_FLOW + ")");
                        break;
                    case PolyvPlayErrorReason.LOCAL_VIEWO_ERROR:
                        showLongToast("本地视频文件损坏，请重新下载(error code " + PolyvPlayErrorReason.LOCAL_VIEWO_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.START_ERROR:
                        showLongToast("播放异常，请重新播放(error code " + PolyvPlayErrorReason.START_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.NOT_PERMISSION:
                        showLongToast("非法播放(error code " + PolyvPlayErrorReason.NOT_PERMISSION + ")");
                        break;
                    case PolyvPlayErrorReason.USER_TOKEN_ERROR:
                        showLongToast("请先设置播放凭证，再进行播放(error code " + PolyvPlayErrorReason.USER_TOKEN_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.VIDEO_STATUS_ERROR:
                        showLongToast("视频状态异常，无法播放(error code " + PolyvPlayErrorReason.VIDEO_STATUS_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.VID_ERROR:
                        showLongToast("视频id不正确，请设置正确的视频id进行播放(error code " + PolyvPlayErrorReason.VID_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.BITRATE_ERROR:
                        showLongToast("清晰度不正确，请设置正确的清晰度进行播放(error code " + PolyvPlayErrorReason.BITRATE_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.VIDEO_NULL:
                        showLongToast("视频信息加载失败，请重新播放(error code " + PolyvPlayErrorReason.VIDEO_NULL + ")");
                        break;
                    case PolyvPlayErrorReason.MP4_LINK_NUM_ERROR:
                        showLongToast("MP4 播放地址服务器数据错误(error code " + PolyvPlayErrorReason.MP4_LINK_NUM_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.M3U8_LINK_NUM_ERROR:
                        showLongToast("HLS 播放地址服务器数据错误(error code " + PolyvPlayErrorReason.M3U8_LINK_NUM_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.HLS_SPEED_TYPE_NULL:
                        showLongToast("请设置播放速度(error code " + PolyvPlayErrorReason.HLS_SPEED_TYPE_NULL + ")");
                        break;
                    case PolyvPlayErrorReason.NOT_LOCAL_VIDEO:
                        showLongToast("找不到本地下载的视频文件，请连网后重新下载(error code " + PolyvPlayErrorReason.NOT_LOCAL_VIDEO + ")");
                        break;
                    case PolyvPlayErrorReason.HLS_15X_INDEX_EMPTY:
                        showLongToast("视频不支持1.5倍自动清晰度播放(error code " + PolyvPlayErrorReason.HLS_15X_INDEX_EMPTY + ")");
                        break;
                    case PolyvPlayErrorReason.HLS_15X_ERROR:
                        showLongToast("视频不支持1.5倍当前清晰度播放(error code " + PolyvPlayErrorReason.HLS_15X_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.HLS_15X_URL_ERROR:
                        showLongToast("1.5倍当前清晰度视频正在编码中(error code " + PolyvPlayErrorReason.HLS_15X_URL_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.M3U8_15X_LINK_NUM_ERROR:
                        showLongToast("HLS 1.5倍播放地址服务器数据错误(error code " + PolyvPlayErrorReason.M3U8_15X_LINK_NUM_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.CHANGE_EQUAL_BITRATE:
                        showLongToast("切换清晰度相同，请选择其它清晰度(error code " + PolyvPlayErrorReason.CHANGE_EQUAL_BITRATE + ")");
                        break;
                    case PolyvPlayErrorReason.CHANGE_EQUAL_HLS_SPEED:
                        showLongToast("切换播放速度相同，请选择其它播放速度(error code " + PolyvPlayErrorReason.CHANGE_EQUAL_HLS_SPEED + ")");
                        break;
                    case PolyvPlayErrorReason.CAN_NOT_CHANGE_BITRATE:
                        showLongToast("未开始播放视频不能切换清晰度，请先播放视频(error code " + PolyvPlayErrorReason.CAN_NOT_CHANGE_BITRATE + ")");
                        break;
                    case PolyvPlayErrorReason.CAN_NOT_CHANGE_HLS_SPEED:
                        showLongToast("未开始播放视频不能切换播放速度，请先播放视频(error code " + PolyvPlayErrorReason.CAN_NOT_CHANGE_HLS_SPEED + ")");
                        break;
                    case PolyvPlayErrorReason.QUESTION_ERROR:
                        showLongToast("视频问答数据加载失败，请重新播放(error code " + PolyvPlayErrorReason.QUESTION_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.CHANGE_BITRATE_NOT_EXIST:
                        showLongToast("视频没有这个清晰度，请切换其它清晰度(error code " + PolyvPlayErrorReason.CHANGE_BITRATE_NOT_EXIST + ")");
                        break;
                    case PolyvPlayErrorReason.HLS_URL_ERROR:
                        showLongToast("播放地址异常，无法播放(error code " + PolyvPlayErrorReason.HLS_URL_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.LOADING_VIDEO_ERROR:
                        showLongToast("视频信息加载中出现异常，请重新播放(error code " + PolyvPlayErrorReason.LOADING_VIDEO_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.HLS2_URL_ERROR:
                        showLongToast("播放地址异常，无法播放(error code " + PolyvPlayErrorReason.HLS2_URL_ERROR + ")");
                        break;
                    case PolyvPlayErrorReason.TOKEN_NULL:
                        showLongToast("播放授权获取失败，请重新播放(error code " + PolyvPlayErrorReason.TOKEN_NULL + ")");
                        break;
                }

                return true;
            }
        });

        polyv_video_view.setOnErrorListener(new IPolyvOnErrorListener2() {
            @Override
            public boolean onError() {
                showLongToast("视频异常，请重新播放");
                return true;
            }
        });



       /* polyv_video_view.setOnGestureLeftUpListener(new IPolyvOnGestureLeftUpListener() {

            @Override
            public void callback(boolean start, boolean end) {
                Log.d(TAG, String.format("LeftUp %b %b brightness %d", start, end, polyv_video_view.getBrightness()));
                int brightness = polyv_video_view.getBrightness() + 5;
                if (brightness > 100) {
                    brightness = 100;
                }

                polyv_video_view.setBrightness(brightness);
              //  polyv_player_light_view.setViewLightValue(brightness, end);
            }
        });
*/
        /*polyv_video_view.setOnGestureLeftDownListener(new IPolyvOnGestureLeftDownListener() {

            @Override
            public void callback(boolean start, boolean end) {
                Log.d(TAG, String.format("LeftDown %b %b brightness %d", start, end, polyv_video_view.getBrightness()));
                int brightness = polyv_video_view.getBrightness() - 5;
                if (brightness < 0) {
                    brightness = 0;
                }

                polyv_video_view.setBrightness(brightness);
             //   polyv_player_light_view.setViewLightValue(brightness, end);
            }
        });*/

        polyv_video_view.setOnGestureRightUpListener(new IPolyvOnGestureRightUpListener() {

            @Override
            public void callback(boolean start, boolean end) {
                Log.d(TAG, String.format("RightUp %b %b volume %d", start, end, polyv_video_view.getVolume()));
                // 加减单位最小为10，否则无效果
                int volume = polyv_video_view.getVolume() + 10;
                if (volume > 100) {
                    volume = 100;
                }

                polyv_video_view.setVolume(volume);
                polyv_player_volume_view.setViewVolumeValue(volume, end);
            }
        });

        polyv_video_view.setOnGestureRightDownListener(new IPolyvOnGestureRightDownListener() {

            @Override
            public void callback(boolean start, boolean end) {
                Log.d(TAG, String.format("RightDown %b %b volume %d", start, end, polyv_video_view.getVolume()));
                // 加减单位最小为10，否则无效果
                int volume = polyv_video_view.getVolume() - 10;
                if (volume < 0) {
                    volume = 0;
                }

                polyv_video_view.setVolume(volume);
                polyv_player_volume_view.setViewVolumeValue(volume, end);
            }
        });

        polyv_video_view.setOnGestureSwipeLeftListener(new IPolyvOnGestureSwipeLeftListener() {

            @Override
            public void callback(boolean start, boolean end) {
                // 左滑事件
                Log.d(TAG, String.format("SwipeLeft %b %b", start, end));
                if (fastForwardPos == 0) {
                    fastForwardPos = polyv_video_view.getCurrentPosition();
                }

                if (end) {
                    if (fastForwardPos < 0)
                        fastForwardPos = 0;
                    polyv_video_view.seekTo(fastForwardPos);
                    if (polyv_video_view.isCompletedState()) {
                        polyv_video_view.start();
                    }
                    fastForwardPos = 0;
                } else {
                    fastForwardPos -= 10000;
                    if (fastForwardPos <= 0)
                        fastForwardPos = -1;
                }
                polyv_player_progress_view.setViewProgressValue(fastForwardPos, polyv_video_view.getDuration(), end, false);
            }
        });

        polyv_video_view.setOnGestureSwipeRightListener(new IPolyvOnGestureSwipeRightListener() {

            @Override
            public void callback(boolean start, boolean end) {
                // 右滑事件
                Log.d(TAG, String.format("SwipeRight %b %b", start, end));
                if (fastForwardPos == 0) {
                    fastForwardPos = polyv_video_view.getCurrentPosition();
                }

                if (end) {
                    if (fastForwardPos > polyv_video_view.getDuration())
                        fastForwardPos = polyv_video_view.getDuration();
                    polyv_video_view.seekTo(fastForwardPos);
                    if (polyv_video_view.isCompletedState()) {
                        polyv_video_view.start();
                    }
                    fastForwardPos = 0;
                } else {
                    fastForwardPos += 10000;
                    if (fastForwardPos > polyv_video_view.getDuration())
                        fastForwardPos = polyv_video_view.getDuration();
                }
                polyv_player_progress_view.setViewProgressValue(fastForwardPos, polyv_video_view.getDuration(), end, true);
            }
        });

        polyv_video_view.setOnGestureClickListener(new IPolyvOnGestureClickListener() {
            @Override
            public void callback(boolean start, boolean end) {
                if (polyv_video_view.isInPlaybackState() && polyv_player_media_controller != null)
                    if (polyv_player_media_controller.isShowing())
                        polyv_player_media_controller.hide();
                    else
                        polyv_player_media_controller.show();
            }
        });
    }

    public void play(final String vid, String videoIdAli, final int bitrate, boolean startNow, final boolean isMustFromLocal) {
        if (TextUtils.isEmpty(vid)) {
            return;
        }
        polyv_video_view.release();
        polyv_player_media_controller.hide();
        loading_progress.setVisibility(View.GONE);
        //  auxiliaryVideoView.hide();
        auxiliary_loading_progress.setVisibility(View.GONE);

        if (startNow) {
            polyv_video_view.setVid(vid, bitrate, isMustFromLocal);
        } else {
            polyv_player_first_start_view.setCallback(new PolyvPlayerPreviewView.Callback() {
                @Override
                public void onClickStart() {
                    polyv_video_view.setVid(vid, bitrate, isMustFromLocal);
                }
            });
            polyv_player_first_start_view.show(vid);
        }
    }

    @Override
    public void playVideo(boolean isSuccess, String playUrl0rErrorMsg) {
    }

    //播放模式
    public enum PlayMode {
        /**
         * 横屏
         */
        landScape(3),
        /**
         * 竖屏
         */
        portrait(4);

        private final int code;

        private PlayMode(int code) {
            this.code = code;
        }

        /**
         * 取得类型对应的code
         *
         * @return
         */
        public int getCode() {
            return code;
        }

        public static PlayMode getPlayMode(int code) {
            switch (code) {
                case 3:
                    return landScape;
                case 4:
                    return portrait;
            }

            return null;
        }
    }
}
