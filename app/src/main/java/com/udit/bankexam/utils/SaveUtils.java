package com.udit.bankexam.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.udit.bankexam.MyApplication;
import com.udit.bankexam.bean.UserBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.ui.exam.holder.HolderWindow;
import com.udit.bankexam.ui.sreach.ExamSreachShouCangActivity;
import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyCheckUtils;
import com.udit.frame.utils.MyDataUtils;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;

import android.content.Context;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;

public class SaveUtils
{
    private static final String FILE_MSG = "file_msg";
    
    private static final String FILE_USER = "file_user";

    private static final String FILE_VIDEO_TYPE_ONE = "file_video_type_one";


    private static final String CODE = "code";
    
    private static final String MOBILE = "mobile";
    
    private static final String PWD = "pwd";

    private static final String PET = "pet";

    private static final String UID = "uid";

    private static final String VIDEO_TYPE_ONE="video_type_one";

    private static final String FILE_EXAM_TEXT_SIZE="file_exam_textsize";

    private static final String TEXTSIZE_EXAM = "exam_textsize";

    public static String getExamTextSize(Context context)
    {
        Object obj_size = MyDataUtils.getData(context, FILE_EXAM_TEXT_SIZE, TEXTSIZE_EXAM, String.class);

        if (obj_size == null)
           return null;

        return obj_size.toString();

    }

    public static void SaveExamTextSize(Context context,String type)
    {
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(TEXTSIZE_EXAM, type);
            MyDataUtils.putData(context, FILE_EXAM_TEXT_SIZE, map);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getMsg(Context context)
    {
        Object obj_code = MyDataUtils.getData(context, FILE_MSG, CODE, String.class);
        
        if (obj_code == null)
            throw new RuntimeException("");
            
        return obj_code.toString();
    }
    
    /**
     * 保存用户信息
     * <功能详细描述>
     * 
     * @param context
     * @param
     * @throws
     * @see [类、类#方法、类#成员]
     */
    public static void saveMsg(Context context, String code)
    {
        try
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(CODE, code);
            MyDataUtils.putData(context, FILE_MSG, map);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
        
    }
    
    public static void deleteMsg(Context context)
    {
        MyDataUtils.deleteData(context, FILE_MSG);
    }

    private static String ACC;
    private static String LASGSIGN;
    private static String SIGNDAYS;

    public static void saveUser(Context context, String uid, String mPhone, String mPwd,String mPet,
                                String acc,String LasgSign,String SignDays)
    {
        try
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(UID, uid);
            map.put(MOBILE, mPhone);
            map.put(PWD, mPwd);
            if(!MyCheckUtils.isEmpty(mPet))
                map.put(PET,mPet);
            if(!MyCheckUtils.isEmpty(acc))
                map.put(ACC,acc);
            if(!MyCheckUtils.isEmpty(LasgSign))
                map.put(LASGSIGN,LasgSign);
            if(!MyCheckUtils.isEmpty(SignDays))
                map.put(SIGNDAYS,SignDays);

            MyDataUtils.putData(context, FILE_USER, map);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public static UserBean getUser(Context context)
    {
        Object obj_uid = MyDataUtils.getData(context, FILE_USER, UID, String.class);
        Object obj_mobile = MyDataUtils.getData(context, FILE_USER, MOBILE, String.class);
        Object obj_pwd = MyDataUtils.getData(context, FILE_USER, PWD, String.class);
        Object obj_pet = MyDataUtils.getData(context,FILE_USER,PET,String.class);
        Object obj_acc = MyDataUtils.getData(context,FILE_USER,ACC,String.class);
        Object obj_lastsign = MyDataUtils.getData(context,FILE_USER,LASGSIGN,String.class);
        Object obj_signdays = MyDataUtils.getData(context,FILE_USER,SIGNDAYS,String.class);
        if (obj_uid == null)
            throw new RuntimeException("");
        if (obj_mobile == null)
            throw new RuntimeException("");
        if (obj_pwd == null)
            throw new RuntimeException("");



        UserBean bean = new UserBean();
        bean.setUid(obj_uid.toString());
        bean.setMobile(obj_mobile.toString());
        bean.setPass(obj_pwd.toString());

        if(obj_pet!=null)
            bean.setPet(obj_pet.toString());

        if(obj_acc!=null)
            bean.setAcc(obj_acc.toString());

        if(obj_lastsign!=null)
            bean.setLastSign(obj_lastsign.toString());
        if(obj_signdays!=null)
            bean.setSignDays(obj_signdays.toString());

        return bean;
    }
    
    public static void deleteUser(Context context)
    {
        MyDataUtils.deleteData(context, FILE_USER);
    }

    public static void saveVideoTypeOne(Context context,String type) {
        try
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(VIDEO_TYPE_ONE, type);
            MyDataUtils.putData(context, FILE_VIDEO_TYPE_ONE, map);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e.getMessage());
        }

    }

    public static void deleteVideoTypeOne(Context context)
    {
        MyDataUtils.deleteData(context,FILE_VIDEO_TYPE_ONE);
    }

    public static String getVideoTypeOne(Context mContext) {
        Object obj_type = MyDataUtils.getData(mContext, FILE_VIDEO_TYPE_ONE, VIDEO_TYPE_ONE, String.class);
        if(obj_type==null)
            return null;
        else
            return obj_type.toString();
    }
    private final static  String FILE_PARAMS_APP = "file_params_app";

    private final static String APP_FIRSTCNT = "app_firstcnt";

    private final static String APP_OUTLINECNT = "app_outlinecnt";

    public static int getAppFirstCnt(Context mContext)
    {
        try {
            Object fistCnt = MyDataUtils.getData(mContext, FILE_PARAMS_APP, APP_FIRSTCNT, Integer.class);
            int fist = Integer.parseInt(fistCnt+"");
            if(fist<=0)
                fist = Constant.ExamData.EXAM_NUM;

            return fist;
        } catch (NumberFormatException e) {
            return Constant.ExamData.EXAM_NUM;
        }
    }

    public static int getAppOutLineCnt(Context mContext)
    {
        try {
            Object outCnt = MyDataUtils.getData(mContext, FILE_PARAMS_APP, APP_OUTLINECNT, Integer.class);
            int out = Integer.parseInt(outCnt+"");
            if(out<=0)
                out = Constant.ExamData.EXAM_HOME_NUM;

            return out;
        } catch (NumberFormatException e) {
            return Constant.ExamData.EXAM_HOME_NUM;
        }
    }


    public static void saveParams(Context context,String firstCnt, String outlineCnt) {
        HashMap<String,Object> map_params = new HashMap<>();
        try {
            int fistr = Integer.parseInt(firstCnt);
            int outline = Integer.parseInt(outlineCnt);

            if(fistr<=0)
                fistr = Constant.ExamData.EXAM_HOME_NUM;

            if(outline<=0)
                outline = Constant.ExamData.EXAM_NUM;

            map_params.put(APP_FIRSTCNT,fistr);
            map_params.put(APP_OUTLINECNT,outline);
        } catch (Exception e) {
            map_params.put(APP_FIRSTCNT,Constant.ExamData.EXAM_HOME_NUM);
            map_params.put(APP_OUTLINECNT,Constant.ExamData.EXAM_NUM);
        }

        try {
            MyDataUtils.putData(context,FILE_PARAMS_APP,map_params);
        } catch (Exception e) {
        }
    }


    private static final String SHOUYE_ID = "shouye_id";

    private static final String FILE_SHOUYE_ID = "file_shouye_id";
    public static void saveExamShouYe(Context mContext,String id) {
        if(MyCheckUtils.isEmpty(id))
            return;
        HashMap<String,Object> map_params = new HashMap<>();
        map_params.put(SHOUYE_ID,id);

        MyDataUtils.putData(mContext,FILE_SHOUYE_ID,map_params);
    }

    public static  String getExamShouYe(Context mContext)
    {
        Object shouye_id = MyDataUtils.getData(mContext, FILE_SHOUYE_ID, SHOUYE_ID, String.class);

        if(shouye_id!=null && !MyCheckUtils.isEmpty(shouye_id.toString()))
        {
            String shoye = shouye_id.toString();
            return shoye;
        }
        else
            return null;


    }

    public static boolean getNotify(Context mContext) {
        Object obj_flag = MyDataUtils.getData(mContext, NOTIFY, FLAG_ZHIBO, boolean.class);
        return Boolean.parseBoolean(obj_flag.toString());

    }

    private static final String FLAG_ZHIBO = "FLAG_ZHIBO";


    private static final String NOTIFY = "NOTIFY";


    private static final String FLAG_ZHIBO_FIRST = "FLAG_ZHIBO_FIRST";


    private static final String NOTIFY_FIRST = "NOTIFY_FIRST";

    private static final String SREACH_KEY_FILE = "SREACH_KEY_FILE";

    private static final String SREACH_KEY = "SREACH_KEY";


    public static Integer getFirst(Context mContext)
    {
        Object obj_flag = MyDataUtils.getData(mContext, NOTIFY_FIRST, FLAG_ZHIBO_FIRST, int.class);
        if(obj_flag!=null)
        {
            int integer = Integer.parseInt(obj_flag.toString());
            if(integer>0)
                return integer;
            else
                return null;
        }
        else
            return null;
    }

    public static void saveFirst(Context mContext,int num)
    {

        HashMap<String,Object> map_params = new HashMap<>();
        map_params.put(FLAG_ZHIBO_FIRST,num);

        MyDataUtils.putData(mContext,NOTIFY_FIRST,map_params);

    }

    public static void saveNotify(Context mContext,boolean flag)
    {

        HashMap<String,Object> map_params = new HashMap<>();
        map_params.put(FLAG_ZHIBO,flag);

        MyDataUtils.putData(mContext,NOTIFY,map_params);

    }

    public static List<String> getExamSreachKey(Context mContext) {
        List<String> datalist=new ArrayList<String>();
        Object obj =  MyDataUtils.getData(mContext,SREACH_KEY_FILE, SREACH_KEY, String.class);
        if(obj!=null)
        {
            String msg = obj.toString();
            try {
                JSONArray jsonArray = new JSONArray(msg);
                for(int i = 0;jsonArray!=null && i<jsonArray.length();i++)
                {
                    String key =  jsonArray.getString(i);
                    datalist.add(key);
                }

            } catch (JSONException e) {
                MyLogUtils.e("SaveUtils",e.getMessage());
                return datalist;
            }
            return datalist;
        }
        else
            return datalist;
    }

    public static void saveSreachKey(List<String> mlist_key, Context mContext) {

        Gson gson = new Gson();
        String msg  = gson.toJson(mlist_key);
        Map<String,Object> map = new HashMap<>();
        map.put(SREACH_KEY,msg);
        MyDataUtils.putData(mContext,SREACH_KEY_FILE,map);
    }
}
