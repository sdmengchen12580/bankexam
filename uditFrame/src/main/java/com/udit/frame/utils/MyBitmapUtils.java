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
    
    // ?????? bitmap ???SD???F
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
        // ??? drawable ?????????
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        
        // ??? drawable ???????????????
        Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565;
        // ???????????? bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // ???????????? bitmap ?????????
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // ??? drawable ?????????????????????
        drawable.draw(canvas);
        return bitmap;
    }
    
    /**
     * ????????????????????????
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
     * @return ??????????????????????????????????????????
     */
    public static float getFontlength(Paint paint, String str)
    {
        return paint.measureText(str);
    }
    
    /**
     * @return ??????????????????????????????
     */
    public static float getFontHeight(Paint paint)
    {
        FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }
    
    /**
     * @return ?????????????????????????????????????????????
     */
    public static float getFontLeading(Paint paint)
    {
        FontMetrics fm = paint.getFontMetrics();
        return fm.leading - fm.ascent;
    }
    
    /**
     * ??????????????????
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
    {// ???????????????????????????????????????????????????????????????Base64????????????
        String imgFile = path;// ??????????????????
        InputStream in = null;
        byte[] data = null;
        // ????????????????????????
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
        // ???????????????Base64??????
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// ??????Base64?????????????????????????????????
    }
    
    public static boolean GenerateImage(String imgStr, String savePath)
    {// ??????????????????????????????Base64?????????????????????
        if (imgStr == null) // ??????????????????
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            // Base64??????
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i)
            {
                if (b[i] < 0)
                {// ??????????????????
                    b[i] += 256;
                }
            }
            // ??????jpeg??????
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
     * ???????????????????????????????????????
     * <??????????????????>
     * 
     * @param bitmap
     * @return
     * @see [?????????#????????????#??????]
     */
    public static Bitmap AdjustBitmap(Bitmap bitmap, float scaleX, float scaleY)
    {
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY); // ??????????????????????????????
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }
}
