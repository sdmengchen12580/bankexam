package com.udit.bankexam.utils;

import android.app.Notification;
import android.content.Context;
import android.os.Handler;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.taobao.accs.ACCSClient;
import com.taobao.accs.AccsClientConfig;
import com.taobao.agoo.TaobaoRegister;
import com.udit.bankexam.MyApplication;
import com.udit.bankexam.R;
import com.udit.bankexam.notify.MyPushIntentService;
import com.udit.frame.utils.MyLogUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

public class PushHelper {

    public static String HAS_AGREE_XY = "has_agree_xy";//是否同意了协议

    public static final String APP_KEY = "591d4ab107fe653759001585";//应用申请的Appkey

    public static final String MESSAGE_SECRET = "8ae88014621df7ad5366a8abb24af50e";//应用申请的UmengMessageSecret

    public static final String CHANNEL = "Umeng";//渠道名称，修改为您App的发布渠道名称

    //预初始化，已添加子进程中初始化sdk。 使用场景：用户未同意隐私政策协议授权时，延迟初始化
    public static void preInit(Context context) {
        try {
            //解决推送消息显示乱码的问题
            AccsClientConfig.Builder builder = new AccsClientConfig.Builder();
            builder.setAppKey("umeng:" + APP_KEY);
            builder.setAppSecret(MESSAGE_SECRET);
            builder.setTag(AccsClientConfig.DEFAULT_CONFIGTAG);
            ACCSClient.init(context, builder.build());
            TaobaoRegister.setAccsConfigTag(context, AccsClientConfig.DEFAULT_CONFIGTAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UMConfigure.preInit(context, APP_KEY, CHANNEL);
        if (!UMUtils.isMainProgress(context)) {
            init(context);
        }
    }

    // 初始化。 场景：用户已同意隐私政策协议授权时
    public static void init(final Context context) {
        // 在此处调用基础组件包提供的初始化函数 相应信息可在应用管理 -> 应用信息 中找到 http://message.umeng.com/list/apps
        // 参数一：当前上下文context；
        // 参数二：应用申请的Appkey；
        // 参数三：渠道名称；
        // 参数四：设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机；
        // 参数五：Push推送业务的secret 填充Umeng Message Secret对应信息
        UMConfigure.init(context, APP_KEY, CHANNEL, UMConfigure.DEVICE_TYPE_PHONE, MESSAGE_SECRET);
        //分享
        PlatformConfig.setWeixin("wxf41ad321ed049ffa", "503e667c6581dd7008846d56ce97d12a");
        PlatformConfig.setQQZone("1106123153", "KEYjPrfUT98UehGKhyO");
        UMShareAPI.get(context);
        if (!shouldInitPush(context)) {
            return;
        }
        PushAgent mPushAgent = PushAgent.getInstance(context);
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                SpUtil.put(context, "umeng_token", deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
        //sdk开启通知声音
        //  mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);

        //  mPushAgent.setNotificationPlaySound(R.raw.notfity);
        // sdk关闭通知声音
//		mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE);
        // 通知声音由服务端控制
        //mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER);
        //呼吸灯
        // mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        //振动
        //mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            /**
             * 自定义消息的回调方法
             * */
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                // 对自定义消息的处理方式，点击或者忽略
                boolean isClickOrDismissed = true;
                if (isClickOrDismissed) {
                    //自定义消息的点击统计
                    UTrack.getInstance(MyApplication.getContext()).trackMsgClick(msg);
                } else {
                    //自定义消息的忽略统计
                    UTrack.getInstance(MyApplication.getContext()).trackMsgDismissed(msg);
                }
                //    Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }

            /**
             * 自定义通知栏样式的回调方法
             * */
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                switch (msg.builder_id) {
                    case 1:
                        Notification.Builder builder = new Notification.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(), R.layout.notification_view);
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
                        myNotificationView.setImageViewResource(R.id.notification_small_icon, getSmallIconId(context, msg));
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, msg))
                                .setTicker(msg.ticker)
                                .setAutoCancel(true);

                        return builder.getNotification();
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

        /**
         * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
         * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }
        };
        //使用自定义的NotificationHandler，来结合友盟统计处理消息通知，参考http://bbs.umeng.com/thread-11112-1-1.html
        //CustomNotificationHandler notificationClickHandler = new CustomNotificationHandler();
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
        //此处是完全自定义处理设置，两个例子，任选一种即可
        mPushAgent.setPushIntentServiceClass(MyPushIntentService.class);
//        mPushAgent.setPushIntentServiceClass(UmengNotificationService.class);
    }

    //是否运行在主进程
    public static boolean isMainProcess(Context context) {
        return UMUtils.isMainProgress(context);
    }

    //只需在主进程和channel进程中注册推送SDK
    private static boolean shouldInitPush(Context context) {
        if (isMainProcess(context)) {
            return true;
        }
        String processName = UMFrUtils.getCurrentProcessName(context);
        return processName != null && processName.endsWith(":channel");
    }
}
