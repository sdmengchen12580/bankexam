package com.udit.frame.common.down;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.udit.frame.R;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DownFile
{
    /* 下载中 */
    private static final int DOWNLOAD = 1;
    
    /* 下载结束 */
    private static final int DOWNLOAD_FINISH = 2;

    public static final int ERROR = 3;

    public static final int START = 4;
    
    /* 保存解析的XML信息 */
    HashMap<String, String> mHashMap;
    
    /* 下载保存路径 */
    private String mSavePath;
    
    /* 记录进度条数量 */
    private int progress;
    
    /* 是否取消更新 */
    private boolean cancelUpdate = false;
    
    private Context mContext;
    
    /* 更新进度条 */
    private ProgressBar mProgress;
    
    private Dialog mDownloadDialog;
    
    private File mFile =null;
    
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case START:
                    mDownloadDialog.show();
                    break;
                // 正在下载
                case DOWNLOAD:
                    // 设置进度条位置
                    mProgress.setProgress(progress);
                    break;
                case DOWNLOAD_FINISH:
                    if(mFile!=null)
                    {
                        // 安装文件
                        openFile(mFile);
                    }
                    break;
                case ERROR:
                    Toast.makeText(mContext, "文件未存档", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        };
    };
    
    public DownFile(Context context)
    {
        this.mContext = context;
        mHashMap = new HashMap<String, String>();
    }
    
    public void setDownInfo(String url,String name)
    {
        mHashMap.put("url", url);
        mHashMap.put("name", name);
        showDownloadDialog();
    }
    
    /**
     * 显示软件下载对话框
     */
    @SuppressLint("InflateParams")
    private void showDownloadDialog()
    {
        // 构造软件下载对话框
        Builder builder = new Builder(mContext);
        builder.setTitle(R.string.strings_filedown_downing);
        // 给下载对话框增加进度条
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.softupdate_progress, null);
        mProgress = (ProgressBar)v.findViewById(R.id.update_progress);
        builder.setView(v);
        // 取消更新
        builder.setNegativeButton(R.string.strings_filedown_cancel, new OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                // 设置取消状态
                cancelUpdate = true;
            }
        });
        mDownloadDialog = builder.create();
        
        // 现在文件
        downloadApk();
    }
    
    /**
     * 下载apk文件
     */
    private void downloadApk()
    {
        // 启动新线程下载软件
        new downloadApkThread().start();
    }
    
    /**
     * 下载文件线程
     * 
     * @author coolszy
     * @date 2012-4-26
     * @blog http://blog.92coding.com
     */
    private class downloadApkThread extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                {
                    // 获得存储卡的路径     Environment.getExternalStorageDirectory()
                    String sdpath = Environment.getExternalStorageDirectory() + "/";
                    mSavePath = sdpath + "银行易考下载文件";
                    URL url = new URL(mHashMap.get("url"));
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                   /*
                    conn.connect();*/
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();
                    
                    File file = new File(mSavePath);
                    // 判断文件目录是否存在
                    if (!file.exists())
                    {
                        file.mkdir();
                    }
                    File apkFile = new File(mSavePath, mHashMap.get("name"));
                    mFile = apkFile;
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    mHandler.sendEmptyMessage(START);
                    // 写入到文件中
                    do
                    {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置
                        progress = (int)(((float)count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0)
                        {
                            // 下载完成
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);// 点击取消就停止下载.
                    fos.close();
                    is.close();
                }
            }
            catch (Exception e)
            {
                mHandler.sendEmptyMessage(ERROR);
            }
            // 取消下载对话框显示
            mDownloadDialog.dismiss();
        }
    };
    
    /**
     * 根据文件后缀名获得对应的MIME类型。
     * 
     * @param file
     */
    @SuppressLint("DefaultLocale")
    private String getMIMEType(File file)
    {
        String type = "*/*";
        String fName = file.getName();
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
    private void openFile(File file)
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
    
}
