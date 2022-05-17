package com.udit.frame.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import com.udit.frame.common.down.EnumFileType;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 
 * 常用工具类
 * 
 * @author 曾宝
 * @version [V1.00, 2016年2月14日]
 * @see [相关类/方法]
 * @since V1.00
 */
@SuppressLint({"ClickableViewAccessibility", "DefaultLocale"})
public class MyCommonUtils
{
    
    public static String StringFormatTwoZero(String number)
    {
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        int n = Integer.parseInt(number);
        String str = String.format("%02d", n);
        System.out.println(str); // 0001
        return str;
    }
    
    
 
    
    /**
     * edit 自动换行，顶部
     * <功能详细描述>
     * 
     * @param view
     * @see [类、类#方法、类#成员]
     */
    public static void setTopEdit(EditText view)
    {
        view.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        // 文本显示的位置在 EditText的最上方
        view.setGravity(Gravity.TOP);
        // 改变默认的单行模式
        view.setSingleLine(false);
        // 水平滚动设置为False
        view.setHorizontallyScrolling(false);
    }
    
    public static int setInt(int num, boolean flag)
    {
        if (flag)
        {
            num++;
        }
        else
        {
            if (num <= 0)
                return num;
            num--;
        }
        return num;
    }
    
    /**
     * 根据文件后缀名获得对应的MIME类型。
     * 
     * @param file
     */
    @SuppressLint("DefaultLocale")
    public static String getMIMEType(File file)
    {
        String type = "*/*";
        String fName = file.getAbsolutePath();
        // 获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0)
        {
            return type;
        }
        /* 获取文件的后缀名 */
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "")
            return type;
        type = EnumFileType.getMime(end);
        return type;
    }
    
    /**
     * 打开文件
     * 
     * @param file
     */
    public static void openFile(File file, Context mContext)
    {
        // Uri uri = Uri.parse("file://"+file.getAbsolutePath());
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        // 获取文件file的MIME类型
        String type = getMIMEType(file);
        // 设置intent的data和Type属性。
        intent.setDataAndType(Uri.fromFile(file), type);
        // 跳转
        mContext.startActivity(intent);
    }
    
    public static File getFile(String file, Context mContext, String name)
    {
        InputStream is;
        
        File tmpFile = new File(mContext.getExternalFilesDir(null), name); // 该路径指向SD卡目录下一个特定文件夹，需要在uses-permission设置WRITE-EXTERNAL-STORAGE权限
        
        try
        {
            
            is = mContext.getAssets().open(name);
            if (!tmpFile.exists())
            {
                FileOutputStream fos = new FileOutputStream(tmpFile);
                byte[] buffer = new byte[400000];
                int count = 0;
                while ((count = is.read(buffer)) > 0)
                {
                    fos.write(buffer, 0, count);
                }
                fos.close();
            }
            is.close();
        }
        catch (IOException e)
        {
            return null;
        }
        
        return tmpFile;
    }
    
    /**
     * 获取项目下的music 路径
     * <功能详细描述>
     * 
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getPath(Context context)
    {
        File str = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        return str.getAbsolutePath();
    }
    
    /**
     * 跳转指定页面（go_app_activity=1） 或者 url
     * <功能详细描述>
     * 
     * @param url
     * @param action
     * @param Key
     * @param mContext
     * @see [类、类#方法、类#成员]
     */
    public static void intentUrl(String url, String action, String Key, Context mContext)
    {
        try
        {
            if (url == null)
                return;
            if (url.contains("go_app_activity="))
            {
                MyLogUtils.e("url:", url);
                String[] str = url.split("=");
                if (str != null && str.length == 2)
                {
                    int id = 0;
                    try
                    {
                        id = Integer.parseInt(str[1]);
                        Intent intent = new Intent();
                        intent.setAction(action);
                        intent.putExtra(Key, id);
                        MyLogUtils.e("url_id:", id + "");
                        intent = Intent.createChooser(intent, null);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                    catch (Exception e)
                    {
                        MyLogUtils.e("TAG", e.getMessage());
                    }
                    
                }
            }
            else
            {
                if (!url.contains("http://"))
                {
                    url = "http://" + url;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                intent = Intent.createChooser(intent, null);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
            
        }
        catch (Exception e)
        {
            Toast.makeText(mContext, "无法进web界面", Toast.LENGTH_LONG).show();
        }
    }
    
    /**
     * Editext失去焦点 恢复hint
     */
    public static OnFocusChangeListener onFocusAutoClear = new OnFocusChangeListener()
    {
        @Override
        public void onFocusChange(View v, boolean hasFocus)
        {
            EditText textView = (EditText)v;
            String hint;
            if (hasFocus)
            {
                hint = textView.getHint().toString();
                textView.setTag(hint);
                textView.setHint("");
            }
            else
            {
                hint = textView.getTag().toString();
                textView.setHint(hint);
            }
            
        }
    };
    
    public static String getFileType(String path)
    {
        if (MyCheckUtils.isEmpty(path))
            throw new RuntimeException("path is null");
        
        String type = path.substring(path.lastIndexOf(".") + 1);
        return type;
    }
    
    /**
     * 读取表情配置文件
     * 
     * @param context
     * @return
     */
    public static List<String> getEmojiFile(Context context)
    {
        try
        {
            List<String> list = new ArrayList<String>();
            InputStream in = context.getResources().getAssets().open("emoji");
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String str = null;
            while ((str = br.readLine()) != null)
            {
                list.add(str);
            }
            // if(list.size()!=0){
            // Log.i("tag", "emoji配置文件读取到数据");
            // }else{
            // Log.i("tag", "emoji配置文件未读取到数据");
            // }
            
            return list;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void intentUrl(String url, String action, Context mContext)
    {
        try
        {
            if (url == null)
                return;
            if (url.contains("go_app_activity="))
            {
                MyLogUtils.e("url:", url);
                String[] str = url.split("=");
                if (str != null && str.length == 2)
                {
                    int id = 0;
                    try
                    {
                        id = Integer.parseInt(str[1]);
                        Intent intent = new Intent();
                        intent.setAction(action);
                        MyLogUtils.e("url_id:", id + "");
                        intent = Intent.createChooser(intent, null);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                    catch (Exception e)
                    {
                        MyLogUtils.e("TAG", e.getMessage());
                    }
                    
                }
            }
            else
            {
                if (!url.contains("http://"))
                {
                    url = "http://" + url;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                intent = Intent.createChooser(intent, null);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
            
        }
        catch (Exception e)
        {
            Toast.makeText(mContext, "无法进web界面", Toast.LENGTH_LONG).show();
        }
    }
    
    /**
     * 跳转到拨号页面
     * <功能详细描述>
     * 
     * @param phone
     * @param context
     * @see [类、类#方法、类#成员]
     */
    public static void sysPhone_call(String phone, Context context)
    {
        try
        {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        catch (Exception e)
        {
            Toast.makeText(context, "无法进拨打电话界面", Toast.LENGTH_LONG).show();
        }
    }
    
    // 首字母转小写
    public static String toLowerCaseFirstOne(String s)
    {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
    // 首字母转大写
    public static String toUpperCaseFirstOne(String s)
    {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
    /**
     * 文本分行，置顶
     * <功能详细描述>
     * 
     * @param editText
     * @see [类、类#方法、类#成员]
     */
    public static void setEditTop(EditText editText)
    {
        editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        // 文本显示的位置在EditText的最上方
        editText.setGravity(Gravity.TOP);
        // 改变默认的单行模式
        editText.setSingleLine(false);
        // 水平滚动设置为False
        editText.setHorizontallyScrolling(false);
    }
    
    /**
     * 设置空间的可见性
     * <功能详细描述>
     * 
     * @param flag
     * @param img
     * @see [类、类#方法、类#成员]
     */
    public static void setVisible(boolean flag, View view)
    {
        if (flag)
        {
            view.setVisibility(View.VISIBLE);
        }
        else
        {
            view.setVisibility(View.GONE);
        }
    }
    
    /**
     * 获取屏幕的宽度
     * <功能详细描述>
     * 
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static DisplayMetrics getscreen(Context context)
    {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        return dm;
    }
    
    @SuppressWarnings("unused")
    public static PopupWindow getPopWindow(Context context, int xmlID, int width, int height)
    {
        View view = LayoutInflater.from(context).inflate(xmlID, null, false);
        final PopupWindow popupWindow = new PopupWindow(view, width, height, true);
        popupWindow.setTouchInterceptor(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (popupWindow != null && popupWindow.isShowing())
                {
                    popupWindow.dismiss();
                }
                return false;
            }
        });
        if (popupWindow != null)
        {
            popupWindow.dismiss();
            return popupWindow;
        }
        return null;
    }
    
    public static int[] getMeasure(View view)
    {
        if (view.getLayoutParams() instanceof LinearLayout.LayoutParams)
        {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)view.getLayoutParams();
            int width = layoutParams.width + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = layoutParams.height + layoutParams.topMargin + layoutParams.bottomMargin;
            return new int[] {width, height};
        }
        else if (view.getLayoutParams() instanceof RelativeLayout.LayoutParams)
        {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)view.getLayoutParams();
            int width = layoutParams.width + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = layoutParams.height + layoutParams.topMargin + layoutParams.bottomMargin;
            return new int[] {width, height};
        }
        else
            throw new RuntimeException("layoutparams is not linear or relative");
    }
    
    public static int[] getMeasure(LayoutParams params)
    {
        if (params instanceof LinearLayout.LayoutParams)
        {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)params;
            int width = layoutParams.width + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = layoutParams.height + layoutParams.topMargin + layoutParams.bottomMargin;
            return new int[] {width, height};
        }
        else if (params instanceof RelativeLayout.LayoutParams)
        {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)params;
            int width = layoutParams.width + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = layoutParams.height + layoutParams.topMargin + layoutParams.bottomMargin;
            return new int[] {width, height};
        }
        else
            throw new RuntimeException("layoutparams is not linear or relative");
    }
    
    public static int[] getMeasureSpec(View view)
    {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.EXACTLY);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.EXACTLY);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();
        return new int[] {width, height};
    }
    
    /**
     * 获取对应的属性
     * 
     * @param attr
     * @see [类、类#方法、类#成员]
     */
    public static float getAttribute(AttributeSet attrs, String name)
    {
        for (int i = 0; i < attrs.getAttributeCount(); i++)
        {
            String attriName = attrs.getAttributeName(i);
            MyLogUtils.d("tag", "attriName:" + attriName + " name" + name);
            if (attriName.equals(name))
            {
                String value = attrs.getAttributeValue(i);
                value = value.substring(0, value.indexOf("dip"));
                float va = Float.parseFloat(value);
                MyLogUtils.d("tag", "va:" + va);
                return va;
            }
        }
        return 0;
    }
    
    /**
     * 更换 头部信息
     * <功能详细描述>
     * 
     * @param activity
     * @param flag
     * @see [类、类#方法、类#成员]
     */
    public static void setTitleBarState(Activity activity, boolean flag)
    {
        if (flag)
        {
            // 隐藏titlebar
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            
        }
        else
        {
            // 显示titlebar
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        flag = !flag;
    }
    
    /**
     * DIP 转PX
     * <功能详细描述>
     * 
     * @param context
     * @param dipValue
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("deprecation")
    public static int dip2px(Context context, float dipValue)
    {
        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        int w = manager.getDefaultDisplay().getWidth();
        int h = manager.getDefaultDisplay().getHeight();
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        float densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
        MyLogUtils.d("window", "w=" + w + " h=" + h + " density=" + density + " densityDPI=" + densityDPI + " xdpi=" + xdpi + " ydpi=" + ydpi);
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }
    
    /**
     * px 转 dip
     * <功能详细描述>
     * 
     * @param context
     * @param pxValue
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static int px2dip(Context context, float pxValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 1.5f);
    }
    
    /**
     * 隐藏键盘
     * <功能详细描述>
     * 
     * @param context
     * @see [类、类#方法、类#成员]
     */
    public static void HideSoftKeyboard(Context context)
    {
        InputMethodManager inputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null)
        {
            View localView = ((Activity)context).getCurrentFocus();
            if (localView != null && localView.getWindowToken() != null)
            {
                IBinder windowToken = localView.getWindowToken();
                inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
            }
        }
    }
    
    /**
     * 获取字体库
     * <功能详细描述>
     * 
     * @param context
     * @param src
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Typeface getTypeface(Context context, String src)
    {
        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), src);
        return typeFace;
    }
    
}
