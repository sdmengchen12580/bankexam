package com.udit.frame.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
/**
 * 網絡檢測工具
 *  
 * @author 曾宝
 * @version  [V1.00, 2017年4月5日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class IPUtils
{
    /**
     * 检测网络是否可用
     * 
     * @return
     */
    public static boolean isNetworkConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }
    
    /**
     * 获取当前网络类型
     * 
     * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
     */
    
    public static final int NETTYPE_WIFI = 0x01;
    
    public static final int NETTYPE_CMWAP = 0x02;
    
    public static final int NETTYPE_CMNET = 0x03;
    
    @SuppressLint("DefaultLocale")
    public static int getNetworkType(Context context)
    {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null)
        {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE)
        {
            String extraInfo = networkInfo.getExtraInfo();
            if (!MyCheckUtils.isEmpty(extraInfo))
            {
                if (extraInfo.toLowerCase().equals("cmnet"))
                {
                    netType = NETTYPE_CMNET;
                }
                else
                {
                    netType = NETTYPE_CMWAP;
                }
            }
        }
        else if (nType == ConnectivityManager.TYPE_WIFI)
        {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }
    
    public static String getLocalIpAddress()
    {
        try
        {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
            {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress())
                    {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (SocketException ex)
        {
            Log.e("WifiPreference IpAddress", ex.toString());
        }
        return null;
    }
    
    public static String getWifiAddress(Context context)
    {
        // 获取wifi服务
        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        // 判断wifi是否开启
        if (!wifiManager.isWifiEnabled())
        {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }
    
    private static String intToIp(int i)
    {
        
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    }
    
    public static String getAddress(Context context)
    {
        if(isNetworkConnected(context))
        {
           int type = getNetworkType(context);
           if(type==NETTYPE_CMWAP || type==NETTYPE_CMNET)
               return getLocalIpAddress();
           else
               return getWifiAddress(context);
        }
        else
            return null;
    }
}
