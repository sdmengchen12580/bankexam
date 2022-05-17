package com.udit.bankexam.notify;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.udit.bankexam.R;
import com.udit.bankexam.bean.notiftyBean;
import com.udit.frame.utils.MyLogUtils;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageService;
import com.umeng.message.entity.UMessage;
import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;
import java.util.Map;

//完全自定义处理类
public class MyPushIntentService extends UmengMessageService {
    private static final String TAG = MyPushIntentService.class.getName();

    @Override
    public void onMessage(Context context, Intent intent) {
        try {

            //可以通过MESSAGE_BODY取得消息体
            String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
            UMessage msg = new UMessage(new JSONObject(message));
            MyLogUtils.e(TAG,"onMessage");
            MyLogUtils.e(TAG, "message=" + message);    //消息体
            MyLogUtils.e(TAG, "custom=" + msg.custom);    //自定义消息的内容
            MyLogUtils.e(TAG, "title=" + msg.title);    //通知标题
            MyLogUtils.e(TAG, "text=" + msg.text);    //通知内容
            MyLogUtils.e(TAG, "text=" +  msg.extra);    //自定义通知内容解析
            // code  to handle message here
            // ...
            Map<String,String> map_msg =  msg.extra;
            notiftyBean bean  = new notiftyBean();;
            if(map_msg.containsKey("notify_id"))
            {
                bean.setLinkId(map_msg.get("notify_id"));
            }
            if(map_msg.containsKey("name"))
            {
                bean.setName(map_msg.get("name"));
            }
            if(map_msg.containsKey("msg"))
            {
                bean.setMsg(map_msg.get("msg"));
            }
            if(map_msg.containsKey("mType"))
            {
                bean.setmType(map_msg.get("mType"));
            }
            if(map_msg.containsKey("linkId"))
            {
                bean.setLinkId(map_msg.get("linkId"));
            }
            if(map_msg.containsKey("msgUrl"))
            {
                bean.setMsgUrl(map_msg.get("msgUrl"));
            }

           // notiftyBean bean = JsonUtil.jsonToBean(msg.extra,notiftyBean.class);


            // 对完全自定义消息的处理方式，点击或者忽略
            boolean isClickOrDismissed = true;
            if (isClickOrDismissed) {
                //完全自定义消息的点击统计
                UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
            } else {
                //完全自定义消息的忽略统计
                UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
            }

            // 使用完全自定义消息来开启应用服务进程的示例代码
            // 首先需要设置完全自定义消息处理方式
            //mPushAgent.setPushIntentServiceClass(MyPushIntentService.class);
            // code to handle to start/stop service for app process
         //   JSONObject json = new JSONObject(msg.custom);

          //  paySound(context);

            if(bean!=null)
            {
                Intent intent1 = new Intent();
                intent1.setClass(context, MyNotificationService.class);
                intent1.putExtra("message",bean);
                context.startService(intent1);
            }


        } catch (Exception e) {
        }
    }

    private void paySound(Context context) {
       String uri = "android.resource://"+context.getPackageName()+"/"+ R.raw.notfity;

        Uri no = Uri.parse(uri);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),no);
        r.play();

    }


}
