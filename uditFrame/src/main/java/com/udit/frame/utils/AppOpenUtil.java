package com.udit.frame.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;

/**
 * 
 * app 打开 / 安装方法
 * @author 曾宝
 * @version  [V1.00, 2017年4月5日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class AppOpenUtil
{
    private static final String TAG =AppOpenUtil.class.getSimpleName();
    
    public static void OpenApp(final Context mContext,final String src,
        String pkg,String cls,Drawable icon)
    {
        try
        {
            ComponentName componet = new ComponentName(pkg, cls);
            Intent i = new Intent();
            i.setComponent(componet);
            mContext.startActivity(i);
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, e.getMessage());
            if (copyApkFromAssets(mContext, src,
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+src))
            {
                Builder m = new Builder(mContext)
                    .setIcon(icon)
                    .setMessage("是否安装？")
                    .setIcon(icon)
                    .setNegativeButton("返回", new OnClickListener()
                    {
                        
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            
                        }
                    })
                    .setPositiveButton("确定", new OnClickListener()
                    {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        /*Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setDataAndType(Uri.parse("file://" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+src), "application/vnd.android.package-archive");
                        mContext.startActivity(intent);*/
                        File apkFile = new File(mContext.getCacheDir() + File.separator+"com.udit.BankExam");

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                            //判断是否是AndroidN以及更高的版本
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Uri apkUri = FileProvider.getUriForFile(mContext, "com.udit.BankExam.fileprovider"
                                    , apkFile);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                        } else {

                            intent.setDataAndType(Uri.fromFile(apkFile),"application/vnd.android.package-archive");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        }
                        mContext.startActivity(intent);

                    }
                });
                m.show();
            }
        }
    }
    
    private static boolean copyApkFromAssets(Context context, String fileName, String path)
    {
        boolean copyIsFinish = false;
        try
        {
            InputStream is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0)
            {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return copyIsFinish;
    }
}
