package com.udit.frame.common.cancas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class HandTouchView extends View {
    public Handler mbitmaphHandler;
    public Handler getMbitmaphHandler() {
        return mbitmaphHandler;
    }
    public void setMbitmaphHandler(Handler mbitmaphHandler) {
        this.mbitmaphHandler = mbitmaphHandler;
    }
    
    private DisplayMetrics dm;
    private Bitmap bitmap=null;
    private Bitmap mBitmap=null;
    private Bitmap myBitmap ;
    private Paint mPaint=null;
    private Canvas mCanvas=null;
    private Paint mBitmapPaint=null;
    private Timer timer=null;
    private savePath sPath;
    private List<savePath> lists =null;
    private FingerMatrix fingerMatrix=null;
    private float Xi,Yi;
    private Path path=null;
    public HandTouchView(Context context) {
        super(context);
        dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        inter(dm.widthPixels, dm.heightPixels);
        
    }

    public HandTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
      //  dm = new DisplayMetrics();
      //  ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
     //   inter(dm.widthPixels, dm.heightPixels);
        
        
    }

    public HandTouchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        inter(dm.widthPixels, dm.heightPixels);
        
        
    }
    
    /**
     * ?????????
     * @param w
     * @param h
     */
    public void inter(int w,int h){
        mBitmap =Bitmap.createBitmap(w, h, Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        path = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);// ???????????????
        mPaint.setStrokeCap(Paint.Cap.SQUARE);// ??????
        mPaint.setStrokeWidth(15);// ????????????
        mPaint.setColor(FingerMatrix.colorValue);
        lists = new ArrayList<savePath>();
        fingerMatrix = new FingerMatrix();
        timer = new Timer(true);                //?????????????????????
    }
    
    public void minter(int w,int h){
        mBitmap =Bitmap.createBitmap(w, h, Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        path = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);// ???????????????
        mPaint.setStrokeCap(Paint.Cap.SQUARE);// ??????
        mPaint.setStrokeWidth(15);// ????????????
        mPaint.setColor(FingerMatrix.colorValue);
        lists = new ArrayList<savePath>();
        fingerMatrix = new FingerMatrix();
        timer = new Timer(true);                
    }
    
    public void RefreshPaint(){
        path = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);// ???????????????
        mPaint.setStrokeCap(Paint.Cap.SQUARE);// ??? ???
        mPaint.setStrokeWidth(15);// ????????????
        mPaint.setColor(Color.RED);
    }
    
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        if (path!=null) {
            canvas.drawPath(path, mPaint);
        }
        super.onDraw(canvas);
    }
    
    
    /**
     * ?????? ????????????
     */
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:           //???????????????
            mPaint.setColor(FingerMatrix.colorValue);
            float x = event.getX();
            float y = event.getY();
            if (task!=null) {           //???????????????
                task.cancel();          //???????????????////?????????????????????????????? 
                task = new TimerTask() {
                    public void run() {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);       //??????handler
                    }
                };
            }
            Log.i("??????", "??????????????????"+x+"y??????"+y);
            if (fingerMatrix==null) {
                fingerMatrix = new FingerMatrix();
                Log.i("??????", "<staRT>fingerMatrix?????????");
                fingerMatrix.init(x, y);
            }else {
                fingerMatrix.getx(x);
                Log.i("??????", "<?????????>fingerMatrix?????????");
                fingerMatrix.getY(y);
            }
            path = new Path();
            sPath =new savePath();
            sPath.paint = mPaint;       //????????????
            sPath.path = path;
            path.moveTo(x, y);          //????????????
            Xi = x;
            Yi = y;
            invalidate();           //??????
            break;
        case MotionEvent.ACTION_MOVE:           //?????????
            float X1 = event.getX();
            float Y1 = event.getY();
            if (task!=null) {
                task.cancel();
                task = new TimerTask() {
                    public void run() {
                        Message message = new Message();
                        message.what=1;
                        handler.sendMessage(message);
                    }
                };
                
            }
            if (fingerMatrix!=null) {
                fingerMatrix.getx(X1);
                fingerMatrix.getY(Y1);
                Log.i("??????", "fingerMatrix????????????");
            }
            float j = Math.abs(X1-Xi);
            float i = Math.abs(Yi-Y1);
            if (j>=5||i>=5) {
                path.quadTo(Xi, Yi, X1, Y1);
                Xi = X1;
                Yi = Y1;
            }
            invalidate();
            break;
        case MotionEvent.ACTION_UP:         //???????????????????????????
            float My = event.getX();        //??????????????????????????????
            float Mx = event.getY();        
            if (fingerMatrix!=null) {
                fingerMatrix.getx(Mx);
                Log.i("??????", "??????"+Mx);
                fingerMatrix.getY(My);
            }
            mCanvas.drawPath(path, mPaint);
            lists.add(sPath);
            invalidate();
            if (timer!=null) {
                if (task!=null) {
                    task.cancel();
                    task = new TimerTask() {
                        public void run() {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                    };
                    timer.schedule(task, 2200, 2200);               //2200?????????????????????handler??????Activity
                }
            }else {
                timer = new Timer(true);
                timer.schedule(task, 2200, 2200);                   //2200?????????????????????handler??????Activity
            }
            break;
        }
        return true;
    }
    
    
    /**
     * ??????????????????
     */
    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {         //handler??????Activity????????????
            case 1:                     //CUT_BITMAP_SEND_TO_ACTIVITY//?????????????????????Activity??????
                Log.i("??????", "handler??????");
                myBitmap = mBitmap;         //?????????????????????
                if (fingerMatrix!=null) {           //???????????????????????????
                    myBitmap = cutBitmap(myBitmap);                 //??????Bitmap????????????
                }
                fingerMatrix=null;      
                saveBiamtImate(myBitmap);
                Message message = new Message();
                message.what=1;
                Bundle bundle = new Bundle();;
                bundle.putParcelable("bitmap",myBitmap);
                message.setData(bundle);
                mbitmaphHandler.sendMessage(message);
                RefreshCanvas();
                break;
            }
            super.handleMessage(msg);
        }
    };
    
    
    /**
     * ???????????????handler??????ACTIVITY       
     */
    TimerTask task = new TimerTask() {
        public void run() {
            Message message = new Message();
            message.what=1;
            Log.i("??????", "??????");
            handler.sendMessage(message);
        }
    };
    
    /**
     * ???????????????
     * @author Administrator
     */
    class savePath{
        Paint paint;
        Path path;
    }
    
    /**
     * ???????????????????????????Activity
     * @param bnBitmap
     */
    public Bitmap cutBitmap(Bitmap bnBitmap){
        //??????
        float minx = fingerMatrix.getMinX();        //?????????x??????
        float miny = fingerMatrix.getMinY();        //?????????y??????
        //??????
        float maxy = fingerMatrix.getMaxY();        //?????????Y??????
        float maxX = fingerMatrix.getMaxX();        //?????????x??????
        Log.i("","???????????????????????????-======??????X:"+maxX+"----====??????Y:"+maxy+"----====??????X:"+minx+"----====??????Y:"+miny); 
        int cutMinX = (int)(minx-15);       //??????????????????
        int cutMinY = (int)(miny-15);       //??????????????????
        int cutMaxX = (int)(maxX+15);
        int cutMaxY = (int)(maxy+15);
        if (cutMinX<=0) {
            cutMinX=0;
        }
        if (cutMinY<=0) {
            cutMinY=0;
        }
        if (cutMaxX>mBitmap.getWidth()) {           //??????X???????????????????????????
            cutMaxX =  mBitmap.getWidth()-1;        //??????????????????????????????X?????? ????????????
        }
        if (cutMaxY>mBitmap.getHeight()) {          //??????Y???????????????????????????
            cutMaxY = mBitmap.getHeight()-1;        //??????????????????????????????Y?????? ????????????
        }
        
        int width =(int)(cutMaxX - cutMinX);        //?????????x?????????????????????x??????
        int height =(int)(cutMaxY-cutMinY);         //?????????Y?????????????????????y??????
        
        Bitmap wBitmap =Bitmap.createBitmap(bnBitmap, cutMinX, cutMinY, width, height);     //????????????????????????????????????????????????????????????
        if (myBitmap!=null ) {
            myBitmap.recycle();
            myBitmap= null;
        }
        Log.i("??????", "??????????????????");
        return wBitmap;
    }
    
    /**
     * ????????????
     * @param bitmap1
     */
    public void saveBiamtImate(Bitmap bitmap1){
        String fileUrl = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/save.png";
            try {
                FileOutputStream fos = new FileOutputStream(new File(fileUrl));
                bitmap1.compress(CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    
    /**
     * ????????????
     */
    public void RefreshCanvas(){
        Log.i("??????", "????????????????????? ????????????");
        if (lists!=null&&lists.size()>0) {
            lists.remove(lists);
            if (mBitmap!=null) {
                mBitmap=null;
            }
            path=null;
//          inter(dm.widthPixels,dm.heightPixels);
            inter(dm.widthPixels,dm.heightPixels);
            
            Log.i("??????", "?????????????????????");
            invalidate();
        }
        if (task!=null) {
            task.cancel();
        }
    }
}
