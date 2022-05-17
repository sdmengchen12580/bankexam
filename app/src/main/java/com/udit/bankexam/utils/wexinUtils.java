/*
package com.udit.bankexam.utils;

import com.udit.bankexam.bean.PayWeiXinInfo;
import com.udit.bankexam.constant.Constant;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

*/
/**
 * Created by zb on 2017/5/24.
 *//*


public class wexinUtils {

    private final static String TAG = wexinUtils.class.getSimpleName();

    public static final String API_KEY="cd9o3o9ehqc35tihao5yqwv7mr0uh7nx";

    public static String getSign(PayWeiXinInfo info,String time) throws Exception {
        TreeMap<String, Object> map= new TreeMap<String, Object>();
        map.put("appid",info.getAppid());
        map.put("partnerid",info.getMch_id();
        map.put("prepayid",info.getPrepay_id());
        map.put("package","Sign=WXPay");
        map.put("noncestr","123456789");
        map.put("timestamp",time);
        Set<String> set = map.keySet();
        Iterator<String> iterator =  set.iterator();
        StringBuffer buffer = new StringBuffer();
        while (iterator.hasNext())
        {
            String key = iterator.next();
            Object value = map.get(key);
            buffer.append(key+"="+value);
        }
        buffer.append("key="+API_KEY);
        MyLogUtils.e(TAG,buffer.toString());
        String sign = MD5Util.getMD5Str(buffer.toString()).toUpperCase();
        MyLogUtils.e(TAG,"SIGN:"+sign);
        return sign;

    }

}
*/
