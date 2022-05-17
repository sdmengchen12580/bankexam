package com.udit.bankexam.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.udit.bankexam.MyApplication;
import com.udit.bankexam.R;
import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.bean.notiftyBean;
import com.udit.bankexam.ui.user.MessageActivity;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyLogUtils;
import com.umeng.message.UTrack;
import com.umeng.message.entity.UMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * Created by mitic_xue on 16/10/28.
 */
public class MyNotificationService extends Service {
    private static final String TAG = UmengNotificationService.class.getName();
    public static UMessage oldMessage = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try
        {
            Object message = intent.getSerializableExtra("message");
            MyLogUtils.e(TAG,"onStartCommand");
            if(message!=null && message instanceof notiftyBean ) {


                notiftyBean bean = (notiftyBean) message;
                if (bean.getmType().equals("关联课程")) {
                    if (MyApplication.flag_notiy) {
                        showNotification(bean);
                    }
                } else
                    showNotification(bean);
            }
            return super.onStartCommand(intent, flags, startId);
        }
        catch(Exception e)
        {
            return super.onStartCommand(intent, flags, startId);
        }

       // showNotification();
     /*   try {
            UMessage msg = new UMessage(new JSONObject(message));
            if (oldMessage != null) {
                UTrack.getInstance(getApplicationContext()).setClearPrevMessage(true);
                UTrack.getInstance(getApplicationContext()).trackMsgDismissed(oldMessage);
            }
            showNotification(msg);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

    private void showNotification(notiftyBean bean) {
        int id = new Random(System.nanoTime()).nextInt();
        //  oldMessage = msg;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
        Notification.Builder mBuilder = new Notification.Builder(this);


        mBuilder.setContentTitle("银行易考")
                .setContentText(bean.getMsg())
                .setTicker(bean.getName())
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)

                .setAutoCancel(true);

        Notification notification = mBuilder.getNotification();
        notification.defaults = Notification.DEFAULT_ALL;



        notification.contentIntent = getClickPendingIntent(this,bean);
      //   PendingIntent clickPendingIntent = getClickPendingIntent(this, bean);
      //  PendingIntent dismissPendingIntent = getDismissPendingIntent(this, msg);
       // notification.deleteIntent = dismissPendingIntent;
        //notification.contentIntent = clickPendingIntent;
        manager.notify(id, notification);
    }

    private void showNotification() {
        int id = new Random(System.nanoTime()).nextInt();
      //  oldMessage = msg;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
        Notification.Builder mBuilder = new Notification.Builder(this);


        mBuilder.setContentTitle("银联易考 title")
                .setContentText("银联易考 text")
                .setTicker("银联易考 ticker")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);
        Notification notification = mBuilder.getNotification();
        notification.defaults = Notification.DEFAULT_ALL;



       // PendingIntent clickPendingIntent = getClickPendingIntent(this, msg);
        //PendingIntent dismissPendingIntent = getDismissPendingIntent(this, msg);
        //notification.deleteIntent = dismissPendingIntent;

        manager.notify(id, notification);
    }

  /*  private void showNotification(UMessage msg) {
        int id = new Random(System.nanoTime()).nextInt();
        oldMessage = msg;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
        Notification.Builder mBuilder = new Notification.Builder(this);
        mBuilder.setContentTitle(msg.title)
                .setContentText(msg.text)
                .setTicker(msg.ticker)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true);
        Notification notification = mBuilder.getNotification();
        PendingIntent clickPendingIntent = getClickPendingIntent(this, msg);
        PendingIntent dismissPendingIntent = getDismissPendingIntent(this, msg);
        notification.deleteIntent = dismissPendingIntent;
        notification.contentIntent = clickPendingIntent;
        manager.notify(id, notification);
    }
*/
    public PendingIntent getClickPendingIntent(Context context, notiftyBean msg) {
        Intent clickIntent = new Intent();
        clickIntent.setClass(context, MessageActivity.class);
        clickIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      /*  clickIntent.putExtra("msg",msg);
        clickIntent.setAction(".ui.user.messageactivity");*/
       /* clickIntent.putExtra(NotificationBroadcast.EXTRA_KEY_ACTION,
                NotificationBroadcast.ACTION_CLICK);*/
        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(context,
                (int) (System.currentTimeMillis()),
                clickIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        return clickPendingIntent;
    }

  /*  public PendingIntent getClickPendingIntent(Context context, UMessage msg) {
        Intent clickIntent = new Intent();
        clickIntent.setClass(context, NotificationBroadcast.class);
        clickIntent.putExtra(NotificationBroadcast.EXTRA_KEY_MSG,
                msg.getRaw().toString());
        clickIntent.putExtra(NotificationBroadcast.EXTRA_KEY_ACTION,
                NotificationBroadcast.ACTION_CLICK);
        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(context,
                (int) (System.currentTimeMillis()),
                clickIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        return clickPendingIntent;
    }*/

    public PendingIntent getDismissPendingIntent(Context context, UMessage msg) {
        Intent deleteIntent = new Intent();
        deleteIntent.setClass(context, NotificationBroadcast.class);
        deleteIntent.putExtra(NotificationBroadcast.EXTRA_KEY_MSG,
                msg.getRaw().toString());
        deleteIntent.putExtra(
                NotificationBroadcast.EXTRA_KEY_ACTION,
                NotificationBroadcast.ACTION_DISMISS);
        PendingIntent deletePendingIntent = PendingIntent.getBroadcast(context,
                (int) (System.currentTimeMillis() + 1),
                deleteIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        return deletePendingIntent;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
