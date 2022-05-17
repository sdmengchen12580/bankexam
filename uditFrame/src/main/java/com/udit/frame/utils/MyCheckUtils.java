package com.udit.frame.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.udit.frame.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * 
 * 公共检查 工具类
 * 
 * @author 曾宝
 * @version [V1.00, 2015年7月14日]
 * @see [相关类/方法]
 * @since V1.00
 */
@SuppressLint("ShowToast")
public class MyCheckUtils
{
    
    @SuppressWarnings("unused")
    private Context context;
    
    public MyCheckUtils(Context context)
    {
        this.context = context;
    }
    
    /**
     * 检查是否为空
     * <功能详细描述>
     * 
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmpty(String str)
    {
        if (str == null || str.equals("") || str.equals("null"))
        {
            return true;
        }
        return false;
    }
    
    public static boolean isYZMCheck(String str,Context mContext)
    {
        if (str == null || str.equals("") || str.equals("null"))
        {
            return true;
        }
        return false;
    }
    
    /**
     * 验证sms 信息
     * <功能详细描述>
     * 
     * @param str
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isSms(String str, Context context)
    {
        if (isEmpty(str))
        {
            ToastUtil.showMessage(context, R.string.err_sms_null);
            //Toast.makeText(context, R.string.err_sms_null, Toast.LENGTH_SHORT).show();
            return false;
        }
        Pattern p = Pattern.compile("\\d{6}");
        Matcher m = p.matcher(str);
        if (!m.matches())
        {
            ToastUtil.showMessage(context, R.string.err_sms_len);
           // Toast.makeText(context, R.string.err_sms_len, Toast.LENGTH_SHORT).show();
        }
        return m.matches();
    }
    
    
    public static boolean checkSmsAgain(String str,String save, Context context)
    {
        if (isEmpty(str))
        {
            ToastUtil.showMessage(context, R.string.err_sms_null);
            //Toast.makeText(context, R.string.err_sms_null, Toast.LENGTH_SHORT).show();
            return false;
        }
        Pattern p = Pattern.compile("\\d{6}");
        Matcher m = p.matcher(str);
        if (!m.matches())
        {
            ToastUtil.showMessage(context, R.string.err_sms_len);
            return false;
           // Toast.makeText(context, R.string.err_sms_len, Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(str.equals(save))
            {
                return true;
            }
            else
            {
                ToastUtil.showMessage(context, "输入的验证码错误");
                return false;
            }
        }
       
    }
    
    /**
     * 验证复选框
     * <功能详细描述>
     * 
     * @param str
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isCheckBox(boolean flag, Context context)
    {
        if (!flag)
        {
            Toast.makeText(context, R.string.err_checkbox_false, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    
    public static boolean isValidateMsgCheck(String validateMsg, Context context)
    {
        if (!isEmpty(validateMsg))
        {
            Pattern p = Pattern.compile("\\d{6}");
            Matcher m = p.matcher(validateMsg);
            if (!m.matches())
            {
                Toast.makeText(context, R.string.err_validatemsg, Toast.LENGTH_LONG).show();
            }
            return m.matches();
        }
        else
        {
            Toast.makeText(context, R.string.err_validatemsg_null, Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public static boolean checkStr(String matchStr,String str)
    {
        int n = matchStr.length();
        String m = "["+matchStr+"]{"+n+"}";
        Pattern pattern = Pattern.compile(m);
        Matcher ms = pattern.matcher(str);
        if(ms.matches())
        {
            return true;
        }
        else
            return false;
    }

    /**
     * 手机号码检查
     * <功能详细描述>
     * 
     * @param mobileNumber
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isMobileCheck(String mobileNumber, Context context)
    {
        if (!isEmpty(mobileNumber))
        {
            Pattern p = Pattern.compile("^1[0-9]{10}$");
            Matcher m = p.matcher(mobileNumber);
            if (!m.matches())
            {
                ToastUtil.showMessage(context, R.string.err_mobile);
               // Toast.makeText(context, R.string.err_mobile, 1000).show();
            }
            return m.matches();
        }
        else
        {
            ToastUtil.showMessage(context, R.string.err_mobile_null);
            //Toast.makeText(context, R.string.err_mobile_null, 1000).show();
        }
        return false;
    }
    
    /**
     * password 2次
     * <功能详细描述>
     * 
     * @param password
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isPasswordCheck(String password, String again_password, Context context)
    {
        if (!password.equals(again_password))
        {
            ToastUtil.showMessage(context, R.string.err_password_not_agin);
           // Toast.makeText(context, R.string.err_password_not_agin, Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            if(isPasswordCheck(password, context))
                return true;
            else
                return false;
        }
    }
    
    /**
     * password 检查
     * <功能详细描述>
     * 
     * @param password
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isPasswordCheck(String password, Context context)
    {
        if (!isEmpty(password))
        {
            if (password.length() >= 6)
            {
               /* if(passCheck(password))
                {*/
                    return true;
               /* }
                else
                {
                    ToastUtil.showMessage(context, "密码长度必须为6-16位");
                   // Toast.makeText(context, "密码必须为数字+字母，长度必须超过6位", 1000).show();
                }*/
            }
            else
            {
                ToastUtil.showMessage(context, R.string.err_password_len);
              //  Toast.makeText(context, R.string.err_password_len, 1000).show();
            }
        }
        else
        {
            ToastUtil.showMessage(context, R.string.err_password_null);
           // Toast.makeText(context, R.string.err_password_null, 1000).show();
        }
        return false;
    }
    
    public static boolean passCheck(String s)
    {
        //(?![^a-zA-Z]+$)
       // String str="^(?!\\D+$).{6,16}$";
        String str="\\d{6}";
       
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(s);
        if (!m.matches())
        {
           return false;
        }
        else
        {
           return true;
        }
        
    }

    /**
     * 检查用户名
     * <功能详细描述>
     * 
     * @param userName
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isUserNameCheck(String userName, Context context)
    {
        if (!isEmpty(userName))
        {
            if (userName.length() > 16)
            {
                Toast.makeText(context, R.string.err_username_len_max, 1000).show();
            }
            else if (userName.length() <= 2)
            {
                Toast.makeText(context, R.string.err_username_len_min, 1000).show();
            }
            else
            {
                return true;
            }
        }
        else
        {
            Toast.makeText(context, R.string.err_username_null, 1000).show();
        }
        return false;
    }
    
    /**
     * 年龄检查
     * <功能详细描述>
     * 
     * @param age
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressLint("ShowToast")
    public static boolean isAgeCheck(String age, Context context)
    {
        if (!isEmpty(age))
        {
            Pattern p = Pattern.compile("(^1[3|4|5|7|8][0-9]{9}$)");
            Matcher m = p.matcher(age);
            if (!m.matches())
            {
                Toast.makeText(context, R.string.err_age, 1000).show();
            }
            else
            {
                return true;
            }
        }
        else
        {
            Toast.makeText(context, R.string.err_age_null, 1000).show();
        }
        return false;
    }

    public static boolean isDoubleEmpty(String price) {
        double price_d = Double.parseDouble(price);
        int flag = Double.compare(price_d,0.0f);
        if(flag==0)
        {
            return true;
        }
        else
            return false;

    }
}
