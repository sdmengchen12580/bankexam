package com.udit.bankexam.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;

public class JsonUtils
{
    private static final String TAG = JsonUtils.class.getSimpleName();
    
    public static MsgBean parseDuanxin(String result)
    {
        try
        {
            JSONArray array = new JSONArray(result);
            String str_object =  array.getString(0);
            /*if(JsonUtil.getJsonForOK(str_object))
            {*/
                MsgBean bean = JsonUtil.jsonToBean(str_object, MsgBean.class);
                if(bean!=null)
                {
                    return bean;
                }
                else
                    return null;
           /* }
            else
                return null;*/
        }
        catch (JSONException e)
        {
            MyLogUtils.e(TAG, e.getMessage());
            return null;
        }
    }

    public static UserBean parseUser(String result)
    {
        try
        {
            JSONArray array = new JSONArray(result);
            String str_object =  array.getString(0);
            if(JsonUtil.getJsonForOK(str_object))
            {
                UserBean bean = JsonUtil.jsonToBean(str_object, UserBean.class);
                if(bean!=null)
                {
                    return bean;
                }
                else
                    return null;
            }
            else
                return null;
        }
        catch (JSONException e)
        {
            MyLogUtils.e(TAG, e.getMessage());
            return null;
        }
    }
}
