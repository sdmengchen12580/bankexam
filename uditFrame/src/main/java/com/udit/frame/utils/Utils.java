package com.udit.frame.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class Utils {


    public static double getVersionCode(Context context)
    {
        int versionCode = 0;
        String versionName = "";
        try
        {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            //  versionCode = context.getPackageManager().getPackageInfo("cn.microvideo.sutongbridgemanage", 0).versionCode;
            versionName =context.getPackageManager().getPackageInfo("com.udit.bankexam", 0).versionName;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        //versionName+=versionCode;

        return  Double.parseDouble(versionName);
    }

    public static String ToSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }


    public static void startQQ(Context context, String qq) {
        String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + qq;
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public static boolean isQQAvailable(Context context) {
        final PackageManager mPackageManager = context.getPackageManager();
        List<PackageInfo> pinfo = mPackageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {

                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
                if(pn.equals("com.tencent.minihd.qq"))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static String doubleOutPut(String paramString, int paramInt) {
        try {
            double d = Double.parseDouble(paramString);
            if (d == Double.valueOf(d).intValue()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Double.valueOf(d).intValue());
                stringBuilder.append("");
                return stringBuilder.toString();
            }
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            numberFormat.setMaximumFractionDigits(paramInt);
            numberFormat.setMinimumFractionDigits(paramInt);
            return numberFormat.format(d);
        } catch (Exception e) {
            return "0.00";
        }
    }

    public static String puzero(int num) {
        // 得到一个NumberFormat的实例
        NumberFormat nf = NumberFormat.getInstance();
        // 设置是否使用分组
        nf.setGroupingUsed(false);
        // 设置最大整数位数
        nf.setMaximumIntegerDigits(2);
        // 设置最小整数位数
        nf.setMinimumIntegerDigits(2);
        // 输出测试语句
        String n = nf.format(num);
        return n;
    }

    /**
     * 获取项目下的music 路径
     * <功能详细描述>
     *
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getPath(Context context) {
        File str = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        return str.getAbsolutePath();
    }

    public static String unicode2string(String s) {
        List<String> list = new ArrayList<String>();
        String zz = "\\\\u[0-9,a-z,A-Z]{4}";

        // 正则表达式用法参考API
        Pattern pattern = Pattern.compile(zz);
        Matcher m = pattern.matcher(s);
        while (m.find()) {
            list.add(m.group());
        }
        for (int i = 0, j = 2; i < list.size(); i++) {
            String st = list.get(i).substring(j, j + 4);

            // 将得到的数值按照16进制解析为十进制整数，再強转为字符
            char ch = (char) Integer.parseInt(st, 16);
            // 用得到的字符替换编码表达式
            s = s.replace(list.get(i), String.valueOf(ch));
        }
        return s;
    }

    /**
     * Editext失去焦点 恢复hint
     */
    public static OnFocusChangeListener onFocusAutoClear = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            EditText textView = (EditText) v;
            String hint;
            if (hasFocus) {
                hint = textView.getHint().toString();
                textView.setTag(hint);
                textView.setHint("");
            } else {
                hint = textView.getTag().toString();
                textView.setHint(hint);
            }

        }
    };

    /**
     * 读取表情配置文件
     *
     * @param context
     * @return
     */
    public static List<String> getEmojiFile(Context context) {
        try {
            List<String> list = new ArrayList<String>();
            InputStream in = context.getResources().getAssets().open("emoji");
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String str = null;
            while ((str = br.readLine()) != null) {
                list.add(str);
            }
            // if(list.size()!=0){
            // Log.i("tag", "emoji配置文件读取到数据");
            // }else{
            // Log.i("tag", "emoji配置文件未读取到数据");
            // }

            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 跳转到拨号页面
     * <功能详细描述>
     *
     * @param phone
     * @param context
     * @see [类、类#方法、类#成员]
     */
    public static void sysPhone_call(String phone, Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "无法进拨打电话界面", Toast.LENGTH_LONG).show();
        }
    }

    // 首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    // 首字母转大写
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static String getPer(String answerCount, String allCount) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);


        try {
            int answer = Integer.parseInt(answerCount);
            int count = Integer.parseInt(allCount);
            if (count == 0)
                return "0%";
            String result = numberFormat.format((float) answer / (float) count * 100);

            return result + "%";
        } catch (NumberFormatException e) {
            return "0%";
        }

    }
}
