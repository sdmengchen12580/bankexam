package com.udit.bankexam;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.easefun.polyvsdk.PolyvDevMountInfo;
import com.easefun.polyvsdk.PolyvSDKClient;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.udit.bankexam.constant.Constant;

import com.udit.bankexam.notify.MyPushIntentService;
import com.udit.bankexam.ui.video.view.PolyvDemoService;
import com.udit.bankexam.utils.DBUtils;
import com.udit.bankexam.utils.FontUtils;
import com.udit.bankexam.utils.PushHelper;
import com.udit.bankexam.utils.SaveUtils;
import com.udit.bankexam.utils.SpUtil;
import com.udit.frame.freamwork.activity.BaseApplication;
import com.udit.frame.utils.MyLogUtils;
import com.umeng.commonsdk.UMConfigure;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;

/**
 * Created by zb on 2017/4/27.
 */

public class MyApplication extends BaseApplication {

    private final String TAG = this.getClass().getSimpleName();

    private static Context mContext;

    private Handler handler;

    public static IWXAPI api;

    public static String weixinLink;

    public static String PlayId;

    public static boolean flag_notiy = false;

    public MyApplication() {

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        DBUtils.getInstance();
        FontUtils.init(getAppContext());
        initVideo();
        preInitUmeng();
        Integer integer_first = SaveUtils.getFirst(getAppContext());
        if (integer_first == null || integer_first <= 0) {
            SaveUtils.saveFirst(getAppContext(), 1);
            SaveUtils.saveNotify(getAppContext(), true);
            flag_notiy = true;
        } else {
            flag_notiy = SaveUtils.getNotify(this);
        }
        if (flag_notiy)
            QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    //友盟
    public static void preInitUmeng() {
//        UMConfigure.setLogEnabled(true);
        //预初始化
        PushHelper.preInit(mContext);
        //同意协议后，初始化
        String hasAgree = (String) SpUtil.get(mContext, "hasaggreeurl", "0");
        if (hasAgree.equals("1")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PushHelper.init(mContext);
                }
            }).start();
        }
    }

    public static IWXAPI getWxApi() {
        if (MyApplication.api == null) {
            MyApplication.api = WXAPIFactory.createWXAPI(getContext(), Constant.PAY.PAY_WEIXIN_APPID);
            MyApplication.api.registerApp(Constant.PAY.PAY_WEIXIN_APPID);
        }
        return MyApplication.api;
    }

    public static Context getContext() {
        return mContext;
    }

    private void initVideo() {
        PolyvSDKClient client = PolyvSDKClient.getInstance();
        client.setConfig(Constant.VIDEO.SDK_PASS, Constant.VIDEO.AESKEY, Constant.VIDEO.IV, getApplicationContext());
        client.initDatabaseService(this);
        client.startService(getApplicationContext(), PolyvDemoService.class);
        PolyvDevMountInfo.getInstance().init(this, new PolyvDevMountInfo.OnLoadCallback() {

            @Override
            public void callback() {
                if (PolyvDevMountInfo.getInstance().isSDCardAvaiable() == false) {
                    // TODO 没有可用的存储设备
                    MyLogUtils.e(TAG, "没有可用的存储设备");
                    return;
                }

                StringBuilder dirPath = new StringBuilder();
                dirPath.append(PolyvDevMountInfo.getInstance().getSDCardPath()).
                        append(File.separator).append("polyvdownload");
                File saveDir = new File(dirPath.toString());
                if (saveDir.exists() == false) {
                    saveDir.mkdir();
                }

                //如果生成不了文件夹，可能是外部SD卡需要写入特定目录/storage/sdcard1/Android/data/包名/
                if (saveDir.exists() == false) {
                    dirPath.delete(0, dirPath.length());
                    dirPath.append(PolyvDevMountInfo.getInstance().getSDCardPath())
                            .append(File.separator).append("Android").append(File.separator).append("data")
                            .append(File.separator).append(getPackageName()).append(File.separator)
                            .append("polyvdownload");
                    saveDir = new File(dirPath.toString());
                    getExternalFilesDir(null); // 生成包名目录
                    saveDir.mkdirs();
                }

                PolyvSDKClient.getInstance().setDownloadDir(saveDir);
            }
        });

    }
}
