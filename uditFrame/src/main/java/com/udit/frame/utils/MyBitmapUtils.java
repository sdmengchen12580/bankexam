package com.udit.frame.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.udit.frame.R;
import com.udit.frame.common.base64.BASE64Decoder;
import com.udit.frame.common.base64.BASE64Encoder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;

public class MyBitmapUtils
{
    
    public Bitmap getSmallBitmap(String path)
    {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int REQUIRE_SIZE = 80;
        int scare = 1;
        
        while (true)
        {
            if (options.outWidth <= REQUIRE_SIZE || options.outHeight <= REQUIRE_SIZE)
            {
                break;
            }
            else
            {
                options.outWidth = options.outWidth / 2;
                options.outHeight = options.outHeight / 2;
                scare++;
            }
        }
        Options newoptions = new Options();
        newoptions.inSampleSize = scare;
        return BitmapFactory.decodeFile(path, newoptions);
    }
    
    @SuppressLint("NewApi")
    public static long getBitmapsize(Bitmap bitmap)
    {
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1)
        {
            return bitmap.getByteCount();
        }
        // Pre HC-MR1
        return bitmap.getRowBytes() * bitmap.getHeight();
        
    }
    
    // 保存 bitmap 到SD卡F
    public static boolean saveBitmapToSDCard(Bitmap bitmap, String filePath, String fileName)
    {
        boolean flag = false;
        if (null != bitmap)
        {
            try
            {
                fileName = fileName + ".jpg";
                File file = new File(filePath);
                if (!file.exists())
                {
                    file.mkdirs();
                }
                File f = new File(filePath + fileName);
                if (f.exists())
                {
                    f.delete();
                }
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(f));
                bitmap.compress(CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
                flag = true;
            }
            catch (FileNotFoundException e)
            {
                flag = false;
            }
            catch (IOException e)
            {
                flag = false;
            }
        }
        return flag;
        
    }
    
    /**
     * 
     * @param drawable
     * @return bitmap
     */
    public static Bitmap drawableToBitmap2(Drawable drawable)
    {
        BitmapDrawable bd = (BitmapDrawable)drawable;
        return bd.getBitmap();
    }
    
    /**
     * 
     * @param bitmap
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Drawable bitmapTodrawable(Bitmap bitmap)
    {
        Drawable drawable = new BitmapDrawable(bitmap);
        drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
        return drawable;
    }
    
    public static Bitmap drawableToBitmap(Drawable drawable)
    {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        
        // 取 drawable 的颜色格式
        Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }
    
    /**
     * 根据文字获取图片
     * 
     * @param text
     * @return
     */
    public static Bitmap getIndustry(Context context, String text)
    {
        String color = "#ffeeeade";
        
        Bitmap src = BitmapFactory.decodeResource(context.getResources(),
            R.drawable.pic_default);
        int x = src.getWidth();
        int y = src.getHeight();
        Bitmap bmp = Bitmap.createBitmap(x, y, Config.ARGB_8888);
        Canvas canvasTemp = new Canvas(bmp);
        canvasTemp.drawColor(Color.parseColor(color));
        Paint p = new Paint(Paint.FAKE_BOLD_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.parseColor("#ff4e0a13"));
        p.setAlpha(45);
        p.setFilterBitmap(true);
        int size = (int)(18 * context.getResources().getDisplayMetrics().density);
        p.setTextSize(size);
        float tX = (x - getFontlength(p, text)) / 2;
        float tY = (y - getFontHeight(p)) / 2 + getFontLeading(p);
        canvasTemp.drawText(text, tX, tY, p);
        return toRoundCorner(bmp, 2);
    }
    
    /**
     * @return 返回指定笔和指定字符串的长度
     */
    public static float getFontlength(Paint paint, String str)
    {
        return paint.measureText(str);
    }
    
    /**
     * @return 返回指定笔的文字高度
     */
    public static float getFontHeight(Paint paint)
    {
        FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }
    
    /**
     * @return 返回指定笔离文字顶部的基准距离
     */
    public static float getFontLeading(Paint paint)
    {
        FontMetrics fm = paint.getFontMetrics();
        return fm.leading - fm.ascent;
    }
    
    /**
     * 获取圆角图片
     * 
     * @param bitmap
     * @param pixels
     * @return
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels)
    {
        
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        
        return output;
    }
    
    public static String GetImageStr(String path)
    {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = path;// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }
    
    public static boolean GenerateImage(String imgStr, String savePath)
    {// 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i)
            {
                if (b[i] < 0)
                {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(savePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    /**
     * 根据缩放参数，调整图片大小
     * <功能详细描述>
     * 
     * @param bitmap
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Bitmap AdjustBitmap(Bitmap bitmap, float scaleX, float scaleY)
    {
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY); // 长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }
}
