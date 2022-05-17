package com.udit.bankexam.notify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telecom.Connection;

import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ToastUtil;

/**
 * Created by zb on 2017/6/17.
 */

public class MyNetBroadCast extends BroadcastReceiver {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkInfo.State wifiState = null;

        NetworkInfo.State mobileState = null;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        if(wifiState!=null && mobileState!=null
                && wifiState != NetworkInfo.State.CONNECTED && mobileState == NetworkInfo.State.CONNECTED)
        {   //手机网络
            MyLogUtils.e(TAG,"您正在使用手机流量");
            ToastUtil.showMessage(context,"您正在使用手机流量");
        }
        else if(wifiState!=null && mobileState!=null
                && wifiState==NetworkInfo.State.CONNECTED && mobileState!=NetworkInfo.State.CONNECTED)
        {// wifi 网络
            //ToastUtil.showMessage(context,"您正在使用手机网络");
        }
        else
        {//无网络
            MyLogUtils.e(TAG,"您的网络已经断开");
            ToastUtil.showMessage(context,"您的网络已经断开");
        }

    }
}
