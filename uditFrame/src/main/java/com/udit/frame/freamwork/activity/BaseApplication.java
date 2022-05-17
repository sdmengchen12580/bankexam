package com.udit.frame.freamwork.activity;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.udit.frame.R;
import com.udit.frame.bugUtils.CrashHandler;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;


import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.Toast;

import de.mindpipe.android.logging.log4j.LogConfigurator;

public class BaseApplication extends Application
{
    private final static String TAG = "BaseApplication";
    
    public static DisplayImageOptions list_options;
    
    public static DisplayImageOptions banner_options;
    
    public static DisplayImageOptions user_options;
    
    public static BaseApplication instance;

    public static final String UPDATE_STATUS_ACTION = "com.umeng.message.example.action.UPDATE_STATUS";
    
    private List<Activity> mList = new LinkedList<Activity>();

    public LogConfigurator logConfigurator;

    public Logger logger;

    /**
     * 存储卡根目录
     */
    public static String EXTERNALSTORAGEDIR = Environment.getExternalStorageDirectory().getPath();

    /**
     * 程序使用的目录
     */
    public final static String DIR_APP = "udit";
    /**
     * 日志保存目录
     */
    private final static String DIR_LOG = "log";

    @Override
    public void onCreate()
    {
        super.onCreate();
        initImage();
       // initVLC();
        initLog();
        initLogConfig();
        instance = this;
        
    }
    public void initLogConfig() {
        //初始化日志信息
    //    initLoginConfig();
        //初始化系统配置
     //   createAppDir();
        //logger.info("程序启动");
//        AndroidCrash.getInstance().setLogFileDir(getLogDir()).setCrashReporter
//                (myCrashListener);
//        AndroidCrash.getInstance().init(getApplicationContext());

        // 新加方法
        CrashHandler catchHandler = CrashHandler.getInstance();
        catchHandler.init(getApplicationContext());
    }



    /**
     * 创建程序目录文件夹
     */
    private  void createAppDir() {
        //检查日志文件目录是否存在
        File logDir = new File(getLogDir());
        if (!logDir.exists())
            logDir.mkdirs();
        File nomedia = new File(EXTERNALSTORAGEDIR + File.separator + DIR_APP, ".nomedia");
        try {
            nomedia.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取日志文件存取目录
     *
     * @return
     */
    public static String getLogDir() {
        return EXTERNALSTORAGEDIR + File.separator + DIR_APP + File.separator + DIR_LOG;
    }




    /**
     * 初始化日志信息配置
     */
    private void initLoginConfig() {
        //初始化日志设置
        this.logConfigurator = new LogConfigurator();
        //设置日志文件名称
        this.logConfigurator.setFileName(getLogDir() + File.separator + MyDateUtil
                .date2String(new Date(), "yyyyMMdd") + ".log");
        this.logConfigurator.setFilePattern("%d [%p]-[%c.%M(%L)] %m %n");
        //设置日志级别
        this.logConfigurator.setRootLevel(Level.DEBUG);
        this.logConfigurator.setLevel("org.apache", Level.ERROR);
        //设置最大文件大小
        this.logConfigurator.setMaxFileSize(1024 * 1024 * 5);
        this.logConfigurator.setImmediateFlush(true);
        //应用日志设置
        this.logConfigurator.configure();
        //初始化日志
        this.logger = Logger.getLogger(BaseApplication.class);
    }

    
    public static void setTag(List<String> mlist)
    {
      
    }
    
    private void initLog()
    {
        MyLogUtils.ISDEBUG = true;
        MyLogUtils.ISERR = true;
        MyLogUtils.ISINFO = true;
    }
    
    // add Activity
    public void addActivity(BaseActivity activity)
    {
        mList.add(activity);
    }

    public void removeActivity(BaseActivity activity )
    {
        mList.remove(activity);
    }

    public void exit()
    {
        try
        {
            for (Activity activity : mList)
            {
                if (activity != null)
                    activity.finish();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.exit(0);
        }
    }
    
    public synchronized static BaseApplication getInstance()
    {
        if (null == instance)
        {
            instance = new BaseApplication();
        }
        return instance;
    }
    
    /**
     * @return the main context of the Application
     */
    public static Context getAppContext()
    {
        return instance;
    }
    
    /**
     * @return the main resources from the Application
     */
    public static Resources getAppResources()
    {
        return instance.getResources();
    }
    
    private void initVLC()
    {
        // Are we using advanced debugging - locale?
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String p = pref.getString("set_locale", "");
        if (p != null && !p.equals(""))
        {
            Locale locale;
            // workaround due to region code
            if (p.startsWith("zh"))
            {
                locale = Locale.CHINA;
            }
            else
            {
                locale = new Locale(p);
            }
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
        
    }
    
    private void initImage()
    {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext()).threadPriority(Thread.NORM_PRIORITY - 2)
            .threadPoolSize(5)// 线程池内加载的数量
            .memoryCacheExtraOptions(480, 800) // max width, max height
            .denyCacheImageMultipleSizesInMemory()
            .tasksProcessingOrder(QueueProcessingType.LIFO)
            .discCacheSize(50 * 1024 * 1024)
            .memoryCache(new LruMemoryCache(5 * 1024 * 1024)) // 建议内存设在5-10M,可以有比较好的表现
            .memoryCacheSize(5 * 1024 * 1024)
            .discCacheFileCount(1000) // 缓存的文件数量
           // .diskCache(new UnlimitedDiscCache(new File(getApplicationContext().getFilesDir()+ "/myApp/imgCache")))
                        // (30 s)
            .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) // connectTimeout (5
                                                                                                  // s), readTimeout
            .writeDebugLogs() // Remove for release app
            .build();
        ImageLoader.getInstance().init(configuration);
        
        /*
         * list_options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.pic_default)
         * .showImageOnFail(R.drawable.pic_default)
         * .showImageForEmptyUri(R.drawable.pic_default)
         * .resetViewBeforeLoading(false)
         * .delayBeforeLoading(100)
         * .cacheInMemory(true)
         * .cacheOnDisc(true)
         * .imageScaleType(ImageScaleType.NONE)
         * .bitmapConfig(Config.RGB_565)
         * .build();
         */
        
        list_options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.pic_default)
            .showImageForEmptyUri(R.drawable.pic_default)
            .showImageOnFail(R.drawable.pic_default)
            .resetViewBeforeLoading(true)
            .cacheInMemory(true)
            .cacheOnDisc(true)
            .imageScaleType(ImageScaleType.EXACTLY)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .considerExifParams(true)
            .displayer(new SimpleBitmapDisplayer())
            .build();
        
        banner_options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.banner_default)
            .showImageForEmptyUri(R.drawable.banner_default)
            .showImageOnFail(R.drawable.banner_default)
            .resetViewBeforeLoading(true)
            .cacheInMemory(true)
            .cacheOnDisc(true)
            .imageScaleType(ImageScaleType.EXACTLY)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .considerExifParams(true)
            .displayer(new SimpleBitmapDisplayer())
            .build();
        
        user_options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.p_pic_user)
            .showImageForEmptyUri(R.drawable.p_pic_user)
            .showImageOnFail(R.drawable.p_pic_user)
            .resetViewBeforeLoading(true)
            .cacheInMemory(true)
            .cacheOnDisc(true)
            .imageScaleType(ImageScaleType.EXACTLY)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .considerExifParams(true)
            .displayer(new SimpleBitmapDisplayer())
            .build();
        
    }
    
    public static void deleteTag()
    {
       
    }
    
}
