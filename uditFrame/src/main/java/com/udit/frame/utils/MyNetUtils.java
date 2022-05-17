package com.udit.frame.utils;

import java.util.List;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * 
 * 网络工具类
 * @author 曾宝
 * @version  [V1.00, 2016年2月14日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class MyNetUtils
{
    /**
     * 检查网络
     * <功能详细描述>
     * 
     * @param context
     * @return true 有网络，false 无网络
     * @see [类、类#方法、类#成员]
     */
    public static boolean checkNetwork(Context context)
    {
        boolean flag = false;
        ConnectivityManager cwjManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cwjManager != null)
        {
            NetworkInfo info = cwjManager.getActiveNetworkInfo();
            if (info != null)
            {
                flag = info.isAvailable();
            }
        }
        return flag;
    }
    
    /**
     * 判断GPS是否打开
     * <功能详细描述>
     * 
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isGpsEnabled(Context context)
    {
        LocationManager lm = ((LocationManager)context.getSystemService(Context.LOCATION_SERVICE));
        List<String> accessibleProviders = lm.getProviders(true);
        return accessibleProviders != null && accessibleProviders.size() > 0;
    }
    
    /**
     * 判断wifi
     * <功能详细描述>
     * 
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isWifiEnabled(Context context)
    {
        ConnectivityManager mgrConn = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }
    
    /**
     * 判断3G网络
     * <功能详细描述>
     * 
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean is3rd(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE)
        {
            return true;
        }
        return false;
    }
    
    /**
     * 判断wifi 还是3G
     * <功能详细描述>
     * 
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isWifi(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_WIFI)
        {
            return true;
        }
        return false;
    }
}
